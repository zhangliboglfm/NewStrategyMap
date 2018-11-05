/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
*/
package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.miapsoft.manager.CgLifeTimeManager;
import com.miapsoft.util.CompressUtil_ztf;
import com.miapsoft.util.DesUtil;
import com.miapsoft.util.EncryptKey;
import com.miapsoft.util.EncryptionUtil;

@Controller
public class CgLifeTimeController {
	
	@Resource
	private CgLifeTimeManager cglifeTimeManager;
	
	private static String cgdir = ServerFilePath.getCalligdir();//书法家，头像，作品目录
	
	@RequestMapping("cglifetime.do")
	public String pglifetime(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		String cgId = request.getParameter("cgId");
		String jsptype = request.getParameter("jsptype");
		String auditStatus = request.getParameter("auditStatus");
		JSONObject obj = cglifeTimeManager.getCgLifeTimeWord(cgId);
		request.setAttribute("cgId", cgId);
		request.setAttribute("jsptype", jsptype);
		request.setAttribute("auditStatus", auditStatus);
		if(obj!=null){
			String artid = obj.getString("artId");
			String artname = obj.getString("artName");
			String arttitle = obj.getString("artTitle");
			String artpic = obj.getString("artPic");
			String arturl= obj.getString("artUrl");
			request.setAttribute("artid", artid);
			request.setAttribute("artname", artname);
			request.setAttribute("arttitle", arttitle);
			request.setAttribute("artpic", artpic);
			request.setAttribute("arturl", arturl);
		}
		return "cgmag/cglifetime";
	}
	
	@ResponseBody
	@RequestMapping("reflushltdata.do")
	public String reflushlifetimedata(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String result = "";
		String cgId = request.getParameter("cgId");
		JSONObject obj = cglifeTimeManager.getCgLifeTimeWord(cgId);
		if(obj==null){
			result = "null";
		}else{
			result = obj.toString();
		}
		return result;
	}
	
	/**
	 * 读取word
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="showcgltword.do")
	public ModelAndView showcgltword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("pgmag/lifetime");
		String pId = request.getParameter("pId");
		String wordName = request.getParameter("wordName");
		String finnalpath = cgdir+"/"+wordName;
		String imgPath = request.getSession().getServletContext().getRealPath("/");
		File imgfile = new File(imgPath);
		if(!imgfile.exists()){
			imgfile.mkdirs();
		}
		String leixing = "doc";
		String content= "";
		InputStream input = new FileInputStream(finnalpath);
		InputStream sbs = null;
		String key = EncryptKey.DesKey;
		sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,input));
		if ("doc".equals(leixing)) {
			HWPFDocument wordDocument = new HWPFDocument(sbs);
			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.newDocument());
			wordToHtmlConverter.setPicturesManager(new PicturesManager() {
				public String savePicture(byte[] content, PictureType pictureType,
						String suggestedName, float widthInches, float heightInches) {
					return suggestedName;
				}
			});
			wordToHtmlConverter.processDocument(wordDocument);
			List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
			if (pics != null) {
				for (int i = 0; i < pics.size(); i++) {
					Picture pic = (Picture) pics.get(i);
					try {
						pic.writeImageContent(new FileOutputStream(imgPath + pic.suggestFullFileName()));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}	
			Document htmlDocument = wordToHtmlConverter.getDocument();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			DOMSource domSource = new DOMSource(htmlDocument);
			StreamResult streamResult = new StreamResult(outStream);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
			outStream.close();
			content = new String(outStream.toByteArray(),"utf-8");
		} else {
			/*XWPFDocument document = new XWPFDocument(input);
	        // 配置
	        XHTMLOptions options = XHTMLOptions.create();
	        // 设置图片存储路径
	        String path2 = System.getProperty("java.io.tmpdir");
	        String firstImagePathStr = path2 + "/" + String.valueOf(System.currentTimeMillis());
	        options.setExtractor(new FileImageExtractor(new File(firstImagePathStr)));
	        options.URIResolver(new BasicURIResolver(firstImagePathStr));
	        // 转换html
	        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
	        XHTMLConverter.getInstance().convert(document, htmlStream, options);
	        String htmlStr = htmlStream.toString();
	        // 将image文件转换为base64并替换到html字符串里 
	        String middleImageDirStr = "/word/media";
	        String imageDirStr = firstImagePathStr + middleImageDirStr;
	        File imageDir = new File(imageDirStr);
	        String[] imageList = imageDir.list();
	        if (imageList != null) {
	            for (int i = 0; i < imageList.length; i++) {
	                String oneImagePathStr = imageDirStr + "/" + imageList[i];
	                File oneImageFile = new File(oneImagePathStr);
	                String imageBase64Str = new String(Base64.encodeBase64(FileUtils.readFileToByteArray(oneImageFile)), "UTF-8");
	                htmlStr = htmlStr.replace(oneImagePathStr, "data:image/png;base64," + imageBase64Str);
	            }
	        }
	        //删除图片路径
	        File firstImagePath = new File(firstImagePathStr);
	        FileUtils.deleteDirectory(firstImagePath);
	        System.out.println(htmlStr);*/
		}
		try { 
	        input.close();  
	        sbs.close();
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		modelAndView.addObject("content", content);
		FileUtils.writeStringToFile(new File(cgdir+"/"+pId+"/tmp/", "tmp.html"), content, "utf-8");
		content=URLEncoder.encode(content,"UTF-8");
		return modelAndView;
	}
	
	/**
	 * 上传生平word文件至服务器
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadcglifetime.do")
	public void uploadcglifetime(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		String name = URLDecoder.decode(URLDecoder.decode(request.getParameter("name"),"UTF-8"),"UTF-8");
		String cgId = request.getParameter("cgId");
		String aId = request.getParameter("aId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件
		
		String tmpfilesuffix  = name.substring(name.lastIndexOf(".")+1, name.length());//文件后缀
    	String newfilename = EncryptionUtil.ENCRYPTSTRING("LI"+sdf.format(new Date()))+"."+tmpfilesuffix;//重命名
    	String repath = cgId+"/lifetime/"+newfilename;
		String filepath = cgdir+File.separator+repath;
		File file = new File(filepath);
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
		if(aId!=null&&!aId.equals("null")&&!aId.equals("")){
			JSONObject obj = new JSONObject();
			obj.put("aTitle", name.substring(0, name.lastIndexOf(".")));
			obj.put("aDoc", repath);
			cglifeTimeManager.updateArticleDocInfo(obj, aId);
		}else{
			JSONObject obj = new JSONObject();
			obj.put("aTitle", name.substring(0, name.lastIndexOf(".")));
			obj.put("aDoc", repath);
			cglifeTimeManager.insertIntoNewArticle(obj, cgId);
		}
		
		FileOutputStream out = new FileOutputStream(file);
		String key = EncryptKey.DesKey;
		DesUtil.encryptFile(key, request.getInputStream(), out);
		try { 
			out.close();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		printWriter.write(repath);
	}
	
	/**
	 * 上传生平文件-图片至服务器
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("uploadcgltpic.do")
	public void uploadcgltpic(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/x-javascript");
		PrintWriter printWriter=null;
		try {
			printWriter = response.getWriter();
		} catch (Exception e) {
		}
		String name = URLDecoder.decode(URLDecoder.decode(request.getParameter("name"),"UTF-8"),"UTF-8");
		String cgId = request.getParameter("cgId");
		String aId = request.getParameter("aId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件
		
		String tmpfilesuffix  = name.substring(name.lastIndexOf(".")+1, name.length());//文件后缀
    	String newfilename = EncryptionUtil.ENCRYPTSTRING("LIP"+sdf.format(new Date()))+"."+tmpfilesuffix;//重命名
    	String repath = cgId+"/lifetime/"+newfilename;
		String filepath = cgdir+File.separator+repath;
		File file = new File(filepath);
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
		if(aId!=null&&!aId.equals("null")&&!aId.equals("")){
			cglifeTimeManager.updateArticlePicInfo(repath, aId);
		}else{
			cglifeTimeManager.insertIntoNewArticleWithPic(repath, cgId);
		}
		
		FileOutputStream out = new FileOutputStream(file);
		String key = EncryptKey.DesKey;
		DesUtil.encryptFile(key, request.getInputStream(), out);
		try { 
			out.close();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		printWriter.write(repath);
	}
	
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downlifeTime.do")
	public String downlifeTime(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String sel = request.getParameter("sel");
		String artname = request.getParameter("artname");
		String artpic = request.getParameter("artpic");
		String arttitle = request.getParameter("arttitle");
		if (arttitle!=null) {
			arttitle = URLDecoder.decode(arttitle,"UTF-8");
		} else {
			arttitle="生平";
		}
		String downloadname = arttitle;
		File file = null;
		if(sel.equals("0")){
			file = new File(cgdir+"/"+artname);
			downloadname = downloadname + ".doc";
			InputStream input = new FileInputStream(file);
			InputStream sbs = null;
			String key = EncryptKey.DesKey;
			sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,input));
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(downloadname.getBytes(), "iso-8859-1"));
			byte[] buff = new byte[1024];
			BufferedInputStream bis = null;
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				bis = new BufferedInputStream(sbs);
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
		}else if(sel.equals("1")){
			String zipPath = CompressUtil_ztf.CompressLongPic(artpic);
			InputStream sbs = new FileInputStream(new File(zipPath));
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String("长图.zip".getBytes(), "iso-8859-1"));
			byte[] buff = new byte[1024];
			BufferedInputStream bis = null;
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				bis = new BufferedInputStream(sbs);
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
		}
		return null;
	}
}
