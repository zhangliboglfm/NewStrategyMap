package com.miapsoft.controller;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MainManager;
import com.miapsoft.manager.PackRollOutManager;

/**
 * <p>Title: PackRollOutController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-30
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class PackRollOutController {
	
	
	@Resource
	public PackRollOutManager packRollOutManager;
	
	@Resource
	public MainManager mainManager;
	
	@RequestMapping(value="packrollout.do")
	public String packrollout(HttpServletRequest request,HttpServletResponse response) {
		
		String minDate=mainManager.getMinDate();
		String curdate=request.getParameter("curdate");
		request.setAttribute("curdate", curdate);
		request.setAttribute("minDate", minDate);
		
		return "jsp/packrollout";
	}
	
	
	@ResponseBody
	@RequestMapping("searchMoneyandUser.do")
	public String searchdata(HttpServletRequest request ,HttpServletResponse response){
		String date =request.getParameter("date");
		JSONObject  result=packRollOutManager.searchMoneyandUser(date);
		return result.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("searchTabelshuju.do")
	public String searchTabelshuju(HttpServletRequest request ,HttpServletResponse response){
		String date =request.getParameter("date");
		JSONArray  result=packRollOutManager.searchTabelshuju(date);
		return result.toString();
	}

	@ResponseBody
	@RequestMapping("searchPartMoneyAndUser.do")
	public String searchPartMoneyAndUser(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String rankNum=request.getParameter("rankNum");
		JSONArray Numbers = JSONArray.fromObject(rankNum);
		JSONObject result=packRollOutManager.searchPartMoneyAndUser(date,Numbers);
		return result.toString();
	}

	public PackRollOutManager getPackRollOutManager() {
		return packRollOutManager;
	}
	public void setPackRollOutManager(PackRollOutManager packRollOutManager) {
		this.packRollOutManager = packRollOutManager;
	}


	public MainManager getMainManager() {
		return mainManager;
	}


	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
	
	
	
}
