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

/**
 * Resource Bundle for GitFx Application
 *
 * @author rvvaidya
 */
public class GitResourceBundle{
    private Locale locale;
    public GitResourceBundle() {
        locale = Locale.US;
    }

    public GitResourceBundle(Locale locale) {
        this.locale = locale;
    }
 
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle("GitResourceBundle", locale);
    }
}
