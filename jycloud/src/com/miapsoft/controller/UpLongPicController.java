package com.miapsoft.controller;	

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.miapsoft.manager.UpLongPicManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;

@Controller
public class UpLongPicController {
	@Resource
	public UpLongPicManager upLongPicManager;

	public static String articlePath=ServerFilePath.getArticledir();//文章路径
	public static String photogPath=ServerFilePath.getPhotogdir();//摄影家路径
	public static String cgArtPath=ServerFilePath.getCgarticledir();//书法家文章路径
	public static String cgPath=ServerFilePath.getCalligdir();//书法家目录
	public static String sysPath=ServerFilePath.getSystemFilePath();//作品路径

	@RequestMapping("upLongPic.do")
	public String upLongPic(HttpServletRequest request, HttpServletResponse response){
		String cgId=request.getParameter("cgId");//书法家/摄影家Id
		String bigTage=request.getParameter("bigTage");//生平、成就、风格、文章
		String flagId=request.getParameter("flagId");//传过来的ID，摄影家或者文章Id
		String pgorcg=request.getParameter("pgorcg");//从书法家还是摄影家跳转过来的标识   upLongPicCg.jsp
		request.setAttribute("cgId", cgId);
		request.setAttribute("bigTage", bigTage);
		request.setAttribute("flagId", flagId);
		request.setAttribute("pgorcg", pgorcg);
		return "pgmag/upLongPic";
	}
	@RequestMapping("upLongPicCg.do")
	public String upLongPicCg(HttpServletRequest request, HttpServletResponse response){
		String cgId=request.getParameter("cgId");//书法家/摄影家Id
		String bigTage=request.getParameter("bigTage");//生平、成就、风格、文章
		String flagId=request.getParameter("flagId");//传过来的ID，摄影家或者文章Id
		String pgorcg=request.getParameter("pgorcg");//从书法家还是摄影家跳转过来的标识   upLongPicCg.jsp
		request.setAttribute("cgId", cgId);
		request.setAttribute("bigTage", bigTage);
		request.setAttribute("flagId", flagId);
		request.setAttribute("pgorcg", pgorcg);
		return "pgmag/upLongPicCg";
	}
	//获取图片路径
	@ResponseBody
	@RequestMapping("getAllPicPath.do")
	public String getArtTitle(HttpServletRequest request,HttpServletResponse response){
		String bigTage=request.getParameter("bigTage");//生平、成就、风格、文章
		String flagId=request.getParameter("flagId");//传过来的ID，摄影家或者文章Id
		String pgorcg=request.getParameter("pgorcg");
		JSONObject result=upLongPicManager.getAllPicPath(bigTage,flagId,pgorcg);
		return result.toString();
	}
	//保存更改后的picPath
	@ResponseBody
	@RequestMapping("saveChange.do")
	public String saveChange(HttpServletRequest request,HttpServletResponse response){
		String bigTage=request.getParameter("bigTage");//生平、成就、风格、文章
		String flagId=request.getParameter("flagId");//传过来的ID，摄影家或者文章Id
		String finalPicPath=request.getParameter("finalPicPath");//最终保存的路径
		String pgorcg=request.getParameter("pgorcg");
		int result=upLongPicManager.saveChange(bigTage,flagId,finalPicPath,pgorcg);
		return result+"";
	}
	//各种图片加载
	@ResponseBody
	@RequestMapping("getLongImage.do")
	public String getLongImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String imgUrl=request.getParameter("imgUrl");
		String imgFlag=request.getParameter("imgFlag");
		String pgorcg=request.getParameter("pgorcg");
		FileInputStream fis = null;  
		OutputStream os = null;  
		try {  
			//imgFlag 1-生平，2-成就，3-风格，4-文章
			if ("cg".equals(pgorcg)) {
				try {
					if ("4".equals(imgFlag)) {
						fis = new FileInputStream(cgArtPath+"/"+imgUrl);
					} else {
						fis = new FileInputStream(cgPath+"/"+imgUrl);
					}
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/default.jpg");
				}
			} else {
				try {
					if ("4".equals(imgFlag)) {
						fis = new FileInputStream(articlePath+"/"+imgUrl);
					} else {
						fis = new FileInputStream(photogPath+"/"+imgUrl);
					}
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/default.jpg");
				}
			}
			// 图片文件解密
			String key = EncryptKey.DesKey;
			InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
			os = response.getOutputStream();  
			int count = 0;  
			byte[] buffer = new byte[1024 * 8];  
			while ((count = sbs.read(buffer)) != -1) {  
				os.write(buffer, 0, count);  
				os.flush();  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		try {  
			fis.close();  
			os.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return null;
	}
	//接收上传过来封面图片并保存
	@RequestMapping("addNewPic.do")
	public String addNewPic(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		String pgorcg=request.getParameter("pgorcg");
		String leixing = request.getParameter("leixing");
		String filename = request.getParameter("filename");
		String bigTage = request.getParameter("bigTage");
		String flagId = request.getParameter("flagId");
		String cgId = request.getParameter("cgId");
		filename = URLDecoder.decode(filename,"UTF-8");
		filename=filename.substring(0,filename.lastIndexOf("."));
		filename = EncryptionUtil.ENCRYPTSTRING(filename)+"."+leixing;
		String filePath = "";
		String rfilePath = "";
		if ("cg".equals(pgorcg)) {
			if ("article".equals(bigTage)) {
				filePath=cgArtPath+"/"+flagId+"/"+filename;
				rfilePath = flagId+"/"+filename;
			} else  {
				filePath=cgPath+"/"+cgId+"/"+bigTage+"/"+filename;
				rfilePath = cgId+"/"+bigTage+"/"+filename;
			}
		} else {
			if ("article".equals(bigTage)) {
				filePath=articlePath+"/"+flagId+"/"+filename;
				rfilePath = flagId+"/"+filename;
			} else  {
				filePath=photogPath+"/"+cgId+"/"+bigTage+"/"+filename;
				rfilePath = cgId+"/"+bigTage+"/"+filename;
			}
		}
		File outFile = new File(filePath);
		File pFile = outFile.getParentFile();
		//判断文件夹是否存在
		if (pFile != null && !pFile.exists()) {
			pFile.mkdirs();
		}
		//文件加密
		String key = EncryptKey.DesKey;
		DesUtil.encryptFile(key,new FileInputStream(f),new FileOutputStream(outFile));
		response.setContentType("text/html;charset=gbk"); 
		PrintWriter out = response.getWriter(); 
		out.print(rfilePath.toString());
		out.flush();
		out.close();
		return null;
	}
}
