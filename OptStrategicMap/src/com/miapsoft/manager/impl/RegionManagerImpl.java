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
 * @time: 2018-3-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("regionmanager")
public class RegionManagerImpl extends AbstractManager implements RegionManager {

	public JSONArray getCity() {
		JSONArray result = new JSONArray();
		String sql = "select * from TB_STRATEGY_MAP_DIM_REGION WHERE LVL_ID = '2' ORDER BY REGION_ID";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map =list.get(i);
				JSONObject obj = new JSONObject();
				obj.put("regionid", map.get("REGION_ID"));
				obj.put("regionname", map.get("REGION_NAME"));
				obj.put("regionlvl", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray getCounty(String parentregionid) {
		JSONArray result = new JSONArray();
		String sql = "select * from TB_STRATEGY_MAP_DIM_REGION WHERE LVL_ID = '3' AND PARENT_REGION_ID = '"+parentregionid+"' ORDER BY REGION_ID";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map =list.get(i);
				JSONObject obj = new JSONObject();
				obj.put("regionid", map.get("REGION_ID"));
				obj.put("regionname", map.get("REGION_NAME"));
				obj.put("regionlvl", map.get("LVL_ID"));
				result.add(obj);
			}
		}
		return result;
	}

}
