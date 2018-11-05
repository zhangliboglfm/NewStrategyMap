package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PackrolloutDayDataManager;

/**
 * <p>Title: PackrolloutDayDataController.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Controller
public class PackrolloutDayDataController {
	
	@Resource
	public PackrolloutDayDataManager packrolloutDayDataManager;
	
	@RequestMapping(value="packrolloutdaydata.do")
	public String packrolloutdaydata(HttpServletRequest request , HttpServletResponse response){
		String curdate=request.getParameter("curdate");
		request.setAttribute("curdate", curdate);
		return "jsp/packrolloutdaydata";
	}
	
	
	@ResponseBody
	@RequestMapping("daydatamiserables.do")
	public String daydatamiserables(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		JSONObject result=packrolloutDayDataManager.daydatamiserables(date);
		return result.toString();
	}



	
	public PackrolloutDayDataManager getPackrolloutDayDataManager() {
		return packrolloutDayDataManager;
	}


	public void setPackrolloutDayDataManager(
			PackrolloutDayDataManager packrolloutDayDataManager) {
		this.packrolloutDayDataManager = packrolloutDayDataManager;
	}
	
	
	
}
