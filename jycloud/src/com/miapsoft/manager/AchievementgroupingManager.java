package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface AchievementgroupingManager {
	public JSONArray getAchievementInfo();
	public JSONArray getauditreasondata(String type);
	public JSONArray getWaitAchievementinfo(String status);
	public JSONArray getAchievementagInfo(String id);
	public JSONArray searchAchievementagInfo(String str);
	public JSONArray searchWaitAchievementagInfo(String str,String status);
	public JSONArray searchAchievementagimageInfo(String id);
	public String addAchievementag(String name,String othername,String describe);
	public boolean updatecgImg(String agimage_id,String filename);
	public String getAchievementId(String sondata,String name);
	public boolean deletemember(String agid,String cgerid);
	public boolean deleteachievement(String id);
	public boolean adoptachievement(String userId,String id,String flag);
	public boolean rejectachievement(String userId,String id,String reason,String flag);
	public boolean regresses(String userId,String id,String reason,String flag);
	public JSONArray getachievementagdata();
	public JSONArray getsearchachievementagdata(String cgname);
	public String addachievementmemberdata(String agid,String cgername,String cgerid);
}
