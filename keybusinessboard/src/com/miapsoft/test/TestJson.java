package com.miapsoft.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.miapsoft.manager.TestManager;

/**
 * <p>Title: TestJons.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-5-2
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public class TestJson {
    public static void main(String[]args) throws JSONException, UnsupportedEncodingException{
	ApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext.xml");
	TestManager testManager = (TestManager) ap.getBean("testManager");
	String[] citys = {"石家庄市","承德市","张家口市","秦皇岛市","唐山市","廊坊市","保定市","沧州市","衡水市","邢台市","邯郸市"};
	String[] cityCodes = {"311350","314050","313500","335300","315400","316250","312002","317100","318200","319450","320150"};
	String querycode = "50";//56:小区；49：学校；50：医院
	for(int c=0;c<citys.length;c++){
	    String city = URLEncoder.encode("='"+citys[c]+"'");
	    String cityCode = cityCodes[c];
	    String url = "http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEW_2016/MapServer/"+querycode+"/query?where=CITY"+city+"&text=&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=&returnGeometry=true&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=&f=pjson";
		GetMethod postMethod = new GetMethod(url);
		HttpClient client = new HttpClient();
		String response = "";
		try {
		    int statusCode = client.executeMethod(postMethod);
		    if (statusCode == HttpStatus.SC_OK) {
			response = postMethod.getResponseBodyAsString();
		    }
		    JSONObject obj = new JSONObject(response);
		    JSONArray array = obj.getJSONArray("features");
		    System.out.println(array.length());
		    int length = array.length();
		    for (int i=0;i<length;i++) {
			JSONObject cell = array.getJSONObject(i);
			String name = cell.getJSONObject("attributes").getString("DATA");
			if(name==null||"".equals(name)){
			    continue;
			}
			String code = i+"";
			int l = code.length();
			switch (l) {
			case 1:
			    code = "0000"+i;
			    break;
			case 2:
			    code = "000"+i;
			    break;
			case 3:
			    code = "00"+i;
			    break;
			case 4:
			    code = "0"+i;
			    break;

			default:
			    break;
			}
			JSONArray list = cell.getJSONObject("geometry").getJSONArray("rings");
			for(int j=0;j<list.length();j++){
			    JSONArray list2= list.getJSONArray(j);
			    for(int k=0;k<list2.length();k++){
				JSONArray list3 = list2.getJSONArray(k);
				String sql = "insert into BASS_DATA.TB_AREA_EDGE_INFO values ('"+cityCode+"hospital"+code+"','"+cityCode+"','"+name+"','"+list3.getString(0)+"','"+list3.getString(1)+"',"+k+",'医院',"+j+")";
				System.out.println(sql);
				testManager.excuteSql(sql);
			    }
			}
		    }
		} catch (HttpException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}  
	}
	
    }
}
