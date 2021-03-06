package com.miapsoft.controller;	

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.ArticleDetailManager;

@Controller
public class ArtAddPgController {
	@Resource
	public ArticleDetailManager artDetailManager;

	public static String articlePath=ServerFilePath.getArticledir();//文章路径
	public static String photogPath=ServerFilePath.getPhotogdir();//摄影家路径
	public static String worksPath=ServerFilePath.getPhotogdir();//作品路径

	@RequestMapping("artAddPhotog.do")
	public String articleDetail(HttpServletRequest request, HttpServletResponse response){
		String pgGroups=request.getParameter("pgGroups");
		pgGroups=pgGroups.replace("[", "(").replace("]", ")").replace("\"", "'");
		request.setAttribute("pgGroups", pgGroups);
		return "pgmag/artAddPhotog";
	}
	//
	@ResponseBody
	@RequestMapping("getPhotogInfo.do")
	public String getArtTitle(HttpServletRequest request,HttpServletResponse response){
		String pgGroups=request.getParameter("pgGroups");
		String pgName=request.getParameter("pgName");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		String result=artDetailManager.selphotogList(pgName,start,end,pgGroups);
		return result;
	}
}
