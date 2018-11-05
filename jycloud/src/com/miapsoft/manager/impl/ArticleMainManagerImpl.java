package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.ArticleMainManager;

@Service("ArticleMainmanager")
public class ArticleMainManagerImpl extends AbstractManager implements ArticleMainManager{

	public JSONObject getWzDates(String wzName, String pageNum, String pageSize) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		List<Object> value2 = new ArrayList<Object>();
		String sql1="select count(*) from TB_PHOTOG_ARTICLE_BASE_INFO a ";
		String sql3="select b.ARTICLE_ID,b.ARTICLE_TYPE,b.ARTICLE_COVER,b.ARTICLE_TITLE,b.ARTICLE_CONTENT,b.ARTICLE_DOC,b.ARTICLE_PIC,b.ARTICLE_URL,b.DEAL_TIME "+
				"from TB_PHOTOG_ARTICLE_BASE_INFO b ";
		if ("".equals(wzName) || ""==wzName || null==wzName) {
			sql1+="where a.ARTICLE_TYPE='TA'";
			sql3+="where b.ARTICLE_TYPE='TA' order by b.DEAL_TIME LIMIT ? , ?";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
		} else {
			sql1+="where a.ARTICLE_TYPE='TA' and a.ARTICLE_TITLE like '%"+wzName+"%'";
			sql3+="where b.ARTICLE_TYPE='TA' and b.ARTICLE_TITLE like '%"+wzName+"%' " +
					"order by b.ARTICLE_ID LIMIT ? , ?";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));			
		}
		int countNum = this.getJdbcTemplate().queryForInt(sql1);
		result.put("countNum", countNum);
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql3,value2.toArray());
		result.put("list", JSONArray.fromObject(list));
		return result;
	}

	public JSONObject getWzTags(String wzName) {
		JSONObject result = new JSONObject();
		String sql="select a.*,d.LABEL_NAME from "+
				"(select c.LABEL_ID "+
				"from TB_PHOTOG_ARTICLE_BASE_INFO b "+
				"left join TB_CDE_PHOTOG_LABEL_RELAT c "+
				"on c.LABEL_MBODY_ID=b.ARTICLE_ID where b.ARTICLE_TYPE='TA' and b.ARTICLE_TITLE='"+wzName+"' " +
				"order by c.LABEL_ORDER "+
				" ) a " +
				"left join TB_CDE_LABEL d "+
				"on a.LABEL_ID=d.LABEL_ID where d.LABEL_TYPE='A' ";
		List<Map<String, Object>> listtag = this.getJdbcTemplate().queryForList(sql);
		result.put("listtag", JSONArray.fromObject(listtag));
		return result;
	}

	public int saveMessages(String wzImageSrc, String wzImageAlt,
			String wzName, String wzContent, JSONArray jsonArray1,
			JSONArray jsonArray2, String wzIdnet) {
		int num1 = 0;
		int num2 = 0;
		String sql1="UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET ARTICLE_COVER=?,ARTICLE_TITLE=?,ARTICLE_CONTENT=? " +
				"where ARTICLE_ID=? and ARTICLE_TYPE='TA'";
		Object [] value1 = {wzImageAlt,wzName,wzContent,wzIdnet};
		num1 =this.getJdbcTemplate().update(sql1, value1);
		int tagsize=jsonArray1.size();
		/*String sql2="";
		for (int i = 0; i < tagsize; i++) {
			String tagName=jsonArray2.get(i).toString();
			String tagId=jsonArray1.get(i).toString();
			sql2="UPDATE TB_CDE_LABEL SET LABEL_NAME='"+tagName+"' where LABEL_ID='"+tagId+"' and LABEL_TYPE='A' ";
			num2 =this.getJdbcTemplate().update(sql2);
		}*/
		System.out.println(num1);
		return num1;
	}

	public JSONArray getLablist_Art(String PHOTOG_ID, String tagName) {
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
			sql+="WHERE a.LABEL_TYPE ='A' order by a.LABEL_ID asc";
		}else {
			sql+="WHERE a.LABEL_TYPE ='A' and a.LABEL_NAME like '%"+tagName+"%' order by a.LABEL_ID asc";
		}
				
		Object [] value = {PHOTOG_ID};
		JSONArray result = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql,value));
		return result;
	}

	public String operateLabel_Art(String PHOTOG_ID, String LABEL_ID,
			String operate) {
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

	public JSONObject getZP(String wzIdnet) {
		JSONObject result = new JSONObject();
		String sql="select b.WORKS_ID,b.WORKS_NAME,b.FILE_NAME " +
				"from TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT a " +
				"left join TB_PHOTOG_WORKS_BASE_INFO b " +
				"on b.WORKS_ID=a.WORKS_ID " +
				"where a.ARTICLE_ID=? " +
				"order by b.SHOW_ORDER";
		Object [] value = {wzIdnet};
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,value);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}

	public boolean updateArtImg(String PHOTOG_ID, String filename) {
		String sql ="UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET ARTICLE_COVER = ? WHERE ARTICLE_ID= ? and ARTICLE_TYPE ='TA'";
		Object [] value = {filename,PHOTOG_ID};
		int row =  this.getJdbcTemplate().update(sql, value);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}

	public JSONObject getRyCounts(String zzName, String pageNum, String pageSize) {
		JSONObject result = new JSONObject();
		int startRow = (Integer.parseInt(pageSize)-1)*Integer.parseInt(pageNum);
		List<Object> value1 = new ArrayList<Object>();
		List<Object> value2 = new ArrayList<Object>();
		String sql1="select count(*) from TB_PHOTOG_BASE_INFO a ";
		String sql3="select a.PHOTOG_ID,a.PHOTOG_NAME,b.PIC_TYPE,b.FILE_NAME,a.SHOW_ORDER " +
				"from TB_PHOTOG_BASE_INFO a " +
				"left join TB_PHOTOG_PIC_INFO b on a.PHOTOG_ID=b.PHOTOG_ID ";
		if ("".equals(zzName) || ""==zzName || null==zzName) {
			sql3+="where b.PIC_TYPE='头像' order by a.SHOW_ORDER LIMIT ? , ?";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));
		} else {
			sql1+="where a.PHOTOG_NAME like '%"+zzName+"%'";
			sql3+="where a.PHOTOG_NAME like '%"+zzName+"%' and b.PIC_TYPE='头像' " +
					"order by a.SHOW_ORDER LIMIT ? , ?";
			value2.add(startRow);
			value2.add(Integer.parseInt(pageNum));			
		}
		int countNum = this.getJdbcTemplate().queryForInt(sql1);
		result.put("countNum", countNum);
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql3,value2.toArray());
		result.put("list", JSONArray.fromObject(list));
		return result;
	}

	public JSONArray getAreInterpret(String articleId, String layId) {
		JSONArray array=new JSONArray();
		String sql="";
		if ("777".equals(layId)) {//文章解读
			sql="select ARTICLE_DOC,ARTICLE_PIC,ARTICLE_URL from TB_PHOTOG_ARTICLE_BASE_INFO where ARTICLE_ID='"+articleId+"' and ARTICLE_TYPE='TA' ";
		} else if ("999".equals(layId)) {//作品解读
			sql="select a.ARTICLE_DOC,a.ARTICLE_PIC,a.ARTICLE_URL " +
					"from TB_PHOTOG_ARTICLE_BASE_INFO a " +
					"left join TB_PHOTOG_WORKS_BASE_INFO b " +
					"on a.ARTICLE_ID=b.WORKS_INTRO " +
					"where b.WORKS_ID='"+articleId+"' and a.ARTICLE_TYPE='WI'";
		} else if ("789".equals(layId)) {//人物解读
			sql+="select a.ARTICLE_DOC,a.ARTICLE_PIC,a.ARTICLE_URL " +
					"from TB_PHOTOG_ARTICLE_BASE_INFO a " +
					"left join TB_PHOTOG_BASE_INFO b " +
					"on a.ARTICLE_ID=b.CORE_INTRO " +
					"where b.PHOTOG_ID='"+articleId+"' and a.ARTICLE_TYPE='CI'";
		}
		List<Map<String, Object>>list = this.getJdbcTemplate().queryForList(sql);
		String ARTICLEPIC="";
		String ARTICLEURL="";
		String ARTICLEDOC="";
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			ARTICLEURL=map.get("ARTICLE_URL").toString();
			ARTICLEPIC=map.get("ARTICLE_PIC").toString();
			ARTICLEDOC=map.get("ARTICLE_DOC").toString();
			array.add(ARTICLEURL);
			array.add(ARTICLEPIC);
			array.add(ARTICLEDOC);
		}
		return array;
	}

	public JSONObject photogResume(String articleId) {
		JSONObject result = new JSONObject();
		String sql="select b.ARTICLE_DOC,b.ARTICLE_PIC,b.ARTICLE_URL " +
				"from TB_PHOTOG_BASE_INFO a " +
				"left join TB_PHOTOG_ARTICLE_BASE_INFO b " +
				"on b.ARTICLE_ID=a.CORE_INTRO " +
				"where b.ARTICLE_TYPE='CI' and a.PHOTOG_ID='"+articleId+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}

	public JSONObject reuploadJDu_ART(String filePath, String articleId,String xuanlei, String layId) {
		JSONObject result = new JSONObject();
		JSONArray array1 = new JSONArray();
		String sql="";
		String ziduanName="";
		if ("wordJD".equals(xuanlei)) {
			ziduanName="ARTICLE_DOC";
		} else if ("imgJD".equals(xuanlei)) {
			ziduanName="ARTICLE_PIC";
		} else {
			ziduanName="ARTICLE_URL";
		}
		if ("777".equals(layId)) {//文章解读
			sql="UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET "+ziduanName+"='"+filePath+"' WHERE ARTICLE_ID ='"+articleId+"' AND ARTICLE_TYPE = 'TA'";
		} else if ("999".equals(layId)) {//作品解读
			String sql1="select WORKS_INTRO from TB_PHOTOG_WORKS_BASE_INFO where WORKS_ID='"+articleId+"'";
			List<Map<String, Object>>list1 = this.getJdbcTemplate().queryForList(sql1);
			//JSONArray result1=JSONArray.fromObject(list1);
			String artid=list1.get(0).get("WORKS_INTRO").toString();
			sql="UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET "+ziduanName+"='"+filePath+"' WHERE ARTICLE_ID ='"+artid+"' AND ARTICLE_TYPE = 'WI'";
		} else if ("789".equals(layId)) {//人物解读
			String sql1="select CORE_INTRO from TB_PHOTOG_BASE_INFO where PHOTOG_ID='"+articleId+"'";
			List<Map<String, Object>>list1 = this.getJdbcTemplate().queryForList(sql1);
			//JSONArray result1=JSONArray.fromObject(list1);
			String artid=list1.get(0).get("CORE_INTRO").toString();
			sql+="UPDATE TB_PHOTOG_ARTICLE_BASE_INFO SET "+ziduanName+"='"+filePath+"' WHERE ARTICLE_ID ='"+artid+"' AND ARTICLE_TYPE = 'CI'";
		}
		int countt1=this.getJdbcTemplate().update(sql);
		if(countt1>0){
			array1.add("1");
		}else{
			array1.add("2");
		}
		result.put("flag", array1);
		return result;
	}

}
