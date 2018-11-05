package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: GeneralSituationManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface GeneralSituationManagerPlus {

	/**
	 * @Title:getDataDayTime
	 * @Description:获取数据周期
	 * @param tabelname
	 * @param columnstr
	 * @return
	 * @throws Exception
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject getDataDayTime(String tabelname,String columnstr) throws Exception;
	/**
	 * @Title:queryBusCntData
	 * @Description: 查询 总办理量,办理用户,重点业务占比
	 * @param date 日期
	 * @param regionId 地域Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject queryGeneralData(String date,String regionId);
	
	
	
	/**
	 * 查询办理量变化趋势
	 * @param regionId
	 * @return
	 */
	public JSONArray getAllHandlingNum(String regionId,String maxdate);
	
	
	/**
	 * @Title:query4GBusinessData
	 * @Description: 查询 4G数据
	 * @param date 日期
	 * @param regionId 地域Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject query4GBusinessData(String date,String regionId);
	/**
	 * @Title:queryKDBusinessData
	 * @Description: 查询 宽带数据
	 * @param date 日期
	 * @param regionId 地域Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject queryKDBusinessData(String date,String regionId);
	/**
	 * @Title:queryFlowBusinessData
	 * @Description: 查询 流量数据
	 * @param date 日期
	 * @param regionId 地域Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject queryFlowBusinessData(String date,String regionId);
	/**
	 * @Title:queryOtherBusinessData
	 * @Description: 查询 其他数据
	 * @param date 日期
	 * @param regionId 地域Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-26
	 */
	public JSONObject queryOtherBusinessData(String date,String regionId);
	
}
