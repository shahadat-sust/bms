package com.bms.admin.controller.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.soa.provider.ProviderAdminService;

@Controller
@Scope("request")
public class HotelAdminController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	@Qualifier("providerAdminService")
	private ProviderAdminService providerAdminService;
	
	@RequestMapping(value = "hoteladmin", method = RequestMethod.GET)
	public String hoteladmin(Model model) throws BmsSqlException, BmsException {
		return "hotel/hoteladmin";
	}
	
	@RequestMapping(value = "assignedhotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderAdminData> getAdminHotelList() throws BmsSqlException, BmsException {
		ResponseModel<ProviderAdminData> responseModel = new ResponseModel<>();
		try {
			List<ProviderAdminData> assignedHotels = providerAdminService.getAssignedProviders(getLoginUserData().getId(), 
					getLoginUserData().getUserGroupData().getGroupId(), 
					getLoginUserData().getUserRoleData().getRoleId());
			if(assignedHotels != null && assignedHotels.size() > 0) {
				Collections.sort(assignedHotels, new Comparator<ProviderAdminData>() {
					@Override
					public int compare(ProviderAdminData o1, ProviderAdminData o2) {
						return o1.getTitle().compareTo(o2.getTitle());
					}
				});
			}
			responseModel.addDatas(assignedHotels);
			responseModel.setStatus(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
		return responseModel;
	}
	
	@RequestMapping(value = "assignablehotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderAdminData> getAdminHotelList(@RequestParam("userId") long userId) throws BmsSqlException, BmsException {
		ResponseModel<ProviderAdminData> responseModel = new ResponseModel<>();
		try {
			List<ProviderAdminData> assignableHotels = providerAdminService.getAssignableProviders(userId, 
					getLoginUserData().getId(), 
					getLoginUserData().getUserGroupData().getGroupId(), 
					getLoginUserData().getUserRoleData().getRoleId());
			if(assignableHotels != null && assignableHotels.size() > 0) {
				Collections.sort(assignableHotels, new Comparator<ProviderAdminData>() {
					@Override
					public int compare(ProviderAdminData o1, ProviderAdminData o2) {
						return o1.getTitle().compareTo(o2.getTitle());
					}
				});
			}
			responseModel.addDatas(assignableHotels);
			responseModel.setStatus(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
		return responseModel;
	}

	@RequestMapping(value = "assignhotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<?> assignHotel(@RequestBody Map<String, String> params) throws BmsSqlException, BmsException {
		ResponseModel<?> responseModel = new ResponseModel<Object>();
		try {
			responseModel.setStatus(providerAdminService.setProviderForAdmin(Long.parseLong(params.get("userId")), 
					Long.parseLong(params.get("providerId")), 
					Boolean.parseBoolean(params.get("isAssign")), 
					getLoginUserData().getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
		return responseModel;
	}
	
}
