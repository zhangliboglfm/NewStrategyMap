package com.miapsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title: MainController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2018-3-27
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class MainController {

	@RequestMapping(value="main.do")
	public String OptStgMap(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("LvlId", "1");
		session.setAttribute("regionId", "1");
		session.setAttribute("regionName", "河北");
		
		/*session.setAttribute("LvlId", "2");
		session.setAttribute("regionId", "311350");
		session.setAttribute("regionName", "石家庄");*/
		/*
		session.setAttribute("LvlId", "3");
		session.setAttribute("regionId", "313515");
		session.setAttribute("regionName", "涿鹿县");*/
		
		return "jsp/main";
	}
	
	@RequestMapping(value="flux.do")
	public String Flux(HttpServletRequest request,HttpServletResponse response){
		return "jsp/flux";
	}
	@RequestMapping(value="terminal.do")
	public String Terminal(HttpServletRequest request,HttpServletResponse response){
		return "jsp/terminal";
	}
	@RequestMapping(value="app.do")
	public String App(HttpServletRequest request,HttpServletResponse response){
		return "jsp/app";
	}
	@RequestMapping(value="complaint.do")
	public String Complaint(HttpServletRequest request,HttpServletResponse response){
		return "jsp/complaint";
	}
	@RequestMapping(value="setoff.do")
	public String Setoff(HttpServletRequest request,HttpServletResponse response){
		return "jsp/setoff";
	}
	
	@ResponseBody
	@RequestMapping(value="getdata.do")
	public String GetData(HttpServletRequest request,HttpServletResponse response){
		return "";
	}
}
