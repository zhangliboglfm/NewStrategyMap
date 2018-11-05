package com.miapsoft.manager;

import net.sf.json.JSONArray;

/**
 * <p>Title: MainManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface MainManager {
	
	/**
	 * 获取表中的最大时间
	 * @Title:getMaxDate
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-7-3
	 */
	public String getMaxDate();
	
	/**
	 * 获取表中的最小时间
	 * @Title:getMinDate
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-7-3
	 */
	public String getMinDate();
	
	/**
	 * 1级工号获取所有地市id数据
	 * @Title:QueryForDishiData
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-8-7
	 */
	public JSONArray QueryForDishiData();
	
	/**
	 * 二级工号只查询所属地市Id
	 * @Title:QueryForOneDiShi
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-8-9
	 */
	
	
	public JSONArray QueryForOneDiShi(String RegionId);
}
