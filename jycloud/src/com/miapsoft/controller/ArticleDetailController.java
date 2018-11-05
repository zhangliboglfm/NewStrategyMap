package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miapsoft.manager.ArticleDetailManager;
import com.miapsoft.manager.JieduManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.ExcelUtil_ztf;
import com.miapsoft.util.OnlineRead;
import com.miapsoft.util.WordToHtml;

@Controller
public class ArticleDetailController {
	@Resource
	public ArticleDetailManager artDetailManager;
	@Resource
	public JieduManager jieduManager;

	public static String articlePath=ServerFilePath.getArticledir();//文章路径
	public static String photogPath=ServerFilePath.getPhotogdir();//摄影家路径
	public static String worksPath=ServerFilePath.getPhotogdir();//作品路径
	public static String sysPath=ServerFilePath.getSystemFilePath();//作品路径

	@RequestMapping("articleDetail.do")
	public String articleDetail(HttpServletRequest request, HttpServletResponse response){
		String photogId = request.getParameter("photogId");//摄影家ID
		String articleId = request.getParameter("articleId");//点击的文章作品ID
		String userId = request.getSession().getAttribute("userId")+"";
		request.setAttribute("photogId", photogId);
		request.setAttribute("articleId", articleId);
		request.setAttribute("userId", userId);
		return "pgmag/articleDetail";
	}
	@RequestMapping("articleDetailAudit.do")
	public String articleDetailAudit(HttpServletRequest request, HttpServletResponse response){
		String photogId = request.getParameter("photogId");//摄影家ID
		String articleId = request.getParameter("articleId");//点击的文章作品ID
		String userId = request.getSession().getAttribute("userId")+"";
		request.setAttribute("photogId", photogId);
		request.setAttribute("articleId", articleId);
		request.setAttribute("userId", "admin");
		return "pgmag/articleDetailAudit";
	}
	//查询所有文章封面及标题，并生成左侧树结构
	@ResponseBody
	@RequestMapping("getAllArticle.do")
	public String getAllArticle(HttpServletRequest request,HttpServletResponse response){
		String photogId=request.getParameter("photogId");
		JSONObject result = artDetailManager.getAllArticle(photogId);
		return result.toString();
	}
	//查询文章名称及封面
	@ResponseBody
	@RequestMapping("getArtTitle.do")
	public String getArtTitle(HttpServletRequest request,HttpServletResponse response){
		String articleId=request.getParameter("articleId");
		JSONObject result = artDetailManager.getArtTitle(articleId);
		return result.toString();
	}
	//查询文章已有标签并展示
	@ResponseBody
	@RequestMapping("getArticlLabel.do")
	public String getArticlLabel(HttpServletRequest request,HttpServletResponse response){
		String articleId=request.getParameter("articleId");
		JSONObject result = artDetailManager.getArticlLabel(articleId);
		return result.toString();
	}
	//查询文章已有标签和所有标签，并分类显示
	@ResponseBody
	@RequestMapping("getAllArtLabel.do")
	public String getAllArtLabel(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = artDetailManager.getAllArtLabel();
		return result.toString();
	}
	//对文章标签进行操作
	@ResponseBody
	@RequestMapping("opArticleLabel.do")
	public String opArticleLabel(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String labelId = request.getParameter("labelId");
		String operate = request.getParameter("operate");
		JSONObject result = artDetailManager.opArticleLabel(articleId,labelId,operate);
		return result.toString();
	}
	//对文章标签进行操作(新)
	@ResponseBody
	@RequestMapping("updateLabel.do")
	public String updateLabel(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String arr = request.getParameter("arr");
		JSONObject result = artDetailManager.updateLabel(articleId,arr);
		return null;
	}
	//添加自定义标签
	@ResponseBody
	@RequestMapping("addCustomLabel.do")
	public String addCustomLabel(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String labelName = request.getParameter("labelName");
		String labeldesc = request.getParameter("labeldesc");
		JSONObject result = artDetailManager.addCustomLabel(articleId,labelName,labeldesc);
		return result.toString();
	}
	//查相关摄影家
	@ResponseBody
	@RequestMapping("searchPhotogInfo.do")
	public String searchPhotogInfo(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		JSONObject result = artDetailManager.searchPhotogInfo(articleId);
		return result.toString();
	}
	//添加相关摄影家
	@ResponseBody
	@RequestMapping("addNewPhotogF.do")
	public String addNewPhotogF(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String pgid = request.getParameter("pgid");
		JSONObject result = artDetailManager.addNewPhotogF(articleId,pgid);
		return result.toString();
	}
	//查相关作品
	@ResponseBody
	@RequestMapping("searchWorksInfo.do")
	public String searchWorksInfo(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		JSONObject result = artDetailManager.searchWorksInfo(articleId);
		return result.toString();
	}
	//添加相关作品
	@ResponseBody
	@RequestMapping("addNewWorksF.do")
	public String addNewWorksF(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String wkid = request.getParameter("wkid");
		JSONObject result = artDetailManager.addNewWorksF(articleId,wkid);
		return result.toString();
	}
	//重新排序摄影家、作品
	@ResponseBody
	@RequestMapping("reOrderPhotog.do")
	public String reOrderPhotog(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String reOrder = request.getParameter("reOrder");
		String opFlag = request.getParameter("opFlag");
		reOrder = reOrder.replace("[", "").replace("]", "");
		reOrder = reOrder.replace("\"", "");
		JSONObject result = artDetailManager.reOrderPhotog(articleId,reOrder,opFlag);
		return result.toString();
	}
	//对文章标签进行操作
	@ResponseBody
	@RequestMapping("deleteOper.do")
	public String deleteOper(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String labelId = request.getParameter("labelId");
		String deleteFlag = request.getParameter("deleteFlag");
		JSONObject result = artDetailManager.deleteOper(articleId,labelId,deleteFlag);
		return result.toString();
	}
	//审核状态更新
	@ResponseBody
	@RequestMapping("changeAuditStatus.do")
	public String changeAuditStatus(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String auditStatus = request.getParameter("auditStatus");
		String auditDesc = request.getParameter("auditDesc");
		String auditPersn = request.getParameter("auditPersn");
		String result = artDetailManager.changeAuditStatus(articleId,auditStatus,auditDesc,auditPersn);
		return result.toString();
	}
	//添加审核状态
	@ResponseBody
	@RequestMapping("addAuditing.do")
	public String addAuditing(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		String articleTile=request.getParameter("articleTile");
		JSONObject result = artDetailManager.addAuditing(articleId,articleTile);
		return null;
	}
	//获取文章的word、图片
	@ResponseBody
	@RequestMapping("getWordAndPic.do")
	public String getWordAndPic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		JSONObject result = artDetailManager.getWordAndPic(articleId);
		return result.toString();
	}
	//查询审核状态
	@ResponseBody
	@RequestMapping("auditState.do")
	public String auditState(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		JSONObject result = artDetailManager.auditState(articleId);
		return result.toString();
	}
	//word解读
	@RequestMapping("artWordShow.do")
	public ModelAndView artWordShow(HttpServletRequest request, HttpServletResponse response) throws TransformerException, IOException, ParserConfigurationException{
		WordToHtml wTH=new WordToHtml();
		String articleId=request.getParameter("articleId");
		String flagId=request.getParameter("flagId");
		ModelAndView result=new ModelAndView();
		String result1=jieduManager.getAreInterpret(articleId,flagId);
		String worksintrodir=ServerFilePath.getWorksintrodir();//作品
		FileInputStream fis = null;
        try {
        	// 图片文件解密
        	fis = new FileInputStream(articlePath+"/"+result1);
            String key = EncryptKey.DesKey;
			InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
			result = OnlineRead.OnlineReadWord(sbs,result1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//result=wTH.wordToHtml(worksintrodir+"/"+result1, "H:/testword/", "123.html");
		return result;
	}
	//各种图片加载
	@ResponseBody
	@RequestMapping("getArtImage.do")
	public String getArtImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String imgUrl=request.getParameter("imgUrl");
		String imgFlag=request.getParameter("imgFlag");
		FileInputStream fis = null;  
	    OutputStream os = null;  
		try {  
			//imgFlag 1- 文章封面路径  2-摄影家标准照  3- 作品图片
			if ("1".equals(imgFlag)) {
				fis = new FileInputStream(articlePath+"/"+imgUrl);
			} else if ("2".equals(imgFlag)) {
				try {
					fis = new FileInputStream(photogPath+"/"+imgUrl);
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/default.jpg");
				}
			} else {
				fis = new FileInputStream(worksPath+"/"+imgUrl);
			}
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
	//接收上传过来封面图片并保存
	@RequestMapping("reuploadImg.do")
	public String reuploadImg(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		String leixing = request.getParameter("leixing");
		String filename = request.getParameter("filename");
		String articleId = request.getParameter("articleId");
		String fileType = request.getParameter("fileType");
		String coverOrfile = request.getParameter("coverOrfile");
		filename = URLDecoder.decode(filename,"UTF-8");
		filename=filename.substring(0,filename.lastIndexOf("."));
		filename = EncryptionUtil.ENCRYPTSTRING(filename)+"."+leixing;
		String filePath = articlePath+"/"+articleId+"/"+filename;
		String rfilePath = articleId+"/"+filename;
		File outFile = new File(filePath);
		File pFile = outFile.getParentFile();
		//判断文件夹是否存在
		if (pFile != null && !pFile.exists()) {
			pFile.mkdirs();
		}
		//文件加密
		String key = EncryptKey.DesKey;
		DesUtil.encryptFile(key,new FileInputStream(f),new FileOutputStream(outFile));
		
		JSONObject result= new JSONObject();
		result = artDetailManager.reuploadImg(rfilePath,articleId,fileType,coverOrfile);
		JSONArray array1 = new JSONArray();
		array1.add(rfilePath);
		result.put("imgPath", array1);
		response.setContentType("text/html;charset=gbk"); 
		PrintWriter out = response.getWriter(); 
		out.print(result.toString());
		out.flush();
		out.close();
		return null;
	}
	//下载文件
	@RequestMapping(value="downFiles.do")
	public void downFiles(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		String fileType=request.getParameter("fileType");
		String filePath= artDetailManager.getFilePath(articleId,fileType);
		String fileName="相关文章解读"+filePath.substring(filePath.lastIndexOf("."));
		FileInputStream fis = null;
		fis = new FileInputStream(articlePath+"/"+filePath);
		String key = EncryptKey.DesKey;
        InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName).getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(sbs);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
}
