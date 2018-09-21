package com.miapsoft.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.ProvinceCityRankManager;
import com.miapsoft.manager.RegionManager;
import com.miapsoft.model.UserInfo;
import com.miapsoft.util.ExcelUtil;

/**
 * <p>Title: ProvinceCityRankController.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */


@Controller
public class ProvinceCityRankController {
	
	@Resource
	private RegionManager regionManager;
	@Resource 
	private ProvinceCityRankManager provinceCityRankManager;
	
	private JSONArray dishijson;
	private JSONArray xianqujson;
	private JSONObject allxianqujson;
	
	@RequestMapping("provincecityrank.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		UserInfo userInfo = null;
		try {
			userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
		} catch (Exception e) {
			System.err.println("用户信息异常！");
			e.printStackTrace();
			request.setAttribute("errorMsg", "用户信息异常！");
			return "errors/biz-error";
		}
		String userRegionlvl = userInfo.getRegion().getLvlId();
		String userRegionId = userInfo.getRegion().getRegionId();
		if("1".equals(userRegionlvl)){
			dishijson=regionManager.QueryForDishiData();
			allxianqujson=regionManager.QueryForXianquData(dishijson);
		}else if("2".equals(userRegionlvl)){
			dishijson=regionManager.QueryForDishiData2(userRegionId);
			allxianqujson=regionManager.QueryForXianquData2(dishijson);
		}else if("3".equals(userRegionlvl)){
			dishijson=regionManager.QueryForDishiData3(userRegionId);
			allxianqujson=regionManager.QueryForXianquData3(dishijson,userRegionId);
		}

		String maxDateD=provinceCityRankManager.getMaxDate("D");
		String maxDateM=provinceCityRankManager.getMaxDate("M");
		String mm_leve = request.getParameter("mm_leve");
		request.setAttribute("dishijson", dishijson);
		request.setAttribute("xinqujson", xianqujson);
		request.setAttribute("allxianqujson", allxianqujson);
		request.setAttribute("mm_leve", mm_leve);
		request.setAttribute("maxDateD", maxDateD);
		request.setAttribute("maxDateM", maxDateM);
		request.setAttribute("date", request.getParameter("date"));
		request.setAttribute("dateType", request.getParameter("dateType"));
		request.setAttribute("dishiId", request.getParameter("dishiId"));
		request.setAttribute("indexId", request.getParameter("indexId"));
		request.setAttribute("xianquId", request.getParameter("xianquId"));
		return "jsp/provincecityrank";
	}
	
	/**
	 * 查询左侧指标信息
	 * @Title:initLeftQueryIndex
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	@ResponseBody
	@RequestMapping("initLeftQueryIndex.do")
	public String initLeftQueryIndex(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String datetype=request.getParameter("datetype");
		String date=request.getParameter("date");
		String MM_LEVEL=null;
		String regionId2="";//要查询地域的id
		String dishiRegionId=request.getParameter("dishiRegionId");
		String xianquRegionId=request.getParameter("xianquRegionId");
		if("all".equals(dishiRegionId.toLowerCase())){
			regionId2="1";
			MM_LEVEL="CITY";
		}else {
			if("all".equals(xianquRegionId.toLowerCase())){
				regionId2=dishiRegionId;
				MM_LEVEL="CITY";
			}else {
				regionId2=xianquRegionId;
				MM_LEVEL="COUNTY";
			}
		}
		result =provinceCityRankManager.initLeftQueryIndex(regionId2, date, MM_LEVEL, datetype);
		return result.toString();
	}

	/***
	 * 查询相关兴趣点的数量
	 * @Title:initInterstingPointNumber
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	@ResponseBody
	@RequestMapping("initInterstingPointNumber.do")
	public String initInterstingPointNumber(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String dishiRegionId=request.getParameter("dishiRegionId");
		String xianquRegionId=request.getParameter("xianquRegionId");
		result =provinceCityRankManager.initInterstingPointNumber(dishiRegionId,xianquRegionId);
		return result.toString();
	}
	
	/**
	 * 查询指标信息
	 * @Title:queryIndexByIndexKey
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-6-15
	 */
	@ResponseBody
	@RequestMapping(value="selectIndexByKey.do")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String datetype = request.getParameter("datatype");
		String keystr = request.getParameter("keystr");
		String MM_LEVEL=request.getParameter("MM_LEVEL");
		result = provinceCityRankManager.queryForIndexByIndexKey(datetype, keystr.toUpperCase(),MM_LEVEL);
	
		return result.toString();
	}
	
	/**
	 * 查询相关指标
	 * @Title:queryRelevantIndex
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	@ResponseBody
	@RequestMapping("queryRelevantIndex.do")
	public String queryRelevantIndex(HttpServletRequest request,HttpServletResponse response){
		String MM_LEVEL=null;
		JSONArray result =new JSONArray();
		String dishiRegionId=request.getParameter("dishiRegionId");
		String xianquRegionId=request.getParameter("xianquRegionId");
		String regionid=null;
		if("all".equals(dishiRegionId.toLowerCase())){
			regionid="1";
			MM_LEVEL="CITY";
		}else {
			if("all".equals(xianquRegionId.toLowerCase())){
				regionid=dishiRegionId;
				MM_LEVEL="CITY";
			}else {
				regionid=xianquRegionId;
				MM_LEVEL="COUNTY";
			}
		}
		String datetype=request.getParameter("datetype");
		String date=request.getParameter("date");
		String indexid=request.getParameter("indexid");
		result=provinceCityRankManager.queryRelevantIndex(datetype, date, indexid,regionid,MM_LEVEL);
		return result.toString();
	}
	
	/**
	 * 查询指标以及指标切换，渲染地图
	 * @Title:querySwitchIndex
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	@ResponseBody
	@RequestMapping("querySwitchIndex")
	public String querySwitchIndex(HttpServletRequest request,HttpServletResponse response){
		JSONObject result =new JSONObject();
		String date=request.getParameter("date");
		String MM_ID=request.getParameter("MM_ID");
		String dishiRegionId=request.getParameter("dishiRegionId");
		String xianquRegionId=request.getParameter("xianquRegionId");
		String dateType=request.getParameter("dateType");
		result=provinceCityRankManager.querySwitchIndex(MM_ID, date, dishiRegionId, xianquRegionId,dateType);
		return result.toString();
	}
	
	
	@RequestMapping("downloadIndexdata")
	public String downloadIndexdata(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fileName="";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String date =request.getParameter("date");
		String datetype =request.getParameter("datetype");
		String dishiid =request.getParameter("dishiid");
		String xianquid =request.getParameter("xianquid");
		String indexid =request.getParameter("indexid");
		String MM_LEVEL=request.getParameter("MM_LEVEL");
		String regionname = "";
		/*获取该地域的name*/
		JSONArray allxinaqudishijson=null;
		if(dishiid.equalsIgnoreCase("all")){
			regionname="河北省";
			allxinaqudishijson=regionManager.QueryForDishiData();
		}else if(xianquid.equalsIgnoreCase("all")){
			JSONObject regionjson = regionManager.QueryOneRegionData(dishiid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
				
				allxinaqudishijson=regionManager.QueryxianquIdbydishiId(dishiid);
			}
		}else{
			JSONObject regionjson = regionManager.QueryOneRegionData(xianquid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
				allxinaqudishijson=null;
			}
		};
		List<Map<String, Object>> list = provinceCityRankManager.downloadIndexdata(indexid, date, datetype, dishiid, xianquid,MM_LEVEL,allxinaqudishijson);
		String datetime = "";
		String mmname = "--";
		String mmcolumnName="--";
		String ringcolumnName="--";
		String samecolumnName="--";
		if(list.size()!=0){
			if(list.get(0).get("MM_NAME")!=null && list.get(0).get("MM_UNIT")!=null){
				mmname = list.get(0).get("MM_NAME").toString();
				mmcolumnName = list.get(0).get("MM_NAME").toString()+"("+list.get(0).get("MM_UNIT").toString()+")";
			}
			if(list.get(0).get("RING_RATE_NAME")!=null){
				ringcolumnName = list.get(0).get("RING_RATE_NAME").toString()+"(%)";
			}
			if(list.get(0).get("SAME_RATE_NAME")!=null){
				samecolumnName = list.get(0).get("SAME_RATE_NAME").toString()+"(%)";
			}
			datetime = list.get(0).get("STATIS_TIME").toString();
		}
		fileName = datetime+regionname+mmname+"统计数据";
		String [] key = {"STATIS_TIME","REGION_NAME","MM_VAL","RING_RATE","SAME_RATE"};
		String [] columnNames = {"统计时间","地域名称",mmcolumnName,ringcolumnName,samecolumnName};
		String [] sheetName = {datetime};
		
		ExcelUtil.createWorkBook(list,key,columnNames,sheetName).write(os); 
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
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
		return null;
	}
	
	public RegionManager getRegionManager() {
		return regionManager;
	}

	public void setRegionManager(RegionManager regionManager) {
		this.regionManager = regionManager;
	}

	public JSONArray getDishijson() {
		return dishijson;
	}

	public void setDishijson(JSONArray dishijson) {
		this.dishijson = dishijson;
	}

	

	public JSONArray getXianqujson() {
		return xianqujson;
	}


	public void setXianqujson(JSONArray xianqujson) {
		this.xianqujson = xianqujson;
	}


	public JSONObject getAllxianqujson() {
		return allxianqujson;
	}

	public void setAllxianqujson(JSONObject allxianqujson) {
		this.allxianqujson = allxianqujson;
	}


	public ProvinceCityRankManager getProvinceCityRankManager() {
		return provinceCityRankManager;
	}


	public void setProvinceCityRankManager(ProvinceCityRankManager provinceCityRankManager) {
		this.provinceCityRankManager = provinceCityRankManager;
	}
	
}
