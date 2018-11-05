package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.RolemanageManager;

@Service("rolemanageManager")
public class RolemanageManagerImpl extends AbstractManager implements RolemanageManager {

	public void editRole(String roleid, String flag) {
		// TODO Auto-generated method stub
		String sql ="Update TB_SYS_ROLE_BASE_INFO SET ROLE_DESC=? Where ROLE_ID=?";
		Object[] value = {roleid,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}
	//查所有的角色信息
	public JSONArray getAllRole(String roleId, String roleName, String curnum, String limitcount) {
		int beginNum = (Integer.parseInt(curnum)-1)*Integer.parseInt(limitcount);
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT ROLE_ID,ROLE_NAME,ROLE_DESC FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID LIKE '%"+roleId+"%' " +
				"AND ROLE_NAME LIKE '%"+roleName+"%' ORDER BY ROLE_ID LIMIT "+beginNum+","+limitcount;
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("roleid",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	//删除角色信息
	public void deleteRole(String roleid) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID=?";
		Object[] value = {roleid};
		int listdata = this.getJdbcTemplate().update(sql,value);
		String sql2 ="DELETE FROM TB_SYS_USER_ROLE_RELAT WHERE ROLE_ID=?";
		Object[] value2 = {roleid};
		int listdata2 = this.getJdbcTemplate().update(sql2,value2);
		String sql3 ="DELETE FROM TB_SYS_ROLE_MODULE_RELAT WHERE ROLE_ID=?";
		Object[] value3 = {roleid};
		int listdata23= this.getJdbcTemplate().update(sql3,value3);
	}

	public void addRole(String roleid, String rolename, String describe) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO TB_SYS_ROLE_BASE_INFO VALUES(?,?,?,'WMH',SYSDATE())";
		Object[] value = {roleid,rolename,describe};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public JSONArray selectRole(String roleid, String rolename) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT ROLE_ID,ROLE_NAME,ROLE_DESC FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID like '%"+roleid+"%' AND ROLE_NAME like '%"+rolename+"%'";
		/*Object[] value = {roleid,rolename};*/
		/*List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);*/
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public JSONArray getfenpeiRole(String flag) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT * FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID IN (SELECT IFNULL(B.ROLE_ID,' ') FROM TB_SYS_USER_BASE_INFO A LEFT JOIN TB_SYS_USER_ROLE_RELAT B ON A.USER_NAME=B.USER_ACCT_ID WHERE A.USER_NAME=?)";
		Object[] value = {flag};
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public JSONArray getweifenpeiRole(String flag) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT * FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID NOT IN (SELECT IFNULL(B.ROLE_ID,' ') FROM TB_SYS_USER_BASE_INFO A LEFT JOIN TB_SYS_USER_ROLE_RELAT B ON A.USER_ACCT_ID=B.USER_ACCT_ID WHERE A.USER_ACCT_ID=?)";
		Object[] value = {flag};
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				obj.put("LAY_CHECKED",false);
				AreaSaturation.add(obj);
			}
		}
		String sql2 ="SELECT * FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID IN (SELECT IFNULL(B.ROLE_ID,' ') FROM TB_SYS_USER_BASE_INFO A LEFT JOIN TB_SYS_USER_ROLE_RELAT B ON A.USER_ACCT_ID=B.USER_ACCT_ID WHERE A.USER_ACCT_ID=?)";
		Object[] value2 = {flag};
		List<Map<String, Object>> lisdata2 = this.getJdbcTemplate().queryForList(sql2,value2);
		if(lisdata2.size()!=0){
			for(int k=0;k<lisdata2.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata2.get(k);
				obj.put("id",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				obj.put("LAY_CHECKED",true);
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public JSONArray getAllBackRole() {
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT ROLE_ID,ROLE_NAME,ROLE_DESC FROM TB_SYS_ROLE_BASE_INFO";
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("id",map.get("ROLE_ID"));
				obj.put("rolename",map.get("ROLE_NAME"));
				obj.put("describe",map.get("ROLE_DESC"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	//查角色个数
	public String getRoleTNum(String roleId, String roleName) {
		String sql ="SELECT COUNT(1) FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID LIKE '%"+roleId+"%' AND ROLE_NAME LIKE '%"+roleName+"%' ORDER BY ROLE_ID";
		int roleTNum = this.getJdbcTemplate().queryForInt(sql);
		return roleTNum+"";
	}

}
