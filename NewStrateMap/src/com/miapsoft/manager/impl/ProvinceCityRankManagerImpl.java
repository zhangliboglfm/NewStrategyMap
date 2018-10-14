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
import com.miapsoft.manager.ProvinceCityRankManager;

/**
 * <p>Title: ProvinceCityRankManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-17
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("provinceCityRankManager")
public class ProvinceCityRankManagerImpl extends AbstractManager implements ProvinceCityRankManager {

/**
 *切换显示指标
 */
	public JSONArray queryForIndexByIndexKey(String datetype, String keystr,String MM_LEVEL) {
		JSONArray result = new JSONArray();
		String sql = "select MM_ID,MM_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO WHERE DATE_TYPE=? and ((MM_NAME LIKE ?) " +
				"or (MM_SMALL_TYPE_NAME like ?) or (MM_BIG_TYPE_NAME like ?))  AND MM_LEVEL= ? ";
		StringBuffer sbf = new StringBuffer("%");
		sbf.append(keystr).append("%");
		String key = sbf.toString();
		Object [] value = {datetype,key,key,key,MM_LEVEL};
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

	/**
	 * 获取最大日期
	 */
	public String getMaxDate(String datetype) {
		String sql="SELECT max(STATIS_DATE) as STATIS_DATE FROM BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE= ?";
		Object[] value={datetype};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		return  listdata.get(0).get("STATIS_DATE").toString();
	}
/**
 * 拼接左侧相关指标
 */
	public JSONArray initLeftQueryIndex(String regionId, String date,String MM_LEVEL, String datetype) {
		String date1=date.replace(".", "");//去除日期中的点
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
		String sql = "select a.MM_ID,a.MM_NAME, round(b.MM_VAL,2) as MM_VAL,a.MM_UNIT,round(b.SAME_RATE,2) as SAME_RATE, round(b.RING_RATE,2) as RING_RATE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
					"left join BASS_DATA."+tabelname+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+datecolumn+" = ? "+
					"where a.MM_LEVEL = ?  AND a.IS_HOME_SHOW != '0' AND a.DATE_TYPE = ? ORDER BY a.IS_HOME_SHOW";
		Object [] value = {regionId,date1,MM_LEVEL,datetype};
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
				obj.put("regionId", regionId);
				result.add(obj);
			}
		}
		return result;
	}

	/**
	 * 查询该地域兴趣点类型和数量
	 */
public JSONArray initInterstingPointNumber(String dishiRegionId,String xianquRegionId) {
	JSONArray result = new JSONArray();
	String sql = "";
	Object [] value = null;
	List<Map<String, Object>> listdata=null;
	if("all".equals(dishiRegionId.toLowerCase())){
		sql="select CELL_TYPE,count(CELL_ID) as POINT_COUNT,ORDER_ID "+
				"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO group by CELL_TYPE,ORDER_ID order by ORDER_ID";
		listdata = this.getJdbcTemplate().queryForList(sql);
	}else{
		if("all".equals(xianquRegionId.toLowerCase())){
			sql = "select CELL_TYPE,count(CELL_ID) as POINT_COUNT,ORDER_ID " +
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO where REGION_CODE=? group by CELL_TYPE,ORDER_ID order by ORDER_ID";
			Object [] temp = {dishiRegionId};
			value = temp;
		}else{
			sql = "select CELL_TYPE,count(CELL_ID) as POINT_COUNT,ORDER_ID " +
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO where REGION_CODE=? and TOWN_ID=? group by CELL_TYPE,ORDER_ID order by ORDER_ID";
			Object [] temp = {dishiRegionId,xianquRegionId};
			value = temp;
		}
		 listdata = this.getJdbcTemplate().queryForList(sql, value);
	}
	
	if(listdata.size()!=0){
		for (int i = 0; i < listdata.size(); i++) {
			JSONObject obj = new JSONObject();
			Map<String, Object> map = listdata.get(i);
			obj.put("type", map.get("CELL_TYPE"));
			double d = Double.valueOf(map.get("POINT_COUNT").toString());
			obj.put("count", NumberFormat.getInstance().format(d));
			result.add(obj);
		}
	}
	return result;

	}

/*查询相关指标*/
public JSONArray queryRelevantIndex(String datetype, String date,String indexid, String regionid,String MM_LEVEL) {
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
	String sql = "select A.MM_ID,C.MM_NAME,round(B.MM_VAL,2) as MM_VAL,C.MM_UNIT,round(B.RING_RATE,2) as RING_RATE  "+
				"from BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO A "+
				"LEFT JOIN BASS_DATA."+tabelname+" B ON A.MM_ID=B.MM_ID and B."+datecolumn+"=? AND B.REGION_ID=? "+
				"LEFT JOIN BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO C ON A.MM_ID=C.MM_ID AND C.DATE_TYPE = ? AND C.MM_LEVEL = ? "+
				"WHERE A.PARENT_MM_ID = ? AND A.DATE_TYPE = ? ORDER BY C.IS_HOME_SHOW";
	Object [] value = {date,regionid,datetype,MM_LEVEL,indexid,datetype};
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
			result.add(obj);
		}
	}
	return result;
}
	

/*指标切换查询以及地图渲染*/
public JSONObject querySwitchIndex(String MM_ID, String date,String dishiRegionId, String xianquRegionId,String dateType) {
		JSONObject result = new JSONObject();
		String tablename="";
		String field="";
		
		//设置查询日表、月表
		if(dateType.equalsIgnoreCase("D")){
			tablename="TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			field="STATIS_DATE";	
		}else if(dateType.equalsIgnoreCase("M")){
			tablename="TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			field="STATIS_MONTH";
		};
		
		if("all".equals(dishiRegionId.toLowerCase())){
			//表明查询的哪一级别的数据 ,1为省级别的数据
			result.put("all", "1");
			//查询出所有的地市Id
			JSONArray alldishiId =new JSONArray();
			String sql = "select REGION_NAME, REGION_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='2' AND PARENT_REGION_ID='1' order by REGION_ID";
			List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql);
			if(listdata.size()!=0){
				for (int i = 0; i < listdata.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata.get(i);
					obj.put("REGION_NAME", map.get("REGION_NAME"));
					obj.put("REGION_ID", map.get("REGION_ID"));
					alldishiId.add(obj);
				}
			}
			/********************************************************************************************************************/
			/*拼接指标切换所需数据*/
			String sql1 = "select a.MM_ID,a.MM_NAME,round(b.MM_VAL,2) as MM_VAL ,a.MM_UNIT,round(b.RING_RATE,2) as RING_RATE ,a.LIGHT_COLOR_MIN,a.LIGHT_COLOR_MAX, "+
					"a.LIGHT_BLUE_MIN,a.LIGHT_BLUE_MAX,a.BLUE_COLOR_MIN,a.BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a  "+
					"left join BASS_DATA."+tablename+" b on a.MM_ID = b.MM_ID and b.REGION_ID ='1' and b."+field+" = ?  "+
					"where a.MM_LEVEL = 'CITY' AND a.DATE_TYPE =  ? and a.MM_ID = ?";
			JSONArray globalIndex =new JSONArray();
			String date1=date.replace(".", "");//去除日期中的点
			Object [] value1 = {date1,dateType,MM_ID};
			List<Map<String, Object>> listdata1 = this.getJdbcTemplate().queryForList(sql1,value1);
			if(listdata1.size()!=0){
				for (int i = 0; i < listdata1.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata1.get(i);
					obj.put("MM_ID", map.get("MM_ID"));
					obj.put("MM_NAME", map.get("MM_NAME"));
					if(map.get("MM_VAL")!=null){
						double d = Double.valueOf(map.get("MM_VAL").toString());
						obj.put("MM_VAL", NumberFormat.getInstance().format(d));
					}else{
						obj.put("MM_VAL", map.get("MM_VAL"));
					}
					
					if(map.get("RING_RATE")!=null){
						double d = Double.valueOf(map.get("RING_RATE").toString());
						obj.put("RING_RATE", NumberFormat.getInstance().format(d));
					}else{
						obj.put("RING_RATE", map.get("RING_RATE"));
					}
					obj.put("MM_UNIT", map.get("MM_UNIT"));
					obj.put("LIGHT_COLOR_MIN", map.get("LIGHT_COLOR_MIN"));
					obj.put("LIGHT_COLOR_MAX", map.get("LIGHT_COLOR_MAX"));
					obj.put("LIGHT_BLUE_MIN", map.get("LIGHT_BLUE_MIN"));
					obj.put("LIGHT_BLUE_MAX", map.get("LIGHT_BLUE_MAX"));
					obj.put("BLUE_COLOR_MIN", map.get("BLUE_COLOR_MIN"));
					obj.put("BLUE_COLOR_MAX", map.get("BLUE_COLOR_MAX"));
					globalIndex.add(obj);
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("globalIndex", globalIndex);
			/**************************************************************************************************************************/
			String sql2 =" select "+
					" a.REGION_ID,a.REGION_NAME,round(c.MM_VAL,2) as MM_VAL "+
				" from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a "+
				" left join  "+
					 " BASS_DATA."+tablename+" c "+
				" on "+
					" c.REGION_ID=a.REGION_ID  AND c."+field+" = ? and  c.MM_ID= ? "+
				" left join  "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b "+
				" on  "+
					" c.MM_ID=b.MM_ID  and b.MM_LEVEL ='CITY' and b.DATE_TYPE= ? "+
				" where "+
					" a.REGION_ID= ? ";
			JSONArray apartIndex =new JSONArray();
			if(alldishiId.size()!=0){
				for (int i=0;i<alldishiId.size();i++){
					Object [] value2 = {date1,MM_ID,dateType,alldishiId.getJSONObject(i).getString("REGION_ID")};
					List<Map<String, Object>> listdata11 = this.getJdbcTemplate().queryForList(sql2,value2);
					if(listdata11.size()!=0){
						for (int i1 = 0; i1 < listdata11.size(); i1++) {
							JSONObject obj = new JSONObject();
							Map<String, Object> map = listdata11.get(i1);
							obj.put("REGION_NAME", map.get("REGION_NAME")+"市");
							obj.put("REGION_ID", map.get("REGION_ID"));
							if(map.get("MM_VAL")!=null){
								double d = Double.valueOf(map.get("MM_VAL").toString());
								obj.put("MM_VAL", d);
								obj.put("indexValue", NumberFormat.getInstance().format(d));
							}else{
								obj.put("MM_VAL",0);
								obj.put("indexValue", "--");
							}
							
							apartIndex.add(obj);
						}
					}
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("apartIndex", apartIndex);
		}else if("all".equals(xianquRegionId.toLowerCase())){
			//表明查询的哪一级别的数据 ,2为地市级别的数据
			result.put("all", "2");
			//查询出所有的地市Id
			JSONArray allxianquId =new JSONArray();
			String sql = "select REGION_NAME, REGION_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID= ? order by REGION_ID";
			Object [] value2 ={dishiRegionId};
			List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value2);
			if(listdata.size()!=0){
				for (int i = 0; i < listdata.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata.get(i);
					obj.put("REGION_NAME", map.get("REGION_NAME"));
					obj.put("REGION_ID", map.get("REGION_ID"));
					allxianquId.add(obj);
				}
			}
			/********************************************************************************************************************/
			/*查询出最高级别Id对应的指标的总体数值*/
			String sql1 ="select a.MM_ID,a.MM_NAME,round(b.MM_VAL,2) as MM_VAL ,a.MM_UNIT,round(b.RING_RATE,2) as RING_RATE,a.LIGHT_COLOR_MIN,a.LIGHT_COLOR_MAX, "+
					"a.LIGHT_BLUE_MIN,a.LIGHT_BLUE_MAX,a.BLUE_COLOR_MIN,a.BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a  "+
					"left join BASS_DATA."+tablename+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+field+" = ?  "+
					"where a.MM_LEVEL = 'COUNTY' AND a.DATE_TYPE =  ? and a.MM_ID = ?";
			JSONArray globalIndex =new JSONArray();
			String date1=date.replace(".", "");//去除日期中的点
			Object [] value1 = {dishiRegionId,date1,dateType,MM_ID};
			List<Map<String, Object>> listdata1 = this.getJdbcTemplate().queryForList(sql1,value1);
			if(listdata1.size()!=0){
				for (int i = 0; i < listdata1.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata1.get(i);
					obj.put("MM_ID", map.get("MM_ID"));
					obj.put("MM_NAME", map.get("MM_NAME"));
					if(map.get("MM_VAL")!=null){
						double d = Double.valueOf(map.get("MM_VAL").toString());
						obj.put("MM_VAL", NumberFormat.getInstance().format(d));
					}else{
						obj.put("MM_VAL", map.get("MM_VAL"));
					}
					
					if(map.get("RING_RATE")!=null){
						double d = Double.valueOf(map.get("RING_RATE").toString());
						obj.put("RING_RATE", NumberFormat.getInstance().format(d));
					}else{
						obj.put("RING_RATE", map.get("RING_RATE"));
					}
					obj.put("MM_UNIT", map.get("MM_UNIT"));
					obj.put("LIGHT_COLOR_MIN", map.get("LIGHT_COLOR_MIN"));
					obj.put("LIGHT_COLOR_MAX", map.get("LIGHT_COLOR_MAX"));
					obj.put("LIGHT_BLUE_MIN", map.get("LIGHT_BLUE_MIN"));
					obj.put("LIGHT_BLUE_MAX", map.get("LIGHT_BLUE_MAX"));
					obj.put("BLUE_COLOR_MIN", map.get("BLUE_COLOR_MIN"));
					obj.put("BLUE_COLOR_MAX", map.get("BLUE_COLOR_MAX"));
					globalIndex.add(obj);
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("globalIndex", globalIndex);
			/**************************************************************************************************************************/
			String sql2 =" select "+
					" a.REGION_ID,a.REGION_NAME,round(c.MM_VAL,2) as MM_VAL "+
				" from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a "+
				" left join  "+
					 " BASS_DATA."+tablename+" c "+
				" on "+
					" c.REGION_ID=a.REGION_ID  AND c."+field+" = ? and  c.MM_ID= ? "+
				" left join  "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b "+
				" on  "+
					" c.MM_ID=b.MM_ID  and b.MM_LEVEL ='COUNTY' and b.DATE_TYPE= ? "+
				" where "+
					" a.REGION_ID= ? ";
			JSONArray apartIndex =new JSONArray();
			if(allxianquId.size()!=0){
				for (int i=0;i<allxianquId.size();i++){
					Object [] value3 = {date1,MM_ID,dateType,allxianquId.getJSONObject(i).getString("REGION_ID")};
					List<Map<String, Object>> listdata11 = this.getJdbcTemplate().queryForList(sql2,value3);
					if(listdata11.size()!=0){
						for (int i1 = 0; i1 < listdata11.size(); i1++) {
							JSONObject obj = new JSONObject();
							Map<String, Object> map = listdata11.get(i1);
							obj.put("REGION_NAME", map.get("REGION_NAME"));
							obj.put("REGION_ID", map.get("REGION_ID"));
							if(map.get("MM_VAL")!=null){
								double d = Double.valueOf(map.get("MM_VAL").toString());
								obj.put("MM_VAL", d);
								obj.put("indexValue", NumberFormat.getInstance().format(d));
							}else{
								obj.put("MM_VAL",0);
								obj.put("indexValue", "--");
							}
							apartIndex.add(obj);
						}
					}
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("apartIndex", apartIndex);
		}else{
			//表明查询的哪一级别的数据 ,3为县区级别的数据
			result.put("all", "3");
			/*查询出最高级别Id对应的指标的总体数值*/
			String sql1 ="select a.MM_ID,a.MM_NAME,round(b.MM_VAL,2) as MM_VAL ,a.MM_UNIT,round(b.RING_RATE,2) as RING_RATE,a.LIGHT_COLOR_MIN,a.LIGHT_COLOR_MAX, "+
					"a.LIGHT_BLUE_MIN,a.LIGHT_BLUE_MAX,a.BLUE_COLOR_MIN,a.BLUE_COLOR_MAX "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO a  "+
					"left join BASS_DATA."+tablename+" b on a.MM_ID = b.MM_ID and b.REGION_ID = ? and b."+field+" = ?  "+
					"where a.MM_LEVEL = 'COUNTY' AND a.DATE_TYPE =  ? and a.MM_ID = ?";
			JSONArray globalIndex =new JSONArray();
			String date1=date.replace(".", "");//去除日期中的点
			Object [] value1 = {xianquRegionId,date1,dateType,MM_ID};
			List<Map<String, Object>> listdata1 = this.getJdbcTemplate().queryForList(sql1,value1);
			if(listdata1.size()!=0){
				for (int i = 0; i < listdata1.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata1.get(i);
					obj.put("REGION_ID",xianquRegionId);
					obj.put("MM_ID", map.get("MM_ID"));
					if(map.get("MM_VAL")!=null){
						double d = Double.valueOf(map.get("MM_VAL").toString());
						obj.put("MM_VAL", NumberFormat.getInstance().format(d));
					}else{
						obj.put("MM_VAL", map.get("MM_VAL"));
					}
					
					if(map.get("RING_RATE")!=null){
						double d = Double.valueOf(map.get("RING_RATE").toString());
						obj.put("RING_RATE", NumberFormat.getInstance().format(d));
					}else{
						obj.put("RING_RATE", map.get("RING_RATE"));
					}
					obj.put("MM_UNIT", map.get("MM_UNIT"));
					obj.put("MM_NAME", map.get("MM_NAME"));
					obj.put("LIGHT_COLOR_MIN", map.get("LIGHT_COLOR_MIN"));
					obj.put("LIGHT_COLOR_MAX", map.get("LIGHT_COLOR_MAX"));
					obj.put("LIGHT_BLUE_MIN", map.get("LIGHT_BLUE_MIN"));
					obj.put("LIGHT_BLUE_MAX", map.get("LIGHT_BLUE_MAX"));
					obj.put("BLUE_COLOR_MIN", map.get("BLUE_COLOR_MIN"));
					obj.put("BLUE_COLOR_MAX", map.get("BLUE_COLOR_MAX"));
					globalIndex.add(obj);
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("globalIndex", globalIndex);
			/*String sql2 =" select "+
					" a.REGION_ID,a.REGION_NAME,round(c.MM_VAL,2) as MM_VAL  "+
				" from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a "+
				" left join  "+
					 " BASS_DATA."+tablename+" c "+
				" on "+
					" c.REGION_ID=a.REGION_ID  AND c."+field+" = ? and  c.MM_ID= ? "+
				" left join  "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b "+
				" on  "+
					" c.MM_ID=b.MM_ID  and b.MM_LEVEL ='COUNTY' and b.DATE_TYPE= ? "+
				" where "+
					" a.REGION_ID= ? ";
			JSONArray apartIndex =new JSONArray();
			
					Object [] value3 = {date1,MM_ID,dateType,xianquRegionId};
					List<Map<String, Object>> listdata11 = this.getJdbcTemplate().queryForList(sql2,value3);
					if(listdata11.size()!=0){
						for (int i1 = 0; i1 < listdata11.size(); i1++) {
							JSONObject obj = new JSONObject();
							Map<String, Object> map = listdata11.get(i1);
							obj.put("REGION_NAME", map.get("REGION_NAME"));
							obj.put("REGION_ID", map.get("REGION_ID"));
							if(map.get("MM_VAL")!=null){
								double d = Double.valueOf(map.get("MM_VAL").toString());
								obj.put("MM_VAL", d);
								obj.put("indexValue", NumberFormat.getInstance().format(d));
							}else{
								obj.put("MM_VAL",0);
								obj.put("indexValue", "--");
							}
							apartIndex.add(obj);
						}
					}
				
			
			//将最高regionId级别的数据放置进去
			result.put("apartIndex", apartIndex);*/
			//查询出所有的地市Id
			JSONArray allxianquId =new JSONArray();
			String sql = "select REGION_NAME, REGION_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID= ? order by REGION_ID";
			Object [] value2 ={dishiRegionId};
			List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value2);
			if(listdata.size()!=0){
				for (int i = 0; i < listdata.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata.get(i);
					obj.put("REGION_NAME", map.get("REGION_NAME"));
					obj.put("REGION_ID", map.get("REGION_ID"));
					allxianquId.add(obj);
				}
			}
			String sql2 =" select "+
					" a.REGION_ID,a.REGION_NAME,round(c.MM_VAL,2) as MM_VAL "+
				" from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a "+
				" left join  "+
					 " BASS_DATA."+tablename+" c "+
				" on "+
					" c.REGION_ID=a.REGION_ID  AND c."+field+" = ? and  c.MM_ID= ? "+
				" left join  "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO b "+
				" on  "+
					" c.MM_ID=b.MM_ID  and b.MM_LEVEL ='COUNTY' and b.DATE_TYPE= ? "+
				" where "+
					" a.REGION_ID= ? ";
			JSONArray apartIndex =new JSONArray();
			if(allxianquId.size()!=0){
				for (int i=0;i<allxianquId.size();i++){
					Object [] value3 = {date1,MM_ID,dateType,allxianquId.getJSONObject(i).getString("REGION_ID")};
					List<Map<String, Object>> listdata11 = this.getJdbcTemplate().queryForList(sql2,value3);
					if(listdata11.size()!=0){
						for (int i1 = 0; i1 < listdata11.size(); i1++) {
							JSONObject obj = new JSONObject();
							Map<String, Object> map = listdata11.get(i1);
							obj.put("REGION_NAME", map.get("REGION_NAME"));
							obj.put("REGION_ID", map.get("REGION_ID"));
							if(map.get("MM_VAL")!=null){
								double d = Double.valueOf(map.get("MM_VAL").toString());
								obj.put("MM_VAL", d);
								obj.put("indexValue", NumberFormat.getInstance().format(d));
							}else{
								obj.put("MM_VAL",0);
								obj.put("indexValue", "--");
							}
							apartIndex.add(obj);
						}
					}
				}
			}
			//将最高regionId级别的数据放置进去
			result.put("apartIndex", apartIndex);
		}
		return result;
	}

public List<Map<String, Object>> downloadIndexdata(String indexid, String date,String datetype, String dishiid, String xianquid,String MM_LEVEL,JSONArray allxinaqudishijson) {
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
	String sql=	" select "+
					" a.REGION_NAME,c.MM_NAME,round(d.MM_VAL,2) as MM_VAL,('环比') as RING_RATE_NAME,round(d.RING_RATE,2) as RING_RATE,('同比') as SAME_RATE_NAME,round(d.SAME_RATE,2) as SAME_RATE,c.MM_UNIT "+
				" from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a  "+ 
				" left join  "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c  "+
				" on  "+
					" c.MM_LEVEL = ?  and c.DATE_TYPE = ? and c.MM_ID = ? "+
				" left join "+
					" BASS_DATA."+tabelname+" d "+ 
				" on "+
					" a.REGION_ID = d.REGION_ID and d."+datecolumn+"= ? and c.MM_ID = d.MM_ID "+
				" WHERE "+
					" a.REGION_ID = ? ";
	if(dishiid.equalsIgnoreCase("all")){
		Object [] temp1 = {MM_LEVEL,datetype,indexid,date,"1"};
		List<Map<String,Object>> listdata1 = new ArrayList<Map<String,Object>>();
		listdata1=this.getJdbcTemplate().queryForList(sql,temp1);
		listdata.addAll(listdata1);
		for(int i=0;i<allxinaqudishijson.size();i++){
			List<Map<String,Object>> listdata2 = new ArrayList<Map<String,Object>>();
			Object [] temp2 = {MM_LEVEL,datetype,indexid,date,allxinaqudishijson.getJSONObject(i).getString("regionId")};
			listdata2=this.getJdbcTemplate().queryForList(sql,temp2);
			listdata.addAll(listdata2);
		}
	}else if(xianquid.equalsIgnoreCase("all")){
		Object [] temp1 = {MM_LEVEL,datetype,indexid,date,dishiid};
		List<Map<String,Object>> listdata1 = new ArrayList<Map<String,Object>>();
		listdata1=this.getJdbcTemplate().queryForList(sql,temp1);
		listdata.addAll(listdata1);
		for(int i=0;i<allxinaqudishijson.size();i++){
			List<Map<String,Object>> listdata2 = new ArrayList<Map<String,Object>>();
			Object [] temp2 = {MM_LEVEL,datetype,indexid,date,allxinaqudishijson.getJSONObject(i).getString("regionId")};
			listdata2=this.getJdbcTemplate().queryForList(sql,temp2);
			listdata.addAll(listdata2);
		}
	}else{
		Object [] temp1 = {MM_LEVEL,datetype,indexid,date,xianquid};
		List<Map<String,Object>> listdata1 = new ArrayList<Map<String,Object>>();
		listdata1=this.getJdbcTemplate().queryForList(sql,temp1);
		listdata.addAll(listdata1);
	}
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

}
