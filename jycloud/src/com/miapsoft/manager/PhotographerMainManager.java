/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
*/
package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PhotographerMainManager {
	/**
	 * 获取摄影家数据
	 * @param pId 摄影家ID
	 * @return
	 */
	public JSONObject getPhotographerBaseInfo(String pId);
	
	/**
	 * 获取国家码表数据
	 * @return
	 */
	public JSONArray getAllNationInfo();
	
	/**
	 * 获取全部摄影家标签信息
	 * @return
	 */
	public JSONArray getAllPgLbaleInfo();
	
	/**
	 * 更新摄影家基础信息
	 * @param obj
	 * @return
	 */
	public int updatePgBaseInfo(String pId,JSONObject obj, String userId);
	
	/**
	 * 更新摄影家头像信息
	 * @param pId
	 * @param filename
	 * @return
	 */
	public int updatePgHeadImgInfo(String pId,String filename);
	
	/**
	 * 更新摄影家标签信息
	 * @param arr
	 * @return
	 */
	public int updatePgLableInfo(String pId,JSONArray arr);
	
	/**
	 * 添加一个新标签
	 * @param obj
	 * @return
	 */
	public JSONObject addNewLableData(JSONObject obj);
	
	/**
	 * 获取全部审核状态
	 * @return
	 */
	public JSONArray getAllAuditStatus();
	
	/**
	 * 保存审核状态
	 * @param pId
	 * @param auditstatusid
	 * @return
	 */
	public int saveAuditStatus(String pId, String auditstatusid, String auditdesc, String userId);
	
}
