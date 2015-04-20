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
import io.github.gitfx.GitFxApp;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

/**
 *
 * @author rvvaidya
 */
public class GitFxAppTest extends FxRobot
{
    @Before
    public void before() throws Exception{
        System.out.println("before test");
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(GitFxApp.class);
        FxToolkit.showStage();
    }
    @Test
    public void launchApplication() throws Exception{
        //TODO Write test to test the GitFx application flows
    }
    
}
