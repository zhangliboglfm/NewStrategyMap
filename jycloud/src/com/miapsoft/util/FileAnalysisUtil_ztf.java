/*** <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: 精益有容（北京）科技有限公司</p> 
 * @author 白少华
 * @date 2018-7-30
 */
package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.miapsoft.controller.ServerFilePath;

public class FileAnalysisUtil_ztf {

	private static String articledir = ServerFilePath.getArticledir();//专题文章Word、图片、H5、封面---摄影家
	private static String cgArticledir = ServerFilePath.getCgarticledir();//专题文章Word、图片、H5、封面---摄影家

	public static JSONArray AFileDataEntry(PrintWriter printWriter,String filepath, String artId) throws Exception{
		JSONArray wbdata = new JSONArray();
		JSONArray arr_ainfo = new JSONArray();
		JSONArray arr_apinfo = new JSONArray();
		JSONArray arr_awinfo = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件

		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				throw new Exception("缺少list文件！");
			}
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
		if(exceltype.equalsIgnoreCase("xlsx")){
			wb = new XSSFWorkbook(fis);
		}else if(exceltype.equalsIgnoreCase("xls")){
			wb = new HSSFWorkbook(fis);
		}
		//关闭文件流
		try {
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet1 = wb.getSheetAt(0);

		//解析第一个sheet
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i <= sheet1_rowNum; i++) {
			//循环生成ArticleId
			artId=artId.substring(4);
			int count = Integer.parseInt(artId)+1;
			artId=IdGenerateUtil.getArticleId("TA", count);
			
			JSONObject obj = new JSONObject();
			Row rw = sheet1.getRow(i);
			Cell cell2 = rw.getCell(0);//根据模板协议，获取文章标题
			Cell cell3 = rw.getCell(1);//根据模板协议，获取文章文档
			Cell cell6 = rw.getCell(2);//根据模板协议，获取相关摄影家(可以为空)
			Cell cell7 = rw.getCell(3);//根据模板协议，获取相关图片(可以为空)
			Cell cell8 = rw.getCell(4);//根据模板协议，获取标签(可以为空)

			File tmpwordfile_wi = new File(filepath+File.separator+"word"+File.separator+POIUtil.getCellValue(cell3));//临时文档文件

			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀

			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("AW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名

			String wordrelativePath_wi = artId+"/"+newwordfilename_wi;//文档文件相对路径

			try {
				FileTransferUtil.FileTransferFile(articledir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}
			try {
				obj.put("aId", artId);//文章Id
				obj.put("aTitle", POIUtil.getCellValue(cell2));//文章标题
				obj.put("aDoc", wordrelativePath_wi);//word路径
				obj.put("aPhotog", POIUtil.getCellValue(cell6));
				obj.put("aWorks", POIUtil.getCellValue(cell7));
				obj.put("aLabel", POIUtil.getCellValue(cell8));
				arr_ainfo.add(obj);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		wbdata.add(arr_ainfo);
		System.out.println(wbdata);
		return wbdata;

	}
	//书法家部分文章上传
	public static JSONArray CgFileDataEntry(PrintWriter printWriter,String filepath, String artId) throws Exception{
		JSONArray wbdata = new JSONArray();
		JSONArray arr_ainfo = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件

		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				throw new Exception("缺少list文件！");
			}
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
		if(exceltype.equalsIgnoreCase("xlsx")){
			wb = new XSSFWorkbook(fis);
		}else if(exceltype.equalsIgnoreCase("xls")){
			wb = new HSSFWorkbook(fis);
		}
		//关闭文件流
		try {
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet1 = wb.getSheetAt(0);

		//解析第一个sheet
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i <= sheet1_rowNum; i++) {
			//循环生成ArticleId
			artId=artId.substring(4);
			int count = Integer.parseInt(artId)+1;
			artId=IdGenerateUtil.getArticleId("TA", count);
			
			JSONObject obj = new JSONObject();
			Row rw = sheet1.getRow(i);
			Cell cell2 = rw.getCell(0);//根据模板协议，获取文章标题
			Cell cell3 = rw.getCell(1);//根据模板协议，获取文章word文档
			Cell cell6 = rw.getCell(2);//根据模板协议，获取相关书法家(可以为空)
			Cell cell7 = rw.getCell(3);//根据模板协议，获取相关书法家作品(可以为空)

			File tmpwordfile_wi = new File(filepath+File.separator+"word"+File.separator+POIUtil.getCellValue(cell3));//临时文档文件

			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀

			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("AW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名

			String wordrelativePath_wi = artId+"/"+newwordfilename_wi;//文档文件相对路径

			try {
				FileTransferUtil.FileTransferFile(cgArticledir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}
			try {
				obj.put("aId", artId);//文章Id
				obj.put("aTitle", POIUtil.getCellValue(cell2));//文章标题
				obj.put("aDoc", wordrelativePath_wi);//word路径
				obj.put("aPhotog", POIUtil.getCellValue(cell6));
				obj.put("aWorks", POIUtil.getCellValue(cell7));
				arr_ainfo.add(obj);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}
		}
		wbdata.add(arr_ainfo);
		System.out.println(wbdata);
		return wbdata;
	}
}
