package io.github.gitfx.util;

import java.io.*;

public class GitCreateHtmlPage {

    public static void parseDiffData(String diffData)
    {
        try {
            //define a HTML String Builder
        	diffData = diffData.replaceAll("<", "&lt;");
        	diffData = diffData.replaceAll(">", "&gt;");
        	String textStr[] = diffData.split("\\r?\\n");
        	System.out.println("Number of lines: " + textStr.length);
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append("<html><head><title>Selenium Test </title></head>");
            //append body
            htmlStringBuilder.append("<body style=\"font-size:70%;font-family:courier;\">");
            //append table
            for(String line : textStr) {
            	if(line.length()>0) {
            	char start = line.charAt(0) ;
            	if(start=='-') {
            		 htmlStringBuilder.append("<div style=\"background-color:rgb(255, 163, 163);\"> " + line + "</div>");
            		}
            	else if(start=='+') {
            		htmlStringBuilder.append("<div style=\"background-color:rgb(66, 244, 158);\"> " + line + "</div>");
            	}
            	else {
            		htmlStringBuilder.append("<div> " + line + "</div>");
            	}
            	}
            	
            }
            htmlStringBuilder.append("</html>");
            //write html string content to a file
            WriteToFile(htmlStringBuilder.toString(),"diffWebViewHtmlPage.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator+ "src\\main\\resources\\" + fileName;
        System.out.println(tempFile);
        File file = new File(tempFile);
        // if file does exists, then delete and create a new file
        if (file.exists()) {
            try {
                File newFileName = new File(projectPath + File.separator+ "src\\main\\resources\\" + "backup_"+fileName);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //write to file with OutputStreamWriter
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();

    }
}
