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
import io.github.gitfx.GitFxApp;
import java.io.File;

import io.github.gitfx.dialog.GitFxDialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.util.WaitForAsyncUtils;
/**
 *
 * @author rvvaidya
 */
public class GitFxAppTest extends ApplicationTest {
    File metaDataJSON;
    Stage stage;
    GitFxDialog gitFxDialog;
    @Override
    public void init()
            throws Exception{
        stage=launch(GitFxApp.class,null);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void stop() throws Exception{
        FxToolkit.hideStage();
    }
    /*
    @Before
    public void before() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(GitFxApp.class);
    }
    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }*/
    @Test
    public void launchApplication() throws Exception {
        WaitForAsyncUtils.waitForFxEvents();
        when:
        //Conditionally executing as the initial dialog box is shown only of
        //no repositories are linked with the application
        metaDataJSON = new File(io.github.gitfx.util.WorkbenchUtil.GITFX_WORKBENCH_RECENT_REPO_FILE);
        if(!metaDataJSON.exists())
          clickOn("OK");
        FxToolkit.showStage();
        then:
        //Temprarily these clickOn are also working as asserts for presence of
        //UI element. Need to add some more TestFX asserts. 
        clickOn("#gitsync");
        clickOn("#gitclone");
        clickOn("#gitinit");
        /*clickOn("Cancel");
        clickOn("#gitinit");
        clickOn("Cancel");*/
        clickOn("#gitsync");
        /*clickOn("Sync Everything");
        clickOn("OK");
        clickOn("#gitsync");
        clickOn("Sync Everything");
        clickOn("Cancel");
        clickOn("#gitsync");
        clickOn("Particular Repository");
        clickOn("OK");
        clickOn("Others");
        clickOn("Changes");
        clickOn("History");*/
    }

    @Test
    public void testBehaviorWhenFileIsNullInFileDialog() {
        gitFxDialog = new GitFxDialog();
        TextField textField = new TextField();
        gitFxDialog.getFileAndSeText(textField,null);
    }
}
