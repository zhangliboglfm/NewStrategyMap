package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import com.miapsoft.controller.ServerFilePath;

public class CompressUtil_ztf {
	
	private static String cgdir = ServerFilePath.getCalligdir();//书法家，头像，作品目录
	private static String tmpdir = ServerFilePath.getTmpFilePath();//临时路径
	private static String photogdir = ServerFilePath.getPhotogdir();//摄影家，头像，作品目录
	private static String cgArtdir = ServerFilePath.getCgarticledir();//书法家文章目录
			
	public static void main(String[] args) throws Exception {
	}
	/**
	 * 
	 * @Title:长图片部分压缩下载--书法家
	 * @Description:TODO
	 * @param picName--长图名称,以#分隔
	 * @author:张腾飞
	 * @throws Exception 
	 * @date:2018-9-17
	 */
	public static String CompressLongPic(String picName) throws Exception {
		picName = picName.replaceAll("]", "#");
		String [] nameArray = {}; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String tmpPath = tmpdir+"/longPicZip/"+sdf.format(new Date());//临时文件夹
		String zipPath = tmpdir+"/longPicZip/"+sdf.format(new Date())+"/longPic.zip";//临时文件夹
		if (cleanUpDir(tmpdir+"/longPicZip")) {
			File zipFile = new File(tmpPath);
			if  (!zipFile .exists()  && !zipFile .isDirectory()){       
				zipFile .mkdirs();    
			}
		}
		if (picName!=null&&!"".equals(picName)&&!"null".equals(picName)) {
			nameArray = picName.split("#");
		}
		//把图片写入到临时文件夹
		for (int i = 0; i < nameArray.length; i++) {
			String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
			InputStream input = new FileInputStream(new File(cgdir+"/"+realPicName));
			OutputStream os = new FileOutputStream(tmpPath+"/长图"+(i+1)+lastSuffix);
			String key = EncryptKey.DesKey;
			os.write(DesUtil.decryptFile(key,input));
		    os.close();
		}
		//压缩图片
		File saveFile = new File(zipPath);
		FileSet fileSet = new FileSet();
        Project prj = new Project();
        for (int i = 0; i < nameArray.length; i++) {
        	String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
        	File srcdir = new File(tmpPath+"/长图"+(i+1)+lastSuffix);
            if (!srcdir.exists()){
                throw new RuntimeException(tmpPath+"/长图"+(i+1)+lastSuffix+"不存在！");
            }
            fileSet.setProject(prj);
            fileSet.setFile(srcdir);
		}
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(saveFile);
        zip.setEncoding("gbk"); //以gbk编码进行压缩，注意windows是默认以gbk编码进行压缩的
        zip.addFileset(fileSet);
        zip.execute();
		System.out.println("压缩文件成功");
		return zipPath;
	}
	/**
	 * 
	 * @Title:摄影家部分长图压缩下载
	 * @Description:TODO
	 * @param picName
	 * @return
	 * @throws Exception
	 * @author:张腾飞
	 * @date:2018-9-21
	 */
	public static String CompressPgPic(String picName) throws Exception {
		picName = picName.replaceAll("]", "#");
		String [] nameArray = {}; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String tmpPath = tmpdir+"/longPicZip/"+sdf.format(new Date());//临时文件夹
		String zipPath = tmpdir+"/longPicZip/"+sdf.format(new Date())+"/longPic.zip";//临时文件夹
		if (cleanUpDir(tmpdir+"/longPicZip")) {
			File zipFile = new File(tmpPath);
			if  (!zipFile .exists()  && !zipFile .isDirectory()){       
				zipFile .mkdirs();    
			}
		}
		if (picName!=null&&!"".equals(picName)&&!"null".equals(picName)) {
			nameArray = picName.split("#");
		}
		//把图片写入到临时文件夹
		for (int i = 0; i < nameArray.length; i++) {
			String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
			InputStream input = new FileInputStream(new File(photogdir+"/"+realPicName));
			OutputStream os = new FileOutputStream(tmpPath+"/长图"+(i+1)+lastSuffix);
			String key = EncryptKey.DesKey;
			os.write(DesUtil.decryptFile(key,input));
		    os.close();
		}
		//压缩图片
		File saveFile = new File(zipPath);
		FileSet fileSet = new FileSet();
        Project prj = new Project();
        for (int i = 0; i < nameArray.length; i++) {
        	String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
        	File srcdir = new File(tmpPath+"/长图"+(i+1)+lastSuffix);
            if (!srcdir.exists()){
                throw new RuntimeException(tmpPath+"/长图"+(i+1)+lastSuffix+"不存在！");
            }
            fileSet.setProject(prj);
            fileSet.setFile(srcdir);
		}
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(saveFile);
        zip.setEncoding("gbk"); //以gbk编码进行压缩，注意windows是默认以gbk编码进行压缩的
        zip.addFileset(fileSet);
        zip.execute();
		System.out.println("压缩文件成功");
		return zipPath;
	}
	
	/**
	 * 
	 * @Title:书法家文章长图片部分压缩下载
	 * @Description:TODO
	 * @param picName--长图名称,以#分隔
	 * @author:张腾飞
	 * @throws Exception 
	 * @date:2018-9-17
	 */
	public static String CompressLongCgArt(String picName) throws Exception {
		String [] nameArray = {}; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String tmpPath = tmpdir+"/longPicZip/"+sdf.format(new Date());//临时文件夹
		String zipPath = tmpdir+"/longPicZip/"+sdf.format(new Date())+"/longPic.zip";//临时文件夹
		if (cleanUpDir(tmpdir+"/longPicZip")) {
			File zipFile = new File(tmpPath);
			if  (!zipFile .exists()  && !zipFile .isDirectory()){       
				zipFile .mkdirs();    
			}
		}
		if (picName!=null&&!"".equals(picName)&&!"null".equals(picName)) {
			nameArray = picName.split("#");
		}
		//把图片写入到临时文件夹
		for (int i = 0; i < nameArray.length; i++) {
			String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
			InputStream input = new FileInputStream(new File(cgArtdir+"/"+realPicName));
			OutputStream os = new FileOutputStream(tmpPath+"/长图"+(i+1)+lastSuffix);
			String key = EncryptKey.DesKey;
			os.write(DesUtil.decryptFile(key,input));
		    os.close();
		}
		//压缩图片
		File saveFile = new File(zipPath);
		FileSet fileSet = new FileSet();
        Project prj = new Project();
        for (int i = 0; i < nameArray.length; i++) {
        	String realPicName = nameArray[i];
			String lastSuffix = realPicName.substring(realPicName.lastIndexOf("."));
        	File srcdir = new File(tmpPath+"/长图"+(i+1)+lastSuffix);
            if (!srcdir.exists()){
                throw new RuntimeException(tmpPath+"/长图"+(i+1)+lastSuffix+"不存在！");
            }
            fileSet.setProject(prj);
            fileSet.setFile(srcdir);
		}
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(saveFile);
        zip.setEncoding("gbk"); //以gbk编码进行压缩，注意windows是默认以gbk编码进行压缩的
        zip.addFileset(fileSet);
        zip.execute();
		System.out.println("压缩文件成功");
		return zipPath;
	}
	/**
	 * 
	 * @Title:清空临时压缩文件目录
	 * @Description:TODO
	 * @param path
	 * @return
	 * @author:张腾飞
	 * @date:2018-9-21
	 */
	public static boolean cleanUpDir(String path) {
		File file = new File(path);
		if(!file.exists()){
			System.err.println("要清空的文件夹不存在");
			file.mkdirs();
			return true;
		}
		String[] content = file.list();//取得当前目录下所有文件和文件夹
		for(String name : content){
			File temp = new File(path, name);
			if(temp.isDirectory()){//判断是否是目录
				cleanUpDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
				temp.delete();//删除空目录
			}else{
				if(!temp.delete()){//直接删除文件
					System.err.println("删除文件：" + name+"时出错");
					return false;
				}
			}
		}
		return true;
	}
}
