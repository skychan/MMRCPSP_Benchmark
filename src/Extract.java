import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class Extract {

	public static void main(String[] args) {
		try {  
            InputStream is = new FileInputStream("liblist");  
            BufferedReader reader = new BufferedReader(  
                    new InputStreamReader(is));  
            String str = null;  
            while (true) {  
                str = reader.readLine();  
                if(str!=null){
                	File dir = new File("lib/" + str);
            		File[] filesList = dir.listFiles();
            		for (File file : filesList) {
            		    if (file.isFile()) {
            		    	 Path path = Paths.get("data/" + str);
            		         //if directory exists?
            		         if (!Files.exists(path)) {
            		             try {
            		                 Files.createDirectories(path);
            		             } catch (IOException e) {
            		                 //fail to create directory
            		                 e.printStackTrace();
            		             }
            		         }
            		    	ProcessFile.wash("lib/" + str + "/" + file.getName(), "data/" + str + "/" + file.getName());
            		    }
            		}
                }
                else  
                    break;  
            }  
            reader.close();  
            is.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		
		

	}

}
