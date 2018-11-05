package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.ProductionManager;


@Service("produtionManager")
public class ProdutionManagerImpl extends AbstractManager implements ProductionManager {
	//查作品作者名
	public String getPhotogName(String photogid) {
		String name = "";
		String sql = "SELECT PHOTOG_NAME FROM TB_PHOTOG_BASE_INFO WHERE PHOTOG_ID = '" + photogid + "'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1.size()!=0) {
			name=list1.get(0).get("PHOTOG_NAME").toString();
		}
		return name;
	}

	
	public JSONArray getLabelList(String Workid) {
		
		String sql ="select  a.LABEL_ID,a.LABEL_NAME,"+
				" (CASE  WHEN b.LABEL_ID IS Null "+
				  "then false "+
					"WHEN b.LABEL_ID = '' "+
					"then false "+
				"else true END)  as 'hasit' "+
				"from TB_CDE_LABEL a "+
				"LEFT JOIN TB_CDE_PHOTOG_LABEL_RELAT b  "+
				"on a.LABEL_ID = b.LABEL_ID and b.LABEL_MBODY_ID= ? "+
				"WHERE a.LABEL_TYPE ='W'  order by a.LABEL_ID asc";
				
		Object [] value = {Workid};
		JSONArray result = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql,value));
		return result;
	}


	public String operateWorkLabel(String workid, String LABEL_ID,String operate) {
		String sql ="";
		Object [] value ={LABEL_ID,workid};
		if("add".equals(operate)){
			String sql1="SELECT case when  MAX(LABEL_ORDER) is NULL THEN 0 ELSE MAX(LABEL_ORDER) END "+
					"FROM TB_CDE_PHOTOG_LABEL_RELAT where LABEL_MBODY_ID = ?  ";
			Object [] value1 = {workid};
			int order = this.getJdbcTemplate().queryForInt(sql1, value1)+1;
			sql+="INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES(?,?,"+order+",now())";
		}else if("delete".equals(operate)) {
			sql+="DELETE from TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_ID= ? and LABEL_MBODY_ID= ?";
		}
		int row=this.getJdbcTemplate().update(sql, value);
		
		if(1==row){
			return "1";
		}else{
			return "0";
		}
	}


	public JSONObject addDefinedLabel(String LabelName,String LabelDesc) {
		JSONObject result = new JSONObject();
		String label_id="";
		String sql="SELECT LABEL_ID FROM TB_CDE_LABEL where  LABEL_TYPE = 'W' AND LABEL_NAME = ?";
		Object [] value = {LabelName};
		List<Map<String,Object>> list =this.getJdbcTemplate().queryForList(sql,value);
		if(list!=null&&list.size()!=0){
			label_id = list.get(0).get("LABEL_ID").toString();
			result.put("success", false);
			result.put("message", "已有该标签，请重新自定义");
			return result;
		}else{
			String sql1 ="SELECT COUNT(*) FROM TB_CDE_LABEL where  LABEL_TYPE = 'W'";
			String num1 =this.getJdbcTemplate().queryForInt(sql1)+1+"";
			StringBuffer sb =new StringBuffer("L_W");
			for (int i = 0; i < (7-num1.length()); i++) {
				sb.append("0");
			};
			label_id=sb.append(num1).toString();
			String insertsql = "INSERT INTO TB_CDE_LABEL(LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC,DEAL_TIME)VALUES (?,?,'W',?,now())";
			Object [] insertValue ={label_id,LabelName,LabelDesc};
			int insertNum = this.getJdbcTemplate().update(insertsql,insertValue);
			if(insertNum!=1){
				result.put("success", false); 
				result.put("message", "请重试");
				return result;
			};
		};
		result.put("success", true);
		result.put("label_id", label_id);
		return result;
	}


	public JSONObject getWorkDesc(String workid) {
		JSONObject result = new JSONObject();
		String sql1="SELECT WORKS_NAME,WORKS_TYPE,SHOOT_DATE,WORKS_INTRO,SHOOT_PROC,IS_REPRE_WORKS FROM TB_PHOTOG_WORKS_BASE_INFO WHERE WORKS_ID = ? ";
		Object [] value1 = {workid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1,value1);
		
		//查询作品标签信息
		String sql2 = " SELECT a.LABEL_ID,b.LABEL_NAME from TB_CDE_PHOTOG_LABEL_RELAT a "+
						" LEFT JOIN TB_CDE_LABEL b on a.LABEL_ID = b.LABEL_ID "+
						" WHERE LABEL_MBODY_ID = ? ORDER BY a.LABEL_ORDER ASC";
		
		JSONArray data2 = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql2,value1));
		
		result.put("data2", data2);
		if(list1!=null&&list1.size()!=0){
			JSONObject data1=JSONObject.fromObject(list1.get(0));
			result.put("data1", data1);
		}else {
			result.put("data1", null);
		};
		String sql3 ="SELECT AUDIT_STATUS,AUDIT_STATUS_DESC FROM TB_SYS_AUDIT_STATUS  " +
				"a LEFT JOIN TB_CDE_AUDIT_STATUS b on a.AUDIT_STATUS = b.AUDIT_STATUS_ID" +
				" WHERE a.AUDIT_MBODY_ID = ? and a.AUDIT_MBODY_TYPE ='W'";
		Object [] value3 = {workid+"_W"};
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql3,value3);
		if(list3!=null&&list3.size()!=0){
			JSONArray onedata = new JSONArray();
			onedata.add(list3.get(0).get("AUDIT_STATUS_DESC"));
			onedata.add(list3.get(0).get("AUDIT_STATUS"));
			result.put("data3",onedata);
		}else {
			JSONArray onedata = new JSONArray();
			onedata.add("");
			onedata.add("");
			result.put("data3",onedata);
		};
		String sql4 ="SELECT AUDIT_STATUS,AUDIT_STATUS_DESC FROM TB_SYS_AUDIT_STATUS  " +
				"a LEFT JOIN TB_CDE_AUDIT_STATUS b on a.AUDIT_STATUS = b.AUDIT_STATUS_ID" +
				" WHERE a.AUDIT_MBODY_ID = ? and a.AUDIT_MBODY_TYPE ='E'";
		Object [] value4 = {workid+"_E"};
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql4,value4);
		if(list4!=null&&list4.size()!=0){
			JSONArray onedata = new JSONArray();
			onedata.add(list4.get(0).get("AUDIT_STATUS_DESC"));
			onedata.add(list4.get(0).get("AUDIT_STATUS"));
			result.put("data4", onedata);
		}else {
			JSONArray onedata = new JSONArray();
			onedata.add("");
			onedata.add("");
			result.put("data4",onedata);
		};
		
		JSONArray data5 = new JSONArray();
		String [] workids={workid+"_W",workid+"_E"};
		String sql5 ="SELECT AUDIT_DESC from TB_SYS_AUDIT_RESULT WHERE AUDIT_MBODY_ID = ? ORDER BY DEAL_TIME DESC";
		for (int i = 0; i < workids.length; i++) {
			Object [] value5 ={workids[i]};
			List<Map<String, Object>> list5=this.getJdbcTemplate().queryForList(sql5,value5);
			if(list5!=null&&list5.size()!=0){
				data5.add(list5.get(0).get("AUDIT_DESC"));
			}else {
				data5.add("");
			}
		}
		result.put("data5",data5);
		return result;
	}


	public String updateWorkDesc(String workid, String works_name,String works_type, String shoot_date, String works_intro,String shoot_proc, String is_repre_works,String [] labelIds){
		
		String sql =" UPDATE TB_PHOTOG_WORKS_BASE_INFO SET "+
					" WORKS_NAME = ?,WORKS_TYPE = ? ,SHOOT_DATE = ? ,WORKS_INTRO = ? ," +
					"SHOOT_PROC = ? ,IS_REPRE_WORKS = ? ,DEAL_TIME = now()"+
					"  WHERE WORKS_ID = ? ";
		Object [] value ={works_name,works_type,shoot_date,works_intro,shoot_proc,is_repre_works,workid};
		int num = this.getJdbcTemplate().update(sql,value);
		if(num!=1){
			return "false";
		};
		String sql1="DELETE FROM TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_MBODY_ID = ? ";
		Object [] value1 = {workid};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1<0){
			return "false";
		};
		
		String sql2= "INSERT INTO  TB_CDE_PHOTOG_LABEL_RELAT VALUES(?,?,?,now())";
		for (int i = 0; i < labelIds.length; i++) {
			Object [] value2 = {labelIds[i],workid,i};
			int num2 =this.getJdbcTemplate().update(sql2,value2);
			if(num2!=1){
				return "false";
			}
		}
		return "true";
		
	}


	public int getWorkNumByPhotogId(String photogid) {
		String sql = "SELECT COUNT(*) from TB_PHOTOG_WORKS_BASE_INFO WHERE PHOTOG_ID = ? ";
		Object [] value = {photogid};
		return this.getJdbcTemplate().queryForInt(sql,value);
	}


	public JSONArray getWorksDataByPhotogID(String photogid) {
		String sql = "SELECT WORKS_ID,FILE_NAME from TB_PHOTOG_WORKS_BASE_INFO WHERE PHOTOG_ID = ? ORDER BY SHOW_ORDER";
		Object [] value = {photogid};
		return JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql, value));
	}


	public boolean uploadWorkImg(String Workid,String filename) {
		String sql ="UPDATE TB_PHOTOG_WORKS_BASE_INFO SET FILE_NAME =? WHERE WORKS_ID = ?";
		Object [] value = {filename,Workid};
		int row =  this.getJdbcTemplate().update(sql, value);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}


	public String changeExamineStatus(String workid, String aUDIT_STATUS,String aUDIT_DESC, String aUDIT_PERSN,String type) {

		String sql1 ="UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS = ? WHERE AUDIT_MBODY_ID =? AND AUDIT_MBODY_TYPE = ? ";
		Object [] value1 = {aUDIT_STATUS,workid+"_"+type,type};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1==0){
			String sql11 ="INSERT INTO TB_SYS_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,?,?,NOW())";
			Object [] value11 = {workid+"_"+type,type,aUDIT_STATUS};
			int num11 = this.getJdbcTemplate().update(sql11,value11);
			if(num11!=1){
				return "false";
			}
		}else if(num1!=1){
			return "false";
		}
		String sql2 ="INSERT INTO TB_SYS_AUDIT_RESULT(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		Object [] value2 = {workid+"_"+type,type,aUDIT_STATUS,aUDIT_DESC,aUDIT_PERSN};
		int num2 = this.getJdbcTemplate().update(sql2,value2);
		if(num2!=1){
			return "false";
		}else{
			return "true";
		}
		
	
		
	}
	
}
 