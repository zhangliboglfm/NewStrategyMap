/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface CgAchieveManager {
	/**
	 * 获取摄影家成就信息
	 * @param cgId
	 * @return
	 */
	public JSONObject getCgAchieveWord(String cgId);
	
	
	/**
	 * 添加新成就
	 * @param obj
	 * @param cgId
	 * @return
	 */
	public String insertIntoNewArticle(JSONObject obj,String cgId);
	
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
	 * @param cgId
	 * @return
	 */
	public String insertIntoNewAchievementWithPic(String picname,String cgId);
	
	/**
	 * 更新成就-图片
	 * @param picname
	 * @param aId
	 * @return
	 */
	public int updateAchievementPicInfo(String picname, String aId);
}
