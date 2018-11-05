package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.stereotype.Service;

import com.ibm.db2.jcc.c.yd;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.Situation4gManager;
import com.miapsoft.manager.Situation4gManagerPlus;

/**
 * <p>Title: Situation4gManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("situation4gManagerPlus")
public class Situation4gManagerImplPlus extends AbstractManager implements Situation4gManagerPlus {

	public JSONObject getdatafortable(String date,String regionId) {
		JSONObject result= new JSONObject();

		JSONArray data= new JSONArray();

		String sql=" select "+ 
				" A.ZR_PROD_ID,B.MARKT_SOLUTION_NAME,A.ADD_NUM,A.JIANG_SUM,A.SHENG_SUM,A.ALL_SUM "+
				" from "+
				" BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO_NEW A "+
				" left join "+
				" BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO_NEW B "+
				" on "+
				" A.ZR_PROD_ID=B.MARKT_SOLUTION_ID "+
				" where "+
				" A.STATIS_DATE= ? AND A.ADD_NUM!=0 AND A.REGION_ID= ? "+
				" order by A.ADD_NUM desc ";
		Object [] value={date,regionId};	
		List<Map<String , Object>> list= this.getJdbcTemplate().queryForList(sql,value);
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				Map<String, Object> map = list.get(i);
				JSONObject hangdata= new JSONObject();
				hangdata.put("id", map.get("ZR_PROD_ID"));
				hangdata.put("name", map.get("MARKT_SOLUTION_NAME"));
				hangdata.put("Num", map.get("ADD_NUM"));
				hangdata.put("down", map.get("JIANG_SUM"));
				hangdata.put("up", map.get("SHENG_SUM"));
				hangdata.put("allNum", map.get("ALL_SUM"));
				data.add(hangdata);
			}
		}
		result.put("data", data);
		JSONArray sumdata=new JSONArray();
		String sql1=" select "+ 
				" sum(A.JIANG_SUM) as JIANG_SUM ,sum(A.SHENG_SUM) as SHENG_SUM ,sum(A.ALL_SUM) as ALL_SUM "+
				" from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO_NEW  A "+
				" where  A.STATIS_DATE= ? AND A.ADD_NUM!=0 AND A.REGION_ID= ? "	;
		List<Map<String , Object>> list1= this.getJdbcTemplate().queryForList(sql1,value);
		if(list1.size()!=0){
			Map<String, Object> map= list1.get(0);
			sumdata.add(map.get("JIANG_SUM"));
			sumdata.add(map.get("SHENG_SUM"));
			sumdata.add(map.get("ALL_SUM"));
		}


		result.put("sumdata", sumdata);

		return result;
	}

	/**
	 * 桑基图数据查询
	 */
	public JSONObject getechartsdata(String id, String date,String regionId) {
		JSONObject result=new JSONObject();
		JSONArray data=new JSONArray();
		JSONArray links= new JSONArray();
		double  Jvalue=0l;
		double  Jsum=0l;
		double  Svalue=0l;
		double  Ssum=0l;
	/*	String sql=	" select b.MARKT_SOLUTION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO_NEW a "+ 
				" inner join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b "+ 
				" on a.ZR_PROD_ID=B.MARKT_SOLUTION_ID "+ 
				" where statis_date= ? and Zc_PROD_ID= ? "+ 
				" union "+ 
				" select b.MARKT_SOLUTION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO_NEW a "+  
				" inner join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b "+ 
				" on a.Zr_PROD_ID=B.MARKT_SOLUTION_ID "+ 
				" where statis_date= ? and ZC_PROD_ID= ? ";
			
		Object [] value={date,id,date,id};
		List<Map<String , Object>> list=this.getJdbcTemplate().queryForList(sql,value);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map=list.get(i);
				JSONObject name=new JSONObject();
				name.put("name", map.get("MARKT_SOLUTION_NAME"));
				data.add(name);
			}
			
		}*/
		String  sql1="select MARKT_SOLUTION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO WHERE MARKT_SOLUTION_ID= ?";
		Object [] value1={id};
		List<Map<String , Object>> list1=this.getJdbcTemplate().queryForList(sql1,value1);
		JSONObject name1=new JSONObject();
		if(list1.size()!=0){
			name1.put("name", list1.get(0).get("MARKT_SOLUTION_NAME"));
			/*String str= "{'normal':{color:'#293d60'}},'label':{'normal':{position:'inside'}}";
			name1.put("itemStyle", str);*/
			data.add(name1);
		}
		
		
		String sql2=" select "+
						" A.ZC_PROD_ID ,B.MARKT_SOLUTION_NAME, abs(A.ADD_NUM) as ADD_NUM , abs(A.ZRZC_SUM) as ZRZC_SUM "+
					" from "+
						"BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO_NEW A "+
					" left join "+
						"BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_INFO B "+
					" on	"+
						" A.ZC_PROD_ID=B.MARKT_SOLUTION_ID	"+
					" WHERE "+
						" A.ZR_PROD_ID=? AND A.STATIS_DATE= ? and REGION_ID = ? AND ZR_SUM-Zc_SUM<0 AND A.ADD_NUM!=0 ORDER BY A.ADD_NUM desc,A.ZC_SUM desc fetch first 5 rows only ";
		Object [] value2={id,date,regionId};
		
		List<Map<String, Object>> list2=this.getJdbcTemplate().queryForList(sql2,value2);
		if(list2.size()!=0){
			for(int i=0;i<list2.size();i++){
				JSONObject name2=new JSONObject();
				Map<String, Object> map=list2.get(i);
				name2.put("name",map.get("MARKT_SOLUTION_NAME"));
				String str="{'normal':{'color':'#f62cc6'}}";
				name2.put("itemStyle", str);
				/*name2.put("value", map.get("ADD_NUM"));*/
				data.add(name2);
				JSONObject link1=new JSONObject();
				link1.put("source", map.get("MARKT_SOLUTION_NAME"));
				link1.put("target","降档");
				double d1= Double.valueOf(map.get("ZRZC_SUM").toString());
				double d2= Double.valueOf(map.get("ADD_NUM").toString());
				link1.put("value", d1);
				Jvalue+=d1;
				Jsum+=d2;
				link1.put("value1", d2);
				link1.put("value2", -1*d1);
				link1.put("zcid", map.get("ZC_PROD_ID"));
				link1.put("zrid", id);
				links.add(link1);
			}
			JSONObject name2=new JSONObject();
			name2.put("name","降档");
			String str="{'normal':{'color':'#e81f7e'}}";
			name2.put("itemStyle", str);
			/*name2.put("value",3000);*/
			data.add(name2);
			JSONObject link1=new JSONObject();
			link1.put("target",name1.get("name"));
			link1.put("source","降档");
			//link1.put("value", map.get("ADD_NUM"));
			link1.put("value", Jvalue);
			link1.put("value1",Jsum);
			link1.put("value2",-Jvalue);
			link1.put("zcid", null);
			link1.put("zrid", null);
			links.add(link1);
		}else{
			/*JSONObject name2=new JSONObject();
			name2.put("name","填充1");
			String str="{'normal':{'color':'#f62cc6'}}";
			String str="{'normal':{position:'right'}}";
			name2.put("itemStyle", str);
			name2.put("value", 0);
			data.add(name2);
			JSONObject link1=new JSONObject();
			link1.put("source", "填充1");
			link1.put("target","降档");
			//link1.put("value", map.get("ADD_NUM"));
			link1.put("value", 0);
			link1.put("value1",0);
			link1.put("value2", 0);
			link1.put("zcid", null);
			link1.put("zrid", null);
			links.add(link1);*/
			
		}
		
		String sql3=" select "+
				" A.ZC_PROD_ID ,B.MARKT_SOLUTION_NAME, abs(A.ADD_NUM) as ADD_NUM , abs(A.ZRZC_SUM) as ZRZC_SUM  "+
			" from "+
				"BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO_NEW A "+
			" left join "+
				"BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_INFO B "+
			" on	"+
				" A.ZC_PROD_ID=B.MARKT_SOLUTION_ID	"+
			" WHERE "+
				" A.ZR_PROD_ID=? AND A.STATIS_DATE= ?  and REGION_ID = ? AND ZR_SUM-Zc_SUM>=0 AND A.ADD_NUM!=0 ORDER BY A.ADD_NUM desc,A.ZC_SUM desc fetch first 5 rows only ";
		
		List<Map<String, Object>> list3=this.getJdbcTemplate().queryForList(sql3,value2);
		
		if(list3.size()!=0){
			for (int i = 0; i < list3.size(); i++) {
				Map<String, Object> map= list3.get(i);
				JSONObject name3=new JSONObject();
				name3.put("name",map.get("MARKT_SOLUTION_NAME"));
				String str="{'normal':{color:'#fff000'}}";
				name3.put("itemStyle", str);
				/*name3.put("value", map.get("ADD_NUM"));*/
				data.add(name3);
				JSONObject link1=new JSONObject();
				link1.put("target", "升档");
				link1.put("source",map.get("MARKT_SOLUTION_NAME"));
				//link1.put("value", map.get("ADD_NUM"));
				double d1= Double.valueOf(map.get("ZRZC_SUM").toString());
				double d2= Double.valueOf(map.get("ADD_NUM").toString());
				link1.put("value", d1);
				Svalue+=d1;
				Ssum+=d2;
				link1.put("value", d1);
				link1.put("value1",d2);
				link1.put("value2",d1);
				link1.put("zcid", map.get("ZC_PROD_ID"));
				link1.put("zrid", id);
				links.add(link1);
			}
			
			JSONObject name3=new JSONObject();
			name3.put("name","升档");
			String str1="{'normal':{'color':'#03fd93'}}";
			String str="{'normal':{position:'right'}}";
			name3.put("itemStyle", str1);
			/*name3.put("value",3000);*/
			data.add(name3);
			JSONObject link2=new JSONObject();
			link2.put("target",name1.get("name"));
			link2.put("source","升档");
			//link1.put("value", map.get("ADD_NUM"));
			link2.put("value", Svalue);
			link2.put("value1",Ssum);
			link2.put("value2",Svalue);
			link2.put("zcid", null);
			link2.put("zrid", null);
			links.add(link2);
		}else{
		/*	JSONObject name3=new JSONObject();
			name3.put("name","填充2");
			String str="{'normal':{color:'#fff000'}}";
			name3.put("itemStyle", str);
			name3.put("value", 0);
			data.add(name3);
			JSONObject link1=new JSONObject();
			link1.put("target", "升档");
			link1.put("source","填充2");
			//link1.put("value", map.get("ADD_NUM"));
			link1.put("value", 0);
			link1.put("value1",0);
			link1.put("value2",0);
			link1.put("zcid", null);
			link1.put("zrid", null);
			links.add(link1);*/
		}
		
		
		/*JSONObject name2=new JSONObject();
		name2.put("name","降档");
		String str="{'normal':{'color':'red'}}";
		String str="{'normal':{position:'right'}}";
		name2.put("itemStyle", str);
		name2.put("value",3000);
		data.add(name2);
		
		JSONObject name3=new JSONObject();
		name3.put("name","升档");
		String str1="{'normal':{'color':'blue'}}";
		String str="{'normal':{position:'right'}}";
		name3.put("itemStyle", str1);
		name3.put("value",3000);
		data.add(name3);
		
		JSONObject link1=new JSONObject();
		link1.put("target",name1.get("name"));
		link1.put("source","降档");
		//link1.put("value", map.get("ADD_NUM"));
		link1.put("value", 9999);
		link1.put("value1",10);
		link1.put("value2",null);
		link1.put("zcid", null);
		link1.put("zrid", null);
		
		JSONObject link2=new JSONObject();
		link2.put("target",name1.get("name"));
		link2.put("source","升档");
		//link1.put("value", map.get("ADD_NUM"));
		link2.put("value", 9999);
		link2.put("value1",1);
		link2.put("value2",null);
		link2.put("zcid", null);
		link2.put("zrid", null);
		links.add(link1);
		links.add(link2);*/
		result.put("data", data);
		result.put("links", links);
		return result;
	}



	public JSONObject queryFeeAreaChartData(String date, String ZCPackId,String ZRPackId) {
		JSONObject result = new JSONObject();
		JSONArray datax = new JSONArray();
		JSONArray datay = new JSONArray();
		JSONArray datearr = new JSONArray();
		JSONArray data1 = new JSONArray();
		JSONArray data2 = new JSONArray();
		String dateprv = "";
		Date nowdate = null;
		Date prvdate = null;
		if(date.length()>6){
			date = date.substring(0,6);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		try {
			nowdate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		calendar.add(Calendar.MONTH,-1);
		prvdate = calendar.getTime();
		dateprv = df.format(prvdate);
		String sql = "select round(DUE_FEE,0) as DUE_FEE,count(distinct subs_id) as USER_CNT from BASS_DATA.TB_CHNL_change_markt_user_gprs_fee_m "+
				"where zr_id=? and zc_id=? "+
				"and statis_month=? "+
				"group by round(DUE_FEE,0) order by round(DUE_FEE,0)";
		Object [] value = {ZRPackId,ZCPackId,date};
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, value);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				datax.add(Double.valueOf(list.get(i).get("DUE_FEE").toString()));
				data1.add(Integer.valueOf(list.get(i).get("USER_CNT").toString()));
			}
			datearr.add(date);
			datay.add(data1);
			Object [] value1= {ZRPackId,ZCPackId,dateprv};
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql, value1);
			if(list1.size()!=0){
				for (int i = 0; i < list1.size(); i++) {
					data2.add(Integer.valueOf(list1.get(i).get("USER_CNT").toString()));
				}
				datearr.add(dateprv);
				datay.add(data2);
			}
		}else{
			String sql_date = "select MAX(statis_month) AS statis_month FROM BASS_DATA.TB_CHNL_change_markt_user_gprs_fee_m";
			List<Map<String, Object>> list_date = this.getJdbcTemplate().queryForList(sql_date);
			if(list_date.size()!=0){
				date = (list_date.get(0).get("statis_month")==null?"":list_date.get(0).get("statis_month").toString());
			}
			try {
				nowdate = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setTime(nowdate);
			calendar.add(Calendar.MONTH,-1);
			prvdate = calendar.getTime();
			dateprv = df.format(prvdate);
			Object [] value_new = {ZRPackId,ZCPackId,date};
			list = this.getJdbcTemplate().queryForList(sql, value_new);
			if(list.size()!=0){
				for (int i = 0; i < list.size(); i++) {
					datax.add(Double.valueOf(list.get(i).get("DUE_FEE").toString()));
					data1.add(Integer.valueOf(list.get(i).get("USER_CNT").toString()));
				}
				datearr.add(date);
				datay.add(data1);
				Object [] value1= {ZRPackId,ZCPackId,dateprv};
				List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql, value1);
				if(list1.size()!=0){
					for (int i = 0; i < list1.size(); i++) {
						data2.add(Integer.valueOf(list1.get(i).get("USER_CNT").toString()));
					}
					datearr.add(dateprv);
					datay.add(data2);
				}
			}
		}
		result.put("datax", datax);
		result.put("datay", datay);
		result.put("datearr", datearr);
		return result;
	}
	public JSONObject queryFluxAreaChartData(String date, String ZCPackId,String ZRPackId) {
		JSONObject result = new JSONObject();
		JSONArray datax = new JSONArray();
		JSONArray datay = new JSONArray();
		JSONArray datearr = new JSONArray();
		JSONArray data1 = new JSONArray();
		JSONArray data2 = new JSONArray();
		String dateprv = "";
		Date nowdate = null;
		Date prvdate = null;
		if(date.length()>6){
			date = date.substring(0,6);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		try {
			nowdate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		calendar.add(Calendar.MONTH,-1);
		prvdate = calendar.getTime();
		dateprv = df.format(prvdate);
		String sql = "select round(CHRG_FLUX,0) as CHRG_FLUX,count(distinct subs_id) as USER_CNT from BASS_DATA.TB_CHNL_change_markt_user_gprs_fee_m "+
				"where zr_id=? and zc_id=? "+
				"and statis_month=? "+
				"group by round(CHRG_FLUX,0) order by round(CHRG_FLUX,0)";
		Object [] value = {ZRPackId,ZCPackId,date};
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, value);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				datax.add(Double.valueOf(list.get(i).get("CHRG_FLUX").toString()));
				data1.add(Integer.valueOf(list.get(i).get("USER_CNT").toString()));
			}
			datay.add(data1);
			datearr.add(date);
			Object [] value1= {ZRPackId,ZCPackId,dateprv};
			List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql, value1);
			if(list1.size()!=0){
				for (int i = 0; i < list1.size(); i++) {
					data2.add(Integer.valueOf(list1.get(i).get("USER_CNT").toString()));
				}
				datearr.add(dateprv);
				datay.add(data2);
			}
		}else{
			String sql_date = "select MAX(statis_month) AS statis_month FROM BASS_DATA.TB_CHNL_change_markt_user_gprs_fee_m";
			List<Map<String, Object>> list_date = this.getJdbcTemplate().queryForList(sql_date);
			if(list_date.size()!=0){
				date = (list_date.get(0).get("statis_month")==null?"":list_date.get(0).get("statis_month").toString());
			}
			try {
				nowdate = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setTime(nowdate);
			calendar.add(Calendar.MONTH,-1);
			prvdate = calendar.getTime();
			dateprv = df.format(prvdate);
			Object [] value_new = {ZRPackId,ZCPackId,date};
			list = this.getJdbcTemplate().queryForList(sql, value_new);
			if(list.size()!=0){
				for (int i = 0; i < list.size(); i++) {
					datax.add(Double.valueOf(list.get(i).get("CHRG_FLUX").toString()));
					data1.add(Integer.valueOf(list.get(i).get("USER_CNT").toString()));
				}
				datearr.add(date);
				datay.add(data1);
				Object [] value1= {ZRPackId,ZCPackId,dateprv};
				List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql, value1);
				if(list1.size()!=0){
					for (int i = 0; i < list1.size(); i++) {
						data2.add(Integer.valueOf(list1.get(i).get("USER_CNT").toString()));
					}
					datearr.add(dateprv);
					datay.add(data2);
				}
			}
		}
		result.put("datax", datax);
		result.put("datay", datay);
		result.put("datearr", datearr);
		return result;
	}


	public JSONArray gethistorydata(String zrid,String regionId) {JSONArray result = new JSONArray();
		JSONObject onedata=new JSONObject();

		String sql =" SELECT a.statis_month,a.USER_GROUP,value(GPRS_LAST,0) as GPRS_LAST ,value(GPRS_NEXT,0) as GPRS_NEXT ,value(GPRS_COMPARE,0) as GPRS_COMPARE,value(FEE_LAST,0)as FEE_LAST,value(FEE_NEXT,0) as FEE_NEXT,value(FEE_COMPARE,0) as FEE_COMPARE "+
			" from "+
			" (select statis_month ,USER_GROUP  from bass_data.tb_chnl_change_markt_user_gprs_fee_ld_all_m "+
			" where  region_id= ? "+
			" group by statis_month,USER_GROUP) a "+
			" left join "+
			" bass_data.tb_chnl_change_markt_user_gprs_fee_ld_all_m b "+
			" on a.statis_month=b.statis_month and a.USER_GROUP=b.USER_GROUP "+
			" and ZR_ID= ? and REGION_ID= ?  "+
			" order by a.statis_month desc,a.USER_GROUP desc";

			Object [] value= {regionId,zrid,regionId};
			List<Map<String, Object>> list= this.getJdbcTemplate().queryForList(sql,value);
			if(list.size()!=0){
				for(int i=0;i<list.size();i++){
					Map<String, Object> map=list.get(i);
					onedata.put("date", map.get("STATIS_MONTH"));
					onedata.put("GPLA", map.get("GPRS_LAST"));
					onedata.put("GPNE", map.get("GPRS_NEXT"));
					onedata.put("GPCOM", map.get("GPRS_COMPARE"));
					onedata.put("FELA", map.get("FEE_LAST"));
					onedata.put("FENE", map.get("FEE_NEXT"));
					onedata.put("FECOM", map.get("FEE_COMPARE"));
					onedata.put("DANG", map.get("USER_GROUP"));
					result.add(onedata);
				}
			}
			return result;
	}


	/**
	 * 雷达图数据查询
	 */
	public JSONArray getRadardata(String zrid, String date,String regionId) {

		/*	JSONArray maxdata= new JSONArray();
		Object [] value={date,zrid,regionId};
 		String sql1=" select  (a.GPRSS_FEES_CNT+a.GPRSJ_FEEJ_CNT+a.GPRSS_FEEJ_CNT+a.GPRSJ_FEES_CNT+a.GPRSE_FEEE_CNT) as zonghe  from  bass_data.tb_chnl_change_markt_user_gprs_fee_ld_m a "+ 
 					" where a.STATIS_MONTH= ? and a.ZR_ID= ? and a.REGION_ID= ? order by ALL_CNT DESC fetch first 5 rows only";
 		List<Map<String, Object>> list1=this.getJdbcTemplate().queryForList(sql1,value);
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map=list1.get(i);
				if(!map.get("zonghe").equals("0")){
					maxdata.add(map.get("zonghe"));
				}else{
					return null;
				};
			};
		};


		JSONObject result = new JSONObject();
		JSONArray name= new JSONArray();
		JSONArray ydata=new JSONArray();

		String sql=	" select "+ 
						" a.GPRSS_FEES_CNT, a.GPRSJ_FEEJ_CNT , a.GPRSS_FEEJ_CNT,a.GPRSJ_FEES_CNT ,a.GPRSE_FEEE_CNT ,b.MARKT_SOLUTION_NAME "+
					" from "+
						" bass_data.tb_chnl_change_markt_user_gprs_fee_ld_m a "+
					" left join "+
					 	" BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b "+
					" on "+
					 	" A.ZC_ID=B.MARKT_SOLUTION_ID "+
					" where " +
						"a.STATIS_MONTH= ? and a.ZR_ID= ? and a.REGION_ID= ? "+
					"order by ALL_CNT DESC fetch first 5 rows only ";
		Object [] value1={date,zrid,regionId};
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,value1);
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				Map<String, Object> map=list.get(i);
				name.add(map.get("MARKT_SOLUTION_NAME"));
				JSONArray ondata=new JSONArray();

				BigDecimal bgd1 = BigDecimal.valueOf(Double.valueOf(map.get("GPRSS_FEES_CNT").toString())/Double.valueOf(maxdata.get(i).toString())*100);
				bgd1=bgd1.setScale(2,BigDecimal.ROUND_HALF_UP);

				BigDecimal bgd2 = BigDecimal.valueOf(Double.valueOf(map.get("GPRSJ_FEEJ_CNT").toString())/Double.valueOf(maxdata.get(i).toString())*100);
				bgd2=bgd2.setScale(2,BigDecimal.ROUND_HALF_UP);

				BigDecimal bgd3 = BigDecimal.valueOf(Double.valueOf(map.get("GPRSS_FEEJ_CNT").toString())/Double.valueOf(maxdata.get(i).toString())*100);
				bgd3=bgd3.setScale(2,BigDecimal.ROUND_HALF_UP);

				BigDecimal bgd4 = BigDecimal.valueOf(Double.valueOf(map.get("GPRSJ_FEES_CNT").toString())/Double.valueOf(maxdata.get(i).toString())*100);
				bgd4=bgd4.setScale(2,BigDecimal.ROUND_HALF_UP);

				BigDecimal bgd5 = BigDecimal.valueOf(Double.valueOf(map.get("GPRSE_FEEE_CNT").toString())/Double.valueOf(maxdata.get(i).toString())*100);
				bgd5=bgd5.setScale(2,BigDecimal.ROUND_HALF_UP);

				ondata.add(bgd1);
				ondata.add(bgd2);
				ondata.add(bgd3);
				ondata.add(bgd4);
				ondata.add(bgd5);
//				ondata.add(map.get("GPRSS_FEES_CNT"));
//				ondata.add(map.get("GPRSJ_FEEJ_CNT"));
//				ondata.add(map.get("GPRSS_FEEJ_CNT"));
//				ondata.add(map.get("GPRSJ_FEES_CNT"));
//				ondata.add(map.get("GPRSE_FEEE_CNT"));
				ydata.add(ondata);
			}
		}
		result.put("ydata", ydata);
		result.put("lenged", name);*/


		JSONArray result=new JSONArray();
		String sql=" select "+
				" a.GPRS,a.FEE,a.user_cnt,b.MARKT_SOLUTION_NAME,a.ZC_ID,a.ZR_ID "+
				" from "+
				" BASS_DATA.TB_CHNL_CHANGE_MARKT_USER_GPRS_FEE_XX_M a "+
				" left join "+
				" BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b "+
				" on "+
				" a.ZC_ID=b.MARKT_SOLUTION_ID "+
				" where "+
				" a.STATIS_MONTH=? and a.ZR_ID= ? and a.REGION_ID=? and a.user_group=? and  a.HIGH_LOW ='5' ";
		String [] groupid={"1","2"};
		for (int i = 0; i < groupid.length; i++) {
			Object [] value={date,zrid,regionId,groupid[i]};
			List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,value);
			JSONArray xiliedata=new JSONArray();
			if(list.size()!=0){
				for (int j = 0; j < list.size(); j++) {
					Map<String, Object> map=list.get(j);
					JSONArray onedata= new JSONArray();
					onedata.add(map.get("GPRS"));
					onedata.add(map.get("FEE"));
					onedata.add(map.get("user_cnt"));
					onedata.add(map.get("MARKT_SOLUTION_NAME"));
					onedata.add(map.get("ZC_ID"));
					onedata.add(map.get("ZR_ID"));
					onedata.add(date);
					onedata.add(regionId);
					xiliedata.add(onedata);
				}
			}
			result.add(xiliedata);
		}
		return result;
	}

	/*	public static void main(String[] args) {
		double big=2.35;
		BigDecimal big1=BigDecimal.valueOf(big);
		big1=big1.setScale(0,BigDecimal.ROUND_UP);
		System.out.println(big1);
	}*/

	/*获取雷达图中的最大数据*/
	public JSONArray getmaxradar() {
		JSONArray result=new JSONArray();
		String sql=" select "+ 
				" max(GPRSS_FEES_CNT) as GPRSS_FEES_CNT ,max(GPRSJ_FEEJ_CNT) as GPRSJ_FEEJ_CNT,max(GPRSS_FEEJ_CNT) as GPRSS_FEEJ_CNT,max(GPRSJ_FEES_CNT) as GPRSJ_FEES_CNT,max(GPRSE_FEEE_CNT) as GPRSE_FEEE_CNT "+ 
				" from "+  
				" bass_data.tb_chnl_change_markt_user_gprs_fee_ld_m ";
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			Map<String, Object> map= list.get(0);
			BigDecimal big1=BigDecimal.valueOf(Double.valueOf(map.get("GPRSS_FEES_CNT").toString()));
			big1=big1.setScale(0,BigDecimal.ROUND_UP);

			BigDecimal big2=BigDecimal.valueOf(Double.valueOf(map.get("GPRSJ_FEEJ_CNT").toString()));
			big2=big2.setScale(0,BigDecimal.ROUND_UP);

			BigDecimal big3=BigDecimal.valueOf(Double.valueOf(map.get("GPRSS_FEEJ_CNT").toString()));
			big3=big3.setScale(0,BigDecimal.ROUND_UP);

			BigDecimal big4=BigDecimal.valueOf(Double.valueOf(map.get("GPRSJ_FEES_CNT").toString()));
			big4=big4.setScale(0,BigDecimal.ROUND_UP);

			BigDecimal big5=BigDecimal.valueOf(Double.valueOf(map.get("GPRSE_FEEE_CNT").toString()));
			big5=big5.setScale(0,BigDecimal.ROUND_UP);
			result.add(big1);
			result.add(big2);
			result.add(big3);
			result.add(big4);
			result.add(big5);
		}
		return result;
	}

	public JSONArray initDescrScatter(String date, String zrid, String zcid,String regionid) {
		JSONArray result=new JSONArray();
		String sql=" select "+
				" a.GPRS,a.FEE,a.user_cnt,b.MARKT_SOLUTION_NAME "+
				" from "+
				" BASS_DATA.TB_CHNL_CHANGE_MARKT_USER_GPRS_FEE_XX_M a "+
				" left join "+
				" BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b "+
				" on "+
				" a.ZC_ID=b.MARKT_SOLUTION_ID "+
				" where "+
				" a.STATIS_MONTH=? and a.ZR_ID= ? and a.ZC_ID= ? and a.REGION_ID=? and  a.HIGH_LOW !='5' ";
		Object [] value={date,zrid,zcid,regionid};
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,value);
		JSONArray xiliedata=new JSONArray();
		if(list.size()!=0){
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> map=list.get(j);
				JSONArray onedata=new JSONArray();
				onedata.add(map.get("GPRS"));
				onedata.add(map.get("FEE"));
				onedata.add(map.get("user_cnt"));
				onedata.add(map.get("MARKT_SOLUTION_NAME"));
				xiliedata.add(onedata);
			}
		}
		result.add(xiliedata);
		return result;
	}

}
