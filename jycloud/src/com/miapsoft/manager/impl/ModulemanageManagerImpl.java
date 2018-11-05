package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.LoginManager;
import com.miapsoft.manager.ModulemanageManager;

@Service("modulemanageManager")
public class ModulemanageManagerImpl extends AbstractManager implements ModulemanageManager {

	public void editModule(String modulename, String modulelink, String flag) {
		String sql ="Update TB_SYS_ADMIN_MODULE SET MODULE_NAME=?,MODULE_URL=? Where MODULE_ID=?";
		Object[] value = {modulename,modulelink,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}
	public JSONArray getAllfenpeiModule() {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT MODULE_ID,PARENT_MODULE_ID,MODULE_NAME,MODULE_LVL,MODULE_URL,MODULE_TYPE,MODULE_ORDER FROM TB_SYS_ADMIN_MODULE";
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));

				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				/*		obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));*/
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	public void deleteModule(String moduleid) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID=?";
		Object[] value = {moduleid};
		int listdata = this.getJdbcTemplate().update(sql,value);
		String sql2 ="DELETE FROM TB_SYS_ROLE_MODULE_RELAT WHERE MODULE_ID=?";
		Object[] value2 = {moduleid};
		int listdata2 = this.getJdbcTemplate().update(sql2,value2);
	}

	public void addModule(String moduleid, String fathermoduleid,
			String modulename, String modulelevel, String modulelink,
			String ismenu, String moduleby) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO TB_SYS_ADMIN_MODULE VALUES(?,?,?,?,'',?,?,?,SYSDATE())";
		Object[] value = {moduleid,fathermoduleid,modulename,modulelevel,modulelink,ismenu,moduleby};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public JSONArray selectModule(String moduleid, String fathermoduleid,
			String modulename) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT MODULE_ID,PARENT_MODULE_ID,MODULE_NAME,MODULE_LVL,MODULE_URL,MODULE_TYPE,MODULE_ORDER " +
				"FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID LIKE '%"+moduleid+"%' AND PARENT_MODULE_ID LIKE '%"+fathermoduleid+"%' AND MODULE_NAME LIKE '%"+modulename+"%'";
		/*Object[] value = {moduleid,fathermoduleid,modulename};*/
		/*List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);*/
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public JSONArray getAllfenpeiModule(String flag) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT * FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID NOT IN(SELECT IFNULL(B.MODULE_ID,' ') FROM TB_SYS_ROLE_BASE_INFO A LEFT JOIN TB_SYS_ROLE_MODULE_RELAT B ON A.ROLE_ID=B.ROLE_ID WHERE A.ROLE_ID=?)";
		Object[] value = {flag};
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));
				obj.put("LAY_CHECKED",false);
				AreaSaturation.add(obj);
			}
		}
		String sql2 ="SELECT * FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID IN(SELECT IFNULL(B.MODULE_ID,' ') FROM TB_SYS_ROLE_BASE_INFO A LEFT JOIN TB_SYS_ROLE_MODULE_RELAT B ON A.ROLE_ID=B.ROLE_ID WHERE A.ROLE_ID=?)";
		Object[] value2 = {flag};
		List<Map<String, Object>> lisdata2 = this.getJdbcTemplate().queryForList(sql2,value2);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata2.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata2.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));
				obj.put("LAY_CHECKED",true);
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public JSONArray getAllweifenpeiModule(String flag) {
		// TODO Auto-generated method stub
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT * FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID IN(SELECT IFNULL(B.MODULE_ID,' ') FROM TB_SYS_ROLE_BASE_INFO A LEFT JOIN TB_SYS_ROLE_MODULE_RELAT B ON A.ROLE_ID=B.ROLE_ID WHERE A.ROLE_ID=?)";
		Object[] value = {flag};
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql,value);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}

	public void modulefenpei(String fatherid, String flag) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO TB_SYS_ROLE_MODULE_RELAT VALUES(?,?,SYSDATE());";
		Object[] value = {fatherid,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public void jiechumodulefenpei(String fatherid, String flag) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM TB_SYS_ROLE_MODULE_RELAT WHERE ROLE_ID=? AND MODULE_ID=?;";
		Object[] value = {fatherid,flag};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public void deleteselectmodule(String moduleid) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM TB_SYS_ROLE_MODULE_RELAT WHERE ROLE_ID=?";
		Object[] value = {moduleid};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}

	public void insertfenpeimodule(String roleid, String moduleid) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO TB_SYS_ROLE_MODULE_RELAT VALUES(?,?,SYSDATE());";
		Object[] value = {roleid,moduleid};
		int listdata = this.getJdbcTemplate().update(sql,value);
	}
	/**
	 * @author:张腾飞
	 * @date:2018-9-28
	 */
	//获取所有的模块信息
	public JSONArray getAllModule(String moduleid, String parentMId, String curnum, String limitcount, String modulename) {
		int beginNum = (Integer.parseInt(curnum)-1)*Integer.parseInt(limitcount);
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT MODULE_ID,PARENT_MODULE_ID,MODULE_NAME,MODULE_LVL,MODULE_URL,MODULE_TYPE,MODULE_ORDER FROM TB_SYS_ADMIN_MODULE " +
				"WHERE MODULE_ID LIKE '%"+moduleid+"%' AND PARENT_MODULE_ID LIKE '%"+parentMId+"%' AND MODULE_NAME LIKE '%"+modulename+"%' " +
				"ORDER BY MODULE_ID LIMIT "+beginNum+","+limitcount;
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleid",map.get("MODULE_ID"));
				obj.put("fathermoduleid",map.get("PARENT_MODULE_ID"));
				obj.put("modulename",map.get("MODULE_NAME"));
				obj.put("modulelevel",map.get("MODULE_LVL"));
				obj.put("modulelink",map.get("MODULE_URL"));
				obj.put("ismenu",map.get("MODULE_TYPE"));
				obj.put("moduleby",map.get("MODULE_ORDER"));
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	//查模块的总个数
	public String getPageNum(String moduleid, String parentMId, String modulename) {
		int pageNum=1;
		String sql ="SELECT COUNT(1) FROM TB_SYS_ADMIN_MODULE " +
				"WHERE MODULE_ID LIKE '%"+moduleid+"%' AND PARENT_MODULE_ID LIKE '%"+parentMId+"%' AND MODULE_NAME LIKE '%"+modulename+"%' ";
		pageNum = this.getJdbcTemplate().queryForInt(sql);
		return pageNum+"";
	}

}
