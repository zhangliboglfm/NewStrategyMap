package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.GeneralSituationManagerPlus;

/**
 * <p>Title: GeneralSituationManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-24
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("generalSituationManagerPlus")
public class GeneralSituationManagerImplPlus extends AbstractManager implements GeneralSituationManagerPlus {

	public JSONObject getDataDayTime(String tabelname, String columnstr) throws Exception {
		JSONObject result = new JSONObject();
		String sql = "select MAX("+columnstr+") AS MAX_DATE,MIN("+columnstr+") AS MIN_DATE from BASS_DATA."+tabelname+" ";
		String maxdate="20170531";
		String mindate="20170501";
		long days=0;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()!=0){
			maxdate=list.get(0).get("MAX_DATE")==null?"20170531":list.get(0).get("MAX_DATE").toString();
			mindate=list.get(0).get("MIN_DATE")==null?"20170501":list.get(0).get("MIN_DATE").toString();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = format.parse(maxdate);
		Date date2 = format.parse(mindate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		days = (time1-time2)/(1000*3600*24);
		result.put("maxdate", maxdate);
		result.put("mindate", mindate);
		result.put("days", days);
		return result;
	}

	public JSONObject queryGeneralData(String date,String regionId) {
		JSONObject result = new JSONObject();
		JSONObject propjson = new JSONObject();
		JSONObject userjson = new JSONObject();
		JSONObject ratejson = new JSONObject();
		Object [] value = {regionId,date};
		String sql_prop = "select a.ADD_PROD_CNT,b.DE_A_SC from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+ 
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID "+
				"WHERE a.REGION_ID = ? and REGION_COUNTRY = 'ALL' AND a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID  = 'all_cnt' and a.CPC_ID = 'prop_cnt' and a.STATIS_DATE=?";
		List<Map<String, Object>> list_prop = this.getJdbcTemplate().queryForList(sql_prop,value);
		if(list_prop.size()!=0){
			propjson.put("num", list_prop.get(0).get("ADD_PROD_CNT"));
			String scstr = list_prop.get(0).get("DE_A_SC")==null? "" : list_prop.get(0).get("DE_A_SC").toString();
			if("1".equals(scstr)){
				propjson.put("sc", "up");
			}else if("0".equals(scstr)){
				propjson.put("sc", "down");
			}else{
				propjson.put("sc", "--");
			}
		}
		String sql_user = "select a.ADD_PROD_CNT,b.DE_A_SC from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+ 
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID "+
				"WHERE a.REGION_ID = ? and REGION_COUNTRY = 'ALL' AND a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID  = 'all_cnt' and a.CPC_ID = 'user_cnt' and a.STATIS_DATE=?";
		List<Map<String, Object>> list_user = this.getJdbcTemplate().queryForList(sql_user,value);
		if(list_user.size()!=0){
			userjson.put("num", list_user.get(0).get("ADD_PROD_CNT"));
			String scstr = list_user.get(0).get("DE_A_SC")==null? "" : list_user.get(0).get("DE_A_SC").toString();
			if("1".equals(scstr)){
				userjson.put("sc", "up");
			}else if("0".equals(scstr)){
				userjson.put("sc", "down");
			}else{
				userjson.put("sc", "--");
			}
		}
		String sql_rate="select (a.ADD_PROD_PROP*100) as ADD_PROD_PROP,b.DE_A_SC from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE  "+
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID  "+
				"WHERE a.REGION_ID = ? and REGION_COUNTRY = 'ALL' AND a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID  = 'all_cnt'  "+
				"and a.CPC_ID = 'import_cnt' and a.STATIS_DATE=?";
		List<Map<String, Object>> list_rate = this.getJdbcTemplate().queryForList(sql_rate,value);
		if(list_rate.size()!=0){
			ratejson.put("num", list_rate.get(0).get("ADD_PROD_PROP"));
			String scstr = list_rate.get(0).get("DE_A_SC")==null? "" : list_rate.get(0).get("DE_A_SC").toString();
			if("1".equals(scstr)){
				ratejson.put("sc", "up");
			}else if("0".equals(scstr)){
				ratejson.put("sc", "down");
			}else{
				ratejson.put("sc", "--");
			}
		}
		result.put("prop", propjson);
		result.put("user", userjson);
		result.put("rate", ratejson);
		return result;
	}

	public JSONObject query4GBusinessData(String date,String regionId) {
		JSONObject result = new JSONObject();
		JSONObject chartjson = new JSONObject();
		JSONObject regiondata = new JSONObject();
		JSONObject channeldata = new JSONObject();
		JSONObject kinddata = new JSONObject();
		double busrate = 0;
		long buscnt = 0;
		String sc="--";
		double scnum=0;
		Object [] value = {date};
		String sql1 = "select a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP,b.DE_A_SC,b.SAME_RATE from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID and b.REGION_ID = '"+regionId+"' "+
				"where a.REGION_ID = '"+regionId+"' and REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE = 'ALL' "+
				"and a.PARENT_CPC_ID = '4G' and a.CPC_ID = 'ALL' and a.STATIS_DATE = ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1, value);
		if(list1.size()!=0){
			buscnt = list1.get(0).get("ADD_PROD_CNT")==null?0:Long.parseLong(list1.get(0).get("ADD_PROD_CNT").toString());
			busrate = list1.get(0).get("ADD_PROD_PROP")==null?0:Double.parseDouble(list1.get(0).get("ADD_PROD_PROP").toString());
			BigDecimal bg = new BigDecimal(busrate);
			busrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String scstr = list1.get(0).get("DE_A_SC")==null? "" : list1.get(0).get("DE_A_SC").toString();
			scnum = list1.get(0).get("SAME_RATE")==null? 0 : Double.valueOf(list1.get(0).get("SAME_RATE").toString());
			if("1".equals(scstr)){	
				sc="up";
			}else if("0".equals(scstr)){
				sc="down";
			}
		}
		String sql_region = "";
		if("1".equals(regionId)){
			sql_region = "select * from (select a.REGION_NAME,b.ADD_PROD_CNT,(b.ADD_PROD_PROP*100) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW b on a.REGION_ID = b.REGION_ID  "+
					"where b.REGION_ID != '1' and b.REGION_COUNTRY = 'ALL' and b.TRANSACT_TYPE = 'ALL' AND B.PARENT_CPC_ID = '4G' AND b.CPC_ID = 'ALL' "+
					"and b.STATIS_DATE ='"+date+"' order by b.ADD_PROD_CNT desc fetch first 6 row only) order by ADD_PROD_CNT asc";
		}else{
			sql_region = "select * from (select b.REGION_NAME,a.REGION_COUNTRY,a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a  "+
					"left join  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on REGION_COUNTRY = b.REGION_ID "+
					"where a.REGION_ID = '"+regionId+"' and a.REGION_COUNTRY != 'ALL' and a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID = '4G' AND a.CPC_ID = 'ALL'  "+
					"and a.STATIS_DATE = '"+date+"' order by a.ADD_PROD_CNT desc fetch first 6 row only ) order by ADD_PROD_CNT asc";
		}
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql_region);
		if(list2.size()!=0){
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			for (int i = 0; i < list2.size(); i++) {
				datax.add(list2.get(i).get("REGION_NAME"));
				cnt.add(list2.get(i).get("ADD_PROD_CNT"));
				rate.add(list2.get(i).get("ADD_PROD_PROP"));
			}
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}
		String sql_channel = "select * from (select b.ACC_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_CHNL_ACC_TYPE_INFO b on a.TRANSACT_TYPE = b.ACC_TYPE "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE !='ALL' and  a.PARENT_CPC_ID = '4G' AND a.CPC_ID = 'ALL' and a.ADD_PROD_CNT !=0 "+
				"and a.STATIS_DATE = ? order by a.ADD_PROD_CNT desc fetch first 10 row only) order by ADD_PROD_CNT asc"; 
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql_channel,value);
		if(list3.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list3.size(); i++) {
				datax.add(list3.get(i).get("ACC_NAME"));
				datay.add(list3.get(i).get("ADD_PROD_CNT"));
			}
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}
		String sql_kind = "select * from( select b.ZR_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO b on a.CPC_ID = b.ZR_ID "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE ='ALL' and  a.PARENT_CPC_ID = '4G' AND a.CPC_ID != 'ALL' and a.ADD_PROD_CNT !=0 "+
				" and a.cpc_id!='user_cnt' and a.STATIS_DATE =? order by a.ADD_PROD_CNT desc fetch first 10 row only ) order by ADD_PROD_CNT asc"; 
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql_kind,value);
		if(list4.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list4.size(); i++) {
				datax.add(list4.get(i).get("ZR_NAME"));
				datay.add(list4.get(i).get("ADD_PROD_CNT"));
			}
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}
		chartjson.put("regiondata", regiondata);
		chartjson.put("channeldata", channeldata);
		chartjson.put("kinddata", kinddata);
		result.put("busrate",busrate);
		result.put("buscnt",buscnt);
		result.put("sc",sc);
		result.put("scnum",scnum);
		result.put("chartdata",chartjson);
		return result;
	}

	public JSONObject queryKDBusinessData(String date,String regionId) {
		JSONObject result = new JSONObject();
		JSONObject chartjson = new JSONObject();
		JSONObject regiondata = new JSONObject();
		JSONObject channeldata = new JSONObject();
		JSONObject kinddata = new JSONObject();
		double busrate = 0;
		long buscnt = 0;
		String sc="--";
		double scnum=0;
		Object [] value = {date};
		String sql1 = "select a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP,b.DE_A_SC,b.SAME_RATE from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID and b.REGION_ID = '"+regionId+"' "+
				"where a.REGION_ID = '"+regionId+"' and REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE = 'ALL' "+
				"and a.PARENT_CPC_ID = 'kd' and a.CPC_ID = 'ALL' and a.STATIS_DATE = ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1, value);
		if(list1.size()!=0){
			buscnt = list1.get(0).get("ADD_PROD_CNT")==null?0:Long.parseLong(list1.get(0).get("ADD_PROD_CNT").toString());
			busrate = list1.get(0).get("ADD_PROD_PROP")==null?0:Double.parseDouble(list1.get(0).get("ADD_PROD_PROP").toString());
			BigDecimal bg = new BigDecimal(busrate);
			busrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String scstr = list1.get(0).get("DE_A_SC")==null? "" : list1.get(0).get("DE_A_SC").toString();
			scnum = list1.get(0).get("SAME_RATE")==null? 0 : Double.valueOf(list1.get(0).get("SAME_RATE").toString());
			if("1".equals(scstr)){
				sc="up";
			}else if("0".equals(scstr)){
				sc="down";
			}
		}
		String sql_region = "";
		if("1".equals(regionId)){
			sql_region = "select * from(select a.REGION_NAME,b.ADD_PROD_CNT,(b.ADD_PROD_PROP*100) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW b on a.REGION_ID = b.REGION_ID  "+
					"where b.REGION_ID != '1' and b.REGION_COUNTRY = 'ALL' and b.TRANSACT_TYPE = 'ALL' AND B.PARENT_CPC_ID = 'kd' AND b.CPC_ID = 'ALL' "+
					"and b.STATIS_DATE ='"+date+"' order by b.ADD_PROD_CNT desc fetch first 6 row only) order by ADD_PROD_CNT asc";
		}else{
			sql_region = "select * from(select b.REGION_NAME,a.REGION_COUNTRY,a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a  "+
					"left join  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on REGION_COUNTRY = b.REGION_ID "+
					"where a.REGION_ID = '"+regionId+"' and a.REGION_COUNTRY != 'ALL' and a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID = 'kd' AND a.CPC_ID = 'ALL'  "+
					"and a.STATIS_DATE = '"+date+"' order by a.ADD_PROD_CNT desc fetch first 6 row only) order by ADD_PROD_CNT asc";
		}
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql_region);
		if(list2.size()!=0){
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			for (int i = 0; i < list2.size(); i++) {
				datax.add(list2.get(i).get("REGION_NAME"));
				cnt.add(list2.get(i).get("ADD_PROD_CNT"));
				rate.add(list2.get(i).get("ADD_PROD_PROP"));
			}
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}
		String sql_channel = "select * from (select b.ACC_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_CHNL_ACC_TYPE_INFO b on a.TRANSACT_TYPE = b.ACC_TYPE "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE !='ALL' and  a.PARENT_CPC_ID = 'kd' AND a.CPC_ID = 'ALL' and a.ADD_PROD_CNT !=0 "+
				"and a.STATIS_DATE = ? order by a.ADD_PROD_CNT desc fetch first 10 row only )order by ADD_PROD_CNT asc "; 
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql_channel,value);
		if(list3.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list3.size(); i++) {
				datax.add(list3.get(i).get("ACC_NAME"));
				datay.add(list3.get(i).get("ADD_PROD_CNT"));
			}
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}
		String sql_kind = " select * from ("+
				"select b.GROUP_NAME,sum(a.ADD_PROD_CNT) as ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a  "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO b on a.CPC_ID = b.ZR_ID "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE ='ALL' and  a.PARENT_CPC_ID = 'kd' AND a.CPC_ID != 'ALL' "+
				" and a.cpc_id!='user_cnt' and a.STATIS_DATE =? group by b.GROUP_NAME order by sum(a.ADD_PROD_CNT) desc fetch first 10 row only ) order by ADD_PROD_CNT asc ";				
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql_kind,value);
		if(list4.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list4.size(); i++) {
				datax.add(list4.get(i).get("GROUP_NAME"));
				datay.add(list4.get(i).get("ADD_PROD_CNT"));
			}
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}
		chartjson.put("regiondata", regiondata);
		chartjson.put("channeldata", channeldata);
		chartjson.put("kinddata", kinddata);
		result.put("busrate",busrate);
		result.put("buscnt",buscnt);
		result.put("sc",sc);
		result.put("scnum",scnum);
		result.put("chartdata",chartjson);
		return result;
	}

	public JSONObject queryFlowBusinessData(String date,String regionId) {
		JSONObject result = new JSONObject();
		JSONObject chartjson = new JSONObject();
		JSONObject regiondata = new JSONObject();
		JSONObject channeldata = new JSONObject();
		JSONObject kinddata = new JSONObject();
		double busrate = 0;
		long buscnt = 0;
		String sc="--";
		double scnum=0;
		Object [] value = {date};
		String sql1 = "select a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP,b.DE_A_SC,b.SAME_RATE from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID and b.REGION_ID = '"+regionId+"' "+
				"where a.REGION_ID = '"+regionId+"' and REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE = 'ALL' "+
				"and a.PARENT_CPC_ID = 'flux' and a.CPC_ID = 'ALL' and a.STATIS_DATE = ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1, value);
		if(list1.size()!=0){
			buscnt = list1.get(0).get("ADD_PROD_CNT")==null?0:Long.parseLong(list1.get(0).get("ADD_PROD_CNT").toString());
			busrate = list1.get(0).get("ADD_PROD_PROP")==null?0:Double.parseDouble(list1.get(0).get("ADD_PROD_PROP").toString());
			BigDecimal bg = new BigDecimal(busrate);
			busrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String scstr = list1.get(0).get("DE_A_SC")==null? "" : list1.get(0).get("DE_A_SC").toString();
			scnum = list1.get(0).get("SAME_RATE")==null? 0 : Double.valueOf(list1.get(0).get("SAME_RATE").toString());
			if("1".equals(scstr)){
				sc="up";
			}else if("0".equals(scstr)){
				sc="down";
			}
		}
		String sql_region = "";
		if("1".equals(regionId)){
			sql_region = "select * from (select a.REGION_NAME,b.ADD_PROD_CNT,(b.ADD_PROD_PROP*100) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW b on a.REGION_ID = b.REGION_ID  "+
					"where b.REGION_ID != '1' and b.REGION_COUNTRY = 'ALL' and b.TRANSACT_TYPE = 'ALL' AND B.PARENT_CPC_ID = 'flux' AND b.CPC_ID = 'ALL' "+
					"and b.STATIS_DATE ='"+date+"' order by b.ADD_PROD_CNT desc fetch first 6 row only ) order by ADD_PROD_CNT asc";
		}else{
			sql_region = "select * from ( select b.REGION_NAME,a.REGION_COUNTRY,a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a  "+
					"left join  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on REGION_COUNTRY = b.REGION_ID "+
					"where a.REGION_ID = '"+regionId+"' and a.REGION_COUNTRY != 'ALL' and a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID = 'flux' AND a.CPC_ID = 'ALL'  "+
					"and a.STATIS_DATE = '"+date+"' order by a.ADD_PROD_CNT desc fetch first 6 row only ) order by ADD_PROD_CNT asc";
		}
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql_region);
		if(list2.size()!=0){
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			for (int i = 0; i < list2.size(); i++) {
				datax.add(list2.get(i).get("REGION_NAME"));
				cnt.add(list2.get(i).get("ADD_PROD_CNT"));
				rate.add(list2.get(i).get("ADD_PROD_PROP"));
			}
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}
		String sql_channel = "select * from (select b.ACC_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_CHNL_ACC_TYPE_INFO b on a.TRANSACT_TYPE = b.ACC_TYPE "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE !='ALL' and  a.PARENT_CPC_ID = 'flux' AND a.CPC_ID = 'ALL' and a.ADD_PROD_CNT !=0 "+
				"and a.STATIS_DATE = ? order by a.ADD_PROD_CNT desc fetch first 10 row only ) order by ADD_PROD_CNT asc "; 
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql_channel,value);
		if(list3.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list3.size(); i++) {
				datax.add(list3.get(i).get("ACC_NAME"));
				datay.add(list3.get(i).get("ADD_PROD_CNT"));
			}
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}
		String sql_kind = "select * from (select b.ZR_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO b on a.CPC_ID = b.ZR_ID "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE ='ALL' and  a.PARENT_CPC_ID = 'flux' AND a.CPC_ID != 'ALL' and a.ADD_PROD_CNT !=0 "+
				" and a.cpc_id!='user_cnt' and a.STATIS_DATE =? order by a.ADD_PROD_CNT desc fetch first 10 row only ) order by ADD_PROD_CNT asc "; 
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql_kind,value);
		if(list4.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list4.size(); i++) {
				datax.add(list4.get(i).get("ZR_NAME"));
				datay.add(list4.get(i).get("ADD_PROD_CNT"));
			}
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}
		chartjson.put("regiondata", regiondata);
		chartjson.put("channeldata", channeldata);
		chartjson.put("kinddata", kinddata);
		result.put("busrate",busrate);
		result.put("buscnt",buscnt);
		result.put("sc",sc);
		result.put("scnum",scnum);
		result.put("chartdata",chartjson);
		return result;
	}

	public JSONObject queryOtherBusinessData(String date,String regionId) {
		JSONObject result = new JSONObject();
		JSONObject chartjson = new JSONObject();
		JSONObject regiondata = new JSONObject();
		JSONObject channeldata = new JSONObject();
		JSONObject kinddata = new JSONObject();
		double busrate = 0;
		long buscnt = 0;
		String sc = "--";
		double scnum=0;
		Object [] value = {date};
		String sql1 = "select a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP,b.DE_A_SC,b.SAME_RATE from  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b on a.STATIS_DATE = b.STATIS_DATE "+
				"and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID and b.REGION_ID = '"+regionId+"'  "+
				"where a.REGION_ID = '"+regionId+"' and REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE = 'ALL' "+
				"and a.PARENT_CPC_ID = 'other' and a.CPC_ID = 'ALL' and a.STATIS_DATE = ? ";
		List<Map<String, Object>> list1 = this.getJdbcTemplate().queryForList(sql1, value);
		if(list1.size()!=0){
			buscnt = list1.get(0).get("ADD_PROD_CNT")==null?0:Long.parseLong(list1.get(0).get("ADD_PROD_CNT").toString());
			busrate = list1.get(0).get("ADD_PROD_PROP")==null?0:Double.parseDouble(list1.get(0).get("ADD_PROD_PROP").toString());
			BigDecimal bg = new BigDecimal(busrate);
			busrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String scstr = list1.get(0).get("DE_A_SC")==null? "" : list1.get(0).get("DE_A_SC").toString();
			scnum = list1.get(0).get("SAME_RATE")==null? 0 : Double.valueOf(list1.get(0).get("SAME_RATE").toString());
			if("1".equals(scstr)){
				sc="up";
			}else if("0".equals(scstr)){
				sc="down";
			}
		}
		String sql_region = "";
		if("1".equals(regionId)){
			sql_region = "select * from (select a.REGION_NAME,b.ADD_PROD_CNT,(b.ADD_PROD_PROP*100) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW b on a.REGION_ID = b.REGION_ID  "+
					"where b.REGION_ID != '1' and b.REGION_COUNTRY = 'ALL' and b.TRANSACT_TYPE = 'ALL' AND B.PARENT_CPC_ID = 'other' AND b.CPC_ID = 'ALL' "+
					"and b.STATIS_DATE ='"+date+"' order by b.ADD_PROD_CNT desc fetch first 6 row only ) order by ADD_PROD_CNT asc";
		}else{
			sql_region = "select * from (select b.REGION_NAME,a.REGION_COUNTRY,a.ADD_PROD_CNT,(a.ADD_PROD_PROP*100) as ADD_PROD_PROP "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a  "+
					"left join  BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on REGION_COUNTRY = b.REGION_ID "+
					"where a.REGION_ID = '"+regionId+"' and a.REGION_COUNTRY != 'ALL' and a.TRANSACT_TYPE = 'ALL' AND a.PARENT_CPC_ID = 'other' AND a.CPC_ID = 'ALL'  "+
					"and a.STATIS_DATE = '"+date+"' order by a.ADD_PROD_CNT desc fetch first 6 row only ) order by ADD_PROD_CNT asc";
		}
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql_region);
		if(list2.size()!=0){
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			for (int i = 0; i < list2.size(); i++) {
				datax.add(list2.get(i).get("REGION_NAME"));
				cnt.add(list2.get(i).get("ADD_PROD_CNT"));
				rate.add(list2.get(i).get("ADD_PROD_PROP"));
			}
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray rate = new JSONArray();
			JSONArray cnt = new JSONArray();
			datay.put("cnt", cnt);
			datay.put("rate", rate);
			regiondata.put("datax", datax);
			regiondata.put("datay", datay);
		}
		String sql_channel = "select * from (select b.ACC_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_CHNL_ACC_TYPE_INFO b on a.TRANSACT_TYPE = b.ACC_TYPE "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE !='ALL' and  a.PARENT_CPC_ID = 'other' AND a.CPC_ID = 'ALL' and a.ADD_PROD_CNT !=0  "+
				"and a.STATIS_DATE = ? order by a.ADD_PROD_CNT desc fetch first 10 row only ) order by ADD_PROD_CNT asc "; 
		List<Map<String, Object>> list3 = this.getJdbcTemplate().queryForList(sql_channel,value);
		if(list3.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list3.size(); i++) {
				datax.add(list3.get(i).get("ACC_NAME"));
				datay.add(list3.get(i).get("ADD_PROD_CNT"));
			}
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			channeldata.put("datax", datax);
			channeldata.put("datay", datay);
		}
		String sql_kind = "select * from (select b.ZR_NAME,a.ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ZR_ALL_INFO b on a.CPC_ID = b.ZR_ID "+
				"where a.REGION_ID = '"+regionId+"'  and a.REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE ='ALL' and  a.PARENT_CPC_ID = 'other' AND a.CPC_ID != 'ALL' and a.ADD_PROD_CNT !=0 "+
				" and a.cpc_id!='user_cnt' and a.STATIS_DATE =? order by a.ADD_PROD_CNT desc fetch first 10 row only ) order by ADD_PROD_CNT asc"; 
		List<Map<String, Object>> list4 = this.getJdbcTemplate().queryForList(sql_kind,value);
		if(list4.size()!=0){
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			for (int i = 0; i < list4.size(); i++) {
				datax.add(list4.get(i).get("ZR_NAME"));
				datay.add(list4.get(i).get("ADD_PROD_CNT"));
			}
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}else{
			JSONArray datax = new JSONArray();
			JSONArray datay = new JSONArray();
			kinddata.put("datax", datax);
			kinddata.put("datay", datay);
		}
		chartjson.put("regiondata", regiondata);
		chartjson.put("channeldata", channeldata);
		chartjson.put("kinddata", kinddata);
		result.put("busrate",busrate);
		result.put("buscnt",buscnt);
		result.put("sc",sc);
		result.put("scnum",scnum);
		result.put("chartdata",chartjson);
		return result;
	}

	public JSONArray getAllHandlingNum(String regionId,String maxdate) {
		JSONArray result=new JSONArray();
		String [] productType={"4G","kd","flux","other"};
		String sql=" select aa.abc,a.STATIS_DATE,a.ADD_PROD_CNT,b.SAME_RATE "+ 
				" from (select substr(statis_date,1,6) as abc,max(statis_date) statis_date from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW where substr(statis_date,1,6)!= ? "+
				" group by substr(statis_date,1,6)) aa "+
				" inner join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO_NEW a "+
				" on aa.statis_date=a.statis_date "+
				" left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_FOUR_INFO b  "+
				" on a.STATIS_DATE = b.STATIS_DATE and a.PARENT_CPC_ID = b.PARENT_CPC_ID and a.CPC_ID = b.CPC_ID and b.REGION_ID =? "+ 
				" where a.REGION_ID = ? and REGION_COUNTRY = 'ALL' and a.TRANSACT_TYPE = 'ALL' and a.PARENT_CPC_ID = ? and a.CPC_ID = 'ALL' order by aa.abc asc ";
		for (int i = 0; i < productType.length; i++) {
			Object [] value={maxdate.substring(0,6),regionId,regionId,productType[i]};
			List<Map<String,Object>> list=this.getJdbcTemplate().queryForList(sql,value);
			JSONObject oneTypeData=new JSONObject();
			JSONArray datayNum=new JSONArray();
			JSONArray datayRate=new JSONArray();
			JSONArray dataxName=new JSONArray();
			if(list.size()!=0){
				for (int j = 0; j < list.size(); j++) {
					Map<String, Object> map=list.get(j);
					datayNum.add(map.get("ADD_PROD_CNT")==null?0:Long.parseLong(map.get("ADD_PROD_CNT").toString()));
					datayRate.add(map.get("SAME_RATE")==null?0:Double.parseDouble(map.get("SAME_RATE").toString()));
					dataxName.add(map.get("abc"));
				}
			}
			oneTypeData.put("datayNum", datayNum);
			oneTypeData.put("datayRate", datayRate);
			oneTypeData.put("dataxName", dataxName);
			result.add(oneTypeData);
		}
		return result;
	}

	
	
}
