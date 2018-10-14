package com.miapsoft.manager;

import net.sf.json.JSONArray;

/**
 * <p>Title: RegionManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2018-3-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface RegionManager {
	public JSONArray getCity();
	public JSONArray getCounty(String parentregionid);
}
