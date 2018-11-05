/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-1
*/
package com.miapsoft.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CheckFileUtil_ztf {

	public static String headpath = "headimg";
	public static String workspath = "worksimg";
	public static String articlecover = "coverimg";
	public static String articleword = "word";
	public static String articleh5 = "h5img";
	
	/**
	 * 校验文章
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkArticleFile2(String filepath,PrintWriter printWriter) throws Exception{
		boolean result1= true,result2= true,result3= true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		String dirname [] = {"word"};
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
		for (int i = 0; i < dirname.length; i++) {
			File dirfile = new File(filepath+File.separator+dirname[i]);
			if(!dirfile.exists()){
				PrintUtil.printLog(printWriter, dirname[i]+"文件夹不存在，请检查上传文件");
				return false;
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----文件完整");
		
		PrintUtil.printLog(printWriter, "开始校验----list文件");
		List<Sheet> sheetList = POIUtil.getAllSheetFromWb(listfile);
		Sheet sheet1 = null;//第一个Sheet为文章基本信息
		if(sheetList.size()==1){
			sheet1 = sheetList.get(0);//第一个Sheet为摄影家基本信息
		}else{
			PrintUtil.printLog(printWriter, "校验结果----list缺少Sheet页");
			return false;
		}
		
		//校验第一个sheet，第一个sheet为文章汇总信息
		PrintUtil.printLog(printWriter, "开始校验----文章汇总信息");
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i < sheet1_rowNum; i++) {
			Row rw = sheet1.getRow(i);
			Cell cell2 = rw.getCell(0);//根据模板协议，获取文章标题
			Cell cell3 = rw.getCell(1);//根据模板协议，获取文章文档
			Cell cell6 = rw.getCell(2);//根据模板协议，获取相关摄影家(可以为空)
			Cell cell7 = rw.getCell(3);//根据模板协议，获取相关图片(可以为空)
			Cell cell8 = rw.getCell(4);//根据模板协议，获取标签(可以为空)
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章标题数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章文档数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+articleword+File.separator+POIUtil.getCellValue(cell3)))){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，缺少文档文件");
    				POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章汇总信息完整");
		if(result1&&result2&&result3){
			return true;
		}else{
			return false;
		}
	}
	public static boolean checkCgArticleFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result1= true,result2= true,result3= true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		String dirname [] = {"word"};
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
		for (int i = 0; i < dirname.length; i++) {
			File dirfile = new File(filepath+File.separator+dirname[i]);
			if(!dirfile.exists()){
				PrintUtil.printLog(printWriter, dirname[i]+"文件夹不存在，请检查上传文件");
				return false;
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----文件完整");
		
		PrintUtil.printLog(printWriter, "开始校验----list文件");
		List<Sheet> sheetList = POIUtil.getAllSheetFromWb(listfile);
		Sheet sheet1 = null;//第一个Sheet为文章基本信息
		if(sheetList.size()==1){
			sheet1 = sheetList.get(0);//第一个Sheet为摄影家基本信息
		}else{
			PrintUtil.printLog(printWriter, "校验结果----list缺少Sheet页");
			return false;
		}
		
		//校验第一个sheet，第一个sheet为文章汇总信息
		PrintUtil.printLog(printWriter, "开始校验----文章汇总信息");
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i < sheet1_rowNum; i++) {
			Row rw = sheet1.getRow(i);
			Cell cell2 = rw.getCell(0);//根据模板协议，获取文章标题
			Cell cell3 = rw.getCell(1);//根据模板协议，获取文章文档
			Cell cell4 = rw.getCell(2);//根据模板协议，获取相关书法家
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章标题数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 0, "信息有误");
    			result1 = false;
    			continue;
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章文档数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 1, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+articleword+File.separator+POIUtil.getCellValue(cell3)))){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，缺少文档文件");
    				POIUtil.writeErrorInfo(listfile, 0, i, 1, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
    		if (cell4 != null && !POIUtil.getCellValue(cell4).equals("")) {
    			boolean hasSpacileChar = RegexUtil.hasSpecialChar(POIUtil.getCellValue(cell4));//是否包含除#以外的特殊字符
    			boolean hasDigit = RegexUtil.HasDigit(POIUtil.getCellValue(cell4));//是否包含数字
    			if(hasSpacileChar&&hasDigit){
    				PrintUtil.printLog(printWriter, "校验结果----相关书法家含有除“#”以外特殊字符或数字");
    				POIUtil.writeErrorInfo(listfile, 0, i, 2, "相关书法家含有除“#”以外特殊字符或数字");
    				result1 = false;
        			continue;
    			}
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章汇总信息完整");
		if(result1&&result2&&result3){
			return true;
		}else{
			return false;
		}
	}
}
