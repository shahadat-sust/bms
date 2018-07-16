package com.bms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

public abstract class BaseController {

	private MessageSource messageSource;
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	@Autowired
	@Qualifier("messageSource")
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
