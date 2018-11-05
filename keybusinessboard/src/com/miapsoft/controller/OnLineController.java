package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MainManager;
import com.miapsoft.manager.OnLineManager;

/**
 * <p>Title: OnLineController.java</p>
 * <p>Description: TODO</p>
 * @author: 周雷
 * @time: 2017-6-30
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class OnLineController {
	
	@Resource
	OnLineManager onLineManager;
	@Resource
	private MainManager mainManager;
	
	@RequestMapping(value="online.do")
	public String online(HttpServletRequest request,HttpServletResponse response) {
		String maxdate = mainManager.getMaxDate();
		String mindate = mainManager.getMinDate();
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("curdate", curdate);
		
		return "jsp/online";
	}
	
	//宽带业务的数据
	@ResponseBody
	@RequestMapping("broadbanddata")
	public String broadbanddata(HttpServletRequest request,HttpServletResponse response){
		JSONArray result=new JSONArray();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		String brandtype=request.getParameter("brandtype");
		result=onLineManager.broadbanddata(onlineoroutline,curdate,brandtype);
		return result.toString();
	}
	//重点业务的数据
	@ResponseBody
	@RequestMapping("keymanagement")
	public String keymanagement(HttpServletRequest request,HttpServletResponse response){
		JSONArray result=new JSONArray();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		result=onLineManager.keymanagement(onlineoroutline,curdate);
		return result.toString();
		
	}
	//业务办理量
	@ResponseBody
	@RequestMapping("businessvloume")
	public String businessvloume(HttpServletRequest request,HttpServletResponse response){
		JSONArray result=new JSONArray();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		result=onLineManager.businessvloume(onlineoroutline,curdate);
		return result.toString();
		
	}
	//4g飞享类套餐办理情况
	@ResponseBody
	@RequestMapping("flyclass")
	public String flyclass(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		result=onLineManager.flyclass(onlineoroutline,curdate);
		return result.toString();
		
	}
	//其他业务
	@ResponseBody
	@RequestMapping("otherbusiness")
	public String otherbusiness(HttpServletRequest request,HttpServletResponse response){
		JSONArray result=new JSONArray();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		String otherbusiness=request.getParameter("otherbusiness");
		result=onLineManager.otherbusiness(onlineoroutline,curdate,otherbusiness);
		return result.toString();
		
	}
	//流量包
	@ResponseBody
	@RequestMapping("flowpacket")
	public String flowpacket(HttpServletRequest request,HttpServletResponse response){
		JSONArray result=new JSONArray();
		String onlineoroutline=request.getParameter("onlineoroutline");
		String curdate=request.getParameter("curdate");
		String flowtype=request.getParameter("flowtype");
		result=onLineManager.broadbaflowpacketnddata(onlineoroutline,curdate,flowtype);
		return result.toString();
		
	}
	
	
	
	
	
}
