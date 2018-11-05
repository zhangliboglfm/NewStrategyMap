/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-7-31
*/
package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileTransferUtil {
	/**
	 * @param dirPath 目标文件夹
	 * @param file 待存放文件
	 * @throws Exception
	 */
	public static void FileTransferFile(String dirPath,File file)throws Exception{
		if(file.isDirectory()){
			throw new Exception("文件夹无法拷贝");
		}else{
			File newile = new File(dirPath);
			File dir = newile.getParentFile();
			if(!dir.exists()){
				dir.mkdirs();
			}
			newile.createNewFile();
			FileInputStream in = new FileInputStream(file);
			FileOutputStream out = new FileOutputStream(newile);
			/*byte[] buffer=new byte[2097152];
	        int readByte = 0;
	        while((readByte = in.read(buffer)) != -1){
	            out.write(buffer, 0, readByte);
	        }*/
			String key = EncryptKey.DesKey;
			DesUtil.encryptFile(key, in, out);
	        in.close();
	        out.close();
		}
	}
	
	/**
	 * 检查文件知否存在
	 * @param file
	 * @return
	 */
	public static boolean CheckFileExists(File file){
		boolean result = true;
		if(!file.exists()){
			result = false;
		}
		return result;
	}
	
	/**
	 * 获取文件后缀
	 * @param file
	 * @return
	 */
	public static String getFileSuffix(File file) throws Exception{
		if(file.isDirectory()){
			throw new Exception("文件没有后缀");
		}
		String Suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		return Suffix;
	}
}
