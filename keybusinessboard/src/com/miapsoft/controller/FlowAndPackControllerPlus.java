package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.FlowAndPackManager;
import com.miapsoft.manager.FlowAndPackManagerPlus;
import com.miapsoft.manager.MainManager;

/**
 * <p>Title: FlowAndPackController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller 
public class FlowAndPackControllerPlus {
	
	@Resource
	private MainManager mainManager;
	@Resource
	private FlowAndPackManagerPlus flowAndPackManagerPlus;
	
	@RequestMapping(value="flowandpackplus.do")
	public String mainplus(HttpServletRequest request,HttpServletResponse response) {
		String maxDate=mainManager.getMaxDate();
		String minDate=mainManager.getMinDate();
		String curdate=request.getParameter("curdate");
		String regionId=request.getParameter("regionId");
		String state=request.getParameter("state");
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("minDate", minDate);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId", regionId);
		request.setAttribute("state", state);
		return "plusjsp/flowandpack";
	}

	@ResponseBody
	@RequestMapping("gettableToptenPlus.do")
	public String gettableTopten(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONArray result=flowAndPackManagerPlus.gettableTopten(date,regionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("initd3underLinePlus.do")
	public String initd3underLine(HttpServletRequest request, HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		String yuzhi=request.getParameter("yuzhi");
		if(yuzhi==null){
			yuzhi="0.1";
		}
		JSONObject result=flowAndPackManagerPlus.initd3underLine(date,regionId,yuzhi);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("initMainPushPackPlus.do")
	public String initMainPushPack(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONObject result=flowAndPackManagerPlus.initMainPushPack(date,regionId);
		return result.toString();
	}
	
	
	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
	public FlowAndPackManagerPlus getFlowAndPackManagerPlus() {
		return flowAndPackManagerPlus;
	}
	public void setFlowAndPackManagerPlus(
			FlowAndPackManagerPlus flowAndPackManagerPlus) {
		this.flowAndPackManagerPlus = flowAndPackManagerPlus;
	}
	
	
}
