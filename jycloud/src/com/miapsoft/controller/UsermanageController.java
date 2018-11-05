package com.miapsoft.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.UsermanageManager;

@Controller
public class UsermanageController {
	@Resource
	public UsermanageManager usermanageManager;
	@RequestMapping("backstageusermanage.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		return "jsp/backstageusermanage";
	}
	@RequestMapping("appusermanage.do")
	public String zhuanti(HttpServletRequest request, HttpServletResponse response){
		return "jsp/appusermanage";
	}
/*	@RequestMapping("appuseredit.do")
	public String zhuanti2(HttpServletRequest request, HttpServletResponse response){
		return "jsp/appuseredit";
	}*/
	@RequestMapping(value="backappedit.do")
	public String articlemain225(HttpServletRequest request, HttpServletResponse response){
		return "jsp/backappedit";
	}
	@RequestMapping(value="backappadd.do")
	public String articlemain226(HttpServletRequest request, HttpServletResponse response){
		return "jsp/backappadd";
	}
	@RequestMapping(value="appuseradd.do")
	public String articlemain227(HttpServletRequest request, HttpServletResponse response){
		return "jsp/appuseradd";
	}
	@RequestMapping(value="appuseredit.do")
	public String articlemain228(HttpServletRequest request, HttpServletResponse response){
		return "jsp/appuseredit";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="get.do")
	public String getLeftNav(HttpServletRequest request, HttpServletResponse response){
		JSONArray AreaSaturation = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("username","lisi");
		AreaSaturation.add(obj);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	//取后台所有用户信息
	@ResponseBody
	@RequestMapping(value="getallbackuser.do")
	public String getLeftNav2(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String userName = request.getParameter("userName");
		String roleId = request.getParameter("roleId");
		String curnum = request.getParameter("curnum");
		String limitcount = request.getParameter("limitcount");
		JSONArray AreaSaturation=usermanageManager.getAllUser(userName,roleId,curnum,limitcount);
		String pageNum = usermanageManager.getPageNumHT(userName,roleId,curnum,limitcount);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		obj2.put("userNum", pageNum);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="get3.do")
	public void getLeftNav3(HttpServletRequest request,HttpServletResponse response){
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		String close=request.getParameter("close");
		String flag=request.getParameter("flag");
		if(close==null){
			close="off";
		}
		usermanageManager.UpdatebackUser(phone, mail, close, flag);
		
	}
	@ResponseBody
	@RequestMapping(value="editappuser.do")
	public void getLeftNav31(HttpServletRequest request,HttpServletResponse response){
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		String QQ=request.getParameter("QQ");
		String VX=request.getParameter("VX");
		String WB=request.getParameter("WB");
		String flag=request.getParameter("flag");
		String close=request.getParameter("close");
		if(close==null){
			close="off";
		}
		usermanageManager.UpdateappUser(phone,mail,QQ,VX,WB,flag,close);
		
	}
	@ResponseBody
	@RequestMapping(value="insertbackuser.do")
	public void getLeftNav4(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String sfzzh=request.getParameter("sfzzh");
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		String QQ=request.getParameter("QQ");
		String VX=request.getParameter("VX");
		String WB=request.getParameter("WB");
		String sex=request.getParameter("sex");
		String rolename=request.getParameter("rolename");
		if(sex.equals("男")){
			sex="M";
		}else if(sex.equals("女")){
			sex="F";
		}else{
			sex="L";
		}
		usermanageManager.addbackUser(username,password,name,sfzzh,phone, mail, QQ, VX,WB,sex,rolename);
		
	}
	@ResponseBody
	@RequestMapping(value="getallbackuser2.do")
	public String getLeftNav3(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation=new JSONArray();
		obj.put("username", "ssssssss");
		AreaSaturation.add(obj);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="deletebackuser.do")
	public void getLeftNav4(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		String flag=request.getParameter("flag");
		usermanageManager.deletebackUser(flag);
	}
	@ResponseBody
	@RequestMapping(value="deleteappuser.do")
	public void getLeftNav44(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		String flag=request.getParameter("flag");
		usermanageManager.deleteappUser(flag);
	}
	@ResponseBody
	@RequestMapping(value="getselectbackuser.do")
	public String getLeft5(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type","text/html;charset=gbk");
		String condition=request.getParameter("condition");
		condition=URLDecoder.decode(condition,"UTF-8");
		String roleId=request.getParameter("roleId");
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation=usermanageManager.getAllSelectUser(condition,roleId);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getselectappuser.do")
	public String getLeft51(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type","text/html;charset=gbk");
		String sex=request.getParameter("sex");
		sex=URLDecoder.decode(sex,"UTF-8");
		String username=request.getParameter("username");
		String phone=request.getParameter("phone");
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation=usermanageManager.getAllSelectappUser(username,phone,sex);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	//取APP所有用户信息
	@ResponseBody
	@RequestMapping(value="getallappuser.do")
	public String getallappuser(HttpServletRequest request,HttpServletResponse response ){
		String userName = request.getParameter("userName");
		String phoneNum = request.getParameter("phoneNum");
		String curnum = request.getParameter("curnum");
		String limitcount = request.getParameter("limitcount");
		String userSex = request.getParameter("userSex");
		JSONArray AreaSaturation=usermanageManager.getAllAppUser(userName,phoneNum,curnum,limitcount,userSex);
		String pageNum = usermanageManager.getPageNumAPP(userName,phoneNum,userSex);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		obj2.put("userNum", pageNum);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="insertappuser.do")
	public void getLeftNav8(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String sfzzh=request.getParameter("sfzzh");
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		String QQ=request.getParameter("QQ");
		String VX=request.getParameter("VX");
		String WB=request.getParameter("WB");
		String sex=request.getParameter("sex");
		if(sex.equals("男")){
			sex="M";
		}else if(sex.equals("女")){
			sex="F";
		}else{
			sex="L";
		}
		usermanageManager.addappUser(username,password,name,sfzzh,phone, mail, QQ, VX,WB,sex);
		
	}
	@ResponseBody
	@RequestMapping(value="rolefenpei22.do")
	public void getLeftNav622(HttpServletRequest request,HttpServletResponse response){
		String fatherid=request.getParameter("fatherid");
		usermanageManager.rolefenpeifirst(fatherid);
	}
	@ResponseBody
	@RequestMapping(value="rolefenpei222.do")
	public void getLeftNav6(HttpServletRequest request,HttpServletResponse response){
		String flag=request.getParameter("id");
		String fatherid=request.getParameter("fatherid");
		usermanageManager.rolefenpei(fatherid,flag);
	}
	//批量删除用户
	@ResponseBody
	@RequestMapping(value="deleteselectbackuser.do")
	public void deleteselectbackuser(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		String flag=request.getParameter("flag");
		usermanageManager.deletebackUser(flag);
	}
	@ResponseBody
	@RequestMapping(value="deleteselectappuser.do")
	public void getLeftNav422(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		String flag=request.getParameter("flag");
		usermanageManager.deleteappUser(flag);
	}
	
}
