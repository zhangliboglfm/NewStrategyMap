/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface PgLifeTimeManager {
	/**
	 * 获取摄影家生平信息
	 * @param pId
	 * @return
	 */
	public JSONObject getPgLifeTimeWord(String pId);
	
	
	/**
	 * 添加新生平
	 * @param obj
	 * @param pId
	 * @return
	 */
	public String insertIntoNewArticle(JSONObject obj,String pId);
	
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
	 * @param pId
	 * @return
	 */
	public String insertIntoNewArticleWithPic(String picname,String pId);
	
	/**
	 * 更新生平-图片
	 * @param picname
	 * @param aId
	 * @return
	 */
	public int updateArticlePicInfo(String picname, String aId);
}
