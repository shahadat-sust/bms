package com.bms.admin.controller.setup;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.soa.ICountryService;

@Controller
@RequestMapping(value = "/country")
@Scope("request")
public class CountryController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private ICountryService countryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String country(Model model) throws BmsSqlException, BmsException {
		List<CountryData> countryList = countryService.getAllCountries();
		model.addAttribute("countryList", countryList);
		return "setup/country";
	}
	
	@RequestMapping(value = "/fetch/{countryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CountryData> getCountryList(@PathVariable long countryId) {
		ResponseModel<CountryData> responseModel = new ResponseModel<CountryData>();
		try {
			if(countryId > 0) {
				CountryData countryData = countryService.getCountryById(countryId);
				responseModel.addData(countryData);
			} else {
				List<CountryData> countryList = countryService.getAllCountries();
				responseModel.addDatas(countryList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CountryData> createCountry(@RequestBody CountryData countryData) {
		ResponseModel<CountryData> responseModel = new ResponseModel<CountryData>();
		try {
			boolean isAvailable = countryService.isAvailable(countryData.getId(), countryData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			long countryId = countryService.create(countryData, getLoginUserData().getId());
			if(countryId > 0) {
				countryData.setId(countryId);
				responseModel.setStatus(true);
				responseModel.addData(countryData);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CountryData> updateCountry(@RequestBody CountryData countryData) {
		ResponseModel<CountryData> responseModel = new ResponseModel<CountryData>();
		try {
			boolean isAvailable = countryService.isAvailable(countryData.getId(), countryData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = countryService.update(countryData, getLoginUserData().getId());
			if(status) {
				CountryData data = countryService.getCountryById(countryData.getId());
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{countryId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CountryData> deleteCountry(@PathVariable long countryId) {
		ResponseModel<CountryData> responseModel = new ResponseModel<CountryData>();
		try {
			responseModel.setStatus(countryService.delete(countryId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("countryService")
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}
	
}
