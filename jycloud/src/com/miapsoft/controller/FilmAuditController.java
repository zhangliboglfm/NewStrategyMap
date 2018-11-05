/*** <p>Title: </p>
* <p>Description: 样片管理弹出框</p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 谢琪
* @date 2018-9-6
*/
package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PhotographerMainManager;

@Controller
public class FilmAuditController {

	
	
	@RequestMapping("filmaudit.do")
	public String photographeraudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/filmaudit";
	}
	
	@RequestMapping("deletesourcesaudit.do")
	public String deletesourcesaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/deletesourcesaudit";
	}
	
	@RequestMapping("reviewfilmaudit.do")
	public String reviewfilmaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/reviewfilmaudit";
	}
	
	@RequestMapping("reviewdigitalaudit.do")
	public String reviewdigitalaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/reviewdigitalaudit";
	}
	
	@RequestMapping("reviewsampleaudit.do")
	public String reviewsampleaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/reviewsampleaudit";
	}
	
	@RequestMapping("rejectreason.do")
	public String rejectreason(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/rejectreason";
	}
	
	@RequestMapping("regersses.do")
	public String regresses(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/regersses";
	}
	
	@RequestMapping("filmmore.do")
	public String filmmore(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("id", request.getParameter("id"));
		return "filter/filmmore";
	}
}
