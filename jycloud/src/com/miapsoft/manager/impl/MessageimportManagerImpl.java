/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-7-30
*/
package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.MessageimportManager;
import com.miapsoft.util.IdGenerateUtil;

@Service("messageimportManager")
public class MessageimportManagerImpl extends AbstractManager implements MessageimportManager {

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertPhotographeIinfo(net.sf.json.JSONObject)
	 */
	public int insertPhotographeIinfo_new(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pName = obj.getString("pName");
		String pSex = obj.getString("pSex");
		String pYear = obj.getString("pYear");
		String pBirth = obj.getString("pBirth");
		String pDeath = obj.getString("pDeath");
		String pNation = obj.getString("pNation");
		//String pIndex = obj.getString("pShowIndex");
		String pLabel = obj.getString("pLabel");
		String nationId="";
		JSONArray lablearr = new JSONArray();
		if(!pLabel.equals("")){
			lablearr = getPgLableArr(pLabel);
		}
		/*查国家编号*/
		String nationNum = "SELECT COUNTRY_ID FROM TB_CDE_COUNTRY WHERE COUNTRY_CHN_NAME = '"+pNation.trim()+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(nationNum);
		if (list.size()!=0) {
			nationId=list.get(0).get("COUNTRY_ID")+"";
		}
		//判断重复不重复
		String repeatSql = "SELECT COUNT(1) FROM TB_PHOTOG_BASE_INFO " +
				"WHERE PHOTOG_NAME = '"+pName+"' AND PHOTOG_GENDER = '"+pSex+"' AND BORN_YEAR = '"+pBirth+"' AND NATION = '"+nationId+"' ";
		int repCount = this.getJdbcTemplate().queryForInt(repeatSql);
		if (repCount==0) {
			String curdate = sdf.format(new Date());
			int pIdNum = getMaxIdNumber("PHOTOG_ID", 4, "TB_PHOTOG_BASE_INFO");
			String pId = IdGenerateUtil.getPhotographerId(pSex, nationId, pIdNum+1);
			int maxorder = 0;
			String maxordersql = "SELECT MAX(SHOW_ORDER) FROM TB_PHOTOG_BASE_INFO";
			String order = this.getJdbcTemplate().queryForObject(maxordersql, String.class);
			if(order!=null && !order.equals("")){
				maxorder = Integer.valueOf(order);
			}
			String sql = "INSERT INTO TB_PHOTOG_BASE_INFO (PHOTOG_ID,PHOTOG_NAME,PHOTOG_GENDER,YEARS,BORN_YEAR,DEATH_YEAR,NATION,SHOW_ORDER,DEAL_TIME) VALUES (?,?,?,?,?,?,?,?,?)";
			if(checkDataConflict(pId)){
				result = this.getJdbcTemplate().update(sql,pId,pName,pSex,pYear,pBirth,pDeath,nationId,maxorder+1,curdate);
				if(result == 1){
					String sql_status = "INSERT INTO TB_SYS_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,?,?,?)";
					this.getJdbcTemplate().update(sql_status,pId,"P","1",curdate);
					if(lablearr.size()!=0){
						for (int i = 0; i < lablearr.size(); i++) {
							String lId = insertLable(lablearr.getString(i),"P","");
							if(!lId.equals("")){
								insertLableRelation(lId, pId, i);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertLable(net.sf.json.JSONObject)
	 */
	public String insertLable(String lablename,String labletype,String labledesc) {
		String csql = "select * from TB_CDE_LABEL WHERE LABEL_NAME = ? and LABEL_TYPE = ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(csql,lablename,labletype);
		if(list.size()==0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = sdf.format(new Date());
			int lIdNum = getMaxIdNumber("LABEL_ID", 7, "TB_CDE_LABEL");
			String lId = IdGenerateUtil.getLableId("P", lIdNum+1);
			String sql = "INSERT INTO TB_CDE_LABEL (LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC,DEAL_TIME) VALUES (?,?,?,?,?)";
			this.getJdbcTemplate().update(sql,lId,lablename,labletype,labledesc,curdate);
			return lId;				
		}else{
			String lId = list.get(0).get("LABEL_ID")!=null ? String.valueOf(list.get(0).get("LABEL_ID")) : "";
			return lId;				
		}
	}


	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertLableRelation(net.sf.json.JSONObject)
	 */
	public int insertLableRelation(String lableid,String mid,int showindex) {
		int result = 0;
		String csql = "select * from TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_ID = ? and LABEL_MBODY_ID = ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(csql,lableid,mid);
		if(list.size()==0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = sdf.format(new Date());
			String sql = "INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT (LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES (?,?,?,?)";
			result = this.getJdbcTemplate().update(sql,lableid,mid,showindex,curdate);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertPhotographeIinfo(net.sf.json.JSONObject)
	 */
	public int insertPhotographeIinfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pId = obj.getString("pId");
		String pName = obj.getString("pName");
		String pSex = obj.getString("pSex");
		String pBirth = obj.getString("pBirth");
		String pDeath = obj.getString("pDeath");
		String pNation = obj.getString("pNation");
		String pIntroduce = obj.getString("pIntroduce");
		String pIndex = obj.getString("pIndex");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_BASE_INFO VALUES ('"+pId+"','"+pName+"','"+pSex+"','"+pBirth+"','"+pDeath+"','"+pNation+"','"+pIntroduce+"','"+pIndex+"','"+curdate+"')";
		if(checkDataConflict(pId)){
			result = this.getJdbcTemplate().update(sql);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertPhotographerHeadImgInfo(net.sf.json.JSONObject)
	 */
	public int insertPhotographerHeadImgInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pId = obj.getString("pId");
		String imgType = obj.getString("imgType");
		String fileName = obj.getString("fileName");
		String showFlag = obj.getString("showFlag");
		String showIndex = obj.getString("showIndex");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_PIC_INFO VALUES ('"+pId+"','"+imgType+"','"+fileName+"','"+showFlag+"','"+showIndex+"','"+curdate+"')";
		result = this.getJdbcTemplate().update(sql);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertPhotographerWorksImgInfo(net.sf.json.JSONObject)
	 */
	public int insertPhotographerWorksImgInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String worksId = obj.getString("worksId");
		String worksName = obj.getString("worksName");
		String worksType = obj.getString("worksType");
		String shootDate = obj.getString("shootDate");
		String worksIntro = obj.getString("worksIntro");
		String shootProc = obj.getString("shootProc");
		String fileName = obj.getString("fileName");
		String pId = obj.getString("pId");
		String isRepre = obj.getString("isRepre");
		String showIndex = obj.getString("showIndex");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_WORKS_BASE_INFO VALUES ('"+worksId+"','"+worksName+"','"+worksType+"','"+shootDate+"','"+worksIntro+"','"+shootProc+"','"+fileName+"','"+pId+"','"+isRepre+"','"+showIndex+"','"+curdate+"')";
		if(checkDataConflict(worksId,"TB_PHOTOG_WORKS_BASE_INFO","WORKS_ID")){
			result = this.getJdbcTemplate().update(sql);
		}
		return result;
	}

	
	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertArticleInfo(net.sf.json.JSONObject)
	 */
	public int insertArticleInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aId = obj.getString("aId");
		String aType = obj.getString("aType");
		String aCover = obj.getString("aCover");
		String aTitle = obj.getString("aTitle");
		String aContent = obj.getString("aContent");
		String aDoc = obj.getString("aDoc");
		String aPic = obj.getString("aPic");
		String aUrl = obj.getString("aUrl");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_PHOTOG_ARTICLE_BASE_INFO VALUES ('"+aId+"','"+aType+"','"+aCover+"','"+aTitle+"','"+aContent+"','"+aDoc+"','"+aPic+"','"+aUrl+"','"+curdate+"')";
		if(checkDataConflict(aId,"TB_PHOTOG_ARTICLE_BASE_INFO","ARTICLE_ID")){
			result = this.getJdbcTemplate().update(sql);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertArticlePhotographerInfo(net.sf.json.JSONObject)
	 */
	public int insertArticlePhotographerInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aId = obj.getString("aId");
		String pId = obj.getString("pId");
		String showIndex = obj.getString("showIndex");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_RELAT VALUES ('"+aId+"','"+pId+"','"+showIndex+"','"+curdate+"')";
		result = this.getJdbcTemplate().update(sql);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.MessageimportManager#insertArticleWorksInfo(net.sf.json.JSONObject)
	 */
	public int insertArticleWorksInfo(JSONObject obj) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aId = obj.getString("aId");
		String wId = obj.getString("wId");
		String showIndex = obj.getString("showIndex");
		String curdate = sdf.format(new Date());
		String sql = "INSERT INTO TB_CDE_PHOTOG_ARTICLE_WORKS_RELAT VALUES ('"+aId+"','"+wId+"','"+showIndex+"','"+curdate+"')";
		result = this.getJdbcTemplate().update(sql);
		return result;
	}
	
	
	private boolean checkDataConflict(String pId){
		boolean result = false;
		String sql = "SELECT  * from TB_PHOTOG_BASE_INFO where PHOTOG_ID = '"+pId+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()==0){
			result=true;
		}
		return result;
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
	
	private int getMaxIdNumber(String Column, int InterceptNum, String TabelName){
		String result = "";
		int resint = 0;
		String sql = "select MAX(right("+Column+", "+InterceptNum+")) from "+TabelName;
		result = this.getJdbcTemplate().queryForObject(sql, String.class);
		if(result == null){
			resint = 0;
		}else{
			resint = Integer.valueOf(result);
		}
		return resint;
	}
	public static JSONArray getPgLableArr(String labelstr){
		JSONArray arr = new JSONArray();
		String [] strarr = labelstr.substring(labelstr.indexOf("#")+1, labelstr.length()).split("#");
		arr = JSONArray.fromObject(strarr);
		return arr;
	}
}
