package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface ProductionManager {
	/**
	 * 获取摄影家名字
	 * @param photogid
	 * @return
	 */
	public String getPhotogName(String photogid);

	/**
	 *获取作品类的标签
	 * @param Workid		作品id
	 * @param LabelName		标签名搜索关键字
	 * @return
	 */
	public JSONArray getLabelList(String Workid);

	/**
	 * 对作品标签进行增，删
	 * @param workid
	 * @param lABEL_ID
	 * @param operate
	 * @return
	 */
	public String operateWorkLabel(String workid, String lABEL_ID,String operate);

	/**
	 * 
	 * @param workid
	 * @param tagName
	 * @param tagDesc
	 * @return
	 */
	public JSONObject addDefinedLabel(String LabelName,String LabelDesc);

	/**
	 * 根据作品Id获取作品详细信息
	 * @param workid
	 * @return
	 */
	public JSONObject getWorkDesc(String workid);

	/**
	 * 更新作品信息
	 * @param workid
	 * @param works_name
	 * @param works_type
	 * @param shoot_date
	 * @param works_intro
	 * @param shoot_proc
	 * @param is_repre_works
	 * @return
	 */
	public String updateWorkDesc(String workid, String works_name,String works_type, String shoot_date, String works_intro,String shoot_proc, String is_repre_works,String [] labelIds);

	/**
	 * 根据摄影家id获取作品数量
	 * @param photogid
	 * @return
	 */
	public int getWorkNumByPhotogId(String photogid);
	
	/**
	 * 根据摄影家id获取作品信息
	 * @param photogid
	 * @return
	 */
	public JSONArray getWorksDataByPhotogID(String photogid);

	/**
	 * 更新摄影家作品
	 * @param productionManager
	 * @param filename
	 * @return
	 */
	public boolean uploadWorkImg(String Workid,String filename);

	/**
	 * 改变作品审核状态
	 * @param workid
	 * @param aUDIT_STATUS
	 * @param aUDIT_DESC
	 * @param aUDIT_PERSN
	 * @return
	 */
	public String changeExamineStatus(String workid, String aUDIT_STATUS,String aUDIT_DESC, String aUDIT_PERSN,String type);

	
}
