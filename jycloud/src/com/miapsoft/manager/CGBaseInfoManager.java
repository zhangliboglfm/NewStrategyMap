/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-29
*/
package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface CGBaseInfoManager {
	
	/**
	 * 获取书法家基本信息
	 * @param importDate
	 * @param pgName
	 * @param start
	 * @param end
	 * @return
	 */
	public JSONObject selectCalligList(String importDate, String cgName, String orderColumn, String orderSortype, String auditStatus, String start, String end);
	
	/**
	 * 获取书法家头像信息
	 * @param pgid
	 * @param picType
	 * @param showFlag
	 * @return
	 */
	public String selectCalligPicInfo(String cgid,String showFlag);
	//审核部分查数
	public JSONObject selectCalligList2(String importDate, String pgName,
			String sortColumn, String sortType, String auditStatus,
			String start, String end);
}
