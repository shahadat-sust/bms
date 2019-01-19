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
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;
import com.bms.service.data.StateData;
import com.bms.service.soa.ICountryService;
import com.bms.service.soa.IStateService;

@Controller
@RequestMapping(value = "/state")
@Scope("request")
public class StateController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IStateService stateService;
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
	public String state(Model model) throws BmsSqlException, BmsException {
		List<StateData> stateList = stateService.getAllStates();
		model.addAttribute("stateList", stateList);
		return "setup/state";
	}
	
	@RequestMapping(value = "/fetch/{stateId}/{countryId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> getStateList(@PathVariable long stateId, @PathVariable long countryId, @PathVariable int sortOrder) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			if(stateId > 0) {
				StateData stateData = stateService.getStateById(stateId);
				responseModel.addData(stateData);
			} else { 
				List<StateData> stateList = null;
				if(countryId > 0) {
					stateList = stateService.getStatesByCountryId(countryId);
				} else {
					stateList = stateService.getAllStates();
				}
				
				if (stateList != null && stateList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(stateList, new Comparator<StateData>() {
							@Override
							public int compare(StateData o1, StateData o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(stateList, new Comparator<StateData>() {
							@Override
							public int compare(StateData o1, StateData o2) {
								return o2.getName().compareTo(o1.getName());
							}
						});
					}
				}
				
				responseModel.addDatas(stateList);
			}
			responseModel.setStatus(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> createState(@RequestBody StateData stateData) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			boolean isAvailable = stateService.isAvailable(stateData.getId(), stateData.getName(), stateData.getCountryId());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and country" }, Locale.getDefault()));
				return responseModel;
			}
			
			long stateId = stateService.create(stateData, getLoginUserData().getId());
			if(stateId > 0) {
				StateData data = stateService.getStateById(stateId);
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> updateState(@RequestBody StateData stateData) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			boolean isAvailable = stateService.isAvailable(stateData.getId(), stateData.getName(), stateData.getCountryId());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and country" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = stateService.update(stateData, getLoginUserData().getId());
			if(status) {
				StateData data = stateService.getStateById(stateData.getId());
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{stateId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> deleteState(@PathVariable long stateId) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			responseModel.setStatus(stateService.delete(stateId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("stateService")
	public void setStateService(IStateService stateService) {
		this.stateService = stateService;
	}
	
	@Autowired
	@Qualifier("countryService")
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}
}
