/*** <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: 精益有容（北京）科技有限公司</p> 
 * @author 白少华
 * @date 2018-7-30
 */
package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.miapsoft.controller.ServerFilePath;

public class FileAnalysisUtil {

	private static String photogdir = ServerFilePath.getPhotogdir();//摄影家，头像，作品目录
	private static String coreintrodir = ServerFilePath.getCoreintrodir();//摄影家核心介绍Word、图片、H5、封面
	private static String worksintrodir = ServerFilePath.getWorksintrodir();//摄影家作品解读Word、图片、H5、封面
	private static String articledir = ServerFilePath.getArticledir();//专题文章Word、图片、H5、封面


	public static JSONArray PFileDataEntry(PrintWriter printWriter,String filepath) throws Exception{
		JSONArray pgdata = new JSONArray();
		File listfile = new File(filepath);//获取list文件
		//读取文件
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
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
		//获取文件数据
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			JSONObject obj = new JSONObject();
			Cell cell1 = row.getCell(0);//根据模板协议，获取摄影家姓名数据
			Cell cell2 = row.getCell(1);//根据模板协议，获取摄影家性别数据
			Cell cell3 = row.getCell(2);//根据模板协议，获取摄影家年代数据
			Cell cell4 = row.getCell(3);//根据模板协议，获取出生日期数据
			Cell cell5 = row.getCell(4);//根据模板协议，获取去世日期数据
			Cell cell6 = row.getCell(5);//根据模板协议，获取国籍数据
			Cell cell7 = row.getCell(6);//根据模板协议，获取显示顺序数据
			Cell cell8 = row.getCell(7);//根据模板协议，获取标签数据
			obj.put("pName", POIUtil.getCellValue(cell1));
			obj.put("pSex", POIUtil.getCellValue(cell2));
			obj.put("pYear", POIUtil.getCellValue(cell3));
			obj.put("pBirth", POIUtil.getCellValue(cell4));
			obj.put("pDeath", POIUtil.getCellValue(cell5));
			obj.put("pNation", POIUtil.getCellValue(cell6));
			obj.put("pShowIndex", POIUtil.getCellValue(cell7)); 
			obj.put("pLabel", POIUtil.getCellValue(cell8)); 
			pgdata.add(obj);
		}
		return pgdata;
	}


	public static JSONArray PFileDataEntry_Old(PrintWriter printWriter,String filepath) throws Exception{

		JSONArray wbdata = new JSONArray();
		JSONObject obj_p = new JSONObject();
		JSONArray arr_head = new JSONArray();
		JSONArray arr_works = new JSONArray();
		JSONObject obj_coreintro = new JSONObject();
		JSONArray arr_worksintro = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件

		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				throw new Exception("缺少list文件！");
			}
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
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

		Sheet sheet1 = wb.getSheetAt(0);
		Sheet sheet2 = wb.getSheetAt(1);
		Sheet sheet3 = wb.getSheetAt(2);
		Sheet sheet4 = wb.getSheetAt(3);
		Sheet sheet5 = wb.getSheetAt(4);
		Sheet sheet6 = wb.getSheetAt(5);

		//解析第一个sheet
		Row sheet1_row = sheet1.getRow(1);
		Cell sheet1_cell1 = sheet1_row.getCell(0);//根据模板协议，获取摄影家ID
		Cell sheet1_cell2 = sheet1_row.getCell(1);//根据模板协议，获取摄影家姓名
		Cell sheet1_cell3 = sheet1_row.getCell(2);//根据模板协议，获取摄影家性别
		Cell sheet1_cell4 = sheet1_row.getCell(3);//根据模板协议，获取摄影家出生日期
		Cell sheet1_cell5 = sheet1_row.getCell(4);//根据模板协议，获取摄影家去世日期
		Cell sheet1_cell6 = sheet1_row.getCell(5);//根据模板协议，获取摄影家国籍
		Cell sheet1_cell7 = sheet1_row.getCell(6);//根据模板协议，获取摄影家核心介绍ID
		Cell sheet1_cell8 = sheet1_row.getCell(7);//根据模板协议，获取显示顺序

		try {
			obj_p.put("pId", POIUtil.getCellValue(sheet1_cell1));
			obj_p.put("pName", POIUtil.getCellValue(sheet1_cell2));
			obj_p.put("pSex", POIUtil.getCellValue(sheet1_cell3));
			obj_p.put("pBirth", POIUtil.getCellValue(sheet1_cell4));
			obj_p.put("pDeath", POIUtil.getCellValue(sheet1_cell5));
			obj_p.put("pNation", POIUtil.getCellValue(sheet1_cell6));
			obj_p.put("pIntroduce", POIUtil.getCellValue(sheet1_cell7));
			obj_p.put("pIndex", POIUtil.getCellValue(sheet1_cell8));
		} catch (Exception e) {
			PrintUtil.printLog(printWriter, "解析异常，请检查文件数据是否存在格式错误，或数据异常");
			e.printStackTrace();
		}
		wbdata.add(obj_p);

		//解析第二个sheet
		int sheet2_rowNum;
		sheet2_rowNum = sheet2.getLastRowNum();
		for (int i = 1; i <= sheet2_rowNum; i++) {
			JSONObject obj_head = new JSONObject();
			Row rw = sheet2.getRow(i);
			Cell cell1 = rw.getCell(0);
			Cell cell2 = rw.getCell(1);
			Cell cell3 = rw.getCell(2);
			Cell cell4 = rw.getCell(3);
			Cell cell5 = rw.getCell(4);

			File tmpfile = new File(filepath+File.separator+"headimg"+File.separator+POIUtil.getCellValue(cell3));//临时文件
			String tmpfilesuffix  = FileTransferUtil.getFileSuffix(tmpfile);//文件后缀
			String newfilename = EncryptionUtil.ENCRYPTSTRING("H"+sdf.format(new Date()))+"."+tmpfilesuffix;//重命名
			String relativePath = POIUtil.getCellValue(cell1)+"/"+"head"+"/"+newfilename;//相对路径
			String serverPath = photogdir+"/"+relativePath;//服务器上传路径
			try {
				FileTransferUtil.FileTransferFile(serverPath, tmpfile);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}
			try {
				obj_head.put("pId", POIUtil.getCellValue(cell1));
				obj_head.put("imgType", POIUtil.getCellValue(cell2));
				obj_head.put("fileName", relativePath);
				obj_head.put("showFlag", POIUtil.getCellValue(cell4));
				obj_head.put("showIndex", POIUtil.getCellValue(cell5));
				arr_head.add(obj_head);
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		wbdata.add(arr_head);

		//解析第三个sheet
		int sheet3_rowNum;
		sheet3_rowNum = sheet3.getLastRowNum();
		for (int i = 1; i <= sheet3_rowNum; i++) {
			JSONObject obj_works = new JSONObject();
			Row rw = sheet3.getRow(i);
			Cell cell1 = rw.getCell(0);
			Cell cell2 = rw.getCell(1);
			Cell cell3 = rw.getCell(2);
			Cell cell4 = rw.getCell(3);
			Cell cell5 = rw.getCell(4);
			Cell cell6 = rw.getCell(5);
			Cell cell7 = rw.getCell(6);
			Cell cell8 = rw.getCell(7);
			Cell cell9 = rw.getCell(8);
			Cell cell10 = rw.getCell(9);

			File tmpfile = new File(filepath+File.separator+"worksimg"+File.separator+POIUtil.getCellValue(cell7));//临时文件
			String tmpfilesuffix  = FileTransferUtil.getFileSuffix(tmpfile);//文件后缀
			String newfilename = EncryptionUtil.ENCRYPTSTRING("W"+sdf.format(new Date()))+"."+tmpfilesuffix;//重命名
			String relativePath = POIUtil.getCellValue(cell8)+"/"+"works"+"/"+newfilename;//相对路径
			String serverPath = photogdir+"/"+relativePath;//服务器上传路径
			try {
				FileTransferUtil.FileTransferFile(serverPath, tmpfile);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}

			try {
				obj_works.put("worksId", POIUtil.getCellValue(cell1));
				obj_works.put("worksName", POIUtil.getCellValue(cell2));
				obj_works.put("worksType", POIUtil.getCellValue(cell3));
				obj_works.put("shootDate", POIUtil.getCellValue(cell4));
				obj_works.put("worksIntro", POIUtil.getCellValue(cell5));
				obj_works.put("shootProc", POIUtil.getCellValue(cell6));
				obj_works.put("fileName", relativePath);
				obj_works.put("pId", POIUtil.getCellValue(cell8));
				obj_works.put("isRepre", POIUtil.getCellValue(cell9));
				obj_works.put("showIndex", POIUtil.getCellValue(cell10));
				arr_works.add(obj_works);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		wbdata.add(arr_works);

		//解析第四个sheet
		Row sheet4_row = sheet4.getRow(1);
		Cell sheet4_cell1 = sheet4_row.getCell(0);//根据模板协议，获取文章ID
		Cell sheet4_cell2 = sheet4_row.getCell(1);//根据模板协议，获取文章类型
		Cell sheet4_cell3 = sheet4_row.getCell(2);//根据模板协议，获取文章封面
		Cell sheet4_cell4 = sheet4_row.getCell(3);//根据模板协议，获取文章标题
		Cell sheet4_cell5 = sheet4_row.getCell(4);//根据模板协议，获取文章内容
		Cell sheet4_cell6 = sheet4_row.getCell(5);//根据模板协议，获取文章文档
		Cell sheet4_cell7 = sheet4_row.getCell(6);//根据模板协议，获取文章图片
		Cell sheet4_cell8 = sheet4_row.getCell(7);//根据模板协议，获取文章链接

		File tmpcoverfile = new File(filepath+File.separator+"coreintro"+File.separator+POIUtil.getCellValue(sheet4_cell3));//临时封面文件
		File tmpwordfile = new File(filepath+File.separator+"coreintro"+File.separator+POIUtil.getCellValue(sheet4_cell6));//临时文档文件
		File tmpH5file = new File(filepath+File.separator+"coreintro"+File.separator+POIUtil.getCellValue(sheet4_cell7));//临时H5文件

		String tmpfilecoversuffix  = FileTransferUtil.getFileSuffix(tmpcoverfile);//封面文件后缀
		String tmpfilewordsuffix  = FileTransferUtil.getFileSuffix(tmpwordfile);//文档文件后缀
		String tmpfileH5suffix  = FileTransferUtil.getFileSuffix(tmpH5file);//H5文件后缀

		String newcoverfilename = EncryptionUtil.ENCRYPTSTRING("CC"+sdf.format(new Date()))+"."+tmpfilecoversuffix;//封面文件重命名
		String newwordfilename = EncryptionUtil.ENCRYPTSTRING("CW"+sdf.format(new Date()))+"."+tmpfilewordsuffix;//文档文件重命名
		String newH5filename = EncryptionUtil.ENCRYPTSTRING("CH"+sdf.format(new Date()))+"."+tmpfileH5suffix;//H5文件重命名

		String coverrelativePath = POIUtil.getCellValue(sheet1_cell1)+"/"+newcoverfilename;//封面文件相对路径
		String wordrelativePath = POIUtil.getCellValue(sheet1_cell1)+"/"+newwordfilename;//文档文件相对路径
		String H5relativePath = POIUtil.getCellValue(sheet1_cell1)+"/"+newH5filename;//H5文件相对路径

		try {
			FileTransferUtil.FileTransferFile(coreintrodir+"/"+coverrelativePath, tmpcoverfile);//拷贝至服务器路径
			FileTransferUtil.FileTransferFile(coreintrodir+"/"+wordrelativePath, tmpwordfile);//拷贝至服务器路径
			FileTransferUtil.FileTransferFile(coreintrodir+"/"+H5relativePath, tmpH5file);//拷贝至服务器路径
		} catch (Exception e) {
			PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
			e.printStackTrace();
		}


		try {
			obj_coreintro.put("aId", POIUtil.getCellValue(sheet4_cell1));
			obj_coreintro.put("aType", POIUtil.getCellValue(sheet4_cell2));
			obj_coreintro.put("aCover", coverrelativePath);
			obj_coreintro.put("aTitle", POIUtil.getCellValue(sheet4_cell4));
			obj_coreintro.put("aContent", POIUtil.getCellValue(sheet4_cell5));
			obj_coreintro.put("aDoc", wordrelativePath);
			obj_coreintro.put("aPic", H5relativePath);
			obj_coreintro.put("aUrl", POIUtil.getCellValue(sheet4_cell8));
		} catch (Exception e) {
			PrintUtil.printLog(printWriter, "解析异常，请检查文件数据是否存在格式错误，或数据异常");
			e.printStackTrace();
		}
		wbdata.add(obj_coreintro);

		//解析第五个sheet
		int sheet5_rowNum;
		sheet5_rowNum = sheet5.getLastRowNum();
		for (int i = 1; i <= sheet5_rowNum; i++) {
			JSONObject obj_worksintro = new JSONObject();
			Row rw = sheet5.getRow(i);
			Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
			Cell cell2 = rw.getCell(1);//根据模板协议，获取文章类型
			Cell cell3 = rw.getCell(2);//根据模板协议，获取文章封面
			Cell cell4 = rw.getCell(3);//根据模板协议，获取文章标题
			Cell cell5 = rw.getCell(4);//根据模板协议，获取文章内容
			Cell cell6 = rw.getCell(5);//根据模板协议，获取文章文档
			Cell cell7 = rw.getCell(6);//根据模板协议，获取文章图片
			Cell cell8 = rw.getCell(7);//根据模板协议，获取文章链接

			File tmpcoverfile_wi = new File(filepath+File.separator+"worksintro"+File.separator+POIUtil.getCellValue(cell3));//临时封面文件
			File tmpwordfile_wi = new File(filepath+File.separator+"worksintro"+File.separator+POIUtil.getCellValue(cell6));//临时文档文件
			File tmpH5file_wi = new File(filepath+File.separator+"worksintro"+File.separator+POIUtil.getCellValue(cell7));//临时H5文件

			String tmpfilecoversuffix_wi  = FileTransferUtil.getFileSuffix(tmpcoverfile_wi);//封面文件后缀
			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀
			String tmpfileH5suffix_wi  = FileTransferUtil.getFileSuffix(tmpH5file_wi);//H5文件后缀

			String newcoverfilename_wi = EncryptionUtil.ENCRYPTSTRING("WIC"+sdf.format(new Date()))+"."+tmpfilecoversuffix_wi;//封面文件重命名
			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("WIW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名
			String newH5filename_wi = EncryptionUtil.ENCRYPTSTRING("WIH"+sdf.format(new Date()))+"."+tmpfileH5suffix_wi;//H5文件重命名

			String coverrelativePath_wi = POIUtil.getCellValue(sheet1_cell1)+"/"+newcoverfilename_wi;//封面文件相对路径
			String wordrelativePath_wi = POIUtil.getCellValue(sheet1_cell1)+"/"+newwordfilename_wi;//文档文件相对路径
			String H5relativePath_wi = POIUtil.getCellValue(sheet1_cell1)+"/"+newH5filename_wi;//H5文件相对路径

			try {
				FileTransferUtil.FileTransferFile(worksintrodir+"/"+coverrelativePath_wi, tmpcoverfile_wi);//拷贝至服务器路径
				FileTransferUtil.FileTransferFile(worksintrodir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
				FileTransferUtil.FileTransferFile(worksintrodir+"/"+H5relativePath_wi, tmpH5file_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}

			try {
				obj_worksintro.put("aId", POIUtil.getCellValue(cell1));
				obj_worksintro.put("aType", POIUtil.getCellValue(cell2));
				obj_worksintro.put("aCover", coverrelativePath_wi);
				obj_worksintro.put("aTitle", POIUtil.getCellValue(cell4));
				obj_worksintro.put("aContent", POIUtil.getCellValue(cell5));
				obj_worksintro.put("aDoc", wordrelativePath_wi);
				obj_worksintro.put("aPic", H5relativePath_wi);
				obj_worksintro.put("aUrl", POIUtil.getCellValue(cell8));
				arr_worksintro.add(obj_worksintro);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		wbdata.add(arr_worksintro);

		//解析第五个sheet
		Row sheet6_row = sheet6.getRow(1);
		if(sheet6_row!=null){
			JSONArray data = new JSONArray();
			int sheet6_rowNum;
			sheet6_rowNum = sheet6.getLastRowNum();
			for (int i = 1; i < sheet6_rowNum; i++) {
				JSONObject obj = new JSONObject();
				Row rw = sheet6.getRow(i);
				Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
				Cell cell2 = rw.getCell(1);//根据模板协议，获取作品ID
				Cell cell3 = rw.getCell(2);//根据模板协议，获取作品顺序
				try {
					obj.put("aId", POIUtil.getCellValue(cell1));
					obj.put("wId", POIUtil.getCellValue(cell2));
					obj.put("showIndex", POIUtil.getCellValue(cell3));
					data.add(obj);
				} catch (Exception e) {
					printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
					e.printStackTrace();
				}
			}
			wbdata.add(data);
		}
		System.out.println(wbdata);
		return wbdata;
	}


	public static JSONArray WFileDataEntry(PrintWriter printWriter,String filepath, String pgId) throws Exception{
		JSONArray wbdata = new JSONArray();
		JSONArray arr_works = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件

		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				throw new Exception("缺少list文件！");
			}
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
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

		Sheet sheet3 = wb.getSheetAt(0);

		//解析第三个sheet
		int sheet3_rowNum;
		sheet3_rowNum = sheet3.getLastRowNum();
		for (int i = 1; i <= sheet3_rowNum; i++) {
			JSONObject obj_works = new JSONObject();
			Row rw = sheet3.getRow(i);
			Cell cell1 = rw.getCell(0);//作品名称
			Cell cell2 = rw.getCell(1);//作品类型
			Cell cell3 = rw.getCell(2);//拍摄日期
			Cell cell4 = rw.getCell(3);//作品解读
			Cell cell5 = rw.getCell(4);//拍摄过程
			Cell cell6 = rw.getCell(5);//文件名
			Cell cell7 = rw.getCell(6);//是否代表作
			Cell cell8 = rw.getCell(7);//显示顺序
			Cell cell9 = rw.getCell(8);//标签
			if (cell1==null&&cell2==null&&cell3==null&&cell4==null&&cell5==null&&cell6==null&&cell7==null&&cell8==null) {
				break;
			}
			File tmpfile = new File(filepath+File.separator+"worksimg"+File.separator+POIUtil.getCellValue(cell6));//临时文件
			String tmpfilesuffix  = FileTransferUtil.getFileSuffix(tmpfile);//文件后缀
			String newfilename = EncryptionUtil.ENCRYPTSTRING("W"+sdf.format(new Date()))+"."+tmpfilesuffix;//重命名
			String relativePath = pgId+"/"+"works"+"/"+newfilename;//相对路径
			//String relativePath = newfilename;//相对路径
			String serverPath = photogdir+"/"+relativePath;//服务器上传路径
			try {
				FileTransferUtil.FileTransferFile(serverPath, tmpfile);//拷贝至服务器路径
				//obj_works.put("worksId", POIUtil.getCellValue(cell1));
				obj_works.put("worksName", POIUtil.getCellValue(cell1));
				obj_works.put("worksType", POIUtil.getCellValue(cell2));
				obj_works.put("shootDate", POIUtil.getCellValue(cell3));
				obj_works.put("worksIntro", POIUtil.getCellValue(cell4));
				obj_works.put("shootProc", POIUtil.getCellValue(cell5));
				obj_works.put("fileName", relativePath);
				//obj_works.put("pId", POIUtil.getCellValue(cell8));
				obj_works.put("isRepre", POIUtil.getCellValue(cell7));
				obj_works.put("showIndex", POIUtil.getCellValue(cell8));
				obj_works.put("labels", POIUtil.getCellValue(cell9));
				arr_works.add(obj_works);
			} catch (Exception e) {
				PrintUtil.printLog(printWriter,POIUtil.getCellValue(cell1)+ "文件拷贝至服务器时出错");
				e.printStackTrace();
			}

			/*try {
				//obj_works.put("worksId", POIUtil.getCellValue(cell1));
				obj_works.put("worksName", POIUtil.getCellValue(cell1));
				obj_works.put("worksType", POIUtil.getCellValue(cell2));
				obj_works.put("shootDate", POIUtil.getCellValue(cell3));
				obj_works.put("worksIntro", POIUtil.getCellValue(cell4));
				obj_works.put("shootProc", POIUtil.getCellValue(cell5));
				obj_works.put("fileName", relativePath);
				//obj_works.put("pId", POIUtil.getCellValue(cell8));
				obj_works.put("isRepre", POIUtil.getCellValue(cell7));
				obj_works.put("showIndex", POIUtil.getCellValue(cell8));
				obj_works.put("labels", POIUtil.getCellValue(cell9));
				arr_works.add(obj_works);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}*/

		}
		wbdata.add(arr_works);

		System.out.println(wbdata);
		return wbdata;
	}



	public static JSONArray AFileDataEntry(PrintWriter printWriter,String filepath) throws Exception{
		JSONArray wbdata = new JSONArray();
		JSONArray arr_ainfo = new JSONArray();
		JSONArray arr_apinfo = new JSONArray();
		JSONArray arr_awinfo = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");//用于重命名文件

		File listfile = new File(filepath+File.separator+"list.xlsx");//获取list文件
		if(!listfile.exists()){
			listfile = new File(filepath+"list.xls");
			if(!listfile.exists()){
				PrintUtil.printLog(printWriter, "list文件不存在，请检查上传文件");
				throw new Exception("缺少list文件！");
			}
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
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

		Sheet sheet1 = wb.getSheetAt(0);
		Sheet sheet2 = wb.getSheetAt(1);
		Sheet sheet3 = wb.getSheetAt(2);

		//解析第一个sheet
		int sheet1_rowNum;
		sheet1_rowNum = sheet1.getLastRowNum();
		for (int i = 1; i <= sheet1_rowNum; i++) {
			JSONObject obj = new JSONObject();
			Row rw = sheet1.getRow(i);
			Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
			Cell cell2 = rw.getCell(1);//根据模板协议，获取文章类型
			Cell cell3 = rw.getCell(2);//根据模板协议，获取文章封面
			Cell cell4 = rw.getCell(3);//根据模板协议，获取文章标题
			Cell cell5 = rw.getCell(4);//根据模板协议，获取文章内容
			Cell cell6 = rw.getCell(5);//根据模板协议，获取文章文档
			Cell cell7 = rw.getCell(6);//根据模板协议，获取文章图片
			Cell cell8 = rw.getCell(7);//根据模板协议，获取文章链接

			File tmpcoverfile_wi = new File(filepath+File.separator+"coverimg"+File.separator+POIUtil.getCellValue(cell3));//临时封面文件
			File tmpwordfile_wi = new File(filepath+File.separator+"word"+File.separator+POIUtil.getCellValue(cell6));//临时文档文件
			File tmpH5file_wi = new File(filepath+File.separator+"h5img"+File.separator+POIUtil.getCellValue(cell7));//临时H5文件

			String tmpfilecoversuffix_wi  = FileTransferUtil.getFileSuffix(tmpcoverfile_wi);//封面文件后缀
			String tmpfilewordsuffix_wi  = FileTransferUtil.getFileSuffix(tmpwordfile_wi);//文档文件后缀
			String tmpfileH5suffix_wi  = FileTransferUtil.getFileSuffix(tmpH5file_wi);//H5文件后缀

			String newcoverfilename_wi = EncryptionUtil.ENCRYPTSTRING("AC"+sdf.format(new Date()))+"."+tmpfilecoversuffix_wi;//封面文件重命名
			String newwordfilename_wi = EncryptionUtil.ENCRYPTSTRING("AW"+sdf.format(new Date()))+"."+tmpfilewordsuffix_wi;//文档文件重命名
			String newH5filename_wi = EncryptionUtil.ENCRYPTSTRING("AH"+sdf.format(new Date()))+"."+tmpfileH5suffix_wi;//H5文件重命名

			String coverrelativePath_wi = POIUtil.getCellValue(cell1)+"/"+newcoverfilename_wi;//封面文件相对路径
			String wordrelativePath_wi = POIUtil.getCellValue(cell1)+"/"+newwordfilename_wi;//文档文件相对路径
			String H5relativePath_wi = POIUtil.getCellValue(cell1)+"/"+newH5filename_wi;//H5文件相对路径

			try {
				FileTransferUtil.FileTransferFile(articledir+"/"+coverrelativePath_wi, tmpcoverfile_wi);//拷贝至服务器路径
				FileTransferUtil.FileTransferFile(articledir+"/"+wordrelativePath_wi, tmpwordfile_wi);//拷贝至服务器路径
				FileTransferUtil.FileTransferFile(articledir+"/"+H5relativePath_wi, tmpH5file_wi);//拷贝至服务器路径
			} catch (Exception e) {
				PrintUtil.printLog(printWriter, "文件拷贝至服务器时出错");
				e.printStackTrace();
			}

			try {
				obj.put("aId", POIUtil.getCellValue(cell1));
				obj.put("aType", POIUtil.getCellValue(cell2));
				obj.put("aCover", coverrelativePath_wi);
				obj.put("aTitle", POIUtil.getCellValue(cell4));
				obj.put("aContent", POIUtil.getCellValue(cell5));
				obj.put("aDoc", wordrelativePath_wi);
				obj.put("aPic", H5relativePath_wi);
				obj.put("aUrl", POIUtil.getCellValue(cell8));
				arr_ainfo.add(obj);
			} catch (Exception e) {
				printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
				e.printStackTrace();
			}

		}
		wbdata.add(arr_ainfo);

		//解析第二个sheet
		Row sheet2_row = sheet2.getRow(1);
		if(sheet2_row!=null){
			int sheet2_rowNum;
			sheet2_rowNum = sheet2.getLastRowNum();
			for (int i = 1; i < sheet2_rowNum; i++) {
				JSONObject obj = new JSONObject();
				Row rw = sheet2.getRow(i);
				Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
				Cell cell2 = rw.getCell(1);//根据模板协议，获取摄影家
				Cell cell3 = rw.getCell(2);//根据模板协议，获取摄影家顺序
				try {
					obj.put("aId", POIUtil.getCellValue(cell1));
					obj.put("pId", POIUtil.getCellValue(cell2));
					obj.put("showIndex", POIUtil.getCellValue(cell3));
					arr_apinfo.add(obj);
				} catch (Exception e) {
					printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
					e.printStackTrace();
				}
			}
		}
		wbdata.add(arr_apinfo);

		//解析第三个sheet
		Row sheet3_row = sheet3.getRow(1);
		if(sheet3_row!=null){
			int sheet3_rowNum;
			sheet3_rowNum = sheet3.getLastRowNum();
			for (int i = 1; i < sheet3_rowNum; i++) {
				JSONObject obj = new JSONObject();
				Row rw = sheet3.getRow(i);
				Cell cell1 = rw.getCell(0);//根据模板协议，获取文章ID
				Cell cell2 = rw.getCell(1);//根据模板协议，获取作品
				Cell cell3 = rw.getCell(2);//根据模板协议，获取作品顺序
				try {
					obj.put("aId", POIUtil.getCellValue(cell1));
					obj.put("wId", POIUtil.getCellValue(cell2));
					obj.put("showIndex", POIUtil.getCellValue(cell3));
					arr_awinfo.add(obj);
				} catch (Exception e) {
					printWriter.println("解析异常，请检查文件数据是否存在格式错误，或数据异常");
					e.printStackTrace();
				}
			}
		}
		wbdata.add(arr_awinfo);
		System.out.println(wbdata);
		return wbdata;

	}
	//书法家信息上传
	public static JSONArray CgInfoEntry(PrintWriter printWriter,String filepath) throws Exception{
		JSONArray pgdata = new JSONArray();
		File listfile = new File(filepath);//获取list文件
		//读取文件
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(listfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb = null;
		String exceltype = listfile.getName().substring(listfile.getName().lastIndexOf(".") + 1);
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
		//获取文件数据
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			JSONObject obj = new JSONObject();
			Cell cell1 = row.getCell(0);//根据模板协议，获取摄影家姓名数据
			Cell cell2 = row.getCell(1);//根据模板协议，获取摄影家性别数据
			Cell cell3 = row.getCell(2);//根据模板协议，获取摄影家年代数据
			Cell cell4 = row.getCell(3);//根据模板协议，获取出生日期数据
			Cell cell5 = row.getCell(4);//根据模板协议，获取去世日期数据
			Cell cell6 = row.getCell(5);//根据模板协议，获取国籍数据
			Cell cell7 = row.getCell(6);//根据模板协议，获取显示顺序数据
			Cell cell8 = row.getCell(7);//根据模板协议，获取标签数据
			Cell cell9 = row.getCell(8);//根据模板协议，获取标签数据
			Cell cell10 = row.getCell(9);//根据模板协议，获取标签数据
			Cell cell11 = row.getCell(10);//根据模板协议，获取标签数据
			Cell cell12 = row.getCell(11);//根据模板协议，获取标签数据
			Cell cell13 = row.getCell(12);//根据模板协议，获取标签数据
			Cell cell14 = row.getCell(13);//根据模板协议，获取标签数据
			obj.put("name", POIUtil.getCellValue(cell1));
			obj.put("sex", POIUtil.getCellValue(cell2));
			obj.put("nation", POIUtil.getCellValue(cell3));
			obj.put("dynasty", POIUtil.getCellValue(cell4));
			obj.put("birthday", POIUtil.getCellValue(cell5));
			obj.put("deathday", POIUtil.getCellValue(cell6));
			obj.put("word", POIUtil.getCellValue(cell7)); 
			obj.put("number", POIUtil.getCellValue(cell8)); 
			obj.put("ancestralhome", POIUtil.getCellValue(cell9)); 
			obj.put("birtharea", POIUtil.getCellValue(cell10)); 
			obj.put("othername", POIUtil.getCellValue(cell11)); 
			obj.put("status", POIUtil.getCellValue(cell12)); 
			obj.put("importantworks", POIUtil.getCellValue(cell13)); 
			obj.put("importantdeeds", POIUtil.getCellValue(cell14)); 
			pgdata.add(obj);
		}
		return pgdata;
	}

}
