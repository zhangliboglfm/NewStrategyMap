package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.FilmfilterManager;


@Controller
public class ReviewfilmController {
	@Resource
	public FilmfilterManager filmFilter;
	/**
	 * 带审阅胶片样片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("reviewfilm.do")
	public String reviewfilm(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/reviewfilm";
	}
	@ResponseBody
	@RequestMapping("rejectPic.do")
	public String rejectPic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.rejectPic(SAMP_PIC_ID);
		return result;
	}
	@ResponseBody
	@RequestMapping("pass.do")
	public String pass(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.pass(SAMP_PIC_ID);
		return result;
	}
}
