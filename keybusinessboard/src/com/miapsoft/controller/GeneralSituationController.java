package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
import com.miapsoft.manager.GeneralSituationManager;
import com.miapsoft.manager.MainManager;
import com.miapsoft.util.ExcelUtil;
import com.miapsoft.util.LogUtil;

/**
 * <p>Title: GeneralSituationController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class GeneralSituationController {

	@Resource
	private MainManager mainManager;
	@Resource
	private GeneralSituationManager situationManager;

	@RequestMapping(value="generalsituation.do")
	public String main(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String curdate = request.getParameter("curdate");
		JSONObject dataDayTime = situationManager.getDataDayTime("TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW", "STATIS_DATE");
		String maxDate=dataDayTime.getString("maxdate");
		String minDate=dataDayTime.getString("mindate");
		String state=request.getParameter("state");
		String regionId=request.getParameter("regionId");
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("minDate", minDate);
		request.setAttribute("curdate", curdate);
		request.setAttribute("regionId", regionId);
		request.setAttribute("state", state);
		return "jspnew/generalsituation";
	}


	@ResponseBody
	@RequestMapping(value="getallcntdata.do")
	public String getallcntdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=situationManager.queryGeneralData(date, regionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="get4Gbusdata.do")
	public String get4Gbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=situationManager.query4GBusinessData(date, regionId);
		return result.toString();
	}

	/* 查询产品办理量变化*/
	@ResponseBody
	@RequestMapping(value="getAllHandlingNum.do")
	public String getAllHandlingNum(HttpServletRequest request,HttpServletResponse response) {
		JSONArray result = new JSONArray();
		String regionId = request.getParameter("regionId");
		String maxdate = request.getParameter("maxdate");
		result=situationManager.getAllHandlingNum(regionId,maxdate);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getKDbusdata.do")
	public String getKDbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=situationManager.queryKDBusinessData(date, regionId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getFlowbusdata.do")
	public String getFlowbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=situationManager.queryFlowBusinessData(date, regionId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="getOtherbusdata.do")
	public String getOtherbusdata(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		result=situationManager.queryOtherBusinessData(date, regionId);
		return result.toString();
	}

	/*办理量排名下载*/
	@RequestMapping("dowlaodHandling.do")
	public void dowlaodHandling(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONObject result=new JSONObject();
		result=situationManager.dowlaodHandling(date, regionId);
		String regionName = result.getString("regionName");
		String [] productType={"4G","kd","flux","other"};
		String [] productName={"4G产品","宽带产品","流量产品","其他产品"};
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"办理量排名";
		//String time = DateFormaters.getyyyyMMddHHmmss(new Date());
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		for (int i = 0; i < productType.length; i++) {
			JSONArray jsa = result.getJSONArray(productType[i]);
				String columnNames[]={"地域","累计办理量","累计办理量占比"};//列名
				String keys[]  =  {"REGION_NAME","ADD_PROD_CNT","ADD_PROD_PROP"};//map中的key
				ExcelUtil.createWorkBook(wb,productName[i],jsa,keys,columnNames);
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
	
	/*套餐办理分布下载*/
	@RequestMapping("downloadpack.do")
	public void downloadpack(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONObject result=new JSONObject();
		result=situationManager.downloadpack(date, regionId);
		String regionName = result.getString("regionName");
		String [] productType={"4G","kd","flux","other"};
		String [] productName={"4G产品","宽带产品","流量产品","其他产品"};
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"套餐办理分布";
		//String time = DateFormaters.getyyyyMMddHHmmss(new Date());
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		for (int i = 0; i < productType.length; i++) {
			JSONArray jsa = result.getJSONArray(productType[i]);
			String columnNames[]={"套餐名称","套餐办理量"};//列名
			String keys[]  =  {"ZR_NAME","ADD_PROD_CNT"};//map中的key
			ExcelUtil.createWorkBook(wb,productName[i],jsa,keys,columnNames);
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
	/*办理量趋势下载*/
	@RequestMapping("downloadTrend.do")
	public void downloadTrend(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONObject result=new JSONObject();
		result=situationManager.downloadTrend(date, regionId);
		String regionName = result.getString("regionName");
		String [] productType={"4G","kd","flux","other"};
		String [] productName={"4G产品","宽带产品","流量产品","其他产品"};
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"办理量趋势";
		//String time = DateFormaters.getyyyyMMddHHmmss(new Date());
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		for (int i = 0; i < productType.length; i++) {
			JSONArray jsa = result.getJSONArray(productType[i]);
			String columnNames[]={"月份","套餐办理量"};//列名
			String keys[]  =  {"abc","ADD_PROD_CNT"};//map中的key
			ExcelUtil.createWorkBook(wb,productName[i],jsa,keys,columnNames);
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
	/*渠道办理分布下载*/
	@RequestMapping("downloadchannel.do")
	public void downloadchannel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONObject result=new JSONObject();
		result=situationManager.downloadchannel(date, regionId);
		String regionName = result.getString("regionName");
		String [] productType={"4G","kd","flux","other"};
		String [] productName={"4G产品","宽带产品","流量产品","其他产品"};
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"渠道办理分布";
		//String time = DateFormaters.getyyyyMMddHHmmss(new Date());
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		for (int i = 0; i < productType.length; i++) {
			JSONArray jsa = result.getJSONArray(productType[i]);
			String columnNames[]={"渠道","套餐办理量"};//列名
			String keys[]  =  {"ACC_NAME","ADD_PROD_CNT"};//map中的key
			ExcelUtil.createWorkBook(wb,productName[i],jsa,keys,columnNames);
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

	public GeneralSituationManager getSituationManager() {
		return situationManager;
	}

	public void setSituationManager(GeneralSituationManager situationManager) {
		this.situationManager = situationManager;
	}
}
