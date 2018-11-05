package com.miapsoft.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import jxl.Workbook;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOut {
	public int excel(String path,List<Map<String, Object>> list){
		int conut=0;
		WritableWorkbook workbook; 
		File file=new File(path);
		file.delete();
		String[] title = {"图片名称","胶卷名称","胶卷品牌","图片中文名称","标签","图片网站","相机名称","焦距","镜头","ISO","快门","图片链接"};
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date());
		try {
			OutputStream os=new  FileOutputStream(path);
			workbook=Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet(date, 0);
			
			Label label;
			for(int i=0;i<title.length;i++){
				label = new Label(i,  0 , title[i]);
				sheet.addCell(label);
			}
			for(int j=0;j<list.size();j++){
				String path2=list.get(j).get("PIC_NAME").toString()+".jpg";
				String ss="HYPERLINK(\""+path2+"\",\"查看图片\")";
				sheet.addCell(new Label(0,  j+1 , list.get(j).get("PIC_ID").toString()));
				sheet.addCell(new Label(1,  j+1 , list.get(j).get("PIC_FILM").toString()));
				sheet.addCell(new Label(2,  j+1 , list.get(j).get("PIC_FILMBRAND").toString()));
				sheet.addCell(new Label(3,  j+1 , list.get(j).get("PIC_NAME").toString()));
				sheet.addCell(new Label(4,  j+1 , list.get(j).get("PIC_LABEL").toString()));
				sheet.addCell(new Label(5,  j+1 , list.get(j).get("PIC_WEB").toString()));
				sheet.addCell(new Label(6,  j+1 , list.get(j).get("PIC_CAMERA").toString()));
				sheet.addCell(new Label(7,  j+1 , list.get(j).get("PIC_FOCAL").toString()));
				sheet.addCell(new Label(8,  j+1 , list.get(j).get("PIC_LENS").toString()));
				sheet.addCell(new Label(9,  j+1 , list.get(j).get("PIC_ISO").toString()));
				sheet.addCell(new Label(10,  j+1 , list.get(j).get("PIC_SHUTTER").toString()));
				sheet.addCell(new Formula(11,  j+1 , ss));
				conut++;
			}
			workbook.write();
			os.flush();
			workbook.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conut;
	}
}
