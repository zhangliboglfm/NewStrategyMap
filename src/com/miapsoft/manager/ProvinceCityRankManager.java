package com.miapsoft.manager;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: ProvinceCityRankManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-17
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface ProvinceCityRankManager {
	
	
	/**
	 * 查询表中最大日期
	 * @Title:getMaxDate
	 * @Description:TODO
	 * @return
	 * @author:张立保
	 * @date:2017-6-26
	 */
	public String  getMaxDate(String dateType);
	
	
	/**
	 * 通过输入关键字查询指标名称
	 * @Title:queryForIndexByIndexKey
	 * @Description:TODO
	 * @param datetype 日期类型
	 * @param keystr 搜索关键字
	 * @return
	 * @author:张立保
	 * @date:2017-6-17
	 */
	public JSONArray queryForIndexByIndexKey(String datetype,String keystr,String MM_LEVEL);
	
	/**
	 * 查询左侧指标
	 * @Title:initLeftQueryIndex
	 * @Description:TODO
	 * @param regionId
	 * @param date
	 * @param MM_LEVEL
	 * @param datetype
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	public JSONArray initLeftQueryIndex(String regionId,String date,String MM_LEVEL,String datetype);
	
	
	/**
	 * 查询该地域兴趣点的数量
	 * @Title:initInterstingPointNumber
	 * @Description:TODO
	 * @param dishiRegionId
	 * @param xianquRegionId
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	public JSONArray initInterstingPointNumber(String dishiRegionId,String xianquRegionId);
	
	/**
	 * 查询相关指标
	 * @Title:queryRelevantIndex
	 * @Description:TODO
	 * @param datetype
	 * @param date
	 * @param parent_mm_id
	 * @param regionId2
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	public JSONArray queryRelevantIndex(String datetype,String date,String indexid,String regionid,String MM_LEVEL);
	
	/**
	 * 指标切换查询以及地图渲染
	 * @Title:querySwitchIndex
	 * @Description:TODO
	 * @param MM_ID
	 * @param date
	 * @param dishiRegionId
	 * @param xianquRegionId
	 * @return
	 * @author:张立保
	 * @date:2017-6-19
	 */
	public JSONObject querySwitchIndex(String MM_ID,String date,String dishiRegionId , String xianquRegionId,String dateType);
	
	/**
	 * 下载该地域的数据
	 * @Title:downloadIndexdata
	 * @Description:TODO
	 * @param indexid
	 * @param date
	 * @param datetype
	 * @param dishiid
	 * @param xianquid
	 * @return
	 * @author:张立保
	 * @date:2017-6-27
	 */
	public List<Map<String , Object>> downloadIndexdata(String indexid, String date,String datetype, String dishiid,String xianquid,String MM_LEVEL,JSONArray allxinaqudishijson);
}
