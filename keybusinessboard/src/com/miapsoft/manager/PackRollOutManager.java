package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: PackRollOutManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface PackRollOutManager {
	
	
	/***
	 * 查询导入导出页面数
	 * @Title:searchdata
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-3
	 */
	public JSONObject searchMoneyandUser(String date);
	
	
	/**
	 * 查询表格中数据
	 * @Title:searchTabelshuju
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-4
	 */
	public JSONArray searchTabelshuju(String date);
	
	/**
	 * 查询部分金额数据
	 * @Title:searchPartMoneyAndUser
	 * @Description:TODO
	 * @param date
	 * @param Numbers
	 * @return
	 * @author:张立保
	 * @date:2017-7-11
	 */
	public JSONObject searchPartMoneyAndUser(String date,JSONArray Numbers);
}
