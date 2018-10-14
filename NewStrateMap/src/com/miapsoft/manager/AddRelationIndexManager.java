package com.miapsoft.manager;

import net.sf.json.JSONArray;

/**
 * <p>Title: AddRelationIndexManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface AddRelationIndexManager {
	
	/**
	 * @Title:queryForIndex
	 * @Description:查找指标
	 * @param keystr 关键字
	 * @param querytype 查询类型
	 * @param datetype 日期类型
	 * @param indexid 指标id
	 * @param indexlvl 指标级别
	 * @param userId 用户id
	 * @return
	 * @author:白少华
	 * @date:2017-6-23
	 */
	public JSONArray queryForIndex(String keystr,String querytype,String datetype,String indexid,String indexlvl,String userId);
	
	/**
	 * @Title:updateIndexRelationState
	 * @Description:修改关联指标关系
	 * @param thisindexid 带修改指标ID
	 * @param datetype 日期类型
	 * @param indexid 主指标ID
	 * @param userId 用ID
	 * @param updatetype 更新类型
	 * @return
	 * @author:白少华
	 * @date:2017-6-23
	 */
	public String updateIndexRelationState(String thisindexid,String datetype,String indexid,String userId,String updatetype) throws Exception;
}
