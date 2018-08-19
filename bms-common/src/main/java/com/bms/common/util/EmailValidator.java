package com.bms.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bms.common.Constants;

public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	public EmailValidator() {
		pattern = Pattern.compile(CommonValidator.EMAIL_PATTERN);
	}

	public boolean validate(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		EmailValidator validator = new EmailValidator();
		List<String> list = new ArrayList<>();
		list.add("sourceengines@gmail.com");
		list.add("sa.gg@yopmail.c.c");
		list.add("sa_yop@yopmail");
		
		for(String email : list) {
			System.out.println(email + " => " + validator.validate(email));
		}
	}
}