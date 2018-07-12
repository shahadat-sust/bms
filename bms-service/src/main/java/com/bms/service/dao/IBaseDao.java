package com.bms.service.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IBaseDao {

	public void setDataSource(DataSource ds);

	public DataSource getDataSource();
	
	public JdbcTemplate getTemplete();

}
