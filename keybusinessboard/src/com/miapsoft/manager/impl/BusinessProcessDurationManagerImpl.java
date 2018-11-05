package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ibm.db2.jcc.a.d;
import com.ibm.db2.jcc.b.k;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.BusinessProcessDurationManager;

/**
 * <p>Title: BusinessProcessDurationManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Service("businessProcessDurationManager")
public class BusinessProcessDurationManagerImpl extends AbstractManager implements BusinessProcessDurationManager {

	public JSONObject businessprocessduration(String date) {
		JSONObject result= new JSONObject();
		JSONArray regionname=new JSONArray();
		JSONArray regionId= new JSONArray();
		String sql1= "select REGION_ID ,REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID desc";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		if(list1.size()!=0){
			for(int i=0;i<list1.size();i++){
				Map<String, Object> map=list1.get(i);
				regionname.add(map.get("REGION_NAME"));
				regionId.add(map.get("REGION_ID"));
			}
		}
		result.put("regionname",regionname);
		JSONArray stoptime= new JSONArray();
		JSONArray accetime=new JSONArray();
		JSONArray waittime=new JSONArray();
		if(regionId.size()!=0){
			String sql2="select STOP_TIME,ACCE_TIME,WAIT_TIME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_TIME_INFO where PARENT_CPC_ID ='ALL' and REGION_ID= ? and STATIS_DATE= ? ";
			for(int j=0;j<regionId.size();j++){
				Object [] value = {regionId.get(j).toString(),date};
				List<Map<String, Object>> list2= this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					Map<String, Object> map2=list2.get(0);
					stoptime.add(map2.get("STOP_TIME"));
					accetime.add(map2.get("ACCE_TIME"));
					waittime.add(map2.get("WAIT_TIME"));
				}
			}
		}
		
		result.put("stoptime", stoptime);
		result.put("accetime", accetime);
		result.put("waittime", waittime);
		
		return result;
	}

	public JSONArray processstimeconsumedifference(String date) {
		JSONArray result= new JSONArray();
		JSONArray regionId= new JSONArray();
		JSONArray regionname=new JSONArray();
		String sql1= "select REGION_ID ,REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		if(list1.size()!=0){
			for(int i=0;i<list1.size();i++){
				Map<String, Object> map=list1.get(i);
				regionId.add(map.get("REGION_ID"));
				regionname.add(map.get("REGION_NAME"));
			}
		}
		
		if(regionId.size()!=0){
			String sql2="select ACCE_TIME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_TIME_INFO where PARENT_CPC_ID !='ALL' and REGION_ID= ? and STATIS_DATE= ?  ORDER BY PARENT_CPC_ID";
			for (int j=0;j<regionId.size();j++){
				JSONArray data=new JSONArray();
				data.add(regionname.get(j).toString());
				Object [] value={regionId.get(j).toString(),date};
				List<Map<String, Object>> list2= this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					for(int k=0;k<list2.size();k++){
						Map<String, Object> map2=list2.get(k);
						data.add(map2.get("ACCE_TIME"));
					}
				}
				result.add(data);
			}
		}
		return result;
	}
	
	
}
