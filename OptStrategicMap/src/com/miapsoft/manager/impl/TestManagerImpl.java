package com.miapsoft.manager.impl;

import java.util.List;

import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.miapsoft.common.exception.BussinessException;
import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.TestManager;
import com.miapsoft.model.TbDimRegion;
/**
 * 测试实现类
 * <p>Title: TestManagerImpl.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
@Service("testManager")
public class TestManagerImpl extends AbstractManager implements TestManager{

	public JSONArray getRegions() {
		String sql = "select region_id,region_parent_id,lvl_id,region_name,region_desc,region_order from BASS_NP.TB_DIM_REGION where lvl_ID='2' fetch first 1 rows only";
//		String sql = "";
		try {
			List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
			RowMapper<TbDimRegion> rw =  ParameterizedBeanPropertyRowMapper.newInstance(TbDimRegion.class);
//			TbDimRegion list2 = this.getJdbcTemplate().queryForObject(sql,TbDimRegion.class);
			return JSONArray.fromObject(list);
		} catch (DataAccessException e) {
			throw new BussinessException("数据库发生错误，请稍后再试！",e);
		}
	}
	public int excuteSql(String sql) {
	    return this.getJdbcTemplate().update(sql);
	}
	public int[] excuteSql(String[] sql) {
	    return this.getJdbcTemplate().batchUpdate(sql);
	}
}
