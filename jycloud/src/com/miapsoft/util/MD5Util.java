package com.miapsoft.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	// 32位MD5加密
	public static String MD5(String sStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sStr.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			//System.out.println("MD5(" + sStr + ",32) = " + result);
			//System.out.println("MD5(" + result + ",16) = " + buf.toString().substring(8, 24));
		} catch (Exception e) {
			System.out.println(e);
		}
		return result.toUpperCase();
	}

	// 32位MD5加密
	public static String newMD5(String sStr) {
		String result = "";
		try {
			//MessageDigest md = MessageDigest.getInstance("MD5");
			//md.update(sStr.getBytes("UTF-8"));
			//byte b[] = md.digest();

			//String s=DigestUtils.md5Hex(sStr).toLowerCase();//.substring(8, 24);
			
			String s = DigestUtils.sha512Hex(sStr);
			
			//System.out.println(s);
			StringBuilder sb = new StringBuilder();
			//System.out.println(DatatypeConverter.parseBase64Binary(s));
			for (int i = 0; i < DatatypeConverter.parseBase64Binary(s).length; i++)
			{
				if(i != DatatypeConverter.parseBase64Binary(s).length - 1){
					int ii =(int) DatatypeConverter.parseBase64Binary(s)[i];

					if (ii < 0)
						ii += 256;
					if (ii < 16)
						sb.append("0");
					String aa =Integer.toHexString(ii);
					sb.append(aa+"-");
				}else{
					int ii = DatatypeConverter.parseBase64Binary(s)[i];

					if (ii < 0)
						ii += 256;
					if (ii < 16)
						sb.append("0");
					String aa =Integer.toHexString(ii);
					sb.append(aa);
				}
			}
			result = sb.toString().toUpperCase();

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
	/**
	 * 对用户密码加密
	 * 先DES加密
	 * 再AES加密
	 * 最后MD5加密保存
	 * @param userPwd
	 * @return
	 */
	public static String encryptPwd(String userPwd){
		String result=newMD5(AesUtil.encrypt(DesUtil.encrypt(userPwd, EncryptKey.DesKey),EncryptKey.AesKey));
		return result;
	}
	public static void main(String[] args) {
		String pwd="企业信用SMMH_KB";
		System.out.println(newMD5(pwd));
		//F3-BD-B8-E3-A7-DE-7D-F7-35-F7-DD-75
	}
}
