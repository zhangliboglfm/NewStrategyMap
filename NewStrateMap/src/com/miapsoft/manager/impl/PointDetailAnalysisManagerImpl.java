package com.miapsoft.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import com.ibm.db2.jcc.b.i;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PointDetailAnalysisManager;

/**
 * <p>Title: PointDetailAnalysisManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-21
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Service("pointDetailAnalysisManager")
public class PointDetailAnalysisManagerImpl extends AbstractManager implements PointDetailAnalysisManager {
	
	/*查询年龄分布和性别分布*/
	public JSONObject queryPointDetailAnalysis(String pointId, String dateType, String date) {
		JSONObject result=new JSONObject();
		String tablename="";
		String field="";
		if(dateType.equalsIgnoreCase("D")){
			tablename="TB_STRATEGY_MAP_LAYER_FACT_INFO_D";
			field="STATIS_DATE";
		}else{
			tablename="TB_STRATEGY_MAP_LAYER_FACT_INFO_M";
			field="STATIS_MONTH";
		}
		/*查询年龄分布*/
		String sql1=" select "+
						" a.LAYER_NAME , a.LAYER_ID , b.LAYER_VAL "+
					" from  "+
						" BASS_DATA.TB_STRATEGY_MAP_DIM_LAYER_INFO a "+
					" left join "+
						" BASS_DATA."+tablename+" b "+
					" on "+
						" a.LAYER_ID= b.LAYER_ID  and b."+field+"= ? and b.REGION_ID= ? "+
					" where "+
						" a.LAYER_TYPE='AGE_LVL'  order by ORDER_ID ";
		Object [] value1={date,pointId};
		/*封装年龄分布*/
		JSONObject nianlingfenbu =new JSONObject();
		List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
		JSONArray jsonx1= new JSONArray();
		JSONArray data1=new JSONArray();
		for(int i1=0;i1<listdata1.size();i1++){
			Map<String, Object> map = listdata1.get(i1);
			jsonx1.add(map.get("LAYER_NAME"));
			data1.add(map.get("LAYER_VAL"));
		}
		nianlingfenbu.put("jsonx1", jsonx1);
		nianlingfenbu.put("data1", data1);
		result.put("ageDistribution",nianlingfenbu );
		
		
		/*查询性别分布*/
		String sql2=" select "+
						" a.LAYER_NAME , a.LAYER_ID , b.LAYER_VAL "+
					" from  "+
						" BASS_DATA.TB_STRATEGY_MAP_DIM_LAYER_INFO a "+
					" left join "+
						" BASS_DATA."+tablename+" b "+
					" on "+
						" a.LAYER_ID= b.LAYER_ID and b."+field+"= ? and b.REGION_ID= ? "+
					" where  "+
						" a.LAYER_TYPE='SEX_LVL'  order by ORDER_ID ";
		Object [] value2={date,pointId};
		/*封装装性别分布*/
		JSONArray xingbiefenbu =new JSONArray();
		List<Map<String, Object>> listdata2= this.getJdbcTemplate().queryForList(sql2,value2);
		for(int i1=0;i1<listdata2.size();i1++){
			JSONArray data2=new JSONArray();
			JSONObject data3 =new JSONObject();
			Map<String, Object> map = listdata2.get(i1);
			if(map.get("LAYER_NAME").toString().equalsIgnoreCase("男")){
				data3.put("name", map.get("LAYER_NAME"));
				data3.put("y", map.get("LAYER_VAL"));
				data3.put("sliced", true);
				data3.put("selected", true);
				xingbiefenbu.add(data3);
			}else {
				data2.add(map.get("LAYER_NAME"));
				data2.add(map.get("LAYER_VAL"));
				xingbiefenbu.add(data2);
			}
			
		}
		result.put("sexDistribution",xingbiefenbu);
		return result;
	}

	public JSONObject queryPeopleFlowTrend(String pointId, String pointType, String dateType, String date) {
		JSONObject result= new JSONObject();
		JSONArray result1 =new JSONArray();
		JSONArray result2= new JSONArray();
		if(dateType.equalsIgnoreCase("D")){
			String  sql1="";
			// 查询医院   HEALTH_CARE，商圈  shop 查询两种数据 常驻人口和人流量  
			if(pointType.equalsIgnoreCase("HEALTH_CARE")||pointType.equalsIgnoreCase("shop")){
			
				    sql1= " SELECT USER_CNT,CZ_USER_CNT ,STATIS_DATE "+
				    	   " FROM "+
				    		" (select  "+ 
				    					" USER_CNT,CZ_USER_CNT ,STATIS_DATE "+
				    		" from  "+
				    			" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
				    		" where  REGION_ID = ? and STATIS_DATE<= ? order by STATIS_DATE desc "+
				    		" FETCH FIRST 15 ROWS ONLY) "+
				    		" ORDER BY STATIS_DATE ";
				  Object [] value1 = {pointId,date};
				  JSONObject changzhu=new JSONObject();
				  changzhu.put("name","常驻用户数");
				  changzhu.put("color", "#EF5F00");
				  JSONArray changzhu1=new JSONArray();
				  JSONObject renliuliang=new JSONObject();
				  renliuliang.put("name", "人流量");
				  renliuliang.put("color", "#19C3E6");
				  JSONArray renliuliang1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  Map<String, Object> map =listdata1.get(i1);
					  changzhu1.add(map.get("CZ_USER_CNT"));
					  renliuliang1.add(map.get("USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  }
				  changzhu.put("data", changzhu1);
				  renliuliang.put("data", renliuliang1);
				  result2.add(changzhu);
				  result2.add(renliuliang);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}else if(pointType.equalsIgnoreCase("orther")){
				 sql1= " SELECT USER_CNT, STATIS_DATE "+
				    	   " FROM "+
				    		" (select  "+ 
				    					" USER_CNT,STATIS_DATE "+
				    		" from  "+
				    			" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
				    		" where  REGION_ID = ? and STATIS_DATE<= ? order by STATIS_DATE desc "+
				    		" FETCH FIRST 15 ROWS ONLY) "+
				    		" ORDER BY STATIS_DATE ";
				  Object [] value1 = {pointId,date};
				  JSONObject renliuliang=new JSONObject();
				  renliuliang.put("name", "人流量");
				  renliuliang.put("color", "#19C3E6");
				  JSONArray renliuliang1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  Map<String, Object> map =listdata1.get(i1);
					  renliuliang1.add(map.get("USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  };
				  renliuliang.put("data", renliuliang1);
				  result2.add(renliuliang);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}else{
				 sql1= " SELECT CZ_USER_CNT ,STATIS_DATE "+
				    	   " FROM "+
				    		" (select  "+ 
				    					" CZ_USER_CNT ,STATIS_DATE "+
				    		" from  "+
				    			" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
				    		" where  REGION_ID = ? and STATIS_DATE<= ? order by STATIS_DATE desc "+
				    		" FETCH FIRST 15 ROWS ONLY) "+
				    		" ORDER BY STATIS_DATE ";
				  Object [] value1 = {pointId,date};
				  JSONObject changzhu=new JSONObject();
				  changzhu.put("name","常驻用户数");
				  changzhu.put("color", "#EF5F00");
				  JSONArray changzhu1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  Map<String, Object> map =listdata1.get(i1);
					  changzhu1.add(map.get("CZ_USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  }
				  changzhu.put("data", changzhu1);
				  result2.add(changzhu);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}
			
		}else if(dateType.equalsIgnoreCase("M")){

			String  sql1="";
			// 查询医院   HEALTH_CARE，商圈  shop 查询两种数据 常驻人口和人流量  
			if(pointType.equalsIgnoreCase("HEALTH_CARE")||pointType.equalsIgnoreCase("shop")){
			
				    sql1=" select "+
							" USER_CNT,CZ_USER_CNT, STATIS_DATE "+
						" from  "+
							" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
						" where "+
							" substr(STATIS_DATE,1,6)= ? and REGION_ID = ?  order by STATIS_DATE ";
				  Object [] value1 = {date,pointId};
				  JSONObject changzhu=new JSONObject();
				  changzhu.put("name","常驻用户数");
				  changzhu.put("color", "#EF5F00");
				  JSONArray changzhu1=new JSONArray();
				  JSONObject renliuliang=new JSONObject();
				  renliuliang.put("name", "人流量");
				  renliuliang.put("color", "#19C3E6");
				  JSONArray renliuliang1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  if((i1%2)==0){
						  continue;
					  }
					  Map<String, Object> map =listdata1.get(i1);
					  changzhu1.add(map.get("CZ_USER_CNT"));
					  renliuliang1.add(map.get("USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  }
				  changzhu.put("data", changzhu1);
				  renliuliang.put("data", renliuliang1);
				  result2.add(changzhu);
				  result2.add(renliuliang);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}else if(pointType.equalsIgnoreCase("orther")){
				 sql1=" select "+
							" USER_CNT , STATIS_DATE "+
						" from  "+
							" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
						" where "+
							" substr(STATIS_DATE,1,6)= ? and REGION_ID = ?  order by STATIS_DATE ";
				  Object [] value1 = {date,pointId};
				  JSONObject renliuliang=new JSONObject();
				  renliuliang.put("name", "人流量");
				  renliuliang.put("color", "#19C3E6");
				  JSONArray renliuliang1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  if((i1%2)==0){
						  continue;
					  }
					  Map<String, Object> map =listdata1.get(i1);
					  renliuliang1.add(map.get("USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  };
				  renliuliang.put("data", renliuliang1);
				  result2.add(renliuliang);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}else{
				 sql1=" select "+
							" CZ_USER_CNT ,STATIS_DATE "+
						" from  "+
							" BASS_DATA.TB_STRATEGY_MAP_POINT_USER_FLOW_FACT_INFO_D "+
						" where "+
							" substr(STATIS_DATE,1,6)= ? and REGION_ID = ?  order by STATIS_DATE ";
				  Object [] value1 = {date,pointId};
				  JSONObject changzhu=new JSONObject();
				  changzhu.put("name","常驻用户数");
				  changzhu.put("color", "#EF5F00");
				  JSONArray changzhu1=new JSONArray();
				  List<Map<String, Object>> listdata1= this.getJdbcTemplate().queryForList(sql1,value1);
				  for(int i1=0;i1<listdata1.size();i1++){
					  if((i1%2)==0){
						  continue;
					  }
					  Map<String, Object> map =listdata1.get(i1);
					  changzhu1.add(map.get("CZ_USER_CNT"));
					  result1.add(map.get("STATIS_DATE").toString().substring(4,6)+"月"+map.get("STATIS_DATE").toString().substring(6,8)+"日");
				  }
				  changzhu.put("data", changzhu1);
				  result2.add(changzhu);
				  result.put("datax", result1);
				  result.put("datashuju", result2);
			}
		}
		return result;
	}

}
