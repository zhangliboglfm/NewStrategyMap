package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CGArticleManager;


@Service("cgArticleManager")
public class CGArtManagerImpl extends AbstractManager implements CGArticleManager {
	//查作品分页数
	public int searchCGArtPage(String cgName, String auditStatus) {
		int pageNum=0;
		String sql = "";
		String auditSql = "";
		if ("1".equals(auditStatus)) {
			auditSql = " AND D.AUDIT_STATUS = '1' ";
		} else if ("5".equals(auditStatus)) {
			auditSql = " AND D.AUDIT_STATUS = '5' ";
		}
		if ("".equals(cgName.trim())) {
			sql = "SELECT COUNT(*) FROM TB_CG_ARTICLE_BASE_INFO A " +
					"LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON A.ARTICLE_ID = D.AUDIT_MBODY_ID " +
					"WHERE A.ARTICLE_TYPE ='TA' ";
		} else {
			sql = "SELECT COUNT(*) FROM TB_CDE_CG_ARTICLE_RELAT A "+
					"LEFT JOIN TB_CG_BASE_INFO B "+
					"ON A.CGER_ID = B.CGER_ID  " +
					"LEFT JOIN TB_CG_ARTICLE_BASE_INFO C "+
				    " ON A.ARTICLE_ID = C.ARTICLE_ID " +
				    "LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON C.ARTICLE_ID = D.AUDIT_MBODY_ID " +
					"WHERE C.ARTICLE_TYPE = 'TA' AND B.CGER_NAME LIKE '%"+cgName+"%'";
		}
		sql+=auditSql;
		pageNum = this.getJdbcTemplate().queryForInt(sql);
		return pageNum;
	}
	//查某一页文章内容
	public JSONObject showCGArtInfo(String cgName, String curr, String auditStatus) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//封面路径
		JSONArray array2 = new JSONArray();//封面标题
		JSONArray array3 = new JSONArray();//文章ID
		int index = Integer.parseInt(curr);
		int beginNum = 15*(index-1);
		String articalSql="";
		String auditSql = "";
		if ("1".equals(auditStatus)) {
			auditSql = " AND D.AUDIT_STATUS != '5' ";
		} else if ("5".equals(auditStatus)) {
			auditSql = " AND D.AUDIT_STATUS = '5' ";
		}
		if ("".equals(cgName.trim())) {
			articalSql = "SELECT A.ARTICLE_COVER,A.ARTICLE_TITLE,A.ARTICLE_ID FROM TB_CG_ARTICLE_BASE_INFO A " +
							"LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON A.ARTICLE_ID = D.AUDIT_MBODY_ID " +
							"WHERE A.ARTICLE_TYPE ='TA' "+auditSql+" ORDER BY A.ARTICLE_ID LIMIT "+beginNum+",15";
		} else {
			articalSql = "SELECT A.ARTICLE_COVER,A.ARTICLE_TITLE,B.SHOW_ORDER,A.ARTICLE_ID FROM TB_CG_ARTICLE_BASE_INFO A  "+
					"LEFT JOIN TB_CDE_CG_ARTICLE_RELAT B  "+
					"ON A.ARTICLE_ID = B.ARTICLE_ID "+
					"LEFT JOIN TB_CG_BASE_INFO C "+
					"ON B.CGER_ID = C.CGER_ID " +
					"LEFT JOIN TB_SYS_CG_AUDIT_STATUS D ON A.ARTICLE_ID = D.AUDIT_MBODY_ID "+
					"WHERE A.ARTICLE_TYPE ='TA' AND C.CGER_NAME LIKE '%"+cgName+"%' "+auditSql+" ORDER BY B.SHOW_ORDER LIMIT "+beginNum+",15";
		}
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(articalSql);
		for (int i = 0; i < list1.size(); i++) {
			array1.add(list1.get(i).get("ARTICLE_COVER"));
			array2.add(list1.get(i).get("ARTICLE_TITLE"));
			array3.add(list1.get(i).get("ARTICLE_ID"));
		}
		result.put("articleCover", array1);
		result.put("articleTitle", array2);
		result.put("articleId", array3);
		return result;
	}
	
	//上传压缩文件插入数据
	public JSONObject insertArticleInfo(JSONObject obj,String artId) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aId = artId;
		String aTitle = obj.getString("aTitle");
		String aDoc = obj.getString("aDoc");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_CG_ARTICLE_BASE_INFO VALUES ('"+aId+"','TA','','"+aTitle+"','','"+aDoc+"','','','"+curdate+"')";
		if(checkDataConflict(aId,"TB_CG_ARTICLE_BASE_INFO","ARTICLE_ID")){
			result = this.getJdbcTemplate().update(sql);
		}
		//添加相关书法家
		JSONObject returnResult = new JSONObject();
		String aPhotog = obj.getString("aPhotog");
		JSONArray array1 = searchPhotog(aPhotog,artId);//存书法家名
		String aWorks = obj.getString("aWorks");
		JSONArray array2=searchWork(aWorks,artId);//存书法家名+作品序号
		returnResult.put("failPhg", array1);
		returnResult.put("failWork", array2);
		String stateSql = "INSERT INTO TB_SYS_CG_AUDIT_STATUS VALUES('"+aId+"','A','1','"+curdate+"')";
		this.getJdbcTemplate().update(stateSql);
		return returnResult;
	}
	//添加相关书法家
	public JSONArray searchPhotog(String aPhotog,String artId) {
		JSONArray array=new JSONArray();
		String [] savePhotog = aPhotog.split("#");
		int insertNum =0;
		int pgOr=0;
		for (int i = 1; i < savePhotog.length; i++) {
			String photogSql="select CGER_ID from TB_CG_BASE_INFO where CGER_NAME ='"+savePhotog[i].trim()+"' ";
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(photogSql);
			
			if (list1.size()!=0) {
				pgOr+=1;
				String addPgArtSql="INSERT INTO TB_CDE_CG_ARTICLE_RELAT(ARTICLE_ID,CGER_ID,SHOW_ORDER,DEAL_TIME)VALUES (?,?,?,now())";
				Object [] values = {artId,list1.get(0).get("CGER_ID"),pgOr};
				insertNum = this.getJdbcTemplate().update(addPgArtSql,values);
				if (insertNum==0) {
					array.add(savePhotog[i]);
				}
			} else {
				array.add(savePhotog[i]);
			}
		}
		return array;
	}
	//添加相关作品
	public JSONArray searchWork(String aWorks,String artId) {
		aWorks=aWorks.replace("#", "-");
		JSONArray array=new JSONArray();
		String [] saveWorks = aWorks.split("-");
		int insertNum =0;
		for (int i = 1; i < saveWorks.length; i++) {
			if (i%2!=0) {
				String selWkId="SELECT A.WORKS_ID FROM TB_CG_WORKS_BASE_INFO A "+
									"LEFT JOIN TB_CG_BASE_INFO B "+
									"ON A.CGER_ID = B.CGER_ID  "+
									"WHERE A.SHOW_ORDER = '"+saveWorks[i+1].trim()+"' AND B.CGER_NAME = '"+saveWorks[i].trim()+"'";
				List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(selWkId);
				int pgOr=0;
				if (list1.size()!=0) {
					pgOr+=1;
					String addWkArSql="INSERT INTO TB_CDE_CG_ARTICLE_WORKS_RELAT(ARTICLE_CODE,WORKS_ID,SHOW_ORDER,DEAL_TIME)VALUES (?,?,?,now())";
					Object [] values = {artId,list1.get(0).get("WORKS_ID"),pgOr};
					insertNum = this.getJdbcTemplate().update(addWkArSql,values);
					if (insertNum==0) {
						array.add(saveWorks[i]+saveWorks[i+1]);
					}
				} else {
					array.add(saveWorks[i]+saveWorks[i+1]);
				}
			}
		}
		return array;
	}
	
	private boolean checkDataConflict(String Id,String tablename,String checkcoulmn){
		boolean result = false;
		String sql = "SELECT  * from "+tablename+" where "+checkcoulmn+" = '"+Id+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()==0){
			result=true;
		}
		return result;
	}
	
}
 