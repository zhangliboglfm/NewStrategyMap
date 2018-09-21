package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SceneCustomizationIndexManager {
	/**
	 * @Title:queryForIndexInfoById
	 * @Description:根据指标ID获取指标信息
	 * @param indexid 指标id
	 * @param datetype 日期类型
	 * @param indexlvl 指标级别
	 * @return
	 * @author:白少华
	 * @date:2017-6-23
	 */
	public JSONObject queryForIndexInfoById(String indexid,String datetype,String indexlvl);
	
	/**
	 * @Title:queryForIndex
	 * @Description:查找指标
	 * @param keystr 关键字
	 * @param querytype 查询类型
	 * @return
	 * @author:白少华
	 * @date:2017-6-15
	 */
	public JSONArray queryForIndex(String keystr,String querytype);
	
	/**
	 * @Title:queryForIndex
	 * @Description:查找指标
	 * @param keystr 关键字
	 * @param querytype 查询类型
	 * @param datetype 日期类型
	 * @return
	 * @author:白少华
	 * @date:2017-6-23
	 */
	public JSONArray queryForIndex(String keystr,String querytype,String datetype);

}
