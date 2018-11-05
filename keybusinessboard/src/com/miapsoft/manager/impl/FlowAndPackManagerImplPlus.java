package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import com.ibm.db2.jcc.b.l;
import com.ibm.db2.jcc.c.yd;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.FlowAndPackManager;
import com.miapsoft.manager.FlowAndPackManagerPlus;

/**
 * <p>Title: FlowAndPackManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("flowAndPackManagerPlus")
public class FlowAndPackManagerImplPlus extends AbstractManager implements FlowAndPackManagerPlus {

	public JSONArray gettableTopten(String date,String regionId) {
		JSONArray result= new JSONArray();
		JSONObject ondata=new JSONObject();
		String sql="	SELECT a.ADD_PROD_CNT, b.zr_name,b.IS_IMPORT FROM BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a	"+
					"	inner join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO b	"+
					"	on a.cpc_id=b.zr_id and b.groupid='flux'	"+
					"	WHERE a.statis_date= ? and a.REGION_ID= ? order by a.ADD_PROD_CNT desc fetch first 10 rows only	";
		Object [] value={date,regionId};
		List<Map<String, Object>> list= this.getJdbcTemplate().queryForList(sql,value);
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map=list.get(i);
				double d = Double.valueOf(map.get("ADD_PROD_CNT").toString());
				ondata.put("num", NumberFormat.getInstance().format(d));
				ondata.put("name", map.get("zr_name"));
				ondata.put("imp", map.get("IS_IMPORT"));
				result.add(ondata);
			}
		}
		return result;
	}

	public JSONObject initd3underLine(String date,String regionId1,String yuzhi) {

		JSONObject  result = new JSONObject();
		JSONArray regiondata=new JSONArray();
		JSONArray data=new JSONArray();
		if(regionId1.equals("1")){
			String sql1=" select REGION_ID ,REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO where order_id<13 order by order_id asc ";
			List<Map<String, Object>> list1=this.getJdbcTemplate().queryForList(sql1);
			
			if (list1.size()!=0) {
				for (int i = 0; i < list1.size(); i++) {
					JSONObject oneRegion=new JSONObject();
					Map<String, Object> map = list1.get(i);
					oneRegion.put("regionname", map.get("REGION_NAME"));
					oneRegion.put("REGION_ID", map.get("REGION_ID"));
					regiondata.add(oneRegion);
				}
			}
			String sql2=" select distinct a.OFFLINE_CNT ,ROUND(a.OFFLINE_RANT ,4) as OFFLINE_RANT ,b.zr_name from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_YJ_INFO a "+
					" INNER JOIN BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO B "+
					" ON A.CPC_ID=B.zr_id AND B.groupid='flux' and b.is_import='1'  "+
					" where statis_date= ? and Offline_rant>= ? and offline_cnt>=300  and REGION_ID= ? and REGION_COUNTRY='ALL' "+
					" order by Offline_rant desc";
			for (int i = 0; i < regiondata.size(); i++) {	
				JSONArray oneCitydata=new JSONArray();
				JSONObject oneRegion=regiondata.getJSONObject(i);
				Object [] value2 ={date,yuzhi,oneRegion.get("REGION_ID")};
				List<Map<String, Object>> list= this.getJdbcTemplate().queryForList(sql2,value2);
				if(list.size()!=0){
					for (int j = 0; j < list.size(); j++) {
						JSONObject onedata = new JSONObject();
						Map<String, Object> map=list.get(j);
						BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("OFFLINE_RANT").toString())*100);
						bgd=bgd.setScale(2,BigDecimal.ROUND_HALF_UP);
						double d = Double.valueOf(map.get("OFFLINE_CNT").toString());
						onedata.put("num", NumberFormat.getInstance().format(d));
						onedata.put("index", j);
						onedata.put("rate", bgd);
						onedata.put("name", map.get("zr_name"));
						onedata.put("rate1", bgd+"%");
						oneCitydata.add(onedata);
					}
				}
				data.add(oneCitydata);
				
			}
			result.put("name", regiondata);
			result.put("data", data);
		}else{
			
			String sql1= " select distinct REGION_COUNTRY,c.REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_YJ_INFO a "+ 
					 	" left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO c "+
					 	" on a.REGION_COUNTRY=c.REGION_ID "+
					 " where a.statis_date= ? and a.Offline_rant>= ? and a.offline_cnt>=100  and a.REGION_ID= ?  and region_country <> 'ALL' "+
									  " order by REGION_COUNTRY desc ";
			Object [] value1={date,yuzhi,regionId1};
			List<Map<String, Object>> list1=this.getJdbcTemplate().queryForList(sql1,value1);
			
			if (list1.size()!=0) {
				for (int i = 0; i < list1.size(); i++) {
					JSONObject oneRegion=new JSONObject();
					Map<String, Object> map = list1.get(i);
					oneRegion.put("regionname", map.get("REGION_NAME"));
					oneRegion.put("REGION_COUNTRY", map.get("REGION_COUNTRY"));
					regiondata.add(oneRegion);
				}
			}
			String sql2=" select distinct a.OFFLINE_CNT ,ROUND(a.OFFLINE_RANT ,4) as OFFLINE_RANT ,b.zr_name from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_YJ_INFO a "+
					" INNER JOIN BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO B "+
					" ON A.CPC_ID=B.zr_id AND B.groupid='flux' and b.is_import='1'  "+
					" where statis_date= ? and Offline_rant>= ? and offline_cnt>=100  and REGION_COUNTRY= ? ";
					
			for (int i = 0; i < regiondata.size(); i++) {	
				JSONArray oneCitydata=new JSONArray();
				JSONObject oneRegion=regiondata.getJSONObject(i);
				Object [] value2 ={date,yuzhi,oneRegion.get("REGION_COUNTRY")};
				List<Map<String, Object>> list= this.getJdbcTemplate().queryForList(sql2,value2);
				if(list.size()!=0){
					for (int j = 0; j < list.size(); j++) {
						JSONObject onedata = new JSONObject();
						Map<String, Object> map=list.get(j);
						BigDecimal bgd = BigDecimal.valueOf(Double.valueOf(map.get("OFFLINE_RANT").toString())*100);
						bgd=bgd.setScale(2,BigDecimal.ROUND_HALF_UP);
						double d = Double.valueOf(map.get("OFFLINE_CNT").toString());
						onedata.put("num", NumberFormat.getInstance().format(d));
						onedata.put("index", j);
						onedata.put("rate", bgd);
						onedata.put("name", map.get("zr_name"));
						onedata.put("rate1", bgd+"%");
						oneCitydata.add(onedata);
					}
				}
				data.add(oneCitydata);
				
			}
			result.put("name", regiondata);
			result.put("data", data);
		}
		return result;
	}

	public JSONObject initMainPushPack(String date,String regionId) {
		JSONObject result=new JSONObject();
		JSONArray xname1=new JSONArray();
		JSONArray ydata=new JSONArray();//本月
		JSONArray ydata1=new JSONArray();//上月
		String sql=" select A.GROUP_NAME,coalesce(SUM(ADD_PROD_CNT),0) as add_prod_cnt from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO A "+
					" LEFT JOIN BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW  B "+
					" ON A.ZR_ID=B.CPC_ID AND B.STATIS_DATE= ? AND REGION_ID= ? AND REGION_COUNTRY='ALL' AND TRANSACT_type='ALL' AND PARENT_CPC_ID='flux' "+
					" where groupid='flux' and is_import='1' "+
					" GROUP BY A.GROUP_NAME";// order by add_prod_cnt DESC";// 加排序
		Object [] value ={date,regionId};
		String date1="";
		DateFormat df= new SimpleDateFormat("yyyyMMdd");
		try {
			Date date2 = df.parse(date);
			Calendar g =Calendar.getInstance();
			g.setTime(date2);
			g.add(Calendar.MONTH,-1);
			Date date3 =g.getTime();
			date1=df.format(date3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Object [] value1 ={date1,regionId};
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sql,value);
		List<Map<String, Object>> list1=this.getJdbcTemplate().queryForList(sql,value1);
	
		if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map=list.get(i);
				//Map<String, Object> map1=list1.get(i);
				xname1.add(map.get("group_name"));
				ydata.add(map.get("add_prod_cnt"));
				//ydata1.add(map1.get("add_prod_cnt"));
			}
		}
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map1=list1.get(i);
				ydata1.add(map1.get("add_prod_cnt"));
			}
		}
		
		JSONArray ydata2 =new JSONArray();
		if(ydata1.size()!=0){
			for(int i=0;i<ydata.size();i++){
				double d=Double.valueOf(ydata.get(i).toString());
				double d1=Double.valueOf(ydata1.get(i).toString());
				if((d==0)&&(d1==0)){
					ydata2.add("0");
				}else if((d!=0)&&(d1==0)){
					ydata2.add("100");
				}else if((d==0)&&(d1!=0)){
					ydata2.add("-100");
				}else if((d!=0)&&(d1!=0)){
					BigDecimal bgd=BigDecimal.valueOf((d-d1)/d*100);
					bgd=bgd.setScale(2,BigDecimal.ROUND_HALF_UP);
					ydata2.add(bgd);
				}
			}
		};
		result.put("name", xname1);
		result.put("data", ydata);
		result.put("data1", ydata1);
		result.put("data2", ydata2);
		return result;
	}
	
	
/*	public static void main(String[] args) throws ParseException {
		String date="20170901";
		DateFormat df= new SimpleDateFormat("yyyyMMdd");
		Date date2= df.parse(date); 
		System.out.println(date2);
		Calendar g =Calendar.getInstance();
		g.setTime(date2);
		g.add(Calendar.MONTH,-1);
		Date date3 =g.getTime();
		System.out.println(df.format(date3));
	}*/
		
}


