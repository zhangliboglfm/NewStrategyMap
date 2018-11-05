package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface RegistAccManager {
	
	//重置密码	
	String resetPass(String userId,String userAccount, String newPassword);
	//验证帐号是否存在
	String verifyAccount(String accountId);
	//注册账号
	String submitRegist(String accountId, String accPassword, String nickName,
			String accType, String accountSex, String idCard, String userPhone, String userEmail,
			String userQQ, String userWechat, String userWeibo, String jumpFlag);
	
}
