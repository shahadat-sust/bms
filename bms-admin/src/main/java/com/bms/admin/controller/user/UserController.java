package com.bms.admin.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.StateData;
import com.bms.service.data.user.UserData;
import com.bms.service.data.user.UserProfileData;
import com.bms.service.soa.ICountryService;
import com.bms.service.soa.user.IUserService;

@Controller
@Scope("request")
public class UserController {

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
		List<EmailAddressData> emailAddressDatas = new ArrayList<>();
		emailAddressDatas.add(new EmailAddressData());
		List<PhoneNumberData> phoneNumberDatas = new ArrayList<>();
		phoneNumberDatas.add(new PhoneNumberData());
		List<PostalAddressData> postalAddressDatas = new ArrayList<>();
		postalAddressDatas.add(new PostalAddressData());
		userForm.setUserProfileData(new UserProfileData());
		userForm.setEmailAddressDatas(emailAddressDatas);
		userForm.setPhoneNumberDatas(phoneNumberDatas);
		userForm.setPostalAddressDatas(postalAddressDatas);
		model.addAttribute("userForm", userForm);
		
		List<CountryData> countryList = countryService.getAllCountries();
		model.addAttribute("countryList", countryList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "edituser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable long userId, Model model) throws BmsSqlException, BmsException {
		UserData userForm = userService.getUserDetailInfo(userId);
		if(userForm.getEmailAddressDatas().size() == 0) {
			EmailAddressData emailAddressData = new EmailAddressData();
			emailAddressData.setUserId(userForm.getId());
			userForm.getEmailAddressDatas().add(emailAddressData);
		}
		if(userForm.getPhoneNumberDatas().size() == 0) {
			PhoneNumberData phoneNumberData = new PhoneNumberData();
			phoneNumberData.setUserId(userForm.getId());
			userForm.getPhoneNumberDatas().add(phoneNumberData);
		}
		if(userForm.getPostalAddressDatas().size() == 0) {
			PostalAddressData postalAddressData = new PostalAddressData();
			postalAddressData.setUserId(userForm.getId());
			userForm.getPostalAddressDatas().add(postalAddressData);
		}
		model.addAttribute("userForm", userForm);
		List<CountryData> countryList = countryService.getAllCountries();
		model.addAttribute("countryList", countryList);
		return "user/usermodify";
	}
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") UserData userForm) throws BmsSqlException, BmsException {
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
