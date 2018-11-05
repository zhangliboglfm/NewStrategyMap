package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface RolemanageManager {
	public void editRole(String roleid,String flag);
	
	public JSONArray getAllBackRole();
	public JSONArray getfenpeiRole(String flag);
	public JSONArray getweifenpeiRole(String flag);
	public void deleteRole(String roleid);
	public void addRole(String roleid,String rolename,String describe);
	public JSONArray selectRole(String roleid,String rolename);
	//查所有的角色信息
	public JSONArray getAllRole(String roleId, String roleName, String curnum, String limitcount);
	//查角色的个数
	public String getRoleTNum(String roleId, String roleName);
}
