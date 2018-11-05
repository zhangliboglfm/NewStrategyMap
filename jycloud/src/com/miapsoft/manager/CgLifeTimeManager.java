/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface CgLifeTimeManager {
	/**
	 * 获取摄影家生平信息
	 * @param cgId
	 * @return
	 */
	public JSONObject getCgLifeTimeWord(String cgId);
	
	
	/**
	 * 添加新生平
	 * @param obj
	 * @param cgId
	 * @return
	 */
	public String insertIntoNewArticle(JSONObject obj,String cgId);
	
	/**
	 * 更新生平
	 * @param obj
	 * @param aId
	 * @return
	 */
	public int updateArticleDocInfo(JSONObject obj, String aId);
	
	/**
	 * 添加生平-图片
	 * @param picname
	 * @param cgId
	 * @return
	 */
	public String insertIntoNewArticleWithPic(String picname,String cgId);
	
	/**
	 * 更新生平-图片
	 * @param picname
	 * @param aId
	 * @return
	 */
	public int updateArticlePicInfo(String picname, String aId);
}
