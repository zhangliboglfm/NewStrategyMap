package com.miapsoft.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GetFileName {
	public static String [] getFileName(String path)
	{
		File file = new File(path);
		String [] fileName = file.list();
		return fileName;
	}
	public static void renameFile(String path,String oldname,String newname){ 
		if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
			File oldfile=new File(path+"\\"+oldname); 
			File newfile=new File("D:\\NewName\\kapaNew\\worksimg\\"+newname); 
			if(!oldfile.exists()){
				return;//重命名文件不存在
			}
			if (oldfile.isHidden()) {
				return;//隐藏文件
			}
			if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
				System.out.println(newname+"已经存在！"); 
			else{ 
				oldfile.renameTo(newfile); 
			} 
		}else{
			System.out.println("新文件名和旧文件名相同...");
		}
	}
	public static void writelogs(String filePath, String content) {
		FileWriter fw = null;
		try {
			//如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f=new File(filePath);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println("追加内容");
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		//String [] fileName = getFileName("D:\\photogrename\\何藩\\worksimg_out");//<span style="font-family: Arial, Helvetica, sans-serif;">此处修改为你的本地路径</span>
		String filesPath="D:\\NewName\\kapa\\worksimg";
		String [] fileName = getFileName(filesPath);
		for (int i = 0; i < fileName.length; i++) {
			//renameFile("D:\\photogrename\\何藩\\worksimg_out", fileName[i], "cx"+i+".jpg");//cx修改为你要修改的文件名格式
			//writelogs("D:\\ddddddddd.txt","cx"+i+".jpg"+"\t"+fileName[i].substring(fileName[i].lastIndexOf(" "),fileName[i].lastIndexOf("."))+"\t"+fileName[i]);
			//System.out.println(fileName[i].lastIndexOf("."));
			//System.out.println(fileName[i].lastIndexOf(" ")==-1?0:fileName[i].lastIndexOf(" "));
			//writelogs("D:\\ddddddddd.txt","cx"+i+".jpg"+"\t"+fileName[i].substring(fileName[i].lastIndexOf(" "),fileName[i].lastIndexOf("."))+"\t"+fileName[i]);

			/////何藩
			/*renameFile("D:\\photogrename\\何藩\\worksimg_out", fileName[i], "cx"+i+".jpg");//cx修改为你要修改的文件名格式
			int start=fileName[i].lastIndexOf(" ")==-1?0:fileName[i].lastIndexOf(" ");
			int end=fileName[i].lastIndexOf(".");
			int dotindex=fileName[i].lastIndexOf(",");
			String ext=fileName[i].substring(end,fileName[i].length());
			System.out.println("cx"+i+ext+"\t"+fileName[i].substring(start,end)+"\t"+fileName[i]);*/

			String filename=fileName[i];
			/////布列松1
			String oldfilename=filename;
			
			System.out.println("newfile"+i+".jpg"+"\t"+filename);
			
			renameFile(filesPath, oldfilename, "newfileKP"+i+".jpg");//cx修改为你要修改的文件名格式
			
			/*int end=filename.lastIndexOf(".");
			int dotindex=filename.lastIndexOf(",");
			
			filename=filename.replaceAll(" （", "（");
			filename=filename.replaceAll("（ ", "（");
			filename=filename.replaceAll(" \\(", "\\(");
			filename=filename.replaceAll("\\( ", "\\(");
			
			filename=filename.replaceAll(" ）", "）");
			filename=filename.replaceAll("） ", "）");
			filename=filename.replaceAll(" ）", "）");
			filename=filename.replaceAll(" \\)", "\\)");
			filename=filename.replaceAll("\\) ", "\\)");
			filename=filename.replaceAll(" \\)", "\\)");
			
			filename=filename.replaceAll(" \\- ", "\\-");
			filename=filename.replaceAll(" \\-", "\\-");
			filename=filename.replaceAll("\\- ", "\\-");
			filename=filename.replaceAll(" \\— ", "\\—");
			filename=filename.replaceAll(" \\—", "\\—");
			filename=filename.replaceAll("\\— ", "\\—");
			filename=filename.replaceAll(" – ", "–");
			filename=filename.replaceAll("-  ", "-");
			//System.out.println(filename);
			
			int start=filename.lastIndexOf(" ")==-1?0:filename.lastIndexOf(" ");
			int leftindex=filename.lastIndexOf("(");
			if (leftindex==-1) {
				leftindex=filename.lastIndexOf("（");
			}
			if (leftindex==-1) {
				leftindex=end;
			}
			
			String ext=filename.substring(end,filename.length());
			System.out.println("bls1"+i+".jpg"+"\t"+filename.substring(start,leftindex)+"\t"+filename);
			
			renameFile(filesPath, oldfilename, "bls1"+i+".jpg");//cx修改为你要修改的文件名格式
*/
			/////布列松2
			
//			int start=filename.lastIndexOf(" ")==-1?0:filename.lastIndexOf(" ");
//			int end=filename.lastIndexOf(".");
//			int dotindex=filename.lastIndexOf(",");
//			String ext=filename.substring(end,filename.length());
			//System.out.println(filename.replaceAll(",", "")+"\t"+filename.substring(start,end)+"\t"+filename);
			//renameFile("D:\\photogrename\\布列松\\worksimg2", oldfilename, "bls2"+i+".jpg");//cx修改为你要修改的文件名格式
			

			/*/////布列松3
			renameFile("D:\\photogrename\\布列松\\worksimg3", filename, "cx"+i+".jpg");//cx修改为你要修改的文件名格式
			int start=filename.lastIndexOf(" ")==-1?0:filename.lastIndexOf(" ");
			int end=filename.lastIndexOf(".");
			int dotindex=filename.lastIndexOf(",");
			String ext=filename.substring(end,filename.length());
			System.out.println("cx"+i+ext+"\t"+filename.substring(start,end)+"\t"+filename);*/
			

		}
	}
}
