package com.miapsoft.manager;

import net.sf.json.JSONArray;

/**
 * <p>Title: RegionManager.java</p>
 * <p>Description: TODO</p>
 * @author: ���ٻ�
 * @time: 2018-3-20
 * <p>Company: �������ݣ��������Ƽ����޹�˾</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface RegionManager {
	public JSONArray getCity();
	public JSONArray getCounty(String parentregionid);
}
