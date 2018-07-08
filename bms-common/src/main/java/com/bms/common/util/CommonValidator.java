package com.bms.common.util;

public class CommonValidator {

	public static String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	
	public static boolean isValidPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}
	
	public static boolean isValidEmail(String email) {
		return new EmailValidator().validate(email);
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
		return new PhoneNumberValidator().validate(phoneNumber);
	}
	
}
