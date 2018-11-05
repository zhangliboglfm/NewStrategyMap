package com.miapsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.FilmfilterManager;
import com.miapsoft.manager.MainManager;


@Controller
public class FilmfilterController {
	@Resource
	public FilmfilterManager filmFilter;
	
	public static String sampPic="/home/nfs/resourcelib/SpiderImg";
	/**
	 * 胶片样片筛选
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("filmfilter.do")
	public String filmfilter(HttpServletRequest request, HttpServletResponse response){
		
		return "jsp/filmfilter";
	}
	@ResponseBody
	@RequestMapping("getNums.do")
	public String getNums(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_NAME=request.getParameter("SAMP_PIC_NAME");
		String FILM_CAMERA_ID=request.getParameter("FILM_CAMERA_ID");
		String DEAL_DATE=request.getParameter("DEAL_DATE");
		String IS_BLACK=request.getParameter("IS_BLACK");
		String SAMP_PIC_LENGTH=request.getParameter("SAMP_PIC_LENGTH");
		String LABEL_NAME=request.getParameter("LABEL_NAME");
		String LENS_ID=request.getParameter("LENS_ID");
		String FILM_PROD_ID=request.getParameter("FILM_PROD_ID");
		String size=request.getParameter("size");
		String state1=request.getParameter("state1");
		String state2=request.getParameter("state2");
		JSONArray result=filmFilter.getNums(SAMP_PIC_NAME, FILM_CAMERA_ID, DEAL_DATE, IS_BLACK, SAMP_PIC_LENGTH, LABEL_NAME, LENS_ID, FILM_PROD_ID, size,state1,state2);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getPic.do")
	public String getPic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_NAME=request.getParameter("SAMP_PIC_NAME");
		String FILM_CAMERA_ID=request.getParameter("FILM_CAMERA_ID");
		String DEAL_DATE=request.getParameter("DEAL_DATE");
		String IS_BLACK=request.getParameter("IS_BLACK");
		String SAMP_PIC_LENGTH=request.getParameter("SAMP_PIC_LENGTH");
		String LABEL_NAME=request.getParameter("LABEL_NAME");
		String LENS_ID=request.getParameter("LENS_ID");
		String FILM_PROD_ID=request.getParameter("FILM_PROD_ID");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		String size=request.getParameter("size");
		String state1=request.getParameter("state1");
		String state2=request.getParameter("state2");
		JSONArray result=filmFilter.getPic(SAMP_PIC_NAME, FILM_CAMERA_ID, DEAL_DATE, IS_BLACK, SAMP_PIC_LENGTH, LABEL_NAME, LENS_ID, FILM_PROD_ID, pageNum, pageSize, size,state1,state2);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getOnePic.do")
	public String getOnePic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		JSONArray result=filmFilter.getOnePic(SAMP_PIC_ID);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getLabel.do")
	public String getLabel(HttpServletRequest request, HttpServletResponse response){
		JSONArray result=filmFilter.getLabel();
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getCamera.do")
	public String getCamera(HttpServletRequest request, HttpServletResponse response){
		String keyword=request.getParameter("keyword");
		JSONArray result=filmFilter.getCamera(keyword);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getFilm.do")
	public String getFilm(HttpServletRequest request, HttpServletResponse response){
		String keyword=request.getParameter("keyword");
		JSONArray result=filmFilter.getFilm(keyword);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getLens.do")
	public String getLens(HttpServletRequest request, HttpServletResponse response){
		String keyword=request.getParameter("keyword");
		JSONArray result=filmFilter.getLens(keyword);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("updateInfo.do")
	public String updateInfo(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String SAMP_PIC_NAME=request.getParameter("SAMP_PIC_NAME");
		String FILM_PROD_ID=request.getParameter("FILM_PROD_ID");
		String FILM_CAMERA_ID=request.getParameter("FILM_CAMERA_ID");
		String LENS_ID=request.getParameter("LENS_ID");
		String IS_BLACK=request.getParameter("IS_BLACK");
		String result=filmFilter.updateInfo(SAMP_PIC_ID,SAMP_PIC_NAME,FILM_PROD_ID,FILM_CAMERA_ID,LENS_ID,IS_BLACK);
		return result;
	}
	@ResponseBody
	@RequestMapping("deletePic.do")
	public String deletePic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.deletePic(SAMP_PIC_ID);
		return result;
	}
	@ResponseBody
	@RequestMapping("screenPic.do")
	public String screenPic(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String result=filmFilter.screenPic(SAMP_PIC_ID);
		return result;
	}
	@ResponseBody
	@RequestMapping("getPicLabel.do")
	public String getPicLabel(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		JSONArray result=filmFilter.getPicLabel(SAMP_PIC_ID);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("searchLabel.do")
	public String searchLabel(HttpServletRequest request, HttpServletResponse response){
		String keyword=request.getParameter("keyword");
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		JSONArray result=filmFilter.searchLabel(keyword,SAMP_PIC_ID);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("addPicLabel.do")
	public String addPicLabel(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String LABEL_ID=request.getParameter("LABEL_ID");
		String order=request.getParameter("order");
		String result=filmFilter.addPicLabel(SAMP_PIC_ID,LABEL_ID,order);
		return result;
	}
	@ResponseBody
	@RequestMapping("deletePicLabel.do")
	public String deletePicLabel(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String LABEL_ID=request.getParameter("LABEL_ID");
		String result=filmFilter.deletePicLabel(SAMP_PIC_ID,LABEL_ID);
		return result;
	}
	@ResponseBody
	@RequestMapping("addLabel.do")
	public String addLabel(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String LABEL_NAME=request.getParameter("LABEL_NAME");
		String LABEL_DESC=request.getParameter("LABEL_DESC");
		String result=filmFilter.addLabel(LABEL_NAME,SAMP_PIC_ID,LABEL_DESC);
		return result;
	}
	@ResponseBody
	@RequestMapping("getDismissal.do")
	public String getDismissal(HttpServletRequest request, HttpServletResponse response){
		JSONArray result=filmFilter.getDismissal();
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getRegresses.do")
	public String getRegresses(HttpServletRequest request, HttpServletResponse response){
		JSONArray result=filmFilter.getRegresses();
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("addRegresses.do")
	public String addRegresses(HttpServletRequest request, HttpServletResponse response){
		String SAMP_PIC_ID=request.getParameter("SAMP_PIC_ID");
		String SAMP_PIC_TYPE=request.getParameter("SAMP_PIC_TYPE");
		String STATUS_ID=request.getParameter("STATUS_ID");
		String AUDIT_DESC=request.getParameter("AUDIT_DESC");
		String AUDIT_PERSN_ACCT_ID=request.getParameter("AUDIT_PERSN_ACCT_ID");
		String BACK_REASON_ID=request.getParameter("BACK_REASON_ID");
		String result=filmFilter.addRegresses(SAMP_PIC_ID,SAMP_PIC_TYPE,STATUS_ID,AUDIT_DESC,AUDIT_PERSN_ACCT_ID,BACK_REASON_ID);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("addReason.do")
	public String addReason(HttpServletRequest request, HttpServletResponse response){
		String REASON_ID=request.getParameter("REASON_ID");
		String REASON_TYPE=request.getParameter("REASON_TYPE");
		String REASON_NAME=request.getParameter("REASON_NAME");
		String REASON_DESC=request.getParameter("REASON_DESC");
		String SHOW_ORDER=request.getParameter("SHOW_ORDER");
		String result=filmFilter.addReason(REASON_ID,REASON_TYPE,REASON_NAME,REASON_DESC,SHOW_ORDER);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("getSampImage.do")
	public String getArtImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String imgUrl=request.getParameter("imgUrl");
		FileInputStream fis = null;  
	    OutputStream os = null;  
		try {  
			fis = new FileInputStream(sampPic+"/"+imgUrl);
	        InputStream sbs =fis;
	        os = response.getOutputStream();
	        int count = 0;
	        byte[] buffer = new byte[1024 * 8];
	        while ((count = sbs.read(buffer)) != -1) {
	            os.write(buffer, 0, count);
	            os.flush();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();  
	    }  
	    try {
	        fis.close();
	        os.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
}
