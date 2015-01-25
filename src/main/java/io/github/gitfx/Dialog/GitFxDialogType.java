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

package io.github.gitfx.Dialog;


/**
 *
 * @author rvvaidya
 */
public enum GitFxDialogType {

    /**
     * Confirmation dialog
     */
    ALERT,
    /**
     * Information dialog
     */
    TEXT_INPUT_DIALOG,
    
    INFORMATION,
    /**
     * Warning dialog
     */
    WARNING,
    /**
     * Error dialog
     */
    ERROR,
    /**
     * Exception dialog
     */
    EXCEPTION,
    /**
     * Input text dialog
     */
    INPUT_TEXT,
    /**
     * Generic OK dialog
     */
    GENERIC_OK,
    /**
     * Generic OK and CANCEL dialog
     */
    GENERIC_OK_CANCEL,
    /**
     * Generic YES and NO dialog
     */
    GIT_INIT,
    /**
     * Generic Choose
     */
    GIT_OPEN
    
}

