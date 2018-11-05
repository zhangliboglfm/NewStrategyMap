package com.miapsoft.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

public class test {
	public static void main(String[] args) {
		System.out.println(formatUpDate("公元前337年"));
	}
	
	//更新出生、去世日期时的时间格式化
	public static String formatUpDate(String timeDate) {
		String formateDate="";
		String adOrBc=timeDate.substring(0,3);
		int monthIndex = timeDate.indexOf("月");
		int dayIndex = timeDate.indexOf("日");
		if ("公元前".equals(adOrBc)) {
			formateDate+="-"+timeDate.substring(3,timeDate.indexOf("年"));
		} else {
			formateDate+=timeDate.substring(0,timeDate.indexOf("年"));
		}
		if (monthIndex>0) {
			formateDate+=timeDate.substring(timeDate.indexOf("年")+1,timeDate.indexOf("月"));
			if (dayIndex>0) {
				formateDate+=timeDate.substring(timeDate.indexOf("月")+1,timeDate.indexOf("日"));
			} else {
				formateDate+="00";
			}
		} else {
			formateDate+="0000";
		}
		return formateDate;
	}
}	
