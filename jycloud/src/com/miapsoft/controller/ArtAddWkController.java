package com.miapsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.miapsoft.manager.ArticleDetailManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;

@Controller
public class ArtAddWkController {
	@Resource
	public ArticleDetailManager artDetailManager;

	public static String articlePath=ServerFilePath.getArticledir();//文章路径
	public static String photogPath=ServerFilePath.getPhotogdir();//摄影家路径
	public static String worksPath=ServerFilePath.getPhotogdir();//作品路径

	@RequestMapping("artAddWorks.do")
	public String articleDetail(HttpServletRequest request, HttpServletResponse response){
		String wkGroups=request.getParameter("wkGroups");
		String pgGroups=request.getParameter("pgGroups");
		request.setAttribute("wkGroups", wkGroups);
		request.setAttribute("pgGroups", pgGroups);
		return "pgmag/artAddWorks";
	}
	//查相关作品
	@ResponseBody
	@RequestMapping("selworksList.do")
	public String selworksList(HttpServletRequest request,HttpServletResponse response){
		String wkGroups=request.getParameter("wkGroups");//已有作品ID列表
		String pgOrder=request.getParameter("pgOrder");//要查询的作品编号
		String pgId=request.getParameter("pgId");//摄影家ID
		String currPage = request.getParameter("currPage");//页数
		String pageSize=request.getParameter("pageSize");//每页个数
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}
		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		String result=artDetailManager.selworksList(wkGroups,pgOrder,pgId,start,end);
		return result;
	}
	//查相关摄影家
	@ResponseBody
	@RequestMapping("selectRelatePg.do")
	public String selectRelatePg(HttpServletRequest request,HttpServletResponse response){
		String pgGroups = request.getParameter("pgGroups");
		JSONObject result = artDetailManager.selectRelatePg(pgGroups);
		return result.toString();
	}
}
