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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
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
    int commitCount=0;
    public GitRepoMetaData() {
        shortMessage = new ArrayList<>();
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
        }
        walk.reset();
        return shortMessage;
    }

    public ArrayList<ArrayList<String>> getCommitFiles() {
        ArrayList<ArrayList<String>> consolidated = null;
        ArrayList<String> commitFiles = new ArrayList<String>();
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repo = null;
        try {
            repo = builder.setGitDir(new File("/Users/rvvaidya/Downloads/GitFx/.git"))
                    .readEnvironment()
                    .setMustExist(true)
                    .findGitDir()
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(GitRepoMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        TreeWalk treeWalk = new TreeWalk(repo);
        RevWalk localwalk = new RevWalk(repo);
        System.out.println("Repository Directory: " + repo.getDirectory());
        try {
            System.out.println("Full Branch: " + repo.getFullBranch());
        } catch (IOException ex) {
            Logger.getLogger(GitRepoMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        try {
            walk.markStart(walk.parseCommit(repo.resolve("HEAD")));
            for (RevCommit revision : localwalk) {
                RevTree tree = revision.getTree();
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                while (treeWalk.next()) {
                    commitFiles.add(treeWalk.getPathString());
                }
                consolidated.add(i++, commitFiles);
            }
        } catch (IOException exception) {
            System.out.println("Exception");
        }
        return consolidated;
    }

    public String getRepoName() {
        String repoPath = repository.getDirectory().getParent();
        int index = repoPath.lastIndexOf("/");
        return repoPath.substring(index + 1);
    }
  
    //Gets commit count for this repository
    public int getCommitCount(){
        return commitCount;
    }
}
