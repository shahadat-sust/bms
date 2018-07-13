package com.bms.service.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDao implements IBaseDao {

	private DataSource dataSource = null;
	private JdbcTemplate template = null;
	
	@Autowired
	@Qualifier("dataSource")
	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	@Qualifier("template")
	@Override
	public JdbcTemplate getTemplete() {
		return template;
	}
}
