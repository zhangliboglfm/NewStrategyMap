package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PgSerInterfaceManager {
	/**
	 * 摄影家顺序接口
	 * @param receiveTime
	 * @return
	 */
	JSONArray getPhotogOrder(String receiveTime);
	/**
	 * 增量摄影家接口
	 * @param receiveTime
	 * @return
	 */
	JSONArray addPhotog(String receiveTime);
	/**
	 * 增量摄影家代表作接口
	 * @param photogId
	 * @return
	 */
	JSONArray addPhotogWorks(String photogId);
	/**
	 * 作品图片下载接口
	 * @param worksId
	 * @return
	 */
	String worksPictures(String worksId);
	/**
	 * 推荐文章接口
	 * @param receiveTime
	 * @return
	 */
	JSONArray recomArticles(String receiveTime);
	/**
	 * 摄影家头像下载接口
	 * @param photogId
	 * @return
	 */
	JSONArray getphotogHead(String photogId);
	/**
	 * 查询接口
	 * @param photoName
	 * @return
	 */
	JSONArray getfuzzyQuery(String photoName);
	/**
	 * 收藏接口
	 * @param sTOREMBODYTYPE
	 * @param sTOREMBODYID
	 * @param uSERACCTID
	 * @return
	 */
	int collectiones(String STOREMBODYTYPE, String APPID,String USERACCTID);
	/**
	 * 生平/风格/成就数据接口
	 * @param photoId
	 * @param classify
	 * @return
	 */
	JSONObject getliStAcDatas(String photoId, String classify);
	/**
	 * 摄影家作品接口
	 * @param photoId
	 * @return
	 */
	JSONArray getphotogWorks(String photoId);
	/**
	 * 作品图片下载
	 * @param workId
	 * @return
	 */
	JSONArray getworksImg(String workId);
	/**
	 * 作品解读
	 * @param workId
	 * @return
	 */
	String getworksRead(String workId);
	/**
	 * 文章查看
	 * @param articleId
	 * @return
	 */
	JSONObject getarticleRead(String articleId);
	/**
	 * 相关作品接口
	 * @param workId
	 * @return
	 */
	JSONArray getrelateWorks(String workId);
}
