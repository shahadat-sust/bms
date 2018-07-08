package com.bms.common;

public class BmsException extends Exception {
	
	public BmsException() {
		super();
	}
	
	public BmsException(String message) {
		super(message);
	}
	
	public BmsException(String message, Throwable ex) {
		super(message, ex);
	}

	public BmsException(Exception ex) {
		super(ex);
	}
	
}
