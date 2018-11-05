package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.AnalyzeZipManager;
import com.miapsoft.util.IdGenerateUtil;


@Service("anaZipManager")
public class AnaZipManagerImpl extends AbstractManager implements AnalyzeZipManager {
	//根据名称查书法家ID
	public String getCalligId(String cgName) {
		String cgId = "";
		String selSql = "SELECT CGER_ID FROM TB_CG_BASE_INFO WHERE CGER_NAME LIKE '%"+cgName+"%'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql);
		if (list.size()!=0) {
			cgId = list.get(0).get("CGER_ID")+"";
		} else {
			cgId = "noPerson";
		}
		return cgId;
	}
	//验证作品是否已存在，如果不存在，插入。存在，不插入
	public String verifiWorks(String newworkId, String name, String cgId) {
		String workId = "";
		/*String verifiSql = "SELECT WORKS_ID FROM TB_CG_WORKS_BASE_INFO_BAK WHERE WORKS_NAME ='"+name.trim()+"' AND CGER_ID = '"+cgId+"'";
		String insertSql = "INSERT INTO TB_CG_WORKS_BASE_INFO_BAK (WORKS_ID,CGER_ID,WORKS_NAME,CUT_TYPE) VALUES ('"+newworkId+"','"+cgId+"','"+name.trim()+"','Y')";*/
		String verifiSql = "SELECT WORKS_ID FROM TB_CG_WORKS_BASE_INFO WHERE WORKS_NAME ='"+name.trim()+"' AND CGER_ID = '"+cgId+"'";
		String insertSql = "INSERT INTO TB_CG_WORKS_BASE_INFO (WORKS_ID,CGER_ID,WORKS_NAME,CUT_TYPE) VALUES ('"+newworkId+"','"+cgId+"','"+name.trim()+"','Y')";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(verifiSql);
		if (list.size()!=0) {
			workId = list.get(0).get("WORKS_ID")+"";
		} else {
			this.getJdbcTemplate().update(insertSql);
			workId = newworkId;
		}
		return workId;//已存在,并返回作品实有ID
	}
	//删除现有的作品图片关联关系
	public String deleteWorkPicR(String workId) {
		/*String delSql = "DELETE FROM TB_CDE_CG_WORKS_PIC_RELAT_BAK WHERE WORKS_ID ='"+workId+"'";*/
		String delSql = "DELETE FROM TB_CDE_CG_WORKS_PIC_RELAT WHERE WORKS_ID ='"+workId+"'";
		this.getJdbcTemplate().update(delSql);
		return null;
	}
	//往作品图片相关表插入数据
	public String insertNerWorks(String realWorkId, String rfilePath, String showOrder) {
		/*String insertSql = "INSERT INTO TB_CDE_CG_WORKS_PIC_RELAT_BAK VALUES('"+realWorkId+"','"+rfilePath+"','"+showOrder+"',now())";*/
		String insertSql = "INSERT INTO TB_CDE_CG_WORKS_PIC_RELAT VALUES('"+realWorkId+"','"+rfilePath+"','"+showOrder+"',now())";
		this.getJdbcTemplate().update(insertSql);
		return null;
	}
	//生成新workId
	public String getNewWorkId(String cgId) {
		String newId="";
		String wid = "0";
		/*String sql="select max(WORKS_ID) as WORKS_ID from TB_CG_WORKS_BASE_INFO_BAK where CGER_ID='"+cgId+"'";*/
		String sql="select max(WORKS_ID) as WORKS_ID from TB_CG_WORKS_BASE_INFO where CGER_ID='"+cgId+"'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql);
		if (list2.size()!=0) {
			wid = list2.get(0).get("WORKS_ID")==null?"0":list2.get(0).get("WORKS_ID")+"";
			if (!"0".equals(wid)) {
				//W_CG_M0010020001000001
				wid=wid.substring(16);
			}
		}
		int count = Integer.parseInt(wid)+1;
		newId=IdGenerateUtil.getCGWorksId(cgId, count);
		return newId;
	}
	
}
 