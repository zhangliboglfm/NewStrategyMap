package com.miapsoft.manager.sys;

import java.util.List;

import javax.jws.WebService;

import com.miapsoft.model.TbAdmUserUnit;
import com.miapsoft.model.TbLoginNumberZc;
import com.miapsoft.model.UserInfo;

@WebService
public interface SystemManager {
	public UserInfo getUserInfo(String userId);
	public List<TbAdmUserUnit> getUser(String parentUserId);
	public List<String> getUserId(String userName);
	//查询工号当前登录的IP地址
	public String tranUserLoginIp(String userId,String clientIp);
	//查询工号登陆信息
	public TbLoginNumberZc tranUserLoginData(String userId);
	public int TestUser(String userId, String ip);
	
}
