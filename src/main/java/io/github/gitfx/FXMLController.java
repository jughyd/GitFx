package io.github.gitfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLController implements Initializable {
    
   
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
}
