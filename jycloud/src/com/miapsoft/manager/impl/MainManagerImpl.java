package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.MainManager;


@Service("mainManager")
public class MainManagerImpl extends AbstractManager implements MainManager {

	public JSONArray getTopNav(String userId) {
		/*String sql = "SELECT role_module.module_id,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
					"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
					"on role_module.ROLE_ID = role_user.ROLE_ID "+
					"LEFT JOIN TB_JYCLOUD_MODULE moudle_t "+
					"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
					"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.module_level ='1' ORDER BY moudle_t.module_order ";*/
		String sql = "SELECT role_module.module_id,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
				"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
				"on role_module.ROLE_ID = role_user.ROLE_ID "+
				"LEFT JOIN TB_SYS_ADMIN_MODULE moudle_t "+
				"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
				"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.module_lvl ='1' ORDER BY moudle_t.module_order ";
		JSONArray result = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql));
		return result;
	}

	
	public JSONArray getLeftNav(String moudle_id,String userId) {
		JSONArray result = new JSONArray();
		/*String sql1 = "SELECT role_module.module_id,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
				"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
				"on role_module.ROLE_ID = role_user.ROLE_ID "+
				"LEFT JOIN TB_JYCLOUD_MODULE moudle_t "+ 
				"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
				"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.PARENT_MODULE_ID ='"+moudle_id+"' ORDER BY moudle_t.MODULE_ORDER ";*/
		String sql1 = "SELECT role_module.module_id,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
				"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
				"on role_module.ROLE_ID = role_user.ROLE_ID "+
				"LEFT JOIN TB_SYS_ADMIN_MODULE moudle_t "+
				"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
				"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.PARENT_MODULE_ID ='"+moudle_id+"' ORDER BY moudle_t.MODULE_ORDER ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				/*String sql2 = "SELECT moudle_t.module_url,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
						"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
						"on role_module.ROLE_ID = role_user.ROLE_ID "+
						"LEFT JOIN TB_JYCLOUD_MODULE moudle_t "+
						"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
						"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.PARENT_MODULE_ID =? ORDER BY moudle_t.MODULE_ORDER ";*/
				String sql2 = "SELECT moudle_t.module_url,moudle_t.module_name from TB_SYS_ROLE_MODULE_RELAT role_module "+
						"LEFT JOIN TB_SYS_USER_ROLE_RELAT role_user "+
						"on role_module.ROLE_ID = role_user.ROLE_ID "+
						"LEFT JOIN TB_SYS_ADMIN_MODULE moudle_t "+
						"ON role_module.MODULE_ID = moudle_t.MODULE_ID "+
						"where role_user.USER_ACCT_ID = '"+userId+"' and moudle_t.PARENT_MODULE_ID =? ORDER BY moudle_t.MODULE_ORDER ";
				Object [] value2 = {map.get("module_id")};
				onedata.put("module_name", map.get("module_name"));
				onedata.put("childList",JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql2,value2)));
				result.add(onedata);
			}
		}
		return result;
	}

	

}
 