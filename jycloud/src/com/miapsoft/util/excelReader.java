package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>Title: excelReader.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 精益有容(北京)科技公司</p>
 * <p>Company: 精益有容(北京)科技公司</p>
 * @author BaiSH
 * @version 1.0 创建时间：2016-2-23 下午3:06:03
 */
public class excelReader {
	  
	/**
	   * 获取excel文件表头
	   * @param file 文件
	   * @param filetype 文件类型
	   * @throws IOException
	   */
	public String[] GetFileTitle(File file,String filetype){
		try {
			Workbook wb = null;
			FileInputStream stream = new FileInputStream(file);
			if ("XLSX".equals(filetype)) {
				wb = new XSSFWorkbook(stream);
			}else if("XLS".equals(filetype)){
				wb = new HSSFWorkbook(stream);
			}else {
		    	throw new FileFormateException();
		    }
			Sheet sheet1 = wb.getSheetAt(0);
			Row row = sheet1.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			String[] title = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				title[i] = row.getCell(i).getStringCellValue();
			}
			return title;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (FileFormateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	  /**
	   * XLSX文件读取
	   * @param file 文件
	   * @throws IOException
	   */
	  public void ReadXLSX(File file,List<Map<String, Object>> listdata){
		  String [] title =  GetFileTitle(file,"XLSX");
		  try {
			FileInputStream stream = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(stream);
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {
				if (i != 0) {
					Map<String, Object> mapdata = new HashMap<String, Object>();
					Row row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j); 
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								mapdata.put(title[j], cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								mapdata.put(title[j], Math.round(cell.getNumericCellValue()));
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								mapdata.put(title[j], cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_FORMULA:                                                    
				                mapdata.put(title[j], cell.getCellFormula());
				                break;   
							default:
								break;
							}
					}
					listdata.add(mapdata);
				}
			}
			System.out.println("ok");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	  }
	  /**
	   * XLS文件读取
	   * @param file 文件
	   * @throws IOException
	   */
	  public void ReadXLS(File file,List<Map<String, Object>> listdata){
		  String [] title =  GetFileTitle(file,"XLS");
		  try {
			FileInputStream stream = new FileInputStream(file);
			Workbook wb = new HSSFWorkbook(stream);
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {
				if (i != 0) {
					Map<String, Object> mapdata = new HashMap<String, Object>();
					Row row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j); 
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								mapdata.put(title[j], cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								mapdata.put(title[j], Math.round(cell.getNumericCellValue()));
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								mapdata.put(title[j], cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_FORMULA:                                                    
				                mapdata.put(title[j], cell.getCellFormula());
				                break;   
							default:
								break;
							}
					}
					listdata.add(mapdata);
				}
			}
			System.out.println("ok");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  }
	  
	  /**
	   * XLSX文件写入
	   * @param outPath 文件路径
	   * @return
	   * @throws Exception
	   */
	  public static boolean write(String outPath){
	    String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
	    System.out.println(fileType);
	    // 创建工作文档对象
	    Workbook wb = null;
	    try {
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook();
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook();
			} else {
				throw new FileFormateException();
			}
		} catch (FileFormateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 创建sheet对象
	    Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
	    // 循环写入行数据
	    for (int i = 0; i < 5; i++) {
	      Row row = (Row) sheet1.createRow(i);
	      // 循环写入列数据
	      for (int j = 0; j < 8; j++) {
	        Cell cell = row.createCell(j);
	        cell.setCellValue("测试" + j);
	      }
	    }
	    // 创建文件流
	    OutputStream stream;
		try {
			stream = new FileOutputStream(outPath);
			// 写入数据
			wb.write(stream);
			// 关闭文件流
			stream.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	  }

	  /**
	   * XLSX文件写入
	   * @param outPath 文件路径
	   * @return
	   * @throws Exception
	   */
	  public static Workbook writetoWorkbook(String userType){
		  	Workbook wb = null;
			if("teacher".equals(userType)){
				// 创建工作文档对象
				wb = new XSSFWorkbook();
				// 创建sheet对象
				Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
				Row row = (Row) sheet1.createRow(0);
				Cell cell0 = row.createCell(0);
				Cell cell1 = row.createCell(1);
				cell0.setCellValue("工号");
				cell1.setCellValue("姓名");
			}else if("student".equals(userType)){
				// 创建工作文档对象
				wb = new XSSFWorkbook();
				// 创建sheet对象
				Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
				Row row = (Row) sheet1.createRow(0);
				Cell cell0 = row.createCell(0);
				Cell cell1 = row.createCell(1);
				Cell cell2 = row.createCell(2);
				cell0.setCellValue("学号");
				cell1.setCellValue("姓名");
				cell2.setCellValue("班级");
			}else{
				throw new NullPointerException();
			}
		    return wb;
	  }
	  
	  /**
	   * 测试
	   * @param args
	   */
	  public static void main(String[] args) {
	   try {	
		  /* List<Map<String, Object>> listdata = new ArrayList<Map<String,Object>>();
		   	excelReader excelReader = new excelReader();
		   	excelReader.ReadXLSX(new File("V:\\1b69\\Do\\学生信息.xlsx"), listdata);
		   	System.out.println(listdata.size());
	    	for (Map<String, Object> map : listdata) {
					System.out.println(map.get("姓名")+" "+map.get("学号"));
			}*/
		   excelReader.write("v:\\123.xlsx");
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	  
	  
}
class FileFormateException extends Exception{
	private final String  FileFormateError = "FileFormate Error :Your file format is incorrect";
	public String getErrorInfo() {
		return FileFormateError;
	}
}