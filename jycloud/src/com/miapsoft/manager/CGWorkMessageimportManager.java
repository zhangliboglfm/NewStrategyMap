package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface CGWorkMessageimportManager {

	/**
	 * 录入书法家信息
	 * @param obj
	 * @return
	 */
	public int insertcgworkinfo(JSONObject obj);
	/**
	 * 录入书法家信息
	 * @param obj
	 * @return
	 */
	public int insertcgworkImageinfo(String filename,String cgerid);
	
	/**
	 * 获取新作品id
	 * @return
	 */
	public String getnewworkid(String cgerId);

}
