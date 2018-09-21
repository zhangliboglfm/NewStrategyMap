package com.miapsoft.manager;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: InterestPointRankManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface InterestPointRankManager {
	
	/**
	 * @Title:querySystemMAXDate
	 * @Description:获取数据日周期
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public JSONObject querySystemMAXDate();
	
	/**
	 * @Title:querySystemMAXMonth
	 * @Description:获取数据月周期
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public JSONObject querySystemMAXMonth();
	
	/**
	 * @Title:queryMapIndex
	 * @Description:TODO
	 * @param indexid 指标Id
	 * @param date 日期
	 * @param datetype 日期类型
	 * @param regionid 地域Id
	 * @param mapmodel 地图级别
	 * @return
	 * @author:白少华
	 * @date:2017-6-19
	 */
	public JSONObject queryMapIndex(String indexid,String date,String datetype,String regionid,String mapmodel);
	
	/**
	 * @Title:queryPointTypeData
	 * @Description:获取兴趣点类型数据
	 * @param dishiregionId
	 * @param xianquregionId
	 * @return
	 * @author:白少华
	 * @date:2017-6-16
	 */
	public JSONArray queryPointTypeData(String dishiregionId,String xianquregionId);
	
	/**
	 * @Title:queryPointTypeData
	 * @Description:获取兴趣点类型数据
	 * @param dishiregionId
	 * @param xianquregionId
	 * @param sceneId
	 * @return
	 * @author:白少华
	 * @date:2017-6-22
	 */
	public JSONArray queryPointTypeData(String dishiregionId,String xianquregionId,String sceneId);
	
	/**
	 * @Title:queryForIndexByIndexKey
	 * @Description:查询指标池指标
	 * @param datetype 日期类型
	 * @param keystr 指标关键字
	 * @return
	 * @author:白少华
	 * @date:2017-6-14
	 */
	public JSONArray queryForIndexByIndexKey(String datetype,String keystr);
	
	/**
	 * @Title:queryForPointByKeys
	 * @Description:搜索兴趣点
	 * @param pointtype 兴趣点类型
	 * @param keystr 兴趣点关键字
	 * @param dishiid 地市ID
	 * @param xianquid 县区ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-19
	 */
	public JSONArray queryForPointByKeys(String pointtype,String keystr,String dishiid,String xianquid);
	
	/**
	 * @Title:queryRegionIndex
	 * @Description:查询地域或兴趣点指标信息
	 * @param date 日期
	 * @param datetype 日期类型
	 * @param regionid 地域id
	 * @return
	 * @author:白少华
	 * @date:2017-6-19
	 */
	public JSONArray queryRegionIndex(String date,String datetype,String regionid);
	
	/**
	 * @Title:queryGridIndex
	 * @Description:查询网格指标数据
	 * @param date
	 * @param datetype
	 * @param gridid
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public JSONArray queryGridIndex(String date,String datetype,String gridid);
	
	/**
	 * @Title:queryRelIndex
	 * @Description: 查询相关指标
	 * @param date 日期
	 * @param datetype 日期类型
	 * @param regionid 地域ID
	 * @param indexid 指标ID
	 * @param userId 用户ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-20
	 */
	public JSONArray queryRelIndex(String date,String datetype,String regionid,String indexid,String userId,String mapmodel);
	
	/**
	 * @Title:queryPointBaseInfo
	 * @Description:查询小区基础信息
	 * @param pointtype 兴趣点类型
	 * @param pointid 兴趣点ID
	 * @param date 日期
	 * @param datetype 日期类型
	 * @return
	 * @author:白少华
	 * @date:2017-6-20
	 */
	public JSONObject queryPointBaseInfo(String pointtype,String pointid,String date,String datetype);
	
	/**
	  * @Title:queryPointPolygonData
	 * @Description:获取兴趣点轮廓
	 * @param pointtype 兴趣点类型
	 * @param indexid 指标ID
	 * @param date 日期
	 * @param datetype 日期类型
	 * @param dishiid 地市ID
	 * @param xianquid 县区ID
	 * @param extent 可视范围
	 * @return
	 * @author:白少华
	 * @date:2017-6-21
	 */
	public JSONArray queryPointPolygonData(String pointtype,String indexid,String date,String datetype,String dishiid,String xianquid,JSONObject extent);
	
	/**
	 * @Title:queryPointPolygonData
	 * @Description:TODO
	 * @param pointtype
	 * @param indexid
	 * @param date
	 * @param datetype
	 * @param dishiid
	 * @param xianquid
	 * @param extent
	 * @param sceneid 场景ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-22
	 */
	public JSONArray queryPointPolygonData(String pointtype,String indexid,String date,String datetype,String dishiid,String xianquid,JSONObject extent,String sceneid);
	
	/**
	 * @Title:queryForSceneListData
	 * @Description:TODO
	 * @param datetype 日期类型
	 * @param userId 用户ID
	 * @param scenetype 场景类型
	 * @return
	 * @author:白少华
	 * @date:2017-6-22
	 */
	public JSONArray queryForSceneListData(String datetype,String userId,String scenetype);
	
	/**
	 * @Title:queryPointNearBTSData
	 * @Description:获取兴趣点附近基站数据
	 * @param date 日期
	 * @param pointid 兴趣点ID
	 * @return
	 * @author:白少华
	 * @date:2017-6-22
	 */
	public JSONArray queryPointNearBTSData(String date,String pointid);
	
	/**
	 * @Title:queryGridPolygonData
	 * @Description:获取地图网格轮廓数据
	 * @param indexid
	 * @param date
	 * @param datetype
	 * @param dishiid
	 * @param xianquid
	 * @param extent
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public JSONArray queryGridPolygonData(String indexid,String date,String datetype,String dishiid,String xianquid,JSONObject extent);
	
	/**
	 * @Title:qetMapPointIndexData
	 * @Description:获取下载地图兴趣点当前指标数据
	 * @param pointtype
	 * @param indexid
	 * @param date
	 * @param datetype
	 * @param dishiid
	 * @param xianquid
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	public List<Map<String, Object>> getMapPointIndexData(String pointtype,String indexid,String date,String datetype,String dishiid,String xianquid);
	
	/**
	 * @Title:getScenePointData
	 * @Description:获取下载场景兴趣点当前指标数据
	 * @param pointtype
	 * @param indexid
	 * @param date
	 * @param datetype
	 * @param dishiid
	 * @param xianquid
	 * @param sceneid
	 * @return
	 * @author:白少华
	 * @date:2017-6-27
	 */
	public List<Map<String, Object>> getScenePointData(String pointtype,String indexid,String date,String datetype,String dishiid,String xianquid,String sceneid);
}
