package com.miapsoft.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.imageio.ImageIO;


import com.miapsoft.util.ResizeImage;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Createthumbnail {
	public static void main(String[] args) throws Exception{
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// mysql驱动
		con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.195:3306/DigitualCamera","root","Flzx3qc");

		Statement ps =  (Statement) con.createStatement();
		String sql = "SELECT * from TB_PIC_INFO";
		ResultSet rs = ps.executeQuery(sql);
		int i =0;
		while (rs.next()) {
			// 循环输出结果集
			String imgpath = rs.getString("PIC_PATH");
			File picture = new File("E:"+"\\"+imgpath);
			String targetFile = "E:\\thumbnail\\"+imgpath;
			BufferedImage sourceImg;
			try {
				sourceImg = ResizeImage.resizeImageByHeight(ImageIO.read(new FileInputStream(picture)), 200);
				ResizeImage.SaveImg(sourceImg, targetFile);
				i+=1;
				System.out.println("第"+i+"张，"+imgpath+"完成");
			} catch (FileNotFoundException e) {
				System.err.println("F:"+"\\"+imgpath+"不存在");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
