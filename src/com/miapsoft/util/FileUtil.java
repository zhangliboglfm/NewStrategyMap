package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.miapsoft.common.exception.BussinessException;

/**
 * <p>Title: FileUtil.java</p>
 * <p>Description: 文件上传和下载工具类</p>
 * @author: 李杰
 * @time: 2017-4-25
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public class FileUtil {
    /**
     * 文件上传
     * @Title:upload
     * @Description:TODO
     * @param request
     * @throws IllegalStateException
     * @throws IOException
     * @author:李杰
     * @date:2017-4-25
     */
    public static String upload(HttpServletRequest request,String saveDir,String allowpath) {
	File dir = new File(saveDir);
	if(!dir.exists()){
	    dir.mkdir();
	}
	String result = "";
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Iterator<String> iter = multipartRequest.getFileNames();
	while (iter.hasNext()) {
	    MultipartFile file = multipartRequest.getFile(iter.next());
	    if (file != null) {
		String fileName = null;
		try {
		    fileName = new String (file.getOriginalFilename().getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e2) {
		    // TODO Auto-generated catch block
		    e2.printStackTrace();
		}
		if(fileName == null){
		    continue;
		}
		String path = saveDir + fileName;
		File localFile = new File(path);
		if(isAllowDownload(allowpath,localFile)){
		    try {
			file.transferTo(localFile);
		    } catch (IOException e) {
			e.printStackTrace();
			result += "," + fileName ;
		    }
		}else{
		    System.out.println("文件保存路径非法！");
		}
	    }
	}
	if("".equals(result)){
	    result = "文件上传成功";
	}else{
	    result = result.replaceFirst(",", "") + "上传失败";
	}
	return result;
    }
    /**
     * 文件下载
     * @Title:download
     * @Description:TODO
     * @param fileName 下载默认保存文件名
     * @param file 下载文件
     * @param response
     * @throws IOException
     * @author:李杰
     * @date:2017-4-25
     */
    public static void download(String fileName,String filepath,String allowpath,HttpServletResponse response) {
	File file = null;
	/*try {
	    filepath = new String(filepath.getBytes("ISO8859-1"),"utf-8");
	} catch (UnsupportedEncodingException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}*/
	if(filepath != null && !"".equals(filepath)){
	    file = new File(filepath);
	}else{
	    throw new BussinessException("文件不存在！");
	}
	if(isAllowDownload(allowpath,file)){
	    InputStream input = null;
	    OutputStream os = null;
	    try {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+ new String(fileName.getBytes(),"ISO-8859-1"));
		input = new FileInputStream(file);
		os = response.getOutputStream();
		byte[] b = new byte[2048];
		int length;
		while ((length = input.read(b))>0) {
		    os.write(b, 0, length);
		}
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		throw new BussinessException("文件不存在！",e);
	    } catch (IOException e) {
		 throw new BussinessException("系统错误！",e);
	    } finally {
		try {
		    if(os!=null) os.close();
		} catch (IOException e) {
		    throw new BussinessException("系统错误！",e);
		}
		try {
		    if(input!=null) input.close();
		} catch (IOException e) {
		    throw new BussinessException("系统错误！",e);
		}
	    }
	}else{
	   System.out.println("您没有权限下载该文件！");
	}
    }
    /**
     * 判断上传或下载的文件路径是否合法
     * @Title:isAllowDownloadFile
     * @Description:TODO
     * @param allowpath 合法文件路径，多个需要用英文“,”分隔
     * @param file 上传或者下载的文件
     * @return
     * @throws IOException
     * @author:李杰
     * @date:2017-4-25
     */
    public static boolean isAllowDownload(String allowpath,File file) {
	if(file != null){
	    String realFilePath;
	    try {
		realFilePath = file.getCanonicalPath().replace("\\", "/");
	    } catch (IOException e) {
		throw new BussinessException("文件不存在！",e);
	    }
	    allowpath = allowpath.replace("\\", "/");
	    if(realFilePath.contains(":/")){//如果包含这个，应该是windows系统，文件路径不区分大小写
		realFilePath = realFilePath.toLowerCase();
		allowpath = allowpath.toLowerCase();
	    }
	    String[] allowPaths = allowpath.split(",");
	    for (String path : allowPaths) {
		if(realFilePath.startsWith(path)){
		    return true;
		}
	    }
	}
	return false;
    }
    /**
     * 判断上传或下载的文件类型是否合法
     * @Title:isAllowFileType
     * @Description:TODO
     * @param allowType 合法类型，多个需要用英文“,”分隔
     * @param file 上传或下载的文件
     * @return
     * @author:李杰
     * @date:2017-4-25
     */
    public static boolean isAllowFileType(String allowType,File file) {
	allowType = allowType.replace(".", "");
	String[] allowTypes = allowType.split(",");
	String fileName = file.getName();
	for (String type : allowTypes) {
	   if(fileName.toLowerCase().endsWith("."+type.toLowerCase())){
	       return true;
	   }
	}
	return false;
    }
    public static void main(String[] args) throws IOException{
	String savePath = "v:\\临时文件\\";
	String fileName = "volte.war";
	File f = new File(savePath+"..\\DD\\"+fileName);
	System.out.println(isAllowFileType(".jpg,.png,.doc", f));
	System.out.println(isAllowDownload("c:\\,D:\\", f));
    }
}
