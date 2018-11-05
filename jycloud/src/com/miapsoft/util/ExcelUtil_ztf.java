package com.miapsoft.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil_ztf {
	
    /**
     * 创建excel文档，xls的文件
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * @throws Exception 
     * */
	public static Workbook exportTable(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        // 创建第一行
        Row row = sheet.createRow((short) 0);
        
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBold(true);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setAlignment(HorizontalAlignment.CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs2.setAlignment(HorizontalAlignment.CENTER);
        
        for(int i=0;i<columnNames.length;i++){
        		Cell cell3 = row.createCell(i);
                cell3.setCellValue(columnNames[i]);
                cell3.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            row1.setHeight((short) (35.7 * 50));
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell4 = row1.createCell(j);
                if (j==2) {
                	try {
                		String imgPath=list.get(i).get(keys[j]).toString();
                		FileInputStream fis  = new FileInputStream(imgPath);
             	        // 图片文件解密
             	        String key = EncryptKey.DesKey;
             	        InputStream sbs = new ByteArrayInputStream(DesUtil.decryptFile(key,fis));
             	       
                		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    	BufferedImage bufferImg = ImageIO.read(sbs);
                    	ImageIO.write(bufferImg, "jpg", byteArrayOut);
                    	Drawing patriarch = sheet.createDrawingPatriarch();
                    	HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 2, i, (short) 3, i+1);   
                    	patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)); 
					} catch (Exception e) {
						// TODO: handle exception
					}
				} else {
					cell4.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
				}
                cell4.setCellStyle(cs2);
            }
        }
        return wb;
    }
	/**
     * 创建excel文档，xls的文件
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * @throws Exception 
     * */
	public static Workbook exportTable2(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new XSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        // 创建第一行
        Row row = sheet.createRow((short) 0);
        
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBold(true);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setAlignment(HorizontalAlignment.CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs2.setAlignment(HorizontalAlignment.CENTER);
        
        for(int i=0;i<columnNames.length;i++){
        		Cell cell3 = row.createCell(i);
                cell3.setCellValue(columnNames[i]);
                cell3.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            row1.setHeight((short) (35.7* 10));
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell4 = row1.createCell(j);
                if (j==5) {
                	try {
                		cell4.setCellValue(list.get(i).get(keys[j])+"");
                		CreationHelper createHelper = wb.getCreationHelper();
                		XSSFHyperlink  link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
                		link.setAddress("./worksimg/"+list.get(i).get(keys[j]));
                		cell4.setHyperlink(link);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {
					cell4.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
				}
                cell4.setCellStyle(cs2);
            }
        }
        return wb;
    }
}
