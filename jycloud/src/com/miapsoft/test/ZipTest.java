package com.miapsoft.test;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipTest {
	public static void main(String[] args) {
		/*String path = "H:/testZip/";
		String [] nameArr = {"newfileKP1.jpg","newfileKP2.jpg","newfileKP3.jpg","newfileKP4.jpg"};
		String zipPath = "H:/testZip/testZip.zip";
		compressFilesM(zipPath,nameArr);*/
		/*for (int i = 0; i < nameArr.length; i++) {
			compressFiles(zipPath,path+nameArr[i]);
		}*/
		String a= "adfadsfasd";
		System.out.println(a.substring(0,a.length()-1));
	}
	//单文件压缩
	public static void compressFiles(String zipFilePath, String srcPathName) {
        File zipFile = new File(zipFilePath);
        File srcdir = new File(srcPathName);
        if (!srcdir.exists()){
            throw new RuntimeException(srcPathName + "不存在！");
        }
        Project prj = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        if(srcdir.isDirectory()) { //是目录
            fileSet.setDir(srcdir);
            //fileSet.setIncludes("*.csv"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
            //fileSet.setExcludes(...); //排除哪些文件或文件夹
        } else {
            fileSet.setFile(srcdir);
        }
        
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        zip.setEncoding("gbk"); //以gbk编码进行压缩，注意windows是默认以gbk编码进行压缩的
        zip.addFileset(fileSet);
        zip.execute();
        System.out.println("---compress files success---");
    }
	/**
	 * 
	 * @param zipFilePath
	 * @param nameArr
	 */
	public static void compressFilesM(String zipFilePath, String [] nameArr) {
        File zipFile = new File(zipFilePath);
        String path = "H:/testZip/";
        FileSet fileSet = new FileSet();
        Project prj = new Project();
        for (int i = 0; i < nameArr.length; i++) {
        	File srcdir = new File(path+nameArr[i]);
            if (!srcdir.exists()){
                throw new RuntimeException(path+nameArr[i] + "不存在！");
            }
            fileSet.setProject(prj);
            fileSet.setFile(srcdir);
		}
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        zip.setEncoding("gbk"); //以gbk编码进行压缩，注意windows是默认以gbk编码进行压缩的
        zip.addFileset(fileSet);
        zip.execute();
        System.out.println("---多文件压缩成功---");
    }
}
