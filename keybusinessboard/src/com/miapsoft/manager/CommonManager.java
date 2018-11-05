package com.miapsoft.manager;

import java.text.ParseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: CommonManager.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-7-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface CommonManager {

	public JSONObject getPackDemoData(String date, String linetype);
	
	public JSONObject getPackDemoData2(String date, String linetype);
	
	public JSONObject getPackQipaoData(String date, String linetype);
	
	public JSONObject getPackQipaoData2(String date, String linetype);
	
	public JSONArray getPackQipaoData3(String regionId,String dateMin)throws ParseException;
	
	public JSONArray getPackQipaoData4();
	
	public JSONArray getPackQipaoData5();
	
	public JSONObject getDataTime(String tabelname,String columnstr) throws Exception;
	
	public JSONArray getPRData();
}
