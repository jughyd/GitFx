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
package io.github.gitfx;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GitResourceBundle test
 *
 * @author athi
 */
public class GitResourceBundleTest {

    Logger logger;

    public GitResourceBundleTest() {
        logger = LoggerFactory.getLogger(GitResourceBundleTest.class.getName());
    }

    @Before
    public void setUp() {
        logger.info("A testcase for GitResourceBundle is starting");
    }

    @After
    public void tearDown() {
        logger.info("A testcase for GitResourceBundle is completed");
    }

    @Test
    public void testDefaultRBProperty() {
        ResourceBundle bundle = ResourceBundle.getBundle("GitResourceBundle",
                Locale.US);
        assertNotNull(bundle);
        assert ("Open Repository".equals(bundle.getString("openRepo")));
        assert ("Choose the Repository Path"
                .equals(bundle.getString("chooseRepo")));
        assert ("Clone Repository".equals(bundle.getString("cloneRepo")));
        assert ("Error Initializing".equals(bundle.getString("errorInit")));
        assert ("Error Cloning".equals(bundle.getString("errorClone")));
        assert ("Enter valid Project Name or Repository Path"
                .equals(bundle.getString("errorInitTitle")));
        assert ("You either entered invalid Project Name or repository path"
                .equals(bundle.getString("errorInitDesc")));
        assert ("Enter valid Remote Repository URL or Local Path"
                .equals(bundle.getString("errorCloneTitle")));
        assert ("You either entered invalid Remote Repo URL or Local path"
                .equals(bundle.getString("errorCloneDesc")));
        assert ("Error Opening".equals(bundle.getString("errorOpen")));
        assert ("Error Opening the Repository"
                .equals(bundle.getString("errorOpenTitle")));
        assert ("Not a valid Git Repository"
                .equals(bundle.getString("errorOpenDesc")));
        assert ("Initialize Repository".equals(bundle.getString("initRepo")));
        assert ("Repository".equals(bundle.getString("repo")));
        assert ("Sync".equals(bundle.getString("sync")));
        assert ("Sync Everything".equals(bundle.getString("syncAll")));
        assert ("Select Repository".equals(bundle.getString("selectRepo")));
        assert ("This will sync all repositories on all servers"
                .equals(bundle.getString("syncAllDesc")));
        assert ("Sync Repositories".equals(bundle.getString("syncRepo")));
    }
}
