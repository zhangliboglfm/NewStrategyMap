package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CgWorksManager;

@Service("cgWorksManager")
public class CgWorksManagerImpl extends AbstractManager implements CgWorksManager{

	public JSONObject getPagenum(String cgerId, String workname,String pageNum, String pageSize) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		/*重要作品页数*/
		String sql1="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW'";
		/*其它作品页数*/
		String sql2="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' "+
				"and c.AUDIT_MBODY_TYPE='CGW'";
		int countNum1 = this.getJdbcTemplate().queryForInt(sql1);
		int countNum2 = this.getJdbcTemplate().queryForInt(sql2);
		int countNum = countNum1+countNum2;
		result.put("countNum", countNum);
		result.put("countNum1", countNum1);
		result.put("countNum2", countNum2);
		/*重要作品*/
		String sql3="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
				"where d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' OR d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' order by a.SHOW_ORDER limit ?,?";
		/*其它作品*/
		String sql4="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
				"where  d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
				"and c.AUDIT_MBODY_TYPE='CGW' OR d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
				"and c.AUDIT_MBODY_TYPE='CGW' order by a.SHOW_ORDER limit ?,?";	
		value1.add(startRow);
		value1.add(Integer.parseInt(pageNum));
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql3,value1.toArray());
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql4,value1.toArray());
		result.put("list3", JSONArray.fromObject(list3));
		result.put("list4", JSONArray.fromObject(list4));
		return result;
	}

	public JSONObject getauditPagenum(String cgerId, String workname,String pageNum, String pageSize,String status) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		if(status.equals("10")){
		/*重要作品页数*/
		sql1="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5)";
		/*其它作品页数*/
		sql2="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' "+
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5)";
		}else{
			sql1="select count(*) from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"'";
			/*其它作品页数*/
			sql2="select count(*) from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' "+
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"'";					
		}
		int countNum1 = this.getJdbcTemplate().queryForInt(sql1);
		int countNum2 = this.getJdbcTemplate().queryForInt(sql2);
		int countNum = countNum1+countNum2;
		result.put("countNum", countNum);
		result.put("countNum1", countNum1);
		result.put("countNum2", countNum2);
		/*重要作品*/
		if(status.equals("10")){
		sql3="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
				"where d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5) OR d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5) order by a.SHOW_ORDER limit ?,?";
		/*其它作品*/
		sql4="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
				"where d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5) OR d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS IN(1,5) order by a.SHOW_ORDER limit ?,?";	
		}else{
			sql3="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
					"where d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"' OR  d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"' order by a.SHOW_ORDER limit ?,?";
			/*其它作品*/
			sql4="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS,c.AUDIT_STATUS,d.PIC_NAME from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"left join TB_CDE_CG_WORKS_PIC_RELAT d on a.WORKS_ID=d.WORKS_ID " +
					"where d.SHOW_ORDER=1 AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"' OR d.SHOW_ORDER IS NULL AND b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"' order by a.SHOW_ORDER limit ?,?";				
			
		}
		value1.add(startRow);
		value1.add(Integer.parseInt(pageNum));
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql3,value1.toArray());
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql4,value1.toArray());
		result.put("list3", JSONArray.fromObject(list3));
		result.put("list4", JSONArray.fromObject(list4));
		return result;
	}	
	
	public JSONObject getimpworks(String cgerId, String workname, String pageNum, String pageSize) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		/*重要作品页数*/
		String sql1="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5'";
		int countNum1 = this.getJdbcTemplate().queryForInt(sql1);
		result.put("countNum", countNum1);
		/*重要作品*/
		String sql3="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='1' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5' order by a.SHOW_ORDER limit ?,?";
		value1.add(startRow);
		value1.add(Integer.parseInt(pageNum));
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql3,value1.toArray());
		result.put("list", JSONArray.fromObject(list3));
		return result;
	}

	public JSONObject getothworks(String cgerId, String workname, String pageNum, String pageSize) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		/*其它作品页数*/
		String sql2="select count(*) from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' "+
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5'";
		int countNum2 = this.getJdbcTemplate().queryForInt(sql2);
		result.put("countNum", countNum2);
		/*其它作品*/
		String sql4="select a.WORKS_ID,a.WORKS_NAME,a.CGER_ID,a.SHOW_ORDER,a.IS_IMPT_WORKS from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID='"+cgerId+"' and a.WORKS_NAME like '%"+workname+"%' and a.IS_IMPT_WORKS='0' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5' order by a.SHOW_ORDER limit ?,?";		
		value1.add(startRow);
		value1.add(Integer.parseInt(pageNum));
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql4,value1.toArray());
		result.put("list", JSONArray.fromObject(list4));
		return result;
	}

	public JSONObject getWorksDetail(String cgerid, String workid) {
		JSONObject result = new JSONObject();
		String sql="select a.* from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"where b.CGER_ID = '"+cgerid+"' and a.WORKS_ID = '"+workid+"'" ;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}

	public JSONObject getworkslist(String cgerid, String isimport) {
		JSONObject result = new JSONObject();
		String sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER,c.AUDIT_STATUS " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' order by a.SHOW_ORDER" ;
		String sql2="select d.PIC_NAME " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT d ON a.WORKS_ID = d.WORKS_ID WHERE d.SHOW_ORDER='1'AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' or d.SHOW_ORDER IS NULL AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' order by a.SHOW_ORDER" ;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		JSONArray array=new JSONArray();
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2);
		if(list2!=null){
			for (int i = 0; i < list2.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list2.get(i);
				onedata.put("PIC_NAME", map.get("PIC_NAME"));
				array.add(onedata);
			}
		}else{
			JSONObject obj = new JSONObject();
			obj.put("PIC_NAME","0");
			array.add(obj);
		}

		result.put("list2", array);
		return result;
	}
	
	public JSONObject getworkslistCPG(String cgerid, String isimport,String workid) {
		JSONObject result = new JSONObject();
		String sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER,c.AUDIT_STATUS " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' AND a.WORKS_ID='"+workid+"' order by a.SHOW_ORDER" ;
		String sql2="select d.PIC_NAME " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT d ON a.WORKS_ID = d.WORKS_ID WHERE d.SHOW_ORDER='1'AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' AND a.WORKS_ID='"+workid+"' or d.SHOW_ORDER IS NULL AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' AND a.WORKS_ID='"+workid+"' order by a.SHOW_ORDER" ;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		JSONArray array=new JSONArray();
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2);
		if(list2!=null){
			for (int i = 0; i < list2.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list2.get(i);
				onedata.put("PIC_NAME", map.get("PIC_NAME"));
				array.add(onedata);
			}
		}else{
			JSONObject obj = new JSONObject();
			obj.put("PIC_NAME","0");
			array.add(obj);
		}

		result.put("list2", array);
		return result;
	} 
	public JSONObject getauditworkslist(String cgerid, String isimport,String status) {
		JSONObject result = new JSONObject();
		String sql="";
		String sql2="";
		if(status.equals("10")){
			sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER,c.AUDIT_STATUS " +
					"from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"where b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW'  order by a.SHOW_ORDER" ;
			sql2="select d.PIC_NAME " +
					"from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT d ON a.WORKS_ID = d.WORKS_ID WHERE d.SHOW_ORDER='1'AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW' or d.SHOW_ORDER IS NULL AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW' order by a.SHOW_ORDER" ;			
			
		}else{
			sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER,c.AUDIT_STATUS " +
					"from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"where b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"'  order by a.SHOW_ORDER" ;
			sql2="select d.PIC_NAME " +
					"from TB_CG_WORKS_BASE_INFO a " +
					"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
					"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
					"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT d ON a.WORKS_ID = d.WORKS_ID WHERE d.SHOW_ORDER='1'AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"'  or d.SHOW_ORDER IS NULL AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
					"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='"+status+"'  order by a.SHOW_ORDER" ;			
		}
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		JSONArray array=new JSONArray();
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2);
		if(list2!=null){
			for (int i = 0; i < list2.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list2.get(i);
				onedata.put("PIC_NAME", map.get("PIC_NAME"));
				array.add(onedata);
			}
		}else{
			JSONObject obj = new JSONObject();
			obj.put("PIC_NAME","0");
			array.add(obj);
		}

		result.put("list2", array);
		return result;
	}
	
	public JSONObject getauditworkslist2(String cgerid, String isimport) {
		JSONObject result = new JSONObject();
		String sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"where b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5' order by a.SHOW_ORDER" ;
		String sql2="select d.PIC_NAME " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"left join TB_SYS_CG_AUDIT_STATUS c on a.WORKS_ID=c.AUDIT_MBODY_ID " +
				"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT d ON a.WORKS_ID = d.WORKS_ID WHERE d.SHOW_ORDER='1' AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5' OR d.SHOW_ORDER IS NULL AND b.CGER_ID = '"+cgerid+"' and a.IS_IMPT_WORKS='"+isimport+"' " +
				"and c.AUDIT_MBODY_TYPE='CGW' and c.AUDIT_STATUS='5'  order by a.SHOW_ORDER" ;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		JSONArray array=new JSONArray();
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2);
		if(list2!=null){
			for (int i = 0; i < list2.size(); i++) {
				JSONObject onedata = new JSONObject();
				Map<String, Object> map = list2.get(i);
				onedata.put("PIC_NAME", map.get("PIC_NAME"));
				array.add(onedata);
			}
		}else{
			JSONObject obj = new JSONObject();
			obj.put("PIC_NAME","0");
			array.add(obj);
		}

		result.put("list2", array);
		return result;
	}
	public JSONObject getworkinfors(String workid, String cgid) {
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		JSONArray array4 = new JSONArray();
		JSONArray array5 = new JSONArray();
		JSONArray array6 = new JSONArray();
		JSONArray array7 = new JSONArray();
		String sql="select a.WORKS_ID,a.CGER_ID,a.WORKS_NAME,a.SHOW_ORDER,a.WHOLENAME,a.YEARS,a.CGY_TYPE,a.WORDS_NUM,a.SPEC,a.PST_COLLECTION " +
				"from TB_CG_WORKS_BASE_INFO a " +
				"left join TB_CG_BASE_INFO b on a.CGER_ID=b.CGER_ID " +
				"where b.CGER_ID = '"+cgid+"' and a.WORKS_ID='"+workid+"' " +
				"order by a.SHOW_ORDER" ;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			array1.add((null==map.get("WORKS_NAME"))?"--":map.get("WORKS_NAME"));
			array2.add((null==map.get("WHOLENAME"))?"--":map.get("WHOLENAME"));
			array3.add((null==map.get("YEARS"))?"--":map.get("YEARS"));
			array4.add((null==map.get("CGY_TYPE"))?"--":map.get("CGY_TYPE"));
			array5.add((null==map.get("WORDS_NUM"))?"--":map.get("WORDS_NUM"));
			array6.add((null==map.get("SPEC"))?"--":map.get("SPEC"));
			array7.add((null==map.get("PST_COLLECTION"))?"--":map.get("PST_COLLECTION"));
		}
		array.add(array1);
		array.add(array2);
		array.add(array3);
		array.add(array4);
		array.add(array5);
		array.add(array6);
		array.add(array7);
		result.put("list",array);
		/*result.put("list", JSONArray.fromObject(list));*/
		return result;
	}

	public JSONObject getCGImgs(String workid, String cgid) {
		JSONObject result=new JSONObject();
		String sql="select A.WORKS_ID,A.PIC_NAME,A.SHOW_ORDER,B.CUT_TYPE from TB_CDE_CG_WORKS_PIC_RELAT A LEFT JOIN TB_CG_WORKS_BASE_INFO B ON A.WORKS_ID=B.WORKS_ID where A.WORKS_ID='"+workid+"' order by A.SHOW_ORDER";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}
	
	public String delworksImg(String pgId, String fileName) {
		String updatesql = "DELETE FROM TB_CDE_CG_WORKS_PIC_RELAT WHERE WORKS_ID=? AND PIC_NAME=?";
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
	
	public String delallworksImg(String pgId) {
		String updatesql = "DELETE FROM TB_CDE_CG_WORKS_PIC_RELAT WHERE WORKS_ID=?";
		int result = this.getJdbcTemplate().update(updatesql,pgId);
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
	
	
	public boolean updateworkinfors(String workid, String cgid,String worksname,String wholename,String years,String cgtype,String worknum,String spec,String pstcoll) {
		// TODO Auto-generated method stub
		if(worknum.equals("--")){
			worknum=null;			
		}
		String sql="UPDATE TB_CG_WORKS_BASE_INFO SET WORKS_NAME=?,WHOLENAME=?," +
				"YEARS=?,CGY_TYPE=?,WORDS_NUM=?,SPEC=?,PST_COLLECTION=? WHERE WORKS_ID=? AND CGER_ID=?";
		Object[] value = {worksname,wholename,years,cgtype,worknum,spec,pstcoll,workid,cgid};
		int count1=this.getJdbcTemplate().update(sql,value);
		if(count1==1){
			String sqlchangestatus="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS='1' WHERE AUDIT_MBODY_ID=?";
			Object[] valuechangestatus = {workid};
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


	public String updateworksImgOrder(String pgId, String fileName,
			String newOrder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updatesql = "UPDATE TB_CDE_CG_WORKS_PIC_RELAT SET SHOW_ORDER=?,DEAL_TIME=? WHERE WORKS_ID=? AND PIC_NAME=?";
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

	public JSONObject addworksImg(String pgId, String filename) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String selsql = "SELECT max(SHOW_ORDER) from TB_CDE_CG_WORKS_PIC_RELAT where WORKS_ID = ?";
		int maxOrder = this.getJdbcTemplate().queryForInt(selsql,pgId);
		//System.out.println("标准照顺序：");
		//System.out.println(maxOrder);
		
		String isql = "INSERT INTO TB_CDE_CG_WORKS_PIC_RELAT VALUES (?,?,?,?)";
		Object[] value = {pgId,filename,maxOrder+1,sdf.format(new Date())};
		result = this.getJdbcTemplate().update(isql,value);
		
		JSONObject object=new JSONObject();
		object.put("result", result);
		object.put("order", maxOrder+1);
		
		return object;
	}

	public JSONObject changeworksImg(String pgId,String filename1, String filename) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String selsql = "SELECT max(SHOW_ORDER) from TB_CDE_CG_WORKS_PIC_RELAT where WORKS_ID = ? AND PIC_NAME=?";
		Object[] value2 = {pgId,filename1};
		int maxOrder = this.getJdbcTemplate().queryForInt(selsql,value2);
		//System.out.println("标准照顺序：");
		//System.out.println(maxOrder);
		
		String isql = "UPDATE TB_CDE_CG_WORKS_PIC_RELAT SET PIC_NAME=?,DEAL_TIME=? WHERE WORKS_ID=? AND PIC_NAME=?";
		Object[] value = {filename,sdf.format(new Date()),pgId,filename1};
		result = this.getJdbcTemplate().update(isql,value);
		
		JSONObject object=new JSONObject();
		object.put("result", result);
		object.put("order", maxOrder);
		
		return object;
	}	
}
