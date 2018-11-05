package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface CgWorksManager {

	/**
	 * 获取作品页数
	 * @param cgerId
	 * @param workname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject getPagenum(String cgerId, String workname, String pageNum,String pageSize);
	/**
	 * 获取待审核作品页数
	 * @param cgerId
	 * @param workname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject getauditPagenum(String cgerId, String workname, String pageNum,String pageSize,String status);	
	/**
	 * 按照页码查找重要作品
	 * @param cgerId
	 * @param workname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject getimpworks(String cgerId, String workname, String pageNum,
			String pageSize);
	/**
	 * 按照页码查找其它作品
	 * @param cgerId
	 * @param workname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject getothworks(String cgerId, String workname, String pageNum,
			String pageSize);
	/**
	 * 获取作品详情
	 * @param cgerid
	 * @param workid
	 * @return
	 */
	JSONObject getWorksDetail(String cgerid, String workid);
	/**
	 * 作品详细信息页面获取作品
	 * @param cgerid
	 * @param isimport
	 * @return
	 */
	JSONObject getworkslist(String cgerid, String isimport);
	/**
	 * 合集详细信息页面获取作品
	 * @param cgerid
	 * @param isimport
	 * @return
	 */
	JSONObject getworkslistCPG(String cgerid, String isimport,String workid);
	
	/**
	 * 作品审核详细信息页面获取作品
	 * @param cgerid
	 * @param isimport
	 * @return
	 */
	JSONObject getauditworkslist(String cgerid, String isimport,String status);
	
	/**
	 * 作品审核详细信息页面获取作品
	 * @param cgerid
	 * @param isimport
	 * @return
	 */
	JSONObject getauditworkslist2(String cgerid, String isimport);
	/**
	 * 作品详细信息
	 * @param workid
	 * @param cgid
	 * @return
	 */
	JSONObject getworkinfors(String workid, String cgid);


	
	/**
	 * 更新详细信息
	 * @param workid
	 * @param cgid
	 * @return
	 */
	boolean updateworkinfors(String workid,String cgid,String worksname,String wholename,String years,String cgtype,String worknum,String spec,String pstcoll);	
	/**
	 * 作品图片
	 * @param workid
	 * @param cgid
	 * @return
	 */
	JSONObject getCGImgs(String workid, String cgid);
	/**
	 * 删除作品图片
	 * @param workid
	 * @param cgid
	 * @return
	 */
	String delworksImg(String workid, String cgid);
	/**
	 * 删除所有作品图片
	 * @param workid
	 * @param cgid
	 * @return
	 */
	String delallworksImg(String workid);
	/**
	 * 更新作品图片顺序
	 * @param workid
	 * @param cgid
	 * @return
	 */	
	String updateworksImgOrder(String pgId,String fileName,String newOrder);
	/**
	 * 上传作品图片
	 * @param workid
	 * @param cgid
	 * @return
	 */	
	JSONObject addworksImg(String pgId,String filename);
	/**
	 * 替换作品图片
	 * @param workid
	 * @param cgid
	 * @return
	 */	
	JSONObject changeworksImg(String pgId,String filename,String rfilename);
}
