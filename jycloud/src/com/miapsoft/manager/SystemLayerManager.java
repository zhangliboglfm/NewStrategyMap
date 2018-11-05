/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SystemLayerManager {
	//查现有的基本信息
	public JSONObject searchBaseInfo(String accUserId);
	//更新基本信息
	public String reupBaseInfo(String accountId, String nickName, String accType, String accountSex
			, String idCard, String userPhone, String userEmail, String userQQ, String userWechat, String userWeibo);
	//查现在用户所有的角色并查处所有的角色
	public JSONObject searchUserRole(String accUserId);
	//查角色描述
	public JSONObject searchRoleDesc(String roleId);
	//保存用户角色信息
	public String saveUserRole(String userRole, String accUserId);
	//查模块基本信息
	public JSONObject searchMoudleInfo(String mouldeId);
	//新加或更改模块信息
	public String upOrNewMoudle(String moduleId, String moduleName,
			String parentMId, String moduleLvl, String moduleType,
			String moduleOrder, String moduleURL, String changeFlag);
	//查角色基本信息
	public JSONObject searchRoleInfo(String roleId);
	//新加或更改角色信息
	public String upOrNewRole(String roleId, String roleName, String roleDesc,
			String changeFlag, String userId);
	//查新加角色是否存在
	public String checkRoleId(String roleId);
	//生成模块树结构
	public JSONArray createMTree(String roleId);
	//更新模块与角色关联关系
	public String upRMRelate(String roleId, String moudleId);
}
