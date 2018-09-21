package com.miapsoft.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.miapsoft.manager.sys.LogManager;
import com.miapsoft.model.UserInfo;

import eu.bitwalker.useragentutils.UserAgent;
/**
 * 添加系统日志操作类
 * <p>Title: LogUtil.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class LogUtil {
	static LogManager logManager = null;
	public static LogManager getInstance(){
		if(logManager==null){
			ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext-cxf.xml");
			logManager=(LogManager) ac.getBean("logManager");
		}
		return logManager;
	}
	/**
	 * 经分项目添加日志记录
	 * @param logManager
	 * @param request
	 * @param moduleId
	 * @param operateDesc
	 * @param moduleBelongId
	 */
	public static void addLog(HttpServletRequest request,String moduleId,String operateDesc,String moduleBelongId){
		LogManager logManager = getInstance();
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");//用户信息
		String ip = getRemoteHost(request);//客户端IP
		String masterId = (String)request.getSession().getAttribute("masterId");//日志添加4A主帐号		
		String agent  = request.getHeader("User-agent");//获得客户端浏览器的版本号、类型及操作系统信息。
		UserAgent userAgent = new UserAgent(agent);
		String browser = userAgent.getBrowser().getName();
		String logType = "1";
		if ("Sys".equals(moduleBelongId)) {
			logType = "0";
		}
		System.out.println(moduleId+"--"+operateDesc+"--"+moduleBelongId+"--"+browser+"---"+ip);
		/*boolean isSuccess = logManager.addLog(masterId,userInfo.getUser().getUserIdAccounts(), moduleId, operateDesc, logType, ip, browser, "1", moduleBelongId);
		System.out.println(isSuccess);*/
	}
	/**
	 * 获取客户端真实IP
	 * @param request
	 * @return
	 */
	static String getRemoteHost(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip==null || ip.length()==0 || ip.equalsIgnoreCase("unknown")){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0 || ip.equalsIgnoreCase("unknown")){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0 || ip.equalsIgnoreCase("unknown")){
			ip = request.getRemoteAddr();
		}
		
		return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
	}
}
