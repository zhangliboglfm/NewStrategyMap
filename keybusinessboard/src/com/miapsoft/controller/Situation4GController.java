package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MainManager;
import com.miapsoft.manager.Situation4gManager;
import com.miapsoft.util.ExcelUtil;
import com.miapsoft.util.LogUtil;

/**
 * <p>Title: Situation4GController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class Situation4GController {
	
	@Resource
	private MainManager mainManager;
	@Resource
	private Situation4gManager situation4gManager;
	
	@RequestMapping(value="situation4gmain.do")
	public String situation4gmain(HttpServletRequest request,HttpServletResponse response) {
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
		return "jspnew/situation4gmain";
	}
	
	
	@RequestMapping(value="situation4g.do")
	public String situation4g(HttpServletRequest request,HttpServletResponse response) {
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
		return "jspnew/situation4g";
	}
	
	@ResponseBody
	@RequestMapping("getdatafortable.do")
	public String getdatafortable(HttpServletRequest request,HttpServletResponse response){
		String  date= request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONObject result = situation4gManager.getdatafortable(date,regionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getechartsdata.do")
	public String getechartsdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String id=request.getParameter("id");
		String regionId=request.getParameter("regionId");
		JSONObject result= situation4gManager.getechartsdata(id, date,regionId);
		return result.toString();
	}
	
	/**
	 * 查询升档、降档、平迁数据
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPackHistory.do")
	public String getPackHistory(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String packid=request.getParameter("packid");
		String regionId=request.getParameter("regionId");
		JSONObject result= situation4gManager.getPackHistory(packid, date,regionId);
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
	@RequestMapping("gethistorydata.do")
	public String gethistorydata(HttpServletRequest request,HttpServletResponse response){
		String zrid=request.getParameter("zrid");
		String regionId=request.getParameter("regionId");
		String date=request.getParameter("date");
		JSONArray result= situation4gManager.gethistorydata(zrid,regionId,date);
		return result.toString();
	}
	
	
	
	/*象限图历史数据*/
	@ResponseBody
	@RequestMapping("getRadardata.do")
	public String getRadardata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String zrid=request.getParameter("zrid");
		String regionId=request.getParameter("regionId");
		JSONArray result= situation4gManager.getRadardata(zrid,date,regionId);
		return result.toString();
	}
	/*雷达图数据*/
	@ResponseBody
	@RequestMapping("initDescrScatter.do")
	public String initDescrScatter(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String zrid=request.getParameter("zrid");
		String zcid=request.getParameter("zcid");
		String regionid=request.getParameter("regionid");
		JSONArray result=situation4gManager.initDescrScatter(date, zrid, zcid, regionid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getpackfeeareachartdata.do")
	public String getpackfeeareachartdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String ZRPackId=request.getParameter("zrid");
		String ZCPackId=request.getParameter("zcid");
		JSONObject result= situation4gManager.queryFeeAreaChartData(date, ZCPackId, ZRPackId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getpackfluxareachartdata.do")
	public String getpackfluxareachartdata(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String ZRPackId=request.getParameter("zrid");
		String ZCPackId=request.getParameter("zcid");
		JSONObject result= situation4gManager.queryFluxAreaChartData(date, ZCPackId, ZRPackId);
		return result.toString();
	}
	
	/**
	 * 下载升降档表格详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("downloadPackSJPDesc.do")
	public void downloadPackSJPDesc(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONArray result=new JSONArray();
		result=situation4gManager.downloadPackSJPDesc(date, regionId);
		String regionName = result.getString(0);
		String [] productType={"MARKT_SOLUTION_NAME","ADD_NUM","ADD_NUM_SD","ADD_NUM_JD","ADD_NUM_PQ","JIANG_SUM","SHENG_SUM","ALL_SUM"};
		String [] productName={"名称","办理量(户)","升档办理量(户)","降档办理量(户)","平迁办理量(户)","降档收入(元)","升档收入(元)","金额(元)"};
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"套餐升降详情";
		//String time = DateFormaters.getyyyyMMddHHmmss(new Date());
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		for (int i = 1; i < result.size(); i++) {
			JSONArray jsa = result.getJSONArray(i);
			ExcelUtil.createWorkBook(wb,jsa.getString(0),jsa.getJSONArray(1),productType,productName);
		}
		wb.write(os); 
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	
	
	public MainManager getMainManager() {
		return mainManager;
	}

	public void setMainManager(MainManager mainManager) {
		this.mainManager = mainManager;
	}

	public Situation4gManager getSituation4gManager() {
		return situation4gManager;
	}

	public void setSituation4gManager(Situation4gManager situation4gManager) {
		this.situation4gManager = situation4gManager;
	}
}
