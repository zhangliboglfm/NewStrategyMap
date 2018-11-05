package com.miapsoft.manager;

import net.sf.json.JSONArray;
/**
 * 测试接口类
 * <p>Title: TestManager.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public interface TestManager {
	public JSONArray getRegions();
	public int excuteSql(String sql);
	public int[] excuteSql(String[] sql);
}
