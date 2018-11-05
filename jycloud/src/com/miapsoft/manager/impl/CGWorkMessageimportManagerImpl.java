package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CGWorkMessageimportManager;
import com.miapsoft.util.IdGenerateUtil;

@Service("cgWorkMessageimportManager")
public class CGWorkMessageimportManagerImpl extends AbstractManager implements CGWorkMessageimportManager{

	public int insertcgworkinfo(JSONObject obj) {
		int result = 0;
		int showorder=0;
		String cgworkid=obj.getString("cgworkid");
		String cgerId=obj.getString("cgerId");
		String worksname=obj.getString("works_name");
		String wholename=obj.getString("wholename");
		String years=obj.getString("years");
		String cgytype=obj.getString("cgy_type");
		String wordsnum=obj.getString("words_num");
		String spec=obj.getString("spec");
		String worksbg=obj.getString("works_bg");
		String pstcollection=obj.getString("pst_collection");
		String isimptworks=obj.getString("is_impt_works");
		String cuttype=obj.getString("cut_type");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		if(checkDataConflict(cgworkid,"TB_CG_WORKS_BASE_INFO","WORKS_ID")){
			String sql1="select max(SHOW_ORDER) as SHOW_ORDER from TB_CG_WORKS_BASE_INFO " +
					"where CGER_ID='"+cgerId+"' and IS_IMPT_WORKS='"+isimptworks+"'";
			int num = this.getJdbcTemplate().queryForInt(sql1);
			/* = list1.get(0).get("SHOW_ORDER");*/
			showorder=num+1;
			String sql2="insert into TB_CG_WORKS_BASE_INFO " +
					"(WORKS_ID,CGER_ID,WORKS_NAME,WHOLENAME,CGY_TYPE,WORDS_NUM,SPEC,WORKS_BG,PST_COLLECTION,IS_IMPT_WORKS,SHOW_ORDER,YEARS,DEAL_TIME,CUT_TYPE) " +
					"values ('"+cgworkid+"','"+cgerId+"','"+worksname+"','"+wholename+"','"+cgytype+"','"+wordsnum+"','"+spec+"','"+worksbg+"','"+pstcollection+"','"+isimptworks+"','"+showorder+"','"+years+"','"+curdate+"','"+cuttype+"')";
			result = this.getJdbcTemplate().update(sql2);
			if (result>0) {
				//审核状态表
				String sql3="insert into TB_SYS_CG_AUDIT_STATUS values ('"+cgworkid+"','CGW','1','"+curdate+"')";
				int result1 = this.getJdbcTemplate().update(sql3);
			}
		}
		return result;
	}
	
	public int insertcgworkImageinfo(String filename,String worksid) {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String sql1="SELECT MAX(SHOW_ORDER) FROM TB_CDE_CG_WORKS_PIC_RELAT WHERE WORKS_ID='"+worksid+"'";
		int count=this.getJdbcTemplate().queryForInt(sql1);
		count=count+1;
		String sql2="INSERT INTO TB_CDE_CG_WORKS_PIC_RELAT VALUES('"+worksid+"','"+filename+"','"+count+"','"+curdate+"')";
		result = this.getJdbcTemplate().update(sql2);
		return result;
	}
	

	public String getnewworkid(String cgerId) {
		String newId="";
		String wid="";
		String sql="select max(WORKS_ID) as WORKS_ID from TB_CG_WORKS_BASE_INFO where CGER_ID='"+cgerId+"'";
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql);
		if (list2.size()!=0) {
			wid = list2.get(0).get("WORKS_ID")==null?"0":list2.get(0).get("WORKS_ID")+"";
			if (!"0".equals(wid)) {
				//W_CG_M0010020001000001
				wid=wid.substring(16);
			}
		}
		int count = Integer.parseInt(wid);
		newId=IdGenerateUtil.getCGWorksId(cgerId, count);
		return newId;
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
public static void main(String[] args) {
}
}
