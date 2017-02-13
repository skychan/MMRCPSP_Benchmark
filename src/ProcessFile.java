import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class ProcessFile {
	public static void wash(String inputfile, String outputfile){
//		System.out.println("Working Directory = " + System.getProperty("user.dir"));
//		Path currentRelativePath = Paths.get("");
//		String s = currentRelativePath.toAbsolutePath().toString();
//		System.out.println("Current relative path is: " + s);
//		System.out.println(inputfile);
//		System.out.println(outputfile);
		List<String> list = new ArrayList<String>();
		Path nfile = Paths.get(outputfile);
        try
        {
            String encoding = "UTF-8";
            File file = new File(inputfile);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                int nbLine = 1;
                int nbTask = 0;                
                
                while ((lineTxt = bufferedReader.readLine()) != null)
                {
//                    list.add(lineTxt);
                    if (nbLine == 6) {
						// get number of jobs
                    	String[] temp = lineTxt.split(":  ");
//                    	String[] temp = lineTxt.split("\\s+");
//                    	System.out.println(temp[1]);
                    	list.add(temp[1]);
                    	nbTask = Integer.parseInt(temp[1]);
//                    	System.out.println(nbTask);
					}
                    
                    if (nbLine == 9) {
						// get renewable resources
						String[] temp = lineTxt.split(":  ");
						String[] temp2 = temp[1].split(" ");
//						System.out.println(temp2[0]);
						list.add(temp2[0]);
//						for (String string : temp) {
//							System.out.println(string);
//						}
					}
                    
                    if (nbLine == 10) {
						// get nonrenewable resources
						String[] temp = lineTxt.split(":  ");
						String[] temp2 = temp[1].split(" ");
//						System.out.println(temp2[0]);
						list.add(temp2[0]);
						list.add(" ");
					}
                    
                    if (nbLine == 15) {
						String[] temp = lineTxt.split("\\s+");
						
						list.add(temp[4]);
					}
                    
                    if (nbLine >= 19 && nbLine < 19 + nbTask) {
						// get main data
//                    	System.out.println(lineTxt);
                    	list.add(lineTxt);
					}
                    
                    if (nbLine >= 19 + nbTask + 4)  {
						if (lineTxt.contains("*") || lineTxt.contains("R")) {
//							System.out.println("continue");
						}else {
//							list.add(" ");
							list.add(lineTxt);
//							System.out.println(lineTxt);
						}
					}
                    
                    nbLine += 1;
                }
                bufferedReader.close();
                read.close();
//                list.add(19 + nbTask + 4, " ");
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        
//        System.out.print(list);
        
        
        
        try {
        	list.add(3, " ");
            list.add(4, list.get(list.size()-1));
            list.remove(list.size()-1);        	
			Files.write(nfile,list,Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
