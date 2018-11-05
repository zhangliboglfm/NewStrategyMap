package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miapsoft.manager.GeneralSituationManager;
import com.miapsoft.manager.MainManager;
import com.miapsoft.manager.sys.SystemManager;
import com.miapsoft.model.UserInfo;
import com.miapsoft.util.LogUtil;

/**
 * <p>Title: MainController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-30
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class MainController {
	
	@Resource
	public MainManager mainManager;
	@Resource
	private GeneralSituationManager situationManager;
	@Resource
	private SystemManager systemManager;
	
	
	@RequestMapping(value="main.do")
	public String main(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result="errors/biz-error";
		String errorMsg="您没有权限访问！";
		String userId = request.getParameter("userId");
		if(userId!=null&&!"".equals(userId)){
			UserInfo userInfo = null;
			try {
				userInfo = systemManager.getUserInfo(userId);
			} catch (Exception e) {
				System.err.println("数据库连接异常或SQL查询异常！");
				e.printStackTrace();
				result = "errors/error";
				return result;
			}
			if(userInfo==null){
				errorMsg="未找到用户信息！";
				request.setAttribute("errorMsg", errorMsg);
				return result;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userInfo.getUser().getUserName());
			session.setAttribute("lvlId", userInfo.getRegion().getLvlId());
			session.setAttribute("regionParentId", userInfo.getRegion().getRegionParentId());
			session.setAttribute("regionDesc", userInfo.getRegion().getRegionDesc());
			session.setAttribute("regionName", userInfo.getRegion().getRegionName());
			
			JSONObject dataDayTime = situationManager.getDataDayTime("TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW", "STATIS_DATE");
			String maxDate=dataDayTime.getString("maxdate");
			String minDate=dataDayTime.getString("mindate");
			if(userInfo.getRegion().getLvlId().equals("1")){
				session.setAttribute("regionId", userInfo.getRegion().getRegionId());
				JSONArray dishijson=mainManager.QueryForDishiData();
				request.setAttribute("dishijson", dishijson);
			}else if(userInfo.getRegion().getLvlId().equals("2")){
				String RegionId=userInfo.getRegion().getRegionId();
				JSONArray dishijson=mainManager.QueryForOneDiShi(RegionId);
				JSONObject obj=(JSONObject) dishijson.get(0);
				session.setAttribute("regionId", obj.get("regionId").toString());
				request.setAttribute("dishijson", dishijson);
			}else if(userInfo.getRegion().getLvlId().equals("3")){
				String RegionId=userInfo.getRegion().getRegionId();
				request.setAttribute("errorMsg", "您没有访问权限！！");
				return result;
			}
			request.setAttribute("maxDate", maxDate);
			request.setAttribute("minDate", minDate);
			
			result="jspnew/main";
		}
		request.setAttribute("errorMsg", errorMsg);
		String userName=request.getSession().getAttribute("userName").toString();
		String openDec=userName+",工号为："+userId+",访问了该项目";
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		return result;
	}
	
	
	@RequestMapping(value="mainplus.do")
	public String mainplus(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result="errors/biz-error";
		String errorMsg="您没有权限访问！";
		String userId = request.getParameter("userId");
		if(userId!=null&&!"".equals(userId)){
			UserInfo userInfo = null;
			try {
				userInfo = systemManager.getUserInfo(userId);
			} catch (Exception e) {
				System.err.println("数据库连接异常或SQL查询异常！");
				e.printStackTrace();
				result = "errors/error";
				return result;
			}
			if(userInfo==null){
				errorMsg="未找到用户信息！";
				request.setAttribute("errorMsg", errorMsg);
				return result;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userInfo.getUser().getUserName());
			session.setAttribute("lvlId", userInfo.getRegion().getLvlId());
			session.setAttribute("regionParentId", userInfo.getRegion().getRegionParentId());
			session.setAttribute("regionDesc", userInfo.getRegion().getRegionDesc());
			session.setAttribute("regionName", userInfo.getRegion().getRegionName());
			
			JSONObject dataDayTime = situationManager.getDataDayTime("TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW", "STATIS_DATE");
			String maxDate=dataDayTime.getString("maxdate");
			String minDate=dataDayTime.getString("mindate");
			if(userInfo.getRegion().getLvlId().equals("1")){
				session.setAttribute("regionId", userInfo.getRegion().getRegionId());
				JSONArray dishijson=mainManager.QueryForDishiData();
				request.setAttribute("dishijson", dishijson);
			}else if(userInfo.getRegion().getLvlId().equals("2")){
				String RegionId=userInfo.getRegion().getRegionId();
				JSONArray dishijson=mainManager.QueryForOneDiShi(RegionId);
				JSONObject obj=(JSONObject) dishijson.get(0);
				session.setAttribute("regionId", obj.get("regionId").toString());
				request.setAttribute("dishijson", dishijson);
			}else if(userInfo.getRegion().getLvlId().equals("3")){
				String RegionId=userInfo.getRegion().getRegionId();
				request.setAttribute("errorMsg", "您没有访问权限！！");
				return result;
			}
			request.setAttribute("maxDate", maxDate);
			request.setAttribute("minDate", minDate);
			
			
			result="plusjsp/mainplus";
		}
		request.setAttribute("errorMsg", errorMsg);
		return result;
	}
	
	
	
	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
	
}
