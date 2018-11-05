package com.miapsoft.controller;

import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.miapsoft.manager.LoginManager;
import com.miapsoft.util.CodeUtil;
import com.miapsoft.util.MD5Util;

@Controller
public class LoginController {
	@Resource
	public LoginManager loginManager;

	public LoginManager getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	@RequestMapping(value="login.do")
	public String Login(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("yzcount",0);
		session.setAttribute("loginFlag", "fail");
		return "login";
	}
	@RequestMapping(value="userform.do")
	public String Login2(HttpServletRequest request, HttpServletResponse response){

		return "jsp/userform";
	}
	@RequestMapping(value="error.do")
	public String error(HttpServletRequest request, HttpServletResponse response){

		return "errors/error";
	}
	@ResponseBody
	@RequestMapping(value="getyz.do")
	public void Login3(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session =(HttpSession) request.getSession();
		Map<String,Object> map = CodeUtil.generateCodeAndPic();
		ImageIO.write((RenderedImage) map.get("codePic"), "jpeg",response.getOutputStream());
		System.out.println("验证码的值为："+map.get("code"));
		session.setAttribute("yzm",map.get("code"));
	}

	@ResponseBody
	@RequestMapping(value="loginyz.do")
	public String Login4(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session =request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		/*String yanzheng=request.getParameter("yanzhengma");*/
		/*String yanzheng=request.getParameter("vercode");*/
		password=MD5Util.newMD5(password).substring(0,128);
		int flag = loginManager.Login2(username,password);
		if(flag==1){
			JSONObject userjson=loginManager.Login(username);
			session.setAttribute("userId", userjson.getString("USER_ACCT_ID"));
			session.setAttribute("userName", userjson.getString("USER_NAME"));
			return "ok";
		}
		return "false";	
	}
	@ResponseBody
	@RequestMapping(value="yz.do")
	public String Login5(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session =request.getSession();
		String yanzheng=request.getParameter("yanzhengma");
		Object o1=session.getAttribute("yzm");
		String yzm=o1.toString();
		if((yzm.toUpperCase()).equals(yanzheng.toUpperCase())){
			return "ok";
		}
		return "false";
	}
	@ResponseBody
	@RequestMapping(value="yz2.do")
	public String Login6(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session =request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		password=MD5Util.newMD5(password).substring(0,128);
		int flag = loginManager.Login2(username,password);
		if(flag==1){
			JSONObject userjson=loginManager.Login(username);
			session.setAttribute("userId", userjson.getString("USER_ACCT_ID"));
			session.setAttribute("userName", userjson.getString("USER_NAME"));
			session.setAttribute("loginFlag", "success");
			return "ok";
			//成功
		} else {
			session.setAttribute("loginFlag", "fail");
			return "false";
		}
			
	}

}
