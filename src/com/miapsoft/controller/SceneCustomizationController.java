package com.miapsoft.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.RegionManager;
import com.miapsoft.manager.SceneCustomizationManager;
import com.miapsoft.manager.sys.SystemManager;
import com.miapsoft.model.UserInfo;


/**
 * <p>Title: IndexPoolController.java</p>
 * <p>Description: TODO</p>
 * @author: 周雷
 * @time: 2017-6-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class SceneCustomizationController {
	
	@Resource
	private RegionManager regionManager;
	@Resource
	private SystemManager systemManager;
	@Resource
	private SceneCustomizationManager sceneCustomizationManager;
	
	private JSONArray dishijson;
	private JSONArray xinqujson;
	private JSONObject allxianqujson;
	
	@RequestMapping("scenecustomization")
	public String  scenecustomization(HttpServletRequest request,HttpServletResponse response){
		UserInfo userInfo = null;
		String userId="";
		String senceid=request.getParameter("sceneid");
		String creatoredit=request.getParameter("type");
		String editortype=request.getParameter("scenetype");
		try {
		    userId=request.getParameter("userId");
		    if(userId==null){
			   HttpSession session = request.getSession();	
			   userId=(String) session.getAttribute("userId");
		    }
			userInfo = systemManager.getUserInfo(userId);
			/*userInfo = (UserInfo)request.getSession().getAttribute("userInfo");*/
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
		String mm_leve = request.getParameter("mm_leve");
		request.setAttribute("userId", userId);
		request.setAttribute("creatoredit", creatoredit);
		request.setAttribute("senceid", senceid);
		request.setAttribute("editortype", editortype);
		request.setAttribute("userRegionlvl", userRegionlvl);
		request.setAttribute("dishijson", dishijson);
		request.setAttribute("xinqujson", xinqujson);
		request.setAttribute("allxinqujson", allxianqujson);
		return "jsp/scenecustomization";
	}
	//兴趣点类型选择
	@ResponseBody
	@RequestMapping("interestindex")
	public String queryIndexByIndexKey(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONArray result = new JSONArray();
		result = sceneCustomizationManager.interestindex();
		return result.toString();
	}
	//兴趣点条件的筛选的地图数据
	@ResponseBody
	@RequestMapping("mapdatajson")
	public String mapdatajson(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONArray result = new JSONArray();
		String dishiregionId=request.getParameter("dishiregionId");
		String xianquregionId=request.getParameter("xianquregionId");
		String dateType=request.getParameter("dateType");
		String interestnumber1=request.getParameter("interestnumber1");
		String interestnumber2=request.getParameter("interestnumber2");
		String interestarry=request.getParameter("interestarry");
		String conditionid=request.getParameter("conditionid");
		String extentjson = request.getParameter("extentjson");
		JSONObject extent = JSONObject.fromObject(extentjson);
		JSONArray interestarrylist=JSONArray.fromObject(interestarry);
		JSONArray conditionidlist=JSONArray.fromObject(conditionid);
		result = sceneCustomizationManager.mapdatajson(dishiregionId,xianquregionId,dateType,interestnumber1,interestnumber2,interestarrylist,conditionidlist,extent);
		return result.toString();
	}
	//兴趣点条件的筛选的保存数据
	@ResponseBody
	@RequestMapping("savedata")
	public String  savedata(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String userId=request.getParameter("userId");
		String phone=request.getParameter("phone");
		String scencname=request.getParameter("scencname");
		String scencdesc=request.getParameter("scencdesc");
		String dataType=request.getParameter("dataType");
		String celltype=request.getParameter("celltype");
		String cellattru=request.getParameter("cellattru");
		String interestnumber1=request.getParameter("interestnumber1");
		String interestnumber2=request.getParameter("interestnumber2");
		JSONArray celltypelist=JSONArray.fromObject(celltype);
		JSONArray cellattrulist=JSONArray.fromObject(cellattru);
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime=df.format(date);
		int result = sceneCustomizationManager.savedata(userId,phone,scencname,scencdesc,dataType,celltypelist,cellattrulist,datetime,interestnumber1,interestnumber2);
		String intstr = String.valueOf(result);
		if(intstr.equals("2")){
			request.setAttribute("sceneid", null);
		}
		return intstr.toString();
	}
	
	//兴趣点的地图数据
	@ResponseBody
	@RequestMapping("pointmapdata")
	public String  pointexchage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONArray result = new JSONArray();
		String dishiregionId=request.getParameter("dishiregionId");
		String xianquregionId=request.getParameter("xianquregionId");
		String pointertype=request.getParameter("pointertype");
		String extentjson = request.getParameter("extentjson");
		JSONObject extent = JSONObject.fromObject(extentjson);
	    result = sceneCustomizationManager.pointmapdata(dishiregionId,xianquregionId,pointertype,extent);
		return result.toString();
	}
	//兴趣点数据的保存
	@ResponseBody
	@RequestMapping("savepointdata")
	public String  savepointdata(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String useId=request.getParameter("useId");
		String pointsencename=request.getParameter("pointsencename");
		String pointphone=request.getParameter("pointphone");
		String pointscencdesc = request.getParameter("pointscencdesc");
		String pointertype = request.getParameter("pointertype");
		String pointerarry = request.getParameter("pointerarry");
		JSONArray pointerarrylist = JSONArray.fromObject(pointerarry);
		int result = sceneCustomizationManager.pointmapdata(useId,pointsencename,pointphone,pointscencdesc,pointertype,pointerarrylist);
		String intstr = String.valueOf(result);
		if(intstr.equals("2")){
			request.setAttribute("sceneid", null);
		}
		return intstr.toString();
	}
	//编辑的数据的查询
	@ResponseBody
	@RequestMapping("editdatasearch")
	public String  editdatasearch(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONArray result=new JSONArray();
		String senceid=request.getParameter("senceid");
		String eidttype=request.getParameter("editortype");
		result = sceneCustomizationManager.editdatasearch(senceid,eidttype);
		return result.toString();
	}
	//编辑的数据保存之前先删除之前的数据
	@ResponseBody
	@RequestMapping("editpointdelete")
	public String  editpointdelete(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String senceid=request.getParameter("senceid");
		int result = sceneCustomizationManager.editpointdelete(senceid);
		String intr=String.valueOf(result);
		return intr.toString();
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

}
