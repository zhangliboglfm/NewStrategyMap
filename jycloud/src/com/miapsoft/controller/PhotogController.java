package com.miapsoft.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PhotogManager;
import com.miapsoft.util.FileUtil;
/**
 * 摄影家详情介绍
 * @author Administrator
 *
 */
@Controller
public class PhotogController {

	@Resource
	private PhotogManager photogManager;

	/*@RequestMapping("photog.do")
	public String photog(HttpServletRequest request,HttpServletResponse response) {

		return "jsp/photog";
	}*/
	
	///摄影家列表页面   kong
	@RequestMapping("photogmag.do")
	public String photogmang(HttpServletRequest request,HttpServletResponse response) {
		return "pgmag/photogmag";
	}

	///摄影家列表页面   kong
	@RequestMapping("photoglist.do")
	public String photoglist(HttpServletRequest request,HttpServletResponse response) {
		return "pgmag/photoglist";
	}

	///获取摄影家
	@ResponseBody
	@RequestMapping("selphotoglist.do")
	public String selphotoglist(HttpServletRequest request,HttpServletResponse response) {
		
		String importDate=request.getParameter("importDate");
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
		
		String result=photogManager.selphotogList(importDate,pgName,start,end);
		return result;
	}
	
	///获取摄影家
	@ResponseBody
	@RequestMapping("selphotoglistaudit.do")
	public String selphotoglistaudit(HttpServletRequest request,HttpServletResponse response) {
		
		String auditStatus=request.getParameter("auditStatus");
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
		
		String result=photogManager.selphotogListAudit(auditStatus,pgName,start,end);
		return result;
	}
	
	///获取摄影家头像
	@ResponseBody
	@RequestMapping(value="selphotoghead.do")
	public void selPhotogHead(HttpServletRequest request,HttpServletResponse response) {
		String pgid = request.getParameter("pgid");//摄影家ID
		String result = photogManager.selphotogPic(pgid, "头像", "1");
		JSONObject object = JSONObject.fromObject(result);
		JSONArray array = object.getJSONArray("dataList");
		
		String fileName="";
		if (array.size()>0) {
			fileName=array.getJSONObject(0).getString("FILE_NAME");
		}
		String filepath= ServerFilePath.getPhotogdir()+File.separator+fileName;
		String allowpath=ServerFilePath.getPhotogdir();
		FileUtil.decryptDownload(fileName, filepath,allowpath, response, request);
	}
}
