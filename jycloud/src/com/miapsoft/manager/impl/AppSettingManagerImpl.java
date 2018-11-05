package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.AppSettingManager;

@Service("AppSettingManager")
public class AppSettingManagerImpl extends AbstractManager implements AppSettingManager{
	//查询点击日期的安排
	public JSONObject getlistbydate(String date, String appId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家ID
		JSONArray array2 = new JSONArray();//摄影家Name
		JSONArray array3 = new JSONArray();//文章ID
		JSONArray array4 = new JSONArray();//文章Name
		String photogSql="SELECT  A.MBODY_ID,A.SHOW_ORDER,B.PHOTOG_NAME FROM TB_ADM_APP_HOME_CONF A "+
					"LEFT JOIN TB_PHOTOG_BASE_INFO B "+
					"ON A.MBODY_ID = B.PHOTOG_ID "+
					"WHERE A.SHOW_DATE = '"+date+"' AND A.MBODY_TYPE = 'P' AND A.APP_ID ='"+appId+"'"+
					"ORDER BY A.SHOW_ORDER ";
		String artSql="SELECT  A.MBODY_ID,A.SHOW_ORDER,B.ARTICLE_TITLE FROM TB_ADM_APP_HOME_CONF A "+
						"LEFT JOIN TB_PHOTOG_ARTICLE_BASE_INFO B  "+
						"ON A.MBODY_ID = B.ARTICLE_ID "+
						"WHERE A.SHOW_DATE = '"+date+"' AND A.MBODY_TYPE = 'A' AND A.APP_ID ='"+appId+"' "+
						"ORDER BY A.SHOW_ORDER ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(photogSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("MBODY_ID"));
			array2.add(list1.get(i).get("PHOTOG_NAME"));
		}
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(artSql);
		for (int i = 0; i < list2.size(); i++) {
			array3.add(list2.get(i).get("MBODY_ID"));
			array4.add(list2.get(i).get("ARTICLE_TITLE"));
		}
		result.put("pgID", array1);
		result.put("pgName", array2);
		result.put("artId", array3);
		result.put("artName", array4);
		return result;
	}
	//查备选的摄影家列表
	public String getAppPgList(String pgName, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select a.PHOTOG_ID,a.PHOTOG_NAME,a.SHOW_ORDER from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE '"+pgName+
						"'  ORDER BY a.SHOW_ORDER LIMIT "+start+","+end+" ";
		String sql1="select count(1) from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE '"+pgName+"'";
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("PHOTOG_ID"));
				obj.put("name",map.get("PHOTOG_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				array.add(obj);
			}
		}
		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object.toString();
	}
	//查备选的文章列表
	public String getAppArtList(String pgName, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select ARTICLE_ID,ARTICLE_TITLE,ARTICLE_COVER from TB_PHOTOG_ARTICLE_BASE_INFO where ARTICLE_TITLE LIKE '"+pgName+
						"' AND ARTICLE_TYPE = 'TA' ORDER BY DEAL_TIME LIMIT "+start+","+end+" ";
		String sql1="select count(1) from TB_PHOTOG_ARTICLE_BASE_INFO where ARTICLE_TITLE LIKE '"+pgName+"' AND ARTICLE_TYPE = 'TA' ";
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("ARTICLE_ID"));
				obj.put("name",map.get("ARTICLE_TITLE"));
				obj.put("cover", map.get("ARTICLE_COVER"));
				array.add(obj);
			}
		}
		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object.toString();
	}
	//重新上传设置
	public String sureOper(String date, String appId, String phgList, String artList) {
		//["P_M8400002","P_M8400005","P_M760004"]
		JSONArray pgIdArr=null;
		JSONArray artIdArr=null;
		if (phgList!=null&&!"".equals(phgList)) {
			pgIdArr=JSONArray.fromObject(phgList);
			String deleteSql="DELETE FROM TB_ADM_APP_HOME_CONF WHERE APP_ID ='"+appId+"' AND MBODY_TYPE = 'P' AND SHOW_DATE = '"+date+"'";
			this.getJdbcTemplate().update(deleteSql);
		}
		if (artList!=null&&!"".equals(artList)) {
			artIdArr=JSONArray.fromObject(artList);
			String deleteSql="DELETE FROM TB_ADM_APP_HOME_CONF WHERE APP_ID ='"+appId+"' AND MBODY_TYPE = 'A' AND SHOW_DATE = '"+date+"'";
			this.getJdbcTemplate().update(deleteSql);
		}
		String insertPgSql="INSERT INTO TB_ADM_APP_HOME_CONF (APP_ID,MBODY_TYPE,MBODY_ID,SHOW_ORDER,SHOW_DATE,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		for (int i = 0; i < pgIdArr.size(); i++) {
			Object [] values={appId,"P",pgIdArr.get(i),i+1,date};
			this.getJdbcTemplate().update(insertPgSql,values);
		}
		String insertArtSql="INSERT INTO TB_ADM_APP_HOME_CONF (APP_ID,MBODY_TYPE,MBODY_ID,SHOW_ORDER,SHOW_DATE,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		for (int i = 0; i < artIdArr.size(); i++) {
			Object [] values={appId,"A",artIdArr.get(i),i+1,date};
			this.getJdbcTemplate().update(insertArtSql,values);
		}
		return "1";
	}
	//生成日历上的项
	public JSONObject makeCalendar(String appId, String start, String end) {
		start=start.replace("-", "");
		end=end.replace("-", "");
		JSONObject object = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家名称
		JSONArray array2 = new JSONArray();//展示日期
		JSONArray array3 = new JSONArray();//文章名
		JSONArray array4 = new JSONArray();//展示日期
		JSONArray array5 = new JSONArray();//摄影家Order
		JSONArray array6 = new JSONArray();//文章Order
		String phgSql="SELECT  A.SHOW_DATE,A.MBODY_ID,A.SHOW_ORDER,B.PHOTOG_NAME "+
						"FROM TB_ADM_APP_HOME_CONF A "+
						"LEFT JOIN TB_PHOTOG_BASE_INFO B "+
						"ON A.MBODY_ID = B.PHOTOG_ID "+
						"WHERE A.SHOW_DATE  BETWEEN '"+start+"' AND '"+end+"' "+
						 "AND A.MBODY_TYPE = 'P' "+
						"AND A.APP_ID ='"+appId+"'"+
						"ORDER BY A.SHOW_DATE,A.SHOW_ORDER ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(phgSql);
		for (int i = 0; i < list.size(); i++) {
			array1.add(list.get(i).get("PHOTOG_NAME"));
			array2.add(list.get(i).get("SHOW_DATE"));
			array5.add(list.get(i).get("SHOW_ORDER"));
		}
		String artSql="SELECT  A.SHOW_DATE,A.MBODY_ID,A.SHOW_ORDER,B.ARTICLE_TITLE "+
				"FROM TB_ADM_APP_HOME_CONF A "+
				"LEFT JOIN TB_PHOTOG_ARTICLE_BASE_INFO B "+
				"ON A.MBODY_ID = B.ARTICLE_ID "+
				"WHERE A.SHOW_DATE  BETWEEN '"+start+"' AND '"+end+"' "+
				 "AND A.MBODY_TYPE = 'A' "+
				"AND A.APP_ID ='"+appId+"'"+
				"ORDER BY A.SHOW_DATE,A.SHOW_ORDER ";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(artSql);
		for (int i = 0; i < list2.size(); i++) {
			array3.add(list2.get(i).get("ARTICLE_TITLE"));
			array4.add(list2.get(i).get("SHOW_DATE"));
			array6.add(list2.get(i).get("SHOW_ORDER"));
		}
		object.put("photogName", array1);
		object.put("showDateP", array2);
		object.put("artTitle", array3);
		object.put("showDateA", array4);
		object.put("showOrderP", array5);
		object.put("showOrderA", array6);
		return object;
	}
	//查app列表
	public String getAppList(String pgName, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select APP_ID,APP_NAME,APP_ICON from TB_ADM_APP_BASE_INFO where APP_NAME LIKE '"+pgName+
						"' and IS_USING = '1' ORDER BY DEAL_TIME LIMIT "+start+","+end+" ";
		String sql1="select count(1) from TB_ADM_APP_BASE_INFO where APP_NAME LIKE '"+pgName+"' and IS_USING = '1' ";
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("APP_ID"));
				obj.put("name",map.get("APP_NAME"));
				obj.put("appIcon", map.get("APP_ICON"));
				array.add(obj);
			}
		}
		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object.toString();
	}
	
}
