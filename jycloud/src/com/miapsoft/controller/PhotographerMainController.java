/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-8
*/
package com.miapsoft.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.PhotographerMainManager;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.FileTransferUtil;
import com.miapsoft.util.InputStreamToFile;

@Controller
public class PhotographerMainController {
	
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	private static String photogdir = ServerFilePath.getPhotogdir();//摄影家，头像，作品目录
	
	@Resource
	private PhotographerMainManager pgmanager;
	
	/**
	 * 摄影家主页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws ServletException 
	 */
	@RequestMapping("photographermain.do")
	public String photographermain(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pId", request.getParameter("pId"));
		return "pgmag/photographermain";
	}
	
	/**
	 * 获取摄影家基础信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getpgbaseinfo.do")
	public String getpgbaseinfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		String pId = request.getParameter("pgId");
		result = pgmanager.getPhotographerBaseInfo(pId);
		return result.toString();
	}
	
	/**
	 * 获取全部国家信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getpgallnation.do")
	public String getpgallnation(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = pgmanager.getAllNationInfo();
		return result.toString();
	}
	
	/**
	 * 获取全部标签信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getpgalllable.do")
	public String getpgalllable(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONArray result = new JSONArray();
		result = pgmanager.getAllPgLbaleInfo();
		return result.toString();
	}
	
	/**
	 * 上传头像至服务器临时路径
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadpgheadimage.do")
	public void uploadpgheadimage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		String name = request.getParameter("name");
		File file = new File(tmpFilePath+File.separator+name);
		File parent = file.getParentFile();
		//判断文件夹是否存在
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		//判断文件夹是否已存在
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
		}
		InputStreamToFile.ServletInputStreamToFile(request.getInputStream(), file);
	}
	
	/**
	 * 显示临时头像
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("showtmpheadimage.do")
	public String showtmpheadimage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("image/jpeg");
		String name = request.getParameter("name");
		File file = new File(tmpFilePath+File.separator+name);
		if(file.exists()){ 
			InputStream in = new FileInputStream(file);   //用该文件创建一个输入流  
			OutputStream os = response.getOutputStream();  //创建输出流  
			byte[] b = new byte[1024];    
			while( in.read(b)!= -1){   
			os.write(b);       
			}  
			in.close();   
			os.flush();  
			os.close();  
		} 
	    return null;
	}
	
	/**
	 * 更新摄影家信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updatepgbaseinfo.do")
	public String updatepgbaseinfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		Object userId = request.getSession().getAttribute("userId");
		if(userId==null){
			result.put("result", "nologin");
			return result.toString();
		}
		String pgId = request.getParameter("pgId");
		String pgName = request.getParameter("pgName");
		String pgSex = request.getParameter("pgSex");
		String pgNationId = request.getParameter("pgNationId");
		String pgYears = request.getParameter("pgYears");
		String pgBirthYears = request.getParameter("pgBirthYears");
		String pgDeadYears = request.getParameter("pgDeadYears");
		String pgHeadImgName = request.getParameter("pgHeadImgName");
		String pgLable = request.getParameter("pgLable");
		try {
			if (!pgHeadImgName.equals("") && pgHeadImgName != null) {
				File file = new File(tmpFilePath + File.separator
						+ pgHeadImgName);
				if (file.exists()) {
					//上传图片文件
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyyMMddhhmmssSSS");//用于重命名文件
					String tmpfilesuffix = FileTransferUtil.getFileSuffix(file);//文件后缀
					String newfilename = EncryptionUtil.ENCRYPTSTRING("H"
							+ sdf.format(new Date()))
							+ "." + tmpfilesuffix;//重命名
					String rfilename = pgId + File.separator + "head"
							+ File.separator + newfilename;//文件相对路径
					File newfile = new File(photogdir + File.separator
							+ rfilename);
					FileTransferUtil.FileTransferFile(
							newfile.getAbsolutePath(), file);
					pgmanager.updatePgHeadImgInfo(pgId, rfilename);
				}
			}
			JSONObject pgobj = new JSONObject();
			pgobj.put("pgName", pgName);
			pgobj.put("pgSex", pgSex);
			pgobj.put("pgNationId", pgNationId);
			pgobj.put("pgYears", pgYears);
			pgobj.put("pgBirthYears", pgBirthYears);
			pgobj.put("pgDeadYears", pgDeadYears);
			pgmanager.updatePgBaseInfo(pgId, pgobj,userId.toString());
			JSONArray pglbarr = new JSONArray();
			if (!pgLable.equals("")) {
				pglbarr = JSONArray.fromObject(pgLable);
			}
			pgmanager.updatePgLableInfo(pgId, pglbarr);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "exception");
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * 添加新标签
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addnewlabledata.do")
	public String addnewlabledata(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		String lablename = request.getParameter("newLbName");
		String labledesc = request.getParameter("newLbDesc");
		JSONObject obj = new JSONObject();
		obj.put("lbName", lablename);
		obj.put("lbDesc", labledesc);
		try {
			JSONObject resobj = pgmanager.addNewLableData(obj);
			int resint = resobj.getInt("state");
			if(resint==0){
				result.put("result", "fail");
			}else if(resint==2){
				result.put("result", "repeat");
			}else if(resint==1){
				result.put("result", "success");
				result.put("lbId", resobj.get("lbId"));
			}
		} catch (Exception e) {
			result.put("result", "exception");
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	
	
	
	
	
}
