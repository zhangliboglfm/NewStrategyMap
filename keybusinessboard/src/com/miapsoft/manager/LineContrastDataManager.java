package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: KeyBusinessDataManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface LineContrastDataManager {

	/**
	 * @Title:getLineKeyBusinessData
	 * @Description:获取线上下对比数据
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONArray getLineKeyBusinessData(String date);
	
	
	/**
	 * @Title:getBusinessHandelData
	 * @Description:获取业务办理数据
	 * @param type
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONObject getBusinessHandelData(String date);
	
	/**
	 * @Title:getKuanDaiData
	 * @Description:获取宽带数据
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONArray getKuanDaiData(String date,String bustype);
	
	/**
	 * @Title:getFluxData
	 * @Description:获取流量包数据
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONArray getFluxData(String date,String bustype);
	
	/**
	 * @Title:getPackBusinessHandelData
	 * @Description:获取套餐办理数据
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONObject getPackBusinessHandelData(String date);
	
	/**
	 * @Title:getOtherBusinessHandelData
	 * @Description:获取其他业务办理数据
	 * @return
	 * @author:白少华
	 * @date:2017-7-3
	 */
	public JSONObject getOtherBusinessHandelData(String date,String bustype);
	
}
