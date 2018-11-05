package com.miapsoft.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.CgInformationImportManager;
import com.miapsoft.util.CheckFileUtil;
import com.miapsoft.util.FileAnalysisUtil;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.PrintUtil;
import com.mysql.fabric.xmlrpc.base.Data;

@Controller
public class CgInformationImportController {
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	@Resource
	public CgInformationImportManager cgInformationImportManager;
	@RequestMapping("cginformationimport.do")
	public String zhuanti(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/cginformationimport";
	}
	@RequestMapping("opencenter.do")
	public String zhuanti2(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/opencenter";
	}
	//书法家人物信息录入
	@ResponseBody
	@RequestMapping("uploadcginfo.do")
	public void uploadphotographer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session =request.getSession();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		File file = new File(tmpFilePath+File.separator+"tmp"+sdf.format(new Date())+"."+name.substring(name.lastIndexOf(".")+1, name.length()));
		File parent = file.getParentFile();
		//判断文件夹是否存在
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		//判断文件夹是否已存在
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
		}
		InputStreamToFile.ServletInputStreamToFile(request.getInputStream(), file);
		if(file.length()!=Long.parseLong(size)){
			throw new Exception("文件上传失败");
		}
		
		//******************************开始校验******************************
		if(CheckFileUtil.checkCgFile(file.getAbsolutePath(), printWriter)){
			//******************************开始解析******************************
			PrintUtil.printLog2(printWriter,"数据如下:");
			JSONArray data = FileAnalysisUtil.CgInfoEntry(printWriter, file.getAbsolutePath());
			if(data.size()!=0){
				for (int i = 0; i < data.size(); i++) {
					JSONObject obj = data.getJSONObject(i);
					cgInformationImportManager.insertcginfo(obj);
				}
			}
			printWriter.print(data.toString());
		}else{
			String str= "文件有误，请<a href='downloadErrorfile.do?filepath="+file.getAbsolutePath()+"'>下载</a>并修改";
			/*JSONArray AreaSaturation = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("data",str);
			AreaSaturation.add(obj);
			printWriter.print(AreaSaturation.toString());*/
			PrintUtil.printLog(printWriter,"文件有误，请<a href='downloadErrorfile.do?filepath="+file.getAbsolutePath()+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
	}
}
