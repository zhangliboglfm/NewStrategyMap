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
import com.miapsoft.manager.ArticleDetailManager;
import com.miapsoft.util.IdGenerateUtil;


@Service("artDetailManager")
public class ArtDetailManagerImpl extends AbstractManager implements ArticleDetailManager {
	//查询所有文章封面及标题，并生成左侧树结构
	public JSONObject getAllArticle(String photogId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//名称
		JSONArray array2 = new JSONArray();//封面
		JSONArray array3 = new JSONArray();//id
		String titleSql = "SELECT A.ARTICLE_ID,A.ARTICLE_TITLE,A.ARTICLE_COVER FROM TB_PHOTOG_ARTICLE_BASE_INFO A " +
				"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_RELAT B " +
				"ON A.ARTICLE_ID = B.ARTICLE_ID " +
				"WHERE B.PHOTOG_ID = '"+photogId+"' AND A.ARTICLE_TYPE = 'TA' ORDER BY B.PHOTOG_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(titleSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("ARTICLE_TITLE"));
			array2.add(list1.get(i).get("ARTICLE_COVER"));
			array3.add(list1.get(i).get("ARTICLE_ID"));
		}
		result.put("artName", array1);
		result.put("artCover", array2);
		result.put("artId", array3);
		return result;
	}
	//查询文章名称
	public JSONObject getArtTitle(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//名称
		JSONArray array2 = new JSONArray();//封面
		JSONArray array3 = new JSONArray();//审核
		JSONArray array4 = new JSONArray();//审核id
		JSONArray array5 = new JSONArray();//审核意见
		String titleSql = "SELECT A.ARTICLE_TITLE,A.ARTICLE_COVER,C.AUDIT_STATUS_DESC,C.AUDIT_STATUS_ID,B.AUDIT_DESC FROM TB_PHOTOG_ARTICLE_BASE_INFO A " +
				"LEFT JOIN TB_SYS_AUDIT_RESULT B " +
				"ON A.ARTICLE_ID = B.AUDIT_MBODY_ID AND B.DEAL_TIME=(SELECT MAX(DEAL_TIME) FROM TB_SYS_AUDIT_RESULT WHERE AUDIT_MBODY_ID='"+articleId+"') " +
				"LEFT JOIN TB_CDE_AUDIT_STATUS C " +
				"ON B.AUDIT_STATUS=C.AUDIT_STATUS_ID " +
				"WHERE A.ARTICLE_ID = '"+articleId+"' ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(titleSql);
		if (list1.size()!=0) {
			array1.add(list1.get(0).get("ARTICLE_TITLE"));
			array2.add(list1.get(0).get("ARTICLE_COVER"));
			array3.add(list1.get(0).get("AUDIT_STATUS_DESC")==null?"待审核":list1.get(0).get("AUDIT_STATUS_DESC"));
			array4.add(list1.get(0).get("AUDIT_STATUS_ID")==null?"1":list1.get(0).get("AUDIT_STATUS_ID"));
			array5.add(list1.get(0).get("AUDIT_DESC")==null?"":list1.get(0).get("AUDIT_DESC"));
		}
		result.put("artName", array1);
		result.put("artCover", array2);
		result.put("audState", array3);
		result.put("audStaId", array4);
		result.put("auditDesc", array5);
		return result;
	}

	//查询文章已有标签并展示
	public JSONObject getArticlLabel(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//名称
		JSONArray array2 = new JSONArray();//标签ID
		String sql = "SELECT A.LABEL_NAME,B.LABEL_ORDER,A.LABEL_ID FROM TB_CDE_LABEL A " +
				"LEFT JOIN TB_CDE_PHOTOG_LABEL_RELAT B " +
				"ON A.LABEL_ID = B.LABEL_ID " +
				"LEFT JOIN TB_PHOTOG_ARTICLE_BASE_INFO C " +
				"ON B.LABEL_MBODY_ID = C.ARTICLE_ID " +	
				"WHERE C.ARTICLE_ID = '"+articleId+"' AND A.LABEL_TYPE = 'A'  ORDER BY B.LABEL_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		JSONArray label = new JSONArray();
		for (int i = 0; i < list1.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("lbId", list1.get(i).get("LABEL_ID"));
			obj.put("lbName", list1.get(i).get("LABEL_NAME"));
			label.add(obj);
		}
		result.put("pLable", label);
		return result;
	}
	//查询已有标签和所有标签，并分类显示
	public JSONObject getAllArtLabel() {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//名称
		JSONArray array2 = new JSONArray();//标签ID
		String sql = "SELECT DISTINCT A.LABEL_NAME,B.LABEL_ORDER,A.LABEL_ID FROM TB_CDE_LABEL A " +
				"LEFT JOIN TB_CDE_PHOTOG_LABEL_RELAT B " +
				"ON A.LABEL_ID = B.LABEL_ID " +
				"LEFT JOIN TB_PHOTOG_ARTICLE_BASE_INFO C " +
				"ON B.LABEL_MBODY_ID = C.ARTICLE_ID " +	
				"WHERE A.LABEL_TYPE = 'A'  ORDER BY B.LABEL_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql);
		JSONArray label = new JSONArray();
		for (int i = 0; i < list1.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("lbId", list1.get(i).get("LABEL_ID"));
			obj.put("lbName", list1.get(i).get("LABEL_NAME"));
			label.add(obj);
		}
		result.put("pLable", label);
		return result;
	}
	//对文章标签进行操作
	public JSONObject opArticleLabel(String articleId, String labelId, String operate) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();
		Date date = new Date();
		String sql ="";
		Object [] value ={labelId,articleId};
		if("add".equals(operate)){
			String sql1="SELECT case when  MAX(LABEL_ORDER) is NULL THEN 0 ELSE MAX(LABEL_ORDER) END "+
					"FROM TB_CDE_PHOTOG_LABEL_RELAT where LABEL_MBODY_ID = ?  ";
			Object [] value1 = {articleId};
			int order = this.getJdbcTemplate().queryForInt(sql1, value1)+1;
			sql+="INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER) VALUES(?,?,"+order+")";
		}else if("delete".equals(operate)) {
			sql+="DELETE from TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_ID= ? and LABEL_MBODY_ID= ?";
		}
		int row=this.getJdbcTemplate().update(sql, value);
		
		if(1==row){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}
	//重新上传解读文件
	public JSONObject reuploadImg(String rfilePath, String articleId, String fileType,String coverOrfile) {
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
		String updateSql = "UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET "+searchFiled+" ='"+rfilePath+"' WHERE ARTICLE_ID = '"+articleId+"'";
		int countt1=this.getJdbcTemplate().update(updateSql);
		if(countt1>0){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}
	//查相关摄影家
	public JSONObject searchPhotogInfo(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家名称
		JSONArray array2 = new JSONArray();//摄影家标准照路径
		JSONArray array3 = new JSONArray();//摄影家ID
		String photogSql = "SELECT PHOTOG_ID FROM TB_CDE_PHOTOG_ARTICLE_RELAT WHERE ARTICLE_ID ='"+articleId+"' ORDER BY PHOTOG_ORDER";
		String titleSql = "SELECT A.PHOTOG_NAME,B.FILE_NAME FROM TB_PHOTOG_BASE_INFO A " +
							"LEFT JOIN TB_PHOTOG_PIC_INFO B " +
							"ON A.PHOTOG_ID = B.PHOTOG_ID AND B.PIC_TYPE = '头像' " +
							"WHERE A.PHOTOG_ID = ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(photogSql);
		for (int i = 0; i < list1.size(); i++) {
			Object [] values = {list1.get(i).get("PHOTOG_ID")};
			List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(titleSql,values);
			for (int j = 0; j < list2.size(); j++) {
				array1.add(list2.get(j).get("PHOTOG_NAME"));
				array2.add(list2.get(j).get("FILE_NAME"));
				array3.add(list1.get(i).get("PHOTOG_ID"));
			}
		}
		result.put("photogName", array1);
		result.put("fileName", array2);
		result.put("photogId", array3);
		return result;
	}
	//查相关作品
	public JSONObject searchWorksInfo(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//作品名称
		JSONArray array2 = new JSONArray();//作品路径
		JSONArray array3 = new JSONArray();//作品ID
		JSONArray array4 = new JSONArray();//作品在关联表中的排序
		String worksSql = "SELECT A.WORKS_NAME,A.FILE_NAME,B.WORKS_ORDER,B.WORKS_ID "+
							"FROM TB_PHOTOG_WORKS_BASE_INFO A "+
							"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT B "+
							"ON A.WORKS_ID = B.WORKS_ID  "+
							"WHERE B.ARTICLE_ID = '"+articleId+"' "+
							"ORDER BY WORKS_ORDER ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("WORKS_NAME"));
			array2.add(list1.get(i).get("FILE_NAME"));
			array3.add(list1.get(i).get("WORKS_ORDER"));
			array4.add(list1.get(i).get("WORKS_ID"));
		}
		result.put("worksName", array1);
		result.put("fileName", array2);
		result.put("worksOrder", array3);
		result.put("worksId", array4);
		return result;
	}
	//重新排序摄影家、作品
	public JSONObject reOrderPhotog(String articleId, String reOrder, String opFlag) {
		JSONObject result = new JSONObject();
		String[] orderList = reOrder.split(",");
		String addSql = "";
		if ("photog".equals(opFlag)) {
			addSql +="UPDATE TB_CDE_PHOTOG_ARTICLE_RELAT SET PHOTOG_ORDER = ? WHERE ARTICLE_ID = ? AND PHOTOG_ID = ?";
		} else {
			addSql +="UPDATE TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT SET WORKS_ORDER = ? WHERE ARTICLE_ID = ? AND WORKS_ID = ?";
		}
		for (int i = 0; i < orderList.length-1; i++) {
			Object [] values = {i,articleId,orderList[i]};
			int count = this.getJdbcTemplate().update(addSql, values);
			if (count>0) {
				System.out.println("OK!");
			} else {
				System.out.println("NO!");
			}
		}
		return result;
	}
	//添加相关摄影家
	public JSONObject addNewPhotogF(String articleId, String pgid) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家名称
		JSONArray array2 = new JSONArray();//摄影家标准照路径
		int pgOrder = 0;
		String orderSql = "SELECT MAX(PHOTOG_ORDER) AS PHOTOG_ORDER FROM TB_CDE_PHOTOG_ARTICLE_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(orderSql);
		if (list1.size()!=0) {
			String orderPg = list1.get(0).get("PHOTOG_ORDER")==null?"0":list1.get(0).get("PHOTOG_ORDER").toString();
			pgOrder=Integer.parseInt(orderPg)+1;
		}
		String insertSql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_RELAT (ARTICLE_ID,PHOTOG_ID,PHOTOG_ORDER) VALUES ('"+articleId+"','"+pgid+"','"+pgOrder+"')";
		int count1=this.getJdbcTemplate().update(insertSql);
		String titleSql = "SELECT A.PHOTOG_NAME,B.FILE_NAME FROM TB_PHOTOG_BASE_INFO A " +
							"LEFT JOIN TB_PHOTOG_PIC_INFO B " +
							"ON A.PHOTOG_ID = B.PHOTOG_ID AND B.PIC_TYPE = '头像' " +
							"WHERE A.PHOTOG_ID = ? ";
		Object [] values = {pgid};
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(titleSql,values);
		for (int j = 0; j < list2.size(); j++) {
			array1.add(list2.get(j).get("PHOTOG_NAME"));
			array2.add(list2.get(j).get("FILE_NAME"));
		}
		result.put("photogName", array1);
		result.put("fileName", array2);
		return result;
	}

	public String selphotogList(String pgName, String start, String end, String pgGroups) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select a.PHOTOG_ID,a.PHOTOG_NAME,a.SHOW_ORDER from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE '"+pgName+
						"' and a.PHOTOG_ID NOT IN "+pgGroups+" ORDER BY a.SHOW_ORDER LIMIT "+start+","+end+" ";
		String sql1="select count(1) from TB_PHOTOG_BASE_INFO a where a.PHOTOG_NAME LIKE '"+pgName+"' and a.PHOTOG_ID NOT IN "+pgGroups;
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
				obj.put("id",map.get("PHOTOG_ID"));
				obj.put("name",map.get("PHOTOG_NAME"));
				obj.put("order", map.get("SHOW_ORDER"));
				array.add(obj);
			}
		}

		int counts=this.getJdbcTemplate().queryForInt(sql1);
		object.put("counts", counts);
		object.put("dataList", array.toString());

		return object.toString();
	}
	//查相关摄影家
	public JSONObject selectRelatePg(String pgGroups) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//摄影家名称
		JSONArray array2 = new JSONArray();//摄影家Id
		String worksSql = "SELECT DISTINCT A.PHOTOG_NAME,A.PHOTOG_ID FROM TB_PHOTOG_BASE_INFO A "+
							"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_RELAT B "+
							"ON A.PHOTOG_ID = B.PHOTOG_ID "+
							"WHERE A.PHOTOG_ID IN "+pgGroups+" ORDER BY B.PHOTOG_ORDER";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("PHOTOG_NAME"));
			array2.add(list1.get(i).get("PHOTOG_ID"));
		}
		result.put("photogName", array1);
		result.put("photogId", array2);
		return result;
	}
	//查作品作品等。
	public String selworksList(String wkGroups, String pgOrder, String pgId, String start, String end) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String sql ="select a.WORKS_ID,a.WORKS_NAME,a.SHOW_ORDER,a.FILE_NAME from TB_PHOTOG_WORKS_BASE_INFO a where ";
		if (!"".equals(pgOrder)&&pgOrder!=null) {
			sql+="a.SHOW_ORDER =' "+pgOrder+"' and ";
		}
		sql+="a.WORKS_ID NOT IN "+wkGroups+" AND PHOTOG_ID='"+pgId+"' ORDER BY a.SHOW_ORDER LIMIT "+start+","+end+" ";
		String sql1="select count(1) from TB_PHOTOG_WORKS_BASE_INFO a where ";
		if (!"".equals(pgOrder)&&pgOrder!=null) {
			sql1+="a.SHOW_ORDER =' "+pgOrder+"' and ";
		}
		sql1+=" a.PHOTOG_ID='"+pgId+"' and a.WORKS_ID NOT IN "+wkGroups;
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
		
		int pgOrder = 0;
		String orderSql = "SELECT MAX(WORKS_ORDER) AS WORKS_ORDER FROM TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT WHERE ARTICLE_ID = '"+articleId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(orderSql);
		if (list1.size()!=0) {
			String orderPg = list1.get(0).get("WORKS_ORDER")==null?"0":list1.get(0).get("WORKS_ORDER").toString();
			pgOrder=Integer.parseInt(orderPg)+1;
		}
		String insertSql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT (ARTICLE_ID,WORKS_ID,WORKS_ORDER) VALUES ('"+articleId+"','"+wkid+"','"+pgOrder+"')";
		int count1=this.getJdbcTemplate().update(insertSql);
		
		String worksSql = "SELECT A.WORKS_NAME,A.FILE_NAME,B.WORKS_ORDER,B.WORKS_ID "+
							"FROM TB_PHOTOG_WORKS_BASE_INFO A "+
							"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT B "+
							"ON A.WORKS_ID = B.WORKS_ID  "+
							"WHERE A.WORKS_ID = '"+wkid+"' AND B.ARTICLE_ID = '"+articleId+"'"+
							"ORDER BY WORKS_ORDER ";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(worksSql);
		for (int i = 0; i < list2.size(); i++) {
			array1.add(list2.get(i).get("WORKS_NAME"));
			array2.add(list2.get(i).get("FILE_NAME"));
			array3.add(list2.get(i).get("WORKS_ORDER"));
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
		String searchSql = "SELECT "+searchFiled+" FROM TB_PHOTOG_ARTICLE_BASE_INFO WHERE ARTICLE_ID ='"+articleId+"' ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(searchSql);
		if (list.size()!=0) {
			pathNew=list.get(0).get(searchFiled)+"";
		}
		return pathNew;
	}
	//添加审核状态
	public JSONObject addAuditing(String articleId,String articleTile) {
		
		//更新文章名称
		String upSql="update TB_PHOTOG_ARTICLE_BASE_INFO set ARTICLE_TITLE='"+articleTile+"' where ARTICLE_ID='"+articleId+"'";
		this.getJdbcTemplate().update(upSql);
		
		//更新审核状态
		int count1=0;
		String selSql = "SELECT COUNT(*) FROM TB_SYS_AUDIT_STATUS WHERE AUDIT_MBODY_ID = '"+articleId+"'";
		String updateSql="";
		count1=this.getJdbcTemplate().queryForInt(selSql);
		if (count1==0) {
			updateSql="INSERT INTO TB_SYS_AUDIT_STATUS(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS) VALUES ('"+articleId+"','A','1')";
		} else {
			updateSql="UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS = '1' where AUDIT_MBODY_ID='"+articleId+"' and AUDIT_MBODY_TYPE ='A'";
		}
		int count2 = this.getJdbcTemplate().update(updateSql);
		return null;
	}
	//添加自定义标签
	public JSONObject addCustomLabel(String articleId, String labelName, String labeldesc) {
		JSONObject result = new JSONObject();
		String label_id="";
		String sql="SELECT LABEL_ID FROM TB_CDE_LABEL where  LABEL_TYPE = 'A' AND LABEL_NAME = ?";
		Object [] value = {labelName};
		List<Map<String,Object>> list =this.getJdbcTemplate().queryForList(sql,value);
		if(list!=null&&list.size()!=0){
			label_id = list.get(0).get("LABEL_ID").toString();
			result.put("success", false);
			result.put("message", "已有该标签，请重新自定义");
			return result;
		}else{
			String sql1 ="SELECT COUNT(*) FROM TB_CDE_LABEL where  LABEL_TYPE = 'A'";
			String num1 =this.getJdbcTemplate().queryForInt(sql1)+1+"";
			StringBuffer sb =new StringBuffer("L_A");
			for (int i = 0; i < (7-num1.length()); i++) {
				sb.append("0");
			};
			label_id=sb.append(num1).toString();
			String insertsql = "INSERT INTO TB_CDE_LABEL(LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC,DEAL_TIME)VALUES (?,?,'A',?,now())";
			Object [] insertValue ={label_id,labelName,labeldesc};
			int insertNum = this.getJdbcTemplate().update(insertsql,insertValue);
			if(insertNum!=1){
				result.put("success", false);
				return result;
			};
		};
		
		String sql2="SELECT case when  MAX(LABEL_ORDER) is NULL THEN	0 ELSE MAX(LABEL_ORDER) END "+
				"FROM TB_CDE_PHOTOG_LABEL_RELAT where LABEL_MBODY_ID = ?  ";
		Object [] value2 = {articleId};
		int order = this.getJdbcTemplate().queryForInt(sql2, value2)+1;
		
		String sql3 ="INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES(?,?,"+order+",now())";
		Object [] value3 ={label_id,articleId};
		int updateNum = this.getJdbcTemplate().update(sql3,value3);
		if(updateNum!=1){
			result.put("success", false);
			return result;
		};
		result.put("result", "success");
		result.put("state", label_id);
		return result;
	}
	//审核提交
	public String changeAuditStatus(String articleId, String auditStatus, String auditDesc, String auditPersn) {
		String sql1 ="UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS = ? WHERE AUDIT_MBODY_ID =? AND AUDIT_MBODY_TYPE ='A'";
		Object [] value1 = {auditStatus,articleId};
		int num1 = this.getJdbcTemplate().update(sql1,value1);
		if(num1==0){
			String sql11 ="INSERT INTO TB_SYS_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,'A',?,NOW())";
			Object [] value11 = {articleId,auditStatus};
			int num11 = this.getJdbcTemplate().update(sql11,value11);
			if(num11!=1){
				return "false";
			}
		}else if(num1!=1){
			return "false";
		}
		String sql2 ="INSERT INTO TB_SYS_AUDIT_RESULT(AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?,now())";
		Object [] value2 = {articleId,"A",auditStatus,auditDesc,auditPersn};
		int num2 = this.getJdbcTemplate().update(sql2,value2);
		if(num2!=1){
			return "false";
		}else{
			return "true";
		}
	}
	//获取文章word  图片
	public JSONObject getWordAndPic(String articleId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//word
		JSONArray array2 = new JSONArray();//图片
		JSONArray array3 = new JSONArray();//H5
		
		String orderSql = "SELECT ARTICLE_DOC,ARTICLE_PIC,ARTICLE_URL FROM TB_PHOTOG_ARTICLE_BASE_INFO WHERE ARTICLE_ID = '"+articleId+"'";
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
		String sql = "SELECT MAX(ARTICLE_ID) AS ARTICLE_ID FROM TB_PHOTOG_ARTICLE_BASE_INFO";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql);
		if (list2.size()!=0) {
			String artId = list2.get(0).get("ARTICLE_ID")==null?"0":list2.get(0).get("ARTICLE_ID")+"";
			if (!"0".equals(artId)) {
				//A_TA00000003
				artId=artId.substring(4);
			}
			int count = Integer.parseInt(artId);
			newId=IdGenerateUtil.getArticleId("TA", count);
		}
		return newId;
	}
	//新操作标签
	public JSONObject updateLabel(String articleId, String arr) {
		String labelList=arr.replace("[", "").replace("]", "").replace("\"", "");
		String [] laList=labelList.split(",");
		String delSql="DELETE FROM TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_MBODY_ID = '"+articleId+"'";
		int count1=this.getJdbcTemplate().update(delSql);
		String insertSql = "INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES (?,?,?,now())";
		for (int i = 0; i < laList.length; i++) {
			Object [] values={laList[i],articleId,i};
			int count2=this.getJdbcTemplate().update(insertSql,values);
		}
		return null;
	}

}
 