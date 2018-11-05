package com.miapsoft.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MainManager;


@Controller
public class MainController {
	
	@Resource
	public MainManager mainManager;
	
	@RequestMapping(value="main.do")
	public String Main(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String loginFlag = session.getAttribute("loginFlag")+"";
		if(!"success".equals(loginFlag)){
			return "login";
		};
		String userId = session.getAttribute("userId")+"";
		String userName = session.getAttribute("userName")+"";
		JSONArray topNav = mainManager.getTopNav(userId);
		request.setAttribute("topNav", topNav);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		return "jsp/main";
	}

	
	@ResponseBody
	@RequestMapping("getLeftNav.do")
	public String getLeftNav(HttpServletRequest request, HttpServletResponse response){
		String module_id = request.getParameter("module_id");
		String userId = request.getSession().getAttribute("userId")+"";
		JSONArray result = mainManager.getLeftNav(module_id,userId);
		return result.toString();
	}
	
	
	
	public MainManager getMainManager() {
		return mainManager;
	}
	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
	
	
}
