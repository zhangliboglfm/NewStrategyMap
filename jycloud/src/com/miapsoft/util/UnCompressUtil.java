package com.miapsoft.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

/*** <p>Title: UnCompressUtil</p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-4-3
*/
public class UnCompressUtil {
	
	/*** 这里用到了synchronized ，也就是防止出现并发问题 ***/
	public static synchronized void untar(String tarFileName, String extPlace) throws Exception {
		unRarFile(tarFileName, extPlace);
	}

	public static synchronized void unzip(String zipFileName, String extPlace) throws Exception {
		unZipFiles(zipFileName, extPlace);
	}


	/**
	 * 解压zip格式的压缩文件到指定位置
	 * 
	 * @param zipFileName 压缩文件
	 * @param extPlace 解压目录
	 * @throws Exception
	 */
	public static boolean unZipFiles(String zipFileName, String extPlace) throws Exception {
		System.setProperty("sun.zip.encoding",System.getProperty("sun.jnu.encoding"));
		InputStream is = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			(new File(extPlace)).mkdirs();
			File f = new File(zipFileName);
			//ZipFile zipFile = new ZipFile(zipFileName,"GB2312"); // 处理中文文件名乱码的问题
			ZipFile zipFile = new ZipFile(zipFileName,"UTF-8"); // 处理中文文件名乱码的问题
			if ((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("要解压的文件不存在!");
			}
			String strPath, gbkPath, strtemp;
			File tempFile = new File(extPlace);
			strPath = tempFile.getAbsolutePath();
			Enumeration<?> e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				ZipEntry zipEnt = (ZipEntry) e.nextElement();
				gbkPath = zipEnt.getName();
				String newNameQ=zipFileName.substring(zipFileName.lastIndexOf(File.separator)+1,zipFileName.length());//去文件后缀
				newNameQ = newNameQ.substring(0,newNameQ.lastIndexOf("."));//去文件后缀
				gbkPath=newNameQ+gbkPath.substring(gbkPath.indexOf("/"));
				if (zipEnt.isDirectory()) {
					strtemp = strPath + File.separator + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else { // 读写文件.
					//zipEnt.setUnixMode(644);
					is = zipFile.getInputStream(zipEnt);
					bis = new BufferedInputStream(is);
					gbkPath = zipEnt.getName();
					gbkPath=newNameQ+gbkPath.substring(gbkPath.indexOf("/"));
					strtemp = strPath + File.separator + gbkPath;// 建目录
					//strtemp=new String((strtemp).getBytes(), "gbk");
					String strsubdir = gbkPath;
					for (int i = 0; i < strsubdir.length(); i++) {
						if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = strPath + File.separator
									+ strsubdir.substring(0, i);
							File subdir = new File(temp);
							if (!subdir.exists())
								subdir.mkdir();
						}
					}
					fos = new FileOutputStream(strtemp);
					bos = new BufferedOutputStream(fos);
					int c;
					while ((c = bis.read()) != -1) {
						bos.write((byte) c);
					}
					bos.close();
					fos.close();
					is.close();
					bis.close();
				}
			}
			bos.close();
			fos.close();
			is.close();
			bis.close();
			return true;
		} catch (Exception e) {
			bos.close();
			fos.close();
			is.close();
			bis.close();
			e.printStackTrace();
			return false;
		}finally{
			bos.close();
			fos.close();
			is.close();
			bis.close();
		}
	}


	/**
	 * 根据原始rar路径，解压到指定文件夹下.
	 * 
	 * @param srcRarPath 原始rar路径
	 * @param dstDirectoryPath 解压到的文件夹
	 */
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("非rar文件！");
			return;
		}
		File dstDiretory = new File(dstDirectoryPath);
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
			dstDiretory.mkdirs();
		}
		Archive a = null;
		try {
			a = new Archive(new File(srcRarPath));
			if (a != null) {
				// a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
					// 防止文件名中文乱码问题的处理
					String fileName = fh.getFileNameW().isEmpty() ? fh.getFileNameString() : fh.getFileNameW();
					if (fh.isDirectory()) { // 文件夹
						File fol = new File(dstDirectoryPath + File.separator + fileName);
						fol.mkdirs();
					} else { // 文件
						File out = new File(dstDirectoryPath + File.separator + fileName.trim());
						try {
							if (!out.exists()) {
								if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
									out.getParentFile().mkdirs();
								}
								out.createNewFile();
							}
							FileOutputStream os = new FileOutputStream(out);
							a.extractFile(fh, os);
							os.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					fh = a.nextFileHeader();
				}
				a.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
