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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GitFxController implements Initializable {
    
   
    @FXML 
    private Button gitclone;
    @FXML 
    private Button gitsettings;
    @FXML
    private Button gitsync;
    @FXML
    private Button gitinit;
    @FXML
    private Button gitopen;
    
    /*@FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    
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
    }
    
    @FXML
    public void onGitOpenClicked(ActionEvent event){
    }
    
    @FXML
    public void onGitSyncClicked(ActionEvent event){
    }
    
    @FXML
    public void onGitInitClicked(ActionEvent event){
    }
    
    
    
    
}
