package com.bms.common.util;

public class CommonValidator {

	public static final String USERNAME_PATTERN =
			"^[a-zA-Z0-9]+$";
	public static final String PASSWORD_REGEX_1 = 
			"^[a-zA-Z0-9!@#$%^&*+=]+$";
	public static final String PASSWORD_REGEX_2 = 
			"(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,}";
	public static final String EMAIL_PATTERN =
			"^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$";
	public static final String NUMBER_PATTERN =
			"^\\d+$";
	
	public static boolean isValidUsername(String username) {
		return username.matches(USERNAME_PATTERN);
	}
	
	public static boolean isValidPassword(String password) {
		return password.matches(PASSWORD_REGEX_1) && password.matches(PASSWORD_REGEX_2);
	}

	public static boolean isValidEmail(String email) {
		return new EmailValidator().validate(email);
	}
	
	public static boolean isValidNumber(String number) {
		return number.matches(NUMBER_PATTERN);
	}
	
}
