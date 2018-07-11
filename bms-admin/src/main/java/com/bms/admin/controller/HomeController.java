package com.bms.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController implements MessageSourceAware {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private MessageSource messageSource;
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	public String home() {
		logger.debug("home");
		return "home";
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
