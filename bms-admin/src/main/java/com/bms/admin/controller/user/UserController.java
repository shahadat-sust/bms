package com.bms.admin.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class UserController {

	private IUserService userService;
	
	@RequestMapping(value = "listusers", method = RequestMethod.GET)
	public String listUsers(Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/userlist";
	}
	
	@RequestMapping(value = "viewuser/{userId}", method = RequestMethod.GET)
	public String viewUser(@PathVariable long userId, Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/userinfo";
	}
	
	@RequestMapping(value = "createuser", method = RequestMethod.GET)
	public String createUser(Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "edituser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable long userId, Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String saveUser(Model model) throws BmsSqlException, BmsException {
		List<UserData> userList = userService.getAllUserDatas();
		model.addAttribute("userList", userList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "deleteuser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("userId") long userId, Model model) throws BmsSqlException, BmsException {
		return "redirect:/listusers";
	}

	@Autowired
	@Qualifier("userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
