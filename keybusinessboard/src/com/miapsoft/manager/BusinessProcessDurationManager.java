package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: BusinessProcessDurationManager.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-7-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */
public interface BusinessProcessDurationManager {
	
	/**
	 * 查询平均办理时长
	 * @Title:businessprocessduration
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-20
	 */
	public JSONObject businessprocessduration(String date);
	
	/**
	 * 查询不同业务办理时长
	 * @Title:processstimeconsumedifference
	 * @Description:TODO
	 * @param date
	 * @return
	 * @author:张立保
	 * @date:2017-7-20
	 */
	public JSONArray processstimeconsumedifference(String date);

}
