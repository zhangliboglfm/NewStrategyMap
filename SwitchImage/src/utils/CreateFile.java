package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CreateFile {
		
	public static void createFile(String path,String filecontent){
        File file = new File(path);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                System.out.println("write file success");
                //创建文件成功后，写入内容到文件里
                writeFileContent(path, filecontent);
            }else{
            	System.out.println("write file success");
                //创建文件成功后，写入内容到文件里
                writeFileContent(path, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("日志文件"+path+"生成");
    }
	
	
	public static void writeFileContent(String filepath,String newstr) throws IOException{
        String filein = newstr+"\r\n";//新写入的行，换行
        
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        OutputStreamWriter ow = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fos = new FileOutputStream(file,true);
            ow = new OutputStreamWriter(fos,"GBK");
            ow.write(filein);
            ow.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (ow != null) {
            	ow.close();
            }
        }
    }
	
	public static void createFile1(String path,String filecontent){
        File file = new File(path);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                System.out.println("write file success");
                //创建文件成功后，写入内容到文件里
                writeFileContent1(path, filecontent);
            }else{
            	System.out.println("write file success");
                //创建文件成功后，写入内容到文件里
                writeFileContent1(path, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("日志文件"+path+"生成");
    }
	
	
	public static void writeFileContent1(String filepath,String newstr) throws IOException{
        String filein = newstr+"\r\n";//新写入的行，换行
        
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        OutputStreamWriter ow = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fos = new FileOutputStream(file,false);
            ow = new OutputStreamWriter(fos,"GBK");
            ow.write(filein);
            ow.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (ow != null) {
            	ow.close();
            }
        }
    }
}
