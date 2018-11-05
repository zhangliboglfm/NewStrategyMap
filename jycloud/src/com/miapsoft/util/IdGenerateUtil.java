/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-8
*/
package com.miapsoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerateUtil {

	private static String PGPrefix="P";//规则：P+下划线（1位）+性别（1位）+国家标识（4位）+编号（4位）示例：P_M00860001
	private static String WPrefix="W";//规则：W+下划线（1位）+摄影家标识（11位）+编号（7位）示例：W_P_M008600010000001
	private static String ArtPrefix="A";//规则：A+下划线（1位）+文章类型（2位）+编号（8位）示例：A_CI00000001
	private static String LablePrefix="L";//规则：L+下划线（1位）+标签类型（1位）+编号（7位）示例：L_P0000001
	private static String CGWorkfix="W";//规则：W+下划线（1位）+书法家编号（14位）+编号（6位）示例：W_CG_M0010020001000001
	
	public static String getPhotographerId(String SexCode, String NationCode, int Number){
		String IDSTR = PGPrefix+"_"+SexCode+NationCode;
		String numstr = "";
		switch (String.valueOf(Number).length()) {
		case 1:
			numstr = "000"+String.valueOf(Number);
			break;
		case 2:
			numstr = "00"+String.valueOf(Number);
			break;
		case 3:
			numstr = "0"+String.valueOf(Number);
			break;
		case 4:
			numstr = String.valueOf(Number);
			break;
		default:
			numstr = "";
			break;
		}
		return IDSTR+numstr;
	}

	public static String getWorksId(String pgId, int Number){
		String IDSTR = WPrefix+"_"+pgId;
		String numstr = "";
		switch (String.valueOf(Number).length()) {
		case 1:
			numstr = "000000"+String.valueOf(Number);
			break;
		case 2:
			numstr = "00000"+String.valueOf(Number);
			break;
		case 3:
			numstr = "0000"+String.valueOf(Number);
			break;
		case 4:
			numstr = "000"+String.valueOf(Number);
			break;
		case 5:
			numstr = "00"+String.valueOf(Number);
			break;
		case 6:
			numstr = "0"+String.valueOf(Number);
			break;
		case 7:
			numstr = String.valueOf(Number);
			break;
		default:
			numstr = "";
			break;
		}
		return IDSTR+numstr;
	}
	
	public static String getArticleId(String artType, int Number){
		String IDSTR = ArtPrefix+"_"+artType;
		String numstr = "";
		switch (String.valueOf(Number).length()) {
		case 1:
			numstr = "0000000"+String.valueOf(Number);
			break;
		case 2:
			numstr = "000000"+String.valueOf(Number);
			break;
		case 3:
			numstr = "00000"+String.valueOf(Number);
			break;
		case 4:
			numstr = "0000"+String.valueOf(Number);
			break;
		case 5:
			numstr = "000"+String.valueOf(Number);
			break;
		case 6:
			numstr = "00"+String.valueOf(Number);
			break;
		case 7:
			numstr = "0"+String.valueOf(Number);
			break;
		case 8:
			numstr = String.valueOf(Number);
			break;
		default:
			numstr = "";
			break;
		}
		return IDSTR+numstr;
	}
	public static String getCGWorksId(String pgId, int Number){
		String IDSTR = CGWorkfix+"_"+pgId;
		String numstr = "";
		switch (String.valueOf(Number).length()) {
		case 1:
			numstr = "00000"+String.valueOf(Number);
			break;
		case 2:
			numstr = "0000"+String.valueOf(Number);
			break;
		case 3:
			numstr = "000"+String.valueOf(Number);
			break;
		case 4:
			numstr = "00"+String.valueOf(Number);
			break;
		case 5:
			numstr = "0"+String.valueOf(Number);
			break;
		case 6:
			numstr = String.valueOf(Number);
			break;
		default:
			numstr = "";
			break;
		}
		return IDSTR+numstr;
	}	
	public static String getLableId(String labelType, int Number){
		String IDSTR = LablePrefix+"_"+labelType;
		String numstr = "";
		switch (String.valueOf(Number).length()) {
		case 1:
			numstr = "000000"+String.valueOf(Number);
			break;
		case 2:
			numstr = "00000"+String.valueOf(Number);
			break;
		case 3:
			numstr = "0000"+String.valueOf(Number);
			break;
		case 4:
			numstr = "000"+String.valueOf(Number);
			break;
		case 5:
			numstr = "00"+String.valueOf(Number);
			break;
		case 6:
			numstr = "0"+String.valueOf(Number);
			break;
		case 7:
			numstr = String.valueOf(Number);
			break;
		default:
			numstr = "";
			break;
		}
		return IDSTR+numstr;
	}
	/*生成书法家id*/
	public static String getCgId(int show_order,String sex,String nationId,String dynastyId){
		int count=0;
		String cgid="";
		if(show_order!=0){
			show_order=show_order+2;
			if(show_order<=10){
				count=show_order/10+1;
			}else if(show_order<=100&&show_order>10){
				count=show_order/100+2;
			}else if(show_order<=1000&&show_order>100){
				count=show_order/1000+3;
			}else if(show_order<10000&&show_order>1000){
				count=show_order/10000+4;
			}
			
		}else{
			show_order=0;
			count=0;
		}
		/*导入书法家*/
		
		String flag="";
		if(count!=4){
			for(int i=0;i<4-count;i++){
				flag=flag+"0";
			}
		}
		flag=flag+(show_order);
		cgid="CG_"+sex+nationId+dynastyId+flag;
		return cgid;
		
	}
	
	/*生成成就id*/
	public static String getAchievementId(int show_order,String curdate){
		int count=0;
		String agid="";
		if(show_order!=0){
			show_order=show_order+2;
			if(show_order<=10){
				count=show_order/10+1;
			}else if(show_order<=100&&show_order>10){
				count=show_order/100+2;
			}else if(show_order<1000&&show_order>100){
				count=show_order/1000+3;
			}
		}else{
			show_order=0;
			count=0;
		}
		/*导入书法家*/
		
		String flag="";
		if(count!=3){
			for(int i=0;i<3-count;i++){
				flag=flag+"0";
			}
		}
		flag=flag+(show_order);
		agid="AG_"+curdate+flag;
		return agid;
		
	}
	
	/*生成合集id*/
	public static String getAggregationId(int show_order,String agid){
		int count=0;
		String rgid="";
		if(show_order!=0){
			show_order=show_order+2;
			if(show_order<=10){
				count=show_order/10+1;
			}else if(show_order<=100&&show_order>10){
				count=show_order/100+2;
			}else if(show_order<=1000&&show_order>100){
				count=show_order/1000+3;
			}
			
		}else{
			show_order=0;
			count=0;
		}
		/*导入书法家*/
		
		String flag="";
		if(count!=3){
			for(int i=0;i<3-count;i++){
				flag=flag+"0";
			}
		}
		flag=flag+(show_order);
		rgid="CPG_"+agid+flag;
		return rgid;
		
	}
}
