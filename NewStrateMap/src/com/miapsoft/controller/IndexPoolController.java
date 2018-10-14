package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.IndexPoolManager;
import com.miapsoft.model.UserInfo;

/**
 * <p>Title: IndexPoolController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class IndexPoolController {
	@Resource
	private IndexPoolManager indexPoolManager;
	
	@RequestMapping(value="indexpool.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		try {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
			if(userInfo==null){
				request.setAttribute("errorMsg", "用户信息异常");
				return "errors/biz-error";
			}
		} catch (Exception e) {
			System.err.println("用户信息异常！");
			e.printStackTrace();
			request.setAttribute("errorMsg", "用户信息异常！");
			return "errors/biz-error";
		}
		return "jsp/indexpool";
	}

	@ResponseBody
	@RequestMapping(value="searchindex.do")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String querytype = request.getParameter("querytype");
		String keystr = request.getParameter("keystr");
		result = indexPoolManager.queryForIndex(keystr.toUpperCase(), querytype);
		return result.toString();
	}

	public IndexPoolManager getIndexPoolManager() {
		return indexPoolManager;
	}

	public void setIndexPoolManager(IndexPoolManager indexPoolManager) {
		this.indexPoolManager = indexPoolManager;
	}
}
