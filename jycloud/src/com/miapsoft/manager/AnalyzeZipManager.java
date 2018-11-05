package com.miapsoft.manager;

public interface AnalyzeZipManager {
	//查书法家Id
	String getCalligId(String cgName);
	//验证作品是否已存在，如果不存在，插入。存在，不插入
	String verifiWorks(String newworkId, String name, String cgId);
	//删除现有的作品图片关联关系
	String deleteWorkPicR(String workId);
	//往作品图片相关表插入数据
	String insertNerWorks(String realWorkId, String rfilePath, String showOrder);
	//生成新的workId
	String getNewWorkId(String cgId);
	
}
