/**
 * Copyright 2015 GitFx
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.gitfx.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author rvvaidya
 */
public class GitRepoMetaData {

    Logger logger = LoggerFactory.getLogger(GitRepoMetaData.class.getName());

    Repository repository;
    RevCommit commit;
    RevWalk walk;
    ArrayList<String> shortMessage;
    ArrayList<ArrayList<String>> commitHistory;
    ArrayList<String> tempCommitHistory;

    //There should be a better way to get this count
    int commitCount = 0;

    public GitRepoMetaData() {
        shortMessage = new ArrayList<>();
        commitHistory = new ArrayList<ArrayList<String>>();
        tempCommitHistory = new ArrayList<>();
    }

    public void setRevWalk(RevWalk walk) {
        this.walk = walk;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setRevCommit(RevCommit commit) {
        this.commit = commit;
    }

    //Gets the short messages to be printed on Titled Pane
    //Also populates the commitHistory container to show history in the accordion
    public ArrayList<String> getShortMessage() {
        for (RevCommit revision : walk) {
            shortMessage.add(revision.getShortMessage());
            logger.debug(revision.getShortMessage());
            DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
            df.setRepository(repository);
            df.setDiffComparator(RawTextComparator.DEFAULT);
            df.setDetectRenames(true);
            RevCommit parent = null;
            if(revision.getParentCount()!=0) {
                try {
                    parent = walk.parseCommit(revision.getParent(0).getId());
                    RevTree tree = revision.getTree();
                    List<DiffEntry> diffs = df.scan(parent.getTree(), revision.getTree());
                    for (DiffEntry diff : diffs) {
                        logger.debug(diff.getChangeType().name());
                            logger.debug(diff.getNewPath());
                            tempCommitHistory.add(diff.getNewPath());
                    }
                }catch (IOException ex) {
                    logger.debug("IOException", ex);
                }
            }
            commitHistory.add(commitCount++,new ArrayList<String>(tempCommitHistory));
            tempCommitHistory.clear();
        }
        walk.reset();
        return shortMessage;
    }

    public ArrayList<ArrayList<String>> getCommitFiles() {
        return commitHistory;
    }

    public String getRepoName() {
        String repoPath = repository.getDirectory().getParent();
        int index = repoPath.lastIndexOf("/");
        return repoPath.substring(index + 1);
    }

    //Gets commit count for this repository
    public int getCommitCount() {
        return commitCount;
    }
}
