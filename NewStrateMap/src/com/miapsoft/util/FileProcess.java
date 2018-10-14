package com.miapsoft.util;

import java.io.File;

/**
 * <p>Title: FileProcess.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-26
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
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
