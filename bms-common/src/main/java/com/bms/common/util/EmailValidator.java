package com.bms.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validate(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		EmailValidator validator = new EmailValidator();
		List<String> list = new ArrayList<>();
		list.add("sourceengines@gmail.com");
		list.add("sa.gg@yopmail");
		
		for(String email : list) {
			System.out.println(email + " => " + validator.validate(email));
		}
	}
}