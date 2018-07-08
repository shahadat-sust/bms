package com.bms.service;

import java.sql.SQLException;

public class BmsSqlException extends SQLException {

	public BmsSqlException() {
		super();
	}
	
	public BmsSqlException(String message) {
		super(message);
	}
	
	public BmsSqlException(String message, Throwable ex) {
		super(message, ex);
	}
	
	public BmsSqlException(Throwable ex) {
		super(ex);
	}
	
}
