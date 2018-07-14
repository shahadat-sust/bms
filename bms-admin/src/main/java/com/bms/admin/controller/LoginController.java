package com.bms.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.admin.AppConstants;

@Controller
public class LoginController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		Object userSession = session.getAttribute(AppConstants.KEY_USER);
		if(userSession == null) {
			return "login";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(HttpSession session) {
		session.setAttribute(AppConstants.KEY_USER, "");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout(HttpSession session) {
		session.removeAttribute(AppConstants.KEY_USER);
		return "redirect:login";
	}

}
