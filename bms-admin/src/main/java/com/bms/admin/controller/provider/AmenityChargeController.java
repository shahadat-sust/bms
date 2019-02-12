package com.bms.admin.controller.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.AppConstants;
import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.AmenityChargeData;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.soa.provider.IAmenityChargeService;

@Controller
@RequestMapping(value = "/amenitycharge")
@Scope("request")
public class AmenityChargeController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IAmenityChargeService amenityChargeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) throws BmsSqlException, BmsException {
		ProviderAdminData defaultHotel = (ProviderAdminData) request.getSession().getAttribute(AppConstants.KEY_DEFAULT_HOTEL);
		List<AmenityChargeData> amenityChargeList = amenityChargeService.getAllAmenityChargesByProviderId(defaultHotel.getProviderId());
		model.addAttribute("amenityChargeList", amenityChargeList);
		return "hotel/amenitycharge";
	}
	
	@RequestMapping(value = "/fetch/{amenityChargeId}/{providerId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<AmenityChargeData> fetch(@PathVariable long amenityChargeId, @PathVariable long providerId, @PathVariable int sortOrder) {
		ResponseModel<AmenityChargeData> responseModel = new ResponseModel<AmenityChargeData>();
		try {
			if(amenityChargeId > 0) {
				AmenityChargeData amenityChargeData = amenityChargeService.getAmenityChargeById(amenityChargeId);
				responseModel.addData(amenityChargeData);
			} else { 
				List<AmenityChargeData> amenityChargeList = null;
				if(providerId > 0) {
					amenityChargeList = amenityChargeService.getAllAmenityChargesByProviderId(providerId);
				} else {
					amenityChargeList = new ArrayList<>();
				}
				
				if (amenityChargeList != null && amenityChargeList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(amenityChargeList, new Comparator<AmenityChargeData>() {
							@Override
							public int compare(AmenityChargeData o1, AmenityChargeData o2) {
								return o1.getAmenityName().compareTo(o2.getAmenityName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(amenityChargeList, new Comparator<AmenityChargeData>() {
							@Override
							public int compare(AmenityChargeData o1, AmenityChargeData o2) {
								return o2.getAmenityName().compareTo(o1.getAmenityName());
							}
						});
					}
				}
				
				responseModel.addDatas(amenityChargeList);
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
	public @ResponseBody ResponseModel<AmenityChargeData> create(@RequestBody AmenityChargeData amenityChargeData) {
		ResponseModel<AmenityChargeData> responseModel = new ResponseModel<AmenityChargeData>();
		try {
			long amenityChargeId = amenityChargeService.create(amenityChargeData, getLoginUserData().getId());
			if(amenityChargeId > 0) {
				AmenityChargeData data = amenityChargeService.getAmenityChargeById(amenityChargeId);
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
	public @ResponseBody ResponseModel<AmenityChargeData> update(@RequestBody AmenityChargeData amenityChargeData) {
		ResponseModel<AmenityChargeData> responseModel = new ResponseModel<AmenityChargeData>();
		try {
			boolean status = amenityChargeService.update(amenityChargeData, getLoginUserData().getId());
			if(status) {
				AmenityChargeData data = amenityChargeService.getAmenityChargeById(amenityChargeData.getId());
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
	
	@RequestMapping(value = "/delete/{amenityChargeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<AmenityChargeData> delete(@PathVariable long amenityChargeId) {
		ResponseModel<AmenityChargeData> responseModel = new ResponseModel<AmenityChargeData>();
		try {
			responseModel.setStatus(amenityChargeService.delete(amenityChargeId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/isAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isAvailable(@RequestParam long amenityChargeId, @RequestParam long amenityId, @RequestParam long providerId) {
		boolean status = false;
		try {
			status = amenityChargeService.isAvailable(amenityChargeId, amenityId, providerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    return status;
	}

	public IAmenityChargeService getAmenityChargeService() {
		return amenityChargeService;
	}

	@Autowired
	@Qualifier("amenityChargeService")
	public void setAmenityChargeService(IAmenityChargeService amenityChargeService) {
		this.amenityChargeService = amenityChargeService;
	}

}
