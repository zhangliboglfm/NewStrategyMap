/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CgStyleManager;
import com.miapsoft.util.IdGenerateUtil;

@Service
public class CgStyleManagerImpl extends AbstractManager implements CgStyleManager {

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PgStyleManager#getPgStyleWord(java.lang.String)
	 */
	public JSONObject getCgStyleWord(String cgId) {
		JSONObject obj = new JSONObject();
		String sql = "SELECT B.ARTICLE_ID,B.ARTICLE_TITLE,B.ARTICLE_DOC,B.ARTICLE_PIC,B.ARTICLE_URL,B.ARTICLE_TITLE "+
				"FROM TB_CDE_CG_ARTICLE_RELAT A "+
				"LEFT JOIN TB_CG_ARTICLE_BASE_INFO B "+ 
				"ON A.ARTICLE_ID = B.ARTICLE_ID "+
				"WHERE A.CGER_ID=? AND B.ARTICLE_TYPE='ST'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,cgId);
		if(list.size()==1){
			obj.put("artId", list.get(0).get("ARTICLE_ID"));
			obj.put("artName", list.get(0).get("ARTICLE_DOC")==null?"":list.get(0).get("ARTICLE_DOC"));
			obj.put("artTitle", list.get(0).get("ARTICLE_TITLE")==null?"":list.get(0).get("ARTICLE_TITLE"));
			obj.put("artPic", list.get(0).get("ARTICLE_PIC")==null?"":list.get(0).get("ARTICLE_PIC"));
			obj.put("artUrl", list.get(0).get("ARTICLE_URL")==null?"":list.get(0).get("ARTICLE_URL"));
		}else{
			return null;
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PgStyleManager#insertIntoNewArticle(net.sf.json.JSONObject, java.lang.String)
	 */
	public String insertIntoNewArticle(JSONObject obj, String cgId) {
		String resstr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aTitle = obj.getString("aTitle");
		String aDoc = obj.getString("aDoc");
		String idsql = "select MAX(right(ARTICLE_ID, 8)) from TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_TYPE='ST'";
		String maxstr = this.getJdbcTemplate().queryForObject(idsql, String.class);
		int maxidint = 0;
		if(maxstr!=null){
			maxidint = Integer.valueOf(maxstr);
		}
		String aId = IdGenerateUtil.getArticleId("ST", maxidint+1);
		String isql = "INSERT INTO TB_CG_ARTICLE_BASE_INFO (ARTICLE_ID,ARTICLE_TYPE,ARTICLE_TITLE,ARTICLE_DOC,DEAL_TIME) VALUES (?,?,?,?,?)";
		this.getJdbcTemplate().update(isql,aId,"ST",aTitle,aDoc,sdf.format(new Date()));
		String rlesql = "INSERT INTO TB_CDE_CG_ARTICLE_RELAT (ARTICLE_ID,CGER_ID,SHOW_ORDER,DEAL_TIME) VALUES (?,?,?,?)";
		String stasql = "INSERT INTO TB_SYS_CG_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,?,?,?);";
		this.getJdbcTemplate().update(rlesql,aId,cgId,0,sdf.format(new Date()));
		this.getJdbcTemplate().update(stasql,aId,"A",1,sdf.format(new Date()));
		resstr = aId;
		return resstr;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PgStyleManager#updateStyleDocInfo(net.sf.json.JSONObject, java.lang.String)
	 */
	public int updateStyleDocInfo(JSONObject obj, String aId) {
		int resultint = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aTitle = obj.getString("aTitle");
		String aDoc = obj.getString("aDoc");
		String usql = "UPDATE TB_CG_ARTICLE_BASE_INFO SET ARTICLE_DOC=?,ARTICLE_TITLE=?,DEAL_TIME=? WHERE ARTICLE_ID = ?";
		resultint = this.getJdbcTemplate().update(usql, aDoc,aTitle,sdf.format(new Date()),aId);
		String stasql = "UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE='A'";
		this.getJdbcTemplate().update(stasql,1,sdf.format(new Date()),aId);
		return resultint;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PgStyleManager#insertIntoNewStyleWithPic(java.lang.String, java.lang.String)
	 */
	public String insertIntoNewStyleWithPic(String picname, String cgId) {
		String resstr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String idsql = "select MAX(right(ARTICLE_ID, 8)) from TB_CG_ARTICLE_BASE_INFO WHERE ARTICLE_TYPE='ST'";
		String maxstr = this.getJdbcTemplate().queryForObject(idsql, String.class);
		int maxidint = 0;
		if(maxstr!=null){
			maxidint = Integer.valueOf(maxstr);
		}
		String pgName="无名氏";
		String pgnSql="SELECT CGER_NAME FROM TB_CG_BASE_INFO WHERE CGER_ID='"+cgId+"' ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(pgnSql);
		if (list.size()!=0) {
			pgName=list.get(0).get("CGER_NAME")+"风格";
		}
		String aId = IdGenerateUtil.getArticleId("ST", maxidint+1);
		String isql = "INSERT INTO TB_CG_ARTICLE_BASE_INFO (ARTICLE_ID,ARTICLE_TYPE,ARTICLE_TITLE,ARTICLE_PIC,DEAL_TIME) VALUES (?,?,?,?,?)";
		this.getJdbcTemplate().update(isql,aId,"ST",pgName,picname,sdf.format(new Date()));
		String rlesql = "INSERT INTO TB_CDE_CG_ARTICLE_RELAT (ARTICLE_ID,CGER_ID,SHOW_ORDER,DEAL_TIME) VALUES (?,?,?,?)";
		String stasql = "INSERT INTO TB_SYS_CG_AUDIT_STATUS (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,DEAL_TIME) VALUES (?,?,?,?);";
		this.getJdbcTemplate().update(rlesql,aId,cgId,0,sdf.format(new Date()));
		this.getJdbcTemplate().update(stasql,aId,"A",1,sdf.format(new Date()));
		resstr = aId;
		return resstr;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PgStyleManager#updateStylePicInfo(java.lang.String, java.lang.String)
	 */
	public int updateStylePicInfo(String picname, String aId) {
		int resultint = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String usql = "UPDATE TB_CG_ARTICLE_BASE_INFO SET ARTICLE_PIC=?,DEAL_TIME=? WHERE ARTICLE_ID = ?";
		resultint = this.getJdbcTemplate().update(usql, picname,sdf.format(new Date()),aId);
		String stasql = "UPDATE TB_SYS_CG_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE='A'";
		this.getJdbcTemplate().update(stasql,1,sdf.format(new Date()),aId);
		return resultint;
	}

}
