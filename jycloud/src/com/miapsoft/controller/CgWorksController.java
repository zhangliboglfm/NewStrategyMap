package com.miapsoft.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.miapsoft.manager.CgWorksManager;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.FileUtil;

@Controller
public class CgWorksController {
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	private static String calligdir = ServerFilePath.getCalligdir();//摄影家，头像，作品目录
	@Resource
	CgWorksManager cgWorksManager;
	
	@RequestMapping("cgworks")
	public String cgworks(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String cgerId=request.getParameter("cgerId");//书法家标识
		request.setAttribute("cgerId", cgerId);
		return "cgmag/cgworks";
	}
	
	@RequestMapping("cgworksaudit")
	public String cgworksaudit(HttpServletRequest request , HttpServletResponse response){
		String cgerId=request.getParameter("cgerId");//书法家标识
		request.setAttribute("cgerId", cgerId);
		return "cgmag/cgworksaudit";
	}
	//获取作品
	@ResponseBody
	@RequestMapping("getPagenum.do")
	public String getPagenum(HttpServletRequest request ,HttpServletResponse response){
		String cgerId=request.getParameter("cgerId");
		String workname=request.getParameter("workname");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		JSONObject result=cgWorksManager.getPagenum(cgerId,workname,pageNum,pageSize);
		return result.toString();
	}
	
	//获取作品
	@ResponseBody
	@RequestMapping("getauditPagenum.do")
	public String getauditPagenum(HttpServletRequest request ,HttpServletResponse response){
		String cgerId=request.getParameter("cgerId");
		String workname=request.getParameter("workname");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		String status=request.getParameter("status");
		JSONObject result=cgWorksManager.getauditPagenum(cgerId,workname,pageNum,pageSize,status);
		return result.toString();
	}
	
	//按照页码查找重要作品
	@ResponseBody
	@RequestMapping("getimpworks.do")
	public String getimpworks(HttpServletRequest request ,HttpServletResponse response){
		String cgerId=request.getParameter("cgerId");
		String workname=request.getParameter("workname");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		JSONObject result=cgWorksManager.getimpworks(cgerId,workname,pageNum,pageSize);
		return result.toString();
	}
	//按照页码查找其它作品
	@ResponseBody
	@RequestMapping("getothworks.do")
	public String getothworks(HttpServletRequest request ,HttpServletResponse response){
		String cgerId=request.getParameter("cgerId");
		String workname=request.getParameter("workname");
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		JSONObject result=cgWorksManager.getothworks(cgerId,workname,pageNum,pageSize);
		return result.toString();
	}
	//跳转到作品详情
	@RequestMapping("worksDetail")
	public String worksDetail(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String workid=request.getParameter("workId");//作品标识
		String isImpt=request.getParameter("isImpt");//是否重要
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		request.setAttribute("isImpt", isImpt);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/worksDetail";
	}
	//合集中跳转到作品详情
	@RequestMapping("worksDetailCPG")
	public String worksDetailCPG(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String workid=request.getParameter("workId");//作品标识
		String isImpt=request.getParameter("isImpt");//是否重要
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		request.setAttribute("isImpt", isImpt);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/worksDetailCPG";
	}
	
	//合集中跳转到作品详情
	@RequestMapping("worksDetailaduit")
	public String worksDetailaduit(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String workid=request.getParameter("workId");//作品标识
		String isImpt=request.getParameter("isImpt");//是否重要
		String workstatus=request.getParameter("workstatus");
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		request.setAttribute("isImpt", isImpt);
		request.setAttribute("workstatus", workstatus);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/worksDetailaduit";
	}
	//合集中跳转到作品详情
	@RequestMapping("worksDetailRegress")
	public String worksDetailRegress(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String workid=request.getParameter("workId");//作品标识
		String isImpt=request.getParameter("isImpt");//是否重要
		String workstatus=request.getParameter("workstatus");
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		request.setAttribute("isImpt", isImpt);
		request.setAttribute("workstatus", workstatus);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/worksDetailRegress";
	}	
	//作品详细信息页面获取作品
	@ResponseBody
	@RequestMapping("getworkslist")
	public String getworkslist(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String isimport=request.getParameter("isimport");//作品标识
		JSONObject result=cgWorksManager.getworkslist(cgerid,isimport);
		return result.toString();
	}
	
	//合集详细信息页面获取作品
	@ResponseBody
	@RequestMapping("getworkslistCPG")
	public String getworkslistCPG(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String isimport=request.getParameter("isimport");//作品标识
		String workId=request.getParameter("workId");//作品标识
		JSONObject result=cgWorksManager.getworkslistCPG(cgerid,isimport,workId);
		return result.toString();
	}	
	//作品审核详细信息页面获取作品
	@ResponseBody
	@RequestMapping("getauditworkslist")
	public String getauditworkslist(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String isimport=request.getParameter("isimport");//作品标识
		String workstatus=request.getParameter("workstatus");
		JSONObject result=cgWorksManager.getauditworkslist(cgerid,isimport,workstatus);
		return result.toString();
	}
	
	//作品审核详细信息页面获取作品
	@ResponseBody
	@RequestMapping("getauditworkslist2")
	public String getauditworkslist2(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgerId");//书法家标识
		String isimport=request.getParameter("isimport");//作品标识
		JSONObject result=cgWorksManager.getauditworkslist2(cgerid,isimport);
		return result.toString();
	}
	//作品详细信息
	@ResponseBody
	@RequestMapping("getworkinfors")
	public String getworkinfors(HttpServletRequest request , HttpServletResponse response){
		String workid=request.getParameter("workid");//作品标识
		String cgid=request.getParameter("cgid");//书法家标识
		/*String workid="test0003";//作品标识
		String cgid="CG_M1011300002";//书法家标识*/
		JSONObject result=cgWorksManager.getworkinfors(workid,cgid);
		return result.toString();
	}
	
	//更新详细信息
	@ResponseBody
	@RequestMapping("updateworkinfors")
	public String updateworkinfors(HttpServletRequest request , HttpServletResponse response){
		String workid=request.getParameter("workid");//作品标识
		String cgid=request.getParameter("cgid");//书法家标识
		/*String workid="test0003";//作品标识
		String cgid="CG_M1011300002";//书法家标识*/
		String worksname=request.getParameter("worksname");
		String wholename=request.getParameter("wholename");
		String years=request.getParameter("years");
		String cgtype=request.getParameter("cgtype");
		String worknum=request.getParameter("worknum");
		String spec=request.getParameter("spec");
		String pstcoll=request.getParameter("pstcoll");
		if(cgWorksManager.updateworkinfors(workid,cgid,worksname,wholename,years,cgtype,worknum,spec,pstcoll)){	
			return "保存成功";
		}else{		
			return "保存失败";
		}
		
	}
	
	//跳转到作品图片编辑页面
	@RequestMapping("cgworkseditor")
	public String cgworkseditor(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgid");//书法家标识
		String workid=request.getParameter("workid");//作品标识
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/cgworkseditor";
	}
	
	//合集中到作品图片编辑页面
	@RequestMapping("cgworkseditorCPG")
	public String cgworkseditorCPG(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgid");//书法家标识
		String workid=request.getParameter("workid");//作品标识
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/cgworkseditorCPG";
	}
	
	//作品图片审核编辑页面
	@RequestMapping("cgworkseditoraduit")
	public String cgworkseditoraduit(HttpServletRequest request , HttpServletResponse response){
		String cgerid=request.getParameter("cgid");//书法家标识
		String workid=request.getParameter("workid");//作品标识
		request.setAttribute("cgerId", cgerid);
		request.setAttribute("workId", workid);
		//JSONObject result=cgWorksManager.getWorksDetail(cgerid,workid);
		return "cgmag/cgworkseditoraduit";
	}	
	//作品图片
	@ResponseBody
	@RequestMapping("getCGImgs")
	public String getCGImgs(HttpServletRequest request , HttpServletResponse response){
		String workid=request.getParameter("workId");//作品标识
		String cgid=request.getParameter("cgerId");//书法家标识
		/*String workid="test0003";//作品标识
		String cgid="CG_M1011300002";//书法家标识*/
		JSONObject result=cgWorksManager.getCGImgs(workid,cgid);
		return result.toString();
	}
	
	///删除作品图片
	@ResponseBody
	@RequestMapping("delworksImg.do")
	public String delStandImg(HttpServletRequest request,HttpServletResponse response) {
		String workid=request.getParameter("workid");
		String fileName=request.getParameter("fileName");
		String result=cgWorksManager.delworksImg(workid,fileName);
		return result;
	}
	///删除所有作品图片
	@ResponseBody
	@RequestMapping("delallworksImg.do")
	public String delallStandImg(HttpServletRequest request,HttpServletResponse response) {
		String workid=request.getParameter("workid");
		String result=cgWorksManager.delallworksImg(workid);
		return result;
	}
	
	///获取摄影家标准照
	@ResponseBody
	@RequestMapping("updateworksImgOrder.do")
	public String updateStandImgOrder(HttpServletRequest request,HttpServletResponse response) {
		String pgId=request.getParameter("pgId");
		String fileName=request.getParameter("fileName");
		String newOrder=request.getParameter("newOrder");
		String result=cgWorksManager.updateworksImgOrder(pgId,fileName,newOrder);
		return result;
	}
	
	//显示作品图片
	@ResponseBody
	@RequestMapping(value="downCGworkImg.do")
	public void downCGworkImg(HttpServletRequest request,HttpServletResponse response) {
		String fileName = request.getParameter("fileName");//标准照相对路径
		String filepath= ServerFilePath.getCalligdir()+File.separator+fileName;
		String allowpath=ServerFilePath.getCalligdir();
		FileUtil.decryptDownload(fileName, filepath,allowpath, response, request);
	}
	//作品上传
	@RequestMapping(value="cgworksupload.do")
	public String cgworksupload(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String workId = request.getParameter("workId");
		String cgerId = request.getParameter("cgerId");
		request.setAttribute("workId", workId);
		request.setAttribute("cgerId", cgerId);
		return "cgmag/cgworksupload";
	}
	
	
	/**
	 * 作品图片上传至服务器临时路径
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadworksPic.do")
	public String uploadStandPic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		String fileName = request.getParameter("fileName");
		String pgId=request.getParameter("pgId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件
		String tmpfilesuffix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
		String newfilename = EncryptionUtil.ENCRYPTSTRING("S"
				+ sdf.format(new Date()))
				+ "." + tmpfilesuffix;//重命名
		String rfilename = pgId + File.separator + newfilename;//文件相对路径
		/*File newfile = new File(photogdir + File.separator	+ rfilename);
		
		File parent = newfile.getParentFile();
		//判断文件夹是否存在
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		//判断文件夹是否已存在
		if (!newfile.exists()) {
			newfile.createNewFile();
		} else {
			newfile.delete();
		}*/
		
		
		String code="0";
		String msg="上传成功";
		String order="";
		String src="downCGworkImg.do?fileName="+rfilename;
		try {
			
			/*DesUtil.encryptFile(EncryptKey.DesKey, request.getInputStream(),new FileOutputStream(newfile));*/
			JSONObject jsonresult=FileUtil.encryptSaveImg(request, calligdir,calligdir + File.separator	+ rfilename);
			
			if ("true".equals(jsonresult.getString("istrue"))) {
				JSONObject addresult=cgWorksManager.addworksImg(pgId, rfilename);
				if (Integer.parseInt(addresult.getString("result"))>0) {
					code="0";
					msg="上传成功";
					order=addresult.getString("order");
					src=rfilename;
				}else{
					code="1";
					msg="上传失败";
					order="";
					src="";	
					File newfile = new File(calligdir + File.separator	+ rfilename);
					if (newfile.exists()) {
						newfile.delete();
					}
				}
			}else {
				code="1";
				msg="上传失败";
				order="";
				src="";	
			}
			
		} catch (Exception e) {
			code="1";
			msg="上传失败";
			order="";
			src="";
			File newfile = new File(calligdir + File.separator	+ rfilename);
			if (newfile.exists()) {
				newfile.delete();
			}
		}
		
		
		
		
		JSONObject object=new JSONObject();
		object.put("code", code);
		object.put("msg", msg);
		JSONObject tmpObject = new JSONObject();
		tmpObject.put("src", src);
		tmpObject.put("order", order);
		object.put("data", tmpObject);
		
		
		return object.toString();
	}
	/**
	 * 替换作品图片上传至服务器临时路径
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("changeworksPic.do")
	public String changeStandPic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		String fileName ="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multipartRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile file = multipartRequest.getFile(iter.next());
			fileName = new String (file.getOriginalFilename().getBytes("ISO-8859-1"),"utf-8");
		}
		String fileName2 = request.getParameter("fileName2");
		String pgId=request.getParameter("pgId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件
		String tmpfilesuffix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
		String newfilename = EncryptionUtil.ENCRYPTSTRING("S"
				+ sdf.format(new Date()))
				+ "." + tmpfilesuffix;//重命名
		String rfilename = pgId + File.separator + "head"
				+ File.separator + newfilename;//文件相对路径
		/*File newfile = new File(photogdir + File.separator	+ rfilename);
		
		File parent = newfile.getParentFile();
		//判断文件夹是否存在
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		//判断文件夹是否已存在
		if (!newfile.exists()) {
			newfile.createNewFile();
		} else {
			newfile.delete();
		}*/
		
		
		String code="0";
		String msg="上传成功";
		String order="";
		String src="downCGworkImg.do?fileName="+rfilename;
		try {
			
			/*DesUtil.encryptFile(EncryptKey.DesKey, request.getInputStream(),new FileOutputStream(newfile));*/
			JSONObject jsonresult=FileUtil.encryptSaveImg(request, calligdir,calligdir + File.separator	+ rfilename);
			
			if ("true".equals(jsonresult.getString("istrue"))) {
				JSONObject addresult=cgWorksManager.changeworksImg(pgId,fileName2,rfilename);
				if (Integer.parseInt(addresult.getString("result"))>0) {
					code="0";
					msg="上传成功";
					order=addresult.getString("order");
					src=rfilename;
				}else{
					code="1";
					msg="上传失败";
					order="";
					src="";	
					File newfile = new File(calligdir + File.separator	+ rfilename);
					if (newfile.exists()) {
						newfile.delete();
					}
				}
			}else {
				code="1";
				msg="上传失败";
				order="";
				src="";	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			code="1";
			msg="上传失败";
			order="";
			src="";
			File newfile = new File(calligdir + File.separator	+ rfilename);
			if (newfile.exists()) {
				newfile.delete();
			}
		}
		
		
		
		
		JSONObject object=new JSONObject();
		object.put("code", code);
		object.put("msg", msg);
		JSONObject tmpObject = new JSONObject();
		tmpObject.put("src", src);
		tmpObject.put("order", order);
		object.put("data", tmpObject);
		
		
		return object.toString();
	}
		
}
