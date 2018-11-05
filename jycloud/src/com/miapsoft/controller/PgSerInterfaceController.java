package com.miapsoft.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PgSerInterfaceManager;
import com.miapsoft.util.FileUtil;

@Controller
public class PgSerInterfaceController {
	@Resource
	PgSerInterfaceManager pgSerInterfaceManager;
	/**
	 * 摄影家顺序接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("photogOrder.do")
	public String PhotogOrder(HttpServletRequest request,HttpServletResponse response){
		String receiveTime=request.getParameter("receiveTime");//日期
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.getPhotogOrder(receiveTime);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 增量摄影家接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addPhotog.do")
	public String addPhotog(HttpServletRequest request,HttpServletResponse response){
		String receiveTime=request.getParameter("receiveTime");//日期
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.addPhotog(receiveTime);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("data", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 增量摄影家代表作接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addPhotogWorks.do")
	public String addPhotogWorks(HttpServletRequest request,HttpServletResponse response){
		String photogId=request.getParameter("photogId");//摄影家ID
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.addPhotogWorks(photogId);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 作品图片下载接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("workPictures.do")
	public String worksPictures(HttpServletRequest request,HttpServletResponse response){
		FileInputStream fis = null;  
	    OutputStream os = null;  
		String worksId=request.getParameter("worksId");//作品ID
	    //String worksId="W_P_F840000200000038";//作品ID
		String result=pgSerInterfaceManager.worksPictures(worksId);
		String picpath=ServerFilePath.getPhotogdir();//获取服务器作品路径
		String filename=picpath+"/"+result;
		FileUtil.decryptDownload(result, filename, filename, response, request);
		return null;
	}
	/**
	 * 推荐文章接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("recomArticles.do")
	public String recomArticles(HttpServletRequest request,HttpServletResponse response){
		String receiveTime=request.getParameter("receiveTime");//日期
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.recomArticles(receiveTime);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 摄影家头像下载接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("photogHead.do")
	public String photogHead(HttpServletRequest request,HttpServletResponse response){
		String photogId=request.getParameter("photogId");//摄影家ID
		JSONArray result=pgSerInterfaceManager.getphotogHead(photogId);
		String headpath=result.getJSONObject(0).getString("FILE_NAME");
		String picpath=ServerFilePath.getPhotogdir();//获取服务器作品路径
		String filename=picpath+"/"+headpath;
		FileUtil.decryptDownload(headpath, filename, filename, response, request);
		return null;
	}
	/**
	 * 查询接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("fuzzyQuery.do")
	public String fuzzyQuery(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String photoName=request.getParameter("photoName");//摄影家姓名
		photoName=URLDecoder.decode(photoName, "utf-8");
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.getfuzzyQuery(photoName);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 收藏接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("collectiones.do")
	public String collectiones(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		String STOREMBODYTYPE=request.getParameter("storeBodyType");//类型
		String APPID=request.getParameter("appId");//ID
		String USERACCTID=request.getParameter("userAcctId");//账号
		int flag=pgSerInterfaceManager.collectiones(STOREMBODYTYPE,APPID,USERACCTID);
		String result="";
		if (flag>0) {
			result="ok";
			obj.put("status", "1000");
		} else {
			result="false";
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 生平/风格/成就数据接口
	 * LI	生平介绍
	 * ST	风格介绍
	 * AC	成就介绍
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("liStAcDatas.do")
	public String liftTimeDatas(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String photoId=request.getParameter("photoId");//摄影家ID
		String classify=request.getParameter("classify");//类别
		JSONObject obj = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			result = pgSerInterfaceManager.getliStAcDatas(photoId, classify);
			String picName = result.getString("ARTICLE_PIC");
			result.put("ARTICLE_PIC","liStAcImg.do?articleCover="+ URLEncoder.encode(URLEncoder.encode(picName, "UTF-8"),"UTF-8"));
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 生平/风格/成就图片下载接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("liStAcImg.do")
	public String liftTimeImg(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String ARTICLECOVER=request.getParameter("articleCover");//图片名称
		ARTICLECOVER = URLDecoder.decode(URLDecoder.decode(ARTICLECOVER,"UTF-8"),"UTF-8");
		String picpath=ServerFilePath.getPhotogdir();
		String filepath=picpath+"/"+ARTICLECOVER;
		FileUtil.decryptDownload(ARTICLECOVER.substring(ARTICLECOVER.lastIndexOf("/")+1, ARTICLECOVER.length()), filepath, filepath, response, request);
		return null;
	}
	/**
	 * 摄影家作品接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("photogWorks.do")
	public String photogWorks(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String photoId=request.getParameter("photoId");//摄影家ID
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.getphotogWorks(photoId);
			if (result.size() != 0) {
				for (int i = 0; i < result.size(); i++) {
					String filename = result.getJSONObject(i).getString("FILE_NAME");
					result.getJSONObject(i).put("FILE_NAME","worksImg.do?workImgPath="+ URLEncoder.encode(URLEncoder.encode(filename, "UTF-8"), "UTF-8"));
				}
			}
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 作品图片下载
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("worksImg.do")
	public String worksImg(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String workImgPath=request.getParameter("workImgPath");
		workImgPath = URLDecoder.decode(URLDecoder.decode(workImgPath,"UTF-8"),"UTF-8");
		String picpath=ServerFilePath.getPhotogdir();//获取服务器作品路径
		String filepath=picpath+"/"+workImgPath;
		FileUtil.decryptDownload(workImgPath.substring(workImgPath.lastIndexOf("/")+1, workImgPath.length()), filepath, filepath, response, request);
		return null;
	}
	/**
	 * 作品解读
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("worksRead.do")
	public String worksRead(HttpServletRequest request,HttpServletResponse response){
		String workId=request.getParameter("workId");
		String result=pgSerInterfaceManager.getworksRead(workId);
		return result;
	}
	/**
	 * 文章查看
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("articleRead.do")
	public String articleRead(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String articleId=request.getParameter("articleId");
		//String articleId="A_TA00000008";
		JSONObject obj = new JSONObject();
		JSONObject result = new JSONObject();
		JSONObject result1 = new JSONObject();
		try {
			result1 = pgSerInterfaceManager.getarticleRead(articleId);
			String picName = result1.getString("ARTICLE_PIC");
			String picTitle = result1.getString("ARTICLE_TITLE");
			
			result.put("ARTICLE_PIC","articleImg.do?articlePic="+ URLEncoder.encode(URLEncoder.encode(picName, "UTF-8"),"UTF-8"));
			result.put("ARTICLE_TITLE", picTitle);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("status", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
	/**
	 * 文章图片下载接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("articleImg.do")
	public String articleImg(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String articlePic=request.getParameter("articlePic");
		articlePic = URLDecoder.decode(URLDecoder.decode(articlePic,"UTF-8"),"UTF-8");
		String picpath=ServerFilePath.getArticledir();//获取服务器作品路径
		String filepath=picpath+"/"+articlePic;
		FileUtil.decryptDownload(articlePic.substring(articlePic.lastIndexOf("/")+1, articlePic.length()), filepath, filepath, response, request);
		return null;
	}
	/**
	 * 相关作品接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("relateWorks.do")
	public String relateWorks(HttpServletRequest request,HttpServletResponse response){
		String workId=request.getParameter("workId");
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		try {
			result = pgSerInterfaceManager.getrelateWorks(workId);
			obj.put("status", "1000");
		} catch (Exception e) {
			obj.put("result", "999");
		}
		obj.put("data", result);
		return obj.toString();
	}
}
