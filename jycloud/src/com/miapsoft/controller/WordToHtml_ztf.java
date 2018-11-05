package com.miapsoft.controller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;



@Controller
public class WordToHtml_ztf {

	@RequestMapping(value="wordToHtml_ztf_test.do")
	public ModelAndView ProductionJieduWord(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsp/productionJieduWord");
		final String path = "H:\\testword\\";
		final String file = "testword.docx";
		final String imgPath = "G:\\apache-tomcat-7.0.69\\webapps\\jycloud\\";
		String leixing = "docx";
		String content= "";
		InputStream input = new FileInputStream(path + file);
		if ("doc".equals(leixing)) {
			HWPFDocument wordDocument = new HWPFDocument(input);
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
			List pics = wordDocument.getPicturesTable().getAllPictures();
			if (pics != null) {
				for (int i = 0; i < pics.size(); i++) {
					Picture pic = (Picture) pics.get(i);
					try {
						pic.writeImageContent(new FileOutputStream(imgPath
								+ pic.suggestFullFileName()));
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
			XWPFDocument document = new XWPFDocument(input);
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
	        /*String htmlStr = htmlStream.toString();
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
		modelAndView.addObject("content", content);
		FileUtils.writeStringToFile(new File(path, "test.html"), content, "utf-8");
		content=URLEncoder.encode(content,"UTF-8");
		return modelAndView;
	}

}