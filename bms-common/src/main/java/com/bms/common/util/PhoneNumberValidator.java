package com.bms.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator for checking a phone number. There are many different types so the
 * check is pretty loose.
 * 
 * <p>
 * For instance, the following are valid Australian formats for the same number:
 * <br />
 * 
 * 1. +61 2 1234 5678 (abbreviated international number)<br />
 * 2. 0011 61 2 1234 5678 (full international number) <br />
 * 3. 02 1234 5678 (expanded area code) <br />
 * 4. (02) 1234 5678 (expanded area code) <br />
 * 5. 1234 5678 (if calling in 02 areacode) <br />
 * <br />
 * 
 * plus all of the above without spaces.
 * </p>
 * 
 * <p>
 * Also added the following style numbers:<br />
 * 6. 111-222-3333 <br />
 * 7. 111.222.3333 <br />
 * 8. (111) 222-3333-444 <br />
 * 9. (111) 222.3333.444 <br />
 * <br />
 * 
 * plus combinations of . and - in the same numbers above
 * </p>
 * 
 * <p>
 * Now for numbers with extensions, add (\\w+)?+ 10. All of the above with any
 * letter/number combo afterwards but it must start with a letter and not
 * contain spaces and only occur once or not at all eg (123-456-789 x123, 123
 * 456 789 ext5)
 * 
 * </p>
 *
 */

public class PhoneNumberValidator {

	private static final List<String> PHONE_NUMBER_PATTERN = new ArrayList<>();

	static {
		/*
		 * EMAIL_PATTERN.add("\\+?([0-9]+|\\s+)+(\\w+)?+"); //matches 1,2,3,5 with 10
		 * EMAIL_PATTERN.add("\\({1}[0-9]+\\){1}([0-9]+|\\s+)+(\\w+)?+"); //matches 4 with 10 
		 * EMAIL_PATTERN.add("([0-9]+(\\-|\\.)?)+(\\w+)?+"); //matches 6, 7 with 10 
		 * EMAIL_PATTERN.add("\\({1}[0-9]+\\){1}([0-9]+|\\s+|\\-?|\\.?)+(\\w+)?+"); //matches 8,9 with 10
		 */ 
		//BD - Bangladesh
		PHONE_NUMBER_PATTERN.add("^(((\\+|00)?880)|0)(\\d){10}$");
		//IN - India
		PHONE_NUMBER_PATTERN.add("((\\+?)((0[ -]+)*|(91 )*)(\\d{12}|\\d{10}))|\\d{5}([- ]*)\\d{6}");
		//PK - Pakistan
		PHONE_NUMBER_PATTERN.add("^0?3[0-9]{2}[0-9]{7}$");
		//US - United States
		PHONE_NUMBER_PATTERN.add("^(?:(1\\-?)|(\\+1 ?))?\\(?\\d{3}\\)?[\\-\\.\\s]?\\d{3}[\\-\\.\\s]?\\d{4}$");
		//UK - United Kingdom			
		PHONE_NUMBER_PATTERN.add("^\\(?(?:(?:0(?:0|11)\\)?[\\s-]?\\(?|\\+)44\\)?[\\s-]?\\(?(?:0\\)?[\\s-]?\\(?)?|0)(?:\\d{2}\\)?[\\s-]?\\d{4}[\\s-]?\\d{4}|\\d{3}\\)?[\\s-]?\\d{3}[\\s-]?\\d{3,4}|\\d{4}\\)?[\\s-]?(?:\\d{5}|\\d{3}[\\s-]?\\d{3})|\\d{5}\\)?[\\s-]?\\d{4,5}|8(?:00[\\s-]?11[\\s-]?11|45[\\s-]?46[\\s-]?4\\d))(?:(?:[\\s-]?(?:x|ext\\.?\\s?|\\#)\\d+)?)$");
		//UAE - United Arab Emirates
		PHONE_NUMBER_PATTERN.add("^(((\\+|00)?971[\\s\\.-]?(\\(0\\)[\\s\\.-]?)?|0)(\\(5(0|2|5|6)\\)|5(0|2|5|6)|2|3|4|6|7|9)|60)([\\s\\.-]?[0-9]){7}");
		//GR - Germany
		PHONE_NUMBER_PATTERN.add("^(((((((00|\\+)49[ \\-/]?)|0)[1-9][0-9]{1,4})[ \\-/]?)|((((00|\\+)49\\()|\\(0)[1-9][0-9]{1,4}\\)[ \\-/]?))[0-9]{1,7}([ \\-/]?[0-9]{1,5})?)$");
		//CN - China
		PHONE_NUMBER_PATTERN.add("^((00|\\+)?(86(?:-| )))?((\\d{11})|(\\d{3}[- ]{1}\\d{4}[- ]{1}\\d{4})|((\\d{2,4}[- ]){1}(\\d{7,8}|(\\d{3,4}[- ]{1}\\d{4}))([- ]{1}\\d{1,4})?))$");
		//FR - France
		PHONE_NUMBER_PATTERN.add("^(?:(?:(?:\\+|00)33[ ]?(?:\\(0\\)[ ]?)?)|0){1}[1-9]{1}([ .-]?)(?:\\d{2}\\1?){3}\\d{2}$");
		//ES - Spain
		PHONE_NUMBER_PATTERN.add("^(?:(?:(?:\\+|00)34\\D?))?(?:5|6|7|8|9)(?:\\d\\D?){8}$");
		//BG - Bulgaria
		PHONE_NUMBER_PATTERN.add("^(\\+)?(0|359|00)(((700|900)[0-9]{5}|((800)[0-9]{5}|(800)[0-9]{4}))|(87|88|89)([0-9]{7})|((2[0-9]{7})|(([3-9][0-9])(([0-9]{6})|([0-9]{5})))))$");
		//BR - Brazil
		PHONE_NUMBER_PATTERN.add("^(([\\d]{4}[-.\\s]{1}[\\d]{2,3}[-.\\s]{1}[\\d]{2}[-.\\s]{1}[\\d]{2})|([\\d]{4}[-.\\s]{1}[\\d]{3}[-.\\s]{1}[\\d]{4})|((\\(?\\+?[0-9]{2}\\)?\\s?)?(\\(?\\d{2}\\)?\\s?)?\\d{4,5}[-.\\s]?\\d{4}))$");
		//CZ - Czech Republic 
		PHONE_NUMBER_PATTERN.add("^(((00)([- ]?)|\\+)(420)([- ]?))?((\\d{3})([- ]?)){2}(\\d{3})$");
		//MO - Morocco
		PHONE_NUMBER_PATTERN.add("^(?:(?:(?:\\+|00)212[\\s]?(?:[\\s]?\\(0\\)[\\s]?)?)|0){1}(?:5[\\s.-]?[2-3]|6[\\s.-]?[13-9]){1}[0-9]{1}(?:[\\s.-]?\\d{2}){3}$");
		//NL - India
		PHONE_NUMBER_PATTERN.add("^((\\+|00(\\s|\\s?\\-\\s?)?)31(\\s|\\s?\\-\\s?)?(\\(0\\)[\\-\\s]?)?|0)[1-9]((\\s|\\s?\\-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]$");
		//RO - Romania 	
		PHONE_NUMBER_PATTERN.add("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$");
		//RU - Russia 	
		PHONE_NUMBER_PATTERN.add("^((8|\\+7|007)[\\-\\.\\/ ]?)?([\\(\\/\\.]?\\d{3}[\\)\\/\\.]?[\\-\\.\\/ ]?)?[\\d\\-\\.\\/ ]{7,10}$");
		//SK - Slovakia 		
		PHONE_NUMBER_PATTERN.add("^(((00)([- ]?)|\\+)(421)([- ]?))?((\\d{3})([- ]?)){2}(\\d{3})$");
		//TH - Thailand 			
		PHONE_NUMBER_PATTERN.add("^0\\(?([6|8-9]{2})*-([0-9]{3})*-([0-9]{4})$");																									// 10
	}

	public boolean validate(final String number) {
		for (String r : PHONE_NUMBER_PATTERN) {
			Pattern p = Pattern.compile(r);
			if (p.matcher(number).matches()) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		PhoneNumberValidator validator = new PhoneNumberValidator();
		List<String> list = new ArrayList<>();

		for (String number : list) {
			if(number.contains("*****")) {
				System.out.println(number);
				continue;
			}
			System.out.println(number + " => " + validator.validate(number));
		}
	}
}

		/*//US
		list.add("********** US **********");
		list.add("1444-555-1234");
		list.add("246.555.8888");
		list.add("1235554567");
		list.add("(123)456-7890");
		list.add("123)456.0987");
		list.add("1-444-555-1234");
		list.add("14325678901");
		list.add("1(123)456-7890");
		list.add("+1 246.555-8888");
		list.add("+1 (123)456-7890");
		list.add("+1(123)456-7890");
		list.add("7334-12.111");//F
		list.add("v123.11.1111");//F
		list.add("(23)440.4448");//F
		list.add("123(456)7890");//F
		list.add("0800 333333 abcdef");//F
		list.add("********** UAE **********");
		list.add("00971501234567");
		list.add("+971521234567");
		list.add("971551234567");
		list.add("971 56 123 4567");
		list.add("971-50-123-4567");
		list.add("971.4.123.4567");
		list.add("+971 (0) 4 1234567");
		list.add("971 (56) 1234567");
		list.add("0551234567");
		list.add("021234567");
		list.add("600-540-000");
		list.add("********** BG **********");
		list.add("359895123456");
		list.add("0898111222");
		list.add("0886111222");
		list.add("0875111222");
		list.add("0899555555");
		list.add("359898111222");
		list.add("00898111222");
		list.add("+35998111222");
		list.add("098111222");
		list.add("090012900");
		list.add("070010007");
		list.add("070043256");
		list.add("35970045045");
		list.add("35970045666");
		list.add("08000700");
		list.add("080088001");
		list.add("080015333");
		list.add("028700000");
		list.add("030100000");
		list.add("03010070");
		list.add("03656745");
		list.add("********** BR **********");
		list.add("0800.000.00.00");
		list.add("0800-000-00-00");
		list.add("0800 000 00 00");
		list.add("0800-00-00-00");
		list.add("0800.00.00.00");
		list.add("0800 00 00 00");
		list.add("0800-000-0000");
		list.add("0800 000 0000");
		list.add("0800.000.0000");
		list.add("08000000000");
		list.add("1692089-4635");
		list.add("16920894635");
		list.add("16992089-4635");
		list.add("16 99202-4635");
		list.add("(16)99202-4635");
		list.add("(16)92089-4635");
		list.add("(16) 92089-4635");
		list.add("(15) 4343-4343");
		list.add("+55 15 3702-7523");
		list.add("(+55) 15 3702-7523");
		list.add("(+55)1537027523");
		list.add("(+55)(15)3702-7523");
		list.add("(+55) 15 3702-7523");
		list.add("(+55) 15 99202-7523");
		list.add("99202-4635");
		list.add("(16) 9208-4635");
		list.add("********** CN **********");
		list.add("18911111111");
		list.add("189 1111 1111");
		list.add("189-1111-1111");
		list.add("0086-18911111111");
		list.add("+86-18911111111");
		list.add("86-18911111111");
		list.add("0086 18911111111");
		list.add("+86 18911111111");
		list.add("86 18911111111");
		list.add("0086 189-1111-1111");
		list.add("+86 189-1111-1111");
		list.add("86 189-1111-1111");
		list.add("02011111111");
		list.add("020-11111111");
		list.add("020 11111111");
		list.add("020 1111 1111");
		list.add("020-1111-1111");
		list.add("0086 020 82803159");
		list.add("0086-020-82803159");
		list.add("+86 20 61302222-8866");
		list.add("+86 20 6130-2222-8866");
		list.add("+86 10 59081185");
		list.add("********** CZ **********");
		list.add("00420123456789");
		list.add("00420 123456789");
		list.add("00420 123 456 789");
		list.add("00 420 123 456 789");
		list.add("+420123456789");
		list.add("+420 123456789");
		list.add("+420 123 456 789");
		list.add("123456789");
		list.add("123 456 789");
		list.add("420123456789");//F
		list.add("420 123456789");//F
		list.add("420 123 456 789");//F
		list.add("00421123456789");//F
		list.add("00421 123456789");//F
		list.add("00421 123 456 789");//F
		list.add("00 421 123 456 789");//F
		list.add("+421123456789");//F
		list.add("+421 123456789");//F
		list.add("+421 123 456 789");//F
		list.add("********** GR **********");
		list.add("+49(89)123456");
		list.add("089-1234567");
		list.add("0891234567");
		list.add("0049-89-123456");
		list.add("089 123456-78");
		list.add("********** FR **********");
		list.add("0644444444");
		list.add("06 44 44 44 44");
		list.add("06-44-44-44-44");
		list.add("06.44.44.44.44");
		list.add("+33644444444");
		list.add("+336.44.44.44.44");
		list.add("+33 6.44.44.44.44");
		list.add("0033644444444");
		list.add("00336.44.44.44.44");
		list.add("0033 6.44.44.44.44");
		list.add("+33(0)644444444");
		list.add("+33 (0) 644444444");
		list.add("06 44.44-44.44");//F
		list.add("06 44 44-44.44");//F
		list.add("06 44 44-4444");//F
		list.add("06 44 44-4444");//F
		list.add("06444444444444");//F
		list.add("6644444444");//F
		list.add("06 44.44-44.44");//F
		list.add("+33 (0) 644444444");//F
		list.add("(0)644444444");//F
		list.add("+33-(0)-644444444");//F
		list.add("+33 (0)-644444444");//F
		list.add("+33-(0) 644444444");//F
		list.add("06.44.44.44.44.");//F
		list.add("********** IN **********");
		list.add("9999114011");//F
		list.add("+919911112341");//F
		list.add("+91 9415007327");//F
		list.add("03598245785");//F
		list.add("+911204312280");//F
		list.add("1302231221");//F
*/