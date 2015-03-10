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
package io.github.gitfx.util;

import java.io.File;
import java.io.FileOutputStream;
import com.google.gson.Gson;
import io.github.gitfx.data.RepositoryData;


/**
 *
 * @author rvvaidya
 */
public final class GitFXGsonUtil {
    public static final String GitFxRepo="GitFxRepo.json";
   /*
    *  Passivate the String JSON to a file on disk to store repository 
    *  information. 
    */
    public static void passivateJSON(String json){
        FileOutputStream outputStream=null;
        File file=new File(GitFxRepo);
        try{
            if(!file.exists())
                file.createNewFile();
            outputStream=new FileOutputStream(file,Boolean.TRUE);
            byte[] contentInBytes = json.getBytes();
            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   /*
    * Utility method which saves Repo meta data to file on disk. 
    */
    public static void saveRepositoryInformation(String serverName,
        String projectName,String projectPath){
        Gson gson= new Gson();
        RepositoryData repoMetaData=new RepositoryData();
        repoMetaData.setServerName("github");
        repoMetaData.setProjectData(projectName,projectPath); 
        passivateJSON(gson.toJson(repoMetaData));
    }
   /*
    * Utility method which returns the server details 
    * TODO: Parse actual JSON and build the metadata. 
    */
    public static RepositoryData getRepositoryMetaData(){
        RepositoryData repoMetaData=new RepositoryData();
        repoMetaData.setServerName("GitHub");
        repoMetaData.setProjectData("SampleProject", "/dummy/location");
        repoMetaData.setProjectData("Calculator", "/dummy/location");
        repoMetaData.setProjectData("Foo", "/dummy/location");
        repoMetaData.setProjectData("Bar", "/dummy/location");
        return repoMetaData;
    }
   
}
