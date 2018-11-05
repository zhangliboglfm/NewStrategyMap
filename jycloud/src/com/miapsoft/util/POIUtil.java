/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-1
*/
package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtil {
	

	/**
	 * 获取单元格数据
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public static String getCellValue(Cell cell) throws Exception {
        String cellValue = ""; 
        if(cell==null){
        	return cellValue;
        }
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellTypeEnum()) {  
        case STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case NUMERIC:  
            cellValue = df.format(cell.getNumericCellValue()).toString();  
            break;  
        case BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case FORMULA:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break; 
        case BLANK:  
            cellValue = "";  
            break;
        default:  
            cellValue = "";  
        }
        return cellValue;  
    }
	//id列
	public static String getCellValue2(Cell cell) throws Exception {
        String cellValue = ""; 
        if(cell==null){
        	return cellValue;
        }
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellTypeEnum()) {  
        case STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case NUMERIC:  
            cellValue = df.format(cell.getNumericCellValue()).toString();  
            break;  
        case BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case FORMULA:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break; 
        case BLANK:  
            break;
        default:  
            cellValue = "";  
        }
        return cellValue;  
    }
	
	/**
	 * 获取Excel文件Sheet
	 * @param excel
	 * @return
	 * @throws Exception
	 */
	public static List<Sheet> getAllSheetFromWb(File excel) throws Exception{
		List<Sheet> list = new ArrayList<Sheet>();
		if(!excel.exists()){
			throw new FileNotFoundException("文件未找到");
		}
		FileInputStream fis = null;
    	try {
            fis = new FileInputStream(excel);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
		Workbook wb = null;
		String exceltype = excel.getName().substring(excel.getName().lastIndexOf(".") + 1);
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
		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet sheet = wb.getSheetAt(i);
			list.add(sheet);
		}
		return list;
	}
	
	/**
	 * 写入错误信息
	 * @param cell
	 * @param str
	 * @throws Exception 
	 */
	public static void writeErrorInfo(File excel,int sheetnum, int rownum,int cellnum,String str) throws Exception{
		if(!excel.exists()){
			throw new FileNotFoundException("文件未找到");
		}
		FileInputStream fis = null;//文件输入流
		FileOutputStream out = null;//文件输出流
    	try {
            fis = new FileInputStream(excel);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
		Workbook wb = null;
		String exceltype = excel.getName().substring(excel.getName().lastIndexOf(".") + 1);
		if(exceltype.equalsIgnoreCase("xlsx")){
        	wb = new XSSFWorkbook(fis);
		}else if(exceltype.equalsIgnoreCase("xls")){
			wb = new HSSFWorkbook(fis);
		}
		System.out.println("文件第"+sheetnum+"个sheet页，第"+rownum+"行，信息有误");
		
		//写入错误信息
		Sheet sheet = wb.getSheetAt(sheetnum);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(str);
		
		out = new FileOutputStream(excel);
		wb.write(out);//写入文件，覆盖原文件
		//关闭文件流
		try {
            fis.close();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}
	
	
}	
