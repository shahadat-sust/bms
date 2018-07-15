package com.bms.admin.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public abstract class BaseController implements MessageSourceAware {

	private MessageSource messageSource;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

}
