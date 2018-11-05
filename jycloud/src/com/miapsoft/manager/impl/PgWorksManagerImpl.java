package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PgWorksManager;

@Service
public class PgWorksManagerImpl extends AbstractManager implements PgWorksManager{

	public String selPhgWorks(String pgId, String start, String end) {

		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		List<Object> value = new ArrayList<Object>();
		List<Object> value1 = new ArrayList<Object>();
		value.add(pgId);
		value.add(Integer.parseInt(start));
		value.add(Integer.parseInt(end));
		value1.add(pgId);
		String sql ="select WORKS_ID,WORKS_NAME,SHOW_ORDER from TB_PHOTOG_WORKS_BASE_INFO a where a.PHOTOG_ID=? ORDER BY a.SHOW_ORDER LIMIT ?,?";
		String sql1="select count(1) from TB_PHOTOG_WORKS_BASE_INFO a where a.PHOTOG_ID=?";

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
				obj.put("id",map.get("WORKS_ID"));
				obj.put("name",map.get("WORKS_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1, value1.toArray());
		object.put("counts", counts);
		object.put("dataList", array.toString());

		return object.toString();
	}

	public String selPhgWorksImg(String wId) {
		String sql ="select a.FILE_NAME,a.SHOW_ORDER from TB_PHOTOG_WORKS_BASE_INFO a where a.WORKS_ID=?";
		List<Object> value = new ArrayList<Object>();
		value.add(wId);
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

	public int addPhgWorksInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String worksId = obj.getString("worksId");
		String worksName = obj.getString("worksName");
		String worksType = obj.getString("worksType");
		String shootDate = obj.getString("shootDate");
		String worksIntro = obj.getString("worksIntro");
		String shootProc = obj.getString("shootProc");
		String fileName = obj.getString("fileName");
		String pgId = obj.getString("pgId");
		String isRepre = "是".equals(obj.getString("isRepre"))?"1":"0";
		String showIndex = obj.getString("showIndex");
		String labels = obj.getString("labels");
		shootDate = formatTime(shootDate);
		//规则：W+下划线（1位）+摄影家标识（10位）+编号（8位）
		//示例：W_P_M156000100000001
		String worksId="";
		String tmpWId="W_"+pgId;
		String selsql = "SELECT CASE WHEN max(WORKS_ID) IS NULL THEN '0' ELSE max(WORKS_ID) END AS MAX_ID,CASE WHEN max(SHOW_ORDER) IS NULL THEN '0' ELSE max(SHOW_ORDER) END AS MAX_ORDER from TB_PHOTOG_WORKS_BASE_INFO where PHOTOG_ID = ?";
		List<Map<String , Object>> list = this.getJdbcTemplate().queryForList(selsql,pgId);
		String maxWid="0";
		String maxOrder="0";
		if(list!=null&&list.size()>0){
				Map<String,Object> map = list.get(0);
				maxWid=map.get("MAX_ID").toString();
				maxOrder=map.get("MAX_ORDER").toString();
		}
		int tmpid=Integer.parseInt(maxWid.replace(tmpWId, ""))+1;
		worksId=tmpWId+String.format("%08d", tmpid);
		showIndex=(Integer.parseInt(maxOrder)+1)+"";
		
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_WORKS_BASE_INFO VALUES ('"+worksId+"','"+worksName+"','"+worksType+"','"+shootDate+"','"+worksIntro+"','"+shootProc+"','"+fileName+"','"+pgId+"','"+isRepre+"','"+showIndex+"','"+curdate+"')";
		result = this.getJdbcTemplate().update(sql);
		/*if(checkDataConflict(worksId,"TB_PHOTOG_WORKS_BASE_INFO","WORKS_ID")){
			result = this.getJdbcTemplate().update(sql);
		}*/
		
		//插入标签信息
		String[] labelArray=labels.split("#");
		for (int i = 0; i < labelArray.length; i++) {
			
			String labelName = labelArray[i].replaceAll("\\s*", "");
			int labelOrder=1;
			if (labelName!=null&&!"".equals(labelName)) {
				String selLableSql = "select LABEL_ID FROM TB_CDE_LABEL WHERE LABEL_TYPE='W' AND LABEL_NAME=?";
				List<Map<String, Object>> lablelist=this.getJdbcTemplate().queryForList(selLableSql, labelName);
				String labelId="";
				if (lablelist!=null&&lablelist.size()>0) {
					labelId=lablelist.get(0).get("LABEL_ID").toString();
				}else{
					
					String maxLabelSql="select SUBSTR(MAX(LABEL_ID),4) AS LABEL_ID FROM TB_CDE_LABEL WHERE LABEL_TYPE='W'";
					int maxIndex=this.getJdbcTemplate().queryForInt(maxLabelSql)+1;
					labelId="L_W"+String.format("%07d", maxIndex);
					
					String addlabsql = "INSERT INTO TB_CDE_LABEL VALUES ('"+labelId+"','"+labelName+"','W','"+labelName+"','"+curdate+"')";
					this.getJdbcTemplate().update(addlabsql);
				}
				String labraltsql = "INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT VALUES ('"+labelId+"','"+worksId+"','"+labelOrder+"','"+curdate+"')";
				this.getJdbcTemplate().update(labraltsql);
				labelOrder+=1;
			}
		}
		
		//往审核状态表插数据
		String auditsql = "INSERT INTO TB_SYS_AUDIT_STATUS VALUES ('"+worksId+"','W','1','"+curdate+"')";
		this.getJdbcTemplate().update(auditsql);
		
		
		return result;
	}
	
	public static void main(String[] args) {
		//String labels = "#风光#人物#景象";
		String labels = "";
		String[] labelArray=labels.split("#");
		System.out.println(labelArray.length);
		System.out.println(labelArray.toString());
		for (int i = 0; i < labelArray.length; i++) {
			System.out.println(labelArray[i]);
		}
		
		
		String a=" 1 2 3 ";
		System.out.println(a.length());
		System.out.println(a.trim().length());
	}
	//拍摄日期格式化
	public String formatTime(String atime) {
		String formatTime="";
		String [] atimeArr = new String[]{};
		if (atime.contains(".")) {
			atimeArr = atime.split("\\.");
		} else if(atime.contains("-")){
			atimeArr = atime.split("-");
		} else if(atime.contains("~")){
			atimeArr = atime.split("~");
		}
		if (atimeArr.length!=0) {
			for (int i = 0; i < atimeArr.length; i++) {
				int nowNum = Integer.parseInt(atimeArr[i]);
				if (nowNum<10) {
					formatTime += "0"+nowNum;
				} else {
					formatTime += nowNum;
				}
			}
		}
		return formatTime;
	}
	//删除作品并重新排序
	public String deletePgWorks(String deleteId, String pgId) {
		String deleteSql1 = "DELETE FROM TB_PHOTOG_WORKS_BASE_INFO WHERE WORKS_ID = '"+deleteId+"'";
		String deleteSql2 = "DELETE FROM TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT WHERE WORKS_ID = '"+deleteId+"'";
		String deleteSql3 = "DELETE FROM TB_SYS_AUDIT_STATUS WHERE AUDIT_MBODY_ID = '"+deleteId+"'";
		this.getJdbcTemplate().update(deleteSql1);
		this.getJdbcTemplate().update(deleteSql2);
		this.getJdbcTemplate().update(deleteSql3);
		return "yes";
	}
	//作品重新排序
	public String reOrderWork(String pgId) {
		String orderSql = "SELECT WORKS_ID FROM TB_PHOTOG_WORKS_BASE_INFO WHERE PHOTOG_ID ='"+pgId+"' ORDER BY SHOW_ORDER";
		String updateSql = "UPDATE TB_PHOTOG_WORKS_BASE_INFO SET SHOW_ORDER = ? WHERE WORKS_ID = ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(orderSql);
		for (int i = 0; i < list.size(); i++) {
			Object [] args = {i+1,list.get(i).get("WORKS_ID")};
			this.getJdbcTemplate().update(updateSql,args);
		}
		return "yes";
	}

}
