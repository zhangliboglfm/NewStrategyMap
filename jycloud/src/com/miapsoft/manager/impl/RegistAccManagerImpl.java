package com.miapsoft.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.RegistAccManager;
import com.miapsoft.util.MD5Util;

@Service("registAccManager")
public class RegistAccManagerImpl extends AbstractManager implements RegistAccManager {
	//重置密码
	public String resetPass(String userId,String userAccount, String newPassword) {
		userAccount=MD5Util.newMD5(userAccount).substring(0,128);//原密码
		String verPassword="SELECT USER_PWD FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID = '"+userId+"' ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(verPassword);
		if (list.size()!=0) {
			if (!(list.get(0).get("USER_PWD")+"").equals(userAccount)) {
				return "2";//原密码不正确
			}
		}
		newPassword=MD5Util.newMD5(newPassword).substring(0,128);
		String resetSql="UPDATE TB_SYS_USER_BASE_INFO SET USER_PWD ='"+newPassword+"' WHERE USER_ACCT_ID ='"+userId+"' ";
		int count=this.getJdbcTemplate().update(resetSql);
		if (count>0) {
			return "1";//成功
		} else {
			return "0";//出现错误
		}
	}
	//验证帐号是否存在
	public String verifyAccount(String accountId) {
		String verifySql = "SELECT COUNT(1) FROM TB_SYS_USER_BASE_INFO WHERE USER_ACCT_ID = '"+accountId+"'";
		int count=this.getJdbcTemplate().queryForInt(verifySql);
		if (count>0) {
			return "0";
		} else {
			return "1";
		}
	}
	//注册账号
	public String submitRegist(String accountId, String accPassword,
			String nickName, String accType, String accountSex, String idCard, String userPhone,
			String userEmail, String userQQ, String userWechat, String userWeibo, String jumpFlag) {
		String sexFlag="";
		accPassword=MD5Util.newMD5(accPassword).substring(0,128);
		if ("男".equals(accountSex.trim())) {
			sexFlag="M";
		} else {
			sexFlag="F";
		}
		if ("1".equals(jumpFlag)) {
			accType="后台用户";
		} else if("2".equals(jumpFlag)) {
			accType="APP用户";
		}
		String insertSql="INSERT INTO TB_SYS_USER_BASE_INFO " +
				"(USER_ACCT_ID,USER_PWD,USER_NAME,USER_TYPE,APP_ID,USER_GENDER,IDCARD_NO,PHONE_NO,EMAIL,QQ_ACCT_ID,MMSG_ACCT_ID,MBLOG_ACCT_ID,DEAL_TIME) " +
				"VALUES (?,?,?,?,'',?,?,?,?,?,?,?,now())";
		Object [] values = {accountId,accPassword,nickName,accType,sexFlag,idCard,userPhone,userEmail,userQQ,userWechat,userWeibo};
		int count=this.getJdbcTemplate().update(insertSql,values);
		if (count>0) {
			return "1";
		} else {
			return "0";
		}
	}

}
