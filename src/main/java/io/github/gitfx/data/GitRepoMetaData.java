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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;


import com.jcabi.aspects.Loggable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rvvaidya
 */
@Loggable
public class GitRepoMetaData {

//[LOG] Logger logger = LoggerFactory.getLogger(GitRepoMetaData.class.getName());

    Repository repository;
    RevCommit commit;
    RevWalk walk;
    ArrayList<String> shortMessage;
    ArrayList<ArrayList<String>> commitHistory;
    ArrayList<String> tempCommitHistory;
    ArrayList<String> commitSHA;
    final String ADD = "ADD";
    final String MODIFY= "MODIFY";
    final String DEL ="DEL";
    String diff = null;
    //There should be a better way to get this count
    int commitCount = 0;

    public GitRepoMetaData() {
        shortMessage = new ArrayList<>();
        commitHistory = new ArrayList<ArrayList<String>>();
        tempCommitHistory = new ArrayList<>();
        commitSHA = new ArrayList<>();
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
//[LOG]            logger.debug(revision.getShortMessage());
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
                        String changeType = diff.getChangeType().name();
                        if(changeType.equals(ADD)|| changeType.equals(MODIFY))
                        {
//[LOG]                            logger.debug(diff.getChangeType().name());
//[LOG]                            logger.debug(diff.getNewPath());
                            tempCommitHistory.add(diff.getNewPath());
                        }
                    }
                }catch (IOException ex) {
//[LOG]                    logger.debug("IOException", ex);
                }
            }
            commitSHA.add(commitCount,revision.name());
            commitHistory.add(commitCount++,new ArrayList<String>(tempCommitHistory));
            tempCommitHistory.clear();
        }
        walk.reset();
        return shortMessage;
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException,
            MissingObjectException,
            IncorrectObjectTypeException {
        RevWalk walk = new RevWalk(repository) ;
        RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
        RevTree tree = walk.parseTree(commit.getTree().getId());
        CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
        ObjectReader oldReader = repository.newObjectReader();
        oldTreeParser.reset(oldReader, tree.getId());
        walk.dispose();
        return oldTreeParser;
    }
    //Given a commit index this API returns the diff between a commit and its parent.
    //Assuming only 1 parent.
    public String getDiffBetweenCommits(int commitIndex) throws IOException,GitAPIException{
        if(commitIndex+1==commitCount)
            return "Nothing to Diff. This is first commit";
        AbstractTreeIterator current = prepareTreeParser(repository,commitSHA.get(commitIndex));
        AbstractTreeIterator parent = prepareTreeParser(repository,commitSHA.get(++commitIndex));
        ObjectReader reader = repository.newObjectReader();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        // finally get the list of changed files
        Git git = new Git(repository) ;
        List<DiffEntry> diff = git.diff().
                setOldTree(parent).
                setNewTree(current).
                //TODO Set the path filter to filter out the selected file
                //setPathFilter(PathFilter.create("README.md")).
                call();
        for (DiffEntry entry : diff) {
            System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
            DiffFormatter formatter = new DiffFormatter(byteStream) ;
                formatter.setRepository(repository);
                formatter.format(entry);
            }
       // System.out.println(byteStream.toString());
        String diffContent = byteStream.toString();
        return byteStream.toString();
    }

    public ArrayList<ArrayList<String>> getCommitFiles() {
        return commitHistory;
    }

    public ArrayList<String> getCommitRef(){
        return commitSHA;
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
