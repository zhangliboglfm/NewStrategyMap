package com.miapsoft.controller;
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

import com.miapsoft.manager.JieduManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.OnlineRead;
import com.miapsoft.util.WordToHtml;



@Controller
public class JieduController {
	@Resource
	public JieduManager jieduManager;

	@RequestMapping(value="productionJiedu.do")
	public String Main(HttpServletRequest request, HttpServletResponse response){
		String articleId = request.getParameter("articleId");
		String photogid = request.getParameter("photogid");
		String flagId = request.getParameter("flagId");
		String wordPath = "";
		String picPath = "";
		String h5Path = "";
		JSONObject result = jieduManager.getJieduInfo(articleId);
		JSONArray obj = result.getJSONArray("data");
		if (obj.size()!=0) {
			wordPath=obj.getJSONObject(0).get("ARTICLE_DOC").toString();
			picPath=obj.getJSONObject(0).get("ARTICLE_PIC").toString();
			h5Path=obj.getJSONObject(0).get("ARTICLE_URL").toString();
		}
		request.setAttribute("articleId", articleId);
		request.setAttribute("wordPath", wordPath);
		request.setAttribute("picPath", picPath);
		request.setAttribute("h5Path", h5Path);
		request.setAttribute("photogid", photogid);
		request.setAttribute("flagId", flagId);
		return "jsp/productionJiedu";
	}

	@RequestMapping("JieduWorksWord.do")
	public ModelAndView JieduWorksWord(HttpServletRequest request, HttpServletResponse response) throws TransformerException, IOException, ParserConfigurationException{
		WordToHtml wTH=new WordToHtml();
		String articleId=request.getParameter("articleId");
		String flagId=request.getParameter("flagId");
		ModelAndView result=new ModelAndView();
		String result1=jieduManager.getAreInterpret(articleId,flagId);
		String worksintrodir=ServerFilePath.getWorksintrodir();//作品
		FileInputStream fis = null;
        try {
        	// 图片文件解密
        	fis = new FileInputStream(worksintrodir+"/"+result1);
            String key = EncryptKey.DesKey;
			InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
			result = OnlineRead.OnlineReadWord(sbs,result1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//result=wTH.wordToHtml(worksintrodir+"/"+result1, "H:/testword/", "123.html");
		return result;
	}
	//重新上传解读文章或图片
	@RequestMapping("reuploadJDu.do")
	public String reuploadJDu(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		String filename = request.getParameter("filename");
		String articleId = request.getParameter("articleId");
		String xuanlei = request.getParameter("xuanlei");
		String photogid = request.getParameter("photogid");
		String flagId = request.getParameter("flagId");
		String linshiPath=ServerFilePath.getWorksintrodir();
		String leixing = filename.substring(filename.lastIndexOf(".")+1);
		filename = (URLDecoder.decode(filename,"UTF-8")).substring(0,filename.lastIndexOf("."));
		filename = EncryptionUtil.ENCRYPTSTRING(filename)+"."+leixing;
		String filePath = linshiPath+"/"+photogid+"/works/"+filename;
		String rfilePath = photogid+"/works/"+filename;
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
		result = jieduManager.reuploadJDu(rfilePath,articleId,xuanlei,flagId);
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
	//查某一页内容
	@ResponseBody
	@RequestMapping("getJieDuImage.do")
	public String getJieDuImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String linshiPath=ServerFilePath.getWorksintrodir();
		String imgUrl=request.getParameter("imgUrl");
		System.out.println(linshiPath+"/"+imgUrl);
		FileInputStream fis = null;  
		OutputStream os = null;  
		try {  
			fis = new FileInputStream(linshiPath+"/"+imgUrl);
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
}