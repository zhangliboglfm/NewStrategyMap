package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface PgStandardImgManager {
	public String selStandImgs(String pgId);
	public JSONObject addStandImg(String pgId, String filename);
	public String updateStandImgOrder(String pgId,String fileName,String newOrder);
	public String delStandImg(String pgId,String fileName);
}
