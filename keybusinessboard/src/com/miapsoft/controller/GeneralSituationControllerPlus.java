package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.GeneralSituationManagerPlus;
import com.miapsoft.manager.MainManager;

/**
 * <p>Title: GeneralSituationController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class GeneralSituationControllerPlus {

	@Resource
	private MainManager mainManager;
	@Resource
	private GeneralSituationManagerPlus generalSituationManagerPlus;

	/**
	 * 大屏版总体情况页面
	 * @Title:main
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author:张立保
	 * @date:2017-9-12
	 */
	@RequestMapping(value="generalsituationplus.do")
	public String mainplus(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String curdate = request.getParameter("curdate");
		JSONObject dataDayTime = generalSituationManagerPlus.getDataDayTime("TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW", "STATIS_DATE");
		String maxDate=dataDayTime.getString("maxdate");
		String minDate=dataDayTime.getString("mindate");
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("minDate", minDate);
		request.setAttribute("curdate", curdate);
		return "plusjsp/generalsituation";
	}

	@ResponseBody
	@RequestMapping(value="getallcntdataPlus.do")
	public String getallcntdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=generalSituationManagerPlus.queryGeneralData(date, regionId);
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value="get4GbusdataPlus.do")
	public String get4Gbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=generalSituationManagerPlus.query4GBusinessData(date, regionId);
		return result.toString();
	}

	/* 查询产品办理量变化*/
	@ResponseBody
	@RequestMapping(value="getAllHandlingNumPlus.do")
	public String getAllHandlingNum(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		String regionId = request.getParameter("regionId");
		String maxdate = request.getParameter("maxdate");
		result=generalSituationManagerPlus.getAllHandlingNum(regionId,maxdate);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getKDbusdataPlus.do")
	public String getKDbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=generalSituationManagerPlus.queryKDBusinessData(date, regionId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getFlowbusdataPlus.do")
	public String getFlowbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=generalSituationManagerPlus.queryFlowBusinessData(date, regionId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getOtherbusdataPlus.do")
	public String getOtherbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=generalSituationManagerPlus.queryOtherBusinessData(date, regionId);
		return result.toString();
	}



	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}

	public GeneralSituationManagerPlus getGeneralSituationManagerPlus() {
		return generalSituationManagerPlus;
	}

	public void setGeneralSituationManagerPlus(
			GeneralSituationManagerPlus generalSituationManagerPlus) {
		this.generalSituationManagerPlus = generalSituationManagerPlus;
	}

	
}
