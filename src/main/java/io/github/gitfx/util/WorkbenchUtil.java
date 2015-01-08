/*
 * Copyright 2015 GitFx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.gitfx.util;

import java.io.File;

/**
 *
 * @author rhegde
 */
public final class WorkbenchUtil {

    public static final String GITFX_WORKBENCH_DIR = System.getProperty("user.home") + File.separator + ".gitfx";
    public static final String GITFX_WORKBENCH_CONFIG_DIR = GITFX_WORKBENCH_DIR + File.separator + "config";
    public static final String GITFX_WORKBENCH_LOG_DIR = GITFX_WORKBENCH_DIR + File.separator + "log";
    public static final String GITFX_WORKBENCH_RECENT_REPO_FILE = GITFX_WORKBENCH_DIR + File.separator + "recent_repos.json";

    public static String getGitFxWorkbenchPath() {
        return GITFX_WORKBENCH_DIR;
    }

    public static boolean isWorkbenchAvailable() {
        return (new File(GITFX_WORKBENCH_DIR)).exists();
    }

    public static boolean isWorkbenchConfigDirAvailable() {
        return (new File(GITFX_WORKBENCH_CONFIG_DIR)).exists();
    }

    public static boolean isWorkbenchLogDirAvailable() {
        return (new File(GITFX_WORKBENCH_LOG_DIR)).exists();
    }

    public static void initializeWorkbench() {
        System.out.println(GITFX_WORKBENCH_DIR);
        if (!isWorkbenchAvailable()) {
            (new File(GITFX_WORKBENCH_DIR)).mkdir();
        }
        if (!isWorkbenchConfigDirAvailable()) {
            (new File(GITFX_WORKBENCH_CONFIG_DIR)).mkdir();
        }
        if (!isWorkbenchLogDirAvailable()) {
            (new File(GITFX_WORKBENCH_LOG_DIR)).mkdir();
        }
    }

}
