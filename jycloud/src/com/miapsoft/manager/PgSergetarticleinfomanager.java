package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface PgSergetarticleinfomanager {
	public JSONArray getmorearticle(int page);
	public String getcoverphoto(String articleid);
	public JSONArray getcoverphoto();
	public int getregisterinfo(String loginid, String password, String qqnum,
			String vxnum, String wbnum);
	public int getlogininfo(String loginid, String password, String qqnum,
			String vxnum, String wbnum);
}
