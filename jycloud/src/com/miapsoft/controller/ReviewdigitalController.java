package com.miapsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReviewdigitalController {
	
	/**
	 * 待审阅数码样片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("reviewdigital.do")
	public String reviewdigital(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/reviewdigital";
	}
}
