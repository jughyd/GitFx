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

import java.util.ListResourceBundle;

/**
 * Resource Bundle for GitFx Application
 *
 * @author rvvaidya
 */
public class GitResourceBundle extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][]{
            {"openRepo", "Open Repostiory"},
            {"chooseRepo", "Choose the Repository Path"},
            {"cloneRepo", "Clone Repository"},
            {"errorInit", "Error Initializing"},
            {"errorClone", "Error Cloning"},
            {"errorInitTitle", "Enter valid Project Name or Repository Path"},
            {"errorInitDesc", "You either entered invalid Project Name or "
                + "repository path"},
            {"errorCloneTitle", "Enter valid Remote Repository URL or Local Path "},
            {"errorCloneDesc", "You either entered invalid Remote Repo URL "
                + "or Local path"},
            {"errorOpen", "Error Opening"},
            {"errorOpenTitle", "Error Opening the Repsitory"},
            {"errorOpenDesc", "Not a valid Git Repsitory"},
            {"initRepo", "Initialize Repository"},
            {"repo", "Repsitory"},
            {"sync", "Sync"},
            {"syncAll", "Sync Everything"},
            {"selectRepo", "Select Repository"},
            {"syncAllDesc", "This will sync all repositories on all servers"}
        };
    }
}
