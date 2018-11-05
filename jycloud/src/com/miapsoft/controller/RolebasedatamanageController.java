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

import com.miapsoft.manager.RolemanageManager;

@Controller
public class RolebasedatamanageController {
	@Resource
	public RolemanageManager rolemanageManager;
	@RequestMapping(value="rolebasedatamanage.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		return "sysmag/rolebasedatamanage";
		//return "jsp/rolebasedatamanage";
	}
	@RequestMapping(value="rolefenpei.do")
	public String articlemain2(HttpServletRequest request, HttpServletResponse response){
		return "jsp/rolefenpei";
	}
	@RequestMapping(value="rolefenpei_module.do")
	public String articlemain3(HttpServletRequest request, HttpServletResponse response){
		return "jsp/rolefenpei_module";
	}
	//查所有角色信息
	@ResponseBody
	@RequestMapping(value="getallrole.do")
	public String getallrole(HttpServletRequest request,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String curnum = request.getParameter("curnum");
		String limitcount = request.getParameter("limitcount");
		JSONArray AreaSaturation=rolemanageManager.getAllRole(roleId,roleName,curnum,limitcount);
		String roleTNum =  rolemanageManager.getRoleTNum(roleId,roleName);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		obj2.put("userNum", roleTNum);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getinitbackrole.do")
	public String getAllBackRole(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation=rolemanageManager.getAllBackRole();
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="editrole.do")
	public void getLeftNav5(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String describe=request.getParameter("describe");
		String flag=request.getParameter("flag");
		rolemanageManager.editRole(describe,flag);
		/*	return obj2.toString();*/
	}
	//删除角色信息
	@ResponseBody
	@RequestMapping(value="deleterole.do")
	public void getLeftNav6(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String flag=request.getParameter("flag");
		rolemanageManager.deleteRole(flag);

	}
	@ResponseBody
	@RequestMapping(value="addrole.do")
	public void getLeftNav7(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String roleid=request.getParameter("roleid");
		String rolename=request.getParameter("rolename");
		String roledescribe=request.getParameter("roledescribe");
		rolemanageManager.addRole(roleid,rolename,roledescribe);

	}
	@ResponseBody
	@RequestMapping(value="selectrole.do")
	public String getLeftNav8(HttpServletRequest request,HttpServletResponse response,String username,String limit ) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type","text/html;charset=gbk");
		JSONObject obj = new JSONObject();
		String roleid=request.getParameter("id");
		String rolename=request.getParameter("rolename");
		rolename=URLDecoder.decode(rolename,"UTF-8");
		JSONArray AreaSaturation=rolemanageManager.selectRole(roleid,rolename);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="deleteselectrole.do")
	public void getLeftNav71(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String roleid=request.getParameter("roleid");
		rolemanageManager.deleteRole(roleid);

	}
	@ResponseBody
	@RequestMapping(value="getfenpeirole.do")
	public String getLeftNav41(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String filepass=request.getParameter("filepass");
		JSONArray AreaSaturation=rolemanageManager.getfenpeiRole(filepass);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getweifenpeirole.do")
	public String getLeftNav42(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String filepass=request.getParameter("filepass");
		JSONArray AreaSaturation=rolemanageManager.getweifenpeiRole(filepass);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
}
