package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import com.miapsoft.manager.CGWorkMessageimportManager;
import com.miapsoft.util.CheckFileUtil;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.FileAnalysisUtil;
import com.miapsoft.util.FileAnalysisUtil_CGWorks;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.PrintUtil;
import com.miapsoft.util.UnCompressUtil;
import com.mysql.jdbc.Field;

@Controller
public class CGWorkMessageimportController {

	private static String tmpFilePath = ServerFilePath.getCgworksintrodir();//书法家作品图片、封面
	
	private static String aModelPath = ServerFilePath.getcgModelPath();//书法家模板
	
	@Resource
	private CGWorkMessageimportManager cgWorkMessageimportManager;

	/**
	 * 书法家信息录入模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadCGWorksfile.do")
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
	 * 上传书法家信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	
	
	/**
	 * 上传书法家作信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadcgworkmessage.do")
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
		String cgerId = request.getParameter("cgerId");
		String newfileName=tmpFilePath+File.separator+sdf.format(new Date())+File.separator+URLDecoder.decode(name,"utf-8");
		File file = new File(newfileName);
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
		String path1=file.getParentFile().getAbsolutePath();
		//解压缩
		String dirname = uploadFile(newfileName, file.getParentFile().getAbsolutePath());
				
		//******************************开始校验******************************
		if(FileAnalysisUtil_CGWorks.checkCGWorkFile(file.getParentFile().getAbsolutePath()+File.separator+dirname, printWriter)){
			//******************************开始解析******************************
			String newworkid=cgWorkMessageimportManager.getnewworkid(cgerId);//获取新作品id
			JSONArray data = FileAnalysisUtil_CGWorks.CGWorkFileDataEntry(printWriter, file.getParentFile().getAbsolutePath()+File.separator+dirname,newworkid,cgerId);
			if(data.size()!=0){
				for (int i = 0; i < data.size(); i++) {
					JSONObject obj = data.getJSONObject(i);
					cgWorkMessageimportManager.insertcgworkinfo(obj);
					uploadCgWorksImageInfo(path1,(String) obj.get("works_name"),dirname,(String) obj.get("cgworkid"));
					
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
	 * 解压缩
	 * @param filepath
	 * @param uncompath
	 * @return
	 */
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
	
	public static String getTmpFilePath() {
		return tmpFilePath;
	}

	public static void setTmpFilePath(String tmpFilePath) {
		CGWorkMessageimportController.tmpFilePath = tmpFilePath;
	}
	
	public void uploadCgWorksImageInfo(String filepath,String worksname,String filename,String cgerid) throws Exception{
		String name="";
		String newname="";
		String path=ServerFilePath.getCalligdir();
		String path2=filepath+File.separator+filename+File.separator+worksname+File.separator;
		File file=new File(path2);
		File[] file2=file.listFiles();
		for(int i=0;i<file2.length;i++){
			name=file2[i].getName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件
			String tmpfilesuffix = file2[i].getName().substring(file2[i].getName().lastIndexOf(".") + 1);//文件后缀
			String newfilename = EncryptionUtil.ENCRYPTSTRING("S"
					+ sdf.format(new Date()))
					+ "." + tmpfilesuffix;//重命名
			String rfilename = cgerid+ File.separator + newfilename;//文件相对路径
			String rfilename2 = cgerid +"/"+ newfilename;//入库的名
			newname=path+ File.separator +rfilename;
			String key = EncryptKey.DesKey;
			File outFile = new File((newname));//H:/resourcelib/tmp/王羲之.zip
			File pFile = outFile.getParentFile();
			//判断文件夹是否存在
			if (pFile != null && !pFile.exists()) {
				pFile.mkdirs();
			}
			DesUtil.encryptFile(key,new FileInputStream(file2[i]),new FileOutputStream(new File(newname)));
			cgWorkMessageimportManager.insertcgworkImageinfo(rfilename2,cgerid);
		}
	}

}
