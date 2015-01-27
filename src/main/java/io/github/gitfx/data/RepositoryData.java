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
package io.github.gitfx.data;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class that maintains the repository metadata 
 * @author rvvaidya
 */
public class RepositoryData {
   
    String serverName;
    List<ProjectData> repositories;
   
    public RepositoryData(){
        repositories=new ArrayList<ProjectData>();
    }
    public void setServerName(String serverName){
        this.serverName=serverName;
    }
  
    public String getServerName(){
        return serverName;
    }
    
    public void setRepositories(ProjectData projectData){
        this.repositories.add(projectData);
    }
    
    public List<ProjectData> getRepositories(){
        return this.repositories;
    } 
   
    public void setProjectData(String projectName,String projectPath){
        ProjectData obj=new ProjectData();
        obj.setProjectName(projectName);
        obj.setProjectPath(projectPath);
        this.repositories.add(obj);
    }
    //Inner class that maintains ProjectData  
    class ProjectData{
        String projectName;
        String projectPath;
        
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
}

