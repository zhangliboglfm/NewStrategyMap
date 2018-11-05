package com.miapsoft.manager;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: Situation4gManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface Situation4gManager {
	/**
	 * 获取表格中数据
	 * @Title:getdatafortable
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-26
	 */
	public JSONObject getdatafortable(String date,String regionId);
	
	/**
	 * 获取echarts表格中数据
	 * @Title:getechartsdata
	 * @Description:TODO
	 * @param id
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-26
	 */
	public JSONObject getechartsdata(String id,String date,String regionId);
	
	/**
	 * 获取echarts表格中数据
	 * @Title:getPackHistory
	 * @Description:TODO
	 * @param id
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-26
	 */
	public JSONObject getPackHistory(String packid,String date,String regionId);
	
	/**
	 * @Title:queryFeeAreaChartData
	 * @Description: 获取转入转出套餐用户消费分布数据
	 * @param date 日期
	 * @param ZCPackId 转出套餐Id
	 * @param ZRPackId 转入套餐Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-27
	 */
	public JSONObject queryFeeAreaChartData(String date,String ZCPackId,String ZRPackId);
	
	/**
	 * @Title:queryFluxAreaChartData
	 * @Description: 获取转入转出套餐用户流量分布数据
	 * @param date 日期
	 * @param ZCPackId 转出套餐Id
	 * @param ZRPackId 转入套餐Id
	 * @return
	 * @author:白少华
	 * @date:2017-7-27
	 */
	public JSONObject queryFluxAreaChartData(String date,String ZCPackId,String ZRPackId);
	
	/**
	 * 象限图历史数据
	 * @Title:getRadardata
	 * @Description:TODO
	 * @param zcid
	 * @param zrid
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-8-1
	 */
	public JSONArray getRadardata(String zrid,String date,String regionId);
	
	
	/**
	 * 气泡图详细数据
	 * @Title:initDescrScatter
	 * @Description:TODO
	 * @param date
	 * @param zrid
	 * @param zcid
	 * @param regionid
	 * @return
	 * @author:张立保
	 * @date:2017-10-17
	 */
	public JSONArray initDescrScatter(String date,String zrid,String zcid,String regionid);
	
	/**
	 * 查询历史数据
	 * @Title:gethistorydata
	 * @Description:TODO
	 * @param zrid
	 * @return
	 * @author:张立保
	 * @date:2017-8-3
	 */
	public JSONArray gethistorydata(String zrid,String regionId,String date);
	
	/**
	 * 
	 * @param date
	 * @param regionId
	 * @return
	 */
	public JSONArray downloadPackSJPDesc(String date, String regionId);
	
}
