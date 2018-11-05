package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.MessagecheckManager;


@Service("messagecheckManager")
public class MessagecheckManagerImpl extends AbstractManager implements MessagecheckManager {

	public JSONObject getPhotoglist(String photogName, String dealTime,String pageSize,String pageNum) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		
		List<Object> value1 = new ArrayList<Object>();
		List<Object> value2 = new ArrayList<Object>();
		
		String sql1 ="SELECT count(*) "+
				 " from TB_PHOTOG_BASE_INFO a ";
		
		String sql2 ="SELECT a.PHOTOG_ID,a.PHOTOG_NAME,a.PHOTOG_GENDER,b.COUNTRY_CHN_NAME,"+
		" a.BORN_DATE,a.DEATH_DATE,a.CORE_INTRO, c.FILE_NAME"+
		 " from TB_PHOTOG_BASE_INFO a "+
		 " LEFT JOIN TB_CDE_COUNTRY  b "+
		" on a.NATION = b.COUNTRY_ID "+
		" LEFT JOIN TB_PHOTOG_PIC_INFO c "+
		 "ON a.PHOTOG_ID = c.PHOTOG_ID and c.PIC_TYPE ='头像' ";
		if(photogName !=null&&photogName!=""&&dealTime!=null&&dealTime!=""){
			sql1+="where a.PHOTOG_NAME like  '%"+photogName+"%'   and DATE_FORMAT(a.DEAL_TIME,'%Y-%m-%d') = ? ";
			sql2 +="where a.PHOTOG_NAME like  '%"+photogName+"%'   and DATE_FORMAT(a.DEAL_TIME,'%Y-%m-%d') = ?  order by a.SHOW_ORDER asc LIMIT ? , ? ";
			value1.add(dealTime);
			value2.add(dealTime);
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
		}else if(photogName !=null&&photogName!=""&&(dealTime==null||dealTime=="")) {
			sql1+="where a.PHOTOG_NAME like  '%"+photogName+"%'  ";
			sql2 +="where a.PHOTOG_NAME like  '%"+photogName+"%'    order by a.SHOW_ORDER asc LIMIT ? , ? ";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
		}else if((photogName ==null||photogName=="")&&dealTime!=null&&dealTime!=""){
			sql1+="where  DATE_FORMAT(a.DEAL_TIME,'%Y-%m-%d') = ? ";
			sql2 +="where  DATE_FORMAT(a.DEAL_TIME,'%Y-%m-%d') = ?  order by a.SHOW_ORDER asc LIMIT ? , ? ";
			value1.add(dealTime);
			value2.add(dealTime);
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
			
		}else {
			sql2 +=" order by a.SHOW_ORDER asc LIMIT ? , ?";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
		}
		
		int countNum = this.getJdbcTemplate().queryForInt(sql1,value1.toArray());
		
		result.put("countNum", countNum);
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql2,value2.toArray());
		result.put("list", JSONArray.fromObject(list));
		
		return result;
	}

	
	public JSONObject getPhotogdesc(String PHOTOG_ID) {
		JSONObject result = new JSONObject();
		String sql1 ="SELECT FILE_NAME  ,SHOW_FLAG from TB_PHOTOG_PIC_INFO WHERE PIC_TYPE='标准照' and PHOTOG_ID = ? ORDER BY SHOW_ORDER ;";
		Object [] value = {PHOTOG_ID};
		String sql2 ="SELECT a.LABEL_ID, b.LABEL_NAME FROM  TB_CDE_PHOTOG_LABEL_RELAT a "+
			" LEFT JOIN TB_CDE_LABEL b  "+
			" on a. LABEL_ID = b.LABEL_ID "+
			" where a.LABEL_MBODY_ID = ? ORDER BY a.LABEL_ORDER asc;";
		String sql3 ="SELECT b.AUDIT_STATUS_DESC,a.AUDIT_STATUS FROM TB_SYS_AUDIT_STATUS  a "+
				" LEFT JOIN TB_CDE_AUDIT_STATUS b "+
				" on a.AUDIT_STATUS = b.AUDIT_STATUS_ID "+
				" WHERE AUDIT_MBODY_ID = ? AND AUDIT_MBODY_TYPE ='P' ";
		
		JSONArray dataPic = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql1,value));
		JSONArray dataLab = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql2,value));
		String eduit_status=this.getJdbcTemplate().queryForMap(sql3,value).get("AUDIT_STATUS_DESC").toString();
		result.put("dataPic", dataPic);
		result.put("dataLab", dataLab);
		result.put("eduit_status", eduit_status);
		return result;
	}


	public String savePhotoDesc(String PHOTOG_ID, String[] params) {
		String nationcode,gender;
		Object [] nation ={params[4]};
		String sql1 ="SELECT COUNTRY_ID from TB_CDE_COUNTRY WHERE COUNTRY_CHN_NAME = ? ";
		 List<Map<String, Object>> list1= this.getJdbcTemplate().queryForList(sql1,nation);
		 if(list1==null||list1.size()==0){
			return "0";
		 }else {
			 nationcode=list1.get(0).get("COUNTRY_ID").toString();
		}
		if("男".equals(params[1])){
			gender="M";
		}else if("女".equals(params[1])){
			gender="F";
		}else{
			return "1";
		}
		Object [] value ={params[0],gender,params[2],params[3],nationcode,PHOTOG_ID};
		String sql ="UPDATE TB_PHOTOG_BASE_INFO SET PHOTOG_NAME = ? ,PHOTOG_GENDER= ? ,BORN_DATE= ?,DEATH_DATE= ?,NATION = ? "+   
				" where PHOTOG_ID = ? ";
		int updateNum = this.getJdbcTemplate().update(sql, value);
		if(updateNum ==1){
			return "2";
		}else {
			return "3";
		}
		
	}

	
	public JSONArray getLablist(String PHOTOG_ID,String tagName) {
		
		String sql ="select  a.LABEL_ID,a.LABEL_NAME,"+
				" (CASE  WHEN b.LABEL_ID IS Null "+
				  "then false "+
					"WHEN b.LABEL_ID = '' "+
					"then false "+
				"else true END)  as 'hasit' "+
				"from TB_CDE_LABEL a "+
				"LEFT JOIN TB_CDE_PHOTOG_LABEL_RELAT b  "+
				"on a.LABEL_ID = b.LABEL_ID and b.LABEL_MBODY_ID= ? ";
		if("".equals(tagName)){
			sql+="WHERE a.LABEL_TYPE ='P' order by a.LABEL_ID asc";
		}else {
			sql+="WHERE a.LABEL_TYPE ='P' and a.LABEL_NAME like '%"+tagName+"%' order by a.LABEL_ID asc";
		}
				
		Object [] value = {PHOTOG_ID};
		JSONArray result = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql,value));
		return result;
	}


	public String operateLabel(String PHOTOG_ID, String LABEL_ID, String operate) {
		String sql ="";
		Object [] value ={LABEL_ID,PHOTOG_ID};
		if("add".equals(operate)){
			String sql1="SELECT case when  MAX(LABEL_ORDER) is NULL THEN	0 ELSE MAX(LABEL_ORDER) END "+
					"FROM TB_CDE_PHOTOG_LABEL_RELAT where LABEL_MBODY_ID = ?  ";
			Object [] value1 = {PHOTOG_ID};
			int order = this.getJdbcTemplate().queryForInt(sql1, value1)+1;
			sql+="INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER) VALUES(?,?,"+order+")";
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


	public boolean updateHeadImg(String PHOTOG_ID, String filename) {
		String sql ="UPDATE TB_PHOTOG_PIC_INFO SET FILE_NAME = ? WHERE PHOTOG_ID= ? and PIC_TYPE ='头像'";
		Object [] value = {filename,PHOTOG_ID};
		int row =  this.getJdbcTemplate().update(sql, value);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean uploadStrandImg(String PHOTOG_ID, String filename) {
		Object [] value1 = {PHOTOG_ID};
		String sql1 = "SELECT (CASE WHEN max(SHOW_ORDER)  IS NULL THEN 0 ELSE MAX(SHOW_ORDER) END) as maxNum from TB_PHOTOG_PIC_INFO WHERE PHOTOG_ID=  ? ";
		int nextnum = this.getJdbcTemplate().queryForInt(sql1,value1)+1;
		String sql ="INSERT INTO TB_PHOTOG_PIC_INFO (PHOTOG_ID,PIC_TYPE,FILE_NAME,SHOW_FLAG,SHOW_ORDER) VALUES ( ? , '标准照' , ? , 1 , ?)";
		Object [] value = {PHOTOG_ID,filename,nextnum};
		int row =  this.getJdbcTemplate().update(sql, value);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}


	
	public String deleteThisStrandImg(String imgname) {
		String sql ="DELETE from TB_PHOTOG_PIC_INFO WHERE FILE_NAME = ? ";
		Object [] value ={imgname};
		int result = this.getJdbcTemplate().update(sql,value);
		if(1==result){
			return "true";
		}else {
			return "false";
		}
	}
	
	public String changeShowStatus(String imgname,String status) {
		String sql ="UPDATE TB_PHOTOG_PIC_INFO SET SHOW_FLAG =  ? WHERE FILE_NAME = ? ";
		Object [] value ={status,imgname};
		int result = this.getJdbcTemplate().update(sql,value);
		if(1==result){
			return "true";
		}else {
			return "false";
		}
	} 
	
	public String updateStrandOrder(String [] imgnamearr) {
		String sql ="UPDATE TB_PHOTOG_PIC_INFO SET SHOW_ORDER =  ? WHERE FILE_NAME = ? ";
		for (int i = 0; i < imgnamearr.length; i++) {
			Object [] value ={i,imgnamearr[i]};
			int result = this.getJdbcTemplate().update(sql,value);
			if(1==result){
				continue;
			}else {
				return "false";
			}
		}
		return "true";
		
	}


	public String changeStatusData(String pHOTOG_ID,String aUDIT_STATUS, String aUDIT_DESC,String aUDIT_PERSN) {
		String sql1 ="UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS = ? WHERE AUDIT_MBODY_ID =? AND AUDIT_MBODY_TYPE ='P'";
		Object [] value1 = {aUDIT_STATUS,pHOTOG_ID};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1!=1){
			return "false";
		};
		String sql2 ="INSERT INTO TB_SYS_AUDIT_RESULT(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN) VALUES (?,?,?,?,?)";
		Object [] value2 = {pHOTOG_ID,"P",aUDIT_STATUS,aUDIT_DESC,aUDIT_PERSN};
		int num2 = this.getJdbcTemplate().update(sql2,value2);
		if(num2!=1){
			return "false";
		}else{
			return "true";
		}
		
	}


	public JSONObject addDefinedTag(String pHOTOG_ID, String tagName, String tagDesc) {
		JSONObject result = new JSONObject();
		String label_id="";
		String sql="SELECT LABEL_ID FROM TB_CDE_LABEL where  LABEL_TYPE = 'P' AND LABEL_NAME = ?";
		Object [] value = {tagName};
		List<Map<String,Object>> list =this.getJdbcTemplate().queryForList(sql,value);
		if(list!=null&&list.size()!=0){
			label_id = list.get(0).get("LABEL_ID").toString();
			result.put("success", false);
			result.put("message", "已有该标签，请重新自定义");
			return result;
		}else{
			String sql1 ="SELECT COUNT(*) FROM TB_CDE_LABEL where  LABEL_TYPE = 'P'";
			int num1 =this.getJdbcTemplate().queryForInt(sql1)+1;
			if(num1<=9){
				label_id ="L_P000000"+num1;
			}else if(num1<=99){
				label_id ="L_P00000"+num1;
			}else if(num1<=999){
				label_id ="L_P0000"+num1;
			};
			String insertsql = "INSERT INTO TB_CDE_LABEL(LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC)VALUES (?,?,'P',?)";
			Object [] insertValue ={label_id,tagName,tagDesc};
			int insertNum = this.getJdbcTemplate().update(insertsql,insertValue);
			if(insertNum!=1){
				result.put("success", false);
				return result;
			};
		};
		
		String sql2="SELECT case when  MAX(LABEL_ORDER) is NULL THEN	0 ELSE MAX(LABEL_ORDER) END "+
				"FROM TB_CDE_PHOTOG_LABEL_RELAT where LABEL_MBODY_ID = ?  ";
		Object [] value2 = {pHOTOG_ID};
		int order = this.getJdbcTemplate().queryForInt(sql2, value2)+1;
		
		String sql3 ="INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER) VALUES(?,?,"+order+")";
		Object [] value3 ={label_id,pHOTOG_ID};
		int updateNum = this.getJdbcTemplate().update(sql3,value3);
		if(updateNum!=1){
			result.put("success", false);
			return result;
		}
		result.put("success", true);
		result.put("label_id", label_id);
		return result;
	}

}
