package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.AddRelationIndexManager;

/**
 * <p>Title: AddRelationIndexManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("addRelationIndexManager")
public class AddRelationIndexManagerImpl extends AbstractManager implements AddRelationIndexManager {

	public JSONArray queryForIndex(String keystr,String querytype,String datetype,String indexid,String indexlvl,String userId) {
		JSONArray result=new JSONArray();
		String sql = "";
		Object [] value = null;
		if("".equals(keystr)){
			sql = "SELECT  a.MM_ID,a.MM_NAME,a.MM_BIG_TYPE_NAME,a.MM_SMALL_TYPE_NAME,a.MM_DESC, " +
					"case when b.user_id = 'SYSTEM' THEN '1' WHEN B.USER_ID = '"+userId+"' THEN '2' ELSE '3' end as REL_TYPE "+
					"from bass_data.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
					"left join bass_data.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO b "+
					"on a.mm_id = b.mm_id and b.PARENT_MM_ID = ? and b.DATE_TYPE = ? "+
					"AND USER_ID IN ('SYSTEM','"+userId+"')  "+
					"where a.DATE_TYPE = ?  and a.MM_LEVEL = '"+indexlvl+"' and a.MM_ID != '"+indexid+"'  " +
					"order by REL_TYPE,mm_id";
			Object [] temp = {indexid,datetype,datetype};
			value = temp;
		}else{
			StringBuffer sbf = new StringBuffer("%");
			sbf.append(keystr.toUpperCase()).append("%");
			String key=sbf.toString();
			if("type".equals(querytype)){
				sql = "SELECT  a.MM_ID,a.MM_NAME,a.MM_BIG_TYPE_NAME,a.MM_SMALL_TYPE_NAME,a.MM_DESC, " +
						"case when b.user_id = 'SYSTEM' THEN '1' WHEN B.USER_ID = '"+userId+"' THEN '2' ELSE '3' end as REL_TYPE "+
						"from bass_data.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
						"left join bass_data.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO b "+
						"on a.mm_id = b.mm_id and b.PARENT_MM_ID = ? and b.DATE_TYPE = ? "+
						"AND USER_ID IN ('SYSTEM','"+userId+"')  "+
						"where a.DATE_TYPE = ?  and a.MM_LEVEL = '"+indexlvl+"' AND (A.MM_BIG_TYPE_NAME LIKE ? or A.MM_SMALL_TYPE_NAME like ?) and a.MM_ID != '"+indexid+"'  " +
						"order by REL_TYPE,mm_id";
				Object [] temp = {indexid,datetype,datetype,key,key};
				value = temp;
			}else if("key".equals(querytype)){
				sql = "SELECT  a.MM_ID,a.MM_NAME,a.MM_BIG_TYPE_NAME,a.MM_SMALL_TYPE_NAME,a.MM_DESC," +
						"case when b.user_id = 'SYSTEM' THEN '1' WHEN B.USER_ID = '"+userId+"' THEN '2' ELSE '3' end as REL_TYPE "+
						"from bass_data.TB_STRATEGY_MAP_DIM_MEASUR_INFO a "+
						"left join bass_data.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO b "+
						"on a.mm_id = b.mm_id and b.PARENT_MM_ID = ? and b.DATE_TYPE = ? "+
						"AND USER_ID IN ('SYSTEM','"+userId+"')  "+
						"where a.DATE_TYPE = ?  and a.MM_LEVEL = '"+indexlvl+"' AND A.MM_NAME LIKE ?  and a.MM_ID != '"+indexid+"' order by REL_TYPE,mm_id";
				Object [] temp = {indexid,datetype,datetype,key};
				value = temp;
			}else{
				return result;
			}
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
				obj.put("indexRelType", map.get("REL_TYPE"));
				result.add(obj);
			}
		}
		return result;
	}

	public String updateIndexRelationState(String thisindexid, String datetype,String indexid, String userId,String updatetype) throws Exception{
		String result = "";
		if("UPDATEREL".equals(updatetype.toUpperCase())){
			String sql_checked = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO WHERE PARENT_MM_ID =? AND MM_ID = ? AND DATE_TYPE = ? and USER_ID = ?";
			Object [] value={indexid,thisindexid,datetype,userId};
			List<Map<String, Object>> list_check=this.getJdbcTemplate().queryForList(sql_checked, value);
			if(list_check.size()!=0){
				if("SYSTEM".equals(list_check.get(0).get("USER_ID").toString().toUpperCase())){
					result="cannot";
				}else{
					String sql_del = "delete from BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO where PARENT_MM_ID =? AND MM_ID = ? AND DATE_TYPE = ? and USER_ID = ?";
					Object [] value_del={indexid,thisindexid,datetype,userId};
					int rint = this.getJdbcTemplate().update(sql_del, value_del);
					if(rint==1){
						result="success";
					}
				}
			}else{
				result="nohave";
			}
		}else if("ADDREL".equals(updatetype.toUpperCase())){
			String sql_checked = "select * from BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO WHERE PARENT_MM_ID =? AND MM_ID = ? AND DATE_TYPE = ? and USER_ID = ?";
			Object [] value={indexid,thisindexid,datetype,userId};
			List<Map<String, Object>> list_check=this.getJdbcTemplate().queryForList(sql_checked, value);
			if(list_check.size()!=0){
				result="have";
			}else{
				String sql_add = "insert into BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO (PARENT_MM_ID,MM_ID,DATE_TYPE,USER_ID) values (?,?,?,?)";
				Object [] value_add={indexid,thisindexid,datetype,userId};
				int rint = this.getJdbcTemplate().update(sql_add, value_add);
				if(rint==1){
					result="success";
				}
			}
		}
		return result;
	}

}
