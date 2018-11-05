package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface CGArticleManager {
	//保存上传的文章内容
	JSONObject insertArticleInfo(JSONObject obj,String artId);
	//查文章分页数
	int searchCGArtPage(String cgName, String auditStatus);
	//查某一页文章内容
	JSONObject showCGArtInfo(String cgName, String curr, String auditStatus);
}
