package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.IndexPoolManager;
import com.miapsoft.manager.SceneCustomizationIndexManager;

@Controller
public class SceneCustomizationIndexController {
	
	@Resource
	private SceneCustomizationIndexManager sceneCustomizationIndexManager;
	
	
	@RequestMapping("scenecustomizatinindex")
	public String  scenecustomizatinindex(HttpServletRequest request,HttpServletResponse response){
	    String dateType=request.getParameter("dateType");
	    request.setAttribute("dateType", dateType);
		return "jsp/scenecustomizatinindex";
	}
	
	@ResponseBody
	@RequestMapping(value="searchindex1.do")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String querytype = request.getParameter("querytype");
		String keystr = request.getParameter("keystr");
		String dateType=request.getParameter("dateType");
		result = sceneCustomizationIndexManager.queryForIndex(keystr.toUpperCase(), querytype,dateType);
		return result.toString();
	}

}
