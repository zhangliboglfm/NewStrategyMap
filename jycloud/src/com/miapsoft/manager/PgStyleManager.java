/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-13
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface PgStyleManager {
	/**
	 * 获取摄影家风格信息
	 * @param pId
	 * @return
	 */
	public JSONObject getPgStyleWord(String pId);
	
	
	/**
	 * 添加新风格
	 * @param obj
	 * @param pId
	 * @return
	 */
	public String insertIntoNewArticle(JSONObject obj,String pId);
	
	/**
	 * 更新风格
	 * @param obj
	 * @param aId
	 * @return
	 */
	public int updateStyleDocInfo(JSONObject obj, String aId);
	
	/**
	 * 添加风格-图片
	 * @param picname
	 * @param pId
	 * @return
	 */
	public String insertIntoNewStyleWithPic(String picname,String pId);
	
	/**
	 * 更新风格-图片
	 * @param picname
	 * @param aId
	 * @return
	 */
	public int updateStylePicInfo(String picname, String aId);
}
