package com.bms.admin.controller.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.LabelValueModel;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.data.CityData;
import com.bms.service.soa.ICityService;
import com.bms.service.soa.ICountryService;

@Controller
@RequestMapping(value = "/city")
@Scope("request")
public class CityController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private ICityService cityService;
	private ICountryService countryService;
	
	@ModelAttribute("countryList")
	public List<LabelValueModel<Long>> getCountryList() throws BmsSqlException, BmsException {
		List<LabelValueModel<Long>> labelValueModels = new ArrayList<LabelValueModel<Long>>();
		List<CountryData> countryDatas = countryService.getAllCountries();
		if(countryDatas != null && countryDatas.size() > 0) {
			Collections.sort(countryDatas, new Comparator<CountryData>() {
				@Override
				public int compare(CountryData o1, CountryData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			
			for(CountryData countryData : countryDatas) {
				labelValueModels.add(new LabelValueModel<Long>(countryData.getName(), countryData.getId()));
			}
		}
		return labelValueModels;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String city(Model model) throws BmsSqlException, BmsException {
		List<CityData> cityList = cityService.getAllCities();
		model.addAttribute("cityList", cityList);
		return "setup/city";
	}
	
	@RequestMapping(value = "/fetch/{cityId}/{countryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CityData> getGroupList(@PathVariable long cityId, @PathVariable long countryId) {
		ResponseModel<CityData> responseModel = new ResponseModel<CityData>();
		try {
			if(cityId > 0) {
				CityData cityData = cityService.getCityById(cityId);
				responseModel.addData(cityData);
			} else if(countryId > 0) {
				List<CityData> cityList = cityService.getCitiesByCountryId(countryId);
				responseModel.addDatas(cityList);
			} else {
				List<CityData> cityList = cityService.getAllCities();
				responseModel.addDatas(cityList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CityData> createGroup(@RequestBody CityData cityData) {
		ResponseModel<CityData> responseModel = new ResponseModel<CityData>();
		try {
			long cityId = cityService.create(cityData, getLoginUserData().getId());
			if(cityId > 0) {
				CityData data = cityService.getCityById(cityId);
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
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CityData> updateGroup(@RequestBody CityData cityData) {
		ResponseModel<CityData> responseModel = new ResponseModel<CityData>();
		try {
			boolean status = cityService.update(cityData, getLoginUserData().getId());
			if(status) {
				CityData data = cityService.getCityById(cityData.getId());
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
	
	@RequestMapping(value = "/delete/{cityId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<CityData> deleteGroup(@PathVariable long cityId) {
		ResponseModel<CityData> responseModel = new ResponseModel<CityData>();
		try {
			responseModel.setStatus(cityService.delete(cityId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("cityService")
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
	@Autowired
	@Qualifier("countryService")
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}
}

