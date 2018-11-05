package com.miapsoft.manager;

import net.sf.json.JSONObject;


public interface AppSettingManager {
	//查询点击日期的安排
	JSONObject getlistbydate(String date, String appId);
	//查备选的摄影家列表
	String getAppPgList(String pgName, String start, String end);
	//查备选的文章列表
	String getAppArtList(String pgName, String start, String end);
	//重新上传设置
	String sureOper(String date, String appId, String phgList, String artList);
	//生成日历上的项
	JSONObject makeCalendar(String appId, String start, String end);
	//获取APP列表
	String getAppList(String pgName, String start, String end);
	
}
