package com.miapsoft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.miapsoft.manager.sys.SystemManager;
import com.miapsoft.model.UserInfo;

/**
 * <p>Title: MainController.java</p>
 * <p>Description: TODO</p>
 * @author: 白少华
 * @time: 2017-6-9
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class MainController {
	
	@Resource
	private SystemManager systemManager;
	
	@RequestMapping(value="main.do")
	public String main(HttpServletRequest request,HttpServletResponse response){
		String result="errors/biz-error";
		String errorMsg="您没有权限访问！";
		String userId = request.getParameter("userId");
		if(userId!=null&&!"".equals(userId)){
			UserInfo userInfo = null;
			try {
				userInfo = systemManager.getUserInfo(userId);
			} catch (Exception e) {
				System.err.println("数据库连接异常或SQL查询异常！");
				e.printStackTrace();
				result = "errors/error";
				return result;
			}
			if(userInfo==null){
				errorMsg="未找到用户信息！";
				request.setAttribute("errorMsg", errorMsg);
				return result;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userInfo.getUser().getUserName());
			session.setAttribute("lvlId", userInfo.getRegion().getLvlId());
			session.setAttribute("regionId", userInfo.getRegion().getRegionId());
			session.setAttribute("regionParentId", userInfo.getRegion().getRegionParentId());
			session.setAttribute("regionDesc", userInfo.getRegion().getRegionDesc());
			session.setAttribute("regionName", userInfo.getRegion().getRegionName());
			result="jsp/main";
		}
		request.setAttribute("errorMsg", errorMsg);
		return result;
	}

	public SystemManager getSystemManager() {
		return systemManager;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}
}
