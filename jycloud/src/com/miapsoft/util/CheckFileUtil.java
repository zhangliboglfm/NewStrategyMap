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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CheckFileUtil {

	public static String headpath = "headimg";
	public static String workspath = "worksimg";
	public static String coreintropath = "coreintro";
	public static String worksintropath = "worksintro";
	public static String articlecover = "coverimg";
	public static String articleword = "word";
	public static String articleh5 = "h5img";
	
	/**
	 * 校验摄影家
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPhotogFile_Old(String filepath,PrintWriter printWriter) throws Exception{
		boolean result1= true,result2= true,result3= true,result4= true,result5= true,result6 = true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		String dirname [] = {"headimg","worksimg","coreintro","worksintro"};
		File file = new File(filepath);
		if(!file.exists()){
			PrintUtil.printLog(printWriter, "文件不存在，无法解析");
			return false;
		}
		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
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
		Sheet sheet1 = null;//第一个Sheet为摄影家基本信息
		Sheet sheet2 = null;//第二个Sheet为摄影家头像信息
		Sheet sheet3 = null;//第三个Sheet为摄影家作品信息
		Sheet sheet4 = null;//第四个Sheet为摄影家核心介绍信息
		Sheet sheet5 = null;//第五个Sheet为摄影家作品解读信息
		Sheet sheet6 = null;//第六个Sheet为摄影家核心介绍与作品关系信息
		if(sheetList.size()==6){
			sheet1 = sheetList.get(0);//第一个Sheet为摄影家基本信息
			sheet2 = sheetList.get(1);//第二个Sheet为摄影家头像信息
			sheet3 = sheetList.get(2);//第三个Sheet为摄影家作品信息
			sheet4 = sheetList.get(3);//第四个Sheet为摄影家核心介绍信息
			sheet5 = sheetList.get(4);//第五个Sheet为摄影家作品解读信息
			sheet6 = sheetList.get(5);//第六个Sheet为核心介绍与作品关系信息
		}else{
			PrintUtil.printLog(printWriter, "校验结果----list缺少Sheet页");
			return false;
		}
		
		//校验第一个sheet，第一个sheet为摄影家汇总信息
		PrintUtil.printLog(printWriter, "开始校验----摄影家基本信息");
		Row sheet1_row = sheet1.getRow(1);
		Cell sheet1_cell1 = sheet1_row.getCell(0);//根据模板协议，获取摄影家ID
		Cell sheet1_cell2 = sheet1_row.getCell(1);//根据模板协议，获取摄影家姓名
		Cell sheet1_cell3 = sheet1_row.getCell(2);//根据模板协议，获取摄影家性别
		Cell sheet1_cell4 = sheet1_row.getCell(3);//根据模板协议，获取摄影家出生日期
		Cell sheet1_cell5 = sheet1_row.getCell(4);//根据模板协议，获取摄影家去世日期
		Cell sheet1_cell6 = sheet1_row.getCell(5);//根据模板协议，获取摄影家国籍
		Cell sheet1_cell7 = sheet1_row.getCell(6);//根据模板协议，获取摄影家核心介绍ID
		Cell sheet1_cell8 = sheet1_row.getCell(7);//根据模板协议，获取显示顺序
		
		if(sheet1_cell1 == null || POIUtil.getCellValue(sheet1_cell1).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家ID数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		if(sheet1_cell2 == null || POIUtil.getCellValue(sheet1_cell2).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家姓名数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		if(sheet1_cell3 == null || POIUtil.getCellValue(sheet1_cell3).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家性别数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}else{
			if(POIUtil.getCellValue(sheet1_cell3).length()!=1){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家性别数据有误");
				POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
				result1 = false;
			}
		}
		if(sheet1_cell4 == null || POIUtil.getCellValue(sheet1_cell4).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家出生日期数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}else{
			if(POIUtil.getCellValue(sheet1_cell4).length()!=8){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家出生日期数据有误");
				POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
				result1 = false;
			}
		}
		if(!POIUtil.getCellValue(sheet1_cell5).equals("") && POIUtil.getCellValue(sheet1_cell5).length()!=8){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家去世日期数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		if(sheet1_cell6 == null || POIUtil.getCellValue(sheet1_cell6).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家国籍数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		if(sheet1_cell7 == null || POIUtil.getCellValue(sheet1_cell7).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家核心介绍数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		if(sheet1_cell8 == null || POIUtil.getCellValue(sheet1_cell8).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家显示顺序数据有误");
			POIUtil.writeErrorInfo(listfile, 0, 1, 8, "信息有误");
			result1 = false;
		}
		PrintUtil.printLog(printWriter, "校验结果----摄影家信息完整");
		
		//校验第二个sheet，第二个sheet为摄影家头像信息
		PrintUtil.printLog(printWriter, "开始校验----摄影家头像信息");
		int sheet2_rowNum;
		sheet2_rowNum = sheet2.getLastRowNum();
		for (int i = 1; i < sheet2_rowNum; i++) {
			Row rw = sheet2.getRow(i);
			Cell cell1 = rw.getCell(0);
			Cell cell2 = rw.getCell(1);
			Cell cell3 = rw.getCell(2);
			Cell cell4 = rw.getCell(3);
			Cell cell5 = rw.getCell(4);
    		if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，摄影家ID数据有误");
    			POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
    			result2 = false;
    			continue;
    		}
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，照片类型数据有误");
    			POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
    			result2 = false;
    			continue;
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，文件名数据有误");
    			POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
    			result2 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+headpath+File.separator+POIUtil.getCellValue(cell3)))){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，缺少摄影家头像");
    				POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
        			result2 = false;
        			continue;
    			}
    		}
    		if(cell4 == null || POIUtil.getCellValue(cell4).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，显示标志数据有误");
    			POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
    			result2 = false;
    			continue;
    		}
    		if(cell5 == null || POIUtil.getCellValue(cell5).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息中，显示顺序数据有误");
    			POIUtil.writeErrorInfo(listfile, 1, i, 5, "信息有误");
    			result2 = false;
    			continue;
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----摄影家头像信息完整");
		
		
		//校验第三个sheet，第三个sheet为摄影家作品信息
		PrintUtil.printLog(printWriter, "开始校验----摄影家作品信息");
		int sheet3_rowNum;
		sheet3_rowNum = sheet3.getLastRowNum();
		for (int i = 1; i < sheet3_rowNum; i++) {
			Row rw = sheet3.getRow(i);
			Cell cell1 = rw.getCell(0);
			Cell cell2 = rw.getCell(1);
			Cell cell3 = rw.getCell(2);
			Cell cell4 = rw.getCell(3);
			Cell cell5 = rw.getCell(4);
			//Cell cell6 = rw.getCell(5);拍摄过程可以为空
			Cell cell7 = rw.getCell(6);
			Cell cell8 = rw.getCell(7);
			Cell cell9 = rw.getCell(8);
			Cell cell10 = rw.getCell(9);
    		if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品ID数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品名称数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品类型数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}else{
    			if(POIUtil.getCellValue(cell3).length()>2){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品类型数据长度有误");
    				POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
        			result3 = false;
        			continue;
    			}
    		}
    		if(cell4 == null || POIUtil.getCellValue(cell4).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品拍摄日期数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}else{
    			if(POIUtil.getCellValue(cell4).length()!=8){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品拍摄日期数据长度有误");
    				POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
        			result3 = false;
        			continue;
    			}
    		}
    		if(cell5 == null || POIUtil.getCellValue(cell5).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品解读数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}else{
    			if(POIUtil.getCellValue(cell5).length()>12){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品解读数据长度有误");
    				POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
        			result3 = false;
        			continue;
    			}
    		}
    		if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，作品文件名数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+workspath+File.separator+POIUtil.getCellValue(cell7)))){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，缺少作品文件");
    				POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
        			result3 = false;
        			continue;
    			}
    		}
    		if(cell8 == null || POIUtil.getCellValue(cell8).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，摄影家ID数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}
    		if(cell9 == null || POIUtil.getCellValue(cell9).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，代表作标识数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}
    		if(cell10 == null || POIUtil.getCellValue(cell10).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息中，显示顺序数据有误");
    			POIUtil.writeErrorInfo(listfile, 2, i, 10, "信息有误");
    			result3 = false;
    			continue;
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----摄影家作品信息完整");
		
		//校验第四个sheet，第四个sheet为摄影家核心介绍
		PrintUtil.printLog(printWriter, "开始校验----摄影家核心介绍");
		Row sheet4_row = sheet4.getRow(1); 
		Cell sheet4_cell1 = sheet4_row.getCell(0);
		Cell sheet4_cell2 = sheet4_row.getCell(1);
		Cell sheet4_cell3 = sheet4_row.getCell(2);
		Cell sheet4_cell4 = sheet4_row.getCell(3);
		Cell sheet4_cell5 = sheet4_row.getCell(4);
		Cell sheet4_cell6 = sheet4_row.getCell(5);
		Cell sheet4_cell7 = sheet4_row.getCell(6);
		//Cell shee4t_cell8 = sheet4_row.getCell(7);H5链接可以为空
		
		if(sheet4_cell1 == null || POIUtil.getCellValue(sheet4_cell1).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章标识数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}
		if(sheet4_cell2 == null || POIUtil.getCellValue(sheet4_cell2).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章类型数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}else{
			if(POIUtil.getCellValue(sheet4_cell2).length()>2){
				PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章类型数据长度有误");
				POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
				result4 = false;
			}
		}
		if(sheet4_cell3 == null || POIUtil.getCellValue(sheet4_cell3).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章封面数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}else{
			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+coreintropath+File.separator+POIUtil.getCellValue(sheet4_cell3)))){
				PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，缺少文章封面文件");
				POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
				result4 = false;
			}
		}
		if(sheet4_cell4 == null || POIUtil.getCellValue(sheet4_cell4).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章标题数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}
		if(sheet4_cell5 == null || POIUtil.getCellValue(sheet4_cell5).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章内容数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}
		if(sheet4_cell6 == null || POIUtil.getCellValue(sheet4_cell6).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章文档数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}else{
			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+coreintropath+File.separator+POIUtil.getCellValue(sheet4_cell6)))){
				PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，缺少文章文档文件");
				POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
				result4 = false;
			}
		}
		if(sheet4_cell7 == null || POIUtil.getCellValue(sheet4_cell7).equals("")){
			PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，文章H5图片数据有误");
			POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
			result4 = false;
		}else{
			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+coreintropath+File.separator+POIUtil.getCellValue(sheet4_cell7)))){
				PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍信息中，缺少文章H5图片");
				POIUtil.writeErrorInfo(listfile, 3, 1, 8, "信息有误");
				result4 = false;
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----摄影家核心介绍完整");
		
		//校验第五个sheet，第五个sheet为摄影家作品解读
		PrintUtil.printLog(printWriter, "开始校验----摄影家作品解读");
		int sheet5_rowNum;
		sheet5_rowNum = sheet5.getLastRowNum();
		for (int i = 1; i < sheet5_rowNum; i++) {
			Row rw = sheet5.getRow(i);
			Cell cell1 = rw.getCell(0);
			Cell cell2 = rw.getCell(1);
			Cell cell3 = rw.getCell(2);
			Cell cell4 = rw.getCell(3);
			Cell cell5 = rw.getCell(4);
			Cell cell6 = rw.getCell(5);
			Cell cell7 = rw.getCell(6);
			//Cell cell8 = rw.getCell(7);H5链接可以为空
    		if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章标识数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章类型数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}else{
    			if(POIUtil.getCellValue(cell2).length()>2){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章类型数据长度有误");
    				POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
        			result5 = false;
        			continue;
    			}
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章封面数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+worksintropath+File.separator+POIUtil.getCellValue(cell3)))){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读中，缺少封面文件");
    				POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
        			result5 = false;
        			continue;
    			}
    		}
    		if(cell4 == null || POIUtil.getCellValue(cell4).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章标题数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}
    		if(cell5 == null || POIUtil.getCellValue(cell5).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章内容数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}
    		if(cell6 == null || POIUtil.getCellValue(cell6).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章文档数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+worksintropath+File.separator+POIUtil.getCellValue(cell6)))){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，缺少文章文档文件");
    				POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
        			result5 = false;
        			continue;
    			}
    		}
    		if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，文章H5图片数据有误");
    			POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
    			result5 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+worksintropath+File.separator+POIUtil.getCellValue(cell7)))){
    				PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读信息中，缺少文章H5图片");
    				POIUtil.writeErrorInfo(listfile, 4, i, 8, "信息有误");
        			result5 = false;
        			continue;
    			}
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----摄影家作品解读完整");
		
		//校验第六个sheet，第六个sheet为核心介绍与作品关系
		Row sheet6_row = sheet6.getRow(1);
		if(sheet6_row!=null){
			PrintUtil.printLog(printWriter, "开始校验----核心介绍与作品关系");
			int sheet6_rowNum;
			sheet6_rowNum = sheet6.getLastRowNum();
			for (int i = 1; i < sheet6_rowNum; i++) {
				Row rw = sheet6.getRow(i);
				Cell cell1 = rw.getCell(0);
				Cell cell2 = rw.getCell(1);
				Cell cell3 = rw.getCell(2);
				if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
					PrintUtil.printLog(printWriter, "校验结果----核心介绍与作品关系信息中，文章标识数据有误");
					POIUtil.writeErrorInfo(listfile, 5, i, 3, "信息有误");
        			result6 = false;
        			continue;
				}
				if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
					PrintUtil.printLog(printWriter, "校验结果----核心介绍与作品关系信息中，作品标识数据有误");
					POIUtil.writeErrorInfo(listfile, 5, i, 3, "信息有误");
        			result6 = false;
        			continue;
				}
				if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
					PrintUtil.printLog(printWriter, "校验结果----核心介绍与作品关系信息中，作品顺序数据有误");
					POIUtil.writeErrorInfo(listfile, 5, i, 3, "信息有误");
        			result6 = false;
        			continue;
				}
			}
			PrintUtil.printLog(printWriter, "校验结果----核心介绍与作品关系完整");
		}
		if(result1&&result2&&result3&&result4&&result5&&result6){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 校验摄影家(新)
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPhotogFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result = true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		File file = new File(filepath);
		if(!file.exists()){
			PrintUtil.printLog(printWriter, "文件不存在，无法解析");
			return false;
		}
		List<Sheet> sheetList = POIUtil.getAllSheetFromWb(file);
		Sheet sheet = sheetList.get(0);
		//校验第一个sheet，第一个sheet为摄影家汇总信息
		PrintUtil.printLog(printWriter, "开始校验----摄影家基本信息");
		
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			Cell cell1 = row.getCell(0);//根据模板协议，获取摄影家姓名
			if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家姓名数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell2 = row.getCell(1);//根据模板协议，获取摄影家性别
			if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家性别数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell2).length()!=1){
					PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家性别数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell3 = row.getCell(2);//根据模板协议，获取摄影家年代
			if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家年代数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell3).length()>16){
					PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家年代数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell4 = row.getCell(3);//根据模板协议，获取摄影家出生日期
			if(cell4 != null && !POIUtil.getCellValue(cell4).equals("")){
				if(POIUtil.getCellValue(cell4).length()>4){
					PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家出生日期数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell5 = row.getCell(4);//根据模板协议，获取摄影家去世日期
			if(cell5 != null && !POIUtil.getCellValue(cell5).equals("")){
				if(POIUtil.getCellValue(cell5).length()>4){
					PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家去世日期数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell6 = row.getCell(5);//根据模板协议，获取摄影家国籍
			if(cell6 == null || POIUtil.getCellValue(cell6).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家国籍数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell6).length()>3){
					PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家国籍数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell7 = row.getCell(6);//根据模板协议，获取显示顺序
			if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家显示顺序数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
		}
		if(result){
			PrintUtil.printLog(printWriter, "校验结果----摄影家信息完整");
		}
		return result;
	}
	
	
	/**
	 * 校验摄影家作品
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPhotogWorksFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result = true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		File file = new File(filepath);
		if(!file.exists()){
			PrintUtil.printLog(printWriter, "文件不存在，无法解析");
			return false;
		}
		String dirname [] = {"worksimg"};
		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
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
		Sheet sheet = sheetList.get(0);
		//校验第一个sheet，第一个sheet为摄影家汇总信息
		PrintUtil.printLog(printWriter, "开始校验----作品基本信息");
		
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			/*Cell cell1 = row.getCell(0);//根据模板协议，获取作品姓名
			if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----摄影家基本信息中，摄影家姓名数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}*/
//			obj_works.put("shootDate", POIUtil.getCellValue(cell3));
//			obj_works.put("worksIntro", POIUtil.getCellValue(cell4));
//			obj_works.put("shootProc", POIUtil.getCellValue(cell5));
//			obj_works.put("fileName", relativePath);
//			//obj_works.put("pId", POIUtil.getCellValue(cell8));
//			obj_works.put("isRepre", POIUtil.getCellValue(cell7));
//			obj_works.put("showIndex", POIUtil.getCellValue(cell8));
			/*Cell cell2 = row.getCell(1);//根据模板协议，获取作品类型
			if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，作品类型数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				/*if(POIUtil.getCellValue(cell2).length()!=1){
					PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，作品类型数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell3 = row.getCell(2);//根据模板协议，获取拍摄日期
			if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，拍摄日期数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell3).length()>16){
					PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，拍摄日期数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}*/
			/*Cell cell4 = row.getCell(3);//根据模板协议，获取作品解读
			if(cell4 != null && !POIUtil.getCellValue(cell4).equals("")){
				if(POIUtil.getCellValue(cell4).length()>4){
					PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，作品解读数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}*/
			/*Cell cell5 = row.getCell(4);//根据模板协议，获取拍摄过程
			if(cell5 != null && !POIUtil.getCellValue(cell5).equals("")){
				if(POIUtil.getCellValue(cell5).length()>4){
					PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，拍摄过程数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}*/
			Cell cell1 = row.getCell(0);//根据模板协议，获取作品姓名
			Cell cell2 = row.getCell(1);//根据模板协议，获取作品类型
			Cell cell3 = row.getCell(2);//根据模板协议，获取拍摄日期
			Cell cell4 = row.getCell(3);//根据模板协议，获取作品解读
			Cell cell5 = row.getCell(4);//根据模板协议，获取拍摄过程
			Cell cell6 = row.getCell(5);//根据模板协议，获取文件名
			Cell cell7 = row.getCell(6);//根据模板协议，获取是否代表作
			Cell cell8 = row.getCell(7);//根据模板协议，获取显示顺序
			if (cell1==null&&cell2==null&&cell3==null&&cell4==null&&cell5==null&&cell6==null&&cell7==null&&cell8==null) {
				break;
			}
			if(cell6 == null || POIUtil.getCellValue(cell6).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，文件名数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，是否代表作数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell7).length()!=1){
					PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，是否代表作数据有误");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			/*Cell cell8 = row.getCell(7);//根据模板协议，获取显示顺序
			if(cell7 == null || POIUtil.getCellValue(cell8).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----作品基本信息中，摄影家显示顺序数据有误");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}*/
		}
		if(result){
			PrintUtil.printLog(printWriter, "校验结果----作品信息完整");
		}
		return result;
	}
	
	/**
	 * 校验文章
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkArticleFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result1= true,result2= true,result3= true;
		PrintUtil.printLog(printWriter, "开始校验----文件是否完整");
		String dirname [] = {"coverimg","h5img","word"};
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
		Sheet sheet2 = null;//第二个Sheet为文章摄影家关系信息
		Sheet sheet3 = null;//第三个Sheet为文章作品关系信息
		if(sheetList.size()==3){
			sheet1 = sheetList.get(0);//第一个Sheet为摄影家基本信息
			sheet2 = sheetList.get(1);//第二个Sheet为摄影家头像信息
			sheet3 = sheetList.get(2);//第三个Sheet为摄影家作品信息
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
			Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
			Cell cell2 = rw.getCell(1);//根据模板协议，获取文章类型
			Cell cell3 = rw.getCell(2);//根据模板协议，获取文章封面
			Cell cell4 = rw.getCell(3);//根据模板协议，获取文章标题
			Cell cell5 = rw.getCell(4);//根据模板协议，获取文章内容
			Cell cell6 = rw.getCell(5);//根据模板协议，获取文章文档
			Cell cell7 = rw.getCell(6);//根据模板协议，获取文章图片
			//Cell cell8 = rw.getCell(7);//根据模板协议，获取文章链接,但可以为空
			if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章标识数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}
    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章类型数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(POIUtil.getCellValue(cell2).length()>2){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章类型数据长度有误");
    				POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章封面数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+articlecover+File.separator+POIUtil.getCellValue(cell3)))){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，缺少封面文件");
    				POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
    		if(cell4 == null || POIUtil.getCellValue(cell4).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章标题数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}
    		/*if(cell5 == null || POIUtil.getCellValue(cell5).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章内容数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}*/
    		if(cell6 == null || POIUtil.getCellValue(cell6).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章文档数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+articleword+File.separator+POIUtil.getCellValue(cell6)))){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，缺少文章文档文件");
    				POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
    		if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
    			PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，文章H5图片数据有误");
    			POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    			result1 = false;
    			continue;
    		}else{
    			if(!FileTransferUtil.CheckFileExists(new File(filepath+File.separator+articleh5+File.separator+POIUtil.getCellValue(cell7)))){
    				PrintUtil.printLog(printWriter, "校验结果----文章汇总信息中，缺少文章H5图片");
    				POIUtil.writeErrorInfo(listfile, 0, i, 8, "信息有误");
    				result1 = false;
        			continue;
    			}
    		}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章汇总信息完整");
		
		//校验第二个sheet，第二个sheet为文章摄影家信息
		PrintUtil.printLog(printWriter, "开始校验----文章摄影家关系信息");
		Row sheet2_row = sheet2.getRow(1);
		if(sheet2_row!=null){
			int sheet2_rowNum;
			sheet2_rowNum = sheet2.getLastRowNum();
			for (int i = 1; i < sheet2_rowNum; i++) {
				Row rw = sheet2.getRow(i);
				Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
				Cell cell2 = rw.getCell(1);//根据模板协议，获取摄影家ID
				Cell cell3 = rw.getCell(2);//根据模板协议，获取摄影家顺序
				if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章摄影家信息中，文章ID数据有误");
	    			POIUtil.writeErrorInfo(listfile, 1, i, 3, "信息有误");
        			result2 = false;
        			continue;
	    		}
	    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章摄影家信息中，摄影家ID数据有误");
	    			POIUtil.writeErrorInfo(listfile, 1, i, 3, "信息有误");
        			result2 = false;
        			continue;
	    		}
	    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章摄影家信息中，摄影家顺序数据有误");
	    			POIUtil.writeErrorInfo(listfile, 1, i, 3, "信息有误");
        			result2 = false;
        			continue;
	    		}
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章摄影家关系信息完整");
		
		//校验第三个sheet，第三个sheet为文章作品信息
		PrintUtil.printLog(printWriter, "开始校验----文章作关系信息");
		Row sheet3_row = sheet3.getRow(1);
		if(sheet3_row!=null){
			int sheet3_rowNum;
			sheet3_rowNum = sheet3.getLastRowNum();
			for (int i = 1; i < sheet3_rowNum; i++) {
				Row rw = sheet3.getRow(i);
				Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
				Cell cell2 = rw.getCell(1);//根据模板协议，获取摄影家ID
				Cell cell3 = rw.getCell(2);//根据模板协议，获取摄影家顺序
				if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章作品信息中，文章ID数据有误");
	    			POIUtil.writeErrorInfo(listfile, 2, i, 3, "信息有误");
        			result3 = false;
        			continue;
	    		}
	    		if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章作品信息中，作品ID数据有误");
	    			POIUtil.writeErrorInfo(listfile, 2, i, 3, "信息有误");
        			result3 = false;
        			continue;
	    		}
	    		if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
	    			PrintUtil.printLog(printWriter, "校验结果----文章作品信息中，作品顺序数据有误");
	    			POIUtil.writeErrorInfo(listfile, 2, i, 3, "信息有误");
        			result3 = false;
        			continue;
	    		}
			}
		}
		PrintUtil.printLog(printWriter, "校验结果----文章作品关系信息完整");
		
		if(result1&&result2&&result3){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 校验书法家
	 * @param filepath
	 * @param printWriter
	 * @return
	 * @throws Exception
	 */
	public static boolean checkCgFile(String filepath,PrintWriter printWriter) throws Exception{
		boolean result = true;
		File file = new File(filepath);
		/*JSONArray AreaSaturation = new JSONArray();
		JSONObject obj = new JSONObject();
		String str="";
		obj.put("data", str);*/
		if(!file.exists()){
	/*		obj.put("data", obj.get("data")+"文件不存在，无法解析<br>");
			AreaSaturation.add(obj);
			printWriter.print(AreaSaturation.toString());*/
			PrintUtil.printLog(printWriter, "文件不存在，无法解析");
			return false;
		}
		List<Sheet> sheetList = POIUtil.getAllSheetFromWb(file);
		Sheet sheet = sheetList.get(0);
		//校验第一个sheet，第一个sheet为摄影家汇总信息
		PrintUtil.printLog(printWriter, "开始校验----书法家基本信息");
		
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			Cell cell1 = row.getCell(0);//根据模板协议，获取摄影家姓名
			if(cell1 == null || POIUtil.getCellValue(cell1).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家姓名数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家姓名数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell2 = row.getCell(1);//根据模板协议，获取摄影家性别
			if(cell2 == null || POIUtil.getCellValue(cell2).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家性别数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家性别数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell2).length()!=1){
					PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家性别数据有误");
					//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家性别数据有误<br>");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell3 = row.getCell(2);//根据模板协议，获取摄影家年代
			if(cell3 == null || POIUtil.getCellValue(cell3).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家民族数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家民族数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell3).length()!=3){
					PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家民族数据有误");
					//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家民族数据有误<br>");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell4 = row.getCell(3);//根据模板协议，获取摄影家出生日期
			if(cell4 != null && !POIUtil.getCellValue(cell4).equals("")){
				if(POIUtil.getCellValue(cell4).length()!=3){
					PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家朝代数据有误");
					//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家朝代数据有误<br>");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell5 = row.getCell(4);//根据模板协议，获取摄影家去世日期
			if(cell5 != null && !POIUtil.getCellValue(cell5).equals("")){
				if(POIUtil.getCellValue(cell5).length()<4){
					PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家出生时间数据有误");
					//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家出生时间数据有误<br>");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell6 = row.getCell(5);//根据模板协议，获取摄影家国籍
			if(cell6 == null || POIUtil.getCellValue(cell6).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家去世时间数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家去世时间数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}else{
				if(POIUtil.getCellValue(cell6).length()<4){
					PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家去世时间数据有误");
					//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家去世时间数据有误<br>");
					POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
					result = false;
					continue;
				}
			}
			Cell cell7 = row.getCell(6);//根据模板协议，获取显示顺序
			if(cell7 == null || POIUtil.getCellValue(cell7).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家字数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家字数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell8 = row.getCell(7);//根据模板协议，获取显示顺序
			if(cell8 == null || POIUtil.getCellValue(cell8).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家号数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家号数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			
			Cell cell9 = row.getCell(8);//根据模板协议，获取显示顺序
			if(cell9 == null || POIUtil.getCellValue(cell9).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家祖籍数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家祖籍数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			
			Cell cell10 = row.getCell(9);//根据模板协议，获取显示顺序
			if(cell10 == null || POIUtil.getCellValue(cell10).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家出生地数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家出生地数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell11 = row.getCell(10);//根据模板协议，获取显示顺序
			if(cell11 == null || POIUtil.getCellValue(cell11).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家别称数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家别称数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell12 = row.getCell(11);//根据模板协议，获取显示顺序
			if(cell12 == null || POIUtil.getCellValue(cell12).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家地位数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家地位数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell13 = row.getCell(12);//根据模板协议，获取显示顺序
			if(cell13 == null || POIUtil.getCellValue(cell13).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家重要作品数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家重要作品数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			Cell cell14 = row.getCell(13);//根据模板协议，获取显示顺序
			if(cell14 == null || POIUtil.getCellValue(cell14).equals("")){
				PrintUtil.printLog(printWriter, "校验结果----书法家基本信息中，书法家重要事迹数据有误");
				//obj.put("data", obj.get("data")+"校验结果----书法家基本信息中，书法家重要事迹数据有误<br>");
				POIUtil.writeErrorInfo(file, 0, i, 8, "信息有误");
				result = false;
				continue;
			}
			
			}
			if(result){
				PrintUtil.printLog(printWriter,"校验结果----书法家信息完整");
				/*obj.put("data", obj.get("data")+"校验结果----书法家信息完整<br>");*/

			}
			
			/*AreaSaturation.add(obj);
			printWriter.print(AreaSaturation.toString());
			printWriter.flush();*/
			return result;
		}
}
