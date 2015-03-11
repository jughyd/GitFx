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
package io.github.gitfx.data;

/**
 *
 * @author rvvaidya
 */
public class ProjectData{
    String projectName;
    String projectPath;
 
    public ProjectData(String projectName,String projectPath){
        this.projectName=projectName;
        this.projectPath=projectPath;
    }
   
    public String getProjectName(){
        return projectName;
    }

    public String getprojectPath(){
        return projectPath;
    }

    public void setProjectName(String projectName){
       this.projectName=projectName;
    }

    public void setProjectPath(String projectPath){
        this.projectPath=projectPath;
    }
}

