package com.bms.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bms.admin.AppConstants;

@Controller
public class LoginController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/login")
	public String login(HttpSession session) {
		Object userSession = session.getAttribute(AppConstants.KEY_USER);
		if(userSession == null) {
			return "login";
		} else {
			return "redirect:home";
		}
	}
	
	@RequestMapping(value = "/doLogin")
	public String doLogin() {
		return "redirect:home";
	}

}
