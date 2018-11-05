package com.miapsoft.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.cxf.wsdl.http.UrlEncoded;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.AnalyzeZipManager;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.UnCompressUtil;

/**
 * <p>Title: 解析切图压缩包并保存
 * <p>Description: TODO</p>
 * @author: 张腾飞
 * @time: 2018-9-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
@Controller
public class AnalyzeCutpicZip {
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录
	private static String cgworksintrodir = ServerFilePath.getCgworksintrodir();//作品路径
	
	@Resource
	private AnalyzeZipManager anaZipManager;
	
	@ResponseBody
	@RequestMapping("testAnaZip.do")
	public String AnalyzeZip(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();//successFlag-- 0-失败，1-成功。  flagReason--失败的原因
		String key = EncryptKey.DesKey;
		try {
			String filePath = request.getParameter("filePath");
			System.out.println(filePath);
			String decodeFileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
			decodeFileName = decodeFileName.substring(0,decodeFileName.length()-4);
			String cgName = DesUtil.decrypt(decodeFileName,key);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String suffixRAR = filePath.substring(filePath.lastIndexOf("."));//去文件后缀
			String tmpFileName=sdf.format(new Date())+File.separator+sdf.format(new Date())+suffixRAR; 
			String newfileName=tmpFilePath+File.separator+tmpFileName;
			File zipFile = new File(filePath);
			if (!zipFile.exists()) {
				String reason = DesUtil.encrypt("文件不存在,请核实后再上传！", key);
				result.put("successFlag", "0");
				result.put("flagReason", "文件不存在,请核实后再上传！");
				System.out.println(result.toString());
				return URLEncoder.encode(result.toString(), "UTF-8");		
			}
			File newZipFile = new File(newfileName);
			File parent = newZipFile.getParentFile();
			//判断文件夹是否存在
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			if (!newZipFile.exists()) {
				newZipFile.createNewFile();
			} else {
				newZipFile.delete();
			}
			InputStreamToFile.ServletInputStreamToFile(new FileInputStream(new File(filePath)), newZipFile);
			//解压缩
			String dirname = uploadFile(newfileName, newZipFile.getParentFile().getAbsolutePath());
			System.out.println(dirname);
			String cgId = anaZipManager.getCalligId(cgName);
			if ("noPerson".equals(cgId)) {
				String reason = DesUtil.encrypt("查无此书法家，请核实", key);
				result.put("successFlag", "0");
				result.put("flagReason", "查无此书法家，请核实");
				System.out.println(result.toString());
				return URLEncoder.encode(result.toString(), "UTF-8");	
			}
			//System.out.println(newZipFile.getParentFile().getAbsolutePath()+dirname+"-----------------");
			String tmpPicRealPath=newZipFile.getParentFile().getAbsolutePath()+"/"+dirname;
			File tmpPicPath = new File(tmpPicRealPath);
			String[] content = tmpPicPath.list();//取得当前目录下所有文件和文件夹
			for(String name : content){
				File temp = new File(tmpPicRealPath, name);
				if(temp.isDirectory()){//判断是否是目录
					String newworkId = anaZipManager.getNewWorkId(cgId);
					//验证作品是否已存在并返回实际操作的workId
					String realWorkId = anaZipManager.verifiWorks(newworkId,name,cgId);
					if (!newworkId.equals(realWorkId)) {
						//删除现有的作品图片关联关系
						anaZipManager.deleteWorkPicR(realWorkId);
					}
					String nowParentPath = tmpPicRealPath+"/"+name;
					File nowParentP = new File(nowParentPath);
					String[] picNameList = nowParentP.list();//取得当前目录下所有文件和文件夹
					for (int i = 0; i < picNameList.length; i++) {
						File judgeFile = new File(nowParentPath,picNameList[i]);
						if (!judgeFile.isDirectory()) {
							String filename = EncryptionUtil.ENCRYPTSTRING(picNameList[i])+".png";
							String rfilePath = cgId+"/"+filename;
							String pfilePath = cgworksintrodir + "/" + rfilePath;
							File outFile = new File(pfilePath);
							File pFile = outFile.getParentFile();
							//判断文件夹是否存在
							if (pFile != null && !pFile.exists()) {
								pFile.mkdirs();
							}
							//文件加密
							DesUtil.encryptFile(key,new FileInputStream(judgeFile),new FileOutputStream(outFile));
							anaZipManager.insertNerWorks(realWorkId,rfilePath,(i+1)+"");
						}
					}
				}
			}
			String reason = DesUtil.encrypt("上传成功", key);
			result.put("successFlag", "1");
			result.put("flagReason", "上传成功");
			System.out.println(result.toString());
			return URLEncoder.encode(result.toString(), "UTF-8");	
		} catch (Exception e) {
			String reason = DesUtil.encrypt("出现异常！", key);
			result.put("successFlag", "0");
			result.put("flagReason", "error");
			return result.toString();
		}
	}
	//
	public static String uploadFile(String filepath,String uncompath){
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
}
