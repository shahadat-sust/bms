package com.bms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import com.bms.admin.listener.LoginUserAware;
import com.bms.service.data.user.UserData;

public abstract class BaseController implements LoginUserAware {

	private MessageSource messageSource;
	private UserData loginUserData;
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	@Autowired
	@Qualifier("messageSource")
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public UserData getLoginUserData() {
		return loginUserData;
	}

	@Override
	public void setLoginUserData(UserData loginUserData) {
		this.loginUserData = loginUserData;
	}

}
