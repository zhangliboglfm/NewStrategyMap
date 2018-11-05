package com.miapsoft.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.AchievementgroupingManager;
import com.miapsoft.util.FileUtil;

@Controller
public class AchievementgroupingController {
	@Resource
	public AchievementgroupingManager achievementgroupingManager;
	@RequestMapping("achievementgrouping.do")
	public String zhuanti(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/achievementgrouping";
	}
	@RequestMapping("achievementgroupingadult.do")
	public String achievementgroupingadult(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/achievementgroupingadult";
	}
	@RequestMapping("achievementgroupingregresses.do")
	public String achievementgroupingregresses(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/achievementgroupingregresses";
	}	
	@RequestMapping("addagachievement.do")
	public String zhuanti2(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/addagachievement";
	}
	@RequestMapping("addachievementmember.do")
	public String zhuanti3(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/addachievementmember";
	}
	@RequestMapping("regresses2.do")
	public String regresses2(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/regresses";
	}
	@RequestMapping("achievementadult.do")
	public String achievementadult(HttpServletRequest request, HttpServletResponse response){
		return "cgmag/achievementadult";
	}	
	
	@ResponseBody
	@RequestMapping("getAchievementinfo.do")
	public String getAchievementinfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray result=achievementgroupingManager.getAchievementInfo();
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getWaitAchievementinfo.do")
	public String getWaitAchievementinfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String status =request.getParameter("status");
		JSONArray result=achievementgroupingManager.getWaitAchievementinfo(status);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getAchievementaginfo.do")
	public String getAchievementaginfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String info =request.getParameter("data");
		JSONArray result=achievementgroupingManager.getAchievementagInfo(info);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("searchAchievementaginfo.do")
	public String searAchievementaginfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String info =request.getParameter("data");
		JSONArray result=achievementgroupingManager.searchAchievementagInfo(info);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("searchWaitAchievementaginfo.do")
	public String searchWaitAchievementaginfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String info =request.getParameter("data");
		String status =request.getParameter("status");
		JSONArray result=achievementgroupingManager.searchWaitAchievementagInfo(info,status);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("searchAchievementagimageinfo.do")
	public String searAchievementagimageinfo(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String info =request.getParameter("data");
		JSONArray result=achievementgroupingManager.searchAchievementagimageInfo(info);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("addAchievementag.do")
	public String addAchievementag(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String name =request.getParameter("name");
		String othername =request.getParameter("othername");
		String describe =request.getParameter("desc");
		String result=achievementgroupingManager.addAchievementag(name,othername,describe);
		return result.toString();
	}
	
	///获取书法家头像
	@ResponseBody
	@RequestMapping(value="selachievementimg.do")
	public void selPhotogHead(HttpServletRequest request,HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		String filepath= ServerFilePath.getCalligdir()+File.separator+fileName;
		String allowpath=ServerFilePath.getCalligdir();
		FileUtil.decryptDownload(fileName, filepath,allowpath, response, request);
	}
	
	@ResponseBody
	@RequestMapping("deletemember.do")
	public String deletemember(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String cgerid =request.getParameter("cgerid");
		String agid =request.getParameter("agid");
		if(achievementgroupingManager.deletemember(agid,cgerid)){
			return "Success";
		}else{
			return "Fail";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("deleteachievement.do")
	public String deleteachievement(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String agid =request.getParameter("agid");
		if(achievementgroupingManager.deleteachievement(agid)){
			return "Success";
		}else{
			return "Fail";
		}
		
	}
	/*审核
	 * 
	 *
	 * */
	/*成就审核通过*/
	@ResponseBody
	@RequestMapping("adoptachievement.do")
	public String adoptachievement(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String agid =request.getParameter("agid");
		String flag =request.getParameter("flag");
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId")+"";
		if(achievementgroupingManager.adoptachievement(userId,agid,flag)){
			return "Success";
		}else{
			return "Fail";
		}
		
	}
	/*成就审核驳回*/
	@ResponseBody
	@RequestMapping("rejectachievement.do")
	public String rejectachievement(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId")+"";
		String agid =request.getParameter("agid");
		String flag =request.getParameter("flag");
		String reason =request.getParameter("reason");
		if(achievementgroupingManager.rejectachievement(userId,agid,reason,flag)){
			return "Success";
		}else{
			return "Fail";
		}
	}
	
	/*成就审核回退*/
	@ResponseBody
	@RequestMapping("regresses.do")
	public String regresses(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId")+"";
		String agid =request.getParameter("agid");
		String flag =request.getParameter("flag");
		String reason =request.getParameter("reason");
		if(achievementgroupingManager.regresses(userId,agid,reason,flag)){
			return "Success";
		}else{
			return "Fail";
		}
	}
	/*得到书法家标签信息*/
	@ResponseBody
	@RequestMapping("getachievementagdata.do")
	public String getachievementagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray result=achievementgroupingManager.getachievementagdata();
		return result.toString();
	}
	/*搜索书法家标签信息*/
	@ResponseBody
	@RequestMapping("getsearchachievementagdata.do")
	public String getsearchachievementagdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String cgname =request.getParameter("cgname");
		JSONArray result=achievementgroupingManager.getsearchachievementagdata(cgname);
		return result.toString();
	}	
	/*添加成就成员*/
	@ResponseBody
	@RequestMapping("addachievementmemberdata.do")
	public String addachievementmemberdata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String agid =request.getParameter("agid");
		String cgerid =request.getParameter("cgerid");
		String cgername =request.getParameter("cgername");
		String result=achievementgroupingManager.addachievementmemberdata(agid,cgerid,cgername);
		return result.toString();
	}
	/*添加驳回原因*/
	@ResponseBody
	@RequestMapping("getauditreasondata.do")
	public String getauditreasondata(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String type=request.getParameter("type");
		JSONArray result=achievementgroupingManager.getauditreasondata(type);
		return result.toString();
	}
}
