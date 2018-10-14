package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcel {
	
	public static Map<String,List<List<String>>> ReadCatalog(File file){
		Map<String,List<List<String>>> result = new HashMap<String, List<List<String>>>();
		String fileName = file.getName();
		String postifx = fileName.substring(fileName.indexOf(".")+1);
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("文件异常！");
			e.printStackTrace();
		};
		Workbook wb = null;
		System.out.println("----------开始转换Excel文件----------");
		if("XLSX".equalsIgnoreCase(postifx.toUpperCase())){
			try {
				wb = new XSSFWorkbook(stream);
			} catch (IOException e) {
				System.err.println("文件类型错误！");
				e.printStackTrace();
			};
		}else if("XLS".equalsIgnoreCase(postifx.toUpperCase())){
			try {
				wb = new XSSFWorkbook(stream);
			} catch (IOException e) {
				System.err.println("文件类型错误！");
				e.printStackTrace();
			};
		}
		System.out.println("----------获取Excel文件成功----------");
		Sheet sheet = wb.getSheetAt(0);
		System.out.println("----------获取第一个sheet----------");
		String groupName="";
		int rowNum = sheet.getLastRowNum();
		for (int i = 0; i <= rowNum; i++) {
			if (i != 0) {
				Row row = sheet.getRow(i);
				if(row==null){
					continue;
				};
				if (i==1){
					groupName =switchstring(row.getCell(1));
					result.put(groupName, new ArrayList<List<String>>());
					List<String> onelist= new ArrayList<String>();
					onelist.add(switchstring(row.getCell(2)));
					onelist.add(switchstring(row.getCell(3)));
					result.get(groupName).add(onelist);
				};
				
				String newgroupName= switchstring(row.getCell(1));
				if(!" ".equals(newgroupName)){
					groupName =newgroupName;
					result.put(groupName, new ArrayList<List<String>>());
					List<String> onelist= new ArrayList<String>();
					onelist.add(switchstring(row.getCell(2)));
					onelist.add(switchstring(row.getCell(3)));
					result.get(groupName).add(onelist);
				}else{
					List<String> onelist= new ArrayList<String>();
					onelist.add(switchstring(row.getCell(2)));
					onelist.add(switchstring(row.getCell(3)));
					result.get(groupName).add(onelist);
				}
				
				
				
			};
		};
		System.out.println("----------文件目录读取结束----------");
		System.out.println(result.toString());
		return result;
	};
	
	
	public static String switchstring(Cell cell){
		String str=" ";
		if(cell!=null){
			switch (cell.getCellType()){
				case Cell.CELL_TYPE_STRING:
					str=cell.getStringCellValue().trim();
					break;
				default:
					break;	
			}
		};
		if("".equals(str)){
			str=" ";
		}
		return str;
	};
	
}
