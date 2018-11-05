package com.miapsoft.manager.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.OnLineManager;
@Service
public class OnLineManagerImpl extends AbstractManager implements OnLineManager {
	
   //宽带业务的数据
	public JSONArray broadbanddata(String onlineoroutline, String curdate,
			String brandtype) {
		JSONArray result=new JSONArray();
		JSONObject obj=new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
		String sql="select b.REGION_NAME,round(a.ADD_PROD_PROP,4) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a " +
				   "left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID=b.REGION_ID " +
				   "where a.PARENT_CPC_ID='broadband_bns' and a.STATIS_DATE=? and a.TRANSACT_TYPE=? and CPC_ID=? order by b.ORDER_ID";
		Object[] value={curdate,onlineoroutline,brandtype};
		List<Map<String,Object>> list=this.getJdbcTemplate().queryForList(sql, value);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				if(map.get("REGION_NAME").equals("石家庄")){
					obj.put("regionId", "SJZ");
				}else if(map.get("REGION_NAME").equals("秦皇岛")){
					obj.put("regionId", "QHD");
				}
				else if(map.get("REGION_NAME").equals("张家口")){
					obj.put("regionId", "ZJK");
				}
				else if(map.get("REGION_NAME").equals("廊坊")){
					obj.put("regionId", "LF");
				}
				else if(map.get("REGION_NAME").equals("唐山")){
					obj.put("regionId", "TS");
				}
				else if(map.get("REGION_NAME").equals("衡水")){
					obj.put("regionId", "HS");
				}
				else if(map.get("REGION_NAME").equals("保定")){
					obj.put("regionId", "BD");
				}
				else if(map.get("REGION_NAME").equals("承德")){
					obj.put("regionId", "CD");
				}
				else if(map.get("REGION_NAME").equals("邢台")){
					obj.put("regionId", "XT");
				}
				else if(map.get("REGION_NAME").equals("邯郸")){
					obj.put("regionId", "HD");
				}
				else if(map.get("REGION_NAME").equals("沧州")){
					obj.put("regionId", "CZ");
				}
				BigDecimal bg = new BigDecimal(Double.valueOf(map.get("ADD_PROD_PROP").toString()));
				double rateD = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj.put("onlineData", rateD);
				result.add(obj);
			}
			
		}else{
			String sql2="select REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID ";
			List<Map<String,Object>> list1=this.getJdbcTemplate().queryForList(sql2);
			for(int i=0;i<list1.size();i++){
					Map<String,Object> map=list1.get(i);
					if(map.get("REGION_NAME").equals("石家庄")){
						obj.put("regionId", "SJZ");
					}else if(map.get("REGION_NAME").equals("秦皇岛")){
						obj.put("regionId", "QHD");
					}
					else if(map.get("REGION_NAME").equals("张家口")){
						obj.put("regionId", "ZJK");
					}
					else if(map.get("REGION_NAME").equals("廊坊")){
						obj.put("regionId", "LF");
					}
					else if(map.get("REGION_NAME").equals("唐山")){
						obj.put("regionId", "TS");
					}
					else if(map.get("REGION_NAME").equals("衡水")){
						obj.put("regionId", "HS");
					}
					else if(map.get("REGION_NAME").equals("保定")){
						obj.put("regionId", "BD");
					}
					else if(map.get("REGION_NAME").equals("承德")){
						obj.put("regionId", "CD");
					}
					else if(map.get("REGION_NAME").equals("邢台")){
						obj.put("regionId", "XT");
					}
					else if(map.get("REGION_NAME").equals("邯郸")){
						obj.put("regionId", "HD");
					}
					else if(map.get("REGION_NAME").equals("沧州")){
						obj.put("regionId", "CZ");
					}
					obj.put("onlineData", 0);
					result.add(obj);
			}
		}
		return result;
	}
   //重点业务的办理
	public JSONArray keymanagement(String onlineoroutline, String curdate) {
		JSONArray result=new JSONArray();
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		JSONObject obj2=new JSONObject();
		JSONArray result1=new JSONArray();
		JSONObject allobject=new JSONObject();
		DecimalFormat df = new DecimalFormat("#.00");
		String sql="select a.*,b.CPC_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_RANKING_INFO a left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO b on  a.CPC_ID =b.CPC_ID and a.PARENT_CPC_ID=b.PARENT_CPC_ID where a.STATIS_DATE=? and a.TRANSACT_TYPE=? order by a.CPC_ID  ";
		Object[]value={curdate,onlineoroutline};
		List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql,value);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				BigDecimal bg = new BigDecimal(Double.valueOf(map.get("ADD_PROD_CNT").toString())*100);
				double rateD = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj2.put("value", rateD);
				obj2.put("name", map.get("CPC_NAME"));
				result1.add(obj2);
				obj1.put("one",map.get("ORDER_ID_1"));
				obj1.put("two", map.get("ORDER_ID_2"));
				obj1.put("three", map.get("ORDER_ID_3"));
				if(map.get("CPC_NAME").equals("4G飞享类")){
					obj.put("4G", obj1);
				}else if(map.get("CPC_NAME").equals("流量包类")){
					obj.put("流量包", obj1);
				}else if(map.get("CPC_NAME").equals("宽带业务类")){
					obj.put("宽带业务", obj1);
				}else if(map.get("CPC_NAME").equals("其他业务类")){
					obj.put("其他", obj1);
				}
				allobject.put("datax", result1)	;
				allobject.put("datay", obj);
			}
			result.add(allobject);
		}
		return result;
	}
	//业务办理量
	public JSONArray businessvloume(String onlineoroutline, String curdate) {
		JSONArray result=new JSONArray();
		JSONObject obj=new JSONObject();
		String sql="select CPC_ID,ADD_PROD_CNT from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO " +
				   "where  CPC_ID in('prop_cnt','user_cnt') and TRANSACT_TYPE=? and STATIS_DATE=? ";
		Object[]value={onlineoroutline,curdate};
		List<Map<String,Object>> list=this.getJdbcTemplate().queryForList(sql,value);
		if(list.size()>0){
			 for(int i=0;i<list.size();i++){
				  Map<String,Object> map =list.get(i);
				  if(map.get("CPC_ID").equals("prop_cnt")){
					   obj.put("prop", BigDecimal.valueOf(Double.valueOf(map.get("ADD_PROD_CNT").toString())));
				  }else{
					  obj.put("user", BigDecimal.valueOf(Double.valueOf(map.get("ADD_PROD_CNT").toString())));
				  }
			 }
			 
		}else{
			obj.put("prop", 0);
			obj.put("user", 0);
		}
		result.add(obj);
		return result;
	}
	//4G飞享类套餐
	public JSONObject flyclass(String onlineoroutline, String curdate) {
		DecimalFormat df = new DecimalFormat("#.00");
		JSONObject result  = new JSONObject();
		String sql = "select CPC_ID from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_QUOTA_INFO where CPC_NAME like '%4G飞享套餐%'";
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql);
		String sql_data = "select b.REGION_NAME,a.CPC_ID,round(a.ADD_PROD_PROP,4) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a "+
						"left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID = b.REGION_ID WHERE a.CPC_ID = ? "+
						"and a.TRANSACT_TYPE=? and a.STATIS_DATE=? and a.REGION_ID !='1' order by  ADD_PROD_PROP desc ";
		String sql_rate="select round(ADD_PROD_PROP,4) as REMARK from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO where TRANSACT_TYPE=? and REGION_ID = '1' and CPC_ID=? and STATIS_DATE=?";
		if(listdata.size()!=0){
			for (int i = 0; i < listdata.size(); i++) {
				Map<String, Object> map = listdata.get(i);
				String cpcid=map.get("CPC_ID").toString();
				Object [] value= {cpcid,onlineoroutline,curdate};
				JSONObject obj = new JSONObject();
				JSONArray datax = new JSONArray();
				JSONObject datay = new JSONObject();
				JSONArray online = new JSONArray();
				JSONArray outline = new JSONArray();
				String rate = "0";
				Object [] value_rate={onlineoroutline,cpcid,curdate};
				List<Map<String, Object>> listrate = this.getJdbcTemplate().queryForList(sql_rate, value_rate);
				if(listrate.size()!=0){
					if(listrate.get(0).get("REMARK")==null){
						rate="0";
					}else{
						rate = listrate.get(0).get("REMARK").toString();
					}
				}
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql_data,value);
				if(list.size()!=0){
					for (int j = 0; j < list.size(); j++) {
						Map<String, Object> eachmap = list.get(j);
						datax.add(eachmap.get("REGION_NAME"));
						online.add(df.format(Double.valueOf(eachmap.get("ADD_PROD_PROP").toString())*100));
					}
				}
				obj.put("datax",datax);
				datay.put("online", online);
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
	//其他业务
	public JSONArray otherbusiness(String onlineoroutline, String curdate,
			String otherbusiness) {
	   JSONArray result=new JSONArray();
	   DecimalFormat df = new DecimalFormat("#.00");
	   String sql="select a.ADD_PROD_PROP  from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a " +
	   		      "left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID=b.REGION_ID  " +
	   		      "where a.TRANSACT_TYPE=? and a.STATIS_DATE=? and a.PARENT_CPC_ID='other_bns' and a.CPC_ID=? order by b.ORDER_ID ";
       Object[] value={onlineoroutline,curdate,otherbusiness};
       List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql, value);
       if(list.size()>0){
    	   for(int i=0;i<list.size();i++){
    		   Map<String,Object> map=list.get(i);
    		   String onlinestr = df.format(Double.valueOf(map.get("ADD_PROD_PROP").toString())*100);
    		   result.add(Double.valueOf(onlinestr));
    	   }
       }
	   return result;
	}
	//流量包数据
	public JSONArray broadbaflowpacketnddata(String onlineoroutline,
			String curdate, String flowtype) {
		JSONArray result=new JSONArray();
		JSONObject obj=new JSONObject();
		String sql="select b.REGION_NAME,round(a.ADD_PROD_PROP,4) as ADD_PROD_PROP from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_ALL_INFO a " +
				   "left join BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO b on a.REGION_ID=b.REGION_ID " +
				   "where a.PARENT_CPC_ID='flux_bag' and a.STATIS_DATE=? and a.TRANSACT_TYPE=? and a.CPC_ID=? order by b.ORDER_ID";
		Object[] value1={curdate,onlineoroutline,flowtype};
		List<Map<String,Object>> list=this.getJdbcTemplate().queryForList(sql, value1);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				if(map.get("REGION_NAME").equals("石家庄")){
					obj.put("regionId", "SJZ");
				}else if(map.get("REGION_NAME").equals("秦皇岛")){
					obj.put("regionId", "QHD");
				}
				else if(map.get("REGION_NAME").equals("张家口")){
					obj.put("regionId", "ZJK");
				}
				else if(map.get("REGION_NAME").equals("廊坊")){
					obj.put("regionId", "LF");
				}
				else if(map.get("REGION_NAME").equals("唐山")){
					obj.put("regionId", "TS");
				}
				else if(map.get("REGION_NAME").equals("衡水")){
					obj.put("regionId", "HS");
				}
				else if(map.get("REGION_NAME").equals("保定")){
					obj.put("regionId", "BD");
				}
				else if(map.get("REGION_NAME").equals("承德")){
					obj.put("regionId", "CD");
				}
				else if(map.get("REGION_NAME").equals("邢台")){
					obj.put("regionId", "XT");
				}
				else if(map.get("REGION_NAME").equals("邯郸")){
					obj.put("regionId", "HD");
				}
				else if(map.get("REGION_NAME").equals("沧州")){
					obj.put("regionId", "CZ");
				}
				BigDecimal bg = new BigDecimal(Double.valueOf(map.get("ADD_PROD_PROP").toString()));
				double rateD = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj.put("onlineData", rateD);
				result.add(obj);
			}
			
		}else{
			String sql2="select REGION_NAME from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_AREA_INFO order by ORDER_ID ";
			List<Map<String,Object>> list1=this.getJdbcTemplate().queryForList(sql2);
			for(int i=0;i<list1.size();i++){
					Map<String,Object> map=list1.get(i);
					if(map.get("REGION_NAME").equals("石家庄")){
						obj.put("regionId", "SJZ");
					}else if(map.get("REGION_NAME").equals("秦皇岛")){
						obj.put("regionId", "QHD");
					}
					else if(map.get("REGION_NAME").equals("张家口")){
						obj.put("regionId", "ZJK");
					}
					else if(map.get("REGION_NAME").equals("廊坊")){
						obj.put("regionId", "LF");
					}
					else if(map.get("REGION_NAME").equals("唐山")){
						obj.put("regionId", "TS");
					}
					else if(map.get("REGION_NAME").equals("衡水")){
						obj.put("regionId", "HS");
					}
					else if(map.get("REGION_NAME").equals("保定")){
						obj.put("regionId", "BD");
					}
					else if(map.get("REGION_NAME").equals("承德")){
						obj.put("regionId", "CD");
					}
					else if(map.get("REGION_NAME").equals("邢台")){
						obj.put("regionId", "XT");
					}
					else if(map.get("REGION_NAME").equals("邯郸")){
						obj.put("regionId", "HD");
					}
					else if(map.get("REGION_NAME").equals("沧州")){
						obj.put("regionId", "CZ");
					}
					obj.put("onlineData", 0);
					result.add(obj);
			}
		}
		return result;
	}

}
