package com.miapsoft.manager.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PackRollOutManager;

/**
 * <p>Title: PackRollOutManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-3
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */


@Service("packRollOutManager")
public class PackRollOutManagerImpl extends AbstractManager implements PackRollOutManager {

	public JSONObject searchMoneyandUser(String date) {
			
			
		JSONObject result=new JSONObject();
		/*查询套餐提升金额和套餐提升用户*/
		String sql1="select ADD_NUM from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_SUS_INFO where STATIS_DATE= ? and ZRZC_ID = ?";
		Object [] value1={date,"markt_sum"};//套餐提升金额
		Object [] value2={date,"user_cnt"};//套餐提升用户
		List<Map<String, Object>> list1= this.getJdbcTemplate().queryForList(sql1,value1);
		List<Map<String, Object>> list2= this.getJdbcTemplate().queryForList(sql1,value2);
		if(list1.size()!=0){
			Map<String, Object> map =list1.get(0);
			result.put("money", map.get("ADD_NUM"));
		}
		if(list2.size()!=0){
			Map<String, Object> map =list2.get(0);
			result.put("user", map.get("ADD_NUM"));
		}
		
		return result;
	}

	public JSONArray searchTabelshuju(String date) {
		
		JSONArray result= new JSONArray();
		String [] rollin={
				"pri_lc.sjz.szx.ty3yth",
				"pri_gl.g.4G.fxtc6y_new",		
				"pri_gl.g.4G.fxtc8y_new",
				"pri_gl.g.szx.llshk.8y.12",	
				"pri_lc.sjz.szx.2013clk8y",	
				"pri_lc.sjz.szx.clk9y",	
				"priv_lc.lf.jyk12_xxt",	
				"pri_gl.g.szx.llk.18y",	
				"pri_gl.g.szx.llthk.18y",	
				"pri_gl.g.szx.llshk.18.12",	
				"pri_gl.g.4G.fxtc18y_new"	,
				"pri_gl.g.szx.llshk.18y",	
				"pri_gl.g.4G.fxtc15y_new",
				"pri_gl.g.4G.cjllw.8y_new",	
				"pri_gl.g.4G.fxtc28y_new",
				"pri_gl.g.4G.fxtc25y_new",
				"pri_gl.g.4G.cjllw.8y",
				"pri_gl.g.szx.llthk.38y"	,
				"pri_gl.g.4G.fxtc38y_new"	,
				"pri_gl.g.4G.fxtc48y_new",
				"pri_gl.g.4G.fxtc45y_new",
				"pri_4Gllk50y_new"	,
				"pri_gl.g.4G.fxtc58y_new",
				"pri_gl.g.qqtsltc.2014_58_new"	,
				"pri_gl.g.qqtswtc2014_58_new"	,
				"pri_gl.g.4G.cjllw.18y",
				"pri_gl.g.4G.fxtc65y_new",
				"pri_gl.g.4G.fxtc88y_new",
				"pri_gl.g.qqtsltc.2014_88_new"	,
				"pri_gl.g.qqtswtc2014_88_new",
				"pri_gl.g.4G.cjllw.18y_2",
				"pri_gl.g.4G.fxtc95y_new",
				"pri_gl.g.4G.cjllw.38y",
				"pri_gl.g.qqtsltc.2014_128_new",
				"pri_gl.g.qqtswtc2014_128_new",
				"pri_gl.g.4G.fxtc138y_new",
				"pri_gl.g.fxtc139y_new",
				"pri_gl.g.qqtswtc2014_158_new",
				"pri_gl.g.qqtsltc.2014_158_new",
				"pri_gl.g.4G.fxtc158y_new",
				"pri_gl.g.qqtsltc.2014_188_new",
				"pri_gl.g.qqtswtc2014_188_new"};
		
		String [] mealName={
				"神州行畅聊卡石家庄田园特惠卡_3元基础包","4G飞享套餐6元流量王","4G飞享套餐8元档_新","神州行8元流量市话卡(0.12)","石家庄2013畅聊卡（8元套餐）","神州行畅聊卡石家庄9元套餐_基础包",
				"神州行家园卡12元套餐_生活气象","18元流量卡","18元流量特惠卡","18元市话卡","4G飞享套餐18元档_新","神州行18元流量市话卡","4G飞享套餐18元流量王",
				"28元超级流量王","4G飞享套餐28元档_新","4G飞享套餐28元流量王","38元超级流量王","38元流量卡","4G飞享套餐38元档_新","4G飞享套餐48元档_新","4G飞享套餐48元流量王",
				"4G流量卡50元(新)","4G飞享套餐58元档_新","4G商旅套餐58元_新","4G上网套餐58元_新","58元超级流量王","4G飞享套餐68元流量王","4G飞享套餐88元档_新","4G商旅套餐88元_新",
				"4G上网套餐88元_新","88元超级流量王","4G飞享套餐98元流量王","108元超级流量王","4G商旅套餐128元_新","4G上网套餐128元_新","4G飞享套餐138元档_新",
				"4G飞享套餐138元流量王","4G上网套餐158元_新","4G商旅套餐158元_新","4G飞享套餐158元档_新","4G商旅套餐188元_新","4G上网套餐188元_新"};
			
		/*String sql2=" select ZC_1,ZC_2,ZC_3,ZC_4,ZC_5,ZC_6,ZC_7,ZC_8,ZC_9,ZC_10, "+
					" ZC_11 ,ZC_12,ZC_13,ZC_14,ZC_15,ZC_16,ZC_17,ZC_18,ZC_19,ZC_20, "+
				 	" ZC_21 ,ZC_22,ZC_23,ZC_24,ZC_25,ZC_26,ZC_27,ZC_28,ZC_29,ZC_30, "+
				 	" ZC_31 ,ZC_32,ZC_33,ZC_34,ZC_35,ZC_36,ZC_37,ZC_38,ZC_39,ZC_40, "+
					" ZC_41, ZC_42 "+
					" from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO WHERE STATIS_DATE= ? AND ZR_PROD_ID= ? ";*/
		String sql2="select"+
					" ZC_1 "+
					",ZC_2 "+
					",ZC_3 "+
					",ZC_4 "+
					",ZC_5 "+
					",ZC_6 "+
					",ZC_7 "+
					",ZC_8 "+
					",ZC_9 "+
					",ZC_10 "+
					",ZC_11 "+
					",ZC_12 "+
					",ZC_13 "+
					",ZC_14 "+
					",ZC_15 "+
					",ZC_16 "+
					",ZC_17 "+
					",ZC_18 "+
					",ZC_19 "+
					",ZC_20 "+
					",ZC_21 "+
					",ZC_22 "+
					",ZC_23 "+
					",ZC_24 "+
					",ZC_25 "+
					",ZC_26 "+
					",ZC_27 "+
					",ZC_28 "+
					",ZC_29 "+
					",ZC_30 "+
					",ZC_31 "+
					",ZC_32 "+
					",ZC_33 "+
					",ZC_34 "+
					",ZC_35 "+
					",ZC_36 "+
					",ZC_37 "+
					",ZC_38 "+
					",ZC_39 "+
					",ZC_40 "+
					",ZC_41 "+
					",ZC_42  "+
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO "+
					"WHERE STATIS_DATE= ? AND ZR_PROD_ID= ? ";
		if(rollin.length!=0){
			
			for(int i=0;i<rollin.length;i++){
				
				Object [] value ={date,rollin[i]};
				List<Map<String , Object>> list2=this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					JSONArray hangshuju=new JSONArray();
					Map<String, Object> map=list2.get(0);
					for(int k=0;k<42;k++){
						JSONObject NumAndRank =new JSONObject();
						if(map.get(("ZC_"+(k+1)).toString())!=null){
							
							double d1=Double.valueOf(map.get(("ZC_"+(k+1))).toString());
								if(d1<0){
									d1=d1*-1;
								}
								NumAndRank.put("num", NumberFormat.getInstance().format(d1));
								NumAndRank.put("name1", mealName[k]);
								NumAndRank.put("name2", mealName[i]);
								NumAndRank.put("rank",judgeColorRank(d1));
						}else{
							NumAndRank.put("num","0");
							NumAndRank.put("name1", mealName[k]);
							NumAndRank.put("name2", mealName[i]);
							NumAndRank.put("rank","COL_LVL1");
						}
						hangshuju.add(NumAndRank);	
					}
					result.add(hangshuju);
				}
				
			}
			
		}
		return result;
	}
	
	public String judgeColorRank(double d){
		String  rank ="";
		
		/*if(d==0){
			rank="COL_LVL1";
		}else if(d<200){
			rank="COL_LVL2";
		}else if(d<500){
			rank="COL_LVL3";
		}else if(d<2000){
			rank="COL_LVL4";
		}else if(d<10000){
			rank="COL_LVL5";
		}else if(d<50000){
			rank="COL_LVL6";
		}else if(d>=50000){
			rank="COL_LVL7";
		}*/
		
		if(d<500){
			rank="COL_LVL1";
		}else if(d<1000){
			rank="COL_LVL2";
		}else if(d<1500){
			rank="COL_LVL3";
		}else if(d<2000){
			rank="COL_LVL4";
		}else if(d<2500){
			rank="COL_LVL5";
		}else if(d<3000){
			rank="COL_LVL6";
		}else if(d<3500){
			rank="COL_LVL7";
		}else if(d<4000){
			rank="COL_LVL8";
		}else if(d<4500){
			rank="COL_LVL9";
		}else if(d<5000){
			rank="COL_LVL10";
		}else if(d<5500){
			rank="COL_LVL11";
		}else if(d<8000){
			rank="COL_LVL12";
		}else if(d<10000){
			rank="COL_LVL13";
		}else if(d<25000){
			rank="COL_LVL14";
		}else if(d<50000){
			rank="COL_LVL15";
		}else if(d<100000){
			rank="COL_LVL16";
		}else if(d<250000){
			rank="COL_LVL17";
		}else if(d<500000){
			rank="COL_LVL18";
		}else if(d<1000000){
			rank="COL_LVL19";
		}else if(d>=1000000){
			rank="COL_LVL20";
		}
		return rank;
	}

	
	public JSONObject searchPartMoneyAndUser(String date, JSONArray Numbers) {
		JSONObject result=new JSONObject();
		String [] sqls={
				" ( abs(ADD_NUM) > 0 and abs(ADD_NUM) < 500 )",
				" ( abs(ADD_NUM) >= 500 and abs(ADD_NUM) < 1000 ) ",
				" ( abs(ADD_NUM) >= 1000 and abs(ADD_NUM) < 1500 ) ",
				" ( abs(ADD_NUM) >= 1500 and abs(ADD_NUM) < 2000 ) ",
				" ( abs(ADD_NUM) >= 2000 and abs(ADD_NUM) < 2500 ) ",
				" ( abs(ADD_NUM) >= 2500 and abs(ADD_NUM) < 3000 ) ",
				" ( abs(ADD_NUM) >= 3000 and abs(ADD_NUM) < 3500 ) ",
				" ( abs(ADD_NUM) >= 3500 and abs(ADD_NUM) < 4000 ) ",
				" ( abs(ADD_NUM) >= 4000 and abs(ADD_NUM) < 4500 ) ",
				" ( abs(ADD_NUM) >= 4500 and abs(ADD_NUM) < 5000 ) ",
				" ( abs(ADD_NUM) >= 5000 and abs(ADD_NUM) < 5500 ) ",
				" ( abs(ADD_NUM) >= 5500 and abs(ADD_NUM) < 8000 ) ",
				" ( abs(ADD_NUM) >= 8000 and abs(ADD_NUM) < 10000 )",
				" ( abs(ADD_NUM) >= 10000 and abs(ADD_NUM) < 25000 ) ",
				" ( abs(ADD_NUM) >= 25000 and abs(ADD_NUM) < 50000 )",
				" ( abs(ADD_NUM) >= 50000 and abs(ADD_NUM) < 100000 ) ",
				" ( abs(ADD_NUM) >= 100000 and abs(ADD_NUM) < 250000 ) ",
				" ( abs(ADD_NUM) >= 250000 and abs(ADD_NUM) < 500000 ) ",
				" ( abs(ADD_NUM) >= 500000 and abs(ADD_NUM) < 1000000 ) ",
				" ( abs(ADD_NUM) >= 1000000 ) "
		};
		String sql="select sum(ADD_NUM) as SumMoney from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_SUM_INFO where statis_date= ? ";
		if(Numbers.size()==0){
			result.put("SumMoney", "0");
			return result;
		}else{
			sql+="and ( ";
			for(int i=0;i<Numbers.size();i++){
				if(i==0){
					sql+=sqls[Integer.parseInt(Numbers.get(i).toString())];
				}else{
					sql+=" or " +sqls[Integer.parseInt(Numbers.get(i).toString())];
				}
			}
			sql+=")";
		}
		Object [] value ={date};
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,value);
		Object SumMoney1=list.get(0).get("SumMoney");
		String SumMoney="";
		if(SumMoney1==null){
			SumMoney="0";
		}else{
			SumMoney=SumMoney1.toString();
		}
		result.put("SumMoney", SumMoney);
		return result;
	}
	
	
}
