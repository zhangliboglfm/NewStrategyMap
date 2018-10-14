package com.miapsoft.util;

import java.util.ResourceBundle;
/**
 * 常量配置信息类
 * @author jyyr
 *
 */
public class ConfigInfo {
	//读取config.properties配置信息
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	/**
	 * 登录地址
	 */
	public static final String LOGIN_URL = bundle.getString("LoginUrl");
}
