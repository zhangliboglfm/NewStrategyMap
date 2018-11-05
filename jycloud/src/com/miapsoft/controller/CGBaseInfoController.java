/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-28
*/
package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.CGBaseInfoManager;
import com.miapsoft.util.FileUtil;

@Controller
public class CGBaseInfoController {
	
	@Resource
	private CGBaseInfoManager cgBaseInfoManager;
	
	/**
	 * 书法家管理主页
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	@RequestMapping("cgmaininfo.do")
	public String cgmaininfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "cgmag/cgmaininfo";
	}
	//书法家审核主页
	@RequestMapping("cgmaininfoAudit.do")
	public String cgmaininfoAudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "cgmag/cgmaininfoAudit";
	}
	
	/**
	 * 书法家管理列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	@RequestMapping("cginfolist.do")
	public String cginfolist(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "cgmag/cginfolist";
	}
	//书法家审核列表
	@RequestMapping("cginfolistAudit.do")
	public String cginfolistAudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "cgmag/cginfolistAudit";
	}
	
	
	/**
	 * 获取全部书法家
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selcalliglist.do")
	public String selcalliglist(HttpServletRequest request,HttpServletResponse response) {
		
		String importDate=request.getParameter("importDate");
		String pgName=request.getParameter("cgName");
		String sortColumn=request.getParameter("sortColumn");
		String sortType=request.getParameter("sortType");
		String auditStatus=request.getParameter("auditStatus");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		JSONObject result=cgBaseInfoManager.selectCalligList(importDate,pgName,sortColumn,sortType,auditStatus,start,end);
		return result.toString();
	}
	/**
	 * 获取审核部分书法家
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selcalliglist2.do")
	public String selcalliglist2(HttpServletRequest request,HttpServletResponse response) {
		
		String importDate=request.getParameter("importDate");
		String pgName=request.getParameter("cgName");
		String sortColumn=request.getParameter("sortColumn");
		String sortType=request.getParameter("sortType");
		String auditStatus=request.getParameter("auditStatus");
		if (pgName==null||"".equals(pgName)) {
			pgName="%%";
		}else{
			pgName="%"+pgName+"%";
		}
		
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");
		
		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";
		
		JSONObject result=cgBaseInfoManager.selectCalligList2(importDate,pgName,sortColumn,sortType,auditStatus,start,end);
		return result.toString();
	}
	/**
	 * 获取书法家头像
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="selcallighead.do")
	public void selCalligHead(HttpServletRequest request,HttpServletResponse response) {
		String cgid = request.getParameter("cgid");//书法家ID
		String fileName = cgBaseInfoManager.selectCalligPicInfo(cgid,"1");
		String filepath = ServerFilePath.getCalligdir()+"/"+fileName;
		String allowpath = ServerFilePath.getCalligdir();
		FileUtil.decryptDownload2(fileName, filepath,allowpath, response, request);
	}
}
