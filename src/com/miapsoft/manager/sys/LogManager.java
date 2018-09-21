package com.miapsoft.manager.sys;

import javax.jws.WebService;

@WebService
public interface LogManager {
	public boolean addLog(String masterId,String userAccounts,String moduleId,String operateDesc,String logType,String loginIp,String browserVersion,String successFlag,String moduleBelongId);
}
