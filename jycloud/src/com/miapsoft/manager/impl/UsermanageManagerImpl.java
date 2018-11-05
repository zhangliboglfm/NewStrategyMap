package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.UsermanageManager;
import com.miapsoft.util.MD5Util;

@Service("usermanageManager")
public class UsermanageManagerImpl extends AbstractManager implements UsermanageManager {

	public JSONArray getAllUser(String userName, String roleId, String curnum, String limitcount) {
		JSONArray AreaSaturation = new JSONArray();
		String roleSql = "";
		if ("0".equals(roleId)) {
			roleSql = "";
		} else {
			roleSql = " AND C.ROLE_ID = '"+roleId+"'";
		}
		int beginNum = (Integer.parseInt(curnum)-1)*Integer.parseInt(limitcount);
		//String sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND A.USER_TYPE !='R_010'";
		String sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
				"FROM TB_SYS_USER_BASE_INFO A " +
				"LEFT JOIN TB_SYS_USER_ROLE_RELAT C ON A.USER_ACCT_ID = C.USER_ACCT_ID " +
				"LEFT JOIN TB_SYS_ROLE_BASE_INFO B ON B.ROLE_ID=C.ROLE_ID " +
				"WHERE A.USER_NAME LIKE '%"+userName+"%' AND A.USER_TYPE = '后台用户' ";
		sql+=roleSql;
		sql+="ORDER BY A.DEAL_TIME LIMIT "+beginNum+","+limitcount;
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("USER_ACCT_ID"));	
				obj.put("username",map.get("USER_NAME"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("phone",map.get("PHONE_NO"));
				obj.put("mail",map.get("EMAIL"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public void UpdatebackUser(String phone, String mail, String describe,
			String flag) {
		// TODO Auto-generated method stub
		String sql ="Update TB_SYS_USER_BASE_INFO SET PHONE_NO=?,EMAIL=? Where USER_ACCT_ID=?";
		String sql0 ="Update TB_SYS_USER_BASE_INFO SET PHONE_NO=?,EMAIL=?,APP_ID=? Where USER_ACCT_ID=?";
		Object[] value = {phone,mail,/*describe,*/flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
		/*	return listdata;*/
	}
	public void UpdateappUser(String phone, String mail,String QQ,String VX,String WB,String flag,String close) {
		// TODO Auto-generated method stub
		String sql ="Update TB_SYS_USER_BASE_INFO SET PHONE_NO=?,EMAIL=?,QQ_ACCT_ID=?,MMSG_ACCT_ID=?,MBLOG_ACCT_ID=? Where USER_ACCT_ID=?";
		String sql0 ="Update TB_SYS_USER_BASE_INFO SET PHONE_NO=?,EMAIL=?,QQ_ACCT_ID=?,MMSG_ACCT_ID=?,MBLOG_ACCT_ID=?,APP_ID=? Where USER_ACCT_ID=?";
		Object[] value = {phone,mail,QQ,VX,WB,/*close,*/flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
		/*	return listdata;*/
	}

	public void addbackUser(String username, String password,String name, String sfzzh,
			String phone, String mail, String QQ, String VX, String WB,String sex,String rolename) {
		// TODO Auto-generated method stub
		String sql0="select count(*) from TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID='"+username+"'";
		int count = this.getJdbcTemplate().queryForInt(sql0);
		if(count==0){
			password=MD5Util.newMD5(password).substring(0,128);
			String sql ="INSERT INTO TB_SYS_USER_BASE_INFO VALUES(?,?,?,?,'',?,?,?,?,?,?,?,SYSDATE());";
			Object[] value = {username,password,name,rolename,sex,sfzzh,phone,mail,QQ,VX,WB};
			int listdata = this.getJdbcTemplate().update(sql,value);
		}

	}
	
	public void deleteappUser(String flag) {
		String sql ="DELETE FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID=? AND USER_TYPE ='R_010'";
		Object[] value = {flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}


	public JSONArray getAllSelectUser(String condition, String roleId) {


		String selSql = "";
		if ("0".equals(roleId)) {
			selSql = "SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A " +
					"LEFT JOIN TB_SYS_ROLE_BASE_INFO B " +
					"ON A.USER_TYPE=B.ROLE_ID ";
		} else {
			selSql = "SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A " +
					"LEFT JOIN TB_SYS_ROLE_BASE_INFO B " +
					"ON A.USER_TYPE=B.ROLE_ID WHERE B.ROLE_ID = '"+roleId+"' ";
		}
		// TODO Auto-generated method stub
		/*String sql="";
		if(condition.equals("系统管理员")){
			condition="R_001";
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND B.ROLE_ID = '"+condition+"'";
		}else if(condition.equals("摄影家管理员")){
			condition="R_002";
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND B.ROLE_ID = '"+condition+"'";

		}else if(condition.equals("摄影家审核员")){
			condition="R_003";
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND B.ROLE_ID = '"+condition+"'";
		}else{
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC " +
					"FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND B.ROLE_ID!='R_010'";	
		}*/
		JSONArray AreaSaturation = new JSONArray();
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(selSql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("USER_ACCT_ID"));
				obj.put("username",map.get("USER_NAME"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("phone",map.get("PHONE_NO"));
				obj.put("mail",map.get("EMAIL"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	public JSONArray getAllSelectappUser(String username,String phone,String condition) {
		// TODO Auto-generated method stub
		String sql="";
		if(condition.equals("男")){
			condition="M";
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME" +
					",case when USER_GENDER='M' then '男' when USER_GENDER='F' then '女'end AS USER_GENDER,A.IDCARD_NO,A.PHONE_NO" +
					",A.EMAIL,A.QQ_ACCT_ID,A.MMSG_ACCT_ID,A.MBLOG_ACCT_ID " +
					"FROM TB_SYS_USER_BASE_INFO A " +
					"WHERE A.USER_TYPE='R_010' " +
					"AND A.USER_NAME like '%"+username+"%' AND A.PHONE_NO like '%"+phone+"%' AND A.USER_GENDER='"+condition+"'";
		}else if(condition.equals("女")){
			condition="F";
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME" +
					",case when USER_GENDER='M' then '男' when USER_GENDER='F' then '女'end AS USER_GENDER,A.IDCARD_NO,A.PHONE_NO" +
					",A.EMAIL,A.QQ_ACCT_ID,A.MMSG_ACCT_ID,A.MBLOG_ACCT_ID " +
					"FROM TB_SYS_USER_BASE_INFO A " +
					"WHERE A.USER_TYPE='R_010' " +
					"AND A.USER_NAME like '%"+username+"%' AND A.PHONE_NO like '%"+phone+"%' AND A.USER_GENDER='"+condition+"'";

		}else{
			sql ="SELECT A.USER_ACCT_ID,A.USER_NAME" +
					",case when USER_GENDER='M' then '男' when USER_GENDER='F' then '女'end AS USER_GENDER,A.IDCARD_NO,A.PHONE_NO" +
					",A.EMAIL,A.QQ_ACCT_ID,A.MMSG_ACCT_ID,A.MBLOG_ACCT_ID " +
					"FROM TB_SYS_USER_BASE_INFO A " +
					"WHERE A.USER_TYPE='R_010' " +
					"AND A.USER_NAME like '%"+username+"%' AND A.PHONE_NO like '%"+phone+"%'";

		}
		JSONArray AreaSaturation = new JSONArray();

		/*		Object[] value = {username,phone,condition};*/
		/*List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);*/
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("USER_ACCT_ID"));
				obj.put("username",map.get("USER_NAME"));
				obj.put("sex",map.get("USER_GENDER"));
				obj.put("sfzzh",map.get("IDCARD_NO"));
				obj.put("phone",map.get("PHONE_NO"));
				obj.put("mail",map.get("EMAIL"));
				obj.put("qqzh",map.get("QQ_ACCT_ID"));
				obj.put("wxzh",map.get("MBLOG_ACCT_ID"));
				obj.put("wbzh",map.get("MBLOG_ACCT_ID"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public void addappUser(String username, String password,String name, String sfzzh,
			String phone, String mail, String QQ, String VX, String WB,
			String sex) {
		// TODO Auto-generated method stub
		String sql0="select count(*) from TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID='"+username+"'";
		int count = this.getJdbcTemplate().queryForInt(sql0);
		if(count==0){
			password=MD5Util.newMD5(password).substring(0,128);
			String sql ="INSERT INTO TB_SYS_USER_BASE_INFO VALUES(?,?,?,'R_010','',?,?,?,?,?,?,?,SYSDATE());";
			Object[] value = {username,password,name,sex,sfzzh,phone,mail,QQ,VX,WB};
			int listdata = this.getJdbcTemplate().update(sql,value);
		}
	}
	public void rolefenpeifirst(String fatherid) {
		// TODO Auto-generated method stub
		String sql0 ="DELETE FROM TB_SYS_USER_ROLE_RELAT WHERE USER_ACCT_ID=?";
		Object[] value0 = {fatherid};
		int listdata0 = this.getJdbcTemplate().update(sql0,value0);
	}
	public void rolefenpei(String fatherid,String flag) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO TB_SYS_USER_ROLE_RELAT VALUES(?,?,SYSDATE());";
		Object[] value = {fatherid,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public void jiechurolefenpei(String fatherid, String flag) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM TB_SYS_USER_ROLE_RELAT WHERE USER_ACCT_ID=? AND ROLE_ID=?;";
		Object[] value = {fatherid,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}
	//取后台用户总条数
	public String getPageNumHT(String userName, String roleId, String curnum, String limitcount) {
		int pageNum=1;
		String roleSql = "";
		if ("0".equals(roleId)) {
			roleSql = "";
		} else {
			roleSql = " AND C.ROLE_ID = '"+roleId+"'";
		}
		//String sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,B.ROLE_NAME,A.PHONE_NO,A.EMAIL,B.ROLE_DESC FROM TB_SYS_USER_BASE_INFO A,TB_SYS_ROLE_BASE_INFO B WHERE A.USER_TYPE=B.ROLE_ID AND A.USER_TYPE !='R_010'";
		String sql ="SELECT COUNT(1) " +
				"FROM TB_SYS_USER_BASE_INFO A " +
				"LEFT JOIN TB_SYS_USER_ROLE_RELAT C ON A.USER_ACCT_ID = C.USER_ACCT_ID " +
				"LEFT JOIN TB_SYS_ROLE_BASE_INFO B ON B.ROLE_ID=C.ROLE_ID " +
				"WHERE A.USER_NAME LIKE '%"+userName+"%' AND A.USER_TYPE = '后台用户'";
		sql+=roleSql;
		pageNum = this.getJdbcTemplate().queryForInt(sql);
		return pageNum+"";
	}
	//取APP所有用户信息
	public JSONArray getAllAppUser(String userName, String phoneNum,
			String curnum, String limitcount, String userSex) {
		int beginNum = (Integer.parseInt(curnum)-1)*Integer.parseInt(limitcount);
		String sexSql = "0".equals(userSex)?"":" AND A.USER_GENDER = '"+userSex+"'";
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT A.USER_ACCT_ID,A.USER_NAME,A.USER_GENDER,A.IDCARD_NO,A.PHONE_NO,A.EMAIL,A.QQ_ACCT_ID,A.MMSG_ACCT_ID,A.MBLOG_ACCT_ID " +
				"FROM TB_SYS_USER_BASE_INFO A WHERE A.USER_NAME LIKE '%"+userName+"%' AND A.PHONE_NO LIKE '%"+phoneNum+"%' AND A.USER_TYPE = 'APP用户' ";
		sql+=sexSql;
		sql+=" ORDER BY A.DEAL_TIME LIMIT "+beginNum+","+limitcount;
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("USER_ACCT_ID"));
				obj.put("username",map.get("USER_NAME"));
				obj.put("sex","F".equals(map.get("USER_GENDER"))?"女":"男");
				obj.put("sfzzh",map.get("IDCARD_NO"));
				obj.put("phone",map.get("PHONE_NO"));
				obj.put("mail",map.get("EMAIL"));
				obj.put("qqzh",map.get("QQ_ACCT_ID"));
				obj.put("wxzh",map.get("MBLOG_ACCT_ID"));
				obj.put("wbzh",map.get("MBLOG_ACCT_ID"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	//取APP用户总条数
	public String getPageNumAPP(String userName, String phoneNum, String userSex) {
		String sexSql = "0".equals(userSex)?"":" AND A.USER_GENDER = '"+userSex+"'";
		int pageNum=1;
		String sql ="SELECT COUNT(1) FROM TB_SYS_USER_BASE_INFO A " +
				"WHERE A.USER_NAME LIKE '%"+userName+"%' AND A.PHONE_NO LIKE '%"+phoneNum+"%' AND A.USER_TYPE = 'APP用户'";
		sql+=sexSql;
		pageNum = this.getJdbcTemplate().queryForInt(sql);
		return pageNum+"";
	}
	//删除用户
	public void deletebackUser(String flag) {
		String sql ="DELETE FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID=?";
		Object[] value = {flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
		String sql2 ="DELETE FROM TB_SYS_USER_ROLE_RELAT WHERE USER_ACCT_ID=?";
		Object[] value2 = {flag};
		int listdata2 = this.getJdbcTemplate().update(sql2,value2);
	}

}
