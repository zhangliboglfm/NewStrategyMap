package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.InterestPointRankManager;

/**
 * <p>Title: InterestPointRankManagerImpl.java</p>
 * <p>Description: 兴趣点数据接口实现类</p>
 * @author: 白少华
 * @time: 2017-6-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("pointRankManager")
public class InterestPointRankManagerImpl extends AbstractManager implements InterestPointRankManager {

	public JSONObject querySystemMAXDate() {
		JSONObject result = new JSONObject();
		String sql = "select MAX(STATIS_DATE) AS MAX_STATIS_DATE,MIN(STATIS_DATE) AS MIN_STATIS_DATE from BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE = 'D'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			result=JSONObject.fromObject(list.get(0));
		}
		return result;
	}

	public JSONObject querySystemMAXMonth() {
		JSONObject result = new JSONObject();
		String sql = "select MAX(STATIS_DATE) AS MAX_STATIS_DATE,MIN(STATIS_DATE) AS MIN_STATIS_DATE from BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE = 'M'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			result=JSONObject.fromObject(list.get(0));
		}
		return result;
	}
	
	public JSONArray queryForIndexByIndexKey(String datetype, String keystr) {
		JSONArray result = new JSONArray();
		String sql = "select MM_ID,MM_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO WHERE DATE_TYPE=? and ((MM_NAME LIKE ?) " +
				"or (MM_SMALL_TYPE_NAME like ?) or (MM_BIG_TYPE_NAME like ?)) AND MM_LEVEL='POINT'";
		StringBuffer sbf = new StringBuffer("%");
		sbf.append(keystr).append("%");
		String key = sbf.toString();
		Object [] value = {datetype,key,key,key};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject indexobj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				indexobj.put("indexId", map.get("MM_ID"));
				indexobj.put("indexName", map.get("MM_NAME"));
				result.add(indexobj);
			}
		}
		return result;
	}

	public JSONArray queryPointTypeData(String dishiregionId,String xianquregionId) {
		JSONArray result = new JSONArray();
		String sql = "";
		Object [] value = null;
		if("all".equals(xianquregionId.toLowerCase())){
			sql = "select CELL_TYPE,count(CELL_ID) as POINT_COUNT,ORDER_ID " +
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO where REGION_CODE=? group by CELL_TYPE,ORDER_ID order by ORDER_ID";
			Object [] temp = {dishiregionId};
			value = temp;
		}else{
			sql = "select CELL_TYPE,count(CELL_ID) as POINT_COUNT,ORDER_ID " +
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO where REGION_CODE=? and TOWN_ID=? group by CELL_TYPE,ORDER_ID order by ORDER_ID";
			Object [] temp = {dishiregionId,xianquregionId};
			value = temp;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("type", map.get("CELL_TYPE"));
				obj.put("count", map.get("POINT_COUNT"));
				result.add(obj);
			}
		}
		return result;
	}

	/*加载场景中兴趣点类型个数*/
	public JSONArray queryPointTypeData(String dishiregionId,String xianquregionId, String sceneId) {
		JSONArray result = new JSONArray();
		String sqlscene = "select a.*,b.CELL_TYPE from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
				"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID=b.SCENE_ID where a.SCENE_ID = '"+sceneId+"'";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sqlscene);
		String scenetype = "";
		if(listdata.size()!=0){
			scenetype = listdata.get(0).get("SCENE_TYPE").toString();
		}
		if("filter".equals(scenetype.toLowerCase())){
			String scenepointtype = getpointtypecolumn(listdata.get(0).get("CELL_TYPE").toString());
			String sql1 = "";
			if("all".equals(xianquregionId.toLowerCase())){
				sql1 = "select CELL_TYPE ,count(1) as POINT_COUNT from "+
						"(SELECT  C.CELL_TYPE ,REGION_ID FROM BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_D A LEFT JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO B  "+
						"ON A.MM_ID = B.MM_ID INNER JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO C "+
						"ON A.REGION_ID = C.CELL_ID AND C.CELL_TYPE IN ("+scenepointtype+") "+
						"WHERE  A.MM_VAL BETWEEN B.MM_MIN AND B.MM_MAX and b.SCENE_ID = '"+sceneId+"' and c.REGION_CODE = '"+dishiregionId+"' "+
						"GROUP BY C.CELL_TYPE ,REGION_ID "+
						"HAVING COUNT(REGION_ID) = MAX(B.MM_CNT) )a "+
						"group by CELL_TYPE";
			}else{
				sql1 = "select CELL_TYPE ,count(1) as POINT_COUNT from "+
						"(SELECT  C.CELL_TYPE ,REGION_ID FROM BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_D A LEFT JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO B  "+
						"ON A.MM_ID = B.MM_ID INNER JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO C "+
						"ON A.REGION_ID = C.CELL_ID AND C.CELL_TYPE IN ("+scenepointtype+") "+
						"WHERE  A.MM_VAL BETWEEN B.MM_MIN AND B.MM_MAX and b.SCENE_ID = '"+sceneId+"' and c.REGION_CODE = '"+dishiregionId+"' and c.town_id='"+xianquregionId+"' "+
						"GROUP BY C.CELL_TYPE ,REGION_ID "+
						"HAVING COUNT(REGION_ID) = MAX(B.MM_CNT) )a "+
						"group by CELL_TYPE";
			}
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
			if(list1.size()!=0){
				for (int i = 0; i < list1.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = list1.get(i);
					obj.put("type", map.get("CELL_TYPE"));
					obj.put("count", map.get("POINT_COUNT"));
					result.add(obj);
				}
			}
		}else if("check".equals(scenetype.toLowerCase())){
			String sql2="";
			if("all".equals(xianquregionId.toLowerCase())){
				sql2="SELECT A.CELL_TYPE,count(A.CELL_ID) as POINT_COUNT FROM BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO A "+
					"INNER JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO B ON A.CELL_ID = B.MM_ID AND b.SCENE_ID = '"+sceneId+"'  "+
					"WHERE REGION_CODE = '"+dishiregionId+"' GROUP BY A.CELL_TYPE";
			}else{
				sql2="SELECT A.CELL_TYPE,count(A.CELL_ID) as POINT_COUNT FROM BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO A "+
					"INNER JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO B ON A.CELL_ID = B.MM_ID AND b.SCENE_ID = '"+sceneId+"'  "+
					"WHERE REGION_CODE = '"+dishiregionId+"' and town_id='"+xianquregionId+"' GROUP BY A.CELL_TYPE";
			}
			List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2);
			if(list2.size()!=0){
				for (int i = 0; i < list2.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = list2.get(i);
					obj.put("type", map.get("CELL_TYPE"));
					obj.put("count", map.get("POINT_COUNT"));
					result.add(obj);
				}
			}
		}
		return result;
	}
	
	private static String getpointtypecolumn(String	ceiltype){
		String result = "";
		String [] jsonarr = ceiltype.split(",");
		JSONArray json = JSONArray.fromObject(jsonarr);
		for (int i = 0; i < json.size(); i++) {
			if(i!=json.size()-1){
				result+="'"+json.getString(i)+"',";
			}else{
				result+="'"+json.getString(i)+"'";
			}
		}
		return result;
	}
	
	public JSONArray queryRegionIndex(String date, String datetype,String regionid) {
		JSONArray result = new JSONArray();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String regiontype = "CITY";
		String sql_type = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO where REGION_ID='"+regionid+"'";
		List<Map<String, Object>> listtype = this.getJdbcTemplate().queryForList(sql_type);
		if(listtype.size()!=0){
			if("2".equals(listtype.get(0).get("LVL_ID").toString())){
				regiontype = "CITY";
			}else if("3".equals(listtype.get(0).get("LVL_ID").toString())){
				regiontype = "COUNTY";
			}
		}else{
			regiontype="POINT";
		}
		String sql = "select a.MM_ID,a.MM_NAME,b.MM_VAL,a.MM_UNIT,round(b.SAME_RATE,2) as SAME_RATE,round(b.RING_RATE,2) as RING_RATE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
					"left join BASS_DATA."+tabelname+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+datecolumn+" = ? "+
					"where a.MM_LEVEL = '"+regiontype+"' AND a.IS_HOME_SHOW != '0' AND a.DATE_TYPE = ? ORDER BY a.IS_HOME_SHOW";
		Object [] value = {regionid,date,datetype};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size() !=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexUnit", map.get("MM_UNIT"));
				if(map.get("MM_VAL")!=null){
					double d = Double.valueOf(map.get("MM_VAL").toString());
					obj.put("indexValue", NumberFormat.getInstance().format(d));
				}else{
					obj.put("indexValue", "--");
					
				}
				if(map.get("RING_RATE")!=null){
					double d = Double.valueOf(map.get("RING_RATE").toString());
					obj.put("indexRate", d);
					if(d==0||d==999){
						obj.put("indexRateType", "");
					}else{
						obj.put("indexRateType", (d>0)? "up" : "down");
					}
				}else{
					obj.put("indexRate", "--");
					obj.put("indexRateType", "--");
				}
				obj.put("regionId", regionid);
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray queryGridIndex(String date, String datetype, String gridid) {
		JSONArray result = new JSONArray();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String sql = "select a.MM_ID,a.MM_NAME,b.MM_VAL,a.MM_UNIT,round(b.SAME_RATE,2) as SAME_RATE,round(b.RING_RATE,2) as RING_RATE "+
				"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
				"left join BASS_DATA."+tabelname+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+datecolumn+" = ? "+
				"where a.MM_LEVEL = 'GRID' AND a.IS_HOME_SHOW != '0' AND a.DATE_TYPE = ? ORDER BY a.IS_HOME_SHOW";
		Object [] value = {gridid,date,datetype};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size() !=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexUnit", map.get("MM_UNIT"));
				if(map.get("MM_VAL")!=null){
					double d = Double.valueOf(map.get("MM_VAL").toString());
					obj.put("indexValue", NumberFormat.getInstance().format(d));
				}else{
					obj.put("indexValue", "--");
					
				}
				if(map.get("RING_RATE")!=null){
					double d = Double.valueOf(map.get("RING_RATE").toString());
					obj.put("indexRate", d);
					if(d==0||d==999){
						obj.put("indexRateType", "");
					}else{
						obj.put("indexRateType", (d>0)? "up" : "down");
					}
				}else{
					obj.put("indexRate", "--");
					obj.put("indexRateType", "--");
				}
				obj.put("gridId", gridid);
				result.add(obj);
			}
		}
		return result;
	} 
	
	public JSONObject queryMapIndex(String indexid, String date,String datetype, String regionid,String mapmodel) {
		JSONObject result = new JSONObject();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String sql = "select a.MM_ID,a.MM_NAME,b.MM_VAL,a.MM_UNIT,round(b.RING_RATE,2) as RING_RATE,a.LIGHT_COLOR_MIN,a.LIGHT_COLOR_MAX, "+
					"a.LIGHT_BLUE_MIN,a.LIGHT_BLUE_MAX,a.BLUE_COLOR_MIN,a.BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a  "+
					"left join BASS_DATA."+tabelname+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+datecolumn+" = ?  "+
					"where a.MM_LEVEL = 'CITY' AND a.DATE_TYPE =  ? and a.MM_ID = ?";
		Object [] value = {regionid,date,datetype,indexid};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size() !=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				result.put("id", map.get("MM_ID"));
				result.put("name", map.get("MM_NAME"));
				if(map.get("MM_VAL")!=null){
					double d = Double.valueOf(map.get("MM_VAL").toString());
					result.put("value", NumberFormat.getInstance().format(d));
				}else{
					result.put("value", "--");
					
				}
				if(map.get("RING_RATE")!=null){
					double d = Double.valueOf(map.get("RING_RATE").toString());
					BigDecimal bgd = BigDecimal.valueOf(d);
					bgd.setScale(2, RoundingMode.UP);
					result.put("rate", bgd);
					if(d==0||d==999){
						result.put("indexRateType", "");
					}else{
						result.put("indexRateType", (d>0)? "up" : "down");
					}
				}else{
					result.put("rate", "--");
					result.put("ratetype", "--");
				}
				result.put("unit", map.get("MM_UNIT"));
			}
		}
		String sql_tips = "select a.MM_UNIT,a.LIGHT_COLOR_MIN,a.LIGHT_COLOR_MAX, "+
				"a.LIGHT_BLUE_MIN,a.LIGHT_BLUE_MAX,a.BLUE_COLOR_MIN,a.BLUE_COLOR_MAX "+
				"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a  "+
				"left join BASS_DATA."+tabelname+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+datecolumn+" = ?  "+
				"where a.MM_LEVEL = '"+mapmodel+"' AND a.DATE_TYPE =  ? and a.MM_ID = ?";
		Object [] value_tips = {regionid,date,datetype,indexid};
		List<Map<String, Object>> listdata_tips = this.getJdbcTemplate().queryForList(sql_tips, value_tips);
		if(listdata_tips.size()!=0){
			for (int i = 0; i < listdata_tips.size(); i++) {
				Map<String, Object> map = listdata_tips.get(i);
				result.put("pointunit", map.get("MM_UNIT"));
				result.put("LMIN", map.get("LIGHT_COLOR_MIN"));
				result.put("LMAX", map.get("LIGHT_COLOR_MAX"));
				result.put("MMIN", map.get("LIGHT_BLUE_MIN"));
				result.put("MMAX", map.get("LIGHT_BLUE_MAX"));
				result.put("HMIN", map.get("BLUE_COLOR_MIN"));
				result.put("HMAX", map.get("BLUE_COLOR_MAX"));
			}
		}else{
			result.put("pointunit", "--");
			result.put("LMIN", "--");
			result.put("LMAX", "--");
			result.put("MMIN", "--");
			result.put("MMAX", "--");
			result.put("HMIN", "--");
			result.put("HMAX", "--");
		}
		return result;
	}

	public JSONArray queryForPointByKeys(String pointtype,String keystr,String dishiid,String xianquid) {
		JSONArray result = new JSONArray();
		String sql = "";
		Object [] value = null;
		StringBuffer bf = new StringBuffer("%");
		bf.append(keystr).append("%");
		String key = bf.toString();
		if("all".equals(xianquid.toLowerCase())){
			sql = "select CELL_ID,CELL_NAME,CELL_LONG,CELL_LAT from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO "+
					"WHERE CELL_TYPE = ? AND CELL_NAME like ? and REGION_CODE = ?";
			Object [] temp = {pointtype.toUpperCase(),key,dishiid};
			value = temp;
		}else{
			sql = "select CELL_ID,CELL_NAME,CELL_LONG,CELL_LAT from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO "+
					"WHERE CELL_TYPE = ? AND CELL_NAME like ? and REGION_CODE = ? and TOWN_ID = ?";
			Object [] temp = {pointtype.toUpperCase(),key,dishiid,xianquid};
			value = temp;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String,Object> map = listdata.get(i);
				obj.put("pointId", map.get("CELL_ID"));
				obj.put("pointName", map.get("CELL_NAME"));
				obj.put("pointLong", map.get("CELL_LONG"));
				obj.put("pointLat", map.get("CELL_LAT"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray queryRelIndex(String date, String datetype,String regionid, String indexid,String userId,String mapmodel) {
		JSONArray result = new JSONArray();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String regiontype = "CITY";
		String sql_type = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO where REGION_ID='"+regionid+"'";
		List<Map<String, Object>> listtype = this.getJdbcTemplate().queryForList(sql_type);
		if(listtype.size()!=0){
			if("2".equals(listtype.get(0).get("LVL_ID").toString())){
				regiontype = "CITY";
			}else if("3".equals(listtype.get(0).get("LVL_ID").toString())){
				regiontype = "COUNTY";
			}
		}else{
			if("GRID".equals(mapmodel)){
				regiontype = "GRID";
			}else{
				regiontype="POINT";
			}
		}
		String sql = "select A.MM_ID,C.MM_NAME,B.MM_VAL,C.MM_UNIT,round(B.RING_RATE,2) as RING_RATE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO A "+
					"LEFT JOIN BASS_DATA."+tabelname+" B ON A.MM_ID=B.MM_ID and B."+datecolumn+"=? AND B.REGION_ID=? "+
					"LEFT JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO C ON A.MM_ID=C.MM_ID AND C.DATE_TYPE = ? AND C.MM_LEVEL = '"+regiontype+"' "+
					"WHERE A.PARENT_MM_ID = ? AND A.DATE_TYPE = ? and (A.USER_ID = ? or A.USER_ID = 'SYSTEM')ORDER BY C.IS_HOME_SHOW";
		Object [] value = {date,regionid,datetype,indexid,datetype,userId};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexUnit", map.get("MM_UNIT"));
				if(map.get("MM_VAL")!=null){
					double d = Double.valueOf(map.get("MM_VAL").toString());
					obj.put("indexValue", NumberFormat.getInstance().format(d));
				}else{
					obj.put("indexValue", "--");
					
				}
				if(map.get("RING_RATE")!=null){
					double d = Double.valueOf(map.get("RING_RATE").toString());
					BigDecimal bgd = BigDecimal.valueOf(d);
					bgd.setScale(2, RoundingMode.UP);
					obj.put("indexRate", bgd);
					if(d==0||d==999){
						obj.put("indexRateType", "");
					}else{
						obj.put("indexRateType", (d>0)? "up" : "down");
					}
				}else{
					obj.put("indexRate", "--");
					obj.put("indexRateType", "--");
				}
				result.add(obj);
			}
		}
		return result;
	}

	public JSONObject queryPointBaseInfo(String pointtype, String pointid,String date, String datetype) {
		JSONObject result = new JSONObject();
		String tabelname = "";
		String datecolumn = "";
		String cntcolumn = "";
		String cnttext="";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		if("HOME".equals(pointtype) || "EDUCATION".equals(pointtype) || "GOVERNMENT".equals(pointtype) || "TRADING_ESTATE".equals(pointtype)){
			cntcolumn = "CZ_USER_CNT";
			cnttext="常驻人口";
		}else{
			cntcolumn = "USER_CNT";
			cnttext="人流量";
		}
		String sql = "select a.CELL_ID,a.CELL_NAME,b."+cntcolumn+" as CEIL_CNT,b.POINT_STAR,a.CELL_TYPE   from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a "+
					"left join BASS_DATA."+tabelname+" b on a.CELL_ID = b.REGION_ID and b."+datecolumn+" = ? and b.POINT_TYPE = ? "+
					"WHERE A.CELL_ID = ? AND a.CELL_TYPE = ?";
		Object [] vlaue = {date, pointtype,pointid,pointtype};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, vlaue);
		if(listdata.size() !=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				result.put("pointid", map.get("CELL_ID"));
				result.put("pointname", map.get("CELL_NAME"));
				if(map.get("CEIL_CNT") != null){
					int intcnt = Integer.valueOf(map.get("CEIL_CNT").toString()); 
					result.put("pointcnt", NumberFormat.getInstance().format(intcnt));
				}else{
					result.put("pointcnt", "--");
				}
				result.put("pointcnttext", cnttext);
				result.put("pointstar", map.get("POINT_STAR"));
				result.put("pointtype", map.get("CELL_TYPE").toString().toUpperCase());
			}
		}else{
			result.put("pointid", pointid);
			result.put("pointname", "--");
			result.put("pointcnt", "--");
			result.put("pointcnttext", "人流量");
			result.put("pointstar", 0);
			result.put("pointtype", pointtype);
		}
		return result;
	}

	public JSONArray queryPointPolygonData(String pointtype, String indexid,String date, String datetype, String dishiid, String xianquid,JSONObject extent) {
		JSONArray result = new JSONArray();
		JSONObject rule = new JSONObject();
		double XMIN = extent.getDouble("xMin");
		double XMAX = extent.getDouble("xMax");
		double YMIN = extent.getDouble("yMin");
		double YMAX = extent.getDouble("yMax");
		String sql = "select LIGHT_COLOR_MIN,LIGHT_COLOR_MAX,LIGHT_BLUE_MIN,LIGHT_BLUE_MAX,BLUE_COLOR_MIN,BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO  "+
					"where MM_ID=? and DATE_TYPE = ? and MM_LEVEL = 'POINT'";
		Object [] value ={indexid,datetype};
		List<Map<String, Object>> listrule = this.getJdbcTemplate().queryForList(sql, value);
		if(listrule.size()!=0){
			for (int i = 0; i < listrule.size(); i++) {
				Map<String, Object> map = listrule.get(i);
				rule.put("LMIN", (map.get("LIGHT_COLOR_MIN")!=null? map.get("LIGHT_COLOR_MIN") : 0 ));
				rule.put("LMAX", (map.get("LIGHT_COLOR_MAX")!=null? map.get("LIGHT_COLOR_MAX") : 0 ));
				rule.put("MMIN", (map.get("LIGHT_BLUE_MIN")!=null? map.get("LIGHT_BLUE_MIN") : 0 ));
				rule.put("MMAX", (map.get("LIGHT_BLUE_MAX")!=null? map.get("LIGHT_BLUE_MAX") : 0 ));
				rule.put("HMIN", (map.get("BLUE_COLOR_MIN")!=null? map.get("BLUE_COLOR_MIN") : 0 ));
				rule.put("HMAX", (map.get("BLUE_COLOR_MAX")!=null? map.get("BLUE_COLOR_MAX") : 0 ));
			}
		}
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String sql_point = "";
		Object [] valuepoint;
		if("all".equals(xianquid.toLowerCase())){
			sql_point = "select a.CELL_ID,a.CELL_NAME,a.CELL_TYPE,a.CELL_LONG,a.CELL_LAT,c.MM_ID,c.MM_NAME,d.MM_VAL,c.MM_UNIT,b.RINGS "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID = b.CELL_ID "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = ? and c.MM_ID = ?"+
					"left join BASS_DATA."+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = ? and c.MM_ID = d.MM_ID "+
					"WHERE a.REGION_CODE = ? and a.CELL_TYPE = ? and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT between "+YMIN+" and "+YMAX+"";
			Object [] temp = {datetype,indexid,date,dishiid,pointtype};
			valuepoint =temp;
		}else{
			sql_point = "select a.CELL_ID,a.CELL_NAME,a.CELL_TYPE,a.CELL_LONG,a.CELL_LAT,c.MM_ID,c.MM_NAME,d.MM_VAL,c.MM_UNIT,b.RINGS "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID = b.CELL_ID "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = ? and c.MM_ID = ?  "+
					"left join BASS_DATA."+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = ? and c.MM_ID = d.MM_ID  "+
					"WHERE a.REGION_CODE = ? and a.TOWN_ID = ? and a.CELL_TYPE = ? and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT between "+YMIN+" and "+YMAX+"";
			Object [] temp = {datetype,indexid,date,dishiid,xianquid,pointtype};
			valuepoint =temp;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql_point, valuepoint);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("pointId", map.get("CELL_ID"));
				obj.put("pointName", map.get("CELL_NAME"));
				obj.put("pointType", map.get("CELL_TYPE"));
				obj.put("pointRings", FormateRingsData(map.get("RINGS").toString()));
				obj.put("indexValue", (map.get("MM_VAL")==null? "--" : map.get("MM_VAL")));
				obj.put("pointColor", (map.get("MM_VAL")==null? getPointColorLVL(rule,0) : getPointColorLVL(rule,Double.valueOf(map.get("MM_VAL").toString()))));
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexUnit", map.get("MM_UNIT"));
				obj.put("pointLong", map.get("CELL_LONG"));
				obj.put("pointLat", map.get("CELL_LAT"));
				result.add(obj);
			}
		}
		return result;
	}
	/*场景兴趣点查询*/
	public JSONArray queryPointPolygonData(String pointtype, String indexid,String date, String datetype, String dishiid, String xianquid,JSONObject extent, String sceneid) {
		JSONArray result = new JSONArray();
		JSONObject rule = new JSONObject();
		String sql_rule = "select LIGHT_COLOR_MIN,LIGHT_COLOR_MAX,LIGHT_BLUE_MIN,LIGHT_BLUE_MAX,BLUE_COLOR_MIN,BLUE_COLOR_MAX "+
				"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO  "+
				"where MM_ID=? and DATE_TYPE = ? and MM_LEVEL = 'POINT'";
		Object [] value ={indexid,datetype};
		List<Map<String, Object>> listrule = this.getJdbcTemplate().queryForList(sql_rule, value);
		if(listrule.size()!=0){
			for (int i = 0; i < listrule.size(); i++) {
				Map<String, Object> map = listrule.get(i);
				rule.put("LMIN", (map.get("LIGHT_COLOR_MIN")!=null? map.get("LIGHT_COLOR_MIN") : 0 ));
				rule.put("LMAX", (map.get("LIGHT_COLOR_MAX")!=null? map.get("LIGHT_COLOR_MAX") : 0 ));
				rule.put("MMIN", (map.get("LIGHT_BLUE_MIN")!=null? map.get("LIGHT_BLUE_MIN") : 0 ));
				rule.put("MMAX", (map.get("LIGHT_BLUE_MAX")!=null? map.get("LIGHT_BLUE_MAX") : 0 ));
				rule.put("HMIN", (map.get("BLUE_COLOR_MIN")!=null? map.get("BLUE_COLOR_MIN") : 0 ));
				rule.put("HMAX", (map.get("BLUE_COLOR_MAX")!=null? map.get("BLUE_COLOR_MAX") : 0 ));
			}
		}
		double XMIN = extent.getDouble("xMin");
		double XMAX = extent.getDouble("xMax");
		double YMIN = extent.getDouble("yMin");
		double YMAX = extent.getDouble("yMax");
		String tabelname = "";
		String datecolumn = "";
		String regionsql = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		if(xianquid.toLowerCase().equals("all")){
			regionsql="b.REGION_CODE='"+dishiid+"'";
		}else{
			regionsql="b.REGION_CODE='"+dishiid+"' and b.TOWN_ID='"+xianquid+"'";
		}
		List<Map<String, Object>> listdata = new ArrayList<Map<String,Object>>();
		//查询场景类型
		String sqlscene = "select a.*,b.CELL_TYPE from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
				"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID=b.SCENE_ID where a.SCENE_ID = '"+sceneid+"'";
		List<Map<String, Object>> listscenetype = this.getJdbcTemplate().queryForList(sqlscene);
		String scenetype = "";
		if(listscenetype.size()!=0){
			scenetype = listscenetype.get(0).get("SCENE_TYPE").toString();
		}
		//场景类型不同需要作区分1.filter 2.check
		if("filter".equals(scenetype.toLowerCase())){
			String sql_scene = "select CELL_TYPE,MM_ID,MM_MIN,MM_MAX from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO WHERE SCENE_ID = '"+sceneid+"'";
			List<Map<String, Object>> listscene = this.getJdbcTemplate().queryForList(sql_scene);
			JSONArray indexjson = new JSONArray();
			if(listscene.size()!=0){
				for (int i = 0; i < listscene.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listscene.get(i);
					obj.put("indexid", map.get("MM_ID"));
					obj.put("indexmin", map.get("MM_MIN"));
					obj.put("indexmax", map.get("MM_MAX"));
					indexjson.add(obj);
				}
			}
			/*----------------------------------------------------*/
			String sql="select  a.REGION_ID from "+tabelname+" a  " +
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO b  on a.REGION_ID=b.CELL_ID " +
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO c on b.CELL_ID=c.CELL_ID " +
					"where  b.CELL_LONG between "+XMIN+" and "+XMAX+" and b.CELL_LAT  between "+YMIN+" and "+YMAX+" and  "+datecolumn+"='"+ date +"' "+
					"  and  "+regionsql+"  and b.CELL_TYPE='"+pointtype+"' "+
					allindex(indexjson)+" group by a.REGION_ID "+havehvaing(indexjson)+" "  ;
			listdata = this.getJdbcTemplate().queryForList(sql);
		}else if("check".equals(scenetype.toLowerCase())){
			String sql2 = "select MM_ID as REGION_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO WHERE SCENE_ID='"+sceneid+"'"; 
			listdata = this.getJdbcTemplate().queryForList(sql2);
		}
		if(listdata.size()!=0){
			String sql2="";
			if("all".equals(xianquid.toLowerCase())){
				sql2="select a.CELL_ID,a.CELL_NAME,a.CELL_TYPE,a.CELL_LONG,a.CELL_LAT,c.MM_ID,c.MM_NAME,d.MM_VAL,c.MM_UNIT,b.RINGS "+
						"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID = b.CELL_ID "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = '"+datetype+"' and c.MM_ID = '"+indexid+"'"+
						"left join "+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = '"+date+"' and c.MM_ID = d.MM_ID "+
						"WHERE a.REGION_CODE = '"+dishiid+"' and a.CELL_TYPE = '"+pointtype+"' and a.CELL_ID in ("+selectregion(listdata)+") "+
						"and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT between "+YMIN+" and "+YMAX+"";
			}else{
				sql2="select a.CELL_ID,a.CELL_NAME,a.CELL_TYPE,a.CELL_LONG,a.CELL_LAT,c.MM_ID,c.MM_NAME,d.MM_VAL,c.MM_UNIT,b.RINGS "+
						"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID = b.CELL_ID "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = '"+datetype+"' and c.MM_ID = '"+indexid+"'"+
						"left join "+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = '"+date+"' and c.MM_ID = d.MM_ID "+
						"WHERE a.REGION_CODE = '"+dishiid+"' and a.TOWN_ID = '"+xianquid+"' and a.CELL_TYPE = '"+pointtype+"' and a.CELL_ID in ("+selectregion(listdata)+")  "+
						"and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT between "+YMIN+" and "+YMAX+"";
			}
			List<Map<String,Object>> list1=this.getJdbcTemplate().queryForList(sql2);
			if(list1.size()>0){
				 for (int i=0;i<list1.size();i++){
					 JSONObject obj = new JSONObject();
					 Map<String,Object> map=list1.get(i);
					 obj.put("pointId", map.get("CELL_ID"));
					 obj.put("pointName", map.get("CELL_NAME"));
					 obj.put("pointType", map.get("CELL_TYPE"));
					 obj.put("pointRings", FormateRingsData(map.get("RINGS").toString()));
					 obj.put("indexValue", (map.get("MM_VAL")==null? "--" : map.get("MM_VAL")));
					 obj.put("pointColor", (map.get("MM_VAL")==null? getPointColorLVL(rule,0) : getPointColorLVL(rule,Double.valueOf(map.get("MM_VAL").toString()))));
					 obj.put("indexId", map.get("MM_ID"));
					 obj.put("indexName", map.get("MM_NAME"));
					 obj.put("indexUnit", map.get("MM_UNIT"));
					 obj.put("pointLong", map.get("CELL_LONG"));
					 obj.put("pointLat", map.get("CELL_LAT"));
					 result.add(obj);
				 }
			}
		}
		return result;
	}
	//取出list中regionId
	public String selectregion(List<Map<String, Object>> list){
		String restr="";
			for (int i=0;i<list.size();i++){
				Map<String,Object> map= list.get(i);
				if(i==(list.size()-1)){
					restr+="'"+map.get("REGION_ID")+"'";
				}else{
					restr+="'"+map.get("REGION_ID")+"'"+",";
				}
			}
		
		return restr;
	}
	//配置条件的数值
	public String allindex(JSONArray arry){
		String str="";
		if(arry.size()>0){
			str+="and (";
			for (int i=0;i<arry.size();i++){
				JSONObject object=arry.getJSONObject(i);
				if(i==(arry.size()-1)){
					str+="("+ "MM_ID='"+object.get("indexid")+"'and MM_VAL between '"+object.get("indexmin")+"' and '"+object.get("indexmax")+"' "+")";
				}else{
					str+="("+ "MM_ID='"+object.get("indexid")+"' and MM_VAL between '"+object.get("indexmin")+"' and '"+object.get("indexmax")+"' " +")"+"or";
				}
			}
			str+=")";
		}
		return str;
	}
	//如果筛选条件有数
	 public String havehvaing(JSONArray arry){
		 String str="";
		 if(arry.size()>0){
			 str+="having count(0)='"+arry.size()+"'";
		 }
		return str;
	 }
	 //取出数组的值
	public String allarry(JSONArray zoom){
		 String str="";
		 for(int i=0;i<zoom.size();i++){
			 if(i==(zoom.size()-1)){
				str+="'"+zoom.getString(i)+"'"; 
			 }else{
				 str+="'"+zoom.getString(i)+"'"+","; 
			 }
		 }
		 return str;
	}
	private static String FormateRingsData(String rings){
		String result = "";
		String str1 = rings.replace(",", "],[").replace(" ", ",");
		StringBuffer bf = new StringBuffer("[[");
		bf.append(str1).append("]]");
		result = bf.toString();
		return result;
	}
	
	private static String getPointColorLVL(JSONObject rule,double val){
		String result = "";
		double H_MIN=rule.getDouble("HMIN");
		double M_MIN=rule.getDouble("MMIN");
		double M_MAX=rule.getDouble("MMAX");
		double L_MIN=rule.getDouble("LMIN");
		double L_MAX=rule.getDouble("LMAX");
		if(val<=0){
			result = "L";
		}else if(val>=L_MIN && val<L_MAX){
			result = "L";
		}else if(val>=M_MIN && val<M_MAX){
			result = "M";
		}else if(val>=H_MIN){
			result = "H";
		}
		return result;
	}

	public JSONArray queryForSceneListData(String datetype,String userId, String scenetype) {
		JSONArray result = new JSONArray();
		String sql = "";
		Object [] value = {};
		if("R".equals(scenetype.toUpperCase())){
			sql = "select distinct a.*,b.MM_ID,c.MM_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID = b.SCENE_ID and b.ORDER_ID = '0' "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on b.MM_ID=c.MM_ID "+
					"WHERE a.DATE_TYPE = ? and a.USER_ID = 'SYSTEM' order by SCENE_ID desc";
			Object [] temp = {datetype};
			value=temp;
		}else if("M".equals(scenetype.toUpperCase())){
			sql = "select distinct a.*,b.MM_ID,c.MM_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID = b.SCENE_ID and b.ORDER_ID = '0' "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on b.MM_ID=c.MM_ID "+
					"WHERE (a.DATE_TYPE = ? or a.DATE_TYPE is NULL) and a.USER_ID = ? order by SCENE_ID desc";
			Object [] temp = {datetype,userId};
			value=temp;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("sceneId",map.get("SCENE_ID"));
				obj.put("sceneName",map.get("SCENE_NAME"));
				obj.put("sceneType",map.get("SCENE_TYPE"));
				obj.put("sceneDesc",map.get("SCENE_DESC"));
				if("filter".equals(map.get("SCENE_TYPE").toString())){
					obj.put("sceneFIndexId",map.get("MM_ID"));
					obj.put("sceneFIndexName",map.get("MM_NAME"));
				}else if("check".equals(map.get("SCENE_TYPE").toString())){
					obj.put("sceneFIndexId","null");
					obj.put("sceneFIndexName","null");
				}
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray queryPointNearBTSData(String date, String pointid) {
		JSONArray result= new JSONArray();
		String sql = "";
		sql = "select a.POINT_ID,b.POINT_NAME,a.NET_TYPE,b.POINT_LONG,b.POINT_LAT from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_BTS_YYT_REAL_INFO a "+
			"left join BASS_DATA.TB_STRATEGY_MAP_DIM_BTS_YYT_INFO b on a.POINT_ID = b.POINT_ID "+
			"where a.CELL_ID = ? and a.STATIS_DATE in (select MAX(STATIS_DATE) from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_BTS_YYT_REAL_INFO) " +
			"and b.STATIS_MONTH in (select MAX(STATIS_MONTH) from BASS_DATA.TB_STRATEGY_MAP_DIM_BTS_YYT_INFO) " +
			"and a.POINT_TYPE = 'BTS' and b.POINT_ID IS NOT NULL";
		Object [] value = {pointid};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("btsId", map.get("POINT_ID"));
				obj.put("btsName", map.get("POINT_NAME"));
				obj.put("btsType", map.get("NET_TYPE"));
				if(map.get("POINT_LONG")!=null){
					obj.put("btsLong", Double.valueOf(map.get("POINT_LONG").toString()));
					obj.put("btsLat", Double.valueOf(map.get("POINT_LAT").toString()));
				}else{
					obj.put("btsLong", "");
					obj.put("btsLat", "");
				}
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray queryGridPolygonData(String indexid, String date,String datetype, String dishiid, String xianquid,JSONObject extent) {
		JSONArray result = new JSONArray();
		JSONObject rule = new JSONObject();
		/*double XMIN = extent.getDouble("xMin");
		double XMAX = extent.getDouble("xMax");
		double YMIN = extent.getDouble("yMin");
		double YMAX = extent.getDouble("yMax");*/
		String sql = "select LIGHT_COLOR_MIN,LIGHT_COLOR_MAX,LIGHT_BLUE_MIN,LIGHT_BLUE_MAX,BLUE_COLOR_MIN,BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO  "+
					"where MM_ID=? and DATE_TYPE = ? and MM_LEVEL = 'GRID'";
		Object [] value ={indexid,datetype};
		List<Map<String, Object>> listrule = this.getJdbcTemplate().queryForList(sql, value);
		if(listrule.size()!=0){
			for (int i = 0; i < listrule.size(); i++) {
				Map<String, Object> map = listrule.get(i);
				rule.put("LMIN", (map.get("LIGHT_COLOR_MIN")!=null? map.get("LIGHT_COLOR_MIN") : 0 ));
				rule.put("LMAX", (map.get("LIGHT_COLOR_MAX")!=null? map.get("LIGHT_COLOR_MAX") : 0 ));
				rule.put("MMIN", (map.get("LIGHT_BLUE_MIN")!=null? map.get("LIGHT_BLUE_MIN") : 0 ));
				rule.put("MMAX", (map.get("LIGHT_BLUE_MAX")!=null? map.get("LIGHT_BLUE_MAX") : 0 ));
				rule.put("HMIN", (map.get("BLUE_COLOR_MIN")!=null? map.get("BLUE_COLOR_MIN") : 0 ));
				rule.put("HMAX", (map.get("BLUE_COLOR_MAX")!=null? map.get("BLUE_COLOR_MAX") : 0 ));
			}
		}
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String sql_point = "";
		Object [] valuepoint;
		if("all".equals(xianquid.toLowerCase())){
			sql_point = "select distinct a.GRID_ID,a.LONG_MIN,a.LONG_MAX,a.LAT_MIN,a.LAT_MAX,b.MM_ID,b.MM_NAME,c.MM_VAL,b.MM_UNIT "+
						"from BASS_DATA.TB_STRATEGY_MAP_DIM_GRID_INFO a  "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b on b.DATE_TYPE=? and b.MM_LEVEL ='GRID' AND b.MM_ID = ? "+
						"left join BASS_DATA."+tabelname+" c on c.MM_ID=b.MM_ID and c."+datecolumn+" =? and a.GRID_ID=c.REGION_ID "+
						"where a.REGION_ID = ? ";
			Object [] temp = {datetype,indexid,date,dishiid};
			valuepoint =temp;
		}else{
			sql_point = "select distinct a.GRID_ID,a.LONG_MIN,a.LONG_MAX,a.LAT_MIN,a.LAT_MAX,b.MM_ID,b.MM_NAME,c.MM_VAL,b.MM_UNIT "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_GRID_INFO a  "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b on b.DATE_TYPE=? and b.MM_LEVEL ='POINT' AND b.MM_ID = ? "+
					"left join BASS_DATA."+tabelname+" c on c.MM_ID=b.MM_ID and c."+datecolumn+" =? and a.GRID_ID=c.REGION_ID "+
					"where a.REGION_ID = ? and a.COUNTY_ID = ? ";
			Object [] temp = {datetype,indexid,date,dishiid,xianquid};
			valuepoint =temp;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql_point, valuepoint);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("gridId", map.get("GRID_ID"));
				obj.put("gridRings", getGridRings(map.get("LONG_MIN").toString(),map.get("LONG_MAX").toString(),map.get("LAT_MIN").toString(),map.get("LAT_MAX").toString()));
				obj.put("indexValue", (map.get("MM_VAL")==null? "--" : map.get("MM_VAL")));
				obj.put("gridColor", (map.get("MM_VAL")==null? getPointColorLVL(rule,0) : getPointColorLVL(rule,Double.valueOf(map.get("MM_VAL").toString()))));
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexUnit", map.get("MM_UNIT"));
				result.add(obj);
			}
		}
		return result;
	}
	/**
	 * @Title:getGridRings
	 * @Description:组合网格边界值
	 * @param LONG_MIN
	 * @param LONG_MAX
	 * @param LAT_MIN
	 * @param LAT_MAX
	 * @return
	 * @author:白少华
	 * @date:2017-6-26
	 */
	private static JSONArray getGridRings(String LONG_MIN,String LONG_MAX,String LAT_MIN,String LAT_MAX){
		JSONArray result = new JSONArray();
		JSONArray eachpoint1 = new JSONArray();
		JSONArray eachpoint2 = new JSONArray();
		JSONArray eachpoint3 = new JSONArray();
		JSONArray eachpoint4 = new JSONArray();
		double D_LONG_MIN = Double.valueOf(LONG_MIN);
		double D_LONG_MAX = Double.valueOf(LONG_MAX);
		double D_LAT_MIN = Double.valueOf(LAT_MIN);
		double D_LAT_MAX = Double.valueOf(LAT_MAX);
		
		eachpoint1.add(D_LONG_MIN);
		eachpoint1.add(D_LAT_MIN);
		
		eachpoint2.add(D_LONG_MIN);
		eachpoint2.add(D_LAT_MAX);
		
		eachpoint3.add(D_LONG_MAX);
		eachpoint3.add(D_LAT_MIN);
		
		eachpoint4.add(D_LONG_MAX);
		eachpoint4.add(D_LAT_MAX);
		
		result.add(eachpoint1);
		result.add(eachpoint2);
		result.add(eachpoint4);
		result.add(eachpoint3);
		return result;
	}

	public List<Map<String, Object>> getMapPointIndexData(String pointtype,String indexid, String date, String datetype, String dishiid,String xianquid) {
		List<Map<String,Object>> listdata = new ArrayList<Map<String,Object>>();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		String sql_point="";
		Object [] value = null;
		if("all".equals(xianquid.toLowerCase())){
			sql_point = "select a.CELL_NAME,c.MM_NAME,d.MM_VAL,('环比') as RING_RATE_NAME,round(d.RING_RATE,2) as RING_RATE,('同比') as SAME_RATE_NAME,round(d.SAME_RATE,2) as SAME_RATE,c.MM_UNIT "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = ? and c.MM_ID = ?"+
					"left join BASS_DATA."+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = ? and c.MM_ID = d.MM_ID "+
					"WHERE a.REGION_CODE = ? and a.CELL_TYPE = ? ";
			Object [] temp = {datetype,indexid,date,dishiid,pointtype};
			value =temp;
		}else{
			sql_point = "select a.CELL_NAME,c.MM_NAME,d.MM_VAL,('环比') as RING_RATE_NAME,d.RING_RATE,('同比') as SAME_RATE_NAME,d.SAME_RATE,c.MM_UNIT "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = ? and c.MM_ID = ?  "+
					"left join BASS_DATA."+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = ? and c.MM_ID = d.MM_ID  "+
					"WHERE a.REGION_CODE = ? and a.TOWN_ID = ? and a.CELL_TYPE = ? ";
			Object [] temp = {datetype,indexid,date,dishiid,xianquid,pointtype};
			value =temp;
		}
		listdata = this.getJdbcTemplate().queryForList(sql_point,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				if("D".equals(datetype.toUpperCase())){
					map.put("STATIS_TIME", date.substring(0, 4)+"年"+date.substring(4, 6)+"月"+date.substring(6, 8)+"日");
				}else if("M".equals(datetype.toUpperCase())){
					map.put("STATIS_TIME", date.substring(0, 4)+"年"+date.substring(4, 6)+"月");
				}
				if(map.get("MM_VAL")==null){
					map.put("MM_VAL", "--");
				}
				if(map.get("RING_RATE")==null){
					map.put("RING_RATE", "--");
				}else{
					if(Double.valueOf(map.get("RING_RATE").toString())==999){
						map.put("RING_RATE", "--");
					}else{
						BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("RING_RATE").toString()));
						bgd.setScale(2, RoundingMode.UP);
						map.put("RING_RATE", bgd);
					}
				}
				if(map.get("SAME_RATE")==null){
					map.put("SAME_RATE", "--");
				}else{
					if(Double.valueOf(map.get("SAME_RATE").toString())==999){
						map.put("SAME_RATE", "--");
					}else{
						BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("SAME_RATE").toString()));
						bgd.setScale(2, RoundingMode.UP);
						map.put("SAME_RATE", bgd);
					}
				}
			}
		}
		return listdata;
	}

	public List<Map<String, Object>> getScenePointData(String pointtype,String indexid, String date, String datetype, String dishiid,String xianquid, String sceneid) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		String tabelname = "";
		String datecolumn = "";
		String regionsql = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		if(xianquid.toLowerCase().equals("all")){
			regionsql="b.REGION_CODE='"+dishiid+"'";
		}else{
			regionsql="b.REGION_CODE='"+dishiid+"' and b.TOWN_ID='"+xianquid+"'";
		}
		List<Map<String, Object>> listdata = new ArrayList<Map<String,Object>>();
		//查询场景类型
		String sqlscene = "select a.*,b.CELL_TYPE from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
				"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID=b.SCENE_ID where a.SCENE_ID = '"+sceneid+"'";
		List<Map<String, Object>> listscenetype = this.getJdbcTemplate().queryForList(sqlscene);
		String scenetype = "";
		if(listscenetype.size()!=0){
			scenetype = listscenetype.get(0).get("SCENE_TYPE").toString();
		}
		//场景类型不同需要作区分1.filter 2.check
		if("filter".equals(scenetype.toLowerCase())){
			String sql_scene = "select CELL_TYPE,MM_ID,MM_MIN,MM_MAX from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO WHERE SCENE_ID = '"+sceneid+"'";
			List<Map<String, Object>> listscene = this.getJdbcTemplate().queryForList(sql_scene);
			JSONArray indexjson = new JSONArray();
			if(listscene.size()!=0){
				for (int i = 0; i < listscene.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listscene.get(i);
					obj.put("indexid", map.get("MM_ID"));
					obj.put("indexmin", map.get("MM_MIN"));
					obj.put("indexmax", map.get("MM_MAX"));
					indexjson.add(obj);
				}
			}
			/*----------------------------------------------------*/
			String sql="select  a.REGION_ID from "+tabelname+" a  " +
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO b  on a.REGION_ID=b.CELL_ID " +
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO c on b.CELL_ID=c.CELL_ID " +
					"where "+datecolumn+"='"+ date +"' "+
					"  and  "+regionsql+" "+
					allindex(indexjson)+" group by a.REGION_ID "+havehvaing(indexjson)+" "  ;
			listdata = this.getJdbcTemplate().queryForList(sql);
		}else if("check".equals(scenetype.toLowerCase())){
			String sql2 = "select MM_ID as REGION_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO WHERE SCENE_ID='"+sceneid+"'"; 
			listdata = this.getJdbcTemplate().queryForList(sql2);
		}
		if(listdata.size()!=0){
			String sql2="";
			if("all".equals(xianquid.toLowerCase())){
				sql2="select a.CELL_NAME,c.MM_NAME,d.MM_VAL,'环比' as RING_RATE_NAME,round(d.RING_RATE,2) as RING_RATE,'同比' as SAME_RATE_NAME,round(d.SAME_RATE,2) as SAME_RATE,c.MM_UNIT "+
						"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = '"+datetype+"' and c.MM_ID = '"+indexid+"'"+
						"left join "+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = '"+date+"' and c.MM_ID = d.MM_ID "+
						"WHERE a.REGION_CODE = '"+dishiid+"'  and a.CELL_ID in ("+selectregion(listdata)+") ";
			}else{
				sql2="select a.CELL_NAME,c.MM_NAME,d.MM_VAL,'环比' as RING_RATE_NAME,d.RING_RATE,'同比' as SAME_RATE_NAME,d.SAME_RATE,c.MM_UNIT "+
						"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a  "+
						"left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_LEVEL = 'POINT' and c.DATE_TYPE = '"+datetype+"' and c.MM_ID = '"+indexid+"'"+
						"left join "+tabelname+" d on a.CELL_ID = d.REGION_ID and d."+datecolumn+" = '"+date+"' and c.MM_ID = d.MM_ID "+
						"WHERE a.REGION_CODE = '"+dishiid+"' and a.TOWN_ID = '"+xianquid+"'  and a.CELL_ID in ("+selectregion(listdata)+") ";
			}
			result=this.getJdbcTemplate().queryForList(sql2);
			if(result.size()!=0){
				for (int i = 0; i < result.size(); i++) {
					Map<String, Object> map = result.get(i);
					if("D".equals(datetype.toUpperCase())){
						map.put("STATIS_TIME", date.substring(0, 4)+"年"+date.substring(4, 6)+"月"+date.substring(6, 8)+"日");
					}else if("M".equals(datetype.toUpperCase())){
						map.put("STATIS_TIME", date.substring(0, 4)+"年"+date.substring(4, 6)+"月");
					}
					if(map.get("MM_VAL")==null){
						map.put("MM_VAL", "--");
					}
					if(map.get("RING_RATE")==null){
						map.put("RING_RATE", "--");
					}else{
						if(Double.valueOf(map.get("RING_RATE").toString())==999){
							map.put("RING_RATE", "--");
						}else{
							BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("RING_RATE").toString()));
							bgd.setScale(2, RoundingMode.UP);
							map.put("RING_RATE", bgd);
						}
					}
					if(map.get("SAME_RATE")==null){
						map.put("SAME_RATE", "--");
					}else{
						if(Double.valueOf(map.get("SAME_RATE").toString())==999){
							map.put("SAME_RATE", "--");
						}else{
							BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("SAME_RATE").toString()));
							bgd.setScale(2, RoundingMode.UP);
							map.put("SAME_RATE", bgd);
						}
					}
				}
			}
		}
		return result;
	}
}
