package com.miapsoft.manager;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
/**
 * 数据库操作抽象类
 * <p>Title: AbstractManager.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class AbstractManager extends JdbcDaoSupport{
	@Resource
	public void setDataSource(Object dataSource) {
		DataSource ds = (DataSource)dataSource;
		super.setDataSource(ds);
	}
}
