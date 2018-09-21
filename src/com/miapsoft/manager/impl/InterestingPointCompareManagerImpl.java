package com.miapsoft.manager.impl;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.InterestingPointCompareManager;

/**
 * <p>Title: InterestingPointCompareManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

/*"12639,12641,12649,12656,12661"*/
@Service("interestingPointCompareManager")
public class InterestingPointCompareManagerImpl extends AbstractManager implements InterestingPointCompareManager {

	public JSONArray queryRelIndex(String date, String datetype,String [] regionid, String indexid, String userId) {
		JSONArray result = new JSONArray();
		String tabelname = "";
		String datecolumn = "";
		if("D".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			datecolumn = "STATIS_DATE";
		}else if("M".equals(datetype.toUpperCase())){
			tabelname = "TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			datecolumn = "STATIS_MONTH";
		}
		for(int j=0;j<regionid.length;j++){
			JSONObject result1=new JSONObject();
			
			//获取该兴趣点的名字
			
			//TB_STRATEGY_MAP_DIM_POINT_INFO  兴趣点码表  CELL_ID   兴趣点id   CELL_NAME  兴趣点名字 
			String sql1="SELECT CELL_NAME FROM  BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO where CELL_ID= ? ";
			Object [] value1={regionid[j]};
			List<Map<String, Object>> listdata1 = this.getJdbcTemplate().queryForList(sql1, value1);
			if(listdata1.size()!=0){
				for(int i=0;i<listdata1.size();i++){
					Map<String, Object> map = listdata1.get(i);
					result1.put("name", map.get("CELL_NAME"));
				}
			}
			
			String  sql2=	" SELECT "+
								" C.MM_ID ,C.MM_NAME , round(B.MM_VAL,2) as MM_VAL , C.MM_UNIT,round(B.RING_RATE,2) as RING_RATE  "+
							" FROM "+
								" BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO C  "+		
							" LEFT JOIN "+
								" BASS_DATA."+tabelname+" B "+						
							" ON B.MM_ID = C.MM_ID AND B.REGION_ID= ? and B."+datecolumn+"= ? "+
							" WHERE C.DATE_TYPE = ? AND  C.MM_LEVEL ='POINT' AND C.MM_ID = ? ";
			Object [] value2 = {regionid[j],date,datetype,indexid};
			List<Map<String, Object>> listdata2 = this.getJdbcTemplate().queryForList(sql2, value2);
			JSONArray Indexvalue=new JSONArray();
			if(listdata2.size()!=0){
				for (int i = 0; i < listdata2.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata2.get(i);
					obj.put("indexId", map.get("MM_ID"));
					obj.put("indexName", map.get("MM_NAME"));
					obj.put("indexUnit", map.get("MM_UNIT"));
					if(map.get("MM_VAL")!=null){
						double d = Double.valueOf(map.get("MM_VAL").toString());
						obj.put("indexValue", NumberFormat.getInstance().format(d));
					}else{
						obj.put("indexValue", "--");
					}
					if(map.get("RING_RATE")!=null){
						double d = Double.valueOf(map.get("RING_RATE").toString());
						obj.put("indexRate", d);
						if(d==0||d==999){
							obj.put("indexRateType", "");
						}else{
							obj.put("indexRateType", (d>0)? "up" : "down");
						}
					}else{
						obj.put("indexRate", "--");
						obj.put("indexRateType", "--");
					}
					Indexvalue.add(obj);
				}
			}
			
			String sql =" select "+
			 		" A.MM_ID,C.MM_NAME, round(B.MM_VAL,2) as MM_VAL , C.MM_UNIT,round(B.RING_RATE,2) as RING_RATE "+
				"from "+
					" BASS_DATA.TB_STRATEGY_MAP_DIM_RELATE_MEASUR_INFO A "+ 			
			    " LEFT JOIN  "+
					 " BASS_DATA."+tabelname+" B "+				
			    " ON "+
					 " A.MM_ID=B.MM_ID and B."+datecolumn+"= ? AND B.REGION_ID= ? "+
				" LEFT JOIN  "+
					 " BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO C 	 "+					
			    " ON  "+
					" A.MM_ID=C.MM_ID AND C.DATE_TYPE = ? AND C.MM_LEVEL = 'POINT' "+
				" WHERE " +
				    "  A.PARENT_MM_ID = ?  AND A.DATE_TYPE = ? and (A.USER_ID = ? or A.USER_ID = 'SYSTEM') ORDER BY C.IS_HOME_SHOW ";
			Object [] value = {date,regionid[j],datetype,indexid,datetype,userId};
			List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql, value);
	
			if(listdata.size()!=0){
			
				for (int i = 0; i < listdata.size(); i++) {
					JSONObject obj = new JSONObject();
					Map<String, Object> map = listdata.get(i);
					obj.put("indexId", map.get("MM_ID"));
					obj.put("indexName", map.get("MM_NAME"));
					obj.put("indexUnit", map.get("MM_UNIT"));
					if(map.get("MM_VAL")!=null){
						double d = Double.valueOf(map.get("MM_VAL").toString());
						obj.put("indexValue", NumberFormat.getInstance().format(d));
					}else{
						obj.put("indexValue", "--");
					}
					if(map.get("RING_RATE")!=null){
						double d = Double.valueOf(map.get("RING_RATE").toString());
						obj.put("indexRate", d);
						if(d==0||d==999){
							obj.put("indexRateType", "");
						}else{
							obj.put("indexRateType", (d>0)? "up" : "down");
						}
					}else{
						obj.put("indexRate", "--");
						obj.put("indexRateType", "--");
					}
					Indexvalue.add(obj);
				}
			}
			result1.put("Indexvalue", Indexvalue);
			result.add(result1);
		}
		return result;
	}

	public String getMaxDate(String dateType) {
		String sql="SELECT STATIS_DATE FROM BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE= ?";
		Object[] value={dateType};
		List<Map<String, Object>> listdata = this.getJdbcTemplate().queryForList(sql,value);
		return  listdata.get(0).get("STATIS_DATE").toString();
	}
	
}
