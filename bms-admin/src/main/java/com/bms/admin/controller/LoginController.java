package com.bms.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bms.admin.AppConstants;
import com.bms.common.BmsException;
import com.bms.common.util.StringUtils;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.auth.IAuthenticationService;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class LoginController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IAuthenticationService authenticationService;
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		UserData userData = (UserData)session.getAttribute(AppConstants.KEY_USER);
		if(userData == null) {
			return "login";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("userForm") UserData userForm, BindingResult result, HttpSession session) throws BmsSqlException, BmsException {
		
		if(StringUtils.isNullEmptyOrWhiteSpace(userForm.getUsername()) 
				|| StringUtils.isNullEmptyOrWhiteSpace(userForm.getPassword())) {
			result.reject("error.username.password.required");
			return "login";
		} else if(StringUtils.isNullEmptyOrWhiteSpace(userForm.getUsername())) {
			result.reject("error.username.required");
			return "login";
		} else if(StringUtils.isNullEmptyOrWhiteSpace(userForm.getPassword())) {
			result.reject("error.password.required");
			return "login";
		}

		long userId = authenticationService.getAuthorizedAdmin(userForm.getUsername(), userForm.getPassword());
		if(userId == 0) {
			result.reject("error.invalid.credentials");
			return "login";
		}
		
		UserData userData = userService.getUserDetailInfo(userId);
		if(userData == null) {
			result.reject("error.user.not.exist");
			return "login";
		}
		
		session.setAttribute(AppConstants.KEY_USER, userData);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout(HttpSession session) {
		session.removeAttribute(AppConstants.KEY_USER);
		return "redirect:login";
	}

	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	@Autowired
	@Qualifier("authenticationService")
	public void setAuthenticationService(IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	@Qualifier("userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
