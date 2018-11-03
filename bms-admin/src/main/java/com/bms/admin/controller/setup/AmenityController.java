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
import com.bms.service.data.provider.AmenityData;
import com.bms.service.data.provider.ProviderTypeData;
import com.bms.service.soa.provider.IAmenityService;
import com.bms.service.soa.provider.IProviderTypeService;

@Controller
@RequestMapping(value = "/amenity")
@Scope("request")
public class AmenityController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IAmenityService amenityService;
	private IProviderTypeService providerTypeService;
	
	@ModelAttribute("providerTypeList")
	public List<LabelValueModel<Long>> getProviderTypeList() throws BmsSqlException, BmsException {
		List<LabelValueModel<Long>> labelValueModels = new ArrayList<LabelValueModel<Long>>();
		List<ProviderTypeData> providerTypeDatas = providerTypeService.getAllProviderTypes();
		if(providerTypeDatas != null && providerTypeDatas.size() > 0) {
			Collections.sort(providerTypeDatas, new Comparator<ProviderTypeData>() {
				@Override
				public int compare(ProviderTypeData o1, ProviderTypeData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			
			for(ProviderTypeData providerTypeData : providerTypeDatas) {
				labelValueModels.add(new LabelValueModel<Long>(providerTypeData.getName(), providerTypeData.getId()));
			}
		}
		return labelValueModels;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String amenity(Model model) throws BmsSqlException, BmsException {
		List<AmenityData> amenityList = amenityService.getAllAmenities();
		model.addAttribute("amenityList", amenityList);
		return "setup/amenity";
	}
	
	@RequestMapping(value = "/fetch/{amenityId}/{providerTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<AmenityData> getAmenityList(@PathVariable long amenityId, @PathVariable long providerTypeId) {
		ResponseModel<AmenityData> responseModel = new ResponseModel<AmenityData>();
		try {
			if(amenityId > 0) {
				AmenityData amenityData = amenityService.getAmenityById(amenityId);
				responseModel.addData(amenityData);
			} else if(providerTypeId > 0) {
				List<AmenityData> amenityList = amenityService.getAmenitiesByProviderTypeId(providerTypeId);
				responseModel.addDatas(amenityList);
			} else {
				List<AmenityData> amenityList = amenityService.getAllAmenities();
				responseModel.addDatas(amenityList);
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
	public @ResponseBody ResponseModel<AmenityData> createAmenity(@RequestBody AmenityData amenityData) {
		ResponseModel<AmenityData> responseModel = new ResponseModel<AmenityData>();
		try {
			boolean isAvailable = amenityService.isAvailable(amenityData.getId(), amenityData.getName(), amenityData.getProviderTypeId(), amenityData.getType());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and provider type" }, Locale.getDefault()));
				return responseModel;
			}
			
			long amenityId = amenityService.create(amenityData, getLoginUserData().getId());
			if(amenityId > 0) {
				AmenityData data = amenityService.getAmenityById(amenityId);
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
	public @ResponseBody ResponseModel<AmenityData> updateAmenity(@RequestBody AmenityData amenityData) {
		ResponseModel<AmenityData> responseModel = new ResponseModel<AmenityData>();
		try {
			boolean isAvailable = amenityService.isAvailable(amenityData.getId(), amenityData.getName(), amenityData.getProviderTypeId(), amenityData.getType());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name and provider type" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = amenityService.update(amenityData, getLoginUserData().getId());
			if(status) {
				AmenityData data = amenityService.getAmenityById(amenityData.getId());
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
	
	@RequestMapping(value = "/delete/{amenityId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<AmenityData> deleteAmenity(@PathVariable long amenityId) {
		ResponseModel<AmenityData> responseModel = new ResponseModel<AmenityData>();
		try {
			responseModel.setStatus(amenityService.delete(amenityId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("amenityService")
	public void setAmenityService(IAmenityService amenityService) {
		this.amenityService = amenityService;
	}
	
	@Autowired
	@Qualifier("providerTypeService")
	public void setProviderTypeService(IProviderTypeService providerTypeService) {
		this.providerTypeService = providerTypeService;
	}
}
