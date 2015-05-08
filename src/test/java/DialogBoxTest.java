
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
import io.github.gitfx.Dialog.GitFxDialog;
import javafx.application.Platform;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author rvvaidya
 */
public class DialogBoxTest extends ApplicationTest {

    GitFxDialog dialog;
    Dialog dialogHandle;
    @Override
    public void start(Stage stage) throws Exception {
        new Thread(){
             public void run(){
                 Platform.runLater(()->{
                     dialog = new GitFxDialog();
                     dialogHandle=dialog.GitInformationDialog("Information Title", "SampleHeader", "Test Label");});
             }
        }.start();
    }

    @Test
    public void testDialog() throws Exception {
        //Initial sleep to wait for the UI to load
        Thread.sleep(2000);
        clickOn("OK");
        assertTrue("Title Incorrect","Information Title".equals(dialogHandle.getTitle()));
        assertTrue("Header Incorrect","SampleHeader".equals(dialogHandle.getHeaderText()));
        assertTrue("Label Incorrect","Test Label".equals(dialogHandle.getContentText()));
    }
}
