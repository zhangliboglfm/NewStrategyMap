package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface UsermanageManager {
	
	public void UpdatebackUser(String str1,String str2,String str3,String str4);
	public void UpdateappUser(String phone,String mail,String QQ,String VX,String WB,String flag,String close);
	public void addbackUser(String username,String password,String name,String sfzzh,String phone,String mail,String QQ,String VX,String WB,String sex,String rolename);
	
	public void deleteappUser(String flag);
	public JSONArray getAllSelectUser(String condition, String roleId);
	public JSONArray getAllSelectappUser(String username,String phone,String condition);
	
	public void addappUser(String username,String password,String name,String sfzzh,String phone,String mail,String QQ,String VX,String WB,String sex);
	public void rolefenpei(String fatherid,String flag);
	public void rolefenpeifirst(String fatherid);
	//取后台所有用户信息
	public JSONArray getAllUser(String userName, String roleId, String curnum, String limitcount);
	//取后台用户总条数
	public String getPageNumHT(String userName, String roleId, String curnum, String limitcount);
	//取APP所有用户信息
	public JSONArray getAllAppUser(String userName, String phoneNum, String curnum, String limitcount, String userSex);
	//取APP用户总条数
	public String getPageNumAPP(String userName, String phoneNum, String userSex);
	//批量删除用户
	public void deletebackUser(String flag);
}
