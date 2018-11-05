package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface MessagecheckManager {
	
	
	/**
	 * 根据条件查询摄影家信息
	 * 分页查询
	 * @param photogName
	 * @param dealTime
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public JSONObject getPhotoglist(String photogName,String dealTime,String pageSize,String pageNum);
	
	/**
	 * 获取摄影家详细信息
	 * @param PHOTOG_ID
	 * @return
	 */
	public JSONObject getPhotogdesc(String PHOTOG_ID);
	
	
	/**
	 * 保存摄影家详细信息
	 * @param PHOTOG_ID
	 * @return
	 */
	public String savePhotoDesc(String PHOTOG_ID,String [] params);
	
	
	/**
	 * 保存摄影家详细信息
	 * @param PHOTOG_ID
	 * @return
	 */
	
	public JSONArray getLablist(String PHOTOG_ID,String tagName);
	
	/**
	 * 操作此标签
	 * @param PHOTOG_ID
	 * @param LABEL_ID
	 * @param operate
	 * @return
	 */
	public String operateLabel(String PHOTOG_ID,String LABEL_ID,String operate);
	
	
	/**
	 * 删除此标准照
	 * @param PHOTOG_ID
	 * @param imgname
	 * @return
	 */
	public String deleteThisStrandImg(String imgname);
	
	
	/**
	 * 改变标准照的显示状态
	 * @param imgname
	 * @param status
	 * @return
	 */
	public String changeShowStatus(String imgname,String status);
	
	/**
	 * 改变标准照显示顺序
	 * @param imgname
	 * @param status
	 * @return
	 */
	public String updateStrandOrder(String [] imgnamearr);
	

	/**
	 * 更新摄影家头像
	 * @param PHOTOG_ID
	 * @param LABEL_ID
	 * @param operate
	 * @return
	 */
	public boolean updateHeadImg(String PHOTOG_ID,String filename);
	
	/**
	 * 上传摄影家标准照
	 * @param PHOTOG_ID
	 * @param LABEL_ID
	 * @param operate
	 * @return
	 */
	public boolean uploadStrandImg(String PHOTOG_ID,String filename);

	/**
	 * 摄影家审核状态修改
	 * @param pHOTOG_ID
	 * @param aUDIT_STATUS
	 * @param aUDIT_DESC
	 * @param aUDIT_PERSN
	 * @return
	 */
	public String changeStatusData(String pHOTOG_ID, String aUDIT_STATUS,String aUDIT_DESC, String aUDIT_PERSN);

	/**
	 * 为摄影家添加自定义标签
	 * @param pHOTOG_ID
	 * @param tagName
	 * @param tagDesc
	 * @return
	 */
	public JSONObject addDefinedTag(String pHOTOG_ID, String tagName, String tagDesc);
	

}
