package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miapsoft.manager.CGArtDetailManager;
import com.miapsoft.manager.JieduManager;
import com.miapsoft.util.CompressUtil_ztf;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.OnlineRead;
import com.miapsoft.util.WordToHtml;

@Controller
public class CGArtDetailController {
	@Resource
	public CGArtDetailManager cgArtManage;

	public static String sysPath=ServerFilePath.getSystemFilePath();//系统默认路径路径
	public static String cgArtPath=ServerFilePath.getCgarticledir();//书法家文章路径
	public static String cgWorksPath=ServerFilePath.getCgworksintrodir();//书法家作品路径
	public static String cgPath=ServerFilePath.getCalligdir();//书法家，头像，作品目录
	//
	@RequestMapping("cgArtDetail.do")
	public String articleDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId = request.getParameter("articleId");//点击的文章作品ID
		String cgName = request.getParameter("cgName");//书法家名称
		cgName = URLDecoder.decode(cgName,"UTF-8");
		String userId = request.getSession().getAttribute("userId")+"";
		String auditStatus = request.getParameter("auditStatus");//审核状态
		request.setAttribute("articleId", articleId);
		request.setAttribute("cgName", cgName);
		request.setAttribute("userId", userId);
		request.setAttribute("auditStatus", auditStatus);
		return "cgmag/cgArtDetail";
	}
	@RequestMapping("cgArtdetailAudit.do")
	public String cgArtdetailAudit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId = request.getParameter("articleId");//点击的文章作品ID
		String cgName = request.getParameter("cgName");//书法家名称
		cgName = URLDecoder.decode(cgName,"UTF-8");
		String userId = request.getSession().getAttribute("userId")+"";
		String auditStatus = request.getParameter("auditStatus");//审核状态
		request.setAttribute("articleId", articleId);
		request.setAttribute("cgName", cgName);
		request.setAttribute("userId", userId);
		request.setAttribute("auditStatus", auditStatus);
		return "cgmag/cgArtdetailAudit";
	}
	//查询所有文章封面及标题，并生成左侧树结构
	@ResponseBody
	@RequestMapping("getAllCGArt.do")
	public String getAllCGArt(HttpServletRequest request,HttpServletResponse response){
		String cgName=request.getParameter("cgName");
		String auditStatus=request.getParameter("auditStatus");
		JSONObject result = cgArtManage.getAllCGArt(cgName,auditStatus);
		return result.toString();
	}
	//查询文章名称及封面
	@ResponseBody
	@RequestMapping("getCGArtTit.do")
	public String getCGArtTit(HttpServletRequest request,HttpServletResponse response){
		String articleId=request.getParameter("articleId");
		JSONObject result = cgArtManage.getCGArtTit(articleId);
		return result.toString();
	}
	
	//查相关书法家
	@ResponseBody
	@RequestMapping("searchCGInfo.do")
	public String searchCGInfo(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		JSONObject result = cgArtManage.searchCGInfo(articleId);
		return result.toString();
	}
	
	//查相关作品
	@ResponseBody
	@RequestMapping("searchCGWorksInfo.do")
	public String searchCGWorksInfo(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		JSONObject result = cgArtManage.searchCGWorksInfo(articleId);
		return result.toString();
	}
	
	//添加相关书法家
	@ResponseBody
	@RequestMapping("addNewCGF.do")
	public String addNewPhotogF(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String pgid = request.getParameter("pgid");
		JSONObject result = cgArtManage.addNewPhotogF(articleId,pgid);
		return result.toString();
	}
	//添加相关书法家作品
	@ResponseBody
	@RequestMapping("addNewCGWorksF.do")
	public String addNewWorksF(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String wkid = request.getParameter("wkid");
		JSONObject result = cgArtManage.addNewWorksF(articleId,wkid);
		return result.toString();
	}
	//获取文章的word、图片
	@ResponseBody
	@RequestMapping("getCGWordAndPic.do")
	public String getWordAndPic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		JSONObject result = cgArtManage.getWordAndPic(articleId);
		return result.toString();
	}
	//重新排序书法家、作品
	@ResponseBody
	@RequestMapping("reOrderCg.do")
	public String reOrderPhotog(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String reOrder = request.getParameter("reOrder");
		String opFlag = request.getParameter("opFlag");
		reOrder = reOrder.replace("[", "").replace("]", "");
		reOrder = reOrder.replace("\"", "");
		JSONObject result = cgArtManage.reOrderPhotog(articleId,reOrder,opFlag);
		return result.toString();
	}
	//添加审核状态
	@ResponseBody
	@RequestMapping("addCGAudit.do")
	public String addAuditing(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		String articleTile=request.getParameter("articleTile");
		JSONObject result = cgArtManage.addCGAudit(articleId,articleTile);
		return null;
	}
	//删除文章
	@ResponseBody
	@RequestMapping("deleteMessage.do")
	public String deleteMessage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		int result = cgArtManage.deleteMessage(articleId);
		return result+"";
	}
	//审核状态更新
	@ResponseBody
	@RequestMapping("changeCGAudit.do")
	public String changeCGAudit(HttpServletRequest request,HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String auditStatus = request.getParameter("auditStatus");
		String auditDesc = request.getParameter("auditDesc");
		String auditPersn = request.getParameter("auditPersn");
		String result = cgArtManage.changeCGAudit(articleId,auditStatus,auditDesc,auditPersn);
		return result.toString();
	}
	//获取所有审核状态
	@ResponseBody
	@RequestMapping("getcgauditstatus.do")
	public String getcgauditstatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String auditStatus = request.getParameter("auditStatus");
		JSONObject result = new JSONObject();
		result = cgArtManage.getcgauditstatus(auditStatus);
		return result.toString();
	}
	//连带删除书法家作品
	@ResponseBody
	@RequestMapping("deleteCgWorks.do")
	public String deleteCgWorks(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String deleteCgId=request.getParameter("deleteCgId");
		JSONObject result = cgArtManage.deleteCgWorks(deleteCgId);
		return result.toString();
	}
	//word解读
	@RequestMapping("cgArtWdShow.do")
	public ModelAndView cgArtWdShow(HttpServletRequest request, HttpServletResponse response) throws TransformerException, IOException, ParserConfigurationException{
		WordToHtml wTH=new WordToHtml();
		String articleId=request.getParameter("articleId");
		String flagId=request.getParameter("flagId");
		ModelAndView result=new ModelAndView();
		String result1=cgArtManage.cgArtWdShow(articleId,flagId);
		String worksintrodir=ServerFilePath.getWorksintrodir();//作品
		FileInputStream fis = null;
        try {
        	// 图片文件解密
        	fis = new FileInputStream(cgArtPath+"/"+result1);
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
	@RequestMapping("getCGArtImage.do")
	public String getCGArtImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String imgUrl=request.getParameter("imgUrl");
		String imgFlag=request.getParameter("imgFlag");
		FileInputStream fis = null;  
	    OutputStream os = null;  
		try {  
			//imgFlag 1- 文章封面路径  2-书法家标准照  3- 作品图片
			if ("1".equals(imgFlag)) {
				try {
					fis = new FileInputStream(cgArtPath+"/"+imgUrl);
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/defaultPic.jpg");
				}
			} else if ("2".equals(imgFlag)) {
				try {
					fis = new FileInputStream(cgPath+"/"+imgUrl);
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/default.jpg");
				}
			} else {
				try {
					fis = new FileInputStream(cgWorksPath+"/"+imgUrl);
				} catch (Exception e) {
					fis = new FileInputStream(sysPath+"/defaultPic.jpg");
				}
			}
	        // 图片文件解密
	        String key = EncryptKey.DesKey;
	        InputStream sbs = null;
	        try {
	        	sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
			} catch (Exception e) {
				if ("2".equals(imgFlag)) {
					sbs = new FileInputStream(new File(ServerFilePath.getSystemFilePath()+File.separator+"default.jpg"));
				}else {
					sbs = new FileInputStream(new File(ServerFilePath.getSystemFilePath()+File.separator+"defaultPic.jpg"));
				}
			}
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
	@RequestMapping("reupCGImg.do")
	public String reupCGImg(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		String filePath = cgArtPath+"/"+articleId+"/"+filename;
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
		result = cgArtManage.reupCGImg(rfilePath,articleId,fileType,coverOrfile);
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
	@RequestMapping(value="downCGFiles.do")
	public void downFiles(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String articleId=request.getParameter("articleId");
		String fileType=request.getParameter("fileType");
		String filePath= cgArtManage.getFilePath(articleId,fileType);
		String fileName="相关文章解读"+filePath.substring(filePath.lastIndexOf("."));
		if ("wordReading".equals(fileType)) {
			FileInputStream fis = new FileInputStream(cgArtPath+"/"+filePath);
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
		} else {
			String zipPath = CompressUtil_ztf.CompressLongCgArt(filePath);
			InputStream sbs = new FileInputStream(new File(zipPath));
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String("相关文章解读长图.zip".getBytes(), "iso-8859-1"));
			byte[] buff = new byte[1024];
			BufferedInputStream bis = null;
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				bis = new BufferedInputStream(sbs);
				int i = 0;
				while ((i = bis.read(buff)) != -1) {
					os.write(buff, 0, i);
					os.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				bis.close();
				os.close();
			}
		}
		
		
		
	}
	
}
