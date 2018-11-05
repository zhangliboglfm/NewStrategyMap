package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CgInformationImportManager;
import com.miapsoft.util.IdGenerateUtil;

@Service("cgInformationImportManager")
public class CgInformationImportManagerImpl extends AbstractManager implements CgInformationImportManager {

	public void insertcginfo(JSONObject obj) {
		// TODO Auto-generated method stub
		String nationId="";
		String dynastyId="";
		int result = 0;
		
		String cgid="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curdate = sdf.format(new Date());
		String name = obj.getString("name");
		String sex = obj.getString("sex");
		String nation = obj.getString("nation");
		String dynasty = obj.getString("dynasty");
		String birthday = obj.getString("birthday");
		String deathday = obj.getString("deathday");
		String word = obj.getString("word");
		String number = obj.getString("number");
		String ancestralhome = obj.getString("ancestralhome");
		String birtharea = obj.getString("birtharea");
		String othername = obj.getString("othername");
		String status = obj.getString("status");
		String importantworks = obj.getString("importantworks");
		String importantdeeds = obj.getString("importantdeeds");
		String repeatSql ="SELECT COUNT(*) FROM TB_CG_BASE_INFO WHERE CGER_NAME='"+name+"'";
		int repCount = this.getJdbcTemplate().queryForInt(repeatSql);
		if (repCount==0) {
			/*查朝代编号*/
		/*	String dynastyNum = "SELECT DYNASTY_CODE FROM TB_CDE_CG_DYNASTY WHERE DYNASTY_NAME='"+dynasty+"'";
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(dynastyNum);
			if (list.size()!=0) {
				dynastyId=list.get(0).get("DYNASTY_CODE")+"";
			}*/
			/*查民族编号*/
		/*	String nationNum = "SELECT NATION_CODE FROM TB_CDE_CG_NATION WHERE NATION_NAME='"+nation+"'";
			List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(nationNum);
			if (list2.size()!=0) {
				nationId=list2.get(0).get("NATION_CODE")+"";
			}*/
			/*显示顺序*/
			String orderNum="SELECT MAX(SHOW_ORDER) FROM TB_CG_BASE_INFO";
			int show_order = this.getJdbcTemplate().queryForInt(orderNum);
			cgid=IdGenerateUtil.getCgId(show_order,sex,nation,dynasty);
			/*int count=0;*/
			if(show_order>=0){
				show_order=show_order+1;
				/*count=show_order/10+1;*/
			}else{
				show_order=0;
				/*count=show_order/10;*/
			}
			/*导入书法家*/
			
		/*	String flag="";
			for(int i=0;i<4-count;i++){
				flag=flag+"0";
			}
			flag=flag+(show_order+1);
			cgid="CG_"+sex+nationId+dynastyId+flag;*/
			String sql="INSERT INTO TB_CG_BASE_INFO VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		/*	Object[] value = {cgid,name,sex,nationId,dynastyId,birthday,deathday,word,number,ancestralhome,birtharea,othername,"???",importantdeeds,show_order,curdate};*/
			Object[] value = {cgid,name,sex,nation,dynasty,birthday,deathday,word,number,ancestralhome,birtharea,othername,"",importantdeeds,show_order,curdate};
			int listdata = this.getJdbcTemplate().update(sql,value);
			String sql2="INSERT INTO TB_SYS_CG_AUDIT_STATUS VALUES(?,?,?,?)";
			Object[] value2 = {cgid,"CG","1",curdate};
			int listdata2 = this.getJdbcTemplate().update(sql2,value2);
		};
	}

}
