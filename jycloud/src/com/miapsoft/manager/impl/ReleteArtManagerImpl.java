package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.RelateArticleManager;


@Service("relateArtManager")
public class ReleteArtManagerImpl extends AbstractManager implements RelateArticleManager {
	//查作品分页数
	public int searchPaging(String photogid) {
		int pageNum=0;
		String sql = "SELECT COUNT(*) FROM TB_CDE_PHOTOG_ARTICLE_RELAT WHERE PHOTOG_ID = '"+photogid+"'";
		pageNum = this.getJdbcTemplate().queryForInt(sql);
		return pageNum;
	}
	//查某一页文章内容
	public JSONObject showArticleInfo(String photogId, String curr) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();//封面路径
		JSONArray array2 = new JSONArray();//封面标题
		JSONArray array3 = new JSONArray();//文章ID
		int index = Integer.parseInt(curr);
		int beginNum = 15*(index-1);
		String articalSql = "SELECT A.ARTICLE_COVER,A.ARTICLE_TITLE,B.PHOTOG_ORDER,A.ARTICLE_ID FROM TB_PHOTOG_ARTICLE_BASE_INFO A " +
				"LEFT JOIN TB_CDE_PHOTOG_ARTICLE_RELAT B " +
				"ON A.ARTICLE_ID = B.ARTICLE_ID  " +
				"WHERE ARTICLE_TYPE ='TA' AND B.PHOTOG_ID = '"+photogId+"' ORDER BY B.PHOTOG_ORDER LIMIT "+beginNum+",15 ";
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
	//查摄影家名称
	public String searchName(String photogId) {
		String photogName = "";
		String nameSql = "SELECT PHOTOG_NAME FROM TB_PHOTOG_BASE_INFO WHERE PHOTOG_ID = '"+photogId+"'";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(nameSql);
		if (list1.size()!=0) {
			photogName = list1.get(0).get("PHOTOG_NAME")+"";
		}
		return photogName;
	}
	
	//上传压缩文件插入数据
	public JSONObject insertArticleInfo(JSONObject obj,String artId) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aId = artId;
		String aTitle = obj.getString("aTitle");
		String aDoc = obj.getString("aDoc");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_ARTICLE_BASE_INFO VALUES ('"+aId+"','TA','','"+aTitle+"','','"+aDoc+"','','','"+curdate+"')";
		if(checkDataConflict(aId,"TB_PHOTOG_ARTICLE_BASE_INFO","ARTICLE_ID")){
			result = this.getJdbcTemplate().update(sql);
		}
		//添加关联标签操作
		String aLabel = obj.getString("aLabel");
		String [] saveLabel = aLabel.split("#");
		String reLabelSql = "INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT(LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES (?,?,?,now())";
		int laResult=0;
		for (int i = 1; i < saveLabel.length; i++) {
			String labelSql="select LABEL_ID from TB_CDE_LABEL where LABEL_NAME ='"+saveLabel[i].trim()+"' and LABEL_TYPE ='A' ";
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(labelSql);
			if (list1.size()!=0) {
				Object [] values = {list1.get(0).get("LABEL_ID"),artId,i};
				laResult=this.getJdbcTemplate().update(reLabelSql,values);
			} else {
				String label_id="";
				String sql1 ="SELECT COUNT(*) FROM TB_CDE_LABEL where  LABEL_TYPE = 'A'";
				String num1 =this.getJdbcTemplate().queryForInt(sql1)+1+"";
				StringBuffer sb =new StringBuffer("L_A");
				for (int a = 0; a < (7-num1.length()); a++) {
					sb.append("0");
				};
				label_id=sb.append(num1).toString();
				String insertsql = "INSERT INTO TB_CDE_LABEL(LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC,DEAL_TIME)VALUES (?,?,'A','',now())";
				Object [] insertValue ={label_id,saveLabel[i].trim()};
				int insertNum = this.getJdbcTemplate().update(insertsql,insertValue);
				Object [] values = {label_id,artId,i};
				laResult=this.getJdbcTemplate().update(reLabelSql,values);
			}
		}
		//添加相关摄影家
		JSONObject returnResult = new JSONObject();
		
		String aPhotog = obj.getString("aPhotog");
		JSONArray array1 = searchPhotog(aPhotog,artId);//存摄影家名
		String aWorks = obj.getString("aWorks");
		JSONArray array2=searchWork(aWorks,artId);//存摄影家名+作品序号
		returnResult.put("failPhg", array1);
		returnResult.put("failWork", array2);
		return returnResult;
	}
	//添加相关摄影家
	public JSONArray searchPhotog(String aPhotog,String artId) {
		JSONArray array=new JSONArray();
		String [] savePhotog = aPhotog.split("#");
		int insertNum =0;
		for (int i = 1; i < savePhotog.length; i++) {
			String photogSql="select PHOTOG_ID from TB_PHOTOG_BASE_INFO where PHOTOG_NAME ='"+savePhotog[i].trim()+"' ";
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(photogSql);
			int pgOr=0;
			if (list1.size()!=0) {
				pgOr+=1;
				String addPgArtSql="INSERT INTO TB_CDE_PHOTOG_ARTICLE_RELAT(ARTICLE_ID,PHOTOG_ID,PHOTOG_ORDER,DEAL_TIME)VALUES (?,?,?,now())";
				Object [] values = {artId,list1.get(0).get("PHOTOG_ID"),pgOr};
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
				String selWkId="SELECT A.WORKS_ID FROM TB_PHOTOG_WORKS_BASE_INFO A "+
									"LEFT JOIN TB_PHOTOG_BASE_INFO B "+
									"ON A.PHOTOG_ID = B.PHOTOG_ID  "+
									"WHERE A.SHOW_ORDER = '"+saveWorks[i+1].trim()+"' AND B.PHOTOG_NAME = '"+saveWorks[i].trim()+"'";
				List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(selWkId);
				int pgOr=0;
				if (list1.size()!=0) {
					pgOr+=1;
					String addWkArSql="INSERT INTO TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT(ARTICLE_ID,WORKS_ID,WORKS_ORDER,DEAL_TIME)VALUES (?,?,?,now())";
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
 