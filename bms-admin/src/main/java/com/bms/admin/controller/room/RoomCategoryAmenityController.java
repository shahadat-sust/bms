package com.bms.admin.controller.room;

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
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.room.ItemCategoryAmenityData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.room.IItemCategoryAmenityService;
import com.bms.service.soa.room.IItemTypeService;

@Controller
@RequestMapping(value = "/roomcategoryamenity")
@Scope("request")
public class RoomCategoryAmenityController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IItemTypeService itemTypeService;
	private IItemCategoryAmenityService itemCategoryAmenityService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) throws BmsSqlException, BmsException {
		ProviderAdminData defaultHotel = (ProviderAdminData) request.getSession().getAttribute(AppConstants.KEY_DEFAULT_HOTEL);
		List<ItemTypeData> roomTypeList = itemTypeService.getAllItemTypesByProviderId(defaultHotel.getProviderId());
		if (roomTypeList != null && roomTypeList.size() > 0) {
			Collections.sort(roomTypeList, new Comparator<ItemTypeData>() {
				@Override
				public int compare(ItemTypeData o1, ItemTypeData o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		model.addAttribute("roomTypeList", roomTypeList);
		return "room/roomcategoryamenity";
	}
	
	@RequestMapping(value = "/fetch/{roomCategoryAmenityId}/{roomCategoryId}/{amenityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemCategoryAmenityData> fetch(@PathVariable long roomCategoryAmenityId, @PathVariable long roomCategoryId, @PathVariable long amenityId) {
		ResponseModel<ItemCategoryAmenityData> responseModel = new ResponseModel<ItemCategoryAmenityData>();
		try {
			if(roomCategoryId > 0 && amenityId > 0) {
				ItemCategoryAmenityData itemCategoryAmenityData = itemCategoryAmenityService.getItemCategoryAmenityByItemCategoryIdAndAmenityId(roomCategoryId, amenityId);
				if (itemCategoryAmenityData != null) {
					responseModel.addData(itemCategoryAmenityData);
				} else {
					responseModel.addDatas(new ArrayList<>());
				}
			} else if(roomCategoryId > 0) {
				List<ItemCategoryAmenityData> itemCategoryAmenityList = itemCategoryAmenityService.getAllItemCategoryAmenitiesByItemCategoryId(roomCategoryId);
				responseModel.addDatas(itemCategoryAmenityList);
			} else if(roomCategoryAmenityId > 0) {
				ItemCategoryAmenityData itemCategoryAmenityData = itemCategoryAmenityService.getItemCategoryAmenityById(roomCategoryAmenityId);
				if (itemCategoryAmenityData != null) {
					responseModel.addData(itemCategoryAmenityData);
				} else {
					responseModel.addDatas(new ArrayList<>());
				}
			} else {
				responseModel.addDatas(new ArrayList<>());
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
	public @ResponseBody ResponseModel<ItemCategoryAmenityData> create(@RequestBody ItemCategoryAmenityData itemCategoryAmenityData) {
		ResponseModel<ItemCategoryAmenityData> responseModel = new ResponseModel<ItemCategoryAmenityData>();
		try {
			long itemCategoryAmenityId = itemCategoryAmenityService.create(itemCategoryAmenityData, getLoginUserData().getId());
			if(itemCategoryAmenityId > 0) {
				ItemCategoryAmenityData data = itemCategoryAmenityService.getItemCategoryAmenityById(itemCategoryAmenityId);
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
	public @ResponseBody ResponseModel<ItemCategoryAmenityData> update(@RequestBody ItemCategoryAmenityData itemCategoryAmenityData) {
		ResponseModel<ItemCategoryAmenityData> responseModel = new ResponseModel<ItemCategoryAmenityData>();
		try {
			boolean status = itemCategoryAmenityService.update(itemCategoryAmenityData, getLoginUserData().getId());
			if(status) {
				ItemCategoryAmenityData data = itemCategoryAmenityService.getItemCategoryAmenityById(itemCategoryAmenityData.getId());
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
	
	@RequestMapping(value = "/delete/{roomCategoryAmenityId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemCategoryAmenityData> delete(@PathVariable long roomCategoryAmenityId) {
		ResponseModel<ItemCategoryAmenityData> responseModel = new ResponseModel<ItemCategoryAmenityData>();
		try {
			responseModel.setStatus(itemCategoryAmenityService.delete(roomCategoryAmenityId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@RequestMapping(value = "/isAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isAvailable(@RequestParam long roomCategoryAmenityId, @RequestParam long amenityId, @RequestParam long roomCategoryId) {
		boolean status = false;
		try {
			status = itemCategoryAmenityService.isAvailable(roomCategoryAmenityId, amenityId, roomCategoryId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    return status;
	}
	
	public IItemTypeService getItemTypeService() {
		return itemTypeService;
	}

	@Autowired
	@Qualifier("itemTypeService")
	public void setItemTypeService(IItemTypeService itemTypeService) {
		this.itemTypeService = itemTypeService;
	}

	public IItemCategoryAmenityService getItemCategoryAmenityService() {
		return itemCategoryAmenityService;
	}

	@Autowired
	@Qualifier("itemCategoryAmenityService")
	public void setItemCategoryAmenityService(IItemCategoryAmenityService itemCategoryAmenityService) {
		this.itemCategoryAmenityService = itemCategoryAmenityService;
	}

	
}
