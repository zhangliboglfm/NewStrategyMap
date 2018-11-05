package com.miapsoft.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * AES加密器
 * 
 * @author Eric_Ni
 * 
 */
public class AesUtil {
	
	public static HashMap<String, String> keys = new HashMap<String, String>();

	public static Boolean isMapContains(String userName, String key){
		boolean flag = false;
		if (AesUtil.keys.containsKey(userName) && AesUtil.keys.get(userName).equals(key)) {
			flag = true;
		}else{
			flag = md5Encode(userName).equals(key);
		}
		return flag;
	}

	public static String base64encrypt(String str){
		String result = "";
		byte[] bytes = str.getBytes();
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String e = base64Encoder.encode(bytes);

		BASE64Decoder base64Decoder = new BASE64Decoder();
		String d = "";
		try {
			d = new String(base64Decoder.decodeBuffer(e));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result + "e:" + e + "；d:" + d + "；";
	}

	/***
	 * MD5加密 生成32位md5码
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) {
		inStr = inStr + md5base;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
	}

	public static String md5base = "JyCloudBackManage";

	public static String base = "JyCloudBackManage";

	public static BASE64Encoder base64Encoder = new BASE64Encoder();
	public static BASE64Decoder base64Decoder = new BASE64Decoder();

	public static String bm = "UTF-8";

	/**
	 * AES加密
	 */
	public static String encrypt(String cleartext,String key) {
		byte[] rawKey;
		try {
			rawKey = getRawKey(key.getBytes(bm));
			byte[] result = encrypt(rawKey, cleartext.getBytes(bm));
			return toHex(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * AES解密
	 */
	public static String decrypt(String encrypted, String key) {
		if(encrypted.length() <9)
			encrypted = encrypt(base, encrypted);
		byte[] rawKey;
		try {
			rawKey = getRawKey(key.getBytes(bm));
			byte[] enc = toByte(encrypted);
			byte[] result = decrypt(rawKey, enc);
			return new String(result);//result
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted)
			throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static String toHex(String txt) throws Exception {
		return toHex(txt.getBytes(bm));
	}

	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private final static String HEX = "0123456789ABCDEF";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}
	
	public static void main(String[] args) {
		String string="S3M9OUifATogZ1doBRpvJbUyk8y7n/UMO1RfH4D2a5c=";
		String md5String=MD5Util.newMD5(string);
		System.out.println("MD5:"+md5String);
		try {
			String desString=DesUtil.encrypt(md5String, md5String);
			String aesString=AesUtil.encrypt(desString, desString);
			String encryptResult=MD5Util.MD5(aesString);
			String aesKey=encryptResult;
			//System.out.println("AES加密KEY："+aesKey);
			String finalEncryptResult=MD5Util.newMD5(encryptResult);
			String desKey=finalEncryptResult;
			System.out.println("加密结果："+finalEncryptResult);
			//System.out.println("DES加密KEY："+desKey);
			//System.out.println("解密后："+decrypt(EncryptKey.AesKey,encryptResult));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//String encryptResult=encrypt(EncryptKey.AesKey,string);
		//System.out.println("加密后："+encryptResult);
		//System.out.println("解密后："+decrypt(EncryptKey.AesKey,encryptResult));
		
		
		
	}
}
