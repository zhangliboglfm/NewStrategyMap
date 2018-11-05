package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.JieduManager;


@Service("jieduManager")
public class JieduManagerImpl extends AbstractManager implements JieduManager {
	//获取作品解读word、H5、图片名称
	public JSONObject getJieduInfo(String articleId) {
		JSONObject result=new JSONObject();
		JSONArray array1=new JSONArray();
		String sql= "SELECT ARTICLE_DOC,ARTICLE_PIC,ARTICLE_URL FROM TB_PHOTOG_ARTICLE_BASE_INFO WHERE ARTICLE_ID ='"+articleId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		array1=JSONArray.fromObject(list1);
		result.put("data", array1);
		return result;
	}

	public String getAreInterpret(String articleId, String flagId) {
		String wordPath= "";
		String sql="select ARTICLE_DOC from TB_PHOTOG_ARTICLE_BASE_INFO where ARTICLE_ID='"+articleId+"' and ARTICLE_TYPE='"+flagId+"' ";
		List<Map<String, Object>>list = this.getJdbcTemplate().queryForList(sql);
		if (list.size()!=0) {
			wordPath=list.get(0).get("ARTICLE_DOC").toString();
		}
		return wordPath;
	}

	public JSONObject reuploadJDu(String filename, String articleId, String xuanlei, String flagId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();
		String ziduanName="";
		if ("wordJD".equals(xuanlei)) {
			ziduanName="ARTICLE_DOC";
		} else if ("imgJD".equals(xuanlei)) {
			ziduanName="ARTICLE_PIC";
		} else {
			ziduanName="ARTICLE_URL";
		}
		String updateSql = "UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET "+ziduanName+"='"+filename+"' WHERE ARTICLE_ID ='"+articleId+"' AND ARTICLE_TYPE = '"+flagId+"'";
		int countt1=this.getJdbcTemplate().update(updateSql);
		if(countt1>0){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}
	
}
 