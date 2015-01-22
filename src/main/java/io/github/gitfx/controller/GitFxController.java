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

import io.github.gitfx.GitFxApp;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    
    // Reference to the main application
    private GitFxApp gitFxApp;

    /*@FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    
    /**
     * Reference to GitFxApp. 
     * @param gitFxApp 
     */
    public void setMainApp(GitFxApp gitFxApp) {
            this.gitFxApp = gitFxApp;
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
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Clone Repository");
        dialog.setHeaderText("Clone Repository");
        ImageView icon = new ImageView(this.getClass().getResource("/icons/init.png").toString());
        icon.setFitHeight(20);
        icon.setFitWidth(20);
        dialog.setGraphic(icon);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField projectName = new TextField();
        projectName.setPromptText("Remote Repo URL");
        TextField localPath = new TextField();
        localPath.setPromptText("Local Path");
        grid.add(new Label("Remote Repo URL:"), 0, 0);
        grid.add(projectName, 1, 0);
        grid.add(new Label("Local Path:"), 0, 1);
        grid.add(localPath, 1, 1);
        ButtonType choose = new ButtonType("Choose");
        ButtonType clone = new ButtonType("Clone");
        ButtonType cancel = new ButtonType("Cancel");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(choose,clone,cancel);
        dialog.showAndWait();
    }
    
    @FXML
    public void onGitOpenClicked(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("Repository Path");
        dialog.setTitle("Open Repository");
        dialog.setHeaderText("Choose the repsitory path");
        dialog.setContentText("Repo:");
        ButtonType choose = new ButtonType("Choose");
        dialog.getDialogPane().getButtonTypes().add(choose);
        Optional<String> result=dialog.showAndWait();
        result.ifPresent(name -> System.out.println("Selected repo: " + name));
    }
    
    @FXML
    public void onGitSyncClicked(ActionEvent event){
    }
    
    @FXML
    public void syncEveryThingClicked(ActionEvent event){
        Alert syncEverything = new Alert(AlertType.CONFIRMATION);
        Image syncGraphic = new Image(getClass().getResourceAsStream("/icons/gitsync.png"));
        syncEverything.setGraphic(new ImageView(syncGraphic));
        syncEverything.setTitle("Sync");
        syncEverything.setHeaderText("Sync Everything");
        syncEverything.setContentText("This will sync all repositories on all servers");
        Optional<ButtonType> result = syncEverything.showAndWait();
        if(result.get()==ButtonType.OK){
            //Sync Action
        }
    }
  
    @FXML
    public void onGitInitClicked(ActionEvent event){
        //gitFxApp.showGitInitDialog();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Initialize Repository");
        dialog.setHeaderText("Initialize Repository");
        ImageView icon = new ImageView(this.getClass().getResource("/icons/init.png").toString());
        icon.setFitHeight(20);
        icon.setFitWidth(20);
        dialog.setGraphic(icon);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField projectName = new TextField();
        projectName.setPromptText("Project Name");
        TextField localPath = new TextField();
        localPath.setPromptText("Local Path");
        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(projectName, 1, 0);
        grid.add(new Label("Local Path:"), 0, 1);
        grid.add(localPath, 1, 1);
        ButtonType choose = new ButtonType("Choose");
        ButtonType initRepo = new ButtonType("Initialize Repository");
        ButtonType cancel = new ButtonType("Cancel");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(choose,initRepo,cancel);
        dialog.showAndWait();
    } 
    
    @FXML
    public void onGitParticularRepositoryClicked(ActionEvent event){
        gitFxApp.showSyncDialog();
    }
    
}
