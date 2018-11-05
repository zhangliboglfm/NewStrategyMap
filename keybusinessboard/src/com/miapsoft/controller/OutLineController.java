package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miapsoft.manager.MainManager;

/**
 * <p>Title: OutLineController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-30
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class OutLineController {
	@Resource
	private MainManager mainManager;
	@RequestMapping(value="outline.do")
	public String outline(HttpServletRequest request,HttpServletResponse response) {
		String maxdate = mainManager.getMaxDate();
		String mindate = mainManager.getMinDate();
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("curdate", curdate);
		
		return "jsp/outline";
	}
}
