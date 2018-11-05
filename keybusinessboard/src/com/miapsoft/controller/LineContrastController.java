package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.LineContrastDataManager;
import com.miapsoft.manager.MainManager;

/**
 * <p>Title: LineContrastController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-30
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class LineContrastController {
	
	@Resource
	private LineContrastDataManager contrastDataManager;
	@Resource
	private MainManager mainManager;
	
	@RequestMapping(value="linecontrast.do")
	public String linecontrast(HttpServletRequest request,HttpServletResponse response) {
		String maxdate = mainManager.getMaxDate();
		String mindate = mainManager.getMinDate();
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("curdate", curdate);
		return "jsp/linecontrast";
	}

	@ResponseBody
	@RequestMapping(value="getpackdata.do")
	public String getpackdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		result = contrastDataManager.getPackBusinessHandelData(date);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getkeydata.do")
	public String getkeydata(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		result = contrastDataManager.getLineKeyBusinessData(date);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getkuandaidata.do")
	public String getkuandaidata(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		result = contrastDataManager.getKuanDaiData(date, type);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getfluxdata.do")
	public String getfluxdata(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		result = contrastDataManager.getFluxData(date, type);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getbusinessdata.do")
	public String getbusinessdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		result = contrastDataManager.getBusinessHandelData(date);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getotherdata.do")
	public String getotherdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		result = contrastDataManager.getOtherBusinessHandelData(date, type);
		return result.toString();
	}
	
	public LineContrastDataManager getContrastDataManager() {
		return contrastDataManager;
	}

	public void setContrastDataManager(LineContrastDataManager contrastDataManager) {
		this.contrastDataManager = contrastDataManager;
	}

	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
}
