package com.miapsoft.manager;

import net.sf.json.JSONObject;


public interface UpLongPicManager {
	//查长图所有的路径
	JSONObject getAllPicPath(String bigTage, String flagId, String pgorcg);
	//保存最终的路径
	int saveChange(String bigTage, String flagId, String finalPicPath, String pgorcg);
	
}
