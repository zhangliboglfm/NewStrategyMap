package com.miapsoft.manager;

import net.sf.json.JSONArray;

/**
 * <p>Title: InterestingPointCompareManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface InterestingPointCompareManager {
	
	/**
	 * 查询相关影响指标
	 * @Title:queryRelIndex
	 * @Description:TODO
	 * @param date
	 * @param datetype
	 * @param regionid
	 * @param indexid
	 * @param userId
	 * @return
	 * @author:张立保
	 * @date:2017-6-23
	 */
	public JSONArray queryRelIndex(String date,String datetype,String [] regionId,String indexid,String userId);
	
	public String getMaxDate(String dateType);

}
