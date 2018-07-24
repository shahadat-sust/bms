package com.bms.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("request")
public class HomeController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	public String home() {
		return "dashboard";
	}
	
}
