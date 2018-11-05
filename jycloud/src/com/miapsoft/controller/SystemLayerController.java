/*** <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: 精益有容（北京）科技有限公司</p> 
 * @author 白少华
 * @date 2018-8-9
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

import com.miapsoft.manager.SystemLayerManager;

@Controller
public class SystemLayerController {

	@Resource
	private SystemLayerManager sysLayerManager;
	//-----------------------------------------------------------用户基本信息部分----------------------------------------------------------
	//编辑用户基本信息
	@RequestMapping("editBaseInfo.do")
	public String pgstyle(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String accUserId = request.getParameter("accUserId");
		request.setAttribute("accUserId", accUserId);
		System.out.println(1231123);
		return "sysmag/editBaseInfo";
	}
	//查现在的基本信息并展示
	@ResponseBody
	@RequestMapping("searchBaseInfo.do")
	public String searchBaseInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accUserId = request.getParameter("accUserId");
		JSONObject result = sysLayerManager.searchBaseInfo(accUserId);
		return result.toString();
	}
	//保存新内容
	@ResponseBody
	@RequestMapping("reupBaseInfo.do")
	public String reupBaseInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accountId = request.getParameter("accountId");
		String nickName = request.getParameter("nickName");
		String accType = request.getParameter("accType");
		String accountSex = request.getParameter("accountSex");
		String idCard = request.getParameter("idCard");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String userQQ = request.getParameter("userQQ");
		String userWechat = request.getParameter("userWechat");
		String userWeibo = request.getParameter("userWeibo");
		String result = sysLayerManager.reupBaseInfo(accountId,nickName,accType,accountSex,idCard,userPhone,userEmail,userQQ,userWechat,userWeibo);
		return result;
	}
	//用户重新赋予角色部分
	//重新给用户角色
	@RequestMapping("allotRole.do")
	public String allotRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String accUserId = request.getParameter("accUserId");
		request.setAttribute("accUserId", accUserId);
		return "sysmag/allotRole";
	}
	//查现在用户所有的角色并查处所有的角色
	@ResponseBody
	@RequestMapping("searchUserRole.do")
	public String searchUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accUserId = request.getParameter("accUserId");
		JSONObject result = sysLayerManager.searchUserRole(accUserId);
		return result.toString();
	}
	//查角色描述
	@ResponseBody
	@RequestMapping("searchRoleDesc.do")
	public String searchRoleDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		JSONObject result = sysLayerManager.searchRoleDesc(roleId);
		return result.toString();
	}
	//保存用户角色信息
	@ResponseBody
	@RequestMapping("saveUserRole.do")
	public String saveUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userRole = request.getParameter("userRole");
		String accUserId = request.getParameter("accUserId");
		String result = sysLayerManager.saveUserRole(userRole,accUserId);
		return result;
	}
	//-----------------------------------------------------------模块管理部分----------------------------------------------------------
	//添加、修改模块基本信息
	@RequestMapping("editMoudle.do")
	public String editMoudle(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String mouldeId = request.getParameter("mouldeId");
		String editFlag = request.getParameter("editFlag");
		request.setAttribute("mouldeId", mouldeId);
		request.setAttribute("editFlag", editFlag);
		return "sysmag/editMoudle";
	}
	//查模块基本信息
	@ResponseBody
	@RequestMapping("searchMoudleInfo.do")
	public String searchMoudleInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String mouldeId = request.getParameter("mouldeId");
		JSONObject result = sysLayerManager.searchMoudleInfo(mouldeId);
		return result.toString();
	}
	//新加或更改模块信息
	@ResponseBody
	@RequestMapping("upOrNewMoudle.do")
	public String upOrNewMoudle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String moduleId = request.getParameter("moduleId");
		String moduleName = request.getParameter("moduleName");
		String parentMId = request.getParameter("parentMId");
		String moduleLvl = request.getParameter("moduleLvl");
		String moduleType = request.getParameter("moduleType");
		String moduleOrder = request.getParameter("moduleOrder");
		String moduleURL = request.getParameter("moduleURL");
		String changeFlag = request.getParameter("changeFlag");
		String result = sysLayerManager.upOrNewMoudle(moduleId,moduleName,parentMId,moduleLvl,moduleType,moduleOrder,moduleURL,changeFlag);
		return result;
	}
	//-----------------------------------------------------------角色管理部分----------------------------------------------------------
	//添加、修改模块基本信息
	@RequestMapping("editRoleInfo.do")
	public String editRoleInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String roleId = request.getParameter("roleId");
		//editFlag 1-新加,2-编辑
		String editFlag = request.getParameter("editFlag");
		//String userId = request.getSession().getAttribute("userId")+"";
		request.setAttribute("roleId", roleId);
		request.setAttribute("editFlag", editFlag);
		return "sysmag/editRoleInfo";
	}
	//查模块基本信息
	@ResponseBody
	@RequestMapping("searchRoleInfo.do")
	public String searchRoleInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		JSONObject result = sysLayerManager.searchRoleInfo(roleId);
		return result.toString();
	}
	//新加或更改角色信息
	@ResponseBody
	@RequestMapping("upOrNewRole.do")
	public String upOrNewRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String changeFlag = request.getParameter("changeFlag");
		String userId = request.getSession().getAttribute("userId")+"";
		String result = sysLayerManager.upOrNewRole(roleId,roleName,roleDesc,changeFlag,userId);
		return result;
	}
	//查角色ID是否可用
	@ResponseBody
	@RequestMapping("checkRoleId.do")
	public String checkRoleId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		String result = sysLayerManager.checkRoleId(roleId);
		return result;
	}
	//角色管理模块的权限分配
	//添加、修改模块基本信息
	@RequestMapping("powerAllot.do")
	public String powerAllot(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		return "sysmag/powerAllot";
	}
	//生成模块树结构
	@ResponseBody
	@RequestMapping("createMTree.do")
	public String createMTree(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		JSONArray result = sysLayerManager.createMTree(roleId);
		JSONObject obj2 = new JSONObject();
		obj2.put("code", 0);
		obj2.put("msg", "");
		obj2.put("count", result.size());
		obj2.put("data", result);
		return obj2.toString();
	}
	//更新模块与角色关联关系
	@ResponseBody
	@RequestMapping("upRMRelate.do")
	public String upRMRelate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		String moudleId = request.getParameter("moudleId");
		String result = sysLayerManager.upRMRelate(roleId,moudleId);
		return result;
	}
}

