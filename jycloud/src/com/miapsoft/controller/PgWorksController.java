/*** <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: 精益有容（北京）科技有限公司</p> 
 * @author 白少华
 * @date 2018-8-9
 */
package com.miapsoft.controller;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import com.miapsoft.manager.PgWorksManager;
import com.miapsoft.util.CheckFileUtil;
import com.miapsoft.util.FileAnalysisUtil;
import com.miapsoft.util.FileUtil;
import com.miapsoft.util.InputStreamToFile;
import com.miapsoft.util.PrintUtil;
import com.miapsoft.util.UnCompressUtil;

@Controller
public class PgWorksController {
	private static String tmpFilePath = ServerFilePath.getTmpFilePath();//上传临时目录

	@Resource
	private PgWorksManager pgWorksManager;
	
	@RequestMapping("pgworksaudit.do")
	public String pgworksaudit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pgId", request.getParameter("pId"));
		return "pgmag/pgworksaudit";
	}
	
	@RequestMapping("pgworks.do")
	public String pgworks(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		request.setAttribute("pgId", request.getParameter("pId"));
		return "pgmag/pgworks";
	}

	///获取摄影家作品
	@ResponseBody
	@RequestMapping("selphgworks.do")
	public String selPhgWorks(HttpServletRequest request,HttpServletResponse response) {

		String pgId=request.getParameter("pgId");
		String currPage = request.getParameter("currPage");
		String pageSize=request.getParameter("pageSize");

		if (currPage==null||"".equals(currPage)) {
			currPage="1";
		}

		int tmppage=Integer.parseInt(currPage);
		int tmpstart=(tmppage-1)*Integer.parseInt(pageSize);
		String start=tmpstart+"";
		String end=pageSize+"";

		String result=pgWorksManager.selPhgWorks(pgId,start,end);
		return result;
	}
	
	
	///获取摄影家作品图片
	@ResponseBody
	@RequestMapping(value="selphgworksimg.do")
	public void selPhgWorksImg(HttpServletRequest request,HttpServletResponse response) {
		String wId = request.getParameter("wId");//摄影家ID
		String result = pgWorksManager.selPhgWorksImg(wId);
		JSONObject object = JSONObject.fromObject(result);
		JSONArray array = object.getJSONArray("dataList");
		
		String fileName="";
		if (array.size()>0) {
			fileName=array.getJSONObject(0).getString("FILE_NAME");
		}
		String filepath= ServerFilePath.getPhotogdir()+File.separator+fileName;
		String allowpath=ServerFilePath.getPhotogdir();
		FileUtil.decryptDownload(fileName, filepath,allowpath, response, request);
	}
	
	//获取上传模板
	@ResponseBody
	@RequestMapping(value="selphgworksmod.do")
	public void selPhgWorksMod(HttpServletRequest request,HttpServletResponse response) {
		
		String fileName="摄影家作品模板文件.zip";
		String filepath= ServerFilePath.getwModelPath();
		
		String allowpath=ServerFilePath.getwModelPath().replace("摄影家作品模板文件.zip", "");
		FileUtil.download(fileName, filepath, allowpath, response);
	}
	
	/**
	 * 上传摄影家作品压缩包并解压缩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadphgworks.do")
	public void upLoadPhgWorks(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		// 下面设置Content-Type:application/x-javascript 是为了适应Webkit的浏览器(chrome,safari)
		response.setHeader("Content-Type","application/x-javascript");
		
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		
		String pgId = request.getParameter("pgId");
		String name = request.getParameter("name");
		System.out.println(URLDecoder.decode(name,"utf-8"));
		String size = request.getParameter("size");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String suffixRAR = name.substring(name.lastIndexOf("."));//去文件后缀
		String tmpFileName=sdf.format(new Date())+pgId+File.separator+sdf.format(new Date())+suffixRAR;
		String newfileName=tmpFilePath+File.separator+tmpFileName;
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
		//解压缩
		String dirname = uploadFile(newfileName, file.getParentFile().getAbsolutePath());
		
		//******************************开始校验******************************
		if(CheckFileUtil.checkPhotogWorksFile(file.getParentFile().getAbsolutePath()+File.separator+dirname, printWriter)){
			//******************************开始解析******************************
			JSONArray data = FileAnalysisUtil.WFileDataEntry(printWriter, file.getParentFile().getAbsolutePath()+File.separator+dirname,pgId);
			if(data.size()!=0&&data.size()==1){
				JSONArray workarr = data.getJSONArray(0);
				if(workarr.size()!=0){
					for (int i = 0; i < workarr.size(); i++) {
						JSONObject obj = workarr.getJSONObject(i);
						//规则：W+下划线（1位）+摄影家标识（10位）+编号（8位）
						//示例：W_P_M156000100000001
//						String tmpWId="W_"+pgId;
//						
//						obj.put("worksId", value);
						obj.put("pgId", pgId);
						//obj_works.put("worksId", POIUtil.getCellValue(cell1));
//						obj_works.put("worksName", POIUtil.getCellValue(cell1));
//						obj_works.put("worksType", POIUtil.getCellValue(cell2));
//						obj_works.put("shootDate", POIUtil.getCellValue(cell3));
//						obj_works.put("worksIntro", POIUtil.getCellValue(cell4));
//						obj_works.put("shootProc", POIUtil.getCellValue(cell5));
//						obj_works.put("fileName", relativePath);
//						//obj_works.put("pId", POIUtil.getCellValue(cell8));
//						obj_works.put("isRepre", POIUtil.getCellValue(cell7));
//						obj_works.put("showIndex", POIUtil.getCellValue(cell8));
						pgWorksManager.addPhgWorksInfo(obj);//录入摄影家作品信息
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
			PrintUtil.printLog(printWriter, "文件有误，请<a href='downloadWorksErrorfile.do?filepath="+URLEncoder.encode(URLEncoder.encode(listfile.getAbsolutePath(), "utf-8"), "utf-8")+"&fileName="+URLEncoder.encode(URLEncoder.encode(tmpFileName, "utf-8"), "utf-8")+"'>下载</a>并修改");
		}
		printWriter.flush();
		printWriter.close();
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
	
	
	//获取上传模板
	@ResponseBody
	@RequestMapping(value="downloadWorksErrorfile.do")
	public void downloadWorksErrorfile(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		String fileName=request.getParameter("fileName");
		fileName = URLDecoder.decode(fileName,"utf-8");
		String filepath= request.getParameter("filepath");
		filepath=URLDecoder.decode(filepath,"utf-8");
		
		String allowpath=filepath.replace(fileName, "");
		FileUtil.download("list.xlsx", filepath, allowpath, response);
	}
	//删除作品
	@ResponseBody
	@RequestMapping("deletePgWorks.do")
	public String deletePgWorks(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String deleteId = request.getParameter("deleteId");
		String pgId = request.getParameter("pgId");
		String result = pgWorksManager.deletePgWorks(deleteId,pgId);
		return result;
	}
	//作品重新排序
	@ResponseBody
	@RequestMapping("reOrderWork.do")
	public String reOrderWork(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pgId = request.getParameter("pgId");
		String result = pgWorksManager.reOrderWork(pgId);
		return result;
	}
	
}
