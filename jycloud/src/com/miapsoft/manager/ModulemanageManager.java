package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface ModulemanageManager {
	public void editModule(String modulename,String modulelink,String flag);
	//获取所有的模块信息
	public JSONArray getAllModule(String moduleid, String parentMId, String curnum, String limitcount, String modulename);
	public JSONArray getAllfenpeiModule();
	public JSONArray getAllfenpeiModule(String flag);
	public JSONArray getAllweifenpeiModule(String flag);
	public void deleteModule(String moduleid);
	public void addModule(String moduleid,String fathermoduleid,String modulename,String modulelevel,String modulelink,String ismenu,String moduleby);
	public JSONArray selectModule(String moduleid,String fathermoduleid,String modulename);
	public void modulefenpei(String fatherid,String flag);
	public void jiechumodulefenpei(String fatherid,String flag);
	public void deleteselectmodule(String moduleid);
	public void insertfenpeimodule(String roleid,String moduleid);
	//查模块总个数
	public String getPageNum(String moduleid, String parentMId, String modulename);
}
