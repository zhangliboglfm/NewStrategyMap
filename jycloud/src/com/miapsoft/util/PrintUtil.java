/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-1
*/
package com.miapsoft.util;

import java.io.PrintWriter;

public class PrintUtil {
	public static void printLog(PrintWriter printWriter,String msg){
		printWriter.print(msg);
		printWriter.print("<br>");
		printWriter.flush();
	}
	public static void printLog2(PrintWriter printWriter,String msg){
		printWriter.print(msg);
		printWriter.flush();
	}
}
