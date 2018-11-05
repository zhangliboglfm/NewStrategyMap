/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
*/
package com.miapsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;

@Controller
public class ImageStreamController {

	private static String PGIMGPATH=ServerFilePath.getPhotogdir()+"/";//摄影家头像,作品使用
	private static String ARTIMGPATH=ServerFilePath.getArticledir()+"/";//摄影家头像,作品使用
	
	private static String ERRORIMAGEPATH = ServerFilePath.getSystemFilePath()+"/"+"default.jpg";
	
	/**
	 * 获取摄影家Img图片流,前台展示
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPGImgStream.do")
	public String getPGImgStream(HttpServletRequest request, HttpServletResponse response){
		FileInputStream fis = null;  
	    OutputStream os = null;  
	    InputStream sbs = null;
		String filename = request.getParameter("filename");
		String absPath = PGIMGPATH+filename;
		File cfile = new File(absPath);
		if(!cfile.exists()){
			absPath = ERRORIMAGEPATH;
		}
		try { 
			fis = new FileInputStream(absPath);
			// 图片文件解密
			String key = EncryptKey.DesKey;
			sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
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
	        sbs.close();
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    return null; 
	}

	/**
	 * 获取文章Img图片流,前台展示
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getARTImgStream.do")
	public String getARTImgStream(HttpServletRequest request, HttpServletResponse response){
		FileInputStream fis = null;  
	    OutputStream os = null;  
		String filename = request.getParameter("filename");
		try {  
	        fis = new FileInputStream(ARTIMGPATH+filename);
	        // 图片文件解密
	        String key = EncryptKey.DesKey;
	        InputStream sbs = new ByteArrayInputStream( DesUtil.decryptFile(key,fis));
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
	 * @return the pGIMGPATH
	 */
	public static String getPGIMGPATH() {
		return PGIMGPATH;
	}

	/**
	 * @param pGIMGPATH the pGIMGPATH to set
	 */
	public static void setPGIMGPATH(String pGIMGPATH) {
		PGIMGPATH = pGIMGPATH;
	}

	/**
	 * @return the aRTIMGPATH
	 */
	public static String getARTIMGPATH() {
		return ARTIMGPATH;
	}

	/**
	 * @param aRTIMGPATH the aRTIMGPATH to set
	 */
	public static void setARTIMGPATH(String aRTIMGPATH) {
		ARTIMGPATH = aRTIMGPATH;
	};
}
