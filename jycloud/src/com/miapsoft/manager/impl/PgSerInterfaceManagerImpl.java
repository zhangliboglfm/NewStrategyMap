package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PgSerInterfaceManager;

@Service("pgSerInterfaceManager")
public class PgSerInterfaceManagerImpl extends AbstractManager implements PgSerInterfaceManager{
	public JSONArray getPhotogOrder(String receiveTime) {
		receiveTime=receiveTime.substring(0, 4)+"-"+receiveTime.substring(4, 6)+"-"+receiveTime.substring(6, 8);
/*		String sql="select PHOTOG_ID,SHOW_ORDER,PHOTOG_NAME from TB_PHOTOG_BASE_INFO where DEAL_TIME like '"+receiveTime+"%' order by SHOW_ORDER";
*/		String sql2="select B.PHOTOG_ID,A.SHOW_ORDER,B.PHOTOG_NAME from TB_ADM_APP_HOME_CONF A LEFT JOIN TB_PHOTOG_BASE_INFO B ON A.MBODY_ID=B.PHOTOG_ID where A.SHOW_DATE like '"+receiveTime+"%' order by A.SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql2);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public JSONArray addPhotog(String receiveTime) {
		receiveTime=receiveTime.substring(0, 4)+"-"+receiveTime.substring(4, 6)+"-"+receiveTime.substring(6, 8);
		String sql="select a.PHOTOG_ID,a.PHOTOG_NAME,a.YEARS,a.SHOW_ORDER,b.COUNTRY_CHN_NAME " +
				"from TB_PHOTOG_BASE_INFO a left join TB_CDE_COUNTRY b " +
				"on b.COUNTRY_ID=a.NATION " +
				"where substr(a.DEAL_TIME,1,10)>='"+receiveTime+"' " +
				"order by SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public JSONArray addPhotogWorks(String photogId) {
		String sql="select WORKS_ID,WORKS_NAME from TB_PHOTOG_WORKS_BASE_INFO where IS_REPRE_WORKS='1' and PHOTOG_ID='"+photogId+"' order by SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public String worksPictures(String worksId) {
		String sql="select FILE_NAME from TB_PHOTOG_WORKS_BASE_INFO where IS_REPRE_WORKS='1' and WORKS_ID='"+worksId+"'";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		String result="";
		if (list.size()!=0) {
			Map<String, Object> map=list.get(0);
			result=map.get("FILE_NAME").toString();
		} else {
			result="false";
		}
		return result;
	}

	public JSONArray recomArticles(String receiveTime) {
		receiveTime=receiveTime.substring(0, 4)+"-"+receiveTime.substring(4, 6)+"-"+receiveTime.substring(6, 8);
/*		String sql="select ARTICLE_ID,ARTICLE_TITLE from TB_PHOTOG_ARTICLE_BASE_INFO where DEAL_TIME like '"+receiveTime+"%' order by DEAL_TIME";
*/		String sql0="select B.ARTICLE_ID,B.ARTICLE_TITLE from TB_ADM_APP_HOME_CONF A LEFT JOIN TB_PHOTOG_ARTICLE_BASE_INFO B ON A.MBODY_ID=B.ARTICLE_ID where A.DEAL_TIME like '"+receiveTime+"%' and A.MBODY_TYPE='A' order by A.SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql0);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public JSONArray getphotogHead(String photogId) {
		String sql="select FILE_NAME from TB_PHOTOG_PIC_INFO where PHOTOG_ID ='"+photogId+"' and PIC_TYPE='头像' and SHOW_FLAG ='1' ORDER BY SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public JSONArray getfuzzyQuery(String photoName) {
		String sql="select a.PHOTOG_ID,a.PHOTOG_NAME,b.FILE_NAME " +
				"from TB_PHOTOG_BASE_INFO a " +
				"left join TB_PHOTOG_PIC_INFO b " +
				"on b.PHOTOG_ID=a.PHOTOG_ID " +
				"where a.PHOTOG_NAME like '%"+photoName+"%' and b.PIC_TYPE='头像' and b.SHOW_FLAG='1'";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public int collectiones(String STOREMBODYTYPE, String APPID,
			String USERACCTID) {
		String sql="";
		if ("P".equals(STOREMBODYTYPE)) {
			sql="insert into TB_ADM_STORE_INFO values ('"+APPID+"','摄影家','"+STOREMBODYTYPE+"','"+USERACCTID+"',SYSDATE())";
		} else if ("W".equals(STOREMBODYTYPE)) {
			sql="insert into TB_ADM_STORE_INFO values ('"+APPID+"','作品','"+STOREMBODYTYPE+"','"+USERACCTID+"',SYSDATE())";
		} else if ("A".equals(STOREMBODYTYPE)) {
			sql="insert into TB_ADM_STORE_INFO values ('"+APPID+"','文章','"+STOREMBODYTYPE+"','"+USERACCTID+"',SYSDATE())";
		} else if ("F".equals(STOREMBODYTYPE)) {
			sql="insert into TB_ADM_STORE_INFO values ('"+APPID+"','胶片样片','"+STOREMBODYTYPE+"','"+USERACCTID+"',SYSDATE())";
		} else if ("D".equals(STOREMBODYTYPE)) {
			sql="insert into TB_ADM_STORE_INFO values ('"+APPID+"','数码样片','"+STOREMBODYTYPE+"','"+USERACCTID+"',SYSDATE())";
		}
		int result=this.getJdbcTemplate().update(sql);
		return result;
	}

	public JSONObject getliStAcDatas(String photoId, String classify) {
		String sql="select ARTICLE_PIC from TB_PHOTOG_ARTICLE_BASE_INFO " +
				"where ARTICLE_TYPE='"+classify+"' and ARTICLE_ID in " +
				"(select ARTICLE_ID from TB_CDE_PHOTOG_ARTICLE_RELAT where PHOTOG_ID='"+photoId+"') ";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONObject result=JSONObject.fromObject(list.get(0));
		return result;
	}

	public JSONArray getphotogWorks(String photoId) {
		String sql="select WORKS_ID,WORKS_NAME,SHOW_ORDER,FILE_NAME from TB_PHOTOG_WORKS_BASE_INFO where PHOTOG_ID=? order by SHOW_ORDER";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,photoId);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public JSONArray getworksImg(String workId) {
		String sql="select FILE_NAME from TB_PHOTOG_WORKS_BASE_INFO where WORKS_ID='"+workId+"'";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	public String getworksRead(String workId) {
		String sql="select WORKS_INTRO from TB_PHOTOG_WORKS_BASE_INFO where WORKS_ID='"+workId+"'";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		String WORKSINTRO="";
		if (list.size()!=0) {
			Map<String, Object> map=list.get(0);
			WORKSINTRO=map.get("WORKS_INTRO").toString();
		} else {
			WORKSINTRO="";
		}
		return WORKSINTRO;
	}

	public JSONObject getarticleRead(String articleId) {
		String sql="select ARTICLE_TITLE,ARTICLE_PIC from TB_PHOTOG_ARTICLE_BASE_INFO where ARTICLE_ID=?";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,articleId);
			Map<String, Object> map=list.get(0);
		JSONObject result=JSONObject.fromObject(map);
		return result;
	}

	public JSONArray getrelateWorks(String workId) {
		String sql="select WORKS_ID,WORKS_NAME,SHOW_ORDER from TB_PHOTOG_WORKS_BASE_INFO where WORKS_ID='"+workId+"' order by SHOW_ORDER ";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}
}
