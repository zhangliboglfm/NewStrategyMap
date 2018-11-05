package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface OnLineManager {

	/**
	 * @Date:2017-7-3
	 * @Description:宽带业务的数据
	 * @Param:@param onlineoroutline 线上线下类型
	 * @Param:@param curdate 时间
	 * @Param:@param brandtype 宽带类型
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	 */
	JSONArray broadbanddata(String onlineoroutline, String curdate,
			String brandtype);

	/**
	 * @Date:2017-7-3
	 * @Description:重点业务的办理
	 * @Param:@param onlineoroutline 线上线下的类型
	 * @Param:@param curdate  时间
	 * @Param:@return
	 * @return:JSONArray
	 * @author:user
	 */
	JSONArray keymanagement(String onlineoroutline, String curdate);

	/**
	 * @Date:2017-7-3
	 * @Description:业务办理量
	 * @Param:@param onlineoroutline 线上线下的类型
	 * @Param:@param curdate 时间
	 * @Param:@return
	 * @return:JSONArray
	 * @author:user
	 */
	JSONArray businessvloume(String onlineoroutline, String curdate);

	/**
	 * @Date:2017-7-3
	 * @Description:4g飞享套餐办理情况
	 * @Param:@param onlineoroutline 线上线下的类型
	 * @Param:@param curdate 时间
	 * @Param:@return
	 * @return:JSONArray
	 * @author:user
	 */
	JSONObject flyclass(String onlineoroutline, String curdate);

	/**
	 * @Date:2017-7-3
	 * @Description:其他业务
	 * @Param:@param onlineoroutline 线上线下的类型
	 * @Param:@param curdate 时间
	 * @Param:@param otherbusiness 其他业务的类型
	 * @Param:@return
	 * @return:JSONArray
	 * @author:user
	 */
	JSONArray otherbusiness(String onlineoroutline, String curdate,
			String otherbusiness);

	/**
	 * @Date:2017-7-3
	 * @Description:流量包
	 * @Param:@param onlineoroutline 线上线下的类型
	 * @Param:@param curdate 时间
	 * @Param:@param flowtype 流量包的类型
	 * @Param:@return
	 * @return:JSONArray
	 * @author:user
	 */
	JSONArray broadbaflowpacketnddata(String onlineoroutline, String curdate,
			String flowtype);

}
