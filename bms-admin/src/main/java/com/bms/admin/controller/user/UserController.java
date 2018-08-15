package com.bms.admin.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.model.CountryCodeModel;
import com.bms.admin.model.LabelValueModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.ICountryService;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class UserController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IUserService userService;
	private ICountryService countryService;
	
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
		UserData userForm = new UserData();
		model.addAttribute("userForm", userForm);
		model.addAttribute("isEditMode", false);
		
		List<CountryData> countryList = countryService.getAllCountries();
		if(countryList != null && countryList.size() > 0) {
			Collections.sort(countryList, new Comparator<CountryData>() {
				@Override
				public int compare(CountryData o1, CountryData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		model.addAttribute("countryList", countryList);
		
		List<CountryCodeModel> countryCodeList = new ArrayList<>();
		for(String[] countryCode : Constants.COUNTRY_CODES) {
			countryCodeList.add(new CountryCodeModel(countryCode[0], countryCode[1], countryCode[2], countryCode[3]));
		}
		model.addAttribute("countryCodeList", countryCodeList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "edituser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable long userId, Model model) throws BmsSqlException, BmsException {
		UserData userForm = userService.getUserDetailInfo(userId);
		model.addAttribute("userForm", userForm);
		model.addAttribute("isEditMode", true);
		
		List<CountryData> countryList = countryService.getAllCountries();
		if(countryList != null && countryList.size() > 0) {
			Collections.sort(countryList, new Comparator<CountryData>() {
				@Override
				public int compare(CountryData o1, CountryData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		model.addAttribute("countryList", countryList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "isUsernameAlreadyExists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isUsernameAlreadyExists(@RequestParam("userId") long userId, @RequestParam("username") String username) throws BmsSqlException, BmsException {
		boolean status = false;
		try {
			status = userService.isUsernameAvailable(userId, username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") UserData userForm, BindingResult result) throws BmsSqlException, BmsException {
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
	
	@Autowired
	@Qualifier("countryService")
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}

}
