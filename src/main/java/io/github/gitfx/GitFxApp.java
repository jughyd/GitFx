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
package io.github.gitfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static io.github.gitfx.util.WorkbenchUtil.initializeWorkbench;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import io.github.gitfx.controller.GitFxController;
import io.github.gitfx.controller.GitOpenDialogController;

public class GitFxApp extends Application {

    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/GitFx.fxml"));
        Parent root = loader.load();
        this.stage=stage;
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        //Give controller access to GitFxApp
        GitFxController controller= loader.getController();
        controller.setMainApp(this);
        initializeWorkbench();
        
        stage.setTitle("GitFX");
        stage.setScene(scene);
        stage.show();
    }
    /**
      * Returns the main stage.
      * @return
      */
    public Stage getPrimaryStage() {
            return stage;
    }
    
    /**
     * Opens a Dialog to Open a existing or new GIT repository.
     * @return 
     */
    public boolean showGitOpenDialog(){
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GitOpenDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Open Repository");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            GitOpenDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return true;
        }
        catch(IOException io){
            io.printStackTrace();
            return false;
        }
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
