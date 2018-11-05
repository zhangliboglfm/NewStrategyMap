package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.MainManager;

/**
 * <p>Title: MainManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("mainManager")
public class MainManagerImpl extends AbstractManager implements MainManager {

	public String getMaxDate() {
		String result = "";
		String sql = "select MAX(STATIS_DATE) AS MAXDATE from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_DATE_INFO ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			result = list.get(0).get("MAXDATE").toString();
		}
		return result;
	}

	public String getMinDate() {
		String result = "";
		String sql = "select MIN(STATIS_DATE) AS MINDATE from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_DATE_INFO ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			result = list.get(0).get("MINDATE").toString();
		}
		return result;
	}
	
	public JSONArray QueryForDishiData() {
		JSONArray result = new JSONArray();
		String sql = "select * from bass_data.TB_CHNL_PRODUCT_CHANGE_AREA_INFO where order_id <13 order by ORDER_ID";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(i);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_ID"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray QueryForOneDiShi(String RegionId) {
		
		JSONArray result = new JSONArray();
		String sql = "select * from bass_data.TB_CHNL_PRODUCT_CHANGE_AREA_INFO_QX where REGION_ID = ? ";
		Object [] value={RegionId};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
				JSONObject obj = new JSONObject();
				Map<String, Object> map = listdata.get(0);
				obj.put("regionName", map.get("REGION_NAME"));
				obj.put("regionId", map.get("REGION_ID_T"));
				result.add(obj);
		}
		return result;
	}
	
}
