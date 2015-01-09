/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.gitfx.controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author rvvaidya
 */
public class GitOpenDialogController implements Initializable {
  
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private Button chooseRepo;
    
    private Stage dialogStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
            this.dialogStage = dialogStage;
    }
    
    @FXML
    public void onCancelClicked(ActionEvent event){
        dialogStage.close();
    }
    
}
