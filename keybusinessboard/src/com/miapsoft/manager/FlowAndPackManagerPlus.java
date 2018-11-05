package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: FlowAndPackManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface FlowAndPackManagerPlus {
	/**
	 * 获取topten套餐
	 * @Title:gattableTopten
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-27
	 */
	public JSONArray gettableTopten(String date,String regionId);
	
	/**
	 * 获取底部d3作图数据
	 * @Title:initd3underLine
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-27
	 */
	public JSONObject initd3underLine(String date,String regionId ,String yuzhi);
	
	
	/**
	 * 查询主推套餐数据
	 * @Title:initMainPushPack
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-27
	 */
	public JSONObject initMainPushPack(String date,String regionId);
}
