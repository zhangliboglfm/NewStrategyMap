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
            //����ļ������ڣ��򴴽��µ��ļ�
            if(!file.exists()){
                file.createNewFile();
                System.out.println("write file success");
                //�����ļ��ɹ���д�����ݵ��ļ���
                writeFileContent(path, filecontent);
            }else{
            	System.out.println("write file success");
                //�����ļ��ɹ���д�����ݵ��ļ���
                writeFileContent(path, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("��־�ļ�"+path+"����");
    }
	
	
	public static void writeFileContent(String filepath,String newstr) throws IOException{
        String filein = newstr+"\r\n";//��д����У�����
        
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        OutputStreamWriter ow = null;
        try {
            File file = new File(filepath);//�ļ�·��(�����ļ�����)
            //���ļ�����������
            fos = new FileOutputStream(file,true);
            ow = new OutputStreamWriter(fos,"GBK");
            ow.write(filein);
            ow.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //��Ҫ���ǹر�
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
            //����ļ������ڣ��򴴽��µ��ļ�
            if(!file.exists()){
                file.createNewFile();
                System.out.println("write file success");
                //�����ļ��ɹ���д�����ݵ��ļ���
                writeFileContent1(path, filecontent);
            }else{
            	System.out.println("write file success");
                //�����ļ��ɹ���д�����ݵ��ļ���
                writeFileContent1(path, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("��־�ļ�"+path+"����");
    }
	
	
	public static void writeFileContent1(String filepath,String newstr) throws IOException{
        String filein = newstr+"\r\n";//��д����У�����
        
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        OutputStreamWriter ow = null;
        try {
            File file = new File(filepath);//�ļ�·��(�����ļ�����)
            //���ļ�����������
            fos = new FileOutputStream(file,false);
            ow = new OutputStreamWriter(fos,"GBK");
            ow.write(filein);
            ow.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //��Ҫ���ǹر�
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
