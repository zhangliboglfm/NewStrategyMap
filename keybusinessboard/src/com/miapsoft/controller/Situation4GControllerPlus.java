package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MainManager;
import com.miapsoft.manager.Situation4gManager;
import com.miapsoft.manager.Situation4gManagerPlus;

/**
 * <p>Title: Situation4GController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class Situation4GControllerPlus {
	
	@Resource
	private MainManager mainManager;
	@Resource
	private Situation4gManagerPlus situation4gManagerPlus;
	
	
	/**
	 * 大屏4g套餐页面
	 * @Title:situation4gmainplus
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-9-13
	 */
	@RequestMapping(value="situation4gplus.do")
	public String situation4gmainplus(HttpServletRequest request,HttpServletResponse response) {
		String maxDate=mainManager.getMaxDate();
		String minDate=mainManager.getMinDate();
		String curdate=request.getParameter("curdate");
		String regionId=request.getParameter("regionId");
		String state=request.getParameter("state");
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("minDate", minDate);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId", regionId);
		request.setAttribute("state", state);
		return "plusjsp/situation4g";
	}
	
	
	@ResponseBody
	@RequestMapping("getdatafortablePlus.do")
	public String getdatafortable(HttpServletRequest request,HttpServletResponse response){
		String  date= request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONObject result = situation4gManagerPlus.getdatafortable(date,regionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getechartsdataPlus.do")
	public String getechartsdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String id=request.getParameter("id");
		String regionId=request.getParameter("regionId");
		JSONObject result= situation4gManagerPlus.getechartsdata(id, date,regionId);
		return result.toString();
	}
	
	
	
	
	/**
	 * 查询历史数据表格
	 * @Title:gethistorydata
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-8-3
	 */
	@ResponseBody
	@RequestMapping("gethistorydataPlus.do")
	public String gethistorydata(HttpServletRequest request,HttpServletResponse response){
		String zrid=request.getParameter("zrid");
		String regionId=request.getParameter("regionId");
		String date=request.getParameter("date");
		JSONArray result= situation4gManagerPlus.gethistorydata(zrid,regionId);
		return result.toString();
	}
	
	
	
	/*雷达图数据*/
	@ResponseBody
	@RequestMapping("getRadardataPlus.do")
	public String getRadardata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String zrid=request.getParameter("zrid");
		String regionId=request.getParameter("regionId");
		JSONArray result= situation4gManagerPlus.getRadardata(zrid,date,regionId);
		return result.toString();
	}
	/*雷达图数据*/
	@ResponseBody
	@RequestMapping("initDescrScatterPlus.do")
	public String initDescrScatter(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String zrid=request.getParameter("zrid");
		String zcid=request.getParameter("zcid");
		String regionid=request.getParameter("regionid");
		JSONArray result=situation4gManagerPlus.initDescrScatter(date, zrid, zcid, regionid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getpackfeeareachartdataPlus.do")
	public String getpackfeeareachartdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String ZRPackId=request.getParameter("zrid");
		String ZCPackId=request.getParameter("zcid");
		JSONObject result= situation4gManagerPlus.queryFeeAreaChartData(date, ZCPackId, ZRPackId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getpackfluxareachartdataPlus.do")
	public String getpackfluxareachartdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String ZRPackId=request.getParameter("zrid");
		String ZCPackId=request.getParameter("zcid");
		JSONObject result= situation4gManagerPlus.queryFluxAreaChartData(date, ZCPackId, ZRPackId);
		return result.toString();
	}
	
	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}


	public Situation4gManagerPlus getSituation4gManagerPlus() {
		return situation4gManagerPlus;
	}


	public void setSituation4gManagerPlus(
			Situation4gManagerPlus situation4gManagerPlus) {
		this.situation4gManagerPlus = situation4gManagerPlus;
	}

}
