package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface PgWorksManager {
	String selPhgWorks(String pgId,String start,String end);
	String selPhgWorksImg(String pgId);
	int addPhgWorksInfo(JSONObject obj);
	//删除作品并重新排序
	String deletePgWorks(String deleteId, String pgId);
	//作品重新排序
	String reOrderWork(String pgId);
}
