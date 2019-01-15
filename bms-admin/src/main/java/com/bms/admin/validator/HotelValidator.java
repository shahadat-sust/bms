package com.bms.admin.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bms.service.data.provider.ProviderData;

@Component("hotelValidator")
public class HotelValidator implements Validator {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProviderData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProviderData providerData = (ProviderData) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title.required");
		
		if (providerData.getHotelData().getNumberOfFloor() <= 0) {
			errors.rejectValue("hotelData.numberOfFloor", "error.numberoffloor.required");
		}
		
		if (providerData.getHotelData().getCheckInTime() == null) {
			errors.rejectValue("hotelData.checkInTime", "error.checkintime.required");
		}
		
		if (providerData.getHotelData().getCheckOutTime() == null) {
			errors.rejectValue("hotelData.checkOutTime", "error.checkouttime.required");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddressDatas[0].email", "error.email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumberDatas[0].code", "error.countrycode.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumberDatas[0].number", "error.phonenumber.required");

		if (providerData.getPostalAddressDatas().get(0).getCityId() == 0) {
			errors.rejectValue("postalAddressDatas[0].cityId", "error.city.required");
		}
	}

}
