package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.RegistAccManager;

@Controller
public class RegistAccController {
	@Resource
	public RegistAccManager registAccManager;

	@RequestMapping(value="registAcc.do")
	public String registAcc(HttpServletRequest request, HttpServletResponse response){
		String jumpFlag = request.getParameter("jumpFlag");
		request.setAttribute("jumpFlag", jumpFlag);
		return "pgmag/registerAccount";
	}
	
	@RequestMapping(value="resetPassword.do")
	public String resetPassword(HttpServletRequest request, HttpServletResponse response){
		return "pgmag/resetPassword";
	}
	
	//提交注册账号信息
	@ResponseBody
	@RequestMapping("submitAcc.do")
	public String getArtTitle(HttpServletRequest request,HttpServletResponse response){
		String dataD = request.getParameter("data");
		String result="";
		return result;
	}
	
	//重置密码
	@ResponseBody
	@RequestMapping("resetPass.do")
	public String resetPass(HttpServletRequest request,HttpServletResponse response){
		String userAccount = request.getParameter("userAccount");
		String newPassword = request.getParameter("newPassword");
		String renewPass = request.getParameter("renewPass");
		String userId=request.getSession().getAttribute("userId")+"";
		String result=registAccManager.resetPass(userId,userAccount,newPassword);
		return result;
	}
	
	//验证帐号是否存在
	@ResponseBody
	@RequestMapping("verifyAccount.do")
	public String verifyAccount(HttpServletRequest request,HttpServletResponse response){
		String accountId = request.getParameter("accountId");
		String result=registAccManager.verifyAccount(accountId);
		return result;
	}
	
	//注册账号
	@ResponseBody
	@RequestMapping("submitRegist.do")
	public String submitRegist(HttpServletRequest request,HttpServletResponse response){
		String accountId = request.getParameter("accountId");
		String accPassword = request.getParameter("accPassword");
		String nickName = request.getParameter("nickName");
		String accType = request.getParameter("accType");
		String accountSex = request.getParameter("accountSex");
		String idCard = request.getParameter("idCard");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String userQQ = request.getParameter("userQQ");
		String userWechat = request.getParameter("userWechat");
		String userWeibo = request.getParameter("userWeibo");
		String jumpFlag = request.getParameter("jumpFlag");
		String result=registAccManager.submitRegist(accountId,accPassword,nickName,accType,accountSex,idCard,userPhone,userEmail,userQQ,userWechat,userWeibo,jumpFlag);
		return result;
	}
	
}
