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
import com.bms.service.data.room.ItemAmenityData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.room.IItemAmenityService;
import com.bms.service.soa.room.IItemCategoryService;
import com.bms.service.soa.room.IItemService;
import com.bms.service.soa.room.IItemTypeService;

@Controller
@RequestMapping(value = "/roomamenity")
@Scope("request")
public class RoomAmenityController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IItemTypeService itemTypeService;
	private IItemCategoryService itemCategoryService;
	private IItemService itemService;
	private IItemAmenityService itemAmenityService;
	
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
		return "room/roomamenity";
	}
	
	@RequestMapping(value = "/fetch/{roomAmenityId}/{roomId}/{amenityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemAmenityData> fetch(@PathVariable long roomAmenityId, @PathVariable long roomId, @PathVariable long amenityId) {
		ResponseModel<ItemAmenityData> responseModel = new ResponseModel<ItemAmenityData>();
		try {
			if(roomId > 0 && amenityId > 0) {
				ItemAmenityData itemAmenityData = itemAmenityService.getItemAmenityByItemIdAndAmenityId(roomId, amenityId);
				if (itemAmenityData != null) {
					responseModel.addData(itemAmenityData);
				} else {
					responseModel.addDatas(new ArrayList<>());
				}
			} else if(roomId > 0) { 
				List<ItemAmenityData> itemAmenityList = itemAmenityService.getAllItemAmenitisByItemId(roomId);
				responseModel.addDatas(itemAmenityList);
			} else if(roomAmenityId > 0) {
				ItemAmenityData itemAmenityData = itemAmenityService.getItemAmenityById(roomAmenityId);
				if (itemAmenityData != null) {
					responseModel.addData(itemAmenityData);
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
	public @ResponseBody ResponseModel<ItemAmenityData> create(@RequestBody ItemAmenityData itemAmenityData) {
		ResponseModel<ItemAmenityData> responseModel = new ResponseModel<ItemAmenityData>();
		try {
			long itemAmenityId = itemAmenityService.create(itemAmenityData, getLoginUserData().getId());
			if(itemAmenityId > 0) {
				ItemAmenityData data = itemAmenityService.getItemAmenityById(itemAmenityId);
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
	public @ResponseBody ResponseModel<ItemAmenityData> update(@RequestBody ItemAmenityData itemAmenityData) {
		ResponseModel<ItemAmenityData> responseModel = new ResponseModel<ItemAmenityData>();
		try {
			boolean status = itemAmenityService.update(itemAmenityData, getLoginUserData().getId());
			if(status) {
				ItemAmenityData data = itemAmenityService.getItemAmenityById(itemAmenityData.getId());
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
	
	@RequestMapping(value = "/delete/{itemAmenityId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemAmenityData> delete(@PathVariable long itemAmenityId) {
		ResponseModel<ItemAmenityData> responseModel = new ResponseModel<ItemAmenityData>();
		try {
			responseModel.setStatus(itemAmenityService.delete(itemAmenityId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@RequestMapping(value = "/isAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isAvailable(@RequestParam long roomAmenityId, @RequestParam long amenityId, @RequestParam long roomId) {
		boolean status = false;
		try {
			status = itemAmenityService.isAvailable(roomAmenityId, amenityId, roomId);
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

	public IItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	@Autowired
	@Qualifier("itemCategoryService")
	public void setItemCategoryService(IItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public IItemService getItemService() {
		return itemService;
	}

	@Autowired
	@Qualifier("itemService")
	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public IItemAmenityService getItemAmenityService() {
		return itemAmenityService;
	}

	@Autowired
	@Qualifier("itemAmenityService")
	public void setItemAmenityService(IItemAmenityService itemAmenityService) {
		this.itemAmenityService = itemAmenityService;
	}

}
