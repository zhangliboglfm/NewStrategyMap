package com.miapsoft.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class NewFileUtil {
    /**
     * 文件上传
     * @Title:upload
     * @Description:TODO
     * @param request
     * @throws IllegalStateException
     * @throws IOException
     * @author:白少华
     * @date:2017-4-25
     */
    public static String upload(HttpServletRequest request,String uoloadpath) {
		File dir = new File(uoloadpath);
		if(!dir.exists()){
		    dir.mkdir();
		}
		String fileName = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multipartRequest.getFileNames();
		while (iter.hasNext()) {
		    MultipartFile file = multipartRequest.getFile(iter.next());
		    if (file != null) {
					fileName = null;
				try {
				    fileName = new String (file.getOriginalFilename().getBytes("ISO-8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e2) {
				    e2.printStackTrace();
				}
				if(fileName == null){
				    continue;
				}
				String path = uoloadpath + File.separator + fileName;
				File localFile = new File(path);
				try {
					if(!localFile.exists()){
						file.transferTo(localFile);
					}
			    } catch (IOException e) {
			    	e.printStackTrace();
			    }
		    }
		}
		return fileName;
    }
}
