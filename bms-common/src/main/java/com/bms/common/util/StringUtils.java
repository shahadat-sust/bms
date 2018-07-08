package com.bms.common.util;

import java.text.MessageFormat;

public class StringUtils {

	public static final String EMPTY = "";
	
	public static boolean isNullEmptyOrWhiteSpace(String value) {
	    if (value == null) {
	        return true;
	    }
	    for (int i = 0; i < value.length(); i++) {
	        if (!Character.isWhitespace(value.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static String getFormattedText(String message, Object[] array) {
		return new MessageFormat(message).format(array);
	}
	
}
