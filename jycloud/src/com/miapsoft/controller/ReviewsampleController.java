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
public class ReviewsampleController {
	@Resource
	public FilmfilterManager filmFilter;
	/**
	 * 已审阅样片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("reviewsample.do")
	public String reviewsample(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/reviewsample";
	}
	@ResponseBody
	@RequestMapping("screenPic2.do")
	public String screenPic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.screenPic(SAMP_PIC_ID);
		return result;
	}
}
