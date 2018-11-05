package com.miapsoft.manager;

import net.sf.json.JSONArray;


public interface MainManager {
	/**
	 * 根据权限信息后去头部导航模块	
	 * @param userId 
	 * @return
	 */
	public JSONArray getTopNav(String userId);
	
	
	/**
	 * 获取左侧导航数据
	 * @return
	 */
	public JSONArray getLeftNav(String moudle_id,String userId);
	
	
}
