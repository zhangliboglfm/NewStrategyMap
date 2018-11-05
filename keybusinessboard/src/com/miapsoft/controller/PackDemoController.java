package com.miapsoft.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.CommonManager;
import com.miapsoft.manager.MainManager;

/**
 * <p>Title: PackDemoController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class PackDemoController {

	@Resource
	private CommonManager commonManager;
	@Resource
	private MainManager mainManager;
	
	@RequestMapping(value="packdemocontroller.do")
	public String packdemocontroller(HttpServletRequest request,HttpServletResponse response) {
		String maxdate = mainManager.getMaxDate();
		String mindate = mainManager.getMinDate();
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("curdate", curdate);
		return "jsp/packdemo";
	}

	@RequestMapping(value="packqipaocontroller.do")
	public String packqipaocontroller(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO", "STATIS_DATE");
		String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");
		String curdate = request.getParameter("curdate");
		String regionId = request.getParameter("regionId");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("days", days);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId",regionId);
		return "jsp/packqipao";
	}
	@RequestMapping(value="newpackqipaocontroller.do")
	public String newpackqipaocontroller(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO", "STATIS_DATE");
		String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");
		String curdate = request.getParameter("curdate");
		String regionId = request.getParameter("regionId");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("days", days);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId",regionId);
		return "jspnew/packqipao";
	}
	@RequestMapping(value="packqipaocontrollerplus.do")
	public String packqipaocontrollerplus(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO", "STATIS_DATE");
		String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");
		String curdate = request.getParameter("curdate");
		String regionId = request.getParameter("regionId");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("days", days);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId",regionId);
		return "plusjsp/packqipao";
	}
	
	
	
	@RequestMapping(value="packqipaoCURcontroller.do")
	public String packqipaoCURcontroller(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO1", "STATIS_DATE");
		String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("days", days);
		request.setAttribute("curdate", curdate);
		return "jsp/packqipaocur";
	}
	
	@RequestMapping(value="packqipaoincomecontroller.do")
	public String packqipaoincomecontroller(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO_NEW", "STATIS_DATE");
		String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", maxdate);
		request.setAttribute("mindate", mindate);
		request.setAttribute("days", days);
		request.setAttribute("curdate", curdate);
		return "jsp/packqipaoincome";
	}
	
	@RequestMapping(value="packprcontroller.do")
	public String packprcontroller(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//JSONObject obj = commonManager.getDataTime("TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO7", "STATIS_MONTH");
		/*String maxdate = obj.getString("maxdate");
		String mindate = obj.getString("mindate");
		long days = obj.getLong("days");*/
		String curdate = request.getParameter("curdate");
		request.setAttribute("maxdate", "201706");
		request.setAttribute("mindate", "201705");
		request.setAttribute("days", 2);
		request.setAttribute("curdate", curdate);
		return "jsp/packqr";
	}
	
	@ResponseBody
	@RequestMapping(value="getpackdemodata.do")
	public String getpackdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String linetype = request.getParameter("linetype");
		result = commonManager.getPackDemoData(date, linetype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackdemodata2.do")
	public String getpackdata2(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String linetype = request.getParameter("linetype");
		result = commonManager.getPackDemoData2(date, linetype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqipaodata.do")
	public String getpackqipaodata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String linetype = request.getParameter("linetype");
		result = commonManager.getPackQipaoData(date, linetype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqipaodata2.do")
	public String getpackqipaodata2(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String linetype = request.getParameter("linetype");
		result = commonManager.getPackQipaoData2(date, linetype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqipaodata3.do")
	public String getpackqipaodata3(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		JSONArray result = new JSONArray();
		String regionId = request.getParameter("regionId");
		String dateMin = request.getParameter("dateMin");
		result = commonManager.getPackQipaoData3(regionId,dateMin);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqipaodata4.do")
	public String getpackqipaodata4(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		result = commonManager.getPackQipaoData4();
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqipaodata5.do")
	public String getpackqipaodata5(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		result = commonManager.getPackQipaoData5();
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpackqrdata.do")
	public String getpackqrdata(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		result = commonManager.getPRData();
		return result.toString();
	}
	
	public CommonManager getCommonManager() {
		return commonManager;
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}
}
