package com.bms.admin.controller.provider;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.CountryCodeModel;
import com.bms.admin.model.ResponseModel;
import com.bms.admin.model.SearchModel;
import com.bms.admin.validator.HotelValidator;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.common.SetupConstants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CityData;
import com.bms.service.data.CountryData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.StateData;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.provider.ProviderData;
import com.bms.service.data.user.UserData;
import com.bms.service.soa.ICityService;
import com.bms.service.soa.ICountryService;
import com.bms.service.soa.IStateService;
import com.bms.service.soa.provider.IHotelProviderService;

@Controller
@Scope("request")
public class HotelController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private HotelValidator hotelValidator;
	private ICountryService countryService;
	private IStateService stateService;
	private ICityService cityService;
	private IHotelProviderService hotelProviderService;
	
	@RequestMapping(value = "listhotels", method = RequestMethod.GET)
	public String listHotels(Model model) throws BmsSqlException, BmsException {
		List<ProviderData> hotelList = hotelProviderService.getAllHotelDatas();
		model.addAttribute("hotelList", hotelList);
		return "hotel/hotellist";
	}

	@RequestMapping(value = "viewhotel/{providerId}", method = RequestMethod.GET)
	public String viewHotel(@PathVariable long providerId, Model model) throws BmsSqlException, BmsException {
		ProviderData providerData = hotelProviderService.getHotelDetailInfo(providerId);
		model.addAttribute("providerData", providerData);
		return "hotel/hotelinfo";
	}
	
	@RequestMapping(value = "createhotel", method = RequestMethod.GET)
	public String createHotel(Model model) throws BmsSqlException, BmsException {
		ProviderData hotelForm = new ProviderData();
		hotelForm.setProviderTypeId(SetupConstants.PROVIDER_TYPE_HOTEL);
		model.addAttribute("hotelForm", hotelForm);
		model.addAttribute("isEditMode", false);
		this.populateSelectOptions(model, hotelForm);
		
		return "hotel/hotelmodify";
	}
	
	@RequestMapping(value = "edithotel/{providerId}", method = RequestMethod.GET)
	public String editHotel(@PathVariable long providerId, Model model) throws BmsSqlException, BmsException {
		ProviderData hotelForm = hotelProviderService.getHotelDetailInfo(providerId); 
		model.addAttribute("hotelForm", hotelForm);
		model.addAttribute("isEditMode", true);
		this.populateSelectOptions(model, hotelForm);
		return "hotel/hotelmodify";
	}
	
	@RequestMapping(value = "savehotel", method = RequestMethod.POST)
	public String saveHotel(@ModelAttribute("hotelForm") ProviderData hotelForm, BindingResult result, Model model) throws BmsSqlException, BmsException {
		hotelValidator.validate(hotelForm, result);
		boolean isEditMode = hotelForm.getId() > 0;
		
		model.addAttribute("hotelForm", hotelForm);
		this.populateSelectOptions(model, hotelForm);
		
		if (result.hasErrors()) {
			model.addAttribute("isEditMode", isEditMode);
			return "hotel/hotelmodify";
		}
		
		try {
			if (isEditMode) {
				hotelProviderService.updateHotel(hotelForm, getLoginUserData().getId());
				model.addAttribute("successMsg", getMessageSource().getMessage("confirm.update.success", new Object[] {"Hotel"}, Locale.getDefault()));
			} else {
				hotelProviderService.createHotel(hotelForm, getLoginUserData().getId());
				model.addAttribute("successMsg", getMessageSource().getMessage("confirm.create.success", new Object[] {"Hotel"}, Locale.getDefault()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			if (isEditMode) {
				model.addAttribute("failedMsg", getMessageSource().getMessage("confirm.update.failed", new Object[] {"Hotel"}, Locale.getDefault()));
			} else {
				model.addAttribute("failedMsg", getMessageSource().getMessage("confirm.create.failed", new Object[] {"Hotel"}, Locale.getDefault()));
			}
		}
		
		model.addAttribute("isEditMode", true);
		return "hotel/hotelmodify";
	}
	
	@RequestMapping(value = "deletehotel", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("providerId") long providerId, Model model) throws BmsSqlException, BmsException {
		try {
			hotelProviderService.deleteHotel(providerId, getLoginUserData().getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("failedMsg", getMessageSource().getMessage("confirm.delete.failed", new Object[] {"hotel"}, Locale.getDefault()));
			return listHotels(model);
		}
		return "redirect:/listhotels";
	}
	
	@RequestMapping(value = "searchhotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<SearchModel> searchHotel(@RequestParam("title") String title, @RequestParam("starRating") int starRating, 
			@RequestParam("countryId") long countryId, @RequestParam("cityId") int cityId) {
		ResponseModel<SearchModel> responseModel = new ResponseModel<>();
		try {
			List<ProviderData> providerDatas = hotelProviderService.getSearchHotel(title, starRating, countryId, cityId);
			List<SearchModel> searchDatas = new ArrayList<>();
			if (providerDatas != null && providerDatas.size() > 0) {
				for (ProviderData providerData : providerDatas) {
					SearchModel searchData = new SearchModel();
					searchData.setProviderId(providerData.getId());
					searchData.setProviderTypeId(providerData.getProviderTypeId());
					searchData.setTitle(providerData.getTitle());
					searchData.setStarRating(providerData.getHotelData().getStarRating());

					searchData.setAddress(providerData.getPostalAddressDatas().get(0).getLine1());
					searchData.setCountryId(providerData.getPostalAddressDatas().get(0).getCountryId());
					searchData.setCountryName(providerData.getPostalAddressDatas().get(0).getCountryName());
					searchData.setCityId(providerData.getPostalAddressDatas().get(0).getCityId());
					searchData.setCityName(providerData.getPostalAddressDatas().get(0).getCityName());

					searchDatas.add(searchData);
				}

				Collections.sort(searchDatas, new Comparator<SearchModel>() {
					@Override
					public int compare(SearchModel o1, SearchModel o2) {
						return o1.getTitle().compareTo(o2.getTitle());
					}
				});
			}
			responseModel.addDatas(searchDatas);
			responseModel.setStatus(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
		return responseModel;
	}
	
	private void populateSelectOptions(Model model, ProviderData hotelForm) throws BmsSqlException, BmsException {
		List<CountryData> countryList = countryService.getAllCountries();
		if(countryList != null && countryList.size() > 0) {
			Collections.sort(countryList, new Comparator<CountryData>() {
				@Override
				public int compare(CountryData o1, CountryData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		model.addAttribute("countryList", countryList);
		
		List<StateData> stateList = new ArrayList<>();
		List<CityData> cityList = new ArrayList<>();
		if (hotelForm.getPostalAddressDatas() != null && hotelForm.getPostalAddressDatas().size() > 0) {
			PostalAddressData postalAddressData = hotelForm.getPostalAddressDatas().get(0);
			if (postalAddressData.getCountryId() > 0) {
				stateList = stateService.getStatesByCountryId(postalAddressData.getCountryId());
			}
			if (postalAddressData.getStateId() > 0) {
				cityList = cityService.getCitiesByStateId(postalAddressData.getStateId());
			}
		}
		model.addAttribute("stateList", stateList);
		model.addAttribute("cityList", cityList);
		
		List<CountryCodeModel> countryCodeList = new ArrayList<>();
		for(String[] countryCode : Constants.COUNTRY_CODES) {
			countryCodeList.add(new CountryCodeModel(countryCode[0], countryCode[1], countryCode[2], countryCode[3]));
		}
		model.addAttribute("countryCodeList", countryCodeList);
	}
	
	@Autowired
	@Qualifier("hotelValidator")
	public void setHotelValidator(HotelValidator hotelValidator) {
		this.hotelValidator = hotelValidator;
	}
	
	@Autowired
	@Qualifier("countryService")
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}
	
	@Autowired
	@Qualifier("stateService")
	public void setStateService(IStateService stateService) {
		this.stateService = stateService;
	}
	
	@Autowired
	@Qualifier("cityService")
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	@Autowired
	@Qualifier("hotelProviderService")
	public void setHotelProviderService(IHotelProviderService hotelProviderService) {
		this.hotelProviderService = hotelProviderService;
	}

}
