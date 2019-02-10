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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.AppConstants;
import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.room.IItemTypeService;

@Controller
@RequestMapping(value = "/hotelpointofinterest")
@Scope("request")
public class HotelPointOfInterestController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IItemTypeService itemTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) throws BmsSqlException, BmsException {
		ProviderAdminData defaultHotel = (ProviderAdminData) request.getSession().getAttribute(AppConstants.KEY_DEFAULT_HOTEL);
		List<ItemTypeData> roomTypeList = itemTypeService.getAllItemTypesByProviderId(defaultHotel.getProviderId());
		model.addAttribute("roomTypeList", roomTypeList);
		return "hotel/hotelpointofinterest";
	}
	
	@RequestMapping(value = "/fetch/{roomTypeId}/{providerId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemTypeData> fetch(@PathVariable long roomTypeId, @PathVariable long providerId, @PathVariable int sortOrder) {
		ResponseModel<ItemTypeData> responseModel = new ResponseModel<ItemTypeData>();
		try {
			if(roomTypeId > 0) {
				ItemTypeData itemTypeData = itemTypeService.getItemTypeById(roomTypeId);
				responseModel.addData(itemTypeData);
			} else { 
				List<ItemTypeData> itemTypeList = null;
				if(providerId > 0) {
					itemTypeList = itemTypeService.getAllItemTypesByProviderId(providerId);
				} else {
					itemTypeList = new ArrayList<>();
				}
				
				if (itemTypeList != null && itemTypeList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(itemTypeList, new Comparator<ItemTypeData>() {
							@Override
							public int compare(ItemTypeData o1, ItemTypeData o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(itemTypeList, new Comparator<ItemTypeData>() {
							@Override
							public int compare(ItemTypeData o1, ItemTypeData o2) {
								return o2.getName().compareTo(o1.getName());
							}
						});
					}
				}
				
				responseModel.addDatas(itemTypeList);
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
	public @ResponseBody ResponseModel<ItemTypeData> create(@RequestBody ItemTypeData itemTypeData) {
		ResponseModel<ItemTypeData> responseModel = new ResponseModel<ItemTypeData>();
		try {
			long itemTypeId = itemTypeService.create(itemTypeData, getLoginUserData().getId());
			if(itemTypeId > 0) {
				ItemTypeData data = itemTypeService.getItemTypeById(itemTypeId);
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
	public @ResponseBody ResponseModel<ItemTypeData> update(@RequestBody ItemTypeData itemTypeData) {
		ResponseModel<ItemTypeData> responseModel = new ResponseModel<ItemTypeData>();
		try {
			boolean status = itemTypeService.update(itemTypeData, getLoginUserData().getId());
			if(status) {
				ItemTypeData data = itemTypeService.getItemTypeById(itemTypeData.getId());
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
	
	@RequestMapping(value = "/delete/{roomTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemTypeData> delete(@PathVariable long roomTypeId) {
		ResponseModel<ItemTypeData> responseModel = new ResponseModel<ItemTypeData>();
		try {
			responseModel.setStatus(itemTypeService.delete(roomTypeId, getLoginUserData().getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	public IItemTypeService getItemTypeService() {
		return itemTypeService;
	}

	@Autowired
	@Qualifier("itemTypeService")
	public void setItemTypeService(IItemTypeService itemTypeService) {
		this.itemTypeService = itemTypeService;
	}

	
}
