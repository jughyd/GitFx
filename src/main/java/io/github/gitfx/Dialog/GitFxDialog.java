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

package io.github.gitfx.Dialog;

import com.github.daytron.simpledialogfx.data.DialogType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.sun.glass.events.MouseEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Concrete class which does GitDialog implementation
 * @author rvvaidya
 */
public class GitFxDialog extends Stage implements Initializable,GitDialog   {

    private GitFxDialogResponse response;
    private Dialog dialog;
    private String tempResponse;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void GitDialog(Exception e) {
        dialog=new Dialog(e);
        dialog.showAndWait();
    }

    @Override
    public void GitDialog(GitFxDialogType type, String header, String label) {
       
       switch(type){
           //All Git specific dialogs get processed here in individual cases
           case GIT_OPEN:
                dialog=new Dialog(DialogType.INPUT_TEXT,
                header,
                label);
                Button choose = new Button();
                Scene obj= dialog.getScene();
                choose.setText("Choose");
                choose.setId("choose");
                //TODO
                //The index need to removed and instaceof needs to be placed
                //I can do this magic because I know the fxml.
                ObservableList<Node> children=((GridPane)(obj.getRoot())).getChildren();
                VBox vbox=(VBox)children.get(0);
                ObservableList<Node> vboxchild=vbox.getChildren();
                HBox hbox=(HBox)vboxchild.get(1);
                hbox.getChildren().add(choose);
                
                choose.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                       setResponse(GitFxDialogResponse.CHOOSE);
                    }
                });
                
                dialog.showAndWait();
                tempResponse=this.getResponse().toString();
                this.response=GitFxDialogResponse.valueOf(tempResponse);
                close();
                break;
           default:
                //All simple dialogs will get processed from here 
                dialog=new Dialog(
                    DialogType.valueOf(type.toString()),
                    header,
                    label);
                dialog.showAndWait();
                tempResponse=dialog.getResponse().toString();
                this.response=GitFxDialogResponse.valueOf(tempResponse);
    
       }
    }
    
    @Override
    public GitFxDialogResponse getResponse(){
        return response;
    }
    
  
    public void setResponse(GitFxDialogResponse response){
        this.response=response;
    }
           
}
