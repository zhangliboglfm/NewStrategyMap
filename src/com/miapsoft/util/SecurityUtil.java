package com.miapsoft.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import com.miapsoft.model.UserInfo;

/**
 * 安全校验工具类
 * <p>Title: SecurityUtil.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class SecurityUtil {

    /**
     * xss漏洞过滤
     * 
     * @param value
     * @return
     */
    public static String stripXSS(String value) {
	if (value != null) {
	    Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
		    Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("</script>",
		    Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("<script(.*?)>",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("eval\\((.*?)\\)",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("javascript:",
		    Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("vbscript:",
		    Pattern.CASE_INSENSITIVE);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile("onload(.*?)=",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	    scriptPattern = Pattern.compile(
		    "\\b(confirm|alert)\\b",
		    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
			    | Pattern.DOTALL);
	    value = scriptPattern.matcher(value).replaceAll("");
	}
	return value;
    }

    static String reg = "(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
	    + "(\\b(select|update|delete|insert|trancate|into|substr|ascii|declare|exec|drop|execute|from)\\b)";

    static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

   /**
    * SQL注入校验
    * @Title:isValid
    * @Description:TODO
    * @param str
    * @return true 存在注入风险
    * @author:李杰
    * @date:2017-3-29
    */
    public static boolean isValid(String str) {
	if (str == null) {
	    return true;
	}
	Matcher m = sqlPattern.matcher(str);
	if (m.find()) {
	    System.out.println("存在sql注入风险:" + str);
	    while (m.find()) {
		System.out.println(m.group(0));
	    }
	    return false;
	}
	return true;
    }
    /**
     * 用户是否登录校验
     * @Title:userIsLogin
     * @Description:不同项目可能会需要改动
     * @return true 已登录，false 未登录
     * @author:李杰
     * @date:2017-3-29
     */
    public static boolean userIsLogin(HttpServletRequest request){
	UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
	if(userInfo == null) {
	    return false;
	} 
	String userId = request.getParameter("userId");
	if(userId == null){
	    return true;
	}
	if(userInfo.getUser()==null){
	    return false;
	}
	if(userInfo.getUser().getUserIdAccounts()==null){
	    return false;
	}
	if(!userId.equals(userInfo.getUser().getUserIdAccounts())){
	    return false;
	}
	return true;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
	String s = "<script>-confirm(1)::(3)=-</script>";
	System.out.println(StringEscapeUtils.unescapeJavaScript(s));
	System.out.println(StringEscapeUtils.escapeJavaScript(s));
    }
}
