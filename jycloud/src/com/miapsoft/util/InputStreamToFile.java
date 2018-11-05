/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-7-27
*/
package com.miapsoft.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamToFile {
	public static void ServletInputStreamToFile(InputStream input,File file) throws IOException{
		FileOutputStream output = null;
		output = new FileOutputStream(file);
        byte[] a = new byte[1024];
        //System.out.println(a.length);
        int len;
        while((len = input.read(a)) != -1){
        	output.write(a, 0, len);
        }
		if (input!=null) {
			input.close();
		}
		if (output!=null) {
			output.close();
		}
	}
}
