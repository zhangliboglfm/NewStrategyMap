package com.miapsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;  
 
import javax.crypto.Cipher;  
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;  

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
  
public class DesUtil {

	private final static String DES = "DES";
	private static final int CACHE_SIZE = 1024 * 1024;

	public static void main(String[] args) throws Exception {
		/*String data = "q/GywfUFoeSJDmv8GpL2AQ==";
		String key = "AA0D493304625C2B3073E9891F96BA02";
		System.out.println(encrypt(data, key));
		System.out.println(decrypt(encrypt(data, key), key));*/
		File file2 = new File("F:\\resourcelib\\photographer\\article\\A_TA00000002\\swing.jpg");
		File file1 = new File("F:\\resourcelib\\photographer\\article\\A_TA00000002\\swing_new.jpg");
		encryptFile(EncryptKey.DesKey, new FileInputStream(file2), new FileOutputStream(file1));
		//E:\resourcelib\photographer\photog\tmp\in
		/*ArrayList<String> files = new ArrayList<String>();
		File file= new File("E:/resourcelib/photographer/photog/tmp/in");
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("文件：" + tempList[i]);
				//files.add(tempList[i].toString());
				
				encryptFile(EncryptKey.DesKey, new FileInputStream(tempList[i]), new FileOutputStream("E:/resourcelib/photographer/photog/tmp/out/45028075_"+(i+1)+".jpg"));
			}
			if (tempList[i].isDirectory()) {
				System.out.println("文件夹：" + tempList[i]);
			}
		}*/
	}

	/**
	 * Description 根据键值进行加密
	 * @param data 
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) {
		byte[] bt = null;
		try {
			bt = encrypt(data.getBytes(), key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException,
	Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf,key.getBytes());
		return new String(bt);
	}

	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}


	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}
	
	
	/**
	 * <p>
	 * 文件加密
	 * </p>
	 * 
	 * @param key
	 * @param sourceFilePath
	 * @param destFilePath
	 * @throws Exception
	 */
	public static InputStream encryptFile(String key, InputStream in, OutputStream out) throws Exception {
		//Key k = toKey(Base64Utils.decode(key));
		/*Key k = toKey(key.getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		CipherInputStream cin = new CipherInputStream(in, cipher);
		
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		while ((nRead = cin.read(cache)) != -1) {
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		cin.close();
		in.close();*/
		
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		CipherInputStream cin = new CipherInputStream(in, cipher);
		
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		while ((nRead = cin.read(cache)) != -1) {
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		cin.close();
		in.close();
		
		return cin;
		
	}
	
	/**
	 * <p>
	 * 文件解密
	 * </p>
	 * 
	 * @param key
	 * @param sourceFilePath
	 * @param destFilePath
	 * @throws Exception
	 */
	public static byte[] decryptFile(String key, InputStream sInputStream) throws Exception {
		InputStream in = sInputStream;
		int i = in.available(); // 得到文件大小
		byte data[] = new byte[i];
		in.read(data); // 读数据
		in.close();
		
		/*
		//Key k = toKey(Base64Utils.decode(key));
		Key k = toKey(key.getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, k);*/
		
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		
		
		return cipher.doFinal(data);
	}
	/**
	 * <p>
	 * 转换密钥
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}
}
