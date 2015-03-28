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
        }
        walk.reset();
        return shortMessage;
    }

    public ArrayList<ArrayList<String>> getCommitFiles() {
        ArrayList<ArrayList<String>> consolidated=null;
        ArrayList<String> commitFiles = new ArrayList<String>();
        TreeWalk treeWalk = new TreeWalk(repository);
        int i=0;
        try {
            //walk.markStart(walk.parseCommit(repository.resolve("HEAD")));
            for (RevCommit revision : this.walk) {
                RevTree tree = revision.getTree();
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                while (treeWalk.next()) {
                    commitFiles.add(treeWalk.getPathString());
                }
                consolidated.add(i++,commitFiles);
            }
        } catch (IOException exception) {
            System.out.println("Exception");
        }
        return consolidated;
    }
}
