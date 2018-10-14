package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import utils.CreateFile;
import utils.ReadExcel;

public class vlidateSourceFile {
	public static void main(String[] args) {
		vlitateSource("E:\\switchImage\\ͼ����Ϣ.xlsx");
	};
	
	
	public static String vlitateSource(String filepath){
		File file =new File(filepath);
		// ��ȡExcel�ļ�	
		Map<String,List<List<String>>> map =ReadExcel.ReadCatalog(file);
		String parentPath = file.getParent();
		//System.out.println(parentPath);
		//ѭ������Map
		Iterator<Map.Entry<String, List<List<String>>>> it=map.entrySet().iterator();
		StringBuffer strbuff1= new StringBuffer();
		StringBuffer strbuff2 = new StringBuffer();
		int j=0,k=0,l=0;
		while (it.hasNext()) {
			Map.Entry<String, List<List<String>>> enty = it.next();
			String groupName = enty.getKey();
			List<List<String>> list =enty.getValue();
			for (int i = 0; i < list.size(); i++) {
				k+=1;
				String path = parentPath+"\\sources\\"+groupName+"\\"+list.get(i).get(0);
				File file2 = new File(path);
				if(!file2.exists()){
					System.out.println("�ļ���"+path+"������");
					strbuff1.append("�ļ���"+path+"������    \r\n");//д����־�ļ�������
					j+=1;
				}else if(file2.length() !=(Double.parseDouble(list.get(i).get(1)))){
					System.out.println("�ļ���"+path+"��С��ƥ��");
					strbuff2.append("�ļ���"+path+"��С��ƥ��    \r\n");//д����־�ļ�������
					l+=1;
				}
			}
			String targetPath = parentPath+"\\target\\"+groupName;
			File targetfile = new File(targetPath);
			if(!targetfile.exists()){ 
				targetfile.mkdir();
			}
		};
		strbuff1.append(strbuff2);
		strbuff1.append("��У��"+k+"���ļ������в������ļ�"+j+"������С��ƥ���ļ�"+l+"��");
		//����Excel�ļ���֤��־�ļ�
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd~HHmm");
		String nowdate=sdf.format(date);
		String excelLogPath = parentPath+"\\vilateExcelFileLog\\source"+nowdate+".log";
		CreateFile.createFile(excelLogPath, strbuff1.toString());
		return excelLogPath;
	}
}
