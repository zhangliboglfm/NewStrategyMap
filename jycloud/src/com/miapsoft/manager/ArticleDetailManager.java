package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface ArticleDetailManager {
	//查询文章已有标签并展示
	JSONObject getArticlLabel(String articleId);
	//查询已有标签和所有标签，并分类显示
	JSONObject getAllArtLabel();
	//对文章标签进行操作
	JSONObject opArticleLabel(String articleId, String labelId, String operate);
	//查询文章名称
	JSONObject getArtTitle(String articleId);
	//接收文件并保存
	JSONObject reuploadImg(String rfilePath, String articleId, String fileType, String coverOrfile);
	//查相关摄影家
	JSONObject searchPhotogInfo(String articleId);
	//查相关作品
	JSONObject searchWorksInfo(String articleId);
	//重新排序摄影家、作品
	JSONObject reOrderPhotog(String articleId, String reOrder, String opFlag);
	//添加相关摄影家
	JSONObject addNewPhotogF(String articleId, String pgid);
	//查询未添加的摄影家
	String selphotogList(String pgName, String start, String end, String pgGroups);
	//查相关摄影家
	JSONObject selectRelatePg(String pgGroups);
	//查询作品
	String selworksList(String wkGroups, String pgOrder, String pgId, String start, String end);
	//添加相关作品
	JSONObject addNewWorksF(String articleId, String wkid);
	//删除相关作品摄影家
	JSONObject deleteOper(String articleId, String labelId, String deleteFlag);
	//查询所有文章封面及标题，并生成左侧树结构
	JSONObject getAllArticle(String photogId);
	//获取下载文件路径
	String getFilePath(String articleId, String fileType);
	//添加审核状态
	JSONObject addAuditing(String articleId,String articleTile);
	//添加自定义标签
	JSONObject addCustomLabel(String articleId, String labelName, String labeldesc);
	//改变审核状态
	String changeAuditStatus(String articleId, String aUDIT_STATUS,
			String aUDIT_DESC, String aUDIT_PERSN);
	JSONObject getWordAndPic(String articleId);
	JSONObject auditState(String articleId);
	//新生成文章ID
	String getNewArtId();
	//操作新标签
	JSONObject updateLabel(String articleId, String arr);
	
	
}
