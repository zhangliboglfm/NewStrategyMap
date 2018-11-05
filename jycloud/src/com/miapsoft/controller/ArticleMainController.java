package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.miapsoft.manager.ArticleMainManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.FileUtil;
import com.miapsoft.util.Word2Html;
import com.miapsoft.util.WordToHtml;

@Controller
public class ArticleMainController {
	@Resource
	ArticleMainManager ArticleMainmanager;
	
	@RequestMapping("articlemain.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		return "jsp/articlemain";
	}
	@RequestMapping("zhuanti.do")
	public String zhuanti(HttpServletRequest request, HttpServletResponse response){
		return "jsp/article_zhuanti";
	}
	@RequestMapping("hexin.do")
	public String hexin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String userId=request.getParameter("userId");
		String wzName="";
		String author="";
		if (!"".equals(userId) && userId!=null) {
			wzName=URLDecoder.decode(request.getParameter("wzName"),"utf-8");
			author=URLDecoder.decode(request.getParameter("author"),"utf-8");
			request.setAttribute("articleName", wzName);
			request.setAttribute("author", author);
		}
		return "jsp/article_hexin";
	}
	//文章/作品解读
	@RequestMapping("reading.do")
	public String zuopin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String articleId=request.getParameter("articleId");
		String layId=request.getParameter("layId");
		request.setAttribute("articleId", articleId);
		request.setAttribute("layId", layId);
		/*String h5path="";
		JSONArray result1=ArticleMainmanager.getAreInterpret(articleId,layId);
		if (result1.size()!=0) {
			h5path=result1.get(2).toString();
		}*/
		String h5path="https://www.baidu.com/";
		request.setAttribute("h5path", h5path);
		return "jsp/article_show";
	}
	//核心介绍人物介绍
	@RequestMapping("photogResume.do")
	public String photogResume(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String articleId=request.getParameter("photogId");
		//JSONObject result=ArticleMainmanager.photogResume(articleId);
		request.setAttribute("articleId", articleId);
		return "jsp/article_show";
	}
	//专题文章总数
	@ResponseBody
	@RequestMapping("getWzCounts.do")
	public String getWzCounts(HttpServletRequest request, HttpServletResponse response){
		String wzName=request.getParameter("wzName");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		JSONObject result=ArticleMainmanager.getWzDates(wzName,pageNum,pageSize);
		return result.toString();
	}
	//获取文章标签
	@ResponseBody
	@RequestMapping("getWzTags.do")
	public String getWzTags(HttpServletRequest request, HttpServletResponse response){
		String wzName=request.getParameter("wzName");
		JSONObject result=ArticleMainmanager.getWzTags(wzName);
		return result.toString();
	}
	//向表内插入文章修改信息
	@ResponseBody
	@RequestMapping("saveMessages.do")
	public String saveMessages(HttpServletRequest request, HttpServletResponse response){
		JSONArray jsonArray1=new JSONArray();
		JSONArray jsonArray2=new JSONArray();
		String wzImageSrc=request.getParameter("wzImageSrc");
		String wzImageAlt=request.getParameter("wzImageAlt");
		String wzName=request.getParameter("wzName");
		String wzContent=request.getParameter("wzContent");
		String tagid=request.getParameter("tagid");
		jsonArray1=JSONArray.fromObject(tagid);
		String tagname=request.getParameter("tagname");
		jsonArray2=JSONArray.fromObject(tagname);
		String wzIdnet=request.getParameter("wzIdnet");
		int result=ArticleMainmanager.saveMessages(wzImageSrc,wzImageAlt,wzName,wzContent,jsonArray1,jsonArray2,wzIdnet);
		return result+"";
	}
	//作者总数
	@ResponseBody
	@RequestMapping("getRyCounts.do")
	public String getRyCounts(HttpServletRequest request, HttpServletResponse response){
		String zzName=request.getParameter("zzName");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		JSONObject result=ArticleMainmanager.getRyCounts(zzName,pageNum,pageSize);
		return result.toString();
	}
	//获取编辑文章的所有标签
	@ResponseBody
	@RequestMapping("getLablist_Art.do")
	public String getLablist_Art(HttpServletRequest request, HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String tagName = request.getParameter("tagName");
		JSONArray result =ArticleMainmanager.getLablist_Art(PHOTOG_ID,tagName);
		return result.toString();
	}
	//删除编辑文章的所有标签
	@ResponseBody
	@RequestMapping("operateLabel_Art.do")
	public String operateLabel_Art(HttpServletRequest request, HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String LABEL_ID = request.getParameter("LABEL_ID");
		String operate = request.getParameter("operate");
		String result =ArticleMainmanager.operateLabel_Art(PHOTOG_ID,LABEL_ID,operate);
		return result;
	}
	//获取文章相关作品
	@ResponseBody
	@RequestMapping("getZP.do")
	public String getZP(HttpServletRequest request, HttpServletResponse response){
		String wzIdnet = request.getParameter("wzIdnet");
		JSONObject result =ArticleMainmanager.getZP(wzIdnet);
		return result.toString();
	}
	/**
	 * 回传Img图片
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPhtotogImg_Art.do")
	public String getPhtotogImg(HttpServletRequest request, HttpServletResponse response){
		FileInputStream fis = null;  
	    OutputStream os = null;  
	    ServerFilePath sfp=new ServerFilePath();
	    String IMGPATH=sfp.getPhotogdir();
		String filename = request.getParameter("filename");
		try {  
			fis = new FileInputStream(IMGPATH+"/"+filename);  
			// 图片文件解密
			String key = EncryptKey.DesKey;
			InputStream sbs = new ByteArrayInputStream( DesUtil.decryptFile(key,fis));
			os = response.getOutputStream();  
			int count = 0;  
			byte[] buffer = new byte[1024*8];  
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
	};	
	/**
	 * 更新摄影家头像
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("headImg_Art.do")
	public String  standardImg(HttpServletRequest request,HttpServletResponse response){
		JSONObject result =new JSONObject();
		ServerFilePath sfp=new ServerFilePath();
	    String IMGPATH=sfp.getPhotogdir();
		String PHOTOG_ID =request.getParameter("PHOTOG_ID");
		JSONObject saveResult = FileUtil.SaveImgEncrypt(request,IMGPATH);
		if("false".equals(saveResult.get("istrue"))){
			result.put("code", 0);
			return result.toString();
		};
		String filename = saveResult.getString("filename");
		boolean istrue =ArticleMainmanager.updateArtImg(PHOTOG_ID,filename);
		if(!istrue){
			result.put("code", 0);
			return result.toString();
		}else{
			result.put("code", 1);
			result.put("filename",filename);
			return result.toString();
		}
	};	
	
	//展现文章/作品word内容
	@RequestMapping("JieduWordART.do")
	public ModelAndView JieduWordART(HttpServletRequest request, HttpServletResponse response) throws TransformerException, IOException, ParserConfigurationException{
		WordToHtml wTH=new WordToHtml();
		String articleId=request.getParameter("articleId");
		String layId=request.getParameter("layId");
		ModelAndView result=new ModelAndView();
		ServerFilePath sfp=new ServerFilePath();
		JSONArray result1=ArticleMainmanager.getAreInterpret(articleId,layId);
		String coreintrodir=sfp.getCoreintrodir();//核心
		String worksintrodir=sfp.getWorksintrodir();//作品
		String articledir=sfp.getArticledir();//文章
		if (result1.size()!=0) {
			String docpath3=result1.get(2).toString();
			if ("777".equals(layId)) {//文章
				result=wTH.wordToHtml(docpath3, "E:/testword/", "123.html");
			} else if ("999".equals(layId)) {//作品
				result=wTH.wordToHtml(docpath3, "E:/testword/", "123.html");
			} else if ("789".equals(layId)) {//人物
				result=wTH.wordToHtml(docpath3, "E:/testword/", "123.html");
			}
		}
		return result;
	}
	//展现文章/作品图片
	@ResponseBody
	@RequestMapping("JieduPicture.do")
	public void JieduPicture(HttpServletRequest request, HttpServletResponse response) throws TransformerException, IOException, ParserConfigurationException{
		response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        String articleId=request.getParameter("articleId");
		String layId=request.getParameter("layId");
        String imgpath="";
        JSONArray result1=ArticleMainmanager.getAreInterpret(articleId,layId);
		if (result1.size()!=0) {
			imgpath=result1.get(1).toString();
			String fullFileName = getRealPath(imgpath);
			FileInputStream fis = new FileInputStream(fullFileName);
			OutputStream os = response.getOutputStream();
			try {
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1)
					os.write(buffer, 0, count);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (os != null)
					os.close();
				if (fis != null)
					fis.close();
			}		
		}
	}
	private String getRealPath(String string) {
		// TODO Auto-generated method stub
		return string;
	}
	@RequestMapping("reuploadJDu_ART.do")
	public String reuploadJDu_ART(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		String filename = request.getParameter("filename");
		String articleId = request.getParameter("articleId");
		String xuanlei = request.getParameter("xuanlei");
		String layId = request.getParameter("layId");
		String linshiPath=ServerFilePath.getArticledir();
		filename = URLDecoder.decode(filename,"UTF-8");
		File outFile = new File(linshiPath+"/"+filename);
		//File outFile = new File("D:/image/"+filename);
		FileOutputStream output = null;
		InputStream in = null;
		try {
			output = new FileOutputStream(outFile);
			in = new BufferedInputStream(new FileInputStream(f), 4096);
			byte[] a = new byte[4096];
			int i=0;
			 while ((i = in.read(a)) > 0) {
				 output.write(a, 0, i);
             }
			 output.flush();
			 in.close();
			 output.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String filePath = linshiPath+"/"+filename;
		//String filePath = "D:/image/"+filename;
		JSONObject result= new JSONObject();
		result = ArticleMainmanager.reuploadJDu_ART(filePath,articleId,xuanlei,layId);
		JSONArray array1 = new JSONArray();
		array1.add(filePath);
		result.put("imgPath", array1);
		System.out.println(result);
		response.setContentType("text/html;charset=gbk"); 
		PrintWriter out = response.getWriter(); 
		out.print(result.toString());
		out.flush();
		out.close();
		return null;
	}	
}
