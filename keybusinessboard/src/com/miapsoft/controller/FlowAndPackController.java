package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ibm.db2.jcc.c.li;
import com.miapsoft.manager.FlowAndPackManager;
import com.miapsoft.manager.MainManager;
import com.miapsoft.util.ExcelUtil;
import com.miapsoft.util.LogUtil;

/**
 * <p>Title: FlowAndPackController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller 
public class FlowAndPackController {
	
	@Resource
	private MainManager mainManager;
	@Resource
	private FlowAndPackManager flowAndPackManager;
	
	@RequestMapping(value="flowandpack.do")
	public String main(HttpServletRequest request,HttpServletResponse response) {
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
		return "jspnew/flowandpack";
	}

	@ResponseBody
	@RequestMapping("gettableTopten.do")
	public String gettableTopten(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONArray result=flowAndPackManager.gettableTopten(date,regionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("initd3underLine.do")
	public String initd3underLine(HttpServletRequest request, HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		String yuzhi=request.getParameter("yuzhi");
		if(yuzhi==null){
			yuzhi="0.1";
		}
		JSONObject result=flowAndPackManager.initd3underLine(date,regionId,yuzhi);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("initMainPushPack.do")
	public String initMainPushPack(HttpServletRequest request,HttpServletResponse response){
		String date=request.getParameter("date");
		String regionId=request.getParameter("regionId");
		JSONObject result=flowAndPackManager.initMainPushPack(date,regionId);
		return result.toString();
	}
	
	/**
	 * 主推流量包历史数据查询
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("mainPushBackHistoty.do")
	public String mainPushBackHistoty(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String name = URLDecoder.decode(request.getParameter("name"),"utf-8");
		String date = request.getParameter("date");
		String regionId = request.getParameter("regionId");
		JSONObject result = flowAndPackManager.mainPushBackHistoty(date, regionId, name);
		return result.toString();
	}
	
	/*主推流量包下载*/
	@RequestMapping("dowlaodMainPush.do")
	public void downloadTrend(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String date =request.getParameter("date");
		String regionId =request.getParameter("regionId");
		
		//填充projects数据
		JSONObject result=new JSONObject();
		result=flowAndPackManager.initMainPushPack(date,regionId);
		JSONArray xname1 = result.getJSONArray("name");
		JSONArray ydata = result.getJSONArray("data");
		JSONArray ydata1 = result.getJSONArray("data1");
		JSONArray ydata2 = result.getJSONArray("data2");
		String regionName = result.getString("regionName");
		Workbook wb = new XSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String fileName=date+regionName+"主推流量包办理情况";
		String userName=request.getSession().getAttribute("userName").toString();
		String userId=request.getSession().getAttribute("userId").toString();
		String openDec=userName+",工号为："+userId+","+",下载了表格:"+fileName;
		LogUtil.addLog(request, "keybusinessboard20170817", openDec, "bigscreeninner");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int j = 0; j < xname1.size(); j++) {
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("name", xname1.getString(j));
			map.put("benyue", ydata.getString(j));
			map.put("shangyue", ydata1.getString(j));
			map.put("rate", ydata2.getString(j)+"%");
			list.add(map);
		}
		String columnNames[]={"主推流量包","本月办理量","上月办理量","同比增长"};//列名
		String keys[]  =  {"name","benyue","shangyue","rate"};//map中的key
		ExcelUtil.createWorkBook(wb,date,list,keys,columnNames);
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

	public FlowAndPackManager getFlowAndPackManager() {
		return flowAndPackManager;
	}

	public void setFlowAndPackManager(FlowAndPackManager flowAndPackManager) {
		this.flowAndPackManager = flowAndPackManager;
	}
}
