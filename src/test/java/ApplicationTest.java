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

import javafx.scene.Scene;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.toolkit.ApplicationFixture;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;

public abstract class ApplicationTest extends FxRobot implements ApplicationFixture {

    static Stage stage;
    public static Stage launch(Class<? extends Application> appClass,
                              String... appArgs) throws Exception {
        if(stage==null)
        {stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(appClass, appArgs);}
        return stage;
    }
    @Before
    public final void internalBefore()
            throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(this);
    }
    @After
    public final void internalAfter()
            throws Exception {
        FxToolkit.cleanupApplication(this);
    }
    @Override
    public void init()
            throws Exception {}

    @Override
    public abstract void start(Stage stage)
            throws Exception;

    @Override
    public void stop()
            throws Exception {}

}
