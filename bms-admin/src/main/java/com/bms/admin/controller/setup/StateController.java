package com.bms.admin.controller.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	@RequestMapping(value = "/fetch/{stateId}/{countryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> getGroupList(@PathVariable long stateId, @PathVariable long countryId) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			if(stateId > 0) {
				StateData stateData = stateService.getStateById(stateId);
				responseModel.addData(stateData);
			} else if(countryId > 0) {
				List<StateData> stateList = stateService.getStatesByCountryId(countryId);
				responseModel.addDatas(stateList);
			} else {
				List<StateData> stateList = stateService.getAllStates();
				responseModel.addDatas(stateList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> createGroup(@RequestBody StateData stateData) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			long stateId = stateService.create(stateData, getLoginUserData().getId());
			if(stateId > 0) {
				StateData data = stateService.getStateById(stateId);
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> updateGroup(@RequestBody StateData stateData) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			boolean status = stateService.update(stateData, getLoginUserData().getId());
			if(status) {
				StateData data = stateService.getStateById(stateData.getId());
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{stateId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<StateData> deleteGroup(@PathVariable long stateId) {
		ResponseModel<StateData> responseModel = new ResponseModel<StateData>();
		try {
			responseModel.setStatus(stateService.delete(stateId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
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
