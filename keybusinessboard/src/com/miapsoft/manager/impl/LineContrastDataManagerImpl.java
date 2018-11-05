package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.LineContrastDataManager;

/**
 * <p>Title: KeyBusinessDataManagerImpl.java</p>
 * <p>Description: </p>
 * @author: 白少华
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Service("contrastDataManager")
public class LineContrastDataManagerImpl extends AbstractManager implements LineContrastDataManager {

	public JSONArray getLineKeyBusinessData(String date) {
		JSONArray result = new JSONArray();
		JSONObject resultobj = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
		String sql = "select a.CPC_ID,b.CPC_NAME,a.\"Online_cont\" as ONLINE_COUNT,a.\"Offline_cont\" as OUTLINE_COUNT "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO a "+
					"LEFT JOIN BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO b on a.CPC_ID= b.CPC_ID "+
					"WHERE a.CPC_ID in ('4g_fx','broadband_bns','other_bns','flux_bag') and a.STATIS_DATE =? order by REMARK";
		Object [] value  = {date};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			JSONArray datax = new JSONArray();
			JSONObject datay = new JSONObject();
			JSONArray online = new JSONArray();
			JSONArray outline = new JSONArray();
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				datax.add(map.get("CPC_NAME"));
				online.add(df.format(Double.valueOf(map.get("ONLINE_COUNT").toString())*100));
				outline.add(df.format(Double.valueOf(map.get("OUTLINE_COUNT").toString())*100));
			}
			datay.put("online", online);
			datay.put("outline", outline);
			resultobj.put("datax", datax);
			resultobj.put("datay", datay);
			result.add(resultobj);
		}
		return result;
	}

	public static void main(String[] args) {
		BigDecimal bg = new BigDecimal(0.3656);
		double f = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f);
	}
	
	public JSONObject getBusinessHandelData(String date) {
		JSONObject result = new JSONObject();
		String sql = "select CPC_ID,REMARK from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO WHERE STATIS_DATE=? and REGION_ID='1' and PARENT_CPC_ID='all_cnt'";
		Object [] value  = {date};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				if(map.get("CPC_ID")!=null){
					if("prop_cnt".equals(map.get("CPC_ID").toString().toLowerCase())){
						result.put("prop", map.get("REMARK"));
					}
					if("user_cnt".equals(map.get("CPC_ID").toString().toLowerCase())){
						result.put("user", map.get("REMARK"));
					}
				}else{
					result.put("prop", 0);
					result.put("user", 0);
				}
			}
		}else{
			result.put("prop", 0);
			result.put("user", 0);
		}
		return result;
	}

	public JSONArray getKuanDaiData(String date,String bustype) {
		JSONArray result = new JSONArray();
		String sql = "select b.REGION_NAME,round(a.\"Online_cont\",4) as ONLINE_COUNT,round(a.\"Offline_cont\",4) as OUTLINE_COUNT "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID "+
					"WHERE a.CPC_ID=? AND a.STATIS_DATE = ? order by b.ORDER_ID";
		Object [] value  = {bustype,date};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				JSONObject obj = new JSONObject();
				obj.put("regionId", TransforRegionName(map.get("REGION_NAME").toString()));
				obj.put("onlineData", map.get("ONLINE_COUNT"));
				obj.put("outlineData", map.get("OUTLINE_COUNT"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONArray getFluxData(String date,String bustype) {
		JSONArray result = new JSONArray();
		String sql = "select b.REGION_NAME,round(a.\"Online_cont\",4) as ONLINE_COUNT,round(a.\"Offline_cont\",4) as OUTLINE_COUNT "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID "+
					"WHERE a.CPC_ID=? AND a.STATIS_DATE = ? order by b.ORDER_ID";
		Object [] value  = {bustype,date};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				JSONObject obj = new JSONObject();
				obj.put("regionId", TransforRegionName(map.get("REGION_NAME").toString()));
				obj.put("onlineData", map.get("ONLINE_COUNT"));
				obj.put("outlineData", map.get("OUTLINE_COUNT"));
				result.add(obj);
			}
		}
		return result;
	}

	public JSONObject getPackBusinessHandelData(String date) {
		DecimalFormat df = new DecimalFormat("#.00");
		JSONObject result  = new JSONObject();
		String sql = "select CPC_ID from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO where CPC_NAME like '%4G飞享套餐%'";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql);
		String sql_data = "select b.REGION_NAME,a.CPC_ID,a.\"Online_cont\"  as ONLINE_COUNT,a.\"Offline_cont\" as OUTLINE_COUNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO a "+
						"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID WHERE a.CPC_ID = ? "+
						"and a.STATIS_DATE=? and a.REGION_ID !='1' order by (ONLINE_COUNT+OUTLINE_COUNT) desc";
		String sql_rate="select round(REMARK,4) as REMARK from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO where REGION_ID = '1' and CPC_ID=? and STATIS_DATE=?";
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				String cpcid=map.get("CPC_ID").toString();
				Object [] value= {cpcid,date};
				JSONObject obj = new JSONObject();
				JSONArray datax = new JSONArray();
				JSONObject datay = new JSONObject();
				JSONArray online = new JSONArray();
				JSONArray outline = new JSONArray();
				String rate = "0";
				Object [] value_rate={cpcid,date};
				List<Map<String, Object>> listrate = this.getJdbcTemplate().queryForList(sql_rate, value_rate);
				if(listrate.size()!=0){
					if(listrate.get(0).get("REMARK")==null){
						rate = "0";
					}else{
						rate = listrate.get(0).get("REMARK").toString();
					}
				}
				List<Map<String, Object>> list = this. getJdbcTemplate().queryForList(sql_data,value);
				if(list.size()!=0){
					for (int j = 0; j < list.size(); j++) {
						Map<String, Object> eachmap = list.get(j);
						datax.add(eachmap.get("REGION_NAME"));
						online.add(df.format(Double.valueOf(eachmap.get("ONLINE_COUNT").toString())*100));
						outline.add(df.format(Double.valueOf(eachmap.get("OUTLINE_COUNT").toString())*100));
					}
				}
				obj.put("datax",datax);
				datay.put("online", online);
				datay.put("outline", outline);
				obj.put("datay",datay);
				BigDecimal bg = new BigDecimal(Double.valueOf(rate)*100);
				double rateD = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				if("pri_gl.g.4G.fxtc6y_new".equals(cpcid)){
					result.put("fxtc6y", obj);
					result.put("fxtc6yrate", rateD);
				}else if("pri_gl.g.4G.fxtc8y_new".equals(cpcid)){
					result.put("fxtc8y", obj);
					result.put("fxtc8yrate", rateD);
				}else if("pri_gl.g.4G.fxtc15y_new".equals(cpcid)){
					result.put("fxtc15y", obj);
					result.put("fxtc15yrate", rateD);
				}else if("pri_gl.g.4G.fxtc18y_new".equals(cpcid)){
					result.put("fxtc18y", obj);
					result.put("fxtc18yrate", rateD);
				}else if("pri_gl.g.4G.fxtc28y_new".equals(cpcid)){
					result.put("fxtc28y", obj);
					result.put("fxtc28yrate", rateD);
				}else if("pri_gl.g.4G.fxtc38y_new".equals(cpcid)){
					result.put("fxtc38y", obj);
					result.put("fxtc38yrate", rateD);
				}else if("pri_gl.g.4G.fxtc45y_new".equals(cpcid)){
					result.put("fxtc45y", obj);
					result.put("fxtc45yrate", rateD);
				}else if("pri_gl.g.4G.fxtc58y_new".equals(cpcid)){
					result.put("fxtc58y", obj);
					result.put("fxtc58yrate", rateD);
				}else if("pri_gl.g.4G.fxtc65y_new".equals(cpcid)){
					result.put("fxtc65y", obj);
					result.put("fxtc65yrate", rateD);
				}else if("pri_gl.g.4G.fxtc88y_new".equals(cpcid)){
					result.put("fxtc88y", obj);
					result.put("fxtc88yrate", rateD);
				}else if("pri_gl.g.4G.fxtc95y_new".equals(cpcid)){
					result.put("fxtc95y", obj);
					result.put("fxtc95yrate", rateD);
				}else if("pri_gl.g.4G.fxtc138y_new".equals(cpcid)){
					result.put("fxtc138y", obj);
					result.put("fxtc138yrate", rateD);
				}
			}
		}
		return result;
	}

	public JSONObject getOtherBusinessHandelData(String date,String bustype) {
		DecimalFormat df = new DecimalFormat("#.00");
		JSONObject result = new JSONObject();
		String sql = "select b.REGION_NAME,round(a.\"Online_cont\",4) as ONLINE_COUNT,round(a.\"Offline_cont\",4) as OUTLINE_COUNT "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ONOROFF_INFO a "+
					"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID "+
					"WHERE a.CPC_ID=? AND a.STATIS_DATE = ? order by b.ORDER_ID";
		Object [] value  = {bustype,date};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		JSONArray datax = new JSONArray();
		JSONObject datay = new JSONObject();
		JSONArray online = new JSONArray();
		JSONArray outline = new JSONArray();
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				datax.add(map.get("REGION_NAME"));
				String onlinestr = df.format(Double.valueOf(map.get("ONLINE_COUNT").toString())*100);
				String outlinestr = df.format(Double.valueOf(map.get("OUTLINE_COUNT").toString())*100);
				online.add(Double.valueOf(onlinestr));
				outline.add(Double.valueOf(outlinestr));
			}
		}
		result.put("datax",datax);
		datay.put("onlinedata", online);
		datay.put("outlinedata", outline);
		result.put("datay",datay);
		return result;
	}
	private String TransforRegionName(String regionName){
		if(regionName.startsWith("石家庄")){
			return "SJZ";
		}else if(regionName.startsWith("保定")){
			return "BD";
		}else if(regionName.startsWith("张家口")){
			return "ZJK";
		}else if(regionName.startsWith("承德")){
			return "CD";
		}else if(regionName.startsWith("唐山")){
			return "TS";
		}else if(regionName.startsWith("廊坊")){
			return "LF";
		}else if(regionName.startsWith("沧州")){
			return "CZ";
		}else if(regionName.startsWith("衡水")){
			return "HS";
		}else if(regionName.startsWith("邢台")){
			return "XT";
		}else if(regionName.startsWith("邯郸")){
			return "HD";
		}else if(regionName.startsWith("秦皇岛")){
			return "QHD";
		}else{
			return "";
		}
	}
}
