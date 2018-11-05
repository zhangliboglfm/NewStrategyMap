package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.miapsoft.controller.ServerFilePath;

public class FileAnalysisUtil_CGWorks {

	private static String cgworksdir = ServerFilePath.getCgworksintrodir();//书法家作品图片、封面
	public static String workspath = "worksimg";
	public static String articlecover = "coverimg";
	/**
	 * 校验文章
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
/*	public static boolean isSpecialChar2(String str) {
	    String regEx = "*";
	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(str);
	    return m.find();
	}*/
	public static boolean isSpecialChar(String str) {
	    String regEx = "^[A-Z]+$";
	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(str);
	    return m.find();
	}
	public static boolean isDigit(String strNum) {  
        boolean result=strNum.matches("[0-9]+");
        return result;
	} 
	public static boolean isDigit01(String strNum) {  
        boolean result=strNum.matches("[0-1]+");
        return result;
	} 
	public static boolean checkCGWorkFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result1= true,result2= true,result3= true,result4= true,result5= true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		/*File lista = new File(filepath+File.separator);
		File listfile2 = new File(filepath+File.separator);
		File [] files=listfile2.listFiles();*/
		System.out.println(filepath+File.separator);
		File file = new File(filepath);
		if(!file.exists()){
			PrintUtil.printLog(printWriter, "文件不存在，无法解析");
			return false;
		}
		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，，请检查上传文件");
				return false;
			}
		}
/*		for (int i = 0; i < dirname.length; i++) {
			File dirfile = new File(filepath+File.separator+dirname[i]);
			if(!dirfile.exists()){
				PrintUtil.printLog(printWriter, dirname[i]+"文件夹不存在，请检查上传文件");
				return false;
			}
		}*/
		PrintUtil.printLog(printWriter, "校验结果----文件完整");
		
		PrintUtil.printLog(printWriter, "开始校验----list文件");
		List<Sheet> sheetList = POIUtil.getAllSheetFromWb(listfile);
		Sheet sheet1 = null;//第一个Sheet为作品基本信息
		if(sheetList.size()!=0){
			sheet1 = sheetList.get(0);//第一个Sheet为作品基本信息
		}/*else{
			PrintUtil.printLog(printWriter, "校验结果----list缺少Sheet页");
			return false;
		}*/
		
		//校验第一个sheet，第一个sheet为文章汇总信息
		PrintUtil.printLog(printWriter, "开始校验----文章汇总信息");
		
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		
		for (int i = 1; i <=sheet1_rowNum; i++) {
			Row rw = sheet1.getRow(i);
			Cell cell2 = rw.getCell(0);//根据模板协议，获取作品名称
			Cell cell3 = rw.getCell(1);//根据模板协议，获取全称
			Cell cell4 = rw.getCell(2);//根据模板协议，获取年代
			Cell cell5 = rw.getCell(3);//根据模板协议，获取书法类型
			Cell cell6 = rw.getCell(4);//根据模板协议，获取字数
			Cell cell7 = rw.getCell(5);//根据模板协议，获取规格
			Cell cell8 = rw.getCell(6);//根据模板协议，获取作品背景
			Cell cell9 = rw.getCell(7);//根据模板协议，获取现收藏地
			Cell cell10 = rw.getCell(8);//根据模板协议，获取是否重要作品
			Cell cell11 = rw.getCell(9);
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----书法家作品汇总信息中，作品名称有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 9, "信息有误");
    			result1 = false;
    			continue;
    		}
    		if(cell5 == null || POIUtil.getCellValue(cell5).equals("")||!FileAnalysisUtil_CGWorks.isSpecialChar(POIUtil.getCellValue(cell5))){
    			PrintUtil.printLog(printWriter, "校验结果----书法家作品汇总信息中，书法类型有误有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 9, "信息有误");
    			result2 = false;
    			continue;
    		}
    		if(cell6 == null || POIUtil.getCellValue(cell6).equals("")||!FileAnalysisUtil_CGWorks.isDigit(POIUtil.getCellValue(cell6))){
    			PrintUtil.printLog(printWriter, "校验结果----书法家作品汇总信息中，书法字数有误有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 9, "信息有误");
    			result3 = false;
    			continue;
    		}
    		if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----书法家作品汇总信息中，书法规格有误有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 9, "信息有误");
    			result4 = false;
    			continue;
    		}
    		if(cell10 == null ||!FileAnalysisUtil_CGWorks.isDigit01(POIUtil.getCellValue(cell10))||POIUtil.getCellValue(cell10).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，是否重要作品数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 9, "信息有误");
    			result5 = false;
    			continue;
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章汇总信息完整");
		if(result1&&result2&&result3&&result4&&result5){
			
			return true;
		}else{
			return false;
		}
	}

	public static JSONArray CGWorkFileDataEntry(PrintWriter printWriter,String filepath, String newworkid, String cgerId) throws Exception{
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
			//循环生成作品id
			newworkid=newworkid.substring(16);
			int count = Integer.parseInt(newworkid)+1;
			newworkid=IdGenerateUtil.getCGWorksId(cgerId, count);
			
			JSONObject obj = new JSONObject();
			Row rw = sheet1.getRow(i);

			Cell cell2 = rw.getCell(0);//根据模板协议，获取作品名称
			Cell cell3 = rw.getCell(1);//根据模板协议，获取全称
			Cell cell4 = rw.getCell(2);//根据模板协议，获取年代
			Cell cell5 = rw.getCell(3);//根据模板协议，获取书法类型
			Cell cell6 = rw.getCell(4);//根据模板协议，获取字数
			Cell cell7 = rw.getCell(5);//根据模板协议，获取规格
			Cell cell8 = rw.getCell(6);//根据模板协议，获取作品背景
			Cell cell9 = rw.getCell(7);//根据模板协议，获取现收藏地
			Cell cell10 = rw.getCell(8);//根据模板协议，获取是否重要作品
			Cell cell11 = rw.getCell(9);
			/*File tmpwordfile_wi = new File(filepath+File.separator+"word"+File.separator+POIUtil.getCellValue(cell3));//临时文档文件

			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀

			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("AW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名

			String wordrelativePath_wi = artId+"/"+newwordfilename_wi;//文档文件相对路径

			try {
				FileTransferUtil.FileTransferFile(articledir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}*/
			try {
				obj.put("cgworkid", newworkid);//作品Id
				obj.put("cgerId", cgerId);//书法家Id
				obj.put("works_name", POIUtil.getCellValue(cell2));//获取作品名称 
				obj.put("wholename", POIUtil.getCellValue(cell3));//获取全称     
				obj.put("years", POIUtil.getCellValue(cell4));//获取年代     
				obj.put("cgy_type", POIUtil.getCellValue(cell5));//获取书法类型 
				obj.put("words_num", POIUtil.getCellValue(cell6));//获取字数     
				obj.put("spec", POIUtil.getCellValue(cell7));//获取规格     
				obj.put("works_bg", POIUtil.getCellValue(cell8));//获取作品背景 
				obj.put("pst_collection", POIUtil.getCellValue(cell9));//获取现收藏地 
				obj.put("is_impt_works", POIUtil.getCellValue(cell10));//是否重要作品
				obj.put("cut_type", POIUtil.getCellValue(cell11));
				arr_ainfo.add(obj);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		/*wbdata.add(arr_ainfo);
		System.out.println(wbdata);*/
		return arr_ainfo;

	}	
	
	//书法家信息上传
	public static JSONArray CgWorksInfoEntry(PrintWriter printWriter,String filepath, String newworkid, String cgerId) throws Exception{
		JSONArray pgdata = new JSONArray();
		File listfile = new File(filepath);//获取list文件
		//读取文件
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
		//获取文件数据
		Sheet sheet1 = wb.getSheetAt(0);

		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i <= sheet1_rowNum; i++) {
			//循环生成作品id
			newworkid=newworkid.substring(16);
			int count = Integer.parseInt(newworkid)+1;
			newworkid=IdGenerateUtil.getCGWorksId(cgerId, count);
			
			JSONObject obj = new JSONObject();
			Row rw = sheet1.getRow(i);

			Cell cell2 = rw.getCell(0);//根据模板协议，获取作品名称
			Cell cell3 = rw.getCell(1);//根据模板协议，获取全称
			Cell cell4 = rw.getCell(2);//根据模板协议，获取年代
			Cell cell5 = rw.getCell(3);//根据模板协议，获取书法类型
			Cell cell6 = rw.getCell(4);//根据模板协议，获取字数
			Cell cell7 = rw.getCell(5);//根据模板协议，获取规格
			Cell cell8 = rw.getCell(6);//根据模板协议，获取作品背景
			Cell cell9 = rw.getCell(7);//根据模板协议，获取现收藏地
			Cell cell10 = rw.getCell(8);//根据模板协议，获取是否重要作品
			Cell cell11 = rw.getCell(9);//获取切割类型
			/*File tmpwordfile_wi = new File(filepath+File.separator+"word"+File.separator+POIUtil.getCellValue(cell3));//临时文档文件

			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀

			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("AW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名

			String wordrelativePath_wi = artId+"/"+newwordfilename_wi;//文档文件相对路径

			try {
				FileTransferUtil.FileTransferFile(articledir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}*/
			try {
				obj.put("cgworkid", newworkid);//作品Id
				obj.put("cgerId", cgerId);//书法家Id
				obj.put("works_name", POIUtil.getCellValue(cell2));//获取作品名称 
				obj.put("wholename", POIUtil.getCellValue(cell3));//获取全称     
				obj.put("years", POIUtil.getCellValue(cell4));//获取年代     
				obj.put("cgy_type", POIUtil.getCellValue(cell5));//获取书法类型 
				obj.put("words_num", POIUtil.getCellValue(cell6));//获取字数     
				obj.put("spec", POIUtil.getCellValue(cell7));//获取规格     
				obj.put("works_bg", POIUtil.getCellValue(cell8));//获取作品背景 
				obj.put("pst_collection", POIUtil.getCellValue(cell9));//获取现收藏地 
				obj.put("is_impt_works", POIUtil.getCellValue(cell10));//是否重要作品
				obj.put("cut_type", POIUtil.getCellValue(cell11));
				pgdata.add(obj);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		return pgdata;
	}

}
