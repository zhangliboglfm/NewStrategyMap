/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
*/
package com.miapsoft.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.PgStandardImgManager;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.FileUtil;

@Controller
public class PgStandardImgController {

	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	private static String photogdir = ServerFilePath.getPhotogdir();//摄影家，头像，作品目录
	
	@Resource
	private PgStandardImgManager pgStandardImgManager;
	
	@RequestMapping("pgstandardimgaudit.do")
	public String pgstandardimgaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pgId", request.getParameter("pId"));
		return "pgmag/pgstandardimgaudit";
	}
	
	@RequestMapping("pgstandardimg.do")
	public String pgstandardimg(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pgId", request.getParameter("pId"));
		return "pgmag/pgstandardimg";
	}
	
	///获取摄影家标准照
	@ResponseBody
	@RequestMapping("selStandImgs.do")
	public String selStandImgs(HttpServletRequest request,HttpServletResponse response) {
		String pgId=request.getParameter("pgId");
		String result=pgStandardImgManager.selStandImgs(pgId);
		return result;
	}
	///显示摄影家标准照
	@ResponseBody
	@RequestMapping(value="downStandImg.do")
	public void downStandImg(HttpServletRequest request,HttpServletResponse response) {
		String fileName = request.getParameter("fileName");//标准照相对路径
		String filepath= ServerFilePath.getPhotogdir()+File.separator+fileName;
		String allowpath=ServerFilePath.getPhotogdir();
		FileUtil.decryptDownload(fileName, filepath,allowpath, response, request);
	}
	
	/**
	 * 标准照上传至服务器临时路径
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadStandPic.do")
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
		String src="downStandImg.do?fileName="+rfilename;
		try {
			
			/*DesUtil.encryptFile(EncryptKey.DesKey, request.getInputStream(),new FileOutputStream(newfile));*/
			JSONObject jsonresult=FileUtil.encryptSaveImg(request, photogdir,photogdir + File.separator	+ rfilename);
			
			if ("true".equals(jsonresult.getString("istrue"))) {
				JSONObject addresult=pgStandardImgManager.addStandImg(pgId, rfilename);
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
					File newfile = new File(photogdir + File.separator	+ rfilename);
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
			File newfile = new File(photogdir + File.separator	+ rfilename);
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
	
	///获取摄影家标准照
	@ResponseBody
	@RequestMapping("updateStandImgOrder.do")
	public String updateStandImgOrder(HttpServletRequest request,HttpServletResponse response) {
		String pgId=request.getParameter("pgId");
		String fileName=request.getParameter("fileName");
		String newOrder=request.getParameter("newOrder");
		String result=pgStandardImgManager.updateStandImgOrder(pgId,fileName,newOrder);
		return result;
	}
	
	///获取摄影家标准照
	@ResponseBody
	@RequestMapping("delStandImg.do")
	public String delStandImg(HttpServletRequest request,HttpServletResponse response) {
		String pgId=request.getParameter("pgId");
		String fileName=request.getParameter("fileName");
		String result=pgStandardImgManager.delStandImg(pgId,fileName);
		return result;
	}
}
