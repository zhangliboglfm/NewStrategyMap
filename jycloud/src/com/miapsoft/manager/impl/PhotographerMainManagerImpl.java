/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
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
import com.miapsoft.manager.PhotographerMainManager;
import com.miapsoft.util.IdGenerateUtil;

@Service
public class PhotographerMainManagerImpl extends AbstractManager implements PhotographerMainManager {

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#getPhotographerBaseInfo(java.lang.String)
	 */
	public JSONObject getPhotographerBaseInfo(String pId) {
		JSONObject result = new JSONObject();
		String sql = "select A.PHOTOG_NAME,(CASE WHEN A.PHOTOG_GENDER = 'M' THEN '男' WHEN A.PHOTOG_GENDER = 'F' THEN '女' ELSE '' END) AS PHOTOG_SEX, "+
					"A.YEARS,A.BORN_YEAR,A.DEATH_YEAR,A.DEAL_TIME,A.NATION,B.COUNTRY_CHN_NAME,C.AUDIT_STATUS,D.AUDIT_STATUS_DESC,P.FILE_NAME,R.AUDIT_DESC " +
					"from TB_PHOTOG_BASE_INFO A " +
					"LEFT JOIN TB_CDE_COUNTRY B ON A.NATION = B.COUNTRY_ID "+
					"LEFT JOIN TB_SYS_AUDIT_STATUS C ON A.PHOTOG_ID = C.AUDIT_MBODY_ID AND C.AUDIT_MBODY_TYPE = 'P' "+
					"LEFT JOIN TB_CDE_AUDIT_STATUS D ON C.AUDIT_STATUS=D.AUDIT_STATUS_ID "+
					"LEFT JOIN TB_PHOTOG_PIC_INFO P ON A.PHOTOG_ID = P.PHOTOG_ID AND P.PIC_TYPE = '头像' "+
					"LEFT JOIN TB_SYS_AUDIT_RESULT R ON A.PHOTOG_ID=R.AUDIT_MBODY_ID AND R.AUDIT_MBODY_TYPE='P' "+
					"where A.PHOTOG_ID = ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, pId);
		if(list.size()==1){
			Map<String, Object> map = list.get(0);
			result.put("pName", map.get("PHOTOG_NAME"));
			result.put("pSex", map.get("PHOTOG_SEX"));
			result.put("pNation", map.get("COUNTRY_CHN_NAME"));
			result.put("pNationId", map.get("NATION"));
			result.put("pYears", map.get("YEARS"));
			result.put("pBirthYears", FormateYear(map.get("BORN_YEAR")==null ? "" : map.get("BORN_YEAR").toString()));
			result.put("pDeadYears", FormateYear(map.get("DEATH_YEAR")==null ? "" : map.get("DEATH_YEAR").toString()));
			result.put("pDealTime", map.get("DEAL_TIME")==null ? "" : map.get("DEAL_TIME").toString());
			result.put("pAuditStateId", map.get("AUDIT_STATUS"));
			result.put("pAuditState", map.get("AUDIT_STATUS_DESC"));
			result.put("pHeadImage", map.get("FILE_NAME"));
			result.put("pAuditResult", map.get("AUDIT_DESC")==null?"":map.get("AUDIT_DESC"));
			String lbsql = "SELECT A.LABEL_ID,B.LABEL_NAME FROM TB_CDE_PHOTOG_LABEL_RELAT A LEFT JOIN TB_CDE_LABEL B on A.LABEL_ID = B.LABEL_ID WHERE A.LABEL_MBODY_ID = ?";
			List<Map<String, Object>> listlable = this.getJdbcTemplate().queryForList(lbsql, pId);
			JSONArray label = new JSONArray();
			if(listlable.size()!=0){
				for (int i = 0; i < listlable.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("lbId", listlable.get(i).get("LABEL_ID"));
					obj.put("lbName", listlable.get(i).get("LABEL_NAME"));
					label.add(obj);
				}
				result.put("pLable", label);
			}else{
				result.put("pLable", label);
			}
		}
		return result;
	}

	private String FormateYear(String yearstr){
		String resstr = "";
		if(yearstr.length()==4){
			resstr = yearstr+"年";
		}else if(yearstr.length()==6){
			resstr = yearstr.substring(0, 4)+"年"+yearstr.substring(4, 6)+"月";
		}else if(yearstr.length()==8){
			resstr = yearstr.substring(0, 4)+"年"+yearstr.substring(4, 6)+"月"+yearstr.substring(6, 8)+"日";
		}else{
			resstr = yearstr;
		}
		return resstr;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#getAllNationInfo(java.lang.String)
	 */
	public JSONArray getAllNationInfo() {
		JSONArray data = new JSONArray();
		String sql = "SELECT COUNTRY_ID,COUNTRY_CHN_NAME FROM TB_CDE_COUNTRY";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("nationId", list.get(i).get("COUNTRY_ID"));
				obj.put("nationName", list.get(i).get("COUNTRY_CHN_NAME"));
				data.add(obj);
			}
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#getAllPgLbaleInfo()
	 */
	public JSONArray getAllPgLbaleInfo() {
		JSONArray data = new JSONArray();
		String sql = "SELECT LABEL_ID,LABEL_NAME FROM TB_CDE_LABEL WHERE LABEL_TYPE = 'P'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("lbId", list.get(i).get("LABEL_ID"));
				obj.put("lbName", list.get(i).get("LABEL_NAME"));
				data.add(obj);
			}
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#updatePgBaseInfo(net.sf.json.JSONObject)
	 */
	public int updatePgBaseInfo(String pId,JSONObject obj,String userId) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pgName = obj.getString("pgName");
		String pgSex = obj.getString("pgSex");
		String pgNationId = obj.getString("pgNationId");
		String pgYears = obj.getString("pgYears");
		String pgBirthYears = obj.getString("pgBirthYears");
		String pgDeadYears = obj.getString("pgDeadYears");
		String sql = "UPDATE TB_PHOTOG_BASE_INFO SET PHOTOG_NAME=?,PHOTOG_GENDER=?,YEARS=?,BORN_YEAR=?,DEATH_YEAR=?,NATION=?,DEAL_TIME=? WHERE PHOTOG_ID = ?";
		result = this.getJdbcTemplate().update(sql,pgName,pgSex,pgYears,pgBirthYears,pgDeadYears,pgNationId,sdf.format(new Date()),pId);
		String ssql = "SELECT * FROM TB_SYS_AUDIT_RESULT WHERE AUDIT_MBODY_ID = ? AND AUDIT_MBODY_TYPE = 'P'";
		List<Map<String, Object>> slist = this.getJdbcTemplate().queryForList(ssql,pId);
		if(slist.size()==0){
			String osql = "INSERT INTO TB_SYS_AUDIT_RESULT (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?)";
			this.getJdbcTemplate().update(osql,pId,"P",1,userId,sdf.format(new Date()));
		}else{
			String ursql = "UPDATE TB_SYS_AUDIT_RESULT SET AUDIT_STATUS=?,AUDIT_PERSN=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE=?";
			this.getJdbcTemplate().update(ursql,1,userId,sdf.format(new Date()),pId,"P");
		}
		String sqlnew = "UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE=?";
		this.getJdbcTemplate().update(sqlnew,1,sdf.format(new Date()),pId,"P");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#updatePgHeadImgInfo(java.lang.String, java.lang.String)
	 */
	public int updatePgHeadImgInfo(String pId, String filename) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String csql = "SELECT * from TB_PHOTOG_PIC_INFO where PHOTOG_ID = ? and PIC_TYPE = '头像'";
		List<Map<String, Object>> clist = this.getJdbcTemplate().queryForList(csql,pId);
		if(clist.size()!=0){
			String usql = "UPDATE TB_PHOTOG_PIC_INFO SET FILE_NAME=?,DEAL_TIME=? WHERE PHOTOG_ID=? and PIC_TYPE = '头像'";
			result = this.getJdbcTemplate().update(usql,filename,sdf.format(new Date()),pId);
		}else{
			String isql = "INSERT INTO TB_PHOTOG_PIC_INFO (PHOTOG_ID,PIC_TYPE,FILE_NAME,SHOW_FLAG,SHOW_ORDER,DEAL_TIME) VALUES (?,?,?,?,?,?)";
			result = this.getJdbcTemplate().update(isql,pId,"头像",filename,"1","0",sdf.format(new Date()));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#updatePgLableInfo(net.sf.json.JSONArray)
	 */
	public int updatePgLableInfo(String pId,JSONArray arr) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dsql = "DELETE FROM TB_CDE_PHOTOG_LABEL_RELAT WHERE LABEL_MBODY_ID = ?";
		if(arr.size()!=0){
			this.getJdbcTemplate().update(dsql,pId);
			String isql = "INSERT INTO TB_CDE_PHOTOG_LABEL_RELAT (LABEL_ID,LABEL_MBODY_ID,LABEL_ORDER,DEAL_TIME) VALUES (?,?,?,?)";
			for (int i = 0; i < arr.size(); i++) {
				JSONObject lbobj = arr.getJSONObject(i);
				result+=this.getJdbcTemplate().update(isql,lbobj.getString("lbId"),pId,i,sdf.format(new Date()));
			}
		}else{
			this.getJdbcTemplate().update(dsql,pId);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#addNewLableData(net.sf.json.JSONObject)
	 */
	public JSONObject addNewLableData(JSONObject obj) {
		JSONObject resultobj = new JSONObject();
		int result = 0;//返回0,即为失败
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lbName = obj.getString("lbName");
		String lbDesc = obj.getString("lbDesc");
		String csql = "SELECT * FROM TB_CDE_LABEL WHERE LABEL_NAME = ? and LABEL_TYPE = 'P'";
		List<Map<String, Object>> clist = this.getJdbcTemplate().queryForList(csql,lbName);
		if(clist.size()!=0){
			result = 2;//返回2,即为存在重复标签
		}else{
			String isql = "INSERT INTO TB_CDE_LABEL (LABEL_ID,LABEL_NAME,LABEL_TYPE,LABEL_DESC,DEAL_TIME) VALUES (?,?,?,?,?)";
			String idsql = "select MAX(right(LABEL_ID, 4)) from TB_CDE_LABEL WHERE LABEL_TYPE='P'";
			String maxstr = this.getJdbcTemplate().queryForObject(idsql, String.class);
			int maxidint = Integer.valueOf(maxstr);
			String lbId = IdGenerateUtil.getLableId("P", maxidint+1);
			this.getJdbcTemplate().update(isql, lbId,lbName,"P",lbDesc,sdf.format(new Date()));
			resultobj.put("lbId", lbId);
			result = 1;//返回1,即为成功插入数据
		}
		resultobj.put("state", result);
		return resultobj;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#getAllAuditStatus()
	 */
	public JSONArray getAllAuditStatus() {
		JSONArray result = new JSONArray();
		String ssql = "SELECT AUDIT_STATUS_ID,AUDIT_STATUS_DESC FROM TB_CDE_AUDIT_STATUS";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(ssql);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("auditId", list.get(i).get("AUDIT_STATUS_ID"));
				obj.put("auditName", list.get(i).get("AUDIT_STATUS_DESC"));
				result.add(obj);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.miapsoft.manager.PhotographerMainManager#saveAuditStatus(java.lang.String, java.lang.String)
	 */
	public int saveAuditStatus(String pId, String auditstatusid, String auditdesc, String userId) {
		int resultint = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String usql = "UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE='P'";
		resultint = this.getJdbcTemplate().update(usql,auditstatusid,sdf.format(new Date()),pId);
		String ssql = "SELECT * FROM TB_SYS_AUDIT_RESULT WHERE AUDIT_MBODY_ID = ? AND AUDIT_MBODY_TYPE = 'P'";
		List<Map<String, Object>> slist = this.getJdbcTemplate().queryForList(ssql,pId);
		if(slist.size()==0){
			String osql = "INSERT INTO TB_SYS_AUDIT_RESULT (AUDIT_MBODY_ID,AUDIT_MBODY_TYPE,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,DEAL_TIME) VALUES (?,?,?,?,?,?)";
			this.getJdbcTemplate().update(osql,pId,"P",auditstatusid,auditdesc,userId,sdf.format(new Date()));
		}else{
			String ursql = "UPDATE TB_SYS_AUDIT_RESULT SET AUDIT_STATUS=?,AUDIT_DESC=?,AUDIT_PERSN=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE=?";
			this.getJdbcTemplate().update(ursql,auditstatusid,auditdesc,userId,sdf.format(new Date()),pId,"P");
		}
		String sqlnew = "UPDATE TB_SYS_AUDIT_STATUS SET AUDIT_STATUS=?,DEAL_TIME=? WHERE AUDIT_MBODY_ID=? AND AUDIT_MBODY_TYPE=?";
		this.getJdbcTemplate().update(sqlnew,auditstatusid,sdf.format(new Date()),pId,"P");
		return resultint;
	}
}
