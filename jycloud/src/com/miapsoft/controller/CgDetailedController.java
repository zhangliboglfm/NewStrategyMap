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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.miapsoft.manager.CgDetailedManager;
import com.miapsoft.manager.PhotographerMainManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;

@Controller
public class CgDetailedController {

	@Resource
	private CgDetailedManager detailed;

	@RequestMapping("calligrapherDetailed.do")
	public String articlemain(HttpServletRequest request, HttpServletResponse response){
		String cgId=request.getParameter("cgId");
		String userId=request.getSession().getAttribute("userId")+"";
		String auditStatus=request.getParameter("auditStatus");
		request.setAttribute("cgId", cgId);
		request.setAttribute("userId", userId);
		request.setAttribute("auditStatus", auditStatus);
		return "cgmag/calligrapherDetailed";
	}
	
	@RequestMapping("calligDetailedAudit.do")
	public String calligDetailedAudit(HttpServletRequest request, HttpServletResponse response){
		String cgId=request.getParameter("cgId");
		String userId=request.getSession().getAttribute("userId")+"";//auditStatus
		String auditStatus=request.getParameter("auditStatus");
		request.setAttribute("cgId", cgId);
		request.setAttribute("userId", userId);
		request.setAttribute("auditStatus", auditStatus);
		return "cgmag/calligDetailedAudit";
	}

	/**
	 * 获取全部民族信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getDynastys.do")
	public String getpgallnation(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = detailed.getDynastys();
		return result.toString();
	}

	/**
	 * 获取全部朝代信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getNation.do")
	public String getNation(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = detailed.getNation();
		return result.toString();
	}
	//查询地位码表
	@ResponseBody
	@RequestMapping("getAgName.do")
	public String getAgName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = detailed.getAgName();
		return result.toString();
	}
	//查询书法家所有的作品
	@ResponseBody
	@RequestMapping("getAllCGWorks.do")
	public String getAllArtLabel(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cgId");
		String cgWkName = request.getParameter("cgWkName");
		JSONObject result = detailed.getAllCGWorks(cgId,cgWkName);
		return result.toString();
	}
	//对书法家作品进行操作
	@ResponseBody
	@RequestMapping("updateWorksI.do")
	public String updateWorksI(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cgId");
		String arr = request.getParameter("arr");
		int result = detailed.updateWorksI(cgId,arr);
		return result+"";
	}
	//保存书法家头像信息
	@ResponseBody
	@RequestMapping("saveHeadImg.do")
	public String saveHeadImg(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cgId");
		String headImgP = request.getParameter("headImgP");
		int result = detailed.saveHeadImg(cgId,headImgP);
		return result+"";
	}
	//审核状态更新
	@ResponseBody
	@RequestMapping("auditCallig.do")
	public String auditCallig(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cgId");
		String auditStatus = request.getParameter("auditStatus");
		String auditDesc = request.getParameter("auditDesc");
		String auditPersn = request.getParameter("auditPersn");
		String result = detailed.auditCallig(cgId,auditStatus,auditDesc,auditPersn);
		return result.toString();
	}
	//对书法家作品进行操作
	@ResponseBody
	@RequestMapping("updateCgBaseInfo.do")
	public String updateCgBaseInfo(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cgId");
		String cgName = request.getParameter("cgName");//书法家名称
		String iptCgSecName = request.getParameter("iptCgSecName");//书法家字
		String iptCgNickName = request.getParameter("iptCgNickName");//书法家号
		String iptCgPhotogSex = request.getParameter("iptCgPhotogSex");//书法家性别
		String iptCgNation = request.getParameter("iptCgNation");//书法家民族
		String iptCgBornTime = request.getParameter("iptCgBornTime");//书法家出生时间
		String iptCgDynasty = request.getParameter("iptCgDynasty");//书法家朝代
		String iptCgAgName = request.getParameter("iptCgAgName");//书法家成就、地位
		String iptCgBirthPlace = request.getParameter("iptCgBirthPlace");//书法家出生地
		String iptCgDescent = request.getParameter("iptCgDescent");//书法家祖籍
		String iptCgAlias = request.getParameter("iptCgAlias");//书法家别称
		String iptCgDeathTime = request.getParameter("iptCgDeathTime");//书法家去世时间
		String iptCgImptDeeds = request.getParameter("iptCgImptDeeds");//书法家重要事迹
		int result = detailed.updateCgBaseInfo(cgId,cgName,iptCgSecName,iptCgNickName,iptCgPhotogSex,iptCgNation,iptCgBornTime,iptCgDynasty,iptCgAgName,iptCgBirthPlace
												,iptCgDescent,iptCgAlias,iptCgDeathTime,iptCgImptDeeds);
		return result+"";
	}
	/**
	 * 获取书法家基础信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getCgBaseinfo.do")
	public String getCgBaseinfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		String cgId = request.getParameter("cgId");
		result = detailed.getDerpherBaseInfo(cgId);
		return result.toString();
	}
	private static String CGIMGPATH=ServerFilePath.getCalligdir()+"/";//摄影家头像,作品使用
	private static String ERRORIMAGEPATH = ServerFilePath.getSystemFilePath()+"/"+"default.jpg";

	//展示生平、成就、风格部分图片用
	@ResponseBody
	@RequestMapping("getCGImgStream.do")
	public String getPGImgStream(HttpServletRequest request, HttpServletResponse response){
		FileInputStream fis = null;  
		OutputStream os = null;  
		InputStream sbs = null;
		String filename = request.getParameter("filename");
		String absPath = CGIMGPATH+filename;
		File cfile = new File(absPath);
		if(!cfile.exists()){
			absPath = ERRORIMAGEPATH;
		}
		try { 
			fis = new FileInputStream(absPath);
			// 图片文件解密
			String key = EncryptKey.DesKey;
			try {
				sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
			} catch (Exception e) {
				sbs = new FileInputStream(new File(ERRORIMAGEPATH));
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
			sbs.close();
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return null; 
	}
	//重新上传书法家头像
	@RequestMapping("reupCgHead.do")
	public String reupCgHead(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		String leixing = request.getParameter("leixing");
		String filename = request.getParameter("filename");
		String cgId = request.getParameter("cgId");
		filename = URLDecoder.decode(filename,"UTF-8");
		filename=filename.substring(0,filename.lastIndexOf("."));
		filename = EncryptionUtil.ENCRYPTSTRING(filename)+"."+leixing;
		/*String filePath = articlePath+"/"+articleId+"/"+filename;
			String rfilePath = articleId+"/"+filename;*/
		String filePath=CGIMGPATH+"/"+cgId+"/head/"+filename;
		String rfilePath = cgId+"/head/"+filename;
		File outFile = new File(filePath);
		File pFile = outFile.getParentFile();
		//判断文件夹是否存在
		if (pFile != null && !pFile.exists()) {
			pFile.mkdirs();
		}
		//文件加密
		String key = EncryptKey.DesKey;
		DesUtil.encryptFile(key,new FileInputStream(f),new FileOutputStream(outFile));
		response.setContentType("text/html;charset=gbk"); 
		PrintWriter out = response.getWriter(); 
		out.print(rfilePath.toString());
		out.flush();
		out.close();
		return null;
	}
}
