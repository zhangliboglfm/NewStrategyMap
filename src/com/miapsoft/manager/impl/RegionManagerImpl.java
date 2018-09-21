package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.RegionManager;

/**
 * <p>Title: RegionManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-13
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("regionManager")
public class RegionManagerImpl extends AbstractManager implements RegionManager {

	public JSONArray QueryForDishiData() {
		JSONArray result = new JSONArray();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='2' AND PARENT_REGION_ID='1' order by REGION_ID";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray QueryForDishiData2(String userRegionId) {
		JSONArray result = new JSONArray();
		Object [] vlaue={userRegionId};
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='2' AND PARENT_REGION_ID='1' AND REGION_ID =?  order by REGION_ID";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,vlaue);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}
	
	
	public JSONArray QueryForDishiData3(String userRegionId) {
		JSONArray result = new JSONArray();
		Object [] vlaue={userRegionId};
		String sql = "select a.LVL_ID, a.PARENT_REGION_ID,a.REGION_ID, a.REGION_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO a "
					+"join BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO b on a.REGION_ID=b.PARENT_REGION_ID where b.REGION_ID=?";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,vlaue);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}
	public JSONObject QueryForXianquData(JSONArray dishiid) {
		JSONObject result = new JSONObject();
		result.put("QB", new JSONArray());
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID=? order by REGION_ID";
		if(dishiid.size()!=0){
			for (int i = 0; i < dishiid.size(); i++) {
				Object [] vlaue = {dishiid.getJSONObject(i).getString("regionId")};
				List<Map<String, Object>> xianqulist = this.getJdbcTemplate().queryForList(sql, vlaue);
				JSONArray xianqujson = new JSONArray();
				for (int j = 0; j < xianqulist.size(); j++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = xianqulist.get(j);
					obj.put("regionName", map.get("REGION_NAME"));
					obj.put("regionId", map.get("REGION_Id"));
					obj.put("regionParentId", map.get("PARENT_REGION_ID"));
					obj.put("regionLvL", map.get("LVL_ID"));
					xianqujson.add(obj);
				}
				result.put(TransforRegionName(dishiid.getJSONObject(i).getString("regionName")), xianqujson);
			}
		}
		return result;
	}
	
	public JSONObject QueryForXianquData2(JSONArray dishiid) {
		JSONObject result = new JSONObject();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID=? order by REGION_ID";
		if(dishiid.size()!=0){
			for (int i = 0; i < dishiid.size(); i++) {
				Object [] vlaue = {dishiid.getJSONObject(i).getString("regionId")};
				List<Map<String, Object>> xianqulist = this.getJdbcTemplate().queryForList(sql, vlaue);
				JSONArray xianqujson = new JSONArray();
				for (int j = 0; j < xianqulist.size(); j++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = xianqulist.get(j);
					obj.put("regionName", map.get("REGION_NAME"));
					obj.put("regionId", map.get("REGION_Id"));
					obj.put("regionParentId", map.get("PARENT_REGION_ID"));
					obj.put("regionLvL", map.get("LVL_ID"));
					xianqujson.add(obj);
				}
				result.put(TransforRegionName(dishiid.getJSONObject(i).getString("regionName")), xianqujson);
			}
		}
		return result;
	}

	public JSONObject QueryForXianquData3(JSONArray dishiid,String regionId) {
		JSONObject result = new JSONObject();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND REGION_ID=?";
		if(dishiid.size()!=0){
				Object [] vlaue = {regionId};
				List<Map<String, Object>> xianqulist = this.getJdbcTemplate().queryForList(sql, vlaue);
				JSONArray xianqujson = new JSONArray();
				for (int j = 0; j < xianqulist.size(); j++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = xianqulist.get(j);
					obj.put("regionName", map.get("REGION_NAME"));
					obj.put("regionId", map.get("REGION_Id"));
					obj.put("regionParentId", map.get("PARENT_REGION_ID"));
					obj.put("regionLvL", map.get("LVL_ID"));
					xianqujson.add(obj);
				}
				result.put(TransforRegionName(dishiid.getJSONObject(0).getString("regionName")), xianqujson);
			
		}
		return result;
	}
	
	public JSONArray QueryForXianquData(String DishiId) {
		JSONArray result = new JSONArray();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID=? order by REGION_ID";
		Object [] value = {DishiId}; 
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}
	
	private String TransforRegionName(String regionName){
		if(regionName.startsWith("石家庄")){
			return "SJZ";
		}else if(regionName.startsWith("保定")){
			return "BD";
		}else if(regionName.startsWith("张家口")){
			return "ZJK";
		}else if(regionName.startsWith("承德")){
			return "CD";
		}else if(regionName.startsWith("唐山")){
			return "TS";
		}else if(regionName.startsWith("廊坊")){
			return "LF";
		}else if(regionName.startsWith("沧州")){
			return "CZ";
		}else if(regionName.startsWith("衡水")){
			return "HS";
		}else if(regionName.startsWith("邢台")){
			return "XT";
		}else if(regionName.startsWith("邯郸")){
			return "HD";
		}else if(regionName.startsWith("秦皇岛")){
			return "QHD";
		}else{
			return "";
		}
	}

	public JSONArray QueryForOneXianquData(String xianquid) {
		JSONArray result = new JSONArray();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND REGION_ID=?";
		Object [] value = {xianquid}; 
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONObject QueryOneRegionData(String regionid) {
		JSONObject result = new JSONObject();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE REGION_ID=?";
		Object [] value = {regionid}; 
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				result.put("regionName", map.get("REGION_NAME"));
				result.put("regionId", map.get("REGION_Id"));
				result.put("regionParentId", map.get("PARENT_REGION_ID"));
				result.put("regionLvL", map.get("LVL_ID"));
			}
		}
		return result;
	}

	public JSONArray QueryxianquIdbydishiId(String dishiId) {

		JSONArray result = new JSONArray();
		String sql = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_REGION_INFO WHERE LVL_ID='3' AND PARENT_REGION_ID= ? order by REGION_ID";
		Object [] value ={dishiId};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_Id"));
				obj.put("regionParentId", map.get("PARENT_REGION_ID"));
				obj.put("regionLvL", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	
	}
}
