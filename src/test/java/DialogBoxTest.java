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
import io.github.gitfx.Dialog.GitFxDialog;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author rvvaidya
 */
public class DialogBoxTest extends ApplicationTest {
    AnchorPane anchor;
    GitFxDialog dialog;
    @Override
    public void start(Stage stage) throws Exception {
        anchor = new AnchorPane();
        Scene scene = new Scene(anchor,400,400);
        dialog = new GitFxDialog();
        dialog.GitInformationDialog("Information Title","SampleHeader","Test Label");
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void testDialog() throws Exception{
        //TODO test code to test dialog. 
    }
}