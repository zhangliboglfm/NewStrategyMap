package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CGArtDetailManager;
import com.miapsoft.util.IdGenerateUtil;


@Service("cgArtManage")
public class CGArtDetailManagerImpl extends AbstractManager implements CGArtDetailManager {
	//查询所有文章封面及标题，并生成左侧树结构
	public JSONObject getAllCGArt(String cgName, String auditStatus) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//封面路径
		JSONArray array2 = new JSONArray();//封面标题
		JSONArray array3 = new JSONArray();//文章ID
		JSONArray array4 = new JSONArray();//审核状态码
		String articalSql="";
		String auditSql = "";
		if ("1".equals(auditStatus)) {
			auditSql = " AND D.AUDIT_STATUS = '1' ";
		} else if ("5".equals(auditStatus)){
			auditSql = " AND D.AUDIT_STATUS = '5' ";
		}
		if ("".equals(cgName.trim())) {
			articalSql = "SELECT A.ARTICLE_COVER,A.ARTICLE_TITLE,A.ARTICLE_ID,D.AUDIT_STATUS FROM TB_CG_ARTICLE_BASE_INFO A " +
							"LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON A.ARTICLE_ID = D.AUDIT_MBODY_ID " +
							"WHERE A.ARTICLE_TYPE ='TA' "+auditSql+" ORDER BY A.ARTICLE_ID";
		} else {
			articalSql = "SELECT A.ARTICLE_COVER,A.ARTICLE_TITLE,B.SHOW_ORDER,A.ARTICLE_ID,D.AUDIT_STATUS FROM TB_CG_ARTICLE_BASE_INFO A  "+
					"LEFT JOIN TB_CDE_CG_ARTICLE_RELAT B  "+
					"ON A.ARTICLE_ID = B.ARTICLE_ID "+
					"LEFT JOIN TB_CG_BASE_INFO C "+
					"ON B.CGER_ID = C.CGER_ID " +
					"LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON A.ARTICLE_ID = D.AUDIT_MBODY_ID "+
					"WHERE A.ARTICLE_TYPE ='TA' AND C.CGER_NAME LIKE '%"+cgName+"%' "+auditSql+" ORDER BY B.SHOW_ORDER";
		}
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(articalSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("ARTICLE_TITLE"));
			array2.add(list1.get(i).get("ARTICLE_COVER"));
			array3.add(list1.get(i).get("ARTICLE_ID"));
			array4.add(list1.get(i).get("AUDIT_STATUS"));
		}
		result.put("artName", array1);
		result.put("artCover", array2);
		result.put("artId", array3);
		result.put("auditStatus", array4);
		return result;
	}
	//查询文章名称
	public JSONObject getCGArtTit(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//名称
		JSONArray array2 = new JSONArray();//封面
		JSONArray array3 = new JSONArray();//审核
		JSONArray array4 = new JSONArray();//审核id
		JSONArray array5 = new JSONArray();//审核意见
		String titleSql = "SELECT A.ARTICLE_TITLE,A.ARTICLE_COVER,B.AUDIT_DESC,B.AUDIT_STATUS, "+
							"CASE B.AUDIT_STATUS WHEN '2' THEN '驳回' WHEN '3' THEN '回退' WHEN '4' THEN '待审核' WHEN '5' THEN '已审核' ELSE '待审核' END AS AUDIT_STATUS_DESC "+
							"FROM TB_CG_ARTICLE_BASE_INFO A "+
							"LEFT JOIN TB_SYS_CG_AUDIT_RESULT B "+ 
							"ON A.ARTICLE_ID = B.AUDIT_MBODY_ID  "+
							"AND B.DEAL_TIME=(SELECT MAX(DEAL_TIME) FROM TB_SYS_CG_AUDIT_RESULT WHERE AUDIT_MBODY_ID='"+articleId+"') "+ 
							"WHERE A.ARTICLE_ID = '"+articleId+"' ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(titleSql); 
		if (list1.size()!=0) {
			array1.add(list1.get(0).get("ARTICLE_TITLE"));
			array2.add(list1.get(0).get("ARTICLE_COVER"));
			array3.add(list1.get(0).get("AUDIT_STATUS_DESC")==null?"待审核":list1.get(0).get("AUDIT_STATUS_DESC"));
			array4.add(list1.get(0).get("AUDIT_STATUS")==null?"1":list1.get(0).get("AUDIT_STATUS"));
			array5.add(list1.get(0).get("AUDIT_DESC")==null?"":list1.get(0).get("AUDIT_DESC"));
		}
		result.put("artName", array1);
		result.put("artCover", array2);
		result.put("audState", array3);
		result.put("audStaId", array4);
		result.put("auditDesc", array5);
		return result;
	}
	//重新上传解读文件
	public JSONObject reupCGImg(String rfilePath, String articleId, String fileType,String coverOrfile) {
		String searchFiled = "";
		if ("artTitle".equals(coverOrfile)) {
			searchFiled="ARTICLE_COVER";
		} else if ("upFile".equals(coverOrfile)){
			if ("wordReading".equals(fileType)) {
				searchFiled="ARTICLE_DOC";
			} else if ("imgReading".equals(fileType)){
				searchFiled="ARTICLE_PIC";
			}
		}
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();
		String updateSql = "UPDATE TB_CG_ARTICLE_BASE_INFO SET "+searchFiled+" ='"+rfilePath+"' WHERE ARTICLE_ID = '"+articleId+"'";
		int countt1=this.getJdbcTemplate().update(updateSql);
		if(countt1>0){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}
	//查相关书法家
	public JSONObject searchCGInfo(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//书法家名称
		JSONArray array2 = new JSONArray();//书法家标准照路径
		JSONArray array3 = new JSONArray();//书法家ID
		String photogSql = "SELECT CGER_ID FROM TB_CDE_CG_ARTICLE_RELAT WHERE ARTICLE_ID ='"+articleId+"' ORDER BY SHOW_ORDER	";
		String titleSql = "SELECT A.CGER_NAME,B.FILE_NAME FROM TB_CG_BASE_INFO A "+
							"LEFT JOIN TB_CG_PIC_INFO B "+
							"ON A.CGER_ID=B.CGER_ID "+
							"WHERE A.CGER_ID =  ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(photogSql);
		for (int i = 0; i < list1.size(); i++) {
			Object [] values = {list1.get(i).get("CGER_ID")};
			List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(titleSql,values);
			for (int j = 0; j < list2.size(); j++) {
				array1.add(list2.get(j).get("CGER_NAME"));
				array2.add(list2.get(j).get("FILE_NAME"));
				array3.add(list1.get(i).get("CGER_ID"));
			}
		}
		result.put("photogName", array1);
		result.put("fileName", array2);
		result.put("photogId", array3);
		return result;
	}
	//查相关书法家作品
	public JSONObject searchCGWorksInfo(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//作品名称
		JSONArray array2 = new JSONArray();//作品路径
		JSONArray array3 = new JSONArray();//作品ID
		JSONArray array4 = new JSONArray();//作品在关联表中的排序
		String worksSql = "SELECT A.WORKS_ID,A.SHOW_ORDER,B.WORKS_NAME,C.PIC_NAME FROM TB_CDE_CG_ARTICLE_WORKS_RELAT A "+
							"LEFT JOIN TB_CG_WORKS_BASE_INFO B "+
							"ON A.WORKS_ID = B.WORKS_ID "+
							"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT C "+
							"ON B.WORKS_ID = C.WORKS_ID "+
							"WHERE A.ARTICLE_CODE = '"+articleId+"' "+
							" GROUP BY B.WORKS_NAME ORDER BY A.SHOW_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("WORKS_NAME"));
			array2.add(list1.get(i).get("PIC_NAME"));
			array3.add(list1.get(i).get("SHOW_ORDER"));
			array4.add(list1.get(i).get("WORKS_ID"));
		}
		result.put("worksName", array1);
		result.put("fileName", array2);
		result.put("worksOrder", array3);
		result.put("worksId", array4);
		return result;
	}
	//重新排序书法家、作品
	public JSONObject reOrderPhotog(String articleId, String reOrder, String opFlag) {
		JSONObject result = new JSONObject();
		String[] orderList = reOrder.split(",");
		String addSql = "";
		String delSql = "";
		if ("photog".equals(opFlag)) {
			delSql +="DELETE FROM TB_CDE_CG_ARTICLE_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
			addSql +="INSERT INTO TB_CDE_CG_ARTICLE_RELAT(ARTICLE_ID,SHOW_ORDER,DEAL_TIME,CGER_ID) VALUES(?,?,NOW(),?)";
		} else {
			delSql +="DELETE FROM TB_CDE_CG_ARTICLE_WORKS_RELAT WHERE ARTICLE_CODE = '"+articleId+"'";
			addSql +="INSERT INTO TB_CDE_CG_ARTICLE_WORKS_RELAT(ARTICLE_CODE,SHOW_ORDER,DEAL_TIME,WORKS_ID) VALUES(?,?,NOW(),?)";
		}
		this.getJdbcTemplate().update(delSql);
		for (int i = 0; i < orderList.length; i++) {
			Object [] values = {articleId,i,orderList[i]};
			int count = this.getJdbcTemplate().update(addSql, values);
			if (count>0) {
				System.out.println("OK!");
			} else {
				System.out.println("NO!");
			}
		}
		return result;
	}
	//添加相关书法家
	public JSONObject addNewPhotogF(String articleId, String pgid) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家名称
		JSONArray array2 = new JSONArray();//摄影家标准照路径
		/*int pgOrder = 0;
		String orderSql = "SELECT MAX(PHOTOG_ORDER) AS PHOTOG_ORDER FROM TB_CDE_PHOTOG_ARTICLE_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(orderSql);
		if (list1.size()!=0) {
			String orderPg = list1.get(0).get("PHOTOG_ORDER")==null?"0":list1.get(0).get("PHOTOG_ORDER").toString();
			pgOrder=Integer.parseInt(orderPg)+1;
		}
		String insertSql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_RELAT (ARTICLE_ID,PHOTOG_ID,PHOTOG_ORDER) VALUES ('"+articleId+"','"+pgid+"','"+pgOrder+"')";
		int count1=this.getJdbcTemplate().update(insertSql);*/
		String titleSql = "SELECT A.CGER_NAME,B.FILE_NAME FROM  TB_CG_BASE_INFO A "+
							"LEFT JOIN TB_CG_PIC_INFO B "+
							"ON A.CGER_ID = B.CGER_ID "+
							"WHERE A.CGER_ID  = ? ";
		Object [] values = {pgid};
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(titleSql,values);
		for (int j = 0; j < list2.size(); j++) {
			array1.add(list2.get(j).get("CGER_NAME"));
			array2.add(list2.get(j).get("FILE_NAME"));
		}
		result.put("cgerName", array1);
		result.put("fileName", array2);
		return result;
	}
	//获取未添加的书法家
	public String getCGInfo(String pgName, String start, String end, String pgGroups) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select a.CGER_ID,a.CGER_NAME,a.SHOW_ORDER,b.FILE_NAME from TB_CG_BASE_INFO a "+
					"LEFT JOIN TB_CG_PIC_INFO b "+
					"on a.CGER_ID=b.CGER_ID "+
					"where a.CGER_NAME LIKE '"+pgName+"' "+
					"and a.CGER_ID NOT IN "+pgGroups+" ORDER BY a.SHOW_ORDER LIMIT "+start+","+end;
		String sql1="select count(1) from TB_CG_BASE_INFO a where a.CGER_NAME LIKE '"+pgName+"' and a.CGER_ID NOT IN "+pgGroups;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			list = null;
			e.printStackTrace();
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				Map<String,Object> map = list.get(i);
				obj.put("id",map.get("CGER_ID"));
				obj.put("name",map.get("CGER_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				obj.put("fileName", map.get("FILE_NAME"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());

		return object.toString();
	}
	//查相关摄影家
	public JSONObject selectRelateCg(String pgGroups) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//书法家名称
		JSONArray array2 = new JSONArray();//书法家Id
		String worksSql = "SELECT DISTINCT A.CGER_ID,A.CGER_NAME FROM TB_CG_BASE_INFO A "+
							"WHERE A.CGER_ID IN "+pgGroups+" ORDER BY A.SHOW_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("CGER_NAME"));
			array2.add(list1.get(i).get("CGER_ID"));
		}
		result.put("CGName", array1);
		result.put("CGId", array2);
		return result;
	}
	//查作品作品等。
	public String selcgworksList(String wkGroups, String pgOrder, String pgId, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="SELECT A.WORKS_ID,A.WORKS_NAME,A.SHOW_ORDER,B.PIC_NAME FROM TB_CG_WORKS_BASE_INFO A " +
				"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT B ON A.WORKS_ID = B.WORKS_ID WHERE ";
		if (!"".equals(pgOrder)&&pgOrder!=null) {//作品序号
			sql+="A.SHOW_ORDER =' "+pgOrder+"' AND ";
		}
		sql+="A.WORKS_ID NOT IN "+wkGroups+" AND A.CGER_ID='"+pgId+"' GROUP BY A.WORKS_NAME ORDER BY A.SHOW_ORDER LIMIT "+start+","+end+" ";
		String sql1="SELECT COUNT(1) FROM TB_CG_WORKS_BASE_INFO A WHERE ";
		if (!"".equals(pgOrder)&&pgOrder!=null) {
			sql1+="A.SHOW_ORDER =' "+pgOrder+"' AND ";
		}
		sql1+=" A.CGER_ID='"+pgId+"' AND A.WORKS_ID NOT IN "+wkGroups;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = this.getJdbcTemplate().queryForList(sql);
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
				obj.put("filename", map.get("FILE_NAME"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());
		return object.toString();
	}
	//添加相关作品
	public JSONObject addNewWorksF(String articleId, String wkid) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//作品名称
		JSONArray array2 = new JSONArray();//作品路径
		JSONArray array3 = new JSONArray();//作品ID
		JSONArray array4 = new JSONArray();//作品在关联表中的排序
		
		/*int pgOrder = 0;
		String orderSql = "SELECT MAX(WORKS_ORDER) AS WORKS_ORDER FROM TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(orderSql);
		if (list1.size()!=0) {
			String orderPg = list1.get(0).get("WORKS_ORDER")==null?"0":list1.get(0).get("WORKS_ORDER").toString();
			pgOrder=Integer.parseInt(orderPg)+1;
		}
		String insertSql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT (ARTICLE_ID,WORKS_ID,WORKS_ORDER) VALUES ('"+articleId+"','"+wkid+"','"+pgOrder+"')";
		int count1=this.getJdbcTemplate().update(insertSql);*/
		
		String worksSql = "SELECT A.WORKS_NAME,C.PIC_NAME,A.SHOW_ORDER,A.WORKS_ID "+
							"FROM TB_CG_WORKS_BASE_INFO A "+
							"LEFT JOIN TB_CDE_CG_WORKS_PIC_RELAT C "+
							"ON C.WORKS_ID = A.WORKS_ID "+
							"WHERE A.WORKS_ID = '"+wkid+"'"+
							"ORDER BY A.SHOW_ORDER";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list2.size(); i++) {
			array1.add(list2.get(i).get("WORKS_NAME"));
			array2.add(list2.get(i).get("PIC_NAME"));
			array3.add(list2.get(i).get("SHOW_ORDER"));
			array4.add(list2.get(i).get("WORKS_ID"));
		}
		result.put("worksName", array1);
		result.put("fileName", array2);
		result.put("worksOrder", array3);
		result.put("worksId", array4);
		return result;
	}
	//删除相关作品摄影家
	public JSONObject deleteOper(String articleId, String labelId, String deleteFlag) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();
		String deleteSql = "";
		if ("works".equals(deleteFlag)) {
			deleteSql = "DELETE FROM TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT WHERE ARTICLE_ID ='"+articleId+"' AND WORKS_ID ='"+labelId+"' ";
		} else if("photog".equals(deleteFlag)) {
			deleteSql = "DELETE FROM TB_CDE_PHOTOG_ARTICLE_RELAT WHERE ARTICLE_ID ='"+articleId+"' AND PHOTOG_ID ='"+labelId+"' ";
		}
		int count1=this.getJdbcTemplate().update(deleteSql);
		if(count1>0){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}
	//获取下载文件路径
	public String getFilePath(String articleId, String fileType) {
		String pathNew="";
		String searchFiled = "";
		if ("wordReading".equals(fileType)) {
			searchFiled="ARTICLE_DOC";
		} else if ("imgReading".equals(fileType)){
			searchFiled="ARTICLE_PIC";
		}
		String searchSql = "SELECT "+searchFiled+" FROM TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_ID ='"+articleId+"' ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(searchSql);
		if (list.size()!=0) {
			pathNew=list.get(0).get(searchFiled)+"";
		}
		return pathNew;
	}
	//添加审核状态
	public JSONObject addCGAudit(String articleId,String articleTile) {
		
		//更新文章名称
		String upSql="update TB_CG_ARTICLE_BASE_INFO set ARTICLE_TITLE='"+articleTile+"' where ARTICLE_ID='"+articleId+"'";
		this.getJdbcTemplate().update(upSql);
		
		//更新审核状态
		int count1=0;
		String selSql = "SELECT COUNT(*) FROM TB_SYS_CG_AUDIT_STATUS WHERE AUDIT_MBODY_ID = '"+articleId+"'";
		String updateSql="";
		count1=this.getJdbcTemplate().queryForInt(selSql);
		if (count1==0) {
			updateSql="INSERT INTO TB_SYS_CG_AUDIT_STATUS(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES ('"+articleId+"','A','1','now()')";
		} else {
			updateSql="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS = '1' where AUDIT_MBODY_ID='"+articleId+"' and AUDIT_MBODY_TYPE ='A'";
		}
		int count2 = this.getJdbcTemplate().update(updateSql);
		return null;
	}
	//获取文章word  图片
	public JSONObject getWordAndPic(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//word
		JSONArray array2 = new JSONArray();//图片
		JSONArray array3 = new JSONArray();//H5
		
		String orderSql = "SELECT ARTICLE_DOC,ARTICLE_PIC,ARTICLE_URL FROM TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_ID = '"+articleId+"'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(orderSql);
		for (int i = 0; i < list2.size(); i++) {
			array1.add(list2.get(i).get("ARTICLE_DOC"));
			array2.add(list2.get(i).get("ARTICLE_PIC"));
			array3.add(list2.get(i).get("ARTICLE_URL"));
		}
		result.put("articleDoc", array1);
		result.put("articlePic", array2);
		result.put("articleUrl", array3);
		return result;
	}
	//审核提交
	public String changeCGAudit(String articleId, String auditStatus, String auditDesc, String auditPersn) {
		String sql1 ="UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS = ? WHERE AUDIT_MBODY_ID =? AND AUDIT_MBODY_TYPE ='A'";
		Object [] value1 = {auditStatus,articleId};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1==0){
			String sql11 ="INSERT INTO TB_SYS_CG_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,'A',?,NOW())";
			Object [] value11 = {articleId,auditStatus};
			int num11 = this.getJdbcTemplate().update(sql11,value11);
			if(num11!=1){
				return "false";
			}
		}else if(num1!=1){
			return "false";
		}
		String sql2 ="INSERT INTO TB_SYS_CG_AUDIT_RESULT(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		Object [] value2 = {articleId,"A",auditStatus,auditDesc,auditPersn};
		int num2 = this.getJdbcTemplate().update(sql2,value2);
		if(num2!=1){
			return "false";
		}else{
			return "true";
		}
	}
	
	public JSONObject auditState(String articleId) {
		String stateSql="select A.AUDIT_MBODY_ID,A.AUDIT_DESC,B.AUDIT_STATUS_DESC FROM TB_SYS_AUDIT_RESULT A " +
				"LEFT JOIN TB_CDE_AUDIT_STATUS B " +
				"ON A.AUDIT_STATUS = B.AUDIT_STATUS_ID " +
				"WHERE A.AUDIT_MBODY_ID ='"+articleId+"' AND A.AUDIT_MBODY_TYPE ='A' ";
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//word
		JSONArray array2 = new JSONArray();//图片
		JSONArray array3 = new JSONArray();//图片
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(stateSql);
		for (int i = 0; i < list2.size(); i++) {
			array1.add(list2.get(i).get("AUDIT_MBODY_ID"));
			array2.add(list2.get(i).get("AUDIT_DESC"));
			array3.add(list2.get(i).get("AUDIT_STATUS_DESC"));
		}
		result.put("AUDIT_ID", array1);
		result.put("audit_Desc", array2);
		result.put("statusDesc", array3);
		return result;
	}
	//新生成ID
	public String getNewArtId() {
		String newId="";
		String sql = "SELECT MAX(ARTICLE_ID) AS ARTICLE_ID FROM TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_TYPE ='TA'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql);
		if (list2.size()!=0) {
			newId = list2.get(0).get("ARTICLE_ID")==null?"0":list2.get(0).get("ARTICLE_ID")+"";
		} else {
			newId = "A_TA00000001";
		}
		return newId;
	}
	//删除文章
	public int deleteMessage(String articleId) {
		String delArtSql="DELETE FROM TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_ID = '"+articleId+"'";
		String delCGArtSql="DELETE FROM TB_CDE_CG_ARTICLE_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
		String delWkArtSql="DELETE FROM TB_CDE_CG_ARTICLE_WORKS_RELAT WHERE ARTICLE_CODE = '"+articleId+"'";
		String delAuditSql="DELETE FROM TB_SYS_CG_AUDIT_STATUS WHERE AUDIT_MBODY_ID = '"+articleId+"'";
		String delAuditRSql="DELETE FROM TB_SYS_CG_AUDIT_RESULT WHERE AUDIT_MBODY_ID = '"+articleId+"'";
		this.getJdbcTemplate().update(delArtSql);
		this.getJdbcTemplate().update(delCGArtSql);
		this.getJdbcTemplate().update(delWkArtSql);
		this.getJdbcTemplate().update(delAuditRSql);
		this.getJdbcTemplate().update(delAuditSql);
		return 1;
	}
	//查word路径
	public String cgArtWdShow(String articleId, String flagId) {
		String wordPath= "";
		String sql="select ARTICLE_DOC from TB_CG_ARTICLE_BASE_INFO where ARTICLE_ID='"+articleId+"' and ARTICLE_TYPE='"+flagId+"' ";
		List<Map<String, Object>>list = this.getJdbcTemplate().queryForList(sql);
		if (list.size()!=0) {
			wordPath=list.get(0).get("ARTICLE_DOC").toString();
		}
		return wordPath;
	}
	//获取所有的审核
	public JSONObject getcgauditstatus(String auditStatus) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//审核状态Id
		JSONArray array2 = new JSONArray();//审核状态Name
		JSONArray array3 = new JSONArray();//驳回、回退的原因Id
		JSONArray array4 = new JSONArray();//驳回、回退的原因Name
		String preSql = "";
		if ("1".equals(auditStatus.trim())) {
			array1.add("2");
			array2.add("驳回");
			array1.add("5");
			array2.add("通过");
			preSql = "SELECT * FROM TB_CDE_AUDIT_BACK_REASON WHERE REASON_TYPE = '驳回' ";
		} else if ("5".equals(auditStatus.trim())) {
			array1.add("3");
			array2.add("回退");
			array1.add("5");
			array2.add("通过");
			preSql = "SELECT * FROM TB_CDE_AUDIT_BACK_REASON WHERE REASON_TYPE = '回退' ";
		}
		//审核状态码表 TB_CDE_AUDIT_BACK_REASON
		List<Map<String, Object>>list = this.getJdbcTemplate().queryForList(preSql);
		for (int i = 0; i < list.size(); i++) {
			array3.add(list.get(i).get("REASON_ID"));
			array4.add(list.get(i).get("REASON_NAME"));
		}
		result.put("auditId", array1);
		result.put("auditName", array2);
		result.put("reasonId", array3);
		result.put("reasonName", array4);
		return result;
	}
	//连带删除书法家作品
	public JSONObject deleteCgWorks(String deleteCgId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//workId
		String workSql = "SELECT WORKS_ID FROM TB_CG_WORKS_BASE_INFO WHERE CGER_ID = '"+deleteCgId+"'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(workSql);
		for (int i = 0; i < list2.size(); i++) {
			array1.add(list2.get(i).get("WORKS_ID"));
		}
		result.put("worksId", array1);
		return result;
	}

}
 