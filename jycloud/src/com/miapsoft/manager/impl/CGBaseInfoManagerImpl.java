/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-29
*/
package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CGBaseInfoManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CGBaseInfoManagerImpl extends AbstractManager implements CGBaseInfoManager {

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.CGBaseInfoManager#selectCalligList()
	 */
	public JSONObject selectCalligList(String importDate, String cgName, String orderColumn, String orderSortype, String auditStatus, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		List<Object> value = new ArrayList<Object>();
		List<Object> value1 = new ArrayList<Object>();
		value.add(cgName);
		value1.add(cgName);
		String auditsql = "";
		if(auditStatus!=null&&!auditStatus.equals("")){
			if ("1".equals(auditStatus.trim())) {
				auditsql = " AND C.AUDIT_STATUS != '5' ";
			} else if("5".equals(auditStatus.trim())) {
				auditsql = " AND C.AUDIT_STATUS = '5' ";
			}
		}
		String sql ="SELECT A.CGER_ID,A.CGER_NAME,A.SHOW_ORDER,COUNT(B.WORKS_ID) AS WORKS_NUM,X.DYNASTY_NAME " +
					"FROM TB_CG_BASE_INFO A " +
					"LEFT JOIN TB_CG_WORKS_BASE_INFO B ON A.CGER_ID=B.CGER_ID " +
					"LEFT JOIN TB_SYS_CG_AUDIT_STATUS C ON A.CGER_ID=C.AUDIT_MBODY_ID " +
					"LEFT JOIN TB_CDE_CG_DYNASTY X ON A.DYNASTY = X.DYNASTY_CODE " +
					"WHERE A.CGER_NAME LIKE ? "+auditsql;
		String sql1="select count(1) from TB_CG_BASE_INFO A where A.CGER_NAME LIKE ?";
		if(auditStatus!=null&&!auditStatus.equals("")){
			if ("1".equals(auditStatus.trim())) {
				sql1 = "select count(1) from TB_CG_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.CGER_ID=B.AUDIT_MBODY_ID where A.CGER_NAME LIKE ? AND B.AUDIT_STATUS != '5' ";
			} else if("5".equals(auditStatus.trim())) {
				sql1 = "select count(1) from TB_CG_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.CGER_ID=B.AUDIT_MBODY_ID where A.CGER_NAME LIKE ? AND B.AUDIT_STATUS = '5' ";
			}
		}
		if (importDate!=null&&!"".equals(importDate)) {
			value.add(importDate);
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
			value1.add(importDate);
			sql +=" and SUBSTR(A.DEAL_TIME,1,10)=?";
			sql1+=" and SUBSTR(A.DEAL_TIME,1,10)=?";
		}else{
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
		}
		sql += " GROUP BY A.CGER_ID,A.CGER_NAME,A.SHOW_ORDER,B.CGER_ID ";
		if(orderColumn!=null&&!orderColumn.equals("")){
			if(orderColumn.equals("birthday")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY CAST(A.BORN_TIME AS SIGNED) LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY CAST(A.BORN_TIME AS SIGNED) DESC LIMIT ?,?";
				}
			}else if(orderColumn.equals("changetime")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY A.DEAL_TIME LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY A.DEAL_TIME DESC LIMIT ?,?";
				}
			}else if(orderColumn.equals("worksnum")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY WORKS_NUM LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY WORKS_NUM DESC LIMIT ?,?";
				}
			}
		}else{
			sql += " ORDER BY A.SHOW_ORDER LIMIT ?,?";
		}

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql,value.toArray());
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("CGER_ID"));
				obj.put("name",map.get("CGER_NAME"));
				obj.put("wnum", map.get("WORKS_NUM"));
				obj.put("order", map.get("SHOW_ORDER"));
				obj.put("dynasty", map.get("DYNASTY_NAME"));
				array.add(obj);
			}
		}
		int counts=this.getJdbcTemplate().queryForInt(sql1, value1.toArray());
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object;
	}
	//审核部分查询
	public JSONObject selectCalligList2(String importDate, String cgName, String orderColumn, String orderSortype, String auditStatus, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		List<Object> value = new ArrayList<Object>();
		List<Object> value1 = new ArrayList<Object>();
		value.add(cgName);
		value1.add(cgName);
		String auditsql = "";
		if(auditStatus!=null&&!auditStatus.equals("")){
			if ("1".equals(auditStatus.trim())) {
				auditsql = " AND C.AUDIT_STATUS = '1' ";
			} else if("5".equals(auditStatus.trim())) {
				auditsql = " AND C.AUDIT_STATUS = '5' ";
			}
		}
		String sql ="SELECT A.CGER_ID,A.CGER_NAME,A.SHOW_ORDER,COUNT(B.WORKS_ID) AS WORKS_NUM,X.DYNASTY_NAME " +
					"FROM TB_CG_BASE_INFO A " +
					"LEFT JOIN TB_CG_WORKS_BASE_INFO B ON A.CGER_ID=B.CGER_ID " +
					"LEFT JOIN TB_SYS_CG_AUDIT_STATUS C ON A.CGER_ID=C.AUDIT_MBODY_ID " +
					"LEFT JOIN TB_CDE_CG_DYNASTY X ON A.DYNASTY = X.DYNASTY_CODE " +
					"WHERE A.CGER_NAME LIKE ? "+auditsql;
		String sql1="select count(1) from TB_CG_BASE_INFO A where A.CGER_NAME LIKE ?";
		if(auditStatus!=null&&!auditStatus.equals("")){
			if ("1".equals(auditStatus.trim())) {
				sql1 = "select count(1) from TB_CG_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.CGER_ID=B.AUDIT_MBODY_ID where A.CGER_NAME LIKE ? AND B.AUDIT_STATUS = '1' ";
			} else if("5".equals(auditStatus.trim())) {
				sql1 = "select count(1) from TB_CG_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.CGER_ID=B.AUDIT_MBODY_ID where A.CGER_NAME LIKE ? AND B.AUDIT_STATUS = '5' ";
			}
		}
		if (importDate!=null&&!"".equals(importDate)) {
			value.add(importDate);
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
			value1.add(importDate);
			sql +=" and SUBSTR(A.DEAL_TIME,1,10)=?";
			sql1+=" and SUBSTR(A.DEAL_TIME,1,10)=?";
		}else{
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
		}
		sql += " GROUP BY A.CGER_ID,A.CGER_NAME,A.SHOW_ORDER,B.CGER_ID ";
		if(orderColumn!=null&&!orderColumn.equals("")){
			if(orderColumn.equals("birthday")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY CAST(A.BORN_TIME AS SIGNED) LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY CAST(A.BORN_TIME AS SIGNED) DESC LIMIT ?,?";
				}
			}else if(orderColumn.equals("changetime")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY A.DEAL_TIME LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY A.DEAL_TIME DESC LIMIT ?,?";
				}
			}else if(orderColumn.equals("worksnum")){
				if(orderSortype.equals("Ascending")){
					sql += " ORDER BY WORKS_NUM LIMIT ?,?";
				}else if(orderSortype.equals("Descending")){
					sql += " ORDER BY WORKS_NUM DESC LIMIT ?,?";
				}
			}
		}else{
			sql += " ORDER BY A.SHOW_ORDER LIMIT ?,?";
		}

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql,value.toArray());
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("CGER_ID"));
				obj.put("name",map.get("CGER_NAME"));
				obj.put("wnum", map.get("WORKS_NUM"));
				obj.put("order", map.get("SHOW_ORDER"));
				obj.put("dynasty", map.get("DYNASTY_NAME"));
				array.add(obj);
			}
		}
		int counts=this.getJdbcTemplate().queryForInt(sql1, value1.toArray());
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object;
	}
	/* (non-Javadoc)
	 * @see com.miapsoft.manager.CGBaseInfoManager#selectCalligPicInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String selectCalligPicInfo(String cgid,String showFlag) {
		String filePath = "";
		String sql ="SELECT FILE_NAME FROM TB_CG_PIC_INFO WHERE CGER_ID = ? AND SHOW_FLAG = ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,cgid,showFlag);
		if(list.size()!=0){
			filePath = list.get(0).get("FILE_NAME") == null ? "" : list.get(0).get("FILE_NAME").toString();
		}
		return filePath;
	}

}
