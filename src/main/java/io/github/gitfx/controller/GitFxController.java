/**
 * Copyright 2015 GitFx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.gitfx.controller;

import io.github.gitfx.Dialog.GitFxDialog;
import io.github.gitfx.Dialog.GitFxDialogResponse;
import io.github.gitfx.GitFxApp;
import io.github.gitfx.GitResourceBundle;
import io.github.gitfx.util.GitFXGsonUtil;
import io.github.gitfx.util.WorkbenchUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class GitFxController implements Initializable {
    
   
    @FXML 
    private Button gitclone;
    @FXML 
    private Button gitsettings;
    @FXML
    private MenuItem gitSyncEverything;
    @FXML
    private Button gitinit;
    @FXML
    private Button gitopen;
    @FXML
    private MenuItem gitSpecific;
    @FXML
    private MenuButton gitsync;
    
    private GitFxDialog dialog;  
    // Reference to the main application
    private GitFxApp gitFxApp;
    private GitResourceBundle resourceBundle;
    /**
     * Reference to GitFxApp. 
     * @param gitFxApp 
     */
    public void setMainApp(GitFxApp gitFxApp) {
        this.gitFxApp = gitFxApp;
        resourceBundle=new GitResourceBundle();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image gitCloneGraphic = new Image(getClass().getResourceAsStream("/icons/clone.gif"));
        gitclone.setGraphic(new ImageView(gitCloneGraphic));
        Image gitSettingsGraphic = new Image(getClass().getResourceAsStream("/icons/settings.gif"));
        gitsettings.setGraphic(new ImageView(gitSettingsGraphic));
        Image gitSyncGraphic = new Image(getClass().getResourceAsStream("/icons/sync.gif"));
        gitsync.setGraphic(new ImageView(gitSyncGraphic));
        Image gitOpenGraphic = new Image(getClass().getResourceAsStream("/icons/open.gif"));
        gitopen.setGraphic(new ImageView(gitOpenGraphic));
        Image gitInitGraphic = new Image(getClass().getResourceAsStream("/icons/git.gif"));
        gitinit.setGraphic(new ImageView(gitInitGraphic));
    }    
    
    
    @FXML
    public void onGitSettingsClicked(ActionEvent event){
    }
    
    @FXML
    public void onGitCloneClicked(ActionEvent event){
        dialog = new GitFxDialog();
        Pair<String,String> clonedRepo = dialog.GitCloneDialog(resourceBundle.getString("cloneRepo"), 
                          resourceBundle.getString("cloneRepo"),
                          null);
        if(dialog.getResponse()==GitFxDialogResponse.CLONE&&clonedRepo!=null){
            System.out.println("Response Ok Repo Path");
            System.out.println("Project Name"+clonedRepo.getKey());
            System.out.println("Local Path"+clonedRepo.getValue());
        }
        else{
            System.out.println("Response Cancel");
        }
    }
    
    @FXML
    public void onGitOpenClicked(ActionEvent event){
        dialog = new GitFxDialog();
        String repoPath = dialog.GitOpenDialog(resourceBundle.getString("openRepo"), 
                          resourceBundle.getString("chooseRepo"),
                          resourceBundle.getString("repo"));
        if(dialog.getResponse()==GitFxDialogResponse.OK){
            System.out.println("Response Ok Repo Path"+repoPath);
        }
        else{
            System.out.println("Response Cancel");
        }
    }
    
    @FXML
    public void onGitSyncClicked(ActionEvent event){
    }
    
    @FXML
    public void syncEveryThingClicked(ActionEvent event){
        dialog = new GitFxDialog();
        dialog.GitConfirmationDialog(resourceBundle.getString("sync"),
                                     resourceBundle.getString("syncAll"),
                                     resourceBundle.getString("syncAllDesc"));
        if(dialog.getResponse()==GitFxDialogResponse.OK){
            System.out.println("Sync all clicked");
        }
        else{
            System.out.println("Cancelled");
        }
    }
  
    @FXML
    public void onGitInitClicked(ActionEvent event){
        dialog = new GitFxDialog();
        Pair<String,String> newRepo=dialog.GitInitDialog(resourceBundle.getString("initRepo"),
                             resourceBundle.getString("initRepo"),
                             null);
        if(dialog.getResponse()==GitFxDialogResponse.INITIALIZE&&newRepo!=null){
            System.out.println("Git init clicked");
            System.out.println("Project Name"+newRepo.getKey());
            System.out.println("Local Path"+newRepo.getValue());
            
         
            String path = WorkbenchUtil.getGitFxWorkbenchPath();
            GitFXGsonUtil.saveRepositoryInformation("github",newRepo.getKey(),
                                              newRepo.getValue());
        }
        else{
            System.out.println("Cancelled");
        }
    } 
    
    @FXML
    public void onGitParticularRepositoryClicked(ActionEvent event){
        //TODO Implement this feature in GitFxDialog
        dialog = new GitFxDialog();
        //TODO Get the string from the resource bundle
        dialog.GitInformationDialog("Sync Repositories", "Repositories", null);
    }
    
}
