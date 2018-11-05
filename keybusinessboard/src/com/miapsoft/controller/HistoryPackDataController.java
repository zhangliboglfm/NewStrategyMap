package com.miapsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: HistoryPackDataController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-8-2
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class HistoryPackDataController {
	
	@RequestMapping(value="historypackdatacontroller.do")
	public String historypackdata(HttpServletRequest request,HttpServletResponse response) {
		return "jspnew/historypackdata";
	}
}
