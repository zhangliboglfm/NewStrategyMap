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
public class DeletedsourcesController {
	@Resource
	public FilmfilterManager filmFilter;
	/**
	 * 已删除资源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deletedsources.do")
	public String deletedsources(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/deletedsources";
	}
	@ResponseBody
	@RequestMapping("recoveryPic.do")
	public String recoveryPic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.recoveryPic(SAMP_PIC_ID);
		return result;
	}
}
