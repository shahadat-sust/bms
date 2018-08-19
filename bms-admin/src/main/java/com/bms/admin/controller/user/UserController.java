package com.bms.admin.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.validator.util.privilegedactions.GetClassLoader;
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

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.CountryCodeModel;
import com.bms.admin.model.LabelValueModel;
import com.bms.admin.validator.UserValidator;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.ICountryService;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class UserController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private UserValidator userValidator;
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
		
		this.populateSelectOptions(model);
		
		return "user/usermodify";
	}
	
	@RequestMapping(value = "edituser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable long userId, Model model) throws BmsSqlException, BmsException {
		UserData userForm = userService.getUserDetailInfo(userId);
		userForm.setPassword("");
		model.addAttribute("userForm", userForm);
		model.addAttribute("isEditMode", true);
		
		this.populateSelectOptions(model);

		return "user/usermodify";
	}
	
	@RequestMapping(value = "isUsernameAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isUsernameAvailable(
			@RequestParam("userId") long userId, 
			@RequestParam("username") String username) throws BmsSqlException, BmsException {
		boolean status = false;
		try {
			status = userService.isUsernameAvailable(userId, username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}
	
	@RequestMapping(value = "isEmailAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isEmailAvailable(
			@RequestParam("userId") long userId, 
			@RequestParam("email") String email) throws BmsSqlException, BmsException {
		boolean status = false;
		try {
			status = userService.isEmailAvailableForUser(userId, email);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}
	
	@RequestMapping(value = "isPhoneNumberAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isPhoneNumberAvailable(
			@RequestParam("userId") long userId, 
			@RequestParam("code") String code, 
			@RequestParam("number") String number) throws BmsSqlException, BmsException {
		boolean status = false;
		try {
			status = userService.isPhoneNumberAvailableForUser(userId, code, number);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") UserData userForm, BindingResult result, Model model) throws BmsSqlException, BmsException {
		userValidator.validate(userForm, result);
		model.addAttribute("userForm", userForm);
		this.populateSelectOptions(model);

		if(result.hasErrors()) {
			userForm.setPassword("");
			userForm.setRepassword("");
			model.addAttribute("isEditMode", userForm.getId() > 0);
			return "user/usermodify";
		}
		
		if(userForm.getId() == 0) {
			userService.createAdminUser(userForm, getLoginUserData().getId());
		} else {
			userService.updateAdminUser(userForm, getLoginUserData().getId());
		}
		userForm.setPassword("");
		userForm.setRepassword("");
		model.addAttribute("isEditMode", true);
		
		return "user/usermodify";
	}
	
	private void populateSelectOptions(Model model) throws BmsSqlException, BmsException {
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
	}
	
	@RequestMapping(value = "deleteuser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("userId") long userId, Model model) throws BmsSqlException, BmsException {
		return "redirect:/listusers";
	}
	
	@Autowired
	@Qualifier("userValidator")
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
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
