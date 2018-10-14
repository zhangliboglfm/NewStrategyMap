package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.RegionManager;

/**
 * <p>Title: RegionController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2018-3-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class RegionController {
	
	@Resource
	private RegionManager regionManager;
	
	@RequestMapping(value="regionquery.do")
	public String RegionQuery(HttpServletRequest request,HttpServletResponse response) {
		return "regionplug";
	}
	
	@ResponseBody
	@RequestMapping(value="regionObtain.do")
	public String RegionObtain(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String regionId = request.getParameter("regionId");
		if(regionId!=null && !regionId.equals("")){
			JSONArray array = regionManager.getCounty(regionId);
			result.put("county", array);
		}else{
			JSONArray array = regionManager.getCity();
			result.put("city", array);
		}
		return result.toString();
	}

	public RegionManager getRegionManager() {
		return regionManager;
	}

	public void setRegionManager(RegionManager regionManager) {
		this.regionManager = regionManager;
	}
}
