package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.BusinessProcessDurationManager;

/**
 * <p>Title: BusinessProcessDurationController.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-19
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Controller
public class BusinessProcessDurationController {
	
	@Resource
	public BusinessProcessDurationManager businessProcessDurationManager;
	
	
	@RequestMapping("businessprocessduration.do")
	public String businessprocessduration(HttpServletRequest request,HttpServletResponse response){
		String curdate=request.getParameter("curdate");
		request.setAttribute("curdate", curdate);
		return "jsp/businessprocessduration";
	}
	
	
	/**
	 * 查询平均办理时长
	 * @Title:processstimeconsume
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-7-20
	 */
	@ResponseBody
	@RequestMapping("processstimeconsume.do")
	public String processstimeconsume(HttpServletRequest request ,HttpServletResponse response){
		String date= request.getParameter("date");
		JSONObject result=businessProcessDurationManager.businessprocessduration(date);
		return result.toString();
	}
	
	
	
	
	@RequestMapping("businessprocessdurationdifferent.do")
	public String businessprocessdurationdifferent(HttpServletRequest request,HttpServletResponse response){
		String curdate=request.getParameter("curdate");
		request.setAttribute("curdate", curdate);
		return "jsp/businessprocessdurationdifferent";
	}
	
	
	/**
	 * 查询不同业务办理时长
	 * @Title:processstimeconsumedifference
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-7-20
	 */
	@ResponseBody
	@RequestMapping("processstimeconsumedifference.do")
	public String processstimeconsumedifference(HttpServletRequest request ,HttpServletResponse response){
		String date= request.getParameter("date");
		JSONArray result=businessProcessDurationManager.processstimeconsumedifference(date);
		return result.toString();
	}


	public BusinessProcessDurationManager getBusinessProcessDurationManager() {
		return businessProcessDurationManager;
	}
	public void setBusinessProcessDurationManager(
			BusinessProcessDurationManager businessProcessDurationManager) {
		this.businessProcessDurationManager = businessProcessDurationManager;
	}
}
