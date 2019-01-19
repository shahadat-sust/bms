package com.bms.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.model.CountryCodeModel;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;

@Controller
@RequestMapping(value = "/common")
@Scope("request")
public class CommonController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value = "/countrycodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CountryCodeModel> fetchCountryCodes() {
		ResponseModel<CountryCodeModel> responseModel = new ResponseModel<>();
		try {
			List<CountryCodeModel> countryCodeList = new ArrayList<>();
			for(String[] countryCode : Constants.COUNTRY_CODES) {
				countryCodeList.add(new CountryCodeModel(countryCode[0], countryCode[1], countryCode[2], countryCode[3]));
			}
			responseModel.setStatus(true);
			responseModel.addDatas(countryCodeList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
		return responseModel;
	}
	
}
