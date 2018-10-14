package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.SceneCustomizationIndexManager;
@Service
public class SceneCustomizationIndexManagerImpl extends AbstractManager implements SceneCustomizationIndexManager {
	
	public JSONArray queryForIndex(String keystr, String querytype) {
		JSONArray result=new JSONArray();
		String sql = "";
		Object [] value = null;
		StringBuffer sbf = new StringBuffer("%");
		sbf.append(keystr).append("%");
		String key=sbf.toString();
		if("type".equals(querytype)){
			sql = "select distinct MM_ID,MM_NAME,MM_BIG_TYPE_NAME,MM_SMALL_TYPE_NAME,MM_DESC,DATE_TYPE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO A where A.MM_BIG_TYPE_NAME LIKE ? or A.MM_SMALL_TYPE_NAME like ?";
			Object [] temp = {key,key};
			value = temp;
		}else if("key".equals(querytype)){
			sql = "select distinct MM_ID,MM_NAME,MM_BIG_TYPE_NAME,MM_SMALL_TYPE_NAME,MM_DESC,DATE_TYPE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO A where A.MM_NAME LIKE ?";
			
			Object [] temp = {key};
			value = temp;
		}else{
			return result;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexBigType", map.get("MM_BIG_TYPE_NAME"));
				obj.put("indexSmallType", map.get("MM_SMALL_TYPE_NAME"));
				obj.put("indexDesc", map.get("MM_DESC"));
				if("D".equals(map.get("DATE_TYPE"))){
					obj.put("indexDateType", "日");
				}else if("M".equals(map.get("DATE_TYPE"))){
					obj.put("indexDateType", "月");
				}else{
					obj.put("indexDateType", "--");
				}
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray queryForIndex(String keystr, String querytype,String datetype) {
		JSONArray result=new JSONArray();
		String sql = "";
		Object [] value = null;
		StringBuffer sbf = new StringBuffer("%");
		sbf.append(keystr).append("%");
		String key=sbf.toString();
		
		if("".equals(keystr)){
			sql = "select distinct MM_UNIT,MM_ID,MM_NAME,MM_BIG_TYPE_NAME,MM_SMALL_TYPE_NAME,MM_DESC,DATE_TYPE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO A where A.DATE_TYPE=?";
			Object [] temp = {datetype};
			value = temp;
		}else if("type".equals(querytype)){
			sql = "select distinct MM_UNIT,MM_ID,MM_NAME,MM_BIG_TYPE_NAME,MM_SMALL_TYPE_NAME,MM_DESC,DATE_TYPE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO A where (A.MM_BIG_TYPE_NAME LIKE ? or A.MM_SMALL_TYPE_NAME like ?) and A.DATE_TYPE=? and A.MM_LEVEL='POINT' ";
			Object [] temp = {key,key,datetype};
			value = temp;
		}else if("key".equals(querytype)){
			sql = "select distinct MM_UNIT,MM_ID,MM_NAME,MM_BIG_TYPE_NAME,MM_SMALL_TYPE_NAME,MM_DESC,DATE_TYPE "+
					"from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO A where A.MM_NAME LIKE ?  and A.DATE_TYPE=? and A.MM_LEVEL='POINT' ";
			
			Object [] temp = {key,datetype};
			value = temp;
		}else{
			return result;
		}
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("indexId", map.get("MM_ID"));
				obj.put("indexunit", map.get("MM_UNIT"));
				obj.put("indexName", map.get("MM_NAME"));
				obj.put("indexBigType", map.get("MM_BIG_TYPE_NAME"));
				obj.put("indexSmallType", map.get("MM_SMALL_TYPE_NAME"));
				obj.put("indexDesc", map.get("MM_DESC"));
				if("D".equals(map.get("DATE_TYPE"))){
					obj.put("indexDateType", "日");
				}else if("M".equals(map.get("DATE_TYPE"))){
					obj.put("indexDateType", "月");
				}else{
					obj.put("indexDateType", "--");
				}
				result.add(obj);
			}
		}
		return result;
	}

	public JSONObject queryForIndexInfoById(String indexid,String datetype,String indexlvl) {
		JSONObject result = new JSONObject();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO WHERE MM_ID = ? AND DATE_TYPE = ? AND MM_LEVEL = '"+indexlvl+"'";
		Object [] value = {indexid,datetype};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			result=JSONObject.fromObject(listdata.get(0));
		}
		return result;
	}
	

}
