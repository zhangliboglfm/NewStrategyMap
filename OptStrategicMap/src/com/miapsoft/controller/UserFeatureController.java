/**
 * 
 */
package com.miapsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title:UserFeatureController.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2018-3-29
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class UserFeatureController {

	@RequestMapping(value="userfeature.do")
	public String MainController(HttpServletRequest request, HttpServletResponse response){
		return "jsp/userfeature";
	}
}
