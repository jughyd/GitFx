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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

/**
 *
 * @author rvvaidya
 */
public class GitRepoMetaData {

    Repository repository;
    RevCommit commit;
    RevWalk walk;
    ArrayList<String> shortMessage;
    ArrayList<ArrayList<String>> changedFile;
    //There should be a better way to get this count
    int commitCount = 0;

    public GitRepoMetaData() {
        shortMessage = new ArrayList<>();
        changedFile = new ArrayList<>();
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

    public ArrayList<String> getShortMessage() {
        for (RevCommit revision : walk) {
            shortMessage.add(revision.getShortMessage());
            commitCount++;
            try {
                RevTree tree = revision.getTree();
                TreeWalk treeWalk = new TreeWalk(repository);
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                ArrayList<String> commitFiles = new ArrayList<>();
                while (treeWalk.next()) {
                    //System.out.println(treeWalk.getPathString());
                    commitFiles.add(treeWalk.getPathString());
                }
                changedFile.add(commitFiles);
            } catch (IncorrectObjectTypeException ex) {
                Logger.getLogger(GitRepoMetaData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CorruptObjectException ex) {
                Logger.getLogger(GitRepoMetaData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GitRepoMetaData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        walk.reset();
        return shortMessage;
    }

    public ArrayList<ArrayList<String>> getCommitFiles() {
        return changedFile;
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
