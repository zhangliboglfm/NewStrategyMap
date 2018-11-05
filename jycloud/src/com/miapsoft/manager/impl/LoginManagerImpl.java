package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.LoginManager;

@Service("loginManager")
public class LoginManagerImpl extends AbstractManager implements LoginManager {

	public JSONObject Login(String username) {
		// TODO Auto-generated method stub
		JSONObject user = new JSONObject();
	/*	String sql="SELECT USER_PWD FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID=?";*/
		String sql="select * from TB_SYS_USER_BASE_INFO where USER_ACCT_ID='"+username+"' ";
/*		Object [] value = {username};*/
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
	/*	List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,value);*/
		if(list.size()==1){
			user = JSONObject.fromObject(list.get(0));
		}else{
			user = null;
		}
		return user;
	}
	public int Login2(String username,String password) {
		// TODO Auto-generated method stub
		JSONObject user = new JSONObject();
	/*	String sql="SELECT USER_PWD FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID=?";*/
		String sql="select count(*) from TB_SYS_USER_BASE_INFO where USER_ACCT_ID='"+username+"' and USER_PWD='"+password+"' ";
		int flag = this.getJdbcTemplate().queryForInt(sql);
	/*	List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,value);*/
		return flag;
	}


}
