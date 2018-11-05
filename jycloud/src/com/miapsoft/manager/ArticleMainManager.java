package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ArticleMainManager {

	JSONObject getWzDates(String wzName, String pageNum, String pageSize);

	JSONObject getWzTags(String wzName);

	int saveMessages(String wzImageSrc, String wzImageAlt,
			String wzName, String wzContent, JSONArray jsonArray1,
			JSONArray jsonArray2, String wzIdnet);

	JSONArray getLablist_Art(String PHOTOG_ID, String tagName);

	String operateLabel_Art(String PHOTOG_ID, String LABEL_ID, String operate);

	JSONObject getZP(String wzIdnet);

	boolean updateArtImg(String PHOTOG_ID, String filename);

	JSONObject getRyCounts(String zzName, String pageNum, String pageSize);

	JSONArray getAreInterpret(String articleId, String layId);

	JSONObject photogResume(String articleId);

	JSONObject reuploadJDu_ART(String filePath, String articleId, String xuanlei, String layId);

}
