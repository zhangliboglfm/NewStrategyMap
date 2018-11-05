/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
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
public class PgAuditController {

	@Resource
	private PhotographerMainManager pgmanager;
	
	@RequestMapping("pgaudit.do")
	public String photographeraudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pId", request.getParameter("pId"));
		return "pgmag/pgaudit";
	}
	
	/**
	 * 获取审核码表信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getallauditstatus.do")
	public String getallauditstatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = pgmanager.getAllAuditStatus();
		return result.toString();
	}
	
	/**
	 * 保存摄影家审核状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveauditstatus.do")
	public String saveauditstatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		String pgId = request.getParameter("pgId");
		String auditStatusId = request.getParameter("auditStatusId");
		String auditDesc = request.getParameter("auditDesc");
		Object userId = request.getSession().getAttribute("userId");
		if(userId!=null){
			int resint=2;
			try {
				resint = pgmanager.saveAuditStatus(pgId, auditStatusId,auditDesc,userId.toString());
			} catch (Exception e) {
				result.put("msg","excption");
				return result.toString();
			}
			if(resint == 1){
				result.put("msg","success");
			}else if(resint == 0){
				result.put("msg","null");
			}else{
				result.put("msg","fail");
			}
		}else{
			result.put("msg","nologin");
		}
		return result.toString();
	}
}
