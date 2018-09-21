package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miapsoft.manager.PointDetailAnalysisManager;
import com.miapsoft.model.UserInfo;

/**
 * <p>Title: PointDetailAnalysisController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class PointDetailAnalysisController {
		
	@Resource
	private PointDetailAnalysisManager pointDetailAnalysisManager;
	
	
	@RequestMapping(value="pointdetailanalysis.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		try {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
		} catch (Exception e) {
			System.err.println("用户信息异常！");
			e.printStackTrace();
			request.setAttribute("errorMsg", "用户信息异常！");
			return "errors/biz-error";
		}
		String pointId = request.getParameter("pointId");
		String pointType = request.getParameter("pointType");
		String dateType = request.getParameter("dateType");
		String date = request.getParameter("date");
		/*查询兴趣点的数据*/
		JSONObject result1=pointDetailAnalysisManager.queryPointDetailAnalysis(pointId, dateType, date);
		JSONObject result2=pointDetailAnalysisManager.queryPeopleFlowTrend(pointId, pointType, dateType, date);
		request.setAttribute("result1", result1);
		request.setAttribute("dateType", dateType);
		request.setAttribute("date", date);
		request.setAttribute("pointType", pointType);
		request.setAttribute("result2", result2);
		return "jsp/pointdetailanalysis";
	}
	
	
	
	
	public PointDetailAnalysisManager getPointDetailAnalysisManager() {
		return pointDetailAnalysisManager;
	}
	public void setPointDetailAnalysisManager(
			PointDetailAnalysisManager pointDetailAnalysisManager) {
		this.pointDetailAnalysisManager = pointDetailAnalysisManager;
	}
	
}
