package com.miapsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.MessagecheckManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.FileUtil;


@Controller
public class MessagecheckController {
	
	public static String IMGPATH=ServerFilePath.getPhotogdir()+"/";//"D:/resourcelib/photographer/";
			
	
	@Resource
	public MessagecheckManager messagecheckManager;
	
	/**
	 * 回传Img图片
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPhtotogImg.do")
	public String getPhtotogImg(HttpServletRequest request, HttpServletResponse response){
		FileInputStream fis = null;  
	    OutputStream os = null;  
		String filename = request.getParameter("filename");
		try {  
	        fis = new FileInputStream(IMGPATH+filename);
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
	};
	
	/**
	 * 信息查看
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("messagecheck.do")
	public String messagecheck(HttpServletRequest request, HttpServletResponse response){
		return "jsp/messagecheck";
	};
	
	
	/**
	 * 获取摄影家详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPhotogdesc.do")
	public String getPhotogdesc(HttpServletRequest request, HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		JSONObject result = messagecheckManager.getPhotogdesc(PHOTOG_ID);
		return result.toString();
	};
	
	
	/**
	 * 更新摄影家头像
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("headImg.do")
	public String  standardImg(HttpServletRequest request,HttpServletResponse response){
		JSONObject result =new JSONObject();
		String PHOTOG_ID =request.getParameter("PHOTOG_ID");
		JSONObject saveResult = FileUtil.SaveImgEncrypt(request,IMGPATH+PHOTOG_ID+"/headimg");
		if("false".equals(saveResult.get("istrue"))){
			result.put("code", 0);
			return result.toString();
		};
		String filename = PHOTOG_ID+"/headimg/"+saveResult.getString("filename");
		boolean istrue =messagecheckManager.updateHeadImg(PHOTOG_ID,filename);
		if(!istrue){
			result.put("code", 0);
			return result.toString();
		}else{
			result.put("code", 1);
			result.put("filename",filename);
			return result.toString();
		}
	};
	
	
	/**
	 * 上传摄影家标准照
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uploadStrandImg.do")
	public String  uploadStrandImg(HttpServletRequest request,HttpServletResponse response){
		JSONObject result =new JSONObject();
		String PHOTOG_ID =request.getParameter("PHOTOG_ID");
		JSONObject saveResult = FileUtil.SaveImgEncrypt(request,IMGPATH+PHOTOG_ID+"/headimg");
		if("false".equals(saveResult.get("istrue"))){
			result.put("code", 0);
			return result.toString();
		};
		String filename = PHOTOG_ID+"/headimg/"+saveResult.getString("filename");
		boolean istrue =messagecheckManager.uploadStrandImg(PHOTOG_ID,filename);
		if(!istrue){
			result.put("code", 0);
			return result.toString();
		}else{
			result.put("code", 1);
			result.put("filename",filename);
			return result.toString();
		}
	};
	
	
	/**
	 * 根据条件获取摄影家列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("getPhotoglist.do")
	public String getPhotoglist(HttpServletRequest request,HttpServletResponse response){
		String photogName = request.getParameter("photogName");
		String dealTime = request.getParameter("dealTime");
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		JSONObject result = messagecheckManager.getPhotoglist(photogName,dealTime,pageSize,pageNum);
		return result.toString();
	}
	/**
	 * 保存摄影家基本信息
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("savePhotoDesc.do")
	public String savePhotoDesc(HttpServletRequest request,HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String param = request.getParameter("param");
		String [] params = param.replace("[", "").replace("]", "").replace("\"", "").split(",");
		String result = messagecheckManager.savePhotoDesc(PHOTOG_ID,params);
		return result;
	}
	
	/**
	 * 设置摄影家审核状态
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("changeStatusData.do")
	public String changeStatusData(HttpServletRequest request,HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String AUDIT_DESC = request.getParameter("AUDIT_DESC");
		String AUDIT_PERSN = request.getParameter("AUDIT_PERSN");// 用户账号
		String AUDIT_STATUS = request.getParameter("AUDIT_STATUS");
		String result = messagecheckManager.changeStatusData(PHOTOG_ID,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN);
		return result;
	}
	
	/**
	 * 获取标签信息
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("getLablist.do")
	public String getLablist(HttpServletRequest request,HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String tagName = request.getParameter("tagName");
		JSONArray result = messagecheckManager.getLablist(PHOTOG_ID,tagName);
		return result.toString();
	}
	
	
	/**
	 * 对摄影家标签进行增，删
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("operateLabel.do")
	public String operateLabel(HttpServletRequest request,HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String LABEL_ID = request.getParameter("LABEL_ID");
		String operate = request.getParameter("operate");
		String result = messagecheckManager.operateLabel(PHOTOG_ID,LABEL_ID,operate);
		return result;
	}
	
	
	/**
	 * 删除此标准照片
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteThisStrandImg.do")
	public String deleteThisStrandImg(HttpServletRequest request,HttpServletResponse response){
		String imgname = request.getParameter("imgname");
		String result = messagecheckManager.deleteThisStrandImg(imgname);
		return result;
	}
	
	
	/**
	 * 更改照片显示状态
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeShowStatus.do")
	public String changeShowStatus(HttpServletRequest request,HttpServletResponse response){
		String imgname = request.getParameter("imgname");
		String status = request.getParameter("status");
		String result = messagecheckManager.changeShowStatus(imgname,status);
		return result;
	}
	
	
	/**
	 * 更新标准照显示顺序
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateStrandOrder.do")
	public String updateStrandOrder(HttpServletRequest request,HttpServletResponse response){
		String imgnames = request.getParameter("imgnames");
		String [] imgnamearr = imgnames.replace("[", "").replace("]", "").replace("\"", "").split(",");
		String result = messagecheckManager.updateStrandOrder(imgnamearr);
		return result;
	}
	
	/**
	 * 添加自定义标签
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addDefinedTag.do")
	public String addDefinedTag(HttpServletRequest request,HttpServletResponse response){
		String PHOTOG_ID = request.getParameter("PHOTOG_ID");
		String tagName = request.getParameter("tagName");
		String tagDesc = request.getParameter("tagDesc");
		JSONObject result = messagecheckManager.addDefinedTag(PHOTOG_ID,tagName,tagDesc);
		return result.toString();
	}
	
	
	
	

	

	public MessagecheckManager getMessagecheckManager() {
		return messagecheckManager;
	}
	public void setMessagecheckManager(MessagecheckManager messagecheckManager) {
		this.messagecheckManager = messagecheckManager;
	};
	
	
}
