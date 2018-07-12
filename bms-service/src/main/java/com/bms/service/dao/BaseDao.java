package com.bms.service.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao implements IBaseDao {

	private DataSource dataSource = null;
	private JdbcTemplate template = null;
	
	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public JdbcTemplate getTemplete() {
		return template;
	}
}
