package com.miapsoft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: 正则表达式工具类
 * <p>Description: TODO</p>
 * @author: 张腾飞
 * @time: 2018-9-19
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public class RegexUtil {
	public static void main(String[] args) {
		String a = "hello world!";
		String a2 = "hello world2!";
		String a3 = "hello world3#!";
		String a4 = "hello world#";
		/*System.out.println(isContainChinese(a));
		System.out.println(isDigit(a));
		System.out.println(HasDigit(a));*/
		//str = str.replace(/\s*/g,"");
		String str="#王羲 之 #李四";
		str = str.replace(" ","");
		System.out.println(hasSpecialChar(str));
		System.out.println(hasSpecialChar(a4));
	}
	//校验是否包含汉字
	public static boolean isContainChinese(String str){
	    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	    Matcher m = p.matcher(str);
	    if (m.find()) {
	        return true;
	    }
	    return false;
	}
	// 判断一个字符串是否都为数字  
	public static boolean isDigit(String strNum) {  
	    return strNum.matches("[0-9]{1,}");  
	} 
	// 判断一个字符串是否含有数字
	public static boolean HasDigit(String content) {
	    boolean flag = false;
	    Pattern p = Pattern.compile(".*\\d+.*");
	    Matcher m = p.matcher(content);
	    if (m.matches()) {
	        flag = true;
	    }
	    return flag;
	}
	//判断是否含有特殊字符
	public static boolean isSpecialChar(String str) {
	    String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(str);
	    return m.find();
	}
	//判断是否含有特殊字符-除#外
	public static boolean hasSpecialChar(String str) {
		str=str.replace(" ","");
	    String regEx = "[ _`~!@$%^&*()+=|{}':;',\\[\\].<>/?~！@￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(str);
	    return m.find();
	}
}
