package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: RegionManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-13
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface RegionManager {
	/**
	 * @Title:QueryForDishiData
	 * @Description:TODO
	 * @return
	 * @author:白少华
	 * @date:2017-6-13
	 */
	public JSONArray QueryForDishiData();
	
	
	/**
	 * @Title:QueryForXianquData
	 * @Description:TODO
	 * @param dishiid 地市ID的json
	 * @return
	 * @author:白少华
	 * @date:2017-6-13
	 */
	public JSONObject QueryForXianquData(JSONArray dishiid);
	
	/**
	 * @Title:QueryForXianquData
	 * @Description:TODO
	 * @param DishiId 地市ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-13
	 */
	public JSONArray QueryForXianquData(String DishiId);
	
	
	/**
	 * @Title:QueryForOneXianquData
	 * @Description:查询单个县区
	 * @param xianquid 县区ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public JSONArray QueryForOneXianquData(String xianquid);
	
	/**
	 * lvlId为2查询地市数据
	 * @Title:QueryForDishiData2
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	public JSONArray QueryForDishiData2(String userRegionId);
	
	
	/**
	 * lvlId为2查询县区数据
	 * @Title:QueryForXianquData2
	 * @Description:TODO
	 * @param dishiid
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	public JSONObject QueryForXianquData2(JSONArray dishiid);
	
	/**
	 * lvlId为3时查询地市数据
	 * @Title:QueryForDishiData3
	 * @Description:TODO
	 * @param userRegionId
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	public JSONArray QueryForDishiData3(String userRegionId);
	
	/**
	 * lvlId为3时查询县区数据
	 * @Title:QueryForXianquData
	 * @Description:TODO
	 * @param dishiid
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	public JSONObject QueryForXianquData3(JSONArray dishiid,String regionId);
	
	/**
	 * @Title:QueryOneRegionData
	 * @Description:查询一个地域信息
	 * @param regionid
	 * @return
	 * @author:白少华
	 * @date:2017-6-27
	 */
	public JSONObject QueryOneRegionData(String regionid);
	
	/**
	 * 根据地市Id查询该地市下所有县区信息
	 * @Title:QueryxianquIdbydishiId
	 * @Description:TODO
	 * @param dishiId
	 * @return
	 * @author:张立保
	 * @date:2017-6-27
	 */
	public JSONArray QueryxianquIdbydishiId(String dishiId);
}
