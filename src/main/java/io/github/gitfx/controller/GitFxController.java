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
import io.github.gitfx.data.GitRepoMetaData;
import io.github.gitfx.data.ProjectData;
import io.github.gitfx.data.RepositoryData;
import io.github.gitfx.util.GitFXGsonUtil;
import io.github.gitfx.util.WorkbenchUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Font;
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
    @FXML
    private TreeView<String> RepositoryTree;
    @FXML
    private Accordion historyAccordion;
   
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
        Font.loadFont(GitFxController.class.getResource("/fonts/fontawesome-webfont.ttf").toExternalForm(),12);
        gitinit.setText('\uf04b'+"");
        gitopen.setText('\uf07c'+"");
        gitsettings.setText('\uf013'+"");
        gitsync.setText('\uf021'+"");
        gitclone.setText('\uf0c5'+"");
        initializeTree();
    }    
   /*
    * Method which initializes the Repository Tree Panel
    * Possible clients calls from
    * 1) Applicaiton Initialize event
    * 2) Initialization of a New Repository
    */
    private void initializeTree(){
        RepositoryData metaData=GitFXGsonUtil.getRepositoryMetaData();
        if(metaData!=null){
            TreeItem<String> treeRoot= new TreeItem<String>(metaData.getServerName());
            List<ProjectData> projectData=metaData.getRepositories();
            for(ProjectData project:projectData){
                 treeRoot.getChildren().add(new TreeItem<String>(project.getProjectName()));
            }
            RepositoryTree.setShowRoot(true);
            RepositoryTree.setRoot(treeRoot);
        } 
    }
    private void initializeHistoryAccordion(GitRepoMetaData metaData){
        ObservableList<TitledPane> panes= historyAccordion.getPanes();
        if(panes!=null){
            historyAccordion.getPanes().removeAll(panes);
        }
        ArrayList<String> list=metaData.getShortMessage();
        TitledPane pane;
        for(String str:list){
          pane= new TitledPane(str,null);
          historyAccordion.getPanes().add(pane);
        }
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
            GitRepoMetaData metaData=GitFXGsonUtil.getGitRepositoryMetaData(repoPath);
            initializeHistoryAccordion(metaData);
            /*ArrayList<String> list=metaData.getShortMessage();
            for(String str:list){
                System.out.println(str);
            }*/
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
            initializeTree();
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
