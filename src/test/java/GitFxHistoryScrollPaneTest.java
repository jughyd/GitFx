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
 *
 * Created by rvvaidya on 03/08/15.
 */
import io.github.gitfx.GitFxApp;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.util.WaitForAsyncUtils;


public class GitFxHistoryScrollPaneTest extends ApplicationTest  {
    Stage stage;
    Scene scene;

    @Override
    public void init()
            throws Exception{
        stage=launch(GitFxApp.class,null);
    }
    @Override
    public void stop() throws Exception{
        FxToolkit.hideStage();
    }
    @Test
    public void  launchApplication() throws Exception {
        WaitForAsyncUtils.waitForFxEvents();
        ScrollPane scrollPane = (ScrollPane)scene.lookup("#historyScrollPane");
        Parent container = scrollPane.getParent();
        //ScrollPane below a VBox causes the accordion not to scroll correctly
        Assert.assertFalse("ScrollPane inside VBox",(container instanceof VBox)?true:false);
    }
    @Override
    public void start(Stage stage) throws Exception {
        scene=stage.getScene();
    }
}
