package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: IndexPoolManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-15
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface IndexPoolManager {

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
