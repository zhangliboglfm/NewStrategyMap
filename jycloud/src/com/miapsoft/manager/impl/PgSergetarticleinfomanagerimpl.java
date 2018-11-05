package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PgSergetarticleinfomanager;

@Service("pgSergetarticleinfomanager")
public class PgSergetarticleinfomanagerimpl extends AbstractManager implements PgSergetarticleinfomanager {
	/*更多推荐文章*/
	public JSONArray getmorearticle(int page) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		int startindex=(page-1)*10;
		/*String sql= "SELECT "+
					"A.ARTICLE_ID, "+
					"A.ARTICLE_TITLE, "+
					"A.ARTICLE_COVER, "+
					"B.PHOTOG_ID ,C.PHOTOG_NAME "+
					"FROM TB_PHOTOG_ARTICLE_BASE_INFO A "+
					"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_RELAT B ON A.ARTICLE_ID = B.ARTICLE_ID "+
					"LEFT JOIN TB_PHOTOG_BASE_INFO C ON B.PHOTOG_ID = C.PHOTOG_ID "+
					"ORDER BY A.DEAL_TIME LIMIT ?,10";*/
		String sql= "select a.ARTICLE_ID,a.ARTICLE_TITLE,a.ARTICLE_COVER,c.PHOTOG_NAME,b.PHOTOG_ID " +
				"from TB_PHOTOG_ARTICLE_BASE_INFO a " +
				"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_RELAT b ON b.ARTICLE_ID =a.ARTICLE_ID " +
				"LEFT JOIN TB_PHOTOG_BASE_INFO c ON c.PHOTOG_ID =b.PHOTOG_ID " +
				"where a.ARTICLE_ID in (select MBODY_ID from TB_ADM_APP_HOME_CONF where MBODY_TYPE='A') " +
				"and ARTICLE_TYPE='TA' " +
				"ORDER BY a.DEAL_TIME LIMIT ?,10 ";
		Object[] value = {startindex};
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("articleid",map.get("ARTICLE_ID"));
				obj.put("articletitle",map.get("ARTICLE_TITLE"));
				obj.put("articlepic",map.get("ARTICLE_COVER")==null?"":map.get("ARTICLE_COVER"));
				obj.put("articlepgname",map.get("PHOTOG_NAME")==null?"":map.get("PHOTOG_NAME"));
				AreaSaturation.add(obj);
		}
	}
		return AreaSaturation;
	}
	
	/*文章封面下载接口*/
	public String getcoverphoto(String articleid) {
		// TODO Auto-generated method stub
		String sql="SELECT ARTICLE_URL FROM TB_PHOTOG_ARTICLE_BASE_INFO WHERE ARTICLE_ID=?";
		Object[] value = {articleid};
		String path="";
		List<Map<String, Object>> lisdata=this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
		Map<String,Object> map = lisdata.get(0);
		path=(String) map.get("ARTICLE_URL");
		}
		return path;
	}
	/*国籍摄影家数量接口*/
	public JSONArray getcoverphoto() {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql="SELECT B.COUNTRY_CHN_NAME,COUNT(B.COUNTRY_ID) AS COUNTNUM FROM TB_PHOTOG_BASE_INFO A LEFT JOIN TB_CDE_COUNTRY B ON A.NATION = B.COUNTRY_ID ORDER BY B.COUNTRY_ID";
		List<Map<String, Object>> lisdata=this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("countryname",map.get("COUNTRY_CHN_NAME"));
				obj.put("countnum",map.get("COUNTNUM"));
				AreaSaturation.add(obj);
		}
	}
		return AreaSaturation;
	}

	public int getregisterinfo(String loginid, String password, String qqnum,
			String vxnum, String wbnum) {
		String sql1="select count(*) from TB_SYS_USER_BASE_INFO where USER_ACCT_ID='"+loginid+"'";
		int result1=this.getJdbcTemplate().queryForInt(sql1);
		int result=0;
		if (result1==0) {
			String sql="insert into TB_SYS_USER_BASE_INFO " +
					"(USER_ACCT_ID,USER_PWD,QQ_ACCT_ID,MMSG_ACCT_ID,MBLOG_ACCT_ID,DEAL_TIME) " +
					"values('"+loginid+"','"+password+"','"+qqnum+"','"+vxnum+"','"+wbnum+"',SYSDATE())";
			result=this.getJdbcTemplate().update(sql);
		} else {
			result=0;
		}
		return result;
	}

	public int getlogininfo(String loginid, String password, String qqnum,
			String vxnum, String wbnum) {
		String sql="";
		if (loginid!="" && null != loginid ) {
			sql="select count(*) from TB_SYS_USER_BASE_INFO where USER_ACCT_ID='"+loginid+"' and USER_PWD='"+password+"' ";
		} else if (qqnum!="" && null != qqnum ){
			sql="select count(*) from TB_SYS_USER_BASE_INFO where QQ_ACCT_ID='"+qqnum+"' ";
		} else if (vxnum!="" && null != vxnum ){
			sql="select count(*) from TB_SYS_USER_BASE_INFO where MMSG_ACCT_ID='"+vxnum+"' ";
		} else if (wbnum!="" && null != wbnum ){
			sql="select count(*) from TB_SYS_USER_BASE_INFO where MBLOG_ACCT_ID='"+wbnum+"' ";
		}
		int result=this.getJdbcTemplate().queryForInt(sql);
		return result;
	}

}
