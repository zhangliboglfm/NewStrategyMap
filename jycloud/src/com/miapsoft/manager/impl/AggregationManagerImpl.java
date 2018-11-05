package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.AggregationManager;
import com.miapsoft.util.IdGenerateUtil;
@Service("aggregationManager")
public class AggregationManagerImpl extends AbstractManager implements AggregationManager {

	public String addaggregation(String agid,String name, String othername, String allname,
			String describe) {
		String repeatSql ="SELECT COUNT(*) FROM TB_COMP_BASE_INFO WHERE COMP_NAME='"+name+"'";
		int repCount = this.getJdbcTemplate().queryForInt(repeatSql);
		if (repCount==0) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_COMP_BASE_INFO";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count>=0){
			count=count+1;
		}else{
			count=0;
		}
		String rgid=IdGenerateUtil.getAggregationId(count,agid);
		String statesql="INSERT INTO TB_SYS_CG_AUDIT_STATUS VALUES(?,?,?,?)";
		Object[] statevalue = {rgid,"CPG",1,curdate};
		int stateresult=this.getJdbcTemplate().update(statesql,statevalue);
		if(stateresult==1){
		String sql1="INSERT INTO TB_COMP_BASE_INFO VALUES(?,?,?,?,?,?,?,?)";
		Object[] value = {rgid,allname,describe,agid,count,curdate,name,othername};
		int result=this.getJdbcTemplate().update(sql1,value);
		if(result==1){
			return rgid;
		}else{
			return "fail";
		}
		}else{
			return "fail";
		}
	}
		return "fail";
	}

	public JSONArray getaggregationInfo(String agid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_COMP_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.COMP_ID=B.AUDIT_MBODY_ID WHERE A.CGER_ID=?";
		Object[] value = {agid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("compname", map.get("COMP_NAME"));
				onedata.put("comothername", map.get("COMP_ALIAS"));
				onedata.put("comallname", map.get("COMP_WHOLENAME"));
				onedata.put("compdesc", map.get("COMP_DESC"));
				onedata.put("comstatus", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}
	
	public JSONArray getauditaggregationInfo(String agid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_COMP_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.COMP_ID=B.AUDIT_MBODY_ID WHERE A.CGER_ID=? AND B.AUDIT_STATUS='1'";
		Object[] value = {agid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("compname", map.get("COMP_NAME"));
				onedata.put("comothername", map.get("COMP_ALIAS"));
				onedata.put("comallname", map.get("COMP_WHOLENAME"));
				onedata.put("compdesc", map.get("COMP_DESC"));
				onedata.put("comstatus", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}
	
	public JSONArray geteditaggregationInfo(String agid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_COMP_BASE_INFO WHERE COMP_ID=?";
		Object[] value = {agid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("compname", map.get("COMP_NAME"));
				onedata.put("comothername", map.get("COMP_ALIAS"));
				onedata.put("comallname", map.get("COMP_WHOLENAME"));
				onedata.put("compdesc", map.get("COMP_DESC"));
				result.add(onedata);
			}
		}
		return result;
	}

	public boolean editaggregationInfo(String compid, String name,
			String othername, String allname, String describe) {
		// TODO Auto-generated method stub
		String sql="UPDATE TB_COMP_BASE_INFO SET COMP_NAME=?" +
				",COMP_ALIAS=?,COMP_WHOLENAME=?,COMP_DESC=? WHERE COMP_ID=?";
		Object[] value = {name,othername,allname,describe,compid};
		int count0=this.getJdbcTemplate().update(sql,value);
		if(count0==1){
			String sqlchangestatus="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='1' WHERE AUDIT_MBODY_ID=?";
			Object[] valuechangestatus = {compid};
			int count2=this.getJdbcTemplate().update(sqlchangestatus,valuechangestatus);
			if(count2==1){
				return true;				
			}else{
				return false;	
			}
		}else{
			return false;
		}
	}
	public JSONArray searchaggregationInfo(String cgid,String name) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_COMP_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.COMP_ID=B.AUDIT_MBODY_ID WHERE A.CGER_ID='"+cgid+"' AND A.COMP_NAME LIKE '%"+name+"%'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("compname", map.get("COMP_NAME"));
				onedata.put("comothername", map.get("COMP_ALIAS"));
				onedata.put("comallname", map.get("COMP_WHOLENAME"));
				onedata.put("compdesc", map.get("COMP_DESC"));
				onedata.put("comstatus", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}
	public JSONArray searchauditaggregationInfo(String cgid,String name) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_COMP_BASE_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.COMP_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='1' AND A.CGER_ID='"+cgid+"' AND A.COMP_NAME LIKE '%"+name+"%'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("compname", map.get("COMP_NAME"));
				onedata.put("comothername", map.get("COMP_ALIAS"));
				onedata.put("comallname", map.get("COMP_WHOLENAME"));
				onedata.put("compdesc", map.get("COMP_DESC"));
				onedata.put("comstatus", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}
	
	public String getworkId(String name) {
		// TODO Auto-generated method stub
		String sql="SELECT CGER_ID FROM TB_CG_WORKS_BASE_INFO WHERE WORKS_NAME='"+name+"'";
		List<Map<String, Object>> list1 =this.getJdbcTemplate().queryForList(sql);
		String ag_id="";
		if (list1!=null&&list1.size()!=0){
			ag_id=(String) list1.get(0).get("WORKS_ID");
		}	
		return ag_id;
	}

	public boolean updateworkId(String workid, String compid) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_COMP_WORKS_RELAT WHERE COMP_ID=?";
		Object[] value = {compid};
		int count=this.getJdbcTemplate().queryForInt(sql0,value);
		if(count!=0){
			count=count+1;
		}else{
			count=0;
		}
		String sql="INSERT INTO TB_CDE_CG_COMP_WORKS_RELAT VALUES(?,?,?,?)";
		Object[] value2 = {compid,count,curdate,workid};
		int count0=this.getJdbcTemplate().queryForInt(sql,value2);
		if(count0==0){
			return false;
		}else{
			return true;
		}
	}

	public JSONArray getaggregationagdata(String compid,String cgid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_WORKS_BASE_INFO C LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON C.WORKS_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='5' AND C.CGER_ID=? AND C.WORKS_ID NOT IN(SELECT WORKS_ID FROM TB_CDE_CG_COMP_WORKS_RELAT A WHERE A.COMP_ID=?)";
		Object[] value = {cgid,compid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}

	public JSONArray searchaggregationagdata(String compid,String cgid,String cgname) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_WORKS_BASE_INFO C LEFT JOIN TB_SYS_CG_AUDIT_STATUS B " +
				"ON C.WORKS_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='5' AND " +
				"C.CGER_ID='"+cgid+"' AND C.WORKS_NAME like '%"+cgname+"%' AND C.WORKS_ID NOT IN(SELECT WORKS_ID FROM TB_CDE_CG_COMP_WORKS_RELAT A WHERE A.COMP_ID='"+compid+"')";
		/*Object[] value = {cgid,compid,cgname};*/
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}	
	
	public JSONArray getnewaggregationagdata(String cgid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_WORKS_BASE_INFO C LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON C.WORKS_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='5' AND C.CGER_ID=?";
		Object[] value = {cgid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}
	public JSONArray searchnewaggregationagdata(String cgid,String cgname) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_WORKS_BASE_INFO C LEFT JOIN TB_SYS_CG_AUDIT_STATUS B " +
				"ON C.WORKS_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='5' AND C.CGER_ID='"+cgid+"' AND C.WORKS_NAME like '%"+cgname+"%'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}		
	public String addaggregationmemberdata(String compid, String worksid,
			String worksname) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_COMP_WORKS_RELAT";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count>=0){
			count=count+1;
		}else{
			count=0;
		}
			String sql2="INSERT INTO TB_CDE_CG_COMP_WORKS_RELAT VALUES(?,?,?,?)";
			Object [] value2 = {compid,count,curdate,worksid};
			int count1=this.getJdbcTemplate().update(sql2,value2);
			if(count1==1){
				return "SUCCESS";
			}else{
				return "FAIL";
			}
		}
	
	
	
	public JSONArray getaggregationImageInfo(String compid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT A.WORKS_ID,C.IS_IMPT_WORKS,A.COMP_ID,B.PIC_NAME,C.WORKS_NAME,D.AUDIT_STATUS FROM TB_CDE_CG_COMP_WORKS_RELAT A LEFT JOIN "+
					"TB_CDE_CG_WORKS_PIC_RELAT B ON A.WORKS_ID=B.WORKS_ID LEFT JOIN " +
					"TB_CG_WORKS_BASE_INFO C ON B.WORKS_ID=C.WORKS_ID LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON C.WORKS_ID=D.AUDIT_MBODY_ID WHERE A.COMP_ID=? AND B.SHOW_ORDER=1 ORDER BY A.SHOW_ORDER";
		Object[] value = {compid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i <list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("picname", map.get("PIC_NAME"));
				onedata.put("compid", map.get("COMP_ID"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				onedata.put("isimpt", map.get("IS_IMPT_WORKS"));
				onedata.put("workstate", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}
	
	public JSONArray getInsertImageInfo(String worksid) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT A.WORKS_ID,A.WORKS_NAME,B.PIC_NAME FROM " +
				"TB_CG_WORKS_BASE_INFO A LEFT JOIN  TB_CDE_CG_WORKS_PIC_RELAT B " +
				"ON A.WORKS_ID=B.WORKS_ID WHERE A.WORKS_ID=? ORDER BY B.SHOW_ORDER";
		Object[] value = {worksid};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < 1; i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("worksid", map.get("WORKS_ID"));
				onedata.put("picname", map.get("PIC_NAME"));
				onedata.put("worksname", map.get("WORKS_NAME"));
				result.add(onedata);
			}
		}
		return result;
	}

	public String newaggregationImageInfo(String compid, String worksid) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_COMP_WORKS_RELAT";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count>=0){
			count=count+1;
		}else{
			count=0;
		}
		String sql="INSERT INTO TB_CDE_CG_COMP_WORKS_RELAT VALUES(?,?,?,?)";
		Object[] value = {compid,count,curdate,worksid};
		int count1=this.getJdbcTemplate().update(sql,value);
		if(count1==1){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
	
	public String editaggregationImageInfo(String compid, String worksid) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_COMP_WORKS_RELAT";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count>=0){
			count=count+1;
		}else{
			count=0;
		}
		String sql="INSERT INTO TB_CDE_CG_COMP_WORKS_RELAT VALUES(?,?,?,?)";
		Object[] value = {compid,count,curdate,worksid};
		int count1=this.getJdbcTemplate().update(sql,value);
		if(count1==1){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
	public boolean clearCOMP_workrelation(String compid){
		String sqlclear="DELETE FROM TB_CDE_CG_COMP_WORKS_RELAT WHERE COMP_ID=?";
		Object[] value = {compid};
		int count=this.getJdbcTemplate().update(sqlclear,value);
		if(count==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteaggregationmember(String compid,String worksid) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM TB_CDE_CG_COMP_WORKS_RELAT WHERE COMP_ID=? AND WORKS_ID=?";
		Object[] value = {compid,worksid};
		int count=this.getJdbcTemplate().update(sql,value);
		if(count==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteaggregationInfo(String compid) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM TB_COMP_BASE_INFO WHERE COMP_ID=?";
		Object[] value = {compid};
		int count=this.getJdbcTemplate().update(sql,value);
		if(count==1){
			String sql2="DELETE FROM TB_CDE_CG_COMP_WORKS_RELAT WHERE COMP_ID=?";
			Object[] value2 = {compid};
			int count2=this.getJdbcTemplate().update(sql2,value2);
				String sql3="DELETE FROM TB_SYS_CG_AUDIT_STATUS WHERE AUDIT_MBODY_ID=?";
				Object[] value3 = {compid};
				int count3=this.getJdbcTemplate().update(sql3,value3);
				if(count3==1){
					return true;
				}else{
					return false;
				}
		}else{
			return false;
		}
	}
}
