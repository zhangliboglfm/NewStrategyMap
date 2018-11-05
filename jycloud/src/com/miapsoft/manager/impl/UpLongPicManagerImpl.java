package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.UpLongPicManager;


@Service("upLongPicManager")
public class UpLongPicManagerImpl extends AbstractManager implements UpLongPicManager {
	//获取图片路径
	public JSONObject getAllPicPath(String bigTage, String flagId, String pgorcg) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//作品名称
		JSONArray array2 = new JSONArray();//URL
		JSONArray array3 = new JSONArray();//判别生平、成就等，1-生平，2-成就，3-风格，4-文章
		String searchFilde = "";
		String tableName = "";
		if ("cg".equals(pgorcg)) {
			tableName="TB_CG_ARTICLE_BASE_INFO";
		} else {
			tableName="TB_PHOTOG_ARTICLE_BASE_INFO";
		}
		int flag=1;
		if ("article".equals(bigTage)) {
			searchFilde="TA";
			flag=4;
		} else if ("style".equals(bigTage)) {
			searchFilde="ST";
			flag=3;
		} else if ("lifetime".equals(bigTage)) {
			searchFilde="LI";
			flag=1;
		} else if ("achievement".equals(bigTage)) {
			searchFilde="AC";
			flag=2;
		} 
		String sql = "SELECT ARTICLE_TITLE,ARTICLE_PIC FROM "+tableName+" WHERE ARTICLE_TYPE ='"+searchFilde+"' AND ARTICLE_ID = '"+flagId+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if (list.size()!=0) {
			array1.add(list.get(0).get("ARTICLE_TITLE"));
			array2.add(list.get(0).get("ARTICLE_PIC"));
			array3.add(flag);
		}
		result.put("artTitle", array1);
		result.put("picPath", array2);
		result.put("flag", array3);
		return result;
	}
	//保存最终图片
	public int saveChange(String bigTage, String flagId,String finalPicPath, String pgorcg) {
		String searchFilde = "";
		String tableName = "";
		String releteTable = "";
		String auditTable = "";
		if ("cg".equals(pgorcg)) {
			tableName="TB_CG_ARTICLE_BASE_INFO";
			releteTable = "TB_CDE_CG_ARTICLE_RELAT";
			auditTable = "TB_SYS_CG_AUDIT_STATUS";
		} else {
			tableName="TB_PHOTOG_ARTICLE_BASE_INFO";
			releteTable = "TB_CDE_PHOTOG_ARTICLE_RELAT";
			auditTable = "TB_SYS_AUDIT_STATUS";
		}
		if ("article".equals(bigTage)) {
			searchFilde="TA";
		} else if ("style".equals(bigTage)) {
			searchFilde="ST";
		} else if ("lifetime".equals(bigTage)) {
			searchFilde="LI";
		} else if ("achievement".equals(bigTage)) {
			searchFilde="AC";
		} 
		String sql ="";
		if ("".equals(finalPicPath.trim())) {
			sql = "DELETE FROM "+tableName+" WHERE  ARTICLE_ID ='"+flagId+"'";
			String relateSql = "DELETE FROM "+releteTable+" WHERE  ARTICLE_ID ='"+flagId+"'";
			String auditSql = "DELETE FROM "+auditTable+" WHERE  AUDIT_MBODY_ID ='"+flagId+"'";
			this.getJdbcTemplate().update(relateSql);
			this.getJdbcTemplate().update(auditSql);
		} else {
			sql = "UPDATE "+tableName+" SET ARTICLE_PIC = '"+finalPicPath+"' WHERE ARTICLE_ID ='"+flagId+"' AND ARTICLE_TYPE = '"+searchFilde+"'";
		}
		int result = 0;
		result = this.getJdbcTemplate().update(sql);
		return result;
	}
	
}
