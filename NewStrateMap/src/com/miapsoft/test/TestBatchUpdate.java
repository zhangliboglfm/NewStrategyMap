package com.miapsoft.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class TestBatchUpdate {
    public static void main(String[]args) throws JSONException, IOException{
	testIO();
    }
    public static void testIO() throws IOException{
	long start = new Date().getTime(),end = 0;
	File file = new File("V:\\新建文件夹 (3)\\import2.txt");
	InputStream is = new BufferedInputStream(new FileInputStream(file));
	byte[] c = new byte[1024];
	int count = 0;
	int readchar = 0;
	while ((readchar = is.read(c))!=-1) {
	    for(int i=0;i<readchar;i++){
		if(c[i]=='\n'){
		    count++;
		}
	    }
	}
	is.close();
	System.out.println("条数："+count);
	end = new Date().getTime();
	System.out.println("时间差："+(end-start));
    }
    public static void testBatchUpdate(){
	ApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext.xml");
	TestManager testManager = (TestManager) ap.getBean("testManager");
	long start = 0,end = 0;
	int size = 100000;
	start = new Date().getTime();
	List<String> sqls = new ArrayList<String>(); 
	for(int i=0;i<size;i++){
	    String sql = "insert into BASS_DATA.TB_AREA_EDGE_INFO_BAK values ('hospital"+i+"','"+i+"','"+i+"','"+i+"','"+i+"',"+i+",'医院',"+i+")";
	    sqls.add(sql);
//	    testManager.excuteSql(sql);
	}
	int s = sqls.size()/10000;
	for(int i=0;i<s;i++){
	    String [] sss = sqls.subList(i*10000, i*10000+9999).toArray(new String[0]);
	    testManager.excuteSql(sss);
	}
	end = new Date().getTime();
	System.out.println(end-start);
    }
}
