package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface FilmfilterManager {
	/**
	 * 获取数目
	 */
	public JSONArray getNums(String SAMP_PIC_NAME,String FILM_CAMERA_ID,String DEAL_DATE,
			String IS_BLACK,String SAMP_PIC_LENGTH,String LABEL_NAME,
			String LENS_ID,String FILM_PROD_ID,String size,String state1,String state2);
	/**
	 * 根据条件获取图片
	 */
	public JSONArray getPic(String SAMP_PIC_NAME,String FILM_CAMERA_ID,String DEAL_DATE,
			String IS_BLACK,String SAMP_PIC_LENGTH,String LABEL_NAME,
			String LENS_ID,String FILM_PROD_ID,String pageNum,String pageSize,String size,String state1,String state2);
	/**
	 * 根据图片ID获取图片信息
	 */
	public JSONArray getOnePic(String SAMP_PIC_ID);
	/**
	 * 查询标签类型
	 */
	public JSONArray getLabel();
	/**
	 * 模糊查询相机型号
	 */
	public JSONArray getCamera(String keyword);
	/**
	 * 模糊查询胶片型号
	 */
	public JSONArray getFilm(String keyword);
	/**
	 * 模糊查询镜头型号
	 */
	public JSONArray getLens(String keyword);
	/**
	 * 更改图片信息
	 */
	public String updateInfo(String SAMP_PIC_ID,String SAMP_PIC_NAME,String FILM_PROD_ID,String FILM_CAMERA_ID,String LENS_ID,String IS_BLACK);
	/**
	 * 删除图片
	 */
	public String deletePic(String SAMP_PIC_ID);
	/**
	 * 筛选图片
	 */
	public String screenPic(String SAMP_PIC_ID);
	/**
	 * 恢复图片
	 */
	public String recoveryPic(String SAMP_PIC_ID);
	/**
	 * 驳回图片
	 */
	public String rejectPic(String SAMP_PIC_ID);
	/**
	 * 通过
	 */
	public String pass(String SAMP_PIC_ID);
	/**
	 * 通过id查找图片标签
	 */
	public JSONArray getPicLabel(String SAMP_PIC_ID);
	/**
	 * 给图片增加标签
	 */
	public String addPicLabel(String SAMP_PIC_ID,String LABEL_ID,String order);
	/**
	 * 删除图片标签
	 */
	public String deletePicLabel(String SAMP_PIC_ID,String LABEL_ID);
	/**
	 * 搜索标签
	 */
	public JSONArray searchLabel(String keyword,String SAMP_PIC_ID);
	/**
	 * 增加自定义标签
	 */
	public String addLabel(String LABEL_NAME,String SAMP_PIC_ID,String LABEL_DESC);
	/**
	 * 获取驳回原因
	 */
	public JSONArray getDismissal();
	/**
	 * 获取回退原因
	 */
	public JSONArray getRegresses();
	/**
	 * 添加图片回退原因
	 */
	public String addRegresses(String SAMP_PIC_ID,String SAMP_PIC_TYPE,String STATUS_ID,String AUDIT_DESC,String AUDIT_PERSN_ACCT_ID,String BACK_REASON_ID);
	/**
	 * 添加自定义原因
	 */
	public String addReason(String REASON_ID,String REASON_TYPE,String REASON_NAME,String REASON_DESC,String SHOW_ORDER);
}
