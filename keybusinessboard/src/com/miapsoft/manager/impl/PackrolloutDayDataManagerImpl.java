package com.miapsoft.manager.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.PackrolloutDayDataManager;

/**
 * <p>Title: PackrolloutDayDataManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-14
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Service("packrolloutDayDataManager")
public class PackrolloutDayDataManagerImpl extends AbstractManager implements PackrolloutDayDataManager {

	public JSONObject daydatamiserables(String date) {
		
		JSONObject result= new JSONObject();
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
		
		JSONArray nodes=new JSONArray();
		for(int i=0;i<mealName.length;i++){
			JSONObject name = new JSONObject();
			name.put("name", mealName[i]);
			nodes.add(name);
		}
		result.put("nodes", nodes);
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
					"from BASS_DATA.TB_CHNL_PRODUCT_CHANGE_MARKT_CNT_INFO1 "+
					"WHERE STATIS_DATE= ? AND ZR_PROD_ID= ? ";
		if(rollin.length!=0){
		JSONArray Links=new JSONArray();
			for(int i=0;i<rollin.length;i++){
				JSONArray hang=new JSONArray();
				Object [] value ={date,rollin[i]};
				List<Map<String , Object>> list2=this.getJdbcTemplate().queryForList(sql2,value);
				if(list2.size()!=0){
					Map<String, Object> map=list2.get(0);
					for(int k=0;k<42;k++){
						JSONObject xyz =new JSONObject();
						
						if(map.get(("ZC_"+(k+1)).toString())!=null){
							double d1=Double.valueOf(map.get(("ZC_"+(k+1))).toString());
								if(d1<0){
									d1=d1*-1;
								}
								xyz.put("value", d1);
								xyz.put("source", i);
								xyz.put("target", k);
								xyz.put("name1",mealName[k]);
								xyz.put("name2", mealName[i]);
								xyz.put("rank", judgeColorRank(d1));
							
						}
						Links.add(xyz);
					}
				}
				
			}
			
			result.put("links", Links);
		}
		return result;
	}

	
	public int judgeColorRank(double d){
		int  rank = 0 ;
		if(d<500){
			rank=0;
		}else if(d<1000){
			rank=1;
		}else if(d<2000){
			rank=2;
		}else if(d<3000){
			rank=3;
		}else if(d<4000){
			rank=4;
		}else if(d<5000){
			rank=5;
		}else if(d<7500){
			rank=6;
		}else if(d<10000){
			rank=7;
		}else if(d<25000){
			rank=8;
		}else if(d<50000){
			rank=9;
		}else if(d<75000){
			rank=10;
		}else if(d<100000){
			rank=11;
		}else if(d>=100000){
			rank=12;
		}
		rank=10;
		return rank;
	}
}
