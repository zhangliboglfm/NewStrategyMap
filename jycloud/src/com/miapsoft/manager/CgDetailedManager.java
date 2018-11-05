package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface CgDetailedManager {
	/**
	 * 获取朝代
	 */
	public JSONArray getDynastys();
	/**
	 * 获取民族
	 */
	public JSONArray getNation();
	/**
	 * 获取书法家基本信息
	 * @param cgId
	 * @return
	 */
	public JSONObject getDerpherBaseInfo(String cgId);
	//查询书法家所有的文章
	public JSONObject getAllCGWorks(String cgId, String cgWkName);
	//对书法家作品进行操作
	public int updateWorksI(String cgId, String arr);
	//ID、名称、字、号、性别、民族、出生日期、朝代、成就\地位、出生地、祖籍、别称、去世时间、重要事迹
	public int updateCgBaseInfo(String cgId, String cgName,
			String iptCgSecName, String iptCgNickName, String iptCgPhotogSex,
			String iptCgNation, String iptCgBornTime, String iptCgDynasty,
			String iptCgAgName, String iptCgBirthPlace, String iptCgDescent,
			String iptCgAlias, String iptCgDeathTime, String iptCgImptDeeds);
	//获取地位码表信息
	public JSONArray getAgName();
	//保存书法家头像信息
	public int saveHeadImg(String cgId, String headImgP);
	//审核书法家
	public String auditCallig(String cgId, String auditStatus,
			String auditDesc, String auditPersn);
}
