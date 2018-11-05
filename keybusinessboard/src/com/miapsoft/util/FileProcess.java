package com.miapsoft.util;

import java.io.File;

public class FileProcess {
	public final static String EXPORT_PATH = "/home/bea/bass_file1/viewquery/export";
	
	/**
	 * 某文件夹下的文件，超过一小时未改动就删除
	 * @param dirpath
	 */
	public static void deleteExpireFile(String dirpath){
		File dir = new File(dirpath);
		if(dir.exists()){
			File[] files = dir.listFiles();
			for(File file:files){
				long lastTime = file.lastModified();
				long currentTime = System.currentTimeMillis();
				if(lastTime!=0L){//文件存在并且无io错误
					long gap = currentTime - lastTime;
					gap = gap/(1000*60*60); //小时
					if(gap>=1L){
						file.delete();
					}
				}
			}
		}
	}
}
