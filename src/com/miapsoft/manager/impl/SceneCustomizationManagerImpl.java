package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.SceneCustomizationManager;
@Service
public class SceneCustomizationManagerImpl extends AbstractManager implements SceneCustomizationManager{
	
	
 //兴趣点类型指标查询
	public JSONArray interestindex() {
		JSONArray result=new JSONArray();
		String sql="select  CELL_TYPE, CELL_TYPE_NAME from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO group by CELL_TYPE, CELL_TYPE_NAME ,ORDER_ID order by ORDER_ID  ";
		List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			result=JSONArray.fromObject(list);
		}
		return result;
	}
//兴趣点的地图数据
	public JSONArray mapdatajson(String dishiregionId,String xianquregionId, String dateType,
			String interestnumber1, String interestnumber2,
			JSONArray interestarrylist, JSONArray conditionidlist,JSONObject extent) {
		JSONArray result=new JSONArray();
		JSONObject obj1=new JSONObject();
		JSONArray obj2=new JSONArray();
		double XMIN = extent.getDouble("xMin");
		double XMAX = extent.getDouble("xMax");
		double YMIN = extent.getDouble("yMin");
		double YMAX = extent.getDouble("yMax");
		String table="";
		String Datesql="";//最日期
		String regionsql="";//地域条件
		if(dateType.equals("D")){
			table="BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_D";
			Datesql="STATIS_DATE in (select Max(STATIS_DATE) from BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE='D')";
		}else{
			table="BASS_DATA.TB_STRATEGY_MAP_MEASUR_FACT_INFO_M";
			Datesql="STATIS_MONTH in (select Max(STATIS_DATE) from BASS_DATA.TB_STRATEGY_MAP_DATE_INFO where DATE_TYPE='M')";
		}
		if(xianquregionId.toLowerCase().equals("all")){
			regionsql="b.REGION_CODE='"+dishiregionId+"'";
		}else{
			regionsql="b.REGION_CODE='"+dishiregionId+"' and b.TOWN_ID='"+xianquregionId+"'";
		}
		String sql="select  a.REGION_ID from "+table+" a  " +
				"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO b  on a.REGION_ID=b.CELL_ID " +
				"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO c on b.CELL_ID=c.CELL_ID " +
				"where a.LVL_ID='5' and b.CELL_LONG between "+XMIN+" and "+XMAX+" and b.CELL_LAT  between "+YMIN+" and "+YMAX+" and  " +
				""+Datesql+"  and  "+regionsql+"  "+
				"and b.CELL_TYPE in ("+allarry(interestarrylist)+") "+allindex(conditionidlist)+" group by a.REGION_ID "+havehvaing(conditionidlist)+" "  ;
		
		List<Map<String,Object>> list=this.getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			String sql2="select b.CELL_TYPE_NAME,b.CELL_LONG,b.CELL_LAT,b.CELL_ID,b.CELL_NAME,b.CELL_LONG,b.CELL_LAT,a.RINGS from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO a " +
					"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO b on a.CELL_ID=b.CELL_ID " +
					"where a.CELL_ID in ("+selectregion(list)+") ";
			List<Map<String,Object>> list1=this.getJdbcTemplate().queryForList(sql2);
			if(list1.size()>0){
				 for (int i=0;i<list1.size();i++){
					 Map<String,Object> map=list1.get(i);
					 obj1.put("CELL_NAME", map.get("CELL_NAME"));
					 obj1.put("CELL_ID", map.get("CELL_ID"));
					 obj1.put("CELL_LONG", map.get("CELL_LONG"));
					 obj1.put("CELL_LAT", map.get("CELL_LAT"));
					 obj1.put("CELL_TYPE_NAME", map.get("CELL_TYPE_NAME"));
					 obj1.put("ringarry",ringformat(map.get ("RINGS").toString()));
					 result.add(obj1);
				 }
				
			}
		}
		return result;
		
	}
//取出list中regionId
	public String selectregion(List<Map<String, Object>> list){
		String restr="";
			for (int i=0;i<list.size();i++){
				Map<String,Object> map= list.get(i);
				if(i==(list.size()-1)){
					restr+="'"+map.get("REGION_ID")+"'";
				}else{
					restr+="'"+map.get("REGION_ID")+"'"+",";
				}
			}
		
		return restr;
	}
	
 //配置条件的数值
	public String allindex(JSONArray arry){
		String str="";
		if(arry.size()>0){
			str+="and (";
			for (int i=0;i<arry.size();i++){
				JSONObject object=arry.getJSONObject(i);
				if(i==(arry.size()-1)){
					str+="("+ "MM_ID='"+object.get("id")+"'and MM_VAL between '"+object.get("input1")+"' and '"+object.get("input2")+"' "+")";
				}else{
					str+="("+ "MM_ID='"+object.get("id")+"' and MM_VAL between '"+object.get("input1")+"' and '"+object.get("input2")+"' " +")"+"or ";
				}
			}
			str+=")";
		}else{
		}
		return str;
	}
//如果筛选条件有数
	 public String havehvaing(JSONArray arry){
		 String str="";
		 if(arry.size()>0){
			 str+="having count(0)='"+arry.size()+"'";
		 }
		return str;
	 }
	
	
	
 //取出数组的值
	public String allarry(JSONArray zoom){
		 String str="";
		 for(int i=0;i<zoom.size();i++){
			 if(i==(zoom.size()-1)){
				str+="'"+zoom.getString(i)+"'"; 
			 }else{
				 str+="'"+zoom.getString(i)+"'"+","; 
			 }
		 }
		 return str;
	}
 //轮廓的数据格式	
	public JSONArray ringformat(String object){
		String ringsbuffer="[["+object.replaceAll(",","],[").replaceAll("\\s",",")+"]]";
		JSONArray ringsbufferarry=JSONArray.fromObject(ringsbuffer);
		return ringsbufferarry;
	}
	
//保存筛选的条件	
	 @Transactional
	public int savedata(String userId, String phone, String scencname,
			String scencdesc, String dataType, JSONArray celltypelist,
			JSONArray cellattrulist, String datetime,String interestnumber1, String interestnumber2) {
		
		int result=0;
		int result1=0;
		int result2 = 0;
		String sql="insert into BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO (SCENE_ID,SCENE_NAME,USER_ID,USER_PHONE,SCENE_DESC,SCENE_TYPE,DATE_TYPE) values(?,?,?,?,?,'filter',?)";
		Object []value={datetime,scencname,userId,phone,scencdesc,dataType};
		result=this.getJdbcTemplate().update(sql, value);
		if(cellattrulist.size()>0){
			 for(int i=0;i<cellattrulist.size();i++){
				 String sql1="insert into BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO (SCENE_ID,CELL_TYPE,MM_ID,MM_MIN,MM_MAX,MM_CNT,ORDER_ID) values('"+datetime+"','"+formatt(celltypelist)+"','"+cellattrulist.getJSONObject(i).get("id")+"','"+cellattrulist.getJSONObject(i).get("input1")+"','"+cellattrulist.getJSONObject(i).get("input2")+"','"+cellattrulist.size()+"','"+i+"') ";
				  result1=this.getJdbcTemplate().update(sql1);
				 if(result1!=1){
					break;
				 }
			 }
		} 
		result2=result+result1;
		return result2;
	}
//兴趣点的类型插入的格式
	public String formatt(JSONArray data){
		//String str=data.toString().replaceAll("[","").replaceAll("]", "").replaceAll("\"", "").replaceAll("\"", "");
		String str="";
		for (int i = 0; i < data.size(); i++) {
			str+=data.getString(i)+",";
		}
		return str.substring(0, str.length()-1);
		
	}
//兴趣点勾选的地图数据	
	public JSONArray pointmapdata(String dishiregionId, String xianquregionId,
			String pointertype, JSONObject extent) {
		 JSONArray result=new JSONArray();
		 JSONObject obj1=new JSONObject();
		 double XMIN = extent.getDouble("xMin");
		 double XMAX = extent.getDouble("xMax");
		 double YMIN = extent.getDouble("yMin");
		 double YMAX = extent.getDouble("yMax");
		 String regionsql="";
		 String sql="";
		 if(xianquregionId.toLowerCase().equals("all")){
				regionsql="a.REGION_CODE='"+dishiregionId+"'";
			}else{
				regionsql="a.REGION_CODE='"+dishiregionId+"' and a.TOWN_ID='"+xianquregionId+"'";
			}
		 
		 if(pointertype.equals("allpoint")){
	       sql="select a.CELL_TYPE,a.CELL_TYPE_NAME,a.CELL_ID,a.CELL_NAME,a.CELL_LONG,a.CELL_LAT,b.RINGS from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a " +
				 	"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID=b.CELL_ID " +
				 	"where "+regionsql+" and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT  between "+YMIN+" and "+YMAX+" ";
		 }else{
		  sql="select a.CELL_TYPE,a.CELL_TYPE_NAME,a.CELL_ID,a.CELL_NAME,a.CELL_LONG,a.CELL_LAT,b.RINGS from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a " +
		 		"left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID=b.CELL_ID " +
		 		"where "+regionsql+" and a.CELL_LONG between "+XMIN+" and "+XMAX+" and a.CELL_LAT  between "+YMIN+" and "+YMAX+" and CELL_TYPE='"+pointertype+"' ";
		 }
		 List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql);
		 if(list.size()>0){
			 for (int i=0;i<list.size();i++){
				 Map<String,Object> map=list.get(i);
				 obj1.put("CELL_NAME", map.get("CELL_NAME"));
				 obj1.put("CELL_ID", map.get("CELL_ID"));
				 obj1.put("CELL_LONG", map.get("CELL_LONG"));
				 obj1.put("CELL_LAT", map.get("CELL_LAT"));
				 obj1.put("CELL_TYPE_NAME", map.get("CELL_TYPE_NAME"));
				 obj1.put("CELL_TYPE", map.get("CELL_TYPE"));
				 obj1.put("ringarry",ringformat(map.get ("RINGS").toString()));
				 result.add(obj1);
			 }
				
			}
		return result;
	}
	//兴趣点的数据保存
    @Transactional
	public int pointmapdata(String useId, String pointsencename,
			String pointphone, String pointscencdesc, String pointertype,
			JSONArray pointerarrylist) {
			int result=0;
			int result1=0;
			int result2=0;
			Date date=new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
			String datetime=df.format(date);
		    String sql="insert into BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO (SCENE_ID,SCENE_NAME,USER_ID,USER_PHONE,SCENE_DESC,SCENE_TYPE) " +
		    		   " values (?,?,?,?,?,'check')";
		    Object[]value={datetime,pointsencename,useId,pointphone,pointscencdesc};
		    result=this.getJdbcTemplate().update(sql, value);
		    String sql1="";
		    if(pointerarrylist.size()>0){
		    	for(int i=0;i<pointerarrylist.size();i++){
		    		JSONObject object=pointerarrylist.getJSONObject(i);
		    		sql1="insert into BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO (SCENE_ID,CELL_TYPE,MM_ID,ORDER_ID) " +
		    				"values ('"+datetime+"','"+object.get("typeid")+"','"+object.get("pointid")+"','"+i+"') ";
		    		 result1=this.getJdbcTemplate().update(sql1);
		    		 if(result1!=1){
		    			break;
		    		   }
		    	}
		    }else{
		    	  sql1="insert into BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO (SCENE_ID,CELL_TYPE,ORDER_ID) " +
	    				"values ('"+datetime+"','"+pointertype+"','0') ";
		    	  result1=this.getJdbcTemplate().update(sql1);
		    } 
		   
		    result2=result+result1;
		   return result2;
	}
	//编辑数据的查询
	public JSONArray editdatasearch(String senceid, String eidttype) {
		// TODO Auto-generated method stub
		JSONArray result=new JSONArray();
		JSONObject obj=new JSONObject();
		String sql="";
		if(eidttype.equals("filter")){
		     sql="select a.SCENE_ID,a.SCENE_NAME,a.DATE_TYPE,a.USER_PHONE,a.SCENE_DESC,b.CELL_TYPE,b.MM_ID,b.MM_MIN,b.MM_MAX,c.MM_NAME,c.MM_UNIT from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
		     		"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID=b.SCENE_ID left join BASS_DATA.TB_STRATEGY_MAP_DIM_MEASUR_INFO c on c.MM_ID=b.MM_ID where a.SCENE_ID= ? and c.MM_LEVEL='POINT'" +
		     		"GROUP BY a.SCENE_ID,a.SCENE_NAME,a.DATE_TYPE,a.USER_PHONE,a.SCENE_DESC,b.CELL_TYPE,b.MM_ID,b.MM_MIN,b.MM_MAX,c.MM_NAME,c.MM_UNIT ,b.ORDER_ID ORDER BY b.ORDER_ID  ";
		     Object[]values={senceid};
		     List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql, values);
		     if(list.size()>0){
		    	 for(int i=0;i<list.size();i++){
		    		 Map<String,Object> map=list.get(i);
		    		 obj.put("SCENE_ID", map.get("SCENE_ID"));
		    		 obj.put("SCENE_NAME", map.get("SCENE_NAME"));
		    		 obj.put("DATE_TYPE", map.get("DATE_TYPE"));
		    		 obj.put("USER_PHONE", map.get("USER_PHONE"));
		    		 obj.put("SCENE_DESC", map.get("SCENE_DESC"));
		    		 obj.put("CELL_TYPE", celltype(map.get("CELL_TYPE").toString()));
		    		 obj.put("MM_ID", map.get("MM_ID"));
		    		 obj.put("MM_MIN", map.get("MM_MIN"));
		    		 obj.put("MM_MAX", map.get("MM_MAX"));
		    		 obj.put("MM_NAME", map.get("MM_NAME"));
		    		 obj.put("MM_UNIT", map.get("MM_UNIT"));
		    		 result.add(obj);
		    	 }
		     }
		}else if(eidttype.equals("check")){
			 sql="select b.CELL_TYPE,b.MM_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
			     	"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on b.SCENE_ID=a.SCENE_ID where a.SCENE_ID= ? ";
			 Object[]values={senceid};
		     List<Map<String,Object>>list=this.getJdbcTemplate().queryForList(sql, values);
		     if(list.size()>0){
		    	  //已选的兴趣点为空
		    	  if(list.size()==1&&(list.get(0).get("MM_ID")==""||list.get(0).get("MM_ID")==null)){
		    		  String sql1="select a.SCENE_ID,a.SCENE_NAME,a.USER_PHONE,a.USER_PHONE,a.SCENE_DESC,b.CELL_TYPE,b.MM_ID from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO a " +
		  			     	"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO b on a.SCENE_ID=b.SCENE_ID where a.SCENE_ID= ? ";
		    		  List<Map<String,Object>>list1=this.getJdbcTemplate().queryForList(sql1, values);
		    		  if(list1.size()>0){
		    			  result=JSONArray.fromObject(list1);
		    		  }
		    	  }else{
		    		 String sql1="select a.CELL_ID,a.CELL_TYPE,a.CELL_TYPE_NAME,a.CELL_NAME,a.CELL_LONG,a.CELL_LAT,b.RINGS,d.SCENE_ID,d.SCENE_NAME,d.USER_PHONE,d.SCENE_DESC " +
		    		 		"from BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_INFO a left join BASS_DATA.TB_STRATEGY_MAP_DIM_POINT_LOCATION_INFO b on a.CELL_ID=b.CELL_ID " +
		    		 		"left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO c on c.MM_ID=b.CELL_ID left join BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO d on c.SCENE_ID=d.SCENE_ID " +
		    		 		"where d.SCENE_ID=? order by c.ORDER_ID "; 
		    		 Object[]value={senceid};
		    		 List<Map<String,Object>>list2=this.getJdbcTemplate().queryForList(sql1, value);
		    		 if(list2.size()>0){
		    			 for (int i=0;i<list.size();i++){
			    			 Map<String,Object> map=list2.get(i);
			    			 obj.put("CELL_NAME", map.get("CELL_NAME"));
			    			 obj.put("CELL_TYPE", map.get("CELL_TYPE"));
							 obj.put("CELL_ID", map.get("CELL_ID"));
							 obj.put("CELL_LONG", map.get("CELL_LONG"));
							 obj.put("CELL_LAT", map.get("CELL_LAT"));
							 obj.put("CELL_TYPE_NAME", map.get("CELL_TYPE_NAME"));
							 obj.put("SCENE_NAME", map.get("SCENE_NAME"));
							 obj.put("USER_PHONE", map.get("USER_PHONE"));
							 obj.put("SCENE_DESC", map.get("SCENE_DESC"));
							 obj.put("ringarry",ringformat(map.get ("RINGS").toString()));
							 result.add(obj);
		    			 } 
		    		 }
		    		  
		    	  }
		     }
		}
		return result;
	}
//兴趣点类型的格式
	public JSONArray celltype(String data){
		
		JSONArray result=new JSONArray();
		if(!data.equals("")){
			String[] arry=data.split(",");
			result=JSONArray.fromObject(arry);
		}
		return result;
	}
//编辑数据保存之前先删除之前的数据
	@Transactional
	public int editpointdelete(String senceid) {
		// TODO Auto-generated method stub
		int result=0;
		int result1=0;
		int result2=0;
	    String sql="delete from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_INFO where SCENE_ID=?";
	    Object[]value={senceid};
	    result1=this.getJdbcTemplate().update(sql, value);
	    String sql2="delete from BASS_DATA.TB_STRATEGY_MAP_DIM_SCENE_MESSAGE_INFO where SCENE_ID=? ";
	    result2=this.getJdbcTemplate().update(sql2, value);
	    result=result1+result2;
		return result;
	}
	
}


