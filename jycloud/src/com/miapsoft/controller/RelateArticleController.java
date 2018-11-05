package com.miapsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.ArticleDetailManager;
import com.miapsoft.manager.RelateArticleManager;
import com.miapsoft.util.CheckFileUtil_ztf;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.FileAnalysisUtil_ztf;
import com.miapsoft.util.FileUtil;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.PrintUtil;
import com.miapsoft.util.UnCompressUtil;

@Controller
public class RelateArticleController {
	@Resource
	public RelateArticleManager relateArtManager;
	@Resource
	public ArticleDetailManager artDetailManager;
	
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录

	public static String articlePath=ServerFilePath.getArticledir();

	@RequestMapping("relateArticle.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		String photogId = request.getParameter("photogId");
		String photogName = relateArtManager.searchName(photogId);
		String userId = request.getSession().getAttribute("userId")+"";
		request.setAttribute("photogId", photogId);
		request.setAttribute("photogName", photogName);
		request.setAttribute("userId", userId);
		return "pgmag/relateArticle";
	}
	@RequestMapping("relateArticleAudit.do")
	public String relateArticleAudit(HttpServletRequest request, HttpServletResponse response){
		String photogId = request.getParameter("photogId");
		String photogName = relateArtManager.searchName(photogId);
		String userId = request.getSession().getAttribute("userId")+"";
		request.setAttribute("photogId", photogId);
		request.setAttribute("photogName", photogName);
		request.setAttribute("userId", userId);
		return "pgmag/relateArticleAudit";
	}

	//查文章总页数
	@ResponseBody
	@RequestMapping("searchPaging.do")
	public String searchPaging(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String photogId=request.getParameter("photogId");
		int pagenum = relateArtManager.searchPaging(photogId);
		int remainder = pagenum%15;
		String allpage = "";
		if (remainder == 0) {
			allpage = pagenum/15+"";
		} else {
			allpage=pagenum/15+1+"";
		}
		return allpage;
	}
	//查某一页文章内容
	@ResponseBody
	@RequestMapping("showArticleInfo.do")
	public String showArticleInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String photogId=request.getParameter("photogId");
		String curr=request.getParameter("curr");//第几页
		JSONObject result = relateArtManager.showArticleInfo(photogId,curr);
		return result.toString();
	}

	//查某一页内容
	@ResponseBody
	@RequestMapping("getArtCover.do")
	public String getArtCover(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String imgUrl=request.getParameter("imgUrl");
		String photogId=request.getParameter("photogId");
		FileInputStream fis = null;  
		OutputStream os = null;  
		try {  
			fis = new FileInputStream(articlePath+"/"+imgUrl);
			// 图片文件解密
			String key = EncryptKey.DesKey;
			InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
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
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return null;
	}
	/**
	 * 上传文章压缩包并解压缩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadArtZip.do")
	public void uploadarticle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		printWriter = response.getWriter();
		
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		
		//String tmpFileName=URLDecoder.decode(name,"utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String tmpFileName=sdf.format(new Date())+File.separator+URLDecoder.decode(name,"utf-8");
		String newfileName=tmpFilePath+File.separator+tmpFileName;
		
		File file = new File(newfileName);
		File parent = file.getParentFile();
		//判断文件夹是否存在
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		//判断文件夹是否已存在
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
		}
		InputStreamToFile.ServletInputStreamToFile(request.getInputStream(), file);
		if(file.length()!=Long.parseLong(size)){
			throw new Exception("文件上传失败");
		}
		//解压缩
		String dirname = uploadFile(newfileName, file.getParentFile().getAbsolutePath());
		
		//******************************开始校验******************************
		if(CheckFileUtil_ztf.checkArticleFile2(file.getParentFile().getAbsolutePath()+File.separator+dirname, printWriter)){
			//******************************开始解析******************************
			String artId=artDetailManager.getNewArtId();
			JSONArray data = FileAnalysisUtil_ztf.AFileDataEntry(printWriter, file.getParentFile().getAbsolutePath()+File.separator+dirname,artId);
			if(data.size()!=0&&data.size()==1){
				JSONArray ainfoarr = data.getJSONArray(0);
				if(ainfoarr.size()!=0){
					for (int i = 0; i < ainfoarr.size(); i++) {
						JSONObject obj = ainfoarr.getJSONObject(i);
						artId=obj.getString("aId");
						JSONObject result = relateArtManager.insertArticleInfo(obj,artId);
					}
				}
			}
			PrintUtil.printLog(printWriter, "上传完成！");
		}else{
			File listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xlsx");//获取list文件
			if(!listfile.exists()){
				listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xls");
				if(!listfile.exists()){
					PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				}
			}
			PrintUtil.printLog(printWriter, "文件有误，请<a href='downloadArticleErrorfile.do?filepath="+URLEncoder.encode(URLEncoder.encode(listfile.getAbsolutePath(), "utf-8"), "utf-8")+"&fileName="+URLEncoder.encode(URLEncoder.encode(tmpFileName, "utf-8"),"utf-8")+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
	}
	
	public String uploadFile(String filepath,String uncompath){
	    File compressfile =new File(filepath);
	    String fileName=compressfile.getName();
	    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    String name = fileName.substring(0,fileName.lastIndexOf("."));
	    try {
	    	if(prefix.equalsIgnoreCase("zip")){
	    		UnCompressUtil.unzip(filepath, uncompath);
	    	}else if(prefix.equalsIgnoreCase("rar")){
	    		UnCompressUtil.untar(filepath, uncompath);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    compressfile.delete();
		return name;
	}
	
	//获取上传模板
	@ResponseBody
	@RequestMapping(value="downloadArticleErrorfile.do")
	public void downloadWorksErrorfile(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		String fileName=request.getParameter("fileName");
		fileName = URLDecoder.decode(fileName,"utf-8");
		String filepath= request.getParameter("filepath");
		filepath=URLDecoder.decode(filepath,"utf-8");
		
		String allowpath=filepath.replace(fileName, "");
		FileUtil.download("list.xlsx", filepath, allowpath, response);
	}
}
