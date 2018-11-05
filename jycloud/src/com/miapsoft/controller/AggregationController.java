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

import com.miapsoft.manager.AggregationManager;
import com.miapsoft.util.FileUtil;

@Controller
public class AggregationController {
	@Resource
	public AggregationManager aggregationManager;
	@RequestMapping("aggregation.do")
	public String zhuanti(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/aggregation";
	}
	@RequestMapping("newaggregation.do")
	public String zhuanti2(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/newaggregation";
	}
	@RequestMapping("editaggregation.do")
	public String zhuanti3(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/editaggregation";
	}
	@RequestMapping("addaggregationmember.do")
	public String addaggregationmember(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/addaggregationmember";
	}
	
	@RequestMapping("newaggregationmember.do")
	public String newaggregationmember(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/newaggregationmember";
	}
	
	@RequestMapping("editaggregationmember.do")
	public String editaggregationmember(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/editaggregationmember";
	}
	@RequestMapping("aggregationaudit.do")
	public String foraggregationadult(HttpServletRequest request, HttpServletResponse response){
		String cgerId = request.getParameter("cgId");
		request.setAttribute("cgerId", cgerId);
		return "cgmag/aggregationaudit";
	}
	@RequestMapping("watchaggregation.do")
	public String watchaggregationadult(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/watchaggregation";
	}
	
	@ResponseBody
	@RequestMapping("getaggregation.do")
	public String getaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String cgid =request.getParameter("cgId");
		JSONArray result=aggregationManager.getaggregationInfo(cgid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getauditaggregation.do")
	public String getauditaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String cgid =request.getParameter("cgId");
		JSONArray result=aggregationManager.getauditaggregationInfo(cgid);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("geteditaggregation.do")
	public String geteditaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String compid =request.getParameter("compid");
		JSONArray result=aggregationManager.geteditaggregationInfo(compid);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("addaggregation.do")
	public String addaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String othername=request.getParameter("othername");
		String allname=request.getParameter("allname");
		String desc=request.getParameter("desc");
		String agid=request.getParameter("cgId");
		String result=aggregationManager.addaggregation(agid,name, othername, allname, desc);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("updateaggregation.do")
	public String editaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String compid =request.getParameter("compid");
		String name=request.getParameter("name");
		String othername=request.getParameter("othername");
		String allname=request.getParameter("allname");
		String desc=request.getParameter("desc");
		if(aggregationManager.editaggregationInfo(compid,name,othername,allname,desc)){
			return "Success";
		}else{
			return "fail";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("searchaggregation.do")
	public String searchaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String name =request.getParameter("name");
		String cgid =request.getParameter("cgId");
		JSONArray result=aggregationManager.searchaggregationInfo(cgid,name);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("searchauditaggregation.do")
	public String searchauditaggregation(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");		
		String name =request.getParameter("name");
		String cgid =request.getParameter("cgId");
		JSONArray result=aggregationManager.searchauditaggregationInfo(cgid,name);
		return result.toString();
	}
/*	*//**
	 * 上传书法家合集图片
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 *//*
	@ResponseBody
	@RequestMapping("addaggregationmember2.do")
	public String addaggregationmember2(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		JSONObject result =new JSONObject();
		ServerFilePath sfp=new ServerFilePath();
	    String IMGPATH=sfp.getPhotogdir();
		String name =request.getParameter("name");
		String sondata =request.getParameter("sondata");
		name=URLDecoder.decode(URLDecoder.decode(name, "UTF-8"), "UTF-8");
		String agimage_id=aggregationManager.getworkId(name);
		if(agimage_id!=null&&agimage_id!=""){
			if(aggregationManager.updateworkId(agimage_id,sondata)){
				return "SUCCESS";
		}else{
				return "FAIL";
		}
		}else{
			return "作品不存在";
		}
	}*/
	
	
	/*得到作品标签信息*/
	@ResponseBody
	@RequestMapping("getaggregationagdata.do")
	public String getachievementagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		String cgId =request.getParameter("cgId");
		JSONArray result=aggregationManager.getaggregationagdata(compid,cgId);
		return result.toString();
	}
	/*搜索作品标签信息*/
	@ResponseBody
	@RequestMapping("searchaggregationagdata.do")
	public String searchaggregationagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		String cgId =request.getParameter("cgId");
		String cgname =request.getParameter("cgname");
		JSONArray result=aggregationManager.searchaggregationagdata(compid,cgId,cgname);
		return result.toString();
	}	
	
	
	/*得到新增合集标签信息*/
	@ResponseBody
	@RequestMapping("getnewaggregationagdata.do")
	public String getnewaggregationagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		/*String compid =request.getParameter("compid");*/
		String cgId =request.getParameter("cgId");
		JSONArray result=aggregationManager.getnewaggregationagdata(cgId);
		return result.toString();
	}
	/*得到搜索合集标签信息*/
	@ResponseBody
	@RequestMapping("searchnewaggregationagdata.do")
	public String searchnewaggregationagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String cgname =request.getParameter("cgname");
		String cgId =request.getParameter("cgId");
		JSONArray result=aggregationManager.searchnewaggregationagdata(cgId,cgname);
		return result.toString();
	}	
	/*添加合集成员*/
	@ResponseBody
	@RequestMapping("addaggregationmemberdata.do")
	public String addachievementmemberdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		String worksid =request.getParameter("worksid");
		String worksname =request.getParameter("worksname");
		String result=aggregationManager.addaggregationmemberdata(compid,worksid,worksname);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("deleteaggregationInfo.do")
	public String deleteaggregationInfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		if(aggregationManager.deleteaggregationInfo(compid)){
			return "删除成功";
		}else{
			return "删除失败";
		}
		
	}
	
	/*得到作品封面图片信息*/
	@ResponseBody
	@RequestMapping("getaggregationImageInfo.do")
	public String getaggregationImageInfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		JSONArray result=aggregationManager.getaggregationImageInfo(compid);
		return result.toString();
	}
	
	/*新增合集中得到预添加作品封面图片信息*/
	@ResponseBody
	@RequestMapping("getInsertImageInfo.do")
	public String getInsertImageInfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String worksid =request.getParameter("worksid");
		JSONArray result=aggregationManager.getInsertImageInfo(worksid);
		return result.toString();
	}
	
	/*新增合集中中预添合集作品关系信息*/
	@ResponseBody
	@RequestMapping("newaggregationImageInfo.do")
	public String newaggregationImageInfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		String worksname =request.getParameter("worksname");
		String worksid =request.getParameter("worksid");
		String result=aggregationManager.newaggregationImageInfo(compid,worksid);
		return result.toString();
	}
	
	/*编辑合集中中预添合集作品关系信息*/
	@ResponseBody
	@RequestMapping("editaggregationImageInfo.do")
	public String EditaggregationImageInfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		String worksname =request.getParameter("worksname");
		String worksid =request.getParameter("worksid");
		String result=aggregationManager.editaggregationImageInfo(compid,worksid);
		return result.toString();
	}
	
	/*编辑合集中修改合集作品关系信息先清理合集信息*/
	@ResponseBody
	@RequestMapping("editclear.do")
	public String editclear(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String compid =request.getParameter("compid");
		if(aggregationManager.clearCOMP_workrelation(compid)){
			return "SUCCESS";
		}else{
			return "FAIL";
		}

	}
	
	
	@ResponseBody
	@RequestMapping("deleteaggregationmember.do")
	public String deletemember(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String worksid =request.getParameter("worksid");
		String compid =request.getParameter("compid");
		if(aggregationManager.deleteaggregationmember(compid,worksid)){
			return "成功";
		}else{
			return "失败";
		}
		
	}
}
