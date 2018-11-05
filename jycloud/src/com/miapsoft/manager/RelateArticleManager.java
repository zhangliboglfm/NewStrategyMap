package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface RelateArticleManager {
	//查文章分页数
	int searchPaging(String photogid);
	//查某一页文章内容
	JSONObject showArticleInfo(String photogId, String curr);
	//查摄影家名称
	String searchName(String photogId);
	//保存上传的文章内容
	JSONObject insertArticleInfo(JSONObject obj,String artId);
}
