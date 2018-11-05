package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PgStandardImgManager;

@Service
public class PgStandardImgManagerImpl extends AbstractManager implements PgStandardImgManager{
	
	public String selStandImgs(String pgId) {
		String selsql = "SELECT PHOTOG_ID,PIC_TYPE,FILE_NAME,SHOW_FLAG,SHOW_ORDER,DEAL_TIME from TB_PHOTOG_PIC_INFO where PHOTOG_ID = ? and PIC_TYPE = '标准照' ORDER BY SHOW_ORDER";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selsql,pgId);
		JSONObject object = new JSONObject();
		object.put("dataList", JSONArray.fromObject(list));
		return object.toString();
	}
	
	
	public JSONObject addStandImg(String pgId, String filename) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String selsql = "SELECT max(SHOW_ORDER) from TB_PHOTOG_PIC_INFO where PHOTOG_ID = ? and PIC_TYPE = '标准照'";
		int maxOrder = this.getJdbcTemplate().queryForInt(selsql,pgId);
		//System.out.println("标准照顺序：");
		//System.out.println(maxOrder);
		
		String isql = "INSERT INTO TB_PHOTOG_PIC_INFO (PHOTOG_ID,PIC_TYPE,FILE_NAME,SHOW_FLAG,SHOW_ORDER,DEAL_TIME) VALUES (?,?,?,?,?,?)";
		result = this.getJdbcTemplate().update(isql,pgId,"标准照",filename,"1",maxOrder+1,sdf.format(new Date()));
		
		JSONObject object=new JSONObject();
		object.put("result", result);
		object.put("order", maxOrder+1);
		
		return object;
	}


	public String updateStandImgOrder(String pgId, String fileName,
			String newOrder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updatesql = "UPDATE TB_PHOTOG_PIC_INFO SET SHOW_ORDER=?,DEAL_TIME=? WHERE PHOTOG_ID=? AND FILE_NAME=? AND PIC_TYPE='标准照'";
		int result = this.getJdbcTemplate().update(updatesql,newOrder,sdf.format(new Date()),pgId,fileName);
		
		String text="更新成功";
		String code="1000";
		if (result>0) {
			
		}else {
			text="更新失败";
			code="1001";
		}
		
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("text", text);
		
		return object.toString();
	}


	public String delStandImg(String pgId, String fileName) {
		String updatesql = "DELETE FROM TB_PHOTOG_PIC_INFO WHERE PHOTOG_ID=? AND FILE_NAME=? AND PIC_TYPE='标准照'";
		int result = this.getJdbcTemplate().update(updatesql,pgId,fileName);
		
		String text="删除成功";
		String code="1000";
		if (result>0) {
			
		}else {
			text="删除失败";
			code="1001";
		}
		
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("text", text);
		
		return object.toString();
	}

	
}
