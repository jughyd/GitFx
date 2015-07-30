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
 * Created by rvvaidya on 31/07/15.
 */
import io.github.gitfx.GitFxApp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.junit.After;
import org.testfx.util.WaitForAsyncUtils;
import org.junit.Assert;
import javafx.scene.Node;
public class GitFxApplicationLaunchTest extends FxRobot {
    Stage stage;
    Scene scene;
    Node root;
    @Before
    public void before() throws Exception {
        stage=FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(GitFxApp.class);
        scene= stage.getScene();

    }
    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }
    @Test
    public void  launchApplication() throws Exception {
        WaitForAsyncUtils.waitForFxEvents();
        Button gitinit = (Button)scene.lookup("#gitinit");
        Assert.assertEquals("\uf04b",gitinit.getText());
        Button gitclone = (Button)scene.lookup("#gitclone");
        Assert.assertEquals("\uF0C5",gitclone.getText());
    }
}
