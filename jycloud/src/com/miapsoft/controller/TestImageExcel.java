package com.miapsoft.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestImageExcel {
	public static void main(String[] args) {
		 FileOutputStream fileOut = null;   
         BufferedImage bufferImg = null;   
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
          //加载图片
            bufferImg = ImageIO.read(new File("C:/Users/张博文.ZBW/Desktop/image/wumaner.jpg"));   
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFWorkbook wb = new HSSFWorkbook();   
            HSSFSheet sheet1 = wb.createSheet("sheet1");  
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();   
            /**
             	dx1 - 定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0
				dy1 - 定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0
				dx2 - 定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0
				dy2 - 定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0
				col1 - 第一个cell所在列，既图片左上角所在列
				row1 - 图片左上角所在行
				col2 - 图片右下角所在列
				row2 - 图片右下角所在行
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 0, 1, (short) 1, 1);   
            //插入图片  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)); 
            fileOut = new FileOutputStream("C:\\Users\\张博文.ZBW\\Desktop\\image\\excel.xls");   
            // 输出文件 
             wb.write(fileOut);   
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
