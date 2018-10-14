package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: PointDetailAnalysisManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-21
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface PointDetailAnalysisManager {
	
	/**
	 * 查询兴趣点性别和年龄分布
	 * @Title:queryPointDetailAnalysis
	 * @Description:TODO
	 * @param pointId
	 * @param pointType
	 * @param dateType
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-6-21
	 */
	public JSONObject queryPointDetailAnalysis(String pointId,String dateType,String date);
	
	
	/**
	 * 查询人流变化趋势
	 * @Title:queryPeopleFlowTrend
	 * @Description:TODO
	 * @param pointId
	 * @param pointType
	 * @param dateType
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-6-21
	 */
	public JSONObject queryPeopleFlowTrend(String pointId,String pointType,String dateType,String date);

}
