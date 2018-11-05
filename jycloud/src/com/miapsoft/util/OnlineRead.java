package com.miapsoft.util;

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
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.miapsoft.controller.ServerFilePath;

public class OnlineRead {
	public static String linshiPath=ServerFilePath.getPhotogdir();
	
	public static ModelAndView OnlineReadWord(InputStream input, String fileName) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsp/productionJieduWord");
		final String path = linshiPath+File.separator+"onlineRead";
		String leixing  = fileName.substring(fileName.lastIndexOf(".")+1);
		String content= "";
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
						pic.writeImageContent(new FileOutputStream(path
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
		}
		modelAndView.addObject("content", content);
		//FileUtils.writeStringToFile(new File(path, "test.html"), content, "utf-8");
		content=URLEncoder.encode(content,"UTF-8");
		return modelAndView;
	}
}
