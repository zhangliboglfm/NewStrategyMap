package com.miapsoft.controller;	

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.common.exception.BussinessException;
import com.miapsoft.util.ExcelUtil_ztf;

@Controller
public class MakeExcelController {

	@RequestMapping("makeExcel.do")
	public String makeExcel(HttpServletRequest request, HttpServletResponse response){
		return "pgmag/makeexcel";
	}
	@RequestMapping(value="downExcel.do")
	public void downExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String flag="0";
		//下载设置
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maptemp = new HashMap<String, Object>();
		maptemp.put("sheetName", "作品信息");
		list.add(maptemp);
		String fileNameArr=request.getParameter("filename");
		JSONArray nameArray2 = JSONArray.fromObject(fileNameArr);
		//数据处理
		for ( int i = 0; i < nameArray2.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("workName","");
			map.put("workType","");
			map.put("workTime","");
			map.put("workSintro","");
			map.put("workPros","");
			map.put("fileName",nameArray2.getString(i));
			map.put("isFlag","");
			map.put("showOrder",i+1);
			map.put("workLabel","");
			list.add(map);
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		String columnNames[]={"作品名称","作品类型","拍摄日期","作品解读","拍摄过程","文件名","是否代表作","显示顺序","标签"};//列名
		String keys[]={"workName","workType","workTime","workSintro","workPros","fileName","isFlag","showOrder","workLabel"};//map中的key
		Workbook wb=ExcelUtil_ztf.exportTable2(list, keys, columnNames);
		wb.write(os);  
		byte[] baos = os.toByteArray();
		String serverPath = request.getSession().getServletContext().getRealPath("/")+"list.xlsx";
		File newile = new File(serverPath);
		FileOutputStream out = null;
		ByteArrayInputStream fis = null;
		try {
			out = new FileOutputStream(newile);
			fis=new ByteArrayInputStream(baos);
			byte[] cache = new byte[2048];
			int nRead = 0;
			while ((nRead = fis.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis!=null) {
				fis.close();
			}
			if (out!=null) {
				out.close();
			}
		}
		
		
	}
	
	@RequestMapping(value="downExcel2.do")
	public void downExcel2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String serverPath = request.getSession().getServletContext().getRealPath("/")+"list.xlsx";
		InputStream input = null;
		OutputStream os = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=list.xlsx");
			input = new FileInputStream(new File(serverPath));
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = input.read(b))>0) {
				os.write(b, 0, length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BussinessException("文件不存在！",e);
		} catch (IOException e) {
			throw new BussinessException("系统错误！",e);
		} finally {
			try {
				if(os!=null) os.close();
			} catch (IOException e) {
				throw new BussinessException("系统错误！",e);
			}
			try {
				if(input!=null) input.close();
			} catch (IOException e) {
				throw new BussinessException("系统错误！",e);
			}
		}
	}
}
