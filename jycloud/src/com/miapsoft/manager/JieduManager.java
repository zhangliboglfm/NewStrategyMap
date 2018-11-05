package com.miapsoft.manager;

import net.sf.json.JSONObject;


public interface JieduManager {
	//获取作品解读word、H5、图片名称
	JSONObject getJieduInfo(String articleId);

	String getAreInterpret(String articleId, String flagId);

	JSONObject reuploadJDu(String filename, String articleId, String xuanlei, String flagId);
	
}
