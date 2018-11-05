package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PhotogManager;

@Service
public class PhotogManagerImpl extends AbstractManager implements PhotogManager{

	public String selphotogList(String importDate, String pgName,String start, String end) {

		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		List<Object> value = new ArrayList<Object>();
		List<Object> value1 = new ArrayList<Object>();
		value.add(pgName);
		value1.add(pgName);
		String sql ="select a.PHOTOG_ID,a.PHOTOG_NAME,a.SHOW_ORDER from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE ?";
		String sql1="select count(1) from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE ?";
		if (importDate!=null&&!"".equals(importDate)) {
			value.add(importDate);
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
			value1.add(importDate);
			sql +=" and SUBSTR(a.DEAL_TIME,1,10)=?";
			sql1+=" and SUBSTR(a.DEAL_TIME,1,10)=?";
		}else{
			value.add(Integer.parseInt(start));
			value.add(Integer.parseInt(end));
		}
		sql +=" ORDER BY a.SHOW_ORDER LIMIT ?,?";

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
				obj.put("id",map.get("PHOTOG_ID"));
				obj.put("name",map.get("PHOTOG_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1, value1.toArray());
		object.put("counts", counts);
		object.put("dataList", array.toString());

		return object.toString();
	}

	public String selphotogPic(String pgid,String picType,String showFlag) {
		String sql ="select a.FILE_NAME,a.SHOW_ORDER from TB_PHOTOG_PIC_INFO a where a.PHOTOG_ID=? and a.PIC_TYPE=? and a.SHOW_FLAG=? order by SHOW_ORDER";
		List<Object> value = new ArrayList<Object>();
		value.add(pgid);
		value.add(picType);
		if("".equals(showFlag)){
			sql ="select a.FILE_NAME,a.SHOW_ORDER from TB_PHOTOG_PIC_INFO a where a.PHOTOG_ID=? and a.PIC_TYPE=? order by SHOW_ORDER";
		}else{
			value.add(showFlag);
			sql ="select a.FILE_NAME,a.SHOW_ORDER from TB_PHOTOG_PIC_INFO a where a.PHOTOG_ID=? and a.PIC_TYPE=? and a.SHOW_FLAG=? order by SHOW_ORDER";			
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql,value.toArray());
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		object.put("dataList", JSONArray.fromObject(list));
		return object.toString();
	}

	public String selphotogListAudit(String auditStatus, String pgName,
			String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		List<Object> value = new ArrayList<Object>();
		List<Object> value1 = new ArrayList<Object>();
		value.add(pgName);
		value1.add(pgName);
		String sql ="SELECT a.PHOTOG_ID,a.PHOTOG_NAME,a.SHOW_ORDER FROM TB_PHOTOG_BASE_INFO a " +
				"LEFT JOIN TB_SYS_AUDIT_STATUS b on a.PHOTOG_ID=b.AUDIT_MBODY_ID AND a.PHOTOG_NAME LIKE ? " +
				"WHERE b.AUDIT_MBODY_TYPE='P' and b.AUDIT_STATUS=? ORDER BY a.SHOW_ORDER LIMIT ?,?";
		String sql1="SELECT COUNT(1) FROM TB_PHOTOG_BASE_INFO a " +
				"LEFT JOIN TB_SYS_AUDIT_STATUS b  on a.PHOTOG_ID=b.AUDIT_MBODY_ID AND a.PHOTOG_NAME LIKE ? " +
				"WHERE b.AUDIT_MBODY_TYPE='P' and b.AUDIT_STATUS=? ";
		value.add(auditStatus);
		value.add(Integer.parseInt(start));
		value.add(Integer.parseInt(end));
		value1.add(auditStatus);

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
				obj.put("id",map.get("PHOTOG_ID"));
				obj.put("name",map.get("PHOTOG_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1, value1.toArray());
		object.put("counts", counts);
		object.put("dataList", array.toString());

		return object.toString();
	}

}
