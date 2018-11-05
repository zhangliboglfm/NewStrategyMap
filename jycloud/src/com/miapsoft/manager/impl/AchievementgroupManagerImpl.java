package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.AchievementgroupingManager;
import com.miapsoft.util.IdGenerateUtil;
@Service("achievementgroupManager")
public class AchievementgroupManagerImpl extends AbstractManager implements AchievementgroupingManager {

	public JSONArray getAchievementInfo(){
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_AG_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.AG_ID=B.AUDIT_MBODY_ID";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("agname", map.get("AG_NAME"));
				onedata.put("agid", map.get("AG_ID"));
				onedata.put("agothername", map.get("AG_ALIAS"));
				onedata.put("agdesc", map.get("AG_DESC"));
				onedata.put("agstate", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}

	public JSONArray getWaitAchievementinfo(String status){
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_AG_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.AG_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS=?";
		Object[] value = {status};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("agname", map.get("AG_NAME"));
				onedata.put("agid", map.get("AG_ID"));
				onedata.put("agothername", map.get("AG_ALIAS"));
				onedata.put("agdesc", map.get("AG_DESC"));
				onedata.put("status", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;
	}	

	public JSONArray getAchievementagInfo(String id) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT B.CGER_NAME,B.CGER_ID,C.FILE_NAME FROM TB_AG_INFO A  LEFT JOIN TB_CG_BASE_INFO B ON " +
				" A.AG_ID=B.AG_ID LEFT JOIN TB_CG_PIC_INFO C ON B.CGER_ID=C.CGER_ID WHERE A.AG_ID=?";
		Object[] value = {id};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("cgerid", map.get("CGER_ID"));
				onedata.put("cgername", map.get("CGER_NAME"));
				onedata.put("filename", map.get("FILE_NAME"));
				result.add(onedata);
			}
		}
		return result;
	}

	public JSONArray searchAchievementagInfo(String str) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_AG_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.AG_ID=B.AUDIT_MBODY_ID WHERE A.AG_NAME LIKE '%"+str+"%'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("agname", map.get("AG_NAME"));
				onedata.put("agid", map.get("AG_ID"));
				onedata.put("agothername", map.get("AG_ALIAS"));
				onedata.put("agdesc", map.get("AG_DESC"));
				onedata.put("agstate", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;	
	}

	public JSONArray searchWaitAchievementagInfo(String str,String status) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_AG_INFO A LEFT JOIN TB_SYS_CG_AUDIT_STATUS B ON A.AG_ID=B.AUDIT_MBODY_ID WHERE B.AUDIT_STATUS='"+status+"' AND A.AG_NAME LIKE '%"+str+"%'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("agname", map.get("AG_NAME"));
				onedata.put("agid", map.get("AG_ID"));
				onedata.put("agothername", map.get("AG_ALIAS"));
				onedata.put("agdesc", map.get("AG_DESC"));
				onedata.put("status", map.get("AUDIT_STATUS"));
				result.add(onedata);
			}
		}
		return result;	
	}	
	public JSONArray searchAchievementagimageInfo(String id) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_AG_INFO A  LEFT JOIN TB_CG_BASE_INFO B ON " +
				" A.AG_ID=B.AG_ID LEFT JOIN TB_CG_PIC_INFO C ON B.CGER_ID=C.CGER_ID WHERE A.AG_ID=?";
		Object[] value = {id};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("cgerid", map.get("CGER_ID"));
				onedata.put("cgername", map.get("CGER_NAME"));
				onedata.put("filename", map.get("FILE_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}

	public String addAchievementag(String name,String othername,String desctribe) {
		// TODO Auto-generated method stub
		String repeatSql ="SELECT COUNT(*) FROM TB_AG_INFO WHERE AG_NAME='"+name+"'";
		int repCount = this.getJdbcTemplate().queryForInt(repeatSql);
		if (repCount==0) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_AG_INFO";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count!=0){
			count=count+1;
			/*count=show_order/10+1;*/
		}else{
			count=0;
			/*count=show_order/10;*/
		}
		String agid=IdGenerateUtil.getAchievementId(count,curdate);
		String statesql="INSERT INTO TB_SYS_CG_AUDIT_STATUS VALUES(?,?,?,?)";
		Object[] statevalue = {agid,"AG",1,curdate};
		int stateresult=this.getJdbcTemplate().update(statesql,statevalue);
		if(stateresult==1){
		String sql1="INSERT INTO TB_AG_INFO VALUES(?,?,?,?,?,?)";
		Object[] value = {agid,name,othername,desctribe,count,curdate};
		int result=this.getJdbcTemplate().update(sql1,value);
		if(result==1){
			return "success";
		}else{
			return "fail";
		}
		}
		}
		return "fail";
	}

	public boolean updatecgImg(String agimage_id, String filename) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sqlcountString="SELECT COUNT(*) FROM TB_CG_PIC_INFO WHERE CGER_ID=?";
		Object [] value0 = {agimage_id};
		int count0=this.getJdbcTemplate().queryForInt(sqlcountString,value0);
		if(count0==0){
			String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CG_PIC_INFO";
			int count=this.getJdbcTemplate().queryForInt(sql0);
			if(count>=0){
				count=count+1;
			}else{
				count=0;
			}
			String sql ="INSERT INTO TB_CG_PIC_INFO VALUES(?,?,?,?,?);";
			Object [] value = {agimage_id,filename,1,count,curdate};
			int row =  this.getJdbcTemplate().update(sql,value);
			if(row==1){
				return true;
			}else{
				return false;
			}
		}else{
			String sql ="UPDATE TB_CG_PIC_INFO SET FILE_NAME=? WHERE CGER_ID=?";
			Object [] value = {filename,agimage_id};
			int row =  this.getJdbcTemplate().update(sql,value);
			if(row==1){
				return true;
			}else{
				return false;
			}
		}
	}

	public String getAchievementId(String sondata,String name) {
		// TODO Auto-generated method stub
		String sql="SELECT CGER_ID FROM TB_CG_BASE_INFO WHERE CGER_NAME='"+name+"'";
		List<Map<String, Object>> list1 =this.getJdbcTemplate().queryForList(sql);
		String ag_id="";
		if (list1!=null&&list1.size()!=0){
			ag_id=(String) list1.get(0).get("CGER_ID");
			String sql1="UPDATE TB_CG_BASE_INFO SET AG_ID=? WHERE CGER_NAME=?";
			Object [] value = {sondata,name};
			int count=this.getJdbcTemplate().update(sql1,value);
		}
		
		return ag_id;
	}

	public boolean deletemember(String agid,String id) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql="UPDATE TB_CG_BASE_INFO SET AG_ID='' WHERE CGER_ID =?";
		Object [] value = {id};
		int count=this.getJdbcTemplate().update(sql,value);
		if(count==1){
			String sql3="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";
			Object [] value3 = {"1",curdate,agid};
			int count3=this.getJdbcTemplate().update(sql3,value3);
			if(count==1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;	
		}
/*		String sql1="DELETE FROM WHERE CGER_ID=?";
		Object [] value2 = {id};
		int count2=this.getJdbcTemplate().update(sql,value);
		if(count==1){
			return true;
		}else{
			return false;	
		}*/
	
	}
	
	public boolean deleteachievement(String id) {
		// TODO Auto-generated method stub
		String sql="UPDATE TB_CG_BASE_INFO SET AG_ID='' WHERE AG_ID =?";
		Object [] value = {id};
		int count=this.getJdbcTemplate().update(sql,value);
		String sql0="DELETE FROM TB_SYS_CG_AUDIT_STATUS WHERE AUDIT_MBODY_ID=?";
		Object [] value0 = {id};
		int count0=this.getJdbcTemplate().update(sql0,value0);
		if(count0==0){
			return false;
		}else{
		String sql1="DELETE FROM TB_AG_INFO WHERE AG_ID=?";
		Object [] value2 = {id};
		int count2=this.getJdbcTemplate().update(sql1,value2);
		if(count2==1){
			return true;
		}else{
			return false;	
		}
		}
	}

	public boolean adoptachievement(String userId,String id,String flag) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='5',DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";
		Object [] value = {curdate,id};
		int count0=this.getJdbcTemplate().update(sql,value);
		if(count0==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean rejectachievement(String userId,String id,String reason,String flag) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql="";
/*		if(flag.equals("AG")){*/
			sql="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='2' AND DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";		
/*		}else{
			sql="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='1' AND DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";			
		}*/
		Object [] value = {curdate,id};
		int count0=this.getJdbcTemplate().update(sql,value);
		if(count0!=1){
			return false;
		}else{	
			String sql1="INSERT INTO TB_SYS_CG_AUDIT_RESULT VALUES(?,?,?,?,?,?)";
			Object [] value1 = {id,flag,"2",reason,userId,curdate};
			int count1=this.getJdbcTemplate().update(sql1,value1);
			if(count1==1){
				return true;
			}else{
				return false;
			}
		}
	}
	public boolean regresses(String userId,String id,String reason,String flag) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='1',DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";
		Object [] value = {curdate,id};
		int count0=this.getJdbcTemplate().update(sql,value);
		if(count0!=1){
			return false;
		}else{	
			String sql1="INSERT INTO TB_SYS_CG_AUDIT_RESULT VALUES(?,?,?,?,?,?)";
			Object [] value1 = {id,flag,"3",reason,userId,curdate};
			int count1=this.getJdbcTemplate().update(sql1,value1);
			if(count1==1){
				return true;
			}else{
				return false;
			}
		}
	}


	public JSONArray getachievementagdata() {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_BASE_INFO WHERE AG_ID=''";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("cgerid", map.get("CGER_ID"));
				onedata.put("cgername", map.get("CGER_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}
	public JSONArray getsearchachievementagdata(String cgname) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CG_BASE_INFO WHERE CGER_NAME like '%"+cgname+"%' AND AG_ID=''";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("cgerid", map.get("CGER_ID"));
				onedata.put("cgername", map.get("CGER_NAME"));
				result.add(onedata);
			}
		}
		return result;		
	}
	public String addachievementmemberdata(String agid, String cgerid,
			String cgername) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql0="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_AG_RELAT";
		int count=this.getJdbcTemplate().queryForInt(sql0);
		if(count>=0){
			count=count+1;
		}else{
			count=0;
		}
		String sql1="UPDATE TB_CG_BASE_INFO SET AG_ID=?,DEAL_TIME=? WHERE CGER_ID=?";
		Object [] value1 = {agid,curdate,cgerid};
		int count0=this.getJdbcTemplate().update(sql1,value1);
		if(count0==1){
			String sql2="INSERT INTO TB_CDE_CG_AG_RELAT VALUES(?,?,?,?)";
			Object [] value2 = {agid,count,curdate,cgerid};
			int count1=this.getJdbcTemplate().update(sql2,value2);
			if(count1==1){
				String sql3="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=?";
				Object [] value3 = {"1",curdate,agid};
				int count3=this.getJdbcTemplate().update(sql3,value3);
				if(count3==1){
					return "SUCCESS";
				}else{
					return "FAIL";
				}
			}else{
				return "FAIL";
			}
		}else{
			return "FAIL";
		}
		
	}

	public JSONArray getauditreasondata(String type){
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		String sql="SELECT * FROM TB_CDE_AUDIT_BACK_REASON WHERE REASON_TYPE=?";
		Object [] value2 = {type};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql,value2);
		if (list1!=null&&list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list1.get(i);
				onedata.put("reasonname", map.get("REASON_NAME"));

				result.add(onedata);
			}
		}
		return result;
	}

}
