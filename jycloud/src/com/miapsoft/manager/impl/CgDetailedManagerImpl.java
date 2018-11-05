package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CgDetailedManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CgDetailedManagerImpl extends AbstractManager implements CgDetailedManager{
	
	public JSONObject getDerpherBaseInfo(String cgId) {
		JSONObject result = new JSONObject();
		String auditSql = "";
		String sql = "SELECT A.CGER_NAME,(CASE WHEN A.GENDER  = 'M' THEN '男' WHEN A.GENDER  = 'F' THEN '女' ELSE '' END) AS PHOTOG_SEX, "+
				"A.BORN_TIME,A.DEATH_TIME,A.SEC_NAME,A.NICK_NAME,A.AG_ID,A.DESCENT,A.BIRTH_PLACE,A.ALIAS,A.IMPT_DEEDS,A.DEAL_TIME,B.NATION_NAME,B.NATION_CODE," +
				"C.DYNASTY_NAME,C.DYNASTY_CODE,D.AG_NAME,G.FILE_NAME,T.AUDIT_DESC,E.AUDIT_STATUS AS STATUS_CODE," +
				"CASE E.AUDIT_STATUS WHEN '2' THEN '驳回' WHEN '3' THEN '回退' WHEN '4' THEN '待发布' WHEN '5' THEN '已审核' ELSE '待审核' END AS AUDIT_STATUS "+ 
				"FROM TB_CG_BASE_INFO A "+
				"LEFT JOIN TB_CDE_CG_NATION B ON A.NATION = B.NATION_CODE "+
				"LEFT JOIN TB_CDE_CG_DYNASTY C ON A.DYNASTY = C.DYNASTY_CODE "+
				"LEFT JOIN TB_AG_INFO D ON A.AG_ID = D.AG_ID "+
				"LEFT JOIN TB_CG_PIC_INFO R ON A.CGER_ID=R.CGER_ID AND R.SHOW_FLAG='1' "+
				"LEFT JOIN TB_SYS_CG_AUDIT_STATUS E ON A.CGER_ID = E.AUDIT_MBODY_ID AND E.AUDIT_MBODY_TYPE = 'CG' "+
				"LEFT JOIN TB_SYS_CG_AUDIT_RESULT T ON A.CGER_ID = T.AUDIT_MBODY_ID AND E.AUDIT_STATUS = T.AUDIT_STATUS "+
				"LEFT JOIN TB_CG_PIC_INFO G ON A.CGER_ID = G.CGER_ID "+
				"WHERE A.CGER_ID = ?";
		String maxTime = "SELECT MAX(DEAL_TIME) AS DEAL_TIME FROM TB_SYS_CG_AUDIT_RESULT WHERE AUDIT_MBODY_ID = '"+cgId+"'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(maxTime);
		if (list2.size()!=0) {
			String timeD = list2.get(0).get("DEAL_TIME")+"";
			if (timeD!=null&&!"null".equals(timeD)) {
				auditSql=" AND T.DEAL_TIME = '"+timeD+"'";
			}
		}
		sql+=auditSql;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, cgId);
		if(list.size()!=0){
			Map<String, Object> map = list.get(0);
			result.put("cName", map.get("CGER_NAME"));//名字
			result.put("cSex", map.get("PHOTOG_SEX"));//性别
			result.put("cNationCode", map.get("NATION_CODE"));//民族id
			result.put("cNation", map.get("NATION_NAME"));//民族
			result.put("cDynastyCode", map.get("DYNASTY_CODE"));//朝代id
			result.put("cDynasty", map.get("DYNASTY_NAME"));//朝代
			result.put("cSecName", map.get("SEC_NAME"));//字
			result.put("cNickName", map.get("NICK_NAME"));//号
			result.put("cDescent", map.get("DESCENT"));//祖籍
			result.put("cBirthPlace", map.get("BIRTH_PLACE"));//出生地
			result.put("cAlias", FormateAlias(map.get("ALIAS")==null ? "" : map.get("ALIAS").toString()));//别名
			result.put("agId", map.get("AG_ID"));//成就Id
			result.put("cAgName", map.get("AG_NAME"));//成就
			result.put("cImptDeeds", map.get("IMPT_DEEDS"));//重要事迹
			result.put("cBornTime", FormateYear(map.get("BORN_TIME")==null ? "" : map.get("BORN_TIME").toString()));//出生
			result.put("cDeathTime", FormateYear(map.get("DEATH_TIME")==null ? "" : map.get("DEATH_TIME").toString()));//去世
			result.put("cDealTime", map.get("DEAL_TIME")==null ? "" : map.get("DEAL_TIME").toString());//修改时间
			result.put("cHeadImage", map.get("FILE_NAME"));//头像路径
			result.put("cAuditState", map.get("AUDIT_STATUS"));//审核状态
			result.put("cStateCode", map.get("STATUS_CODE"));//审核状态码
			result.put("cAuditResult", map.get("AUDIT_DESC")==null?"":map.get("AUDIT_DESC"));//审核描述
			String lbsql = "SELECT A.WORKS_NAME,A.WORKS_ID FROM TB_CG_WORKS_BASE_INFO	A LEFT JOIN TB_CG_BASE_INFO B ON A.CGER_ID = B.CGER_ID WHERE A.IS_IMPT_WORKS = '1' AND A.CGER_ID = ?";
			List<Map<String, Object>> listlable = this.getJdbcTemplate().queryForList(lbsql, cgId);
			JSONArray label = new JSONArray();
			if(listlable.size()!=0){
				for (int i = 0; i < listlable.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("worksId", listlable.get(i).get("WORKS_ID"));//作品id
					obj.put("worksName", listlable.get(i).get("WORKS_NAME"));//名称
					label.add(obj);
				}
				result.put("pLable", label);
			}else{
				result.put("pLable", label);
			}
		}
		System.err.println(result);
		return result;
	}
	
	private String FormateAlias(String alias){
		String resstr = "";
		if( !"".equals(alias) && alias != null){
			resstr = alias.substring(1).replaceAll("#","、");
		}
		return resstr;
	}
	private String FormateYear(String yearstr){
		String resstr = "";
		if(yearstr.length()==7){
			resstr = yearstr.substring(0,3)+"年"+(yearstr.substring(3,yearstr.length()).equals("0000")?"": yearstr.substring(3,5)+"月"+yearstr.substring(5,7)+"日" );
		}else if(yearstr.length()==8){
			int acyear = Integer.valueOf(yearstr.substring(0,4));
			resstr = (acyear>0?yearstr.substring(0,4)+"年" : "公元前"+yearstr.substring(1,4)+"年")+(yearstr.substring(4,yearstr.length()).equals("0000")? "" : yearstr.substring(4,6)+"月"+yearstr.substring(6,8)+"日" );
		}
		return resstr;
	}
	/**
	 * 获取朝代
	 */
	public JSONArray getDynastys() {
		JSONArray data = new JSONArray();
		String sql = "SELECT DYNASTY_CODE,DYNASTY_NAME FROM TB_CDE_CG_DYNASTY";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("nationCode", list.get(i).get("DYNASTY_CODE"));
				obj.put("nationName", list.get(i).get("DYNASTY_NAME"));
				data.add(obj);
			}
		}
		return data;
	}
	/**
	 * 获取民族
	 */
	public JSONArray getNation() {
		JSONArray data = new JSONArray();
		String sql = "SELECT NATION_CODE,NATION_NAME FROM TB_CDE_CG_NATION";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("nationCode", list.get(i).get("NATION_CODE"));
				obj.put("nationName", list.get(i).get("NATION_NAME"));
				data.add(obj);
			}
		}
		return data;
	}
	//获取地位码表信息
	public JSONArray getAgName() {
		JSONArray data = new JSONArray();
		String sql = "SELECT AG_ID,AG_NAME FROM TB_AG_INFO";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("agid", list.get(i).get("AG_ID"));
				obj.put("agName", list.get(i).get("AG_NAME"));
				data.add(obj);
			}
		}
		return data;
	}
	//
	public JSONObject getAllCGWorks(String cgId, String cgWkName) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//work名称
		JSONArray array2 = new JSONArray();//workID   十
		String sql = "SELECT WORKS_ID,WORKS_NAME FROM TB_CG_WORKS_BASE_INFO WHERE CGER_ID = '"+cgId+"' ";
		if (cgWkName!=null&&!"null".equals(cgWkName)) {
			sql+=" AND WORKS_NAME LIKE '%"+cgWkName.trim()+"%'";
		}
		sql+="ORDER BY SHOW_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		JSONArray label = new JSONArray();
		for (int i = 0; i < list1.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("workID", list1.get(i).get("WORKS_ID"));
			obj.put("worksName", list1.get(i).get("WORKS_NAME"));
			label.add(obj);
		}
		result.put("cgWorks", label);
		return result;
	}
	//对书法家作品进行操作
	public int updateWorksI(String cgId, String arr) {
		JSONArray nameArray2 = JSONArray.fromObject(arr);
		String resetSql = "UPDATE TB_CG_WORKS_BASE_INFO SET IS_IMPT_WORKS = '0' WHERE CGER_ID = '"+cgId+"'";
		this.getJdbcTemplate().update(resetSql);
		String updateSql = "UPDATE TB_CG_WORKS_BASE_INFO SET IS_IMPT_WORKS = '1' WHERE WORKS_ID = ?";
		for (int i = 0; i < nameArray2.size(); i++) {
			this.getJdbcTemplate().update(updateSql,nameArray2.getString(i));
		}
		return 1;
	}
	//ID、名称、字、号、性别、民族、出生日期、朝代、成就\地位、出生地、祖籍、别称、去世时间、重要事迹
	public int updateCgBaseInfo(String cgId, String cgName,String iptCgSecName, String iptCgNickName, String iptCgPhotogSex,
			String iptCgNation, String iptCgBornTime, String iptCgDynasty, String iptCgAgName, String iptCgBirthPlace, String iptCgDescent,
			String iptCgAlias, String iptCgDeathTime, String iptCgImptDeeds) {
		String cgGender="男".equals(iptCgPhotogSex.trim())?"M":"F";
		String brithDate = formatUpDate(iptCgBornTime);
		String deathDate = formatUpDate(iptCgDeathTime);
		iptCgAlias="#"+iptCgAlias.replace("、", "#");
		String updateSql = "UPDATE TB_CG_BASE_INFO SET CGER_NAME =? , GENDER = ?, NATION=?, DYNASTY = ? , BORN_TIME = ? , DEATH_TIME = ? , SEC_NAME = ? "+
							", NICK_NAME = ? , DESCENT = ? , BIRTH_PLACE = ? , ALIAS = ? "+
							", AG_ID = ? , IMPT_DEEDS = ? , DEAL_TIME = now() WHERE CGER_ID = ?";
		Object [] values = {cgName,cgGender,iptCgNation,iptCgDynasty,brithDate,deathDate,iptCgSecName,iptCgNickName,iptCgDescent,iptCgBirthPlace,iptCgAlias,iptCgAgName,iptCgImptDeeds,cgId};
		int count=this.getJdbcTemplate().update(updateSql,values);
		return count;
	}
	//更新出生、去世日期时的时间格式化
	public String formatUpDate(String timeDate) {
		String formateDate="";
		String adOrBc=timeDate.substring(0,3);
		int yearIndex = timeDate.indexOf("年");
		int monthIndex = timeDate.indexOf("月");
		int dayIndex = timeDate.indexOf("日");
		if (yearIndex<0) {
			return formateDate;
		}
		if ("公元前".equals(adOrBc)) {
			formateDate+="-"+timeDate.substring(3,timeDate.indexOf("年"));
		} else {
			formateDate+=timeDate.substring(0,timeDate.indexOf("年"));
		}
		if (monthIndex>0) {
			formateDate+=timeDate.substring(timeDate.indexOf("年")+1,timeDate.indexOf("月"));
			if (dayIndex>0) {
				formateDate+=timeDate.substring(timeDate.indexOf("月")+1,timeDate.indexOf("日"));
			} else {
				formateDate+="00";
			}
		} else {
			formateDate+="0000";
		}
		return formateDate;
	}
	//保存书法家头像信息
	public int saveHeadImg(String cgId, String headImgP) {
		String addSql = "INSERT INTO TB_CG_PIC_INFO (CGER_ID,FILE_NAME,SHOW_FLAG,SHOW_ORDER,DEAL_TIME) VALUES (?,?,'1',0,NOW())";
		String updateSql = "UPDATE TB_CG_PIC_INFO SET FILE_NAME = ? WHERE CGER_ID = ? ";
		String selSql = "SELECT COUNT(1) FROM TB_CG_PIC_INFO WHERE CGER_ID ='"+cgId+"'";
		int count1=this.getJdbcTemplate().queryForInt(selSql);
		Object [] values1 = {cgId,headImgP};
		Object [] values2 = {headImgP,cgId};
		int count2=0;
		if (count1>0) {
			count2=this.getJdbcTemplate().update(updateSql,values2);
		} else {
			count2=this.getJdbcTemplate().update(addSql,values1);
		}
		return count2;
	}
	//审核书法家
	public String auditCallig(String cgId, String auditStatus, String auditDesc, String auditPersn) {
		String sql1 ="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS = ? WHERE AUDIT_MBODY_ID =? AND AUDIT_MBODY_TYPE ='CG'";
		Object [] value1 = {auditStatus,cgId};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1==0){
			String sql11 ="INSERT INTO TB_SYS_CG_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,'CG',?,NOW())";
			Object [] value11 = {cgId,auditStatus};
			int num11 = this.getJdbcTemplate().update(sql11,value11);
			if(num11!=1){
				return "false";
			}
		}else if(num1!=1){
			return "false";
		}
		String sql2 ="INSERT INTO TB_SYS_CG_AUDIT_RESULT(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		Object [] value2 = {cgId,"CG",auditStatus,auditDesc,auditPersn};
		int num2 = this.getJdbcTemplate().update(sql2,value2);
		if(num2!=1){
			return "false";
		}else{
			return "true";
		}
	}

}
