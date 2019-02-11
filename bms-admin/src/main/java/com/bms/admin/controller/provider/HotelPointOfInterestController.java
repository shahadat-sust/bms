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
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.provider.ProviderPointOfInterestData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.provider.IProviderPointOfInterestService;

@Controller
@RequestMapping(value = "/hotelpointofinterest")
@Scope("request")
public class HotelPointOfInterestController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IProviderPointOfInterestService providerPointOfInterestService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) throws BmsSqlException, BmsException {
		ProviderAdminData defaultHotel = (ProviderAdminData) request.getSession().getAttribute(AppConstants.KEY_DEFAULT_HOTEL);
		List<ProviderPointOfInterestData> hotelPointOfInterestList = providerPointOfInterestService.getAllProviderPointOfInterestsByProviderId(
				defaultHotel.getProviderId());
		model.addAttribute("hotelPointOfInterestList", hotelPointOfInterestList);
		return "hotel/hotelpointofinterest";
	}
	
	@RequestMapping(value = "/fetch/{hotelPointOfInterestId}/{providerId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderPointOfInterestData> fetch(@PathVariable long hotelPointOfInterestId, @PathVariable long providerId, @PathVariable int sortOrder) {
		ResponseModel<ProviderPointOfInterestData> responseModel = new ResponseModel<ProviderPointOfInterestData>();
		try {
			if(hotelPointOfInterestId > 0) {
				ProviderPointOfInterestData providerPointOfInterestData = providerPointOfInterestService.getProviderPointOfInterestById(hotelPointOfInterestId);
				responseModel.addData(providerPointOfInterestData);
			} else { 
				List<ProviderPointOfInterestData> providerPointOfInterestList = null;
				if(providerId > 0) {
					providerPointOfInterestList = providerPointOfInterestService.getAllProviderPointOfInterestsByProviderId(providerId);
				} else {
					providerPointOfInterestList = new ArrayList<>();
				}
				
				if (providerPointOfInterestList != null && providerPointOfInterestList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(providerPointOfInterestList, new Comparator<ProviderPointOfInterestData>() {
							@Override
							public int compare(ProviderPointOfInterestData o1, ProviderPointOfInterestData o2) {
								return o1.getPointOfInterestName().compareTo(o2.getPointOfInterestName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(providerPointOfInterestList, new Comparator<ProviderPointOfInterestData>() {
							@Override
							public int compare(ProviderPointOfInterestData o1, ProviderPointOfInterestData o2) {
								return o2.getPointOfInterestName().compareTo(o1.getPointOfInterestName());
							}
						});
					}
				}
				
				responseModel.addDatas(providerPointOfInterestList);
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
	public @ResponseBody ResponseModel<ProviderPointOfInterestData> create(@RequestBody ProviderPointOfInterestData providerPointOfInterestData) {
		ResponseModel<ProviderPointOfInterestData> responseModel = new ResponseModel<ProviderPointOfInterestData>();
		try {
			long providerPointOfInterestId = providerPointOfInterestService.create(providerPointOfInterestData, getLoginUserData().getId());
			if(providerPointOfInterestId > 0) {
				ProviderPointOfInterestData data = providerPointOfInterestService.getProviderPointOfInterestById(providerPointOfInterestId);
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
	
	@RequestMapping(value = "/delete/{providerPointOfInterestId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemTypeData> delete(@PathVariable long providerPointOfInterestId) {
		ResponseModel<ItemTypeData> responseModel = new ResponseModel<ItemTypeData>();
		try {
			responseModel.setStatus(providerPointOfInterestService.delete(providerPointOfInterestId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/isAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isAvailable(@RequestParam long pointOfInterestId, @RequestParam long providerId) {
		boolean status = false;
		try {
			status = providerPointOfInterestService.isAvailable(pointOfInterestId, providerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    return status;
	}

	public IProviderPointOfInterestService getProviderPointOfInterestService() {
		return providerPointOfInterestService;
	}

	@Autowired
	@Qualifier("providerPointOfInterestService")
	public void setProviderPointOfInterestService(IProviderPointOfInterestService providerPointOfInterestService) {
		this.providerPointOfInterestService = providerPointOfInterestService;
	}

}
