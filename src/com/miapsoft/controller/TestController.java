package com.miapsoft.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.TestManager;
import com.miapsoft.model.TbAdmUserUnit;
import com.miapsoft.model.UserInfo;
import com.miapsoft.util.FileUtil;
import com.miapsoft.util.LogUtil;
/**
 * 测试controller
 * <p>Title: TestController.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
@Controller
public class TestController {
	@Resource
	private TestManager testManager;
	
	@RequestMapping(value="test1.do")
	public String test1(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("test1");
//		JSONArray result = testManager.getRegions();
//		int i = 10/0;
		//添加日志
		LogUtil.addLog(request, "", "", "");
		return "test1";
	}
	@RequestMapping(value="test4.do")
	public String test4(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("test4");
//		JSONArray result = testManager.getRegions();
		return "test4";
	}
	@RequestMapping(value="test2.do")
	public String test2(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("test2");
		//String test = request.getParameter("");
		request.setAttribute("test", "aaa");
		return "test2";
	}
	@RequestMapping(value="mtree.do")
	public String mtree(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("mtree");
		return "tree";
	}
	@ResponseBody
	@RequestMapping(value="test3.do")
	public String test3(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("test3");
		JSONArray result = testManager.getRegions();
		return result.toString();
	}
	@RequestMapping(value="login/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("login");
		String userId = request.getParameter("userId");
		UserInfo u = new UserInfo();
		TbAdmUserUnit tbAdmUserUnit = new TbAdmUserUnit();
		tbAdmUserUnit.setUserIdAccounts(userId);
		u.setUser(tbAdmUserUnit);
		request.getSession().setAttribute("userInfo", u);
		return "login";
	}
	@RequestMapping(value="downloadAndupload.do")
	public String downloadAndupload(HttpServletRequest request,HttpServletResponse response) {
	    System.out.println("mtree");
	    return "downloadAndupload";
	}
	/**
	 * 文件下载例子
	 * @Title:download
	 * @author:李杰
	 * @date:2017-4-25
	 */
	@RequestMapping(value="download.do")
	public String download(HttpServletRequest request,HttpServletResponse response) {
		String path = request.getParameter("path");//下载文件路径
		try {
		    path = URLDecoder.decode(path,"utf-8");
		} catch (UnsupportedEncodingException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		String fileName = "测试文档.txt";//下载文件保存的名称
		fileName = path;
		String allowpath = "v:/";//允许下载的路径
		FileUtil.download(fileName, "v:/测试文档/"+path,allowpath, response);
		return null;
	}
	/**
	 * 文件上传例子
	 * @Title:upload
	 * @Description:TODO
	 * @param request
	 * @param response
	 * @return
	 * @author:李杰
	 * @date:2017-4-25
	 */
	@ResponseBody
	@RequestMapping(value="upload.do")
	public String upload(HttpServletRequest request,HttpServletResponse response) {
	    String saveDir = "v:/测试文档/";//文件保存路径
	    String allowpath = "v:/";//允许上传的路径
	    String result = FileUtil.upload(request,saveDir,allowpath);
	    return result;
	}
}
