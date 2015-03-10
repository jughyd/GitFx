/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

}
