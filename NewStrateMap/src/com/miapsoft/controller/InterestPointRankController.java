package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
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
import com.miapsoft.manager.InterestPointRankManager;
import com.miapsoft.manager.RegionManager;
import com.miapsoft.model.UserInfo;
import com.miapsoft.util.ExcelUtil;

/**
 * <p>Title: InterestPointRankController.java</p>
 * <p>Description: 兴趣点控制层</p>
 * @author: 白少华
 * @time: 2017-6-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class InterestPointRankController {

	@Resource
	private RegionManager regionManager;
	@Resource
	private InterestPointRankManager pointRankManager;
	
	private JSONArray dishijson;
	private JSONArray xinqujson;
	private JSONObject allxianqujson;
	private String maxDateD;
	private String minDateD;
	private String maxDateM;
	private String minDateM;
	
	@RequestMapping(value="interestpointrank.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		String curdishiid = request.getParameter("curdishiid");
		String curxianquid = request.getParameter("curxianquid");
		String curindexid = request.getParameter("curindexid");
		String curdate = request.getParameter("curdate");
		String curdatetype = request.getParameter("curdatetype");
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
			xinqujson=regionManager.QueryForXianquData(userRegionId);
		}else if("3".equals(userRegionlvl)){
			String parentregionid = request.getSession().getAttribute("regionParentId").toString();
			dishijson=regionManager.QueryForDishiData2(parentregionid);
			xinqujson=regionManager.QueryForOneXianquData(userRegionId);
		}
		JSONObject datejson = pointRankManager.querySystemMAXDate();
		JSONObject monthjson = pointRankManager.querySystemMAXMonth();
		if(datejson!=null&&datejson.size()!=0){
			maxDateD=datejson.getString("MAX_STATIS_DATE");
			minDateD=datejson.getString("MIN_STATIS_DATE");
		}else{
			
		}
		if(monthjson!=null&&monthjson.size()!=0){
			maxDateM=monthjson.getString("MAX_STATIS_DATE");
			minDateM=monthjson.getString("MIN_STATIS_DATE");
		}else{
			
		}
		if(curdishiid!=null&&"all".equals(curdishiid.toLowerCase())){
			curdishiid = "311350";
		}
		if(curxianquid!=null&&"all".equals(curxianquid.toLowerCase())){
			curxianquid = "all";
		}
		request.setAttribute("dishijson", dishijson);
		request.setAttribute("xianqujson", xinqujson);
		request.setAttribute("allxinqujson", allxianqujson);
		request.setAttribute("maxDateD", maxDateD);
		request.setAttribute("minDateD", minDateD);
		request.setAttribute("maxDateM", maxDateM);
		request.setAttribute("minDateM", minDateM);
		request.setAttribute("curdishiid", curdishiid);
		request.setAttribute("curxianquid", curxianquid);
		request.setAttribute("cur_index", curindexid);
		request.setAttribute("curdate", curdate);
		request.setAttribute("curdatetype", curdatetype);
		return "jsp/interestpointrank";
	}

	@ResponseBody
	@RequestMapping(value="getmapcurindexdata.do")
	public String getMapCurIndexData(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		String indexid = request.getParameter("indexid");
		String datetype = request.getParameter("datatype");
		String date = request.getParameter("date");
		String regionid = request.getParameter("regionid");
		String mapmodel = request.getParameter("mapmodel");
		result = pointRankManager.queryMapIndex(indexid, date, datetype, regionid, mapmodel.toUpperCase());
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getpointtype.do")
	public String getPointType(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String dishiregionId = request.getParameter("dishiid");
		String xianquregionId = request.getParameter("xinaquid");
		result = pointRankManager.queryPointTypeData(dishiregionId, xianquregionId);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="getscenepointtype.do")
	public String getScenePointType(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String dishiregionId = request.getParameter("dishiid");
		String xianquregionId = request.getParameter("xinaquid");
		String sceneid = request.getParameter("sceneid");
		result = pointRankManager.queryPointTypeData(dishiregionId, xianquregionId,sceneid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="queryindexbykey.do")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String datetype = request.getParameter("datatype");
		String keystr = request.getParameter("keystr");
		result = pointRankManager.queryForIndexByIndexKey(datetype, keystr.toUpperCase());
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querymappointbykey.do")
	public String queryMapPointByKey(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String keystr = request.getParameter("keystr");
		String pointtype = request.getParameter("pointtype");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		result = pointRankManager.queryForPointByKeys(pointtype, keystr, dishiid, xianquid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="queryregionindex.do")
	public String queryRegionIndex(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String regionid = request.getParameter("regionid");
		result = pointRankManager.queryRegionIndex(date, datetype, regionid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="queryrelindex.do")
	public String queryRelIndex(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String regionid = request.getParameter("regionid");
		String indexid = request.getParameter("indexid");
		String mapmodel = request.getParameter("mapmodel");
		Object userIdObj = request.getSession().getAttribute("userId");
		String userId = "";
		if(userIdObj == null){
			return "";
		}else{
			userId = userIdObj.toString();
		}
		result = pointRankManager.queryRelIndex(date, datetype, regionid, indexid, userId, mapmodel);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querypointbaseinfo.do")
	public String queryPointBaseInfo(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String pointid = request.getParameter("pointid");
		String pointtype = request.getParameter("pointtype");
		result = pointRankManager.queryPointBaseInfo(pointtype, pointid, date, datetype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querypointpolygondata.do")
	public String queryPointPolygonData(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String pointtype = request.getParameter("pointtype");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		String extentjson = request.getParameter("extentjson");
		JSONObject extent = JSONObject.fromObject(extentjson);
		result = pointRankManager.queryPointPolygonData(pointtype, indexid, date, datetype, dishiid, xianquid,extent);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="queryscenepointpolygondata.do")
	public String queryScenePointPolygonData(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String pointtype = request.getParameter("pointtype");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		String extentjson = request.getParameter("extentjson");
		String sceneid = request.getParameter("sceneid");
		JSONObject extent = JSONObject.fromObject(extentjson);
		result = pointRankManager.queryPointPolygonData(pointtype, indexid, date, datetype, dishiid, xianquid, extent, sceneid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="queryscenelist.do")
	public String querySceneList(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String scenelisttype = request.getParameter("scenelisttype");
		Object userIdObj = request.getSession().getAttribute("userId");
		String dateType = request.getParameter("dateType");
		String userId = "";
		if(userIdObj == null){
			return "";
		}else{
			userId = userIdObj.toString();
		}
		result = pointRankManager.queryForSceneListData(dateType, userId, scenelisttype);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querypointnearbtsdata.do")
	public String queryPointNearBTSData(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String pointid = request.getParameter("pointid");
		result = pointRankManager.queryPointNearBTSData(date, pointid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querygridpolygondata.do")
	public String queryGridPolygonData(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		String extentjson = request.getParameter("extentjson");
		JSONObject extent = JSONObject.fromObject(extentjson);
		result = pointRankManager.queryGridPolygonData(indexid, date, datetype, dishiid, xianquid,extent);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="querygridindex.do")
	public String queryGridIndex(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String gridid = request.getParameter("gridid");
		result = pointRankManager.queryGridIndex(date, datetype, gridid);
		return result.toString();
	}
	
	
	@RequestMapping(value="downloadcurmapindex.do")
	public String download(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String fileName="";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String pointtype = request.getParameter("pointtype");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		String regionname = "";
		if("all".equals(xianquid.toLowerCase())){
			JSONObject regionjson = regionManager.QueryOneRegionData(dishiid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
			}
		}else{
			JSONObject regionjson = regionManager.QueryOneRegionData(xianquid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
			}
		}
		List<Map<String, Object>> list = pointRankManager.getMapPointIndexData(pointtype, indexid, date, datetype, dishiid, xianquid);
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
		String [] key = {"STATIS_TIME","CELL_NAME","MM_VAL","RING_RATE","SAME_RATE"};
		String [] columnNames = {"统计时间","兴趣点名称",mmcolumnName,ringcolumnName,samecolumnName};
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
	
	@RequestMapping(value="downloadcursceneindex.do")
	public String downloadscene(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String fileName="";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String indexid = request.getParameter("indexid");
		String pointtype = request.getParameter("pointtype");
		String dishiid = request.getParameter("dishiid");
		String xianquid = request.getParameter("xianquid");
		String sceneid = request.getParameter("sceneid");
		String scenename = URLDecoder.decode(request.getParameter("scenename"), "UTF-8");
		String regionname = "";
		if("all".equals(xianquid.toLowerCase())){
			JSONObject regionjson = regionManager.QueryOneRegionData(dishiid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
			}
		}else{
			JSONObject regionjson = regionManager.QueryOneRegionData(xianquid);
			if(regionjson!=null){
				regionname = regionjson.getString("regionName");
			}
		}
		List<Map<String, Object>> list = pointRankManager.getScenePointData(pointtype, indexid, date, datetype, dishiid, xianquid, sceneid);
		String datetime = "--";
		String mmcolumnName="--";
		String ringcolumnName="--";
		String samecolumnName="--";
		if(list.size()!=0){
			if(list.get(0).get("MM_NAME")!=null && list.get(0).get("MM_UNIT")!=null){
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
		fileName = datetime+regionname+scenename+"统计数据";
		String [] key = {"STATIS_TIME","CELL_NAME","MM_VAL","RING_RATE","SAME_RATE"};
		String [] columnNames = {"统计时间","兴趣点名称",mmcolumnName,ringcolumnName,samecolumnName};
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

	public JSONArray getXinqujson() {
		return xinqujson;
	}

	public void setXinqujson(JSONArray xinqujson) {
		this.xinqujson = xinqujson;
	}

	public JSONObject getAllxianqujson() {
		return allxianqujson;
	}

	public void setAllxianqujson(JSONObject allxianqujson) {
		this.allxianqujson = allxianqujson;
	}

	public InterestPointRankManager getPointRankManager() {
		return pointRankManager;
	}

	public void setPointRankManager(InterestPointRankManager pointRankManager) {
		this.pointRankManager = pointRankManager;
	}

	public String getMaxDateD() {
		return maxDateD;
	}

	public void setMaxDateD(String maxDateD) {
		this.maxDateD = maxDateD;
	}

	public String getMinDateD() {
		return minDateD;
	}

	public void setMinDateD(String minDateD) {
		this.minDateD = minDateD;
	}

	public String getMaxDateM() {
		return maxDateM;
	}

	public void setMaxDateM(String maxDateM) {
		this.maxDateM = maxDateM;
	}

	public String getMinDateM() {
		return minDateM;
	}

	public void setMinDateM(String minDateM) {
		this.minDateM = minDateM;
	}
}
