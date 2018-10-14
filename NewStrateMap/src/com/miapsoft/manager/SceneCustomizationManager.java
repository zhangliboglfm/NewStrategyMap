package com.miapsoft.manager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


 /**
 * @ClassName:SceneCustomizationManager
 * @Description:战略地图场景定制
 * @author 周雷
 * @Date:2017-6-16
 */
public interface SceneCustomizationManager {
	
	
	/**
	 * @Date:2017-6-16
	 * @Description:兴趣点的指标查询
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	 */
	public JSONArray interestindex();

	/**
	 * @Date:2017-6-19
	 * @Description:查询兴趣点的地图数据
	 * @Param:@param regionId  地域
	 * @Param:@param dateType 时间的类型
	 * @Param:@param interestnumber1 兴趣点移动用户数的值
	 * @Param:@param interestnumber2兴趣点移动用户数的值
	 * @Param:@param interestarrylist 兴趣点类型的多选
	 * @Param:@param conditionidlist  筛选条件配置
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	  
	 */

	public JSONArray mapdatajson(String dishiregionId, String xianquregionId,
			String dateType, String interestnumber1, String interestnumber2,
			JSONArray interestarrylist, JSONArray conditionidlist, JSONObject extent
			);

	/**
	 * @Date:2017-6-21
	 * @Description:TODO
	 * @Param:@param userId 创建人Id
	 * @Param:@param phone  电话
	 * @Param:@param scencname 场景名称
	 * @Param:@param scencdesc 场景描述
	 * @Param:@param dataType  日期类型
	 * @Param:@param celltypelist 兴趣点
	 * @Param:@param cellattrulist 筛选条件
	 * @Param:@param datetime      当前时间
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	 * @param interestnumber2 兴趣点移动用户数
	 * @param interestnumber1 兴趣点移动用户数
	 */
	public int savedata(String userId, String phone, String scencname,
			String scencdesc, String dataType, JSONArray celltypelist,
			JSONArray cellattrulist, String datetime, String interestnumber1, String interestnumber2);

	

	/**
	 * @Date:2017-6-22
	 * @Description:兴趣点地图数据的查询
	 * @Param:@param dishiregionId 地市的id
	 * @Param:@param xianquregionId 县区的id
	 * @Param:@param pointertype    选中的兴趣点类型
	 * @Param:@param extent         当前的可视范围
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	 */
	public JSONArray pointmapdata(String dishiregionId, String xianquregionId,
			String pointertype, JSONObject extent);

	/**
	 * @Date:2017-6-22
	 * @Description:TODO
	 * @Param:@param useId 创建人
	 * @Param:@param pointsencename 场景名字
	 * @Param:@param pointphone  电话
	 * @Param:@param pointscencdesc 描述
	 * @Param:@param pointertype 兴趣点
	 * @Param:@param pointerarry 关注的兴趣点
	 * @Param:@return
	 * @return:JSONArray
	 * @author:周雷
	 */
	public int pointmapdata(String useId, String pointsencename,
			String pointphone, String pointscencdesc, String pointertype,
			JSONArray pointerarrylist);

	/**
	 * @Date:2017-6-23
	 * @Description:TODO
	 * @Param:@param senceid  场景的id
	 * @Param:@param eidttype 是筛选还是勾选点
	 * @Param:@return
	 * @return:int
	 * @author:user
	 */
	public JSONArray editdatasearch(String senceid, String eidttype);

	/**
	 * @Date:2017-6-24
	 * @Description:编辑保存数据
	 * @Param:@param senceid 场景Id
	 * @Param:@return
	 * @return:int
	 * @author:user
	 */
	public int editpointdelete(String senceid);
	
	
}
