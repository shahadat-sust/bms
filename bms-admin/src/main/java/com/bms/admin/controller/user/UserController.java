package com.bms.admin.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class UserController {

	private IUserService userService;
	
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public String getUserList(Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/userlist";
	}

	@Autowired
	@Qualifier("userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
