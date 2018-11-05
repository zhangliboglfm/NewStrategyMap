/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-7-30
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface MessageimportManager {
	/**
	 * 录入摄影家信息
	 * @param obj
	 * @return
	 */
	public int insertPhotographeIinfo_new(JSONObject obj);
	
	/**
	 * 录入摄影家信息
	 * @param obj
	 * @return
	 */
	public int insertPhotographeIinfo(JSONObject obj);
	
	/**
	 * 录入摄影家头像信息
	 * @param obj
	 * @return
	 */
	public int insertPhotographerHeadImgInfo(JSONObject obj);
	
	/**
	 * 录入摄影家作品信息
	 * @param obj
	 * @return
	 */
	public int insertPhotographerWorksImgInfo(JSONObject obj);
	
	/**
	 * 录入文章信息
	 * @param obj
	 * @return
	 */
	public int insertArticleInfo(JSONObject obj);
	
	/**
	 * 录入文章摄影家信息
	 * @param obj
	 * @return
	 */
	public int insertArticlePhotographerInfo(JSONObject obj);
	
	/**
	 * 录入文章作品信息
	 * @param obj
	 * @return
	 */
	public int insertArticleWorksInfo(JSONObject obj);
	
	/**
	 * 录入标签信息
	 * @param obj
	 * @return
	 */
	public String insertLable(String lablestr,String labletype,String labledesc);
	
	/**
	 * 录入标签关系
	 * @param obj
	 * @return
	 */
	public int insertLableRelation(String lableid,String mid,int showindex);
}
