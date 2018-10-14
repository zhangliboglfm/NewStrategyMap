package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import utils.CreateFile;
import utils.ReadExcel;

public class vlidateTargetFile {
	public static void main(String[] args) {
		vlitateTarget("E:\\switchImage\\图标信息.xlsx");
	};
	
	
	public static String vlitateTarget(String filepath){
		File file =new File(filepath);
		// 读取Excel文件	
		Map<String,List<List<String>>> map =ReadExcel.ReadCatalog(file);
		String parentPath = file.getParent();
		//System.out.println(parentPath);
		//循环遍历Map
		Iterator<Map.Entry<String, List<List<String>>>> it=map.entrySet().iterator();
		StringBuffer strbuff = new StringBuffer();
		int j=0,k=0;
		while (it.hasNext()) {
			Map.Entry<String, List<List<String>>> enty = it.next();
			String groupName = enty.getKey();
			List<List<String>> list =enty.getValue();
			for (int i = 0; i < list.size(); i++) {
				String sourfile = list.get(i).get(0);
				String jpgpath = sourfile.substring(0,sourfile.indexOf("."));
				for (int l = 0; l < 5; l++) {
					k+=1;
					String path = parentPath+"\\target\\"+groupName+"\\"+jpgpath+"_"+l+".jpg";
					if(!new File(path).exists()){
						System.out.println("文件："+path+"不存在");
						strbuff.append("文件："+path+"不存在    \r\n");//写入日志文件并换行
						j+=1;
					};
				}
				
			}
		};
		strbuff.append("共校验"+k+"个文件，其中异常文件"+j+"个");
		//生成Excel文件验证日志文件
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd~HHmm");
		String nowdate=sdf.format(date);
		String excelLogPath = parentPath+"\\vilateExcelFileLog\\target"+nowdate+".log";
		CreateFile.createFile(excelLogPath, strbuff.toString());
		return excelLogPath;
	}
	
}
