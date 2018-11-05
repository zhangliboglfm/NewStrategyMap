package com.miapsoft.controller;	

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.AppSettingManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;

@Controller
public class AppSettingController {
	
	public static String appPath=ServerFilePath.getAppdir();//APP图片路径
	public static String sysPath=ServerFilePath.getSystemFilePath();//默认图片路径
	
	@Resource
	public AppSettingManager appSettingManager;

	@RequestMapping("appSetting.do")
	public String makeExcel(HttpServletRequest request, HttpServletResponse response){
		return "pgmag/appSetting";
	}
	
	@RequestMapping("applist.do")
	public String applist(HttpServletRequest request, HttpServletResponse response){
		return "pgmag/applist";
	}
	
	
	@RequestMapping("appDetail.do")
	public String appDetail(HttpServletRequest request, HttpServletResponse response){
		String appId = request.getParameter("appId");
		request.setAttribute("appId", appId);
		return "pgmag/appDetail";
	}
	
	//查询点击日期的安排
	@ResponseBody
	@RequestMapping("getlistbydate.do")
	public String getlistbydate(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String appId=request.getParameter("appId");
		JSONObject result = appSettingManager.getlistbydate(date,appId);
		return result.toString();
	}
	//查备选的摄影家列表
	@ResponseBody
	@RequestMapping("getAppPgList.do")
	public String getAppPgList(HttpServletRequest request,HttpServletResponse response){
		String pgName=request.getParameter("pgName");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		String result=appSettingManager.getAppPgList(pgName,start,end);
		return result;
	}
	//查备选的文章列表
	@ResponseBody
	@RequestMapping("getAppArtList.do")
	public String getAppArtList(HttpServletRequest request,HttpServletResponse response){
		String pgName=request.getParameter("artName");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		String result=appSettingManager.getAppArtList(pgName,start,end);
		return result;
	}
	//重新提交APP设置
	@ResponseBody
	@RequestMapping("sureOper.do")
	public String sureOper(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date1");
		String appId=request.getParameter("appId");
		String phgList=request.getParameter("phgList");
		String artList=request.getParameter("artList");
		String result = appSettingManager.sureOper(date,appId,phgList,artList);
		return result;
	}
	
	//生成日历上的东西
	@ResponseBody
	@RequestMapping("makeCalendar.do")
	public String makeCalendar(HttpServletRequest request,HttpServletResponse response){
		String appId=request.getParameter("appId");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		JSONObject result = appSettingManager.makeCalendar(appId,start,end);
		return result.toString();
	}
	
	//查APP列表
	@ResponseBody
	@RequestMapping("getAppList.do")
	public String getAppList(HttpServletRequest request,HttpServletResponse response){
		String pgName=request.getParameter("pgName");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		String result=appSettingManager.getAppList(pgName,start,end);
		return result;
	}
	
	//APP图片加载
	@ResponseBody
	@RequestMapping("getAppImg.do")
	public String getAppImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String appIcon=request.getParameter("appIcon");
		FileInputStream fis = null;  
	    OutputStream os = null;  
		try {  
			try {
				fis = new FileInputStream(appPath+"/"+appIcon);
			} catch (Exception e) {
				fis = new FileInputStream(sysPath+"/default.jpg");
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
}
