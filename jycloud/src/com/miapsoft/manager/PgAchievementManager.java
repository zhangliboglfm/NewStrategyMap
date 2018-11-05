/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface PgAchievementManager {
	/**
	 * 获取摄影家成就信息
	 * @param pId
	 * @return
	 */
	public JSONObject getPgAchievementWord(String pId);
	
	
	/**
	 * 添加新成就
	 * @param obj
	 * @param pId
	 * @return
	 */
	public String insertIntoNewArticle(JSONObject obj,String pId);
	
	/**
	 * 更新成就
	 * @param obj
	 * @param aId
	 * @return
	 */
	public int updateAchievementDocInfo(JSONObject obj, String aId);
	
	/**
	 * 添加成就-图片
	 * @param picname
	 * @param pId
	 * @return
	 */
	public String insertIntoNewAchievementWithPic(String picname,String pId);
	
	/**
	 * 更新成就-图片
	 * @param picname
	 * @param aId
	 * @return
	 */
	public int updateAchievementPicInfo(String picname, String aId);
}
