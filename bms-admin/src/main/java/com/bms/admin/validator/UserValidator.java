package com.bms.admin.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bms.common.BmsException;
import com.bms.common.util.CommonValidator;
import com.bms.common.util.StringUtils;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.user.IUserService;

@Component("userValidator")
public class UserValidator implements Validator {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IUserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserData userData = (UserData)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userProfileData.firstName", "error.firstname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userProfileData.lastName", "error.lastname.required");
		
		if(userData.getUserProfileData().getGender() == 0) {
			errors.rejectValue("userProfileData.gender", "error.gender.required");
		}
		
		if(userData.getId() == 0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.required");
			if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getUsername())
					&& !CommonValidator.isValidUsername(userData.getUsername())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.invalid");
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.required");
			if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getPassword())
					&& !CommonValidator.isValidPassword(userData.getPassword())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.invalid");
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repassword", "error.repassword.required");
			
			if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getPassword())
					&& !StringUtils.isNullEmptyOrWhiteSpace(userData.getRepassword())
					&& !userData.getPassword().equals(userData.getRepassword())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repassword", "error.confirmpassword.not.matched");
			}
		}

		if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getUsername())) {
			try {
				boolean isAvailable = userService.isUsernameAvailable(userData.getId(), userData.getUsername());
				if(!isAvailable) {
					errors.rejectValue("username", "error.username.not.available", new Object[] {userData.getUsername()}, 
							"Username is not available");
				}
			} catch (BmsSqlException | BmsException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddressDatas[0].email", "error.email.required");
		if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getEmailAddressDatas().get(0).getEmail())) {
			if(CommonValidator.isValidEmail(userData.getEmailAddressDatas().get(0).getEmail())) {
				try {
					boolean isAvailable = userService.isEmailAvailableForUser(userData.getId(), 
							userData.getEmailAddressDatas().get(0).getEmail());
					if(!isAvailable) {
						errors.rejectValue("emailAddressDatas[0].email", "error.email.not.available", new Object[] {userData.getEmailAddressDatas().get(0).getEmail()}, 
								"Email is not available");
					}
				} catch (BmsSqlException | BmsException e) {
					logger.error(e.getMessage(), e);
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddressDatas[0].email", "error.email.invalid");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumberDatas[0].code", "error.countrycode.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumberDatas[0].number", "error.phonenumber.required");

		if(!StringUtils.isNullEmptyOrWhiteSpace(userData.getPhoneNumberDatas().get(0).getCode())
				&& !StringUtils.isNullEmptyOrWhiteSpace(userData.getPhoneNumberDatas().get(0).getNumber())) {
			
			if (CommonValidator.isValidNumber(userData.getPhoneNumberDatas().get(0).getNumber())) {
				try {
					boolean isAvailable = userService.isPhoneNumberAvailableForUser(userData.getId(), 
							userData.getPhoneNumberDatas().get(0).getCode(), 
							userData.getPhoneNumberDatas().get(0).getNumber());
					if(!isAvailable) {
						errors.rejectValue("phoneNumberDatas[0].number", "error.phonenumber.not.available", 
								new Object[] {userData.getPhoneNumberDatas().get(0).getCode() + "-" + userData.getPhoneNumberDatas().get(0).getNumber()}, 
								"Phone number is not available");
					}
				} catch (BmsSqlException | BmsException e) {
					logger.error(e.getMessage(), e);
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumberDatas[0].number", "error.phonenumber.invalid");
			}
		}
		
		if(userData.getPostalAddressDatas().get(0).getCountryId() == 0) {
			errors.rejectValue("postalAddressDatas[0].countryId", "error.country.required");
		}
		
		if(userData.getUserGroupData().getGroupId() == 0) {
			errors.rejectValue("userGroupData.groupId", "error.group.required");
		}
		
		if(userData.getUserRoleData().getRoleId() == 0) {
			errors.rejectValue("userRoleData.roleId", "error.role.required");
		}
	}
	
	@Autowired
	@Qualifier("userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
