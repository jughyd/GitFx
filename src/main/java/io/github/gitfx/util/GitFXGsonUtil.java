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
package io.github.gitfx.util;

import java.io.File;
import java.io.FileOutputStream;
import com.google.gson.Gson;
import io.github.gitfx.dialog.GitFxDialog;
import io.github.gitfx.data.GitRepoMetaData;
import io.github.gitfx.data.RepositoryData;
import static io.github.gitfx.util.WorkbenchUtil.GITFX_WORKBENCH_RECENT_REPO_FILE;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import javafx.application.Platform;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/**
 *
 * @author rvvaidya
 */
public final class GitFXGsonUtil {

    static Logger logger = LoggerFactory.getLogger(GitFXGsonUtil.class.getName());
    //public static final String GitFxRepo = "GitFxRepo.json";
    /*
     *  Passivate the String JSON to a file on disk to store repository 
     *  information. 
     */

    public static void passivateJSON(String json) {
        FileOutputStream outputStream = null;
        File file = new File(GITFX_WORKBENCH_RECENT_REPO_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            //The JSON file will containt just one JSON object hence 
            //not appending to End of file. 
            outputStream = new FileOutputStream(file, Boolean.FALSE);
            byte[] contentInBytes = json.getBytes();
            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.debug("Error Passivating JSON", e);
        }
    }
    /*
     * Utility method which saves Repo meta data to file on disk. 
     */

    public static void saveRepositoryInformation(String serverName,
            String projectName, String projectPath) {
        Gson gson = new Gson();
        RepositoryData passivatedMetaData = getRepositoryMetaData();
        if (passivatedMetaData == null) {
            passivatedMetaData = new RepositoryData();
        }
        //TODO Hardcoded server shall be removed latter
        passivatedMetaData.setServerName("github");
        //TODO: Validations for existing repository names
        /*List<ProjectData> projectDetails = passivatedMetaData.getRepositories();
         for(ProjectData project:projectDetails){
            
         }*/
        initializeGitRepository(serverName, projectName, projectPath);
        passivatedMetaData.setProjectData(projectName, projectPath);
        passivateJSON(gson.toJson(passivatedMetaData));
    }
    
    public static void initializeGitRepository(String serverName,
            String projectName,String projectPath){
        try{
        File localPath = new File(projectPath);
        Git git;
                git = Git.init().setDirectory(localPath).call();
        git.close();
        }
        catch(GitAPIException e){
            logger.debug("Error creating Git repository",e.getMessage());
        }
    }
    public static boolean cloneGitRepository(String repoURL,String localPath){
        try{
        Git result = Git.cloneRepository()
                .setURI(repoURL)
                .setDirectory(new File(localPath))
                .call();
        result.close();
        return true;
        }
        catch(GitAPIException e){
            logger.debug("Error creating Git repository",e.getMessage());
            return false;
        }
    }

    /*
     * Utility method which parses JSON on disk returns the server details 
     */
    public static RepositoryData getRepositoryMetaData() {
        RepositoryData repoMetaData = new RepositoryData();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(GITFX_WORKBENCH_RECENT_REPO_FILE))) {
            StringBuffer buffer = new StringBuffer();
            String temp;
            //Read JSON from Disk
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
            if (buffer != null) {
                temp = buffer.toString();
                repoMetaData = gson.fromJson(temp.replace("\"", "'"), RepositoryData.class);
                return repoMetaData;
            }
        } catch (IOException e) {

            logger.debug("IOException", e);
        }
        return null;
    }

    //Utility method that checks for the presence of json on disk
    public static boolean checkRepoInformation() {
        File file = new File(GITFX_WORKBENCH_RECENT_REPO_FILE);
        if (file.exists()) {
            return true;
        } else {
            new Thread() {
                public void run() {
                    Platform.runLater(() -> {
                        GitFxDialog dialog = new GitFxDialog();
                        dialog.GitInformationDialog("No Repository Linked", "Click Init to add your first Repository", "Have fun!!!");
                    });
                }
            }.start();
            return false;
        }
    }
    /*
     * Utility method which gets repository path and returns GitRepoMetaData object
     */

    public static GitRepoMetaData getGitRepositoryMetaData(String repoPath) {
        try {
            if(!repoPath.endsWith(".git"))
                repoPath = repoPath+"/.git";
            System.out.println("repopath:  "+repoPath);
            GitRepoMetaData gitMetaData = new GitRepoMetaData();
            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repository = builder.setGitDir(new File(repoPath))
                    .readEnvironment()
                    .setMustExist(true)
                    .findGitDir()
                    .build();
            RevWalk walk = new RevWalk(repository);
            AnyObjectId id = repository.resolve("HEAD");
            if(id == null)
                return null;//Empty repository
            RevCommit rCommit =walk.parseCommit(id);
            walk.markStart(rCommit);
            gitMetaData.setRepository(repository);
            gitMetaData.setRevWalk(walk);
            return gitMetaData;
        } catch (IOException exception) {
            logger.debug("IOException getGitRepositoryMetaData", exception);
        };
        return null;
    }
}
