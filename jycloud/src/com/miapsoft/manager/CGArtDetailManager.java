package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface CGArtDetailManager {
	//查询文章名称
	JSONObject getCGArtTit(String articleId);
	//接收文件并保存
	JSONObject reupCGImg(String rfilePath, String articleId, String fileType, String coverOrfile);
	//查相关书法家
	JSONObject searchCGInfo(String articleId);
	//查相关作品
	JSONObject searchCGWorksInfo(String articleId);
	//重新排序书法家、作品
	JSONObject reOrderPhotog(String articleId, String reOrder, String opFlag);
	//添加相关书法家
	JSONObject addNewPhotogF(String articleId, String pgid);
	//查询未添加的书法家
	String getCGInfo(String pgName, String start, String end, String pgGroups);
	//查相关书法家
	JSONObject selectRelateCg(String pgGroups);
	//查询作品
	String selcgworksList(String wkGroups, String pgOrder, String pgId, String start, String end);
	//添加相关作品
	JSONObject addNewWorksF(String articleId, String wkid);
	//删除相关作品书法家
	JSONObject deleteOper(String articleId, String labelId, String deleteFlag);
	//查询所有文章封面及标题，并生成左侧树结构
	JSONObject getAllCGArt(String cgName, String auditStatus);
	//获取下载文件路径
	String getFilePath(String articleId, String fileType);
	//添加审核状态
	JSONObject addCGAudit(String articleId,String articleTile);
	/*//添加自定义标签
	JSONObject addCustomLabel(String articleId, String labelName, String labeldesc);*/
	//改变审核状态
	String changeCGAudit(String articleId, String aUDIT_STATUS,
			String aUDIT_DESC, String aUDIT_PERSN);
	JSONObject getWordAndPic(String articleId);
	JSONObject auditState(String articleId);
	//新生成文章ID
	String getNewArtId();
	//删除文章
	int deleteMessage(String articleId);
	//查word路径
	String cgArtWdShow(String articleId, String flagId);
	//获取所有审核
	JSONObject getcgauditstatus(String auditStatus);
	//连带删除书法家作品
	JSONObject deleteCgWorks(String deleteCgId);
	
}
