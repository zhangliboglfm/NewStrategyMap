package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.CommonManager;

/**
 * <p>Title: CommonManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("commonManager")
public class CommonManagerImpl extends AbstractManager implements CommonManager {

	public JSONObject getPackDemoData(String date, String linetype) {
		JSONObject result = new JSONObject();
		String ltype = "Online";
		if("online".equalsIgnoreCase(linetype)){
			ltype = "Online";
		}else if("offline".equalsIgnoreCase(linetype)){
			ltype = "Offline";
		}
		JSONArray dataZ = new JSONArray();
		JSONArray dataX = new JSONArray();
		JSONArray dataY = new JSONArray();
		String sql1 = "select CPC_ID,CPC_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO where CPC_NAME like '%4G飞享套餐%' order by ORDER_ID";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select b.REGION_NAME,a.CPC_ID,a.ADD_PROD_PROP "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a "+ 
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID WHERE a.CPC_ID = ?  "+
					"and a.STATIS_DATE=? and a.REGION_ID !='1' and a.TRANSACT_TYPE='"+ltype+"' order by b.ORDER_ID";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				dataZ.add(map.get("CPC_NAME").toString().replace("4G飞享套餐", ""));
			}
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				String cpcid=map.get("CPC_ID").toString();
				Object [] value = {cpcid,date};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2, value);
				if(list2.size()!=0){
					if(dataX.size()==0){
						for (int j = 0; j < list2.size(); j++) {
							Map<String, Object> map2 = list2.get(j);
							dataX.add(map2.get("REGION_NAME"));
						}
					}
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						String add_prod_prop = map2.get("ADD_PROD_PROP")!=null ? map2.get("ADD_PROD_PROP").toString() : "0";
						BigDecimal bg = new BigDecimal(Double.valueOf(add_prod_prop)*100);
						double num = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						JSONArray dataarr = new JSONArray();
						dataarr.add(i);
						dataarr.add(j);
						dataarr.add(num);
						dataY.add(dataarr);
					}
				}
			}
		}
		result.put("dataZ", dataZ);		
		result.put("dataX", dataX);		
		result.put("dataY", dataY);		
		return result;
	}
	
	public JSONObject getPackDemoData2(String date, String linetype) {
		JSONObject result = new JSONObject();
		String ltype = "Online";
		if("online".equalsIgnoreCase(linetype)){
			ltype = "Online";
		}else if("offline".equalsIgnoreCase(linetype)){
			ltype = "Offline";
		}
		JSONArray dataZ = new JSONArray();
		JSONArray dataX = new JSONArray();
		JSONArray dataY = new JSONArray();
		ltype="Offline";
		String sql1 = "select REGION_ID,REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select b.CPC_NAME,a.ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a  "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO b on b.CPC_ID=a.CPC_ID "+
					"WHERE a.STATIS_DATE=? and a.REGION_ID =? and b.PARENT_CPC_ID='4g_fx' and a.TRANSACT_TYPE='"+ltype+"' order by b.ORDER_ID";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				dataZ.add(map.get("REGION_NAME").toString());
			}
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				String regionid=map.get("REGION_ID").toString();
				Object [] value = {date,regionid};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2, value);
				if(list2.size()!=0){
					if(dataX.size()==0){
						for (int j = 0; j < list2.size(); j++) {
							Map<String, Object> map2 = list2.get(j);
							dataX.add(map2.get("CPC_NAME").toString().replace("4G飞享套餐", ""));
						}
					}
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						String add_prod_prop = map2.get("ADD_PROD_PROP")!=null ? map2.get("ADD_PROD_PROP").toString() : "0";
						BigDecimal bg = new BigDecimal(Double.valueOf(add_prod_prop)*100);
						double num = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						JSONArray dataarr = new JSONArray();
						dataarr.add(i);
						dataarr.add(j);
						dataarr.add(num);
						dataY.add(dataarr);
					}
				}
			}
		}
		result.put("dataZ", dataZ);		
		result.put("dataX", dataX);		
		result.put("dataY", dataY);		
		return result;
	}

	public JSONObject getPackQipaoData(String date, String linetype) {
		JSONObject result = new JSONObject();
		JSONArray dataLegend = new JSONArray();
		JSONArray data = new JSONArray();
		String ltype = "Online";
		if("online".equalsIgnoreCase(linetype)){
			ltype = "Online";
		}else if("offline".equalsIgnoreCase(linetype)){
			ltype = "Offline";
		}
		String sql1 = "select REGION_ID,REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select b.CPC_NAME,a.ADD_PROD_PROP,a.PROD_CNT,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a  "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO b on b.CPC_ID=a.CPC_ID "+
				"WHERE a.STATIS_DATE=? and a.REGION_ID =? and b.PARENT_CPC_ID='4g_fx' and a.TRANSACT_TYPE='"+ltype+"' order by b.ORDER_ID";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				dataLegend.add(map.get("REGION_NAME").toString());
			}
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				String regionid=map.get("REGION_ID").toString();
				String regionname=map.get("REGION_NAME").toString();
				Object [] value = {date,regionid};
				JSONArray regiondata = new JSONArray();
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2, value);
				if(list2.size()!=0){
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						JSONArray eachdata = new JSONArray();
						eachdata.add(map2.get("PROD_CNT"));
						eachdata.add(map2.get("ADD_PROD_CNT"));
						String add_prod_prop = map2.get("ADD_PROD_PROP")!=null ? map2.get("ADD_PROD_PROP").toString() : "0";
						BigDecimal bg = new BigDecimal(Double.valueOf(add_prod_prop)*100);
						double num = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						eachdata.add(num);
						eachdata.add(map2.get("CPC_NAME"));
						eachdata.add(regionname);
						regiondata.add(eachdata);
					}
				}
				data.add(regiondata);
			}
		}
		result.put("dataLegend", dataLegend);		
		result.put("data", data);		
		return result;
	}

	public JSONObject getPackQipaoData2(String date, String linetype) {
		JSONObject result = new JSONObject();
		JSONArray dataLegend = new JSONArray();
		JSONArray data = new JSONArray();
		String sql1 = "select B.MARKT_SOLUTION_NAME,A.ADD_CNT,A.ADD_H_SUM,ABS(A.ADD_L_SUM) AS ADD_L_SUM "+
					"from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO A "+
					"LEFT JOIN BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO B ON A.ZR_PROD_ID = B.MARKT_SOLUTION_ID "+
					"WHERE A.STATIS_DATE=? ORDER BY B.PRI_SUM";
		Object [] value ={date};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1,value);
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				dataLegend.add(map.get("MARKT_SOLUTION_NAME").toString());
			}
		}
		for (int i = 0; i < dataLegend.size(); i++) {
			String packname = dataLegend.getString(i);
			JSONArray regiondata = new JSONArray();
			for (int j = 0; j < list1.size(); j++) {
				JSONArray eachdata = new JSONArray();
				if(packname.equals(list1.get(j).get("MARKT_SOLUTION_NAME").toString())){
					eachdata.add(list1.get(j).get("ADD_H_SUM"));
					eachdata.add(list1.get(j).get("ADD_L_SUM"));
					eachdata.add(list1.get(j).get("ADD_CNT"));
					eachdata.add(packname);
					eachdata.add(packname);
					regiondata.add(eachdata);
				}
			}
			data.add(regiondata);
		}
		result.put("dataLegend", dataLegend);		
		result.put("data", data);	
		return result;
	}

	//月累计
	public JSONArray getPackQipaoData3(String regionId,String minDate) throws ParseException {
		JSONArray result = new JSONArray();
		String sql1 = "select distinct a.ZR_PROD_ID as MARKT_SOLUTION_ID,b.MARKT_SOLUTION_NAME,b.PRI_SUM from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO_NEW a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO_NEW b on a.ZR_PROD_ID = b.MARKT_SOLUTION_ID "+
					"where a.REGION_ID = ? and a.STATIS_DATE>= ?  order by b.PRI_SUM";
		Object [] vlaue1 = {regionId,minDate};
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1,vlaue1);
		String sql2 = "select a.STATIS_DATE,a.SHENG_SUM as ADD_H_SUM,ABS(a.JIANG_SUM) AS ADD_L_SUM,ABS(a.ALL_SUM) AS ADD_SUM " +
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZR_INFO_NEW a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO_NEW b on a.ZR_PROD_ID=b.MARKT_SOLUTION_ID  "+
					"where a.ZR_PROD_ID = ? and a.REGION_ID = ? AND a.STATIS_DATE>= ?  order by a.STATIS_DATE";
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Date date1 = format1.parse(minDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		
		if(list1.size()!=0){
			for (int i = 0; i <list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				JSONObject packjson = new JSONObject();
				JSONArray ZRData = new JSONArray();
				JSONArray ZCData = new JSONArray();
				JSONArray Data = new JSONArray();
				Object [] value = {map.get("MARKT_SOLUTION_ID"),regionId,minDate};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						String  date = map2.get("STATIS_DATE").toString();
						
						Date date2 = format1.parse(date);
						cal.setTime(date2);
						long time2 = cal.getTimeInMillis();
						long days = (time2-time1)/(1000*3600*24)+1;
						
						double zr = Double.parseDouble(map2.get("ADD_H_SUM").toString());
						double zc = Double.parseDouble(map2.get("ADD_L_SUM").toString());
						double data = Double.parseDouble(map2.get("ADD_SUM").toString());
						
						JSONArray eachZRData = new JSONArray();
						JSONArray eachZCData = new JSONArray();
						JSONArray eachData = new JSONArray();
						
						zr = (zr==0?1:zr);
						
						eachZRData.add(days);
						eachZRData.add(zr);
						eachZCData.add(days);
						eachZCData.add(zc);
						eachData.add(days);
						eachData.add(data);
						
						ZRData.add(eachZCData);
						ZCData.add(eachZRData);
						Data.add(eachData);
					}
				}
				packjson.put("name",map.get("MARKT_SOLUTION_NAME"));
				packjson.put("region", i);
				packjson.put("income", ZRData);//升档收入
				packjson.put("lifeExpectancy", ZCData);//降档收入
				packjson.put("population", Data);//圈大小
				result.add(packjson);
			}
		}
		return result;
	}
	
	//负轴数据
	public JSONArray getPackQipaoData4() {
		JSONArray result = new JSONArray();
		String sql1 = "select * from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO order by PRI_SUM";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select a.STATIS_DATE,a.ALL_SUM,a.TO_GPRS,a.BILL_FEE from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO4 a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b on a.ZR_PROD_ID=b.MARKT_SOLUTION_ID  "+
					"where a.ZR_PROD_ID = ? order by a.STATIS_DATE";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				JSONObject packjson = new JSONObject();
				JSONArray XData = new JSONArray();
				JSONArray YData = new JSONArray();
				JSONArray Data = new JSONArray();
				Object [] value = {map.get("MARKT_SOLUTION_ID")};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						int date = j+1;
						double shouru = Double.parseDouble(map2.get("ALL_SUM").toString());
						double liuliang = Double.parseDouble(map2.get("TO_GPRS").toString());
						double xiaofei = Double.parseDouble(map2.get("BILL_FEE").toString());
						JSONArray eachXData = new JSONArray();
						JSONArray eachYData = new JSONArray();
						JSONArray eachData = new JSONArray();
						
						eachXData.add(date);
						eachXData.add(liuliang);
						eachYData.add(date);
						eachYData.add(shouru/10000);
						eachData.add(date);
						eachData.add(xiaofei);
						
						XData.add(eachXData);
						YData.add(eachYData);
						Data.add(eachData);
					}
				}
				packjson.put("name",map.get("MARKT_SOLUTION_NAME"));
				packjson.put("region", i);
				packjson.put("income", XData);
				packjson.put("lifeExpectancy", YData);
				packjson.put("population", Data);
				result.add(packjson);
			}
		}
		return result;
	}

	//当日新增
	public JSONArray getPackQipaoData5() {
		JSONArray result = new JSONArray();
		String sql1 = "select * from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO order by PRI_SUM";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select a.STATIS_DATE,a.ADD_H_SUM,ABS(a.ADD_L_SUM) AS ADD_L_SUM,ABS(a.ADD_H_SUM+a.ADD_L_SUM) AS ADD_SUM from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO1 a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_ZRZC_INFO b on a.ZR_PROD_ID=b.MARKT_SOLUTION_ID  "+
				"where a.ZR_PROD_ID = ? order by a.STATIS_DATE";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				JSONObject packjson = new JSONObject();
				JSONArray ZRData = new JSONArray();
				JSONArray ZCData = new JSONArray();
				JSONArray Data = new JSONArray();
				Object [] value = {map.get("MARKT_SOLUTION_ID")};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						int date = j+1;
						double zr = Double.parseDouble(map2.get("ADD_H_SUM").toString());
						double zc = Double.parseDouble(map2.get("ADD_L_SUM").toString());
						double data = Double.parseDouble(map2.get("ADD_SUM").toString());
						
						JSONArray eachZRData = new JSONArray();
						JSONArray eachZCData = new JSONArray();
						JSONArray eachData = new JSONArray();
						
						zr = (zr==0?1:zr);
						
						eachZRData.add(date);
						eachZRData.add(zr);
						eachZCData.add(date);
						eachZCData.add(zc);
						eachData.add(date);
						eachData.add(data);
						
						ZRData.add(eachZCData);
						ZCData.add(eachZRData);
						Data.add(eachData);
					}
				}
				packjson.put("name",map.get("MARKT_SOLUTION_NAME"));
				packjson.put("region", i);
				packjson.put("income", ZRData);
				packjson.put("lifeExpectancy", ZCData);
				packjson.put("population", Data);
				result.add(packjson);
			}
		}
		return result;
	}

	/*获取动态气泡图展示时间*/
	public JSONObject getDataTime(String tabelname, String columnstr) throws Exception {
		JSONObject result = new JSONObject();
		Date date = new Date();
		String sql = "select MAX("+columnstr+") AS MAX_DATE from BASS_DATA."+tabelname+" ";
		String maxdate="20170531";
		long days=0;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			maxdate=list.get(0).get("MAX_DATE")==null?"20170531":list.get(0).get("MAX_DATE").toString();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Date date1 = format1.parse(maxdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.add(Calendar.MONTH, -3);
		Date date2 = cal.getTime();
		String mindate = format1.format(date2).substring(0,6)+"01";
		Date date3 = format1.parse(mindate);
		cal.setTime(date3);
		long time3 = cal.getTimeInMillis();
		days = (time1-time3)/(1000*3600*24);
		result.put("maxdate", maxdate);
		result.put("mindate", mindate);
		result.put("days", days);
		return result;
	}

	//人群分布
	public JSONArray getPRData() {
		JSONArray result = new JSONArray();
		String sql1 = "select distinct PEO_GROUP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO6";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1);
		String sql2 = "select USER_CNT,TC_RANGE,USER_FEE from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZRZC_SUM_INFO6 where PEO_GROUP= ?";
		if(list1.size()!=0){
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = list1.get(i);
				JSONObject packjson = new JSONObject();
				JSONArray ZRData = new JSONArray();
				JSONArray ZCData = new JSONArray();
				JSONArray Data = new JSONArray();
				Object [] value = {map.get("PEO_GROUP")};
				List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					for (int j = 0; j < list2.size(); j++) {
						Map<String, Object> map2 = list2.get(j);
						int date = j+1;
						double zr = Double.parseDouble(map2.get("USER_FEE").toString());
						double zc = Double.parseDouble(map2.get("TC_RANGE").toString());
						double data = Double.parseDouble(map2.get("USER_CNT").toString());
						
						JSONArray eachZRData = new JSONArray();
						JSONArray eachZCData = new JSONArray();
						JSONArray eachData = new JSONArray();
						
						zr = (zr==0?10:zr);
						
						eachZRData.add(date);
						eachZRData.add(zr);
						eachZCData.add(date);
						eachZCData.add(zc);
						eachData.add(date);
						eachData.add(data);
						
						ZRData.add(eachZCData);
						ZCData.add(eachZRData);
						Data.add(eachData);
					}
				}
				packjson.put("name",map.get("PEO_GROUP"));
				packjson.put("region", i);
				packjson.put("income", ZCData);
				packjson.put("lifeExpectancy", ZRData);
				packjson.put("population", Data);
				result.add(packjson);
			}
		}
		return result;
	}
}
