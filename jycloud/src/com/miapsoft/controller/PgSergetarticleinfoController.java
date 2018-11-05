package com.miapsoft.controller;

import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PgSergetarticleinfomanager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.FileUtil;

@Controller
public class PgSergetarticleinfoController {
	
	@Resource
	public PgSergetarticleinfomanager pgSergetarticleinfomanager;
	@ResponseBody
	@RequestMapping(value="getmorearticle.do")
	/*更多推荐文章*/
	public String getmorearticle(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		JSONObject obj = new JSONObject();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type","text/html;charset=gbk");
		String page=request.getParameter("page");
		int pagenum=Integer.parseInt(page);
		JSONArray AreaSaturation = new JSONArray();
		try {
			AreaSaturation = pgSergetarticleinfomanager.getmorearticle(pagenum);
			if (AreaSaturation.size() != 0) {
				for (int i = 0; i < AreaSaturation.size(); i++) {
					String coverpicname = AreaSaturation.getJSONObject(i).getString("articlepic");
					AreaSaturation.getJSONObject(i).put("articlepic","getcoverphoto.do?artCoverPic="+ URLEncoder.encode(URLEncoder.encode(coverpicname, "UTF-8"), "UTF-8"));
				}
			}
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", AreaSaturation);
		return obj.toString();
	}
	@ResponseBody
	@RequestMapping(value="getcoverphoto.do")
	/*文章封面下载接口*/
	public void getcoverphoto(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String artCoverPic=request.getParameter("artCoverPic");
		artCoverPic = URLDecoder.decode(URLDecoder.decode(artCoverPic,"UTF-8"),"UTF-8");
		String filename = ServerFilePath.getArticledir()+"/"+artCoverPic;
		FileUtil.decryptDownload(artCoverPic.substring(artCoverPic.lastIndexOf("/")+1, artCoverPic.length()), filename, filename, response, request);
	}
	@ResponseBody
	@RequestMapping(value="getPgScountry.do")
	/*国籍摄影家数量接口*/
	public String getPgScountry(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		JSONArray AreaSaturation = new JSONArray();
		try {
			AreaSaturation = pgSergetarticleinfomanager.getcoverphoto();
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", AreaSaturation);
		return obj.toString();
	}
	@ResponseBody
	@RequestMapping(value="getregisterinfo.do")
	/*注册接口*/
	public String getregisterinfo(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String loginid=request.getParameter("loginid");
		String password=request.getParameter("password");
		String qqnum=request.getParameter("qqnum");
		String vxnum=request.getParameter("vxnum");
		String wbnum=request.getParameter("wbnum");
		int flag=pgSergetarticleinfomanager.getregisterinfo(loginid,password,qqnum,vxnum,wbnum);
		String result="";
		if (flag>0) {
			result="ok";
			return result;
		} else {
			result="false";
			return result;
		}
	}
	@ResponseBody
	@RequestMapping(value="getlogininfo.do")
	/*登录接口*/
	public String getlogininfo(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String loginid=request.getParameter("loginid");
		String password=request.getParameter("password");
		String qqnum=request.getParameter("qqnum");
		String vxnum=request.getParameter("vxnum");
		String wbnum=request.getParameter("wbnum");
		int flag=pgSergetarticleinfomanager.getlogininfo(loginid,password,qqnum,vxnum,wbnum);
		String result="";
		if (flag>0) {
			result="ok";
			return result;
		} else {
			result="false";
			return result;
		}
	}
}
