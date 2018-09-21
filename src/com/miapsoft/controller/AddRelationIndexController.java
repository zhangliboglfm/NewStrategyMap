package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.AddRelationIndexManager;
import com.miapsoft.manager.IndexPoolManager;
import com.miapsoft.model.UserInfo;

/**
 * <p>Title: AddRecommendIndexController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class AddRelationIndexController {
	
	@Resource
	private IndexPoolManager indexPoolManager;
	@Resource
	private AddRelationIndexManager addRelationIndexManager;
	
	@RequestMapping(value="addrelationindex.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		UserInfo userInfo;
		try {
			userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
		} catch (Exception e) {
			System.err.println("用户信息异常！");
			e.printStackTrace();
			request.setAttribute("errorMsg", "用户信息异常！");
			return "errors/biz-error";
		}
		String indexId = request.getParameter("indexId");
		String dateType = request.getParameter("dateType");
		String indexlvl = request.getParameter("indexlvl");
		String indexname="";
		JSONObject indexjson = indexPoolManager.queryForIndexInfoById(indexId, dateType, indexlvl);
		if(indexjson != null && indexjson.size()!=0){
			indexname=indexjson.getString("MM_NAME");
		}
		request.setAttribute("indexId", indexId);
		request.setAttribute("dateType", dateType);
		request.setAttribute("indexName", indexname);
		request.setAttribute("indexLVL", indexlvl);
		return "jsp/addrecommendindex";
	}
	
	@ResponseBody
	@RequestMapping(value="searchaddrelindex.do")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String querytype = request.getParameter("querytype");
		String keystr = request.getParameter("keystr");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String indexlvl = request.getParameter("indexlvl");
		String userId = request.getParameter("userId");
		result = addRelationIndexManager.queryForIndex(keystr, querytype, datetype, indexid, indexlvl, userId);
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value="relthisindexwithmainindex.do")
	public String relThisIndexWithMainIndex(HttpServletRequest request,HttpServletResponse response){
		JSONObject resultjson = new JSONObject();
		String result = "";
		String thisindex = request.getParameter("thisindex");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String userId = request.getParameter("userId");
		String updatetype = request.getParameter("updatetype");
		try {
			result = addRelationIndexManager.updateIndexRelationState(thisindex, datetype, indexid, userId, updatetype);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
		resultjson.put("result", result);
		return resultjson.toString();
	}
	
	public IndexPoolManager getIndexPoolManager() {
		return indexPoolManager;
	}

	public void setIndexPoolManager(IndexPoolManager indexPoolManager) {
		this.indexPoolManager = indexPoolManager;
	}

	public AddRelationIndexManager getAddRelationIndexManager() {
		return addRelationIndexManager;
	}

	public void setAddRelationIndexManager(
			AddRelationIndexManager addRelationIndexManager) {
		this.addRelationIndexManager = addRelationIndexManager;
	}
}
