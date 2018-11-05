/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.SystemLayerManager;

@Service
public class SystemLayerManagerImpl extends AbstractManager implements SystemLayerManager {
	//查现在的基本信息并展示
	public JSONObject searchBaseInfo(String accUserId) {
		JSONObject result = new JSONObject();
		String selSql = "SELECT * FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID ='"+accUserId+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}
	//更新基本信息
	public String reupBaseInfo(String accountId, String nickName, String accType, String accountSex, 
			String idCard, String userPhone, String userEmail, String userQQ, String userWechat, String userWeibo) {
		accountSex = "男".equals(accountSex.trim())?"M":"F";
		String updateSql = "UPDATE TB_SYS_USER_BASE_INFO SET USER_NAME =? , USER_TYPE = ?, USER_GENDER=?, IDCARD_NO = ? , PHONE_NO = ? , EMAIL = ? , QQ_ACCT_ID = ? "+
							", MMSG_ACCT_ID = ? , MBLOG_ACCT_ID = ? , DEAL_TIME = now() WHERE USER_ACCT_ID = ?";
		Object[] values={nickName,accType,accountSex,idCard,userPhone,userEmail,userQQ,userWechat,userWeibo,accountId};	
		int count1 = this.getJdbcTemplate().update(updateSql, values);
		if (count1==0) {
			return "no";
		} else {
			return "yes";
		}
	}
	//查现在用户所有的角色并查处所有的角色
	public JSONObject searchUserRole(String accUserId) {
		JSONObject result = new JSONObject();
		String userRoleSql = "SELECT ROLE_ID FROM TB_SYS_USER_ROLE_RELAT WHERE USER_ACCT_ID = '"+accUserId+"'";
		String allRoleSql = "SELECT DISTINCT ROLE_ID,ROLE_NAME,ROLE_DESC FROM TB_SYS_ROLE_BASE_INFO";
		List<Map<String, Object>> userRole = this.getJdbcTemplate().queryForList(userRoleSql);
		List<Map<String, Object>> allRole = this.getJdbcTemplate().queryForList(allRoleSql);
		result.put("userRole", JSONArray.fromObject(userRole));
		result.put("allRole", JSONArray.fromObject(allRole));
		return result;
	}
	//查角色描述
	public JSONObject searchRoleDesc(String roleId) {
		JSONObject result = new JSONObject();
		String allRoleSql = "SELECT DISTINCT ROLE_DESC FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID = '"+roleId+"'";
		List<Map<String, Object>> userRole = this.getJdbcTemplate().queryForList(allRoleSql);
		result.put("roleDesc", JSONArray.fromObject(userRole));
		return result;
	}
	//保存用户角色信息
	public String saveUserRole(String userRole, String accUserId) {
		String insertSql = "INSERT INTO TB_SYS_USER_ROLE_RELAT VALUES (?,?,NOW())";
		String delSql = "DELETE FROM TB_SYS_USER_ROLE_RELAT WHERE USER_ACCT_ID ='"+accUserId+"'";
		this.getJdbcTemplate().update(delSql);
		Object [] values = {accUserId,userRole};
		int count = this.getJdbcTemplate().update(insertSql, values);
		if (count>0) {
			return "yes";
		} else {
			return "no";
		}
	}
	//查模块基本信息
	public JSONObject searchMoudleInfo(String mouldeId) {
		JSONObject result = new JSONObject();
		String moudleSql = "SELECT * FROM TB_SYS_ADMIN_MODULE WHERE MODULE_ID = '"+mouldeId+"' ";
		List<Map<String, Object>> moudleList = this.getJdbcTemplate().queryForList(moudleSql);
		result.put("moudleList", JSONArray.fromObject(moudleList));
		return result;
	}
	//新加或更改模块信息
	public String upOrNewMoudle(String moduleId, String moduleName, String parentMId, String moduleLvl, 
			String moduleType, String moduleOrder, String moduleURL, String changeFlag) {
		int count1=0;
		String insertSql = "INSERT INTO TB_SYS_ADMIN_MODULE VALUES(?,?,?,?,'',?,?,?,NOW())";
		String updateSql = "UPDATE TB_SYS_ADMIN_MODULE SET PARENT_MODULE_ID =?, MODULE_NAME =?, MODULE_LVL=?, MODULE_URL =?," +
							"MODULE_TYPE =?,MODULE_ORDER =?,DEAL_TIME = now() WHERE MODULE_ID = ?";
		Object [] insertV = {moduleId,parentMId,moduleName,moduleLvl,moduleURL,moduleType,moduleOrder};
		Object [] updateV = {parentMId,moduleName,moduleLvl,moduleURL,moduleType,moduleOrder,moduleId};
		if ("1".equals(changeFlag)) {
			count1 = this.getJdbcTemplate().update(insertSql,insertV);
		} else {
			count1 = this.getJdbcTemplate().update(updateSql,updateV);
		}
		if (count1>0) {
			return "yes";
		} else {
			return "no";
		}
	}
	//查角色基本信息
	public JSONObject searchRoleInfo(String roleId) {
		JSONObject result = new JSONObject();
		String roleSql = "SELECT * FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID = '"+roleId+"' ";
		List<Map<String, Object>> roleList = this.getJdbcTemplate().queryForList(roleSql);
		result.put("roleList", JSONArray.fromObject(roleList));
		return result;
	}
	//新加或更改角色信息
	public String upOrNewRole(String roleId, String roleName, String roleDesc, String changeFlag, String userId) {
		int count1=0;
		String insertSql = "INSERT INTO TB_SYS_ROLE_BASE_INFO VALUES(?,?,?,?,NOW())";
		String updateSql = "UPDATE TB_SYS_ROLE_BASE_INFO SET ROLE_NAME =?, ROLE_DESC =?, OPER_PERSN=?,DEAL_TIME = now() WHERE ROLE_ID = ?";
		Object [] insertV = {roleId,roleName,roleDesc,userId};
		Object [] updateV = {roleName,roleDesc,userId,roleId};
		if ("1".equals(changeFlag)) {
			count1 = this.getJdbcTemplate().update(insertSql,insertV);
		} else {
			count1 = this.getJdbcTemplate().update(updateSql,updateV);
		}
		if (count1>0) {
			return "yes";
		} else {
			return "no";
		}
	}
	//查新加角色ID是否可用
	public String checkRoleId(String roleId) {
		String checkSql = "SELECT COUNT(1) FROM TB_SYS_ROLE_BASE_INFO WHERE ROLE_ID = '"+roleId+"' ";
		int count = this.getJdbcTemplate().queryForInt(checkSql);
		return count+"";
	}
	//生成模块树结构
	public JSONArray createMTree(String roleId) {
		JSONArray AreaSaturation = new JSONArray();
		String sql ="SELECT * FROM TB_SYS_ADMIN_MODULE ";
		String relateSql = "SELECT MODULE_ID FROM TB_SYS_ROLE_MODULE_RELAT WHERE ROLE_ID = '"+roleId+"'";
		List<Map<String, Object>> lisdata = this.getJdbcTemplate().queryForList(sql);
		List<Map<String, Object>> relateList = this.getJdbcTemplate().queryForList(relateSql);
		if(lisdata.size()!=0){
			for(int k=0;k<lisdata.size();k++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = lisdata.get(k);
				obj.put("moduleId",map.get("MODULE_ID"));
				obj.put("pMoudleId",map.get("PARENT_MODULE_ID"));
				obj.put("moduleName",map.get("MODULE_NAME"));
				obj.put("moduleLvl",map.get("MODULE_LVL"));
				String mouldeId = map.get("MODULE_ID")+"";
				if (relateList.size()!=0) {
					boolean isSaved = false;
					for (int i = 0; i < relateList.size(); i++) {
						if (mouldeId.equals(relateList.get(i).get("MODULE_ID")+"")) {
							isSaved = true;
						}
					}
					obj.put("LAY_CHECKED",isSaved);
				} else {
					obj.put("LAY_CHECKED",false);
				}
				
				AreaSaturation.add(obj);
			}
		}
		return AreaSaturation;
	}
	//更新模块与角色关联关系
	public String upRMRelate(String roleId, String moudleId) {
		JSONArray nameArray2 = JSONArray.fromObject(moudleId);
		String delSql = "DELETE FROM TB_SYS_ROLE_MODULE_RELAT WHERE ROLE_ID ='"+roleId+"'";
		this.getJdbcTemplate().update(delSql);
		String insertSql = "INSERT INTO TB_SYS_ROLE_MODULE_RELAT VALUES (?,?,now())";
		for (int i = 0; i < nameArray2.size(); i++) {
			Object [] values = {roleId,nameArray2.getString(i)};
			this.getJdbcTemplate().update(insertSql, values);
		}
		return "1";
	}

}
