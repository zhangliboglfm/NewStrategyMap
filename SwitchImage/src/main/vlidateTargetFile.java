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
		vlitateTarget("E:\\switchImage\\ͼ����Ϣ.xlsx");
	};
	
	
	public static String vlitateTarget(String filepath){
		File file =new File(filepath);
		// ��ȡExcel�ļ�	
		Map<String,List<List<String>>> map =ReadExcel.ReadCatalog(file);
		String parentPath = file.getParent();
		//System.out.println(parentPath);
		//ѭ������Map
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
						System.out.println("�ļ���"+path+"������");
						strbuff.append("�ļ���"+path+"������    \r\n");//д����־�ļ�������
						j+=1;
					};
				}
				
			}
		};
		strbuff.append("��У��"+k+"���ļ��������쳣�ļ�"+j+"��");
		//����Excel�ļ���֤��־�ļ�
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd~HHmm");
		String nowdate=sdf.format(date);
		String excelLogPath = parentPath+"\\vilateExcelFileLog\\target"+nowdate+".log";
		CreateFile.createFile(excelLogPath, strbuff.toString());
		return excelLogPath;
	}
	
}
