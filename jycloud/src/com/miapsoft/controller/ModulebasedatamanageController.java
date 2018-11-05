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

import com.miapsoft.manager.ModulemanageManager;

@Controller
public class ModulebasedatamanageController {
	@Resource
	public ModulemanageManager modulemanageManager;
	@RequestMapping(value="basedatamanage.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		return "sysmag/basedatamanage";
		
	}
	@RequestMapping(value="modulefenpei.do")
	public String articlemain2(HttpServletRequest request, HttpServletResponse response){
		return "jsp/modulefenpei";
	}
	@RequestMapping(value="moduleweifenpei.do")
	public String articlemain22(HttpServletRequest request, HttpServletResponse response){
		return "jsp/moduleweifenpei";
	}
	//获取所有的模块信息
	@ResponseBody
	@RequestMapping(value="getallmodule.do")
	public String getLeftNav4(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		String moduleid = request.getParameter("moduleid");
		String parentMId = request.getParameter("parentMId");
		String curnum = request.getParameter("curnum");
		String limitcount = request.getParameter("limitcount");
		String modulename = request.getParameter("modulename");
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation=modulemanageManager.getAllModule(moduleid,parentMId,curnum,limitcount,modulename);
		String pageNum = modulemanageManager.getPageNum(moduleid,parentMId,modulename);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		obj2.put("userNum", pageNum);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getallfenpei1module.do")
	public String getLeftNav41(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String filepass=request.getParameter("filepass");
		
		JSONArray AreaSaturation=modulemanageManager.getAllfenpeiModule();
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="editmodule.do")
	public void getLeftNav5(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String modulename=request.getParameter("modulename");
		String modulelink=request.getParameter("modulelink");
		String flag=request.getParameter("flag");
		modulemanageManager.editModule(modulename,modulelink,flag);
	/*	return obj2.toString();*/
	}
	//删除模块信息
	@ResponseBody
	@RequestMapping(value="deletemodule.do")
	public void getLeftNav6(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String flag=request.getParameter("flag");
		modulemanageManager.deleteModule(flag);

	}
	@ResponseBody
	@RequestMapping(value="insertmodule.do")
	public void getLeftNav7(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String moduleid=request.getParameter("moduleid");
		String fathermoduleid=request.getParameter("fathermoduleid");
		String modulename=request.getParameter("modulename");
		String modulelevel=request.getParameter("modulelevel");
		String modulelink=request.getParameter("modulelink");
		String ismenu=request.getParameter("ismenu");
		String moduleby=request.getParameter("moduleby");
		modulemanageManager.addModule(moduleid,fathermoduleid,modulename,modulelevel,modulelink,ismenu,moduleby);
	}
	@ResponseBody
	@RequestMapping(value="selectmodule.do")
	public String getLeftNav8(HttpServletRequest request,HttpServletResponse response,String username,String limit ) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type","text/html;charset=gbk");
		JSONObject obj = new JSONObject();
		String moduleid=request.getParameter("moduleid");
		String fathermoduleid=request.getParameter("fathermoduleid");
		String modulename=request.getParameter("modulename");
		modulename=URLDecoder.decode(modulename,"UTF-8");
		JSONArray AreaSaturation=modulemanageManager.selectModule(moduleid,fathermoduleid,modulename);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getfenpeimodule.do")
	public String getLeftNav45(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String filepass=request.getParameter("filepass");
		JSONArray AreaSaturation=modulemanageManager.getAllfenpeiModule(filepass);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="getweifenpeimodule.do")
	public String getLeftNav456(HttpServletRequest request,HttpServletResponse response,String username,String limit ){
		JSONObject obj = new JSONObject();
		String filepass=request.getParameter("filepass");
		JSONArray AreaSaturation=modulemanageManager.getAllweifenpeiModule(filepass);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", AreaSaturation.size());
		obj2.put("data", AreaSaturation);
		return obj2.toString();
	}
	@ResponseBody
	@RequestMapping(value="addmodulefenpei.do")
	public void getLeftNav6(HttpServletRequest request,HttpServletResponse response){
		String flag=request.getParameter("id");
		String fatherid=request.getParameter("fatherid");
		modulemanageManager.modulefenpei(fatherid,flag);
	
	}
	@ResponseBody
	@RequestMapping(value="deletemodulefenpei.do")
	public void getLeftNav61(HttpServletRequest request,HttpServletResponse response){
		String flag=request.getParameter("id");
		String fatherid=request.getParameter("fatherid");
		modulemanageManager.jiechumodulefenpei(fatherid,flag);
	
	}
	@ResponseBody
	@RequestMapping(value="deleteselectmodule.do")
	public void getLeftNav611(HttpServletRequest request,HttpServletResponse response){
		String moduleid=request.getParameter("moduleid");;
		modulemanageManager.deleteselectmodule(moduleid);
	
	}
	@ResponseBody
	@RequestMapping(value="modulefenpeipowerfirst.do")
	public void getLeftNav612(HttpServletRequest request,HttpServletResponse response){
		String fatherid=request.getParameter("fatherid");
		modulemanageManager.deleteselectmodule(fatherid);
	
	}
	@ResponseBody
	@RequestMapping(value="modulefenpeipower.do")
	public void getLeftNav6122(HttpServletRequest request,HttpServletResponse response){
		String moduleid=request.getParameter("id");
		String fatherid=request.getParameter("fatherid");
		modulemanageManager.insertfenpeimodule(fatherid,moduleid);
	
	}
}
