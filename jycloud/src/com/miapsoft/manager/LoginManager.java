package com.miapsoft.manager;

import net.sf.json.JSONObject;

public interface LoginManager {
	public JSONObject Login(String username);
	public int Login2(String username,String password);
}
