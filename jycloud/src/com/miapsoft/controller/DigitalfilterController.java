package com.miapsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DigitalfilterController {
	
	/**
	 * 数码样片筛选
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("digitalfilter.do")
	public String digitalfilter(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/digitalfilter";
	}
}
