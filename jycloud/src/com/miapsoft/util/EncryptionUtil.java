/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-1
*/
package com.miapsoft.util;

public class EncryptionUtil {
	public static String ENCRYPTSTRING(String str){
		String result = str;
		result=MD5Util.newMD5(result);
		result=DesUtil.encrypt(result, result);
		result=AesUtil.encrypt(result, result);
		result=MD5Util.MD5(result);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(ENCRYPTSTRING("1231313113.jpg"));
	}
}
