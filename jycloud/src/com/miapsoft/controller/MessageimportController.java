package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.manager.MessageimportManager;
import com.miapsoft.util.CheckFileUtil;
import com.miapsoft.util.FileAnalysisUtil;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.PrintUtil;
import com.miapsoft.util.UnCompressUtil;


@Controller
public class MessageimportController{

	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	private static String photogdir = ServerFilePath.getPhotogdir();//摄影家，头像，作品目录
	private static String coreintrodir = ServerFilePath.getCoreintrodir();//摄影家核心介绍Word、图片、H5、封面
	private static String worksintrodir = ServerFilePath.getWorksintrodir();//摄影家作品解读Word、图片、H5、封面
	private static String articledir = ServerFilePath.getArticledir();//专题文章Word、图片、H5、封面
	
	private static String pModelPath = ServerFilePath.getpModelPath();
	private static String aModelPath = ServerFilePath.getaModelPath();
	private static String cgInputModelPath = ServerFilePath.getCgInputModelPath();
	@Resource
	private MessageimportManager messageimportManager;
	
	/**
	 * 信息导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws ServletException 
	 */
	@RequestMapping("messageimport.do")
	public String digitalfilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "pgmag/importphotographer";
	}
	
	/*@RequestMapping("importphotographer.do")
	public String importphotographer(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "pgmag/importphotographer";
	}
	
	@RequestMapping("importarticle.do")
	public String importarticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "pgmag/importarticle";
	}*/
	
	/**
	 * 上传摄影家压缩包并解压缩(旧)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadphotographer_old.do")
	public void uploadphotographer_old(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		
		String name = request.getParameter("name");
		String size = request.getParameter("size");
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
		if(file.length()!=Long.parseLong(size)){
			throw new Exception("文件上传失败");
		}
		//解压缩
		String dirname = uploadFile(tmpFilePath+File.separator+name, tmpFilePath);
		
		//******************************开始校验******************************
		if(CheckFileUtil.checkPhotogFile(tmpFilePath+File.separator+dirname, printWriter)){
			//******************************开始解析******************************
			JSONArray data = FileAnalysisUtil.PFileDataEntry(printWriter, tmpFilePath+File.separator+dirname);
			if(data.size()!=0&&data.size()>=5){
				JSONObject pobj = data.getJSONObject(0);
				JSONArray headarr = data.getJSONArray(1);
				JSONArray workarr = data.getJSONArray(2);
				JSONObject cintroobj = data.getJSONObject(3);
				JSONArray workintroobj = data.getJSONArray(4);
				
				messageimportManager.insertPhotographeIinfo(pobj);//录入摄影家基本信息
				
				JSONObject coreintroobj = new JSONObject();
				coreintroobj.put("aId", pobj.get("pIntroduce"));
				coreintroobj.put("pId", pobj.get("pId"));
				coreintroobj.put("showIndex", 0);
				messageimportManager.insertArticlePhotographerInfo(coreintroobj);//录入核心介绍与摄影家关系信息
				
				if(headarr.size()!=0){
					for (int i = 0; i < headarr.size(); i++) {
						JSONObject obj = headarr.getJSONObject(i);
						messageimportManager.insertPhotographerHeadImgInfo(obj);//录入摄影家头像信息
					}
				}
				if(workarr.size()!=0){
					for (int i = 0; i < workarr.size(); i++) {
						JSONObject obj = workarr.getJSONObject(i);
						messageimportManager.insertPhotographerWorksImgInfo(obj);//录入摄影家作品信息
						JSONObject artP = new JSONObject();//作品解读与摄影家关系
						JSONObject artW = new JSONObject();//作品解读与作品关系
						artP.put("aId", obj.get("worksIntro"));
						artP.put("pId", obj.get("pId"));
						artP.put("showIndex", 0);
						messageimportManager.insertArticlePhotographerInfo(artP);//录入作品解读与摄影家关系信息
						artW.put("aId", obj.get("worksIntro"));
						artW.put("wId", obj.get("worksId"));
						artW.put("showIndex", 0);
						messageimportManager.insertArticleWorksInfo(artW);////录入作品解读与作品关系信息
					}
				}
				messageimportManager.insertArticleInfo(cintroobj);//录入摄影家核心介绍信息
				if(workintroobj.size()!=0){
					for (int i = 0; i < workintroobj.size(); i++) {
						JSONObject obj = workintroobj.getJSONObject(i);
						messageimportManager.insertArticleInfo(obj);
					}
				}
				if(data.size()==6){
					JSONArray coreworksdata = data.getJSONArray(5);
					if(coreworksdata.size()!=0){
						for (int i = 0; i < coreworksdata.size(); i++) {
							JSONObject obj = coreworksdata.getJSONObject(i);
							messageimportManager.insertArticleWorksInfo(obj);//录入摄影家作品信息
						}
					}
				}
			}
			PrintUtil.printLog(printWriter, "上传完成！");
		}else{
			File listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xlsx");//获取list文件
			if(!listfile.exists()){
				listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xls");
				if(!listfile.exists()){
					PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				}
			}
			PrintUtil.printLog(printWriter, "文件有误，请<a href='downloadErrorfile.do?filepath="+listfile.getAbsolutePath()+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * 上传摄影家压缩包并解压缩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadphotographer.do")
	public void uploadphotographer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		File file = new File(tmpFilePath+File.separator+"tmp"+sdf.format(new Date())+"."+name.substring(name.lastIndexOf(".")+1, name.length()));
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
		if(file.length()!=Long.parseLong(size)){
			throw new Exception("文件上传失败");
		}
		
		//******************************开始校验******************************
		if(CheckFileUtil.checkPhotogFile(file.getAbsolutePath(), printWriter)){
			//******************************开始解析******************************
			JSONArray data = FileAnalysisUtil.PFileDataEntry(printWriter, file.getAbsolutePath());
			if(data.size()!=0){
				for (int i = 0; i < data.size(); i++) {
					JSONObject obj = data.getJSONObject(i);
					messageimportManager.insertPhotographeIinfo_new(obj);
				}
			}
			PrintUtil.printLog(printWriter, "上传完成！");
		}else{
			PrintUtil.printLog(printWriter, "文件有误，请<a href='downloadErrorfile.do?filepath="+file.getAbsolutePath()+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * 上传文章压缩包并解压缩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadarticle.do")
	public void uploadarticle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		printWriter = response.getWriter();
		
		String name = request.getParameter("name");
		String size = request.getParameter("size");
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
		if(file.length()!=Long.parseLong(size)){
			throw new Exception("文件上传失败");
		}
		//解压缩
		String dirname = uploadFile(tmpFilePath+File.separator+name, tmpFilePath);
		
		//******************************开始校验******************************
		if(CheckFileUtil.checkArticleFile(tmpFilePath+File.separator+dirname, printWriter)){
			//******************************开始解析******************************
			JSONArray data = FileAnalysisUtil.AFileDataEntry(printWriter, tmpFilePath+File.separator+dirname);
			if(data.size()!=0&&data.size()==3){
				JSONArray ainfoarr = data.getJSONArray(0);
				JSONArray apinfoarr = data.getJSONArray(1);
				JSONArray awinfoarr = data.getJSONArray(2);
				if(ainfoarr.size()!=0){
					for (int i = 0; i < ainfoarr.size(); i++) {
						JSONObject obj = ainfoarr.getJSONObject(i);
						messageimportManager.insertArticleInfo(obj);
					}
				}
				if(apinfoarr.size()!=0){
					for (int i = 0; i < apinfoarr.size(); i++) {
						JSONObject obj = apinfoarr.getJSONObject(i);
						messageimportManager.insertArticlePhotographerInfo(obj);
					}
				}
				if(awinfoarr.size()!=0){
					for (int i = 0; i < awinfoarr.size(); i++) {
						JSONObject obj = awinfoarr.getJSONObject(i);
						messageimportManager.insertArticleWorksInfo(obj);
					}
				}
			}
			PrintUtil.printLog(printWriter, "上传完成！");
		}else{
			File listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xlsx");//获取list文件
			if(!listfile.exists()){
				listfile = new File(tmpFilePath+File.separator+dirname+File.separator+"list.xls");
				if(!listfile.exists()){
					PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				}
			}
			PrintUtil.printLog(printWriter, "文件有误，请<a href='downloadErrorfile.do?filepath="+listfile.getAbsolutePath()+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * 下载摄影家信息录入模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadPmodelfile.do")
	public String downloadPmodelfile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		File file = new File(pModelPath);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((file.getName()).getBytes("utf-8"), "iso-8859-1"));
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
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
		
		return null;
	}
	
	/**
	 * 下载书法家信息录入模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadcgmodelfile.do")
	public String downloadcgmodelfile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		File file = new File(cgInputModelPath);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((file.getName()).getBytes("utf-8"), "iso-8859-1"));
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
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
		
		return null;
	}
	
	/**
	 * 下载文章信息录入模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadAmodelfile.do")
	public String downloadAmodelfile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		File file = new File(aModelPath);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((file.getName()).getBytes(), "iso-8859-1"));
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
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
		
		return null;
	}
	
	/**
	 * 下载错误信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadErrorfile.do")
	public String downloadErrorfile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String filepath = request.getParameter("filepath");
		File file = new File(filepath);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((file.getName()).getBytes(), "iso-8859-1"));
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
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
		
		return null;
	}
	
	public String uploadFile(String filepath,String uncompath){
	    File compressfile =new File(filepath);
	    String fileName=compressfile.getName();
	    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    String name = fileName.substring(0,fileName.lastIndexOf("."));
	    try {
	    	if(prefix.equalsIgnoreCase("zip")){
	    		UnCompressUtil.unzip(filepath, uncompath);
	    	}else if(prefix.equalsIgnoreCase("rar")){
	    		UnCompressUtil.untar(filepath, uncompath);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    compressfile.delete();
		return name;
	}
	
    public static String getCellValue(Cell cell) throws Exception {
        String cellValue = "";  
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellTypeEnum()) {  
        case STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case NUMERIC:  
            cellValue = df.format(cell.getNumericCellValue()).toString();  
            break;  
        case BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case FORMULA:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break; 
        case BLANK:  
            cellValue = "";  
            break;
        default:  
            cellValue = "";  
        }
        return cellValue;  
    }

	/**
	 * @return the tmpFilePath
	 */
	public static String getTmpFilePath() {
		return tmpFilePath;
	}

	/**
	 * @param tmpFilePath the tmpFilePath to set
	 */
	public static void setTmpFilePath(String tmpFilePath) {
		MessageimportController.tmpFilePath = tmpFilePath;
	}

	/**
	 * @return the messageimportManager
	 */
	public MessageimportManager getMessageimportManager() {
		return messageimportManager;
	}

	/**
	 * @param messageimportManager the messageimportManager to set
	 */
	public void setMessageimportManager(MessageimportManager messageimportManager) {
		this.messageimportManager = messageimportManager;
	}

	/**
	 * @return the photogdir
	 */
	public static String getPhotogdir() {
		return photogdir;
	}

	/**
	 * @param photogdir the photogdir to set
	 */
	public static void setPhotogdir(String photogdir) {
		MessageimportController.photogdir = photogdir;
	}

	/**
	 * @return the coreintrodir
	 */
	public static String getCoreintrodir() {
		return coreintrodir;
	}

	/**
	 * @param coreintrodir the coreintrodir to set
	 */
	public static void setCoreintrodir(String coreintrodir) {
		MessageimportController.coreintrodir = coreintrodir;
	}

	/**
	 * @return the worksintrodir
	 */
	public static String getWorksintrodir() {
		return worksintrodir;
	}

	/**
	 * @param worksintrodir the worksintrodir to set
	 */
	public static void setWorksintrodir(String worksintrodir) {
		MessageimportController.worksintrodir = worksintrodir;
	}

	/**
	 * @return the articledir
	 */
	public static String getArticledir() {
		return articledir;
	}

	/**
	 * @param articledir the articledir to set
	 */
	public static void setArticledir(String articledir) {
		MessageimportController.articledir = articledir;
	}
}
