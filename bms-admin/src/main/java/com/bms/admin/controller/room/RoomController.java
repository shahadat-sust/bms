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
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.room.ItemData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.room.IItemCategoryService;
import com.bms.service.soa.room.IItemService;
import com.bms.service.soa.room.IItemTypeService;

@Controller
@RequestMapping(value = "/room")
@Scope("request")
public class RoomController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IItemTypeService itemTypeService;
	private IItemCategoryService itemCategoryService;
	private IItemService itemService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) throws BmsSqlException, BmsException {
		ProviderAdminData defaultHotel = (ProviderAdminData) request.getSession().getAttribute(AppConstants.KEY_DEFAULT_HOTEL);
		List<ItemData> roomList = itemService.getAllItemsByProviderId(defaultHotel.getProviderId());
		model.addAttribute("roomList", roomList);
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
		return "room/room";
	}
	
	@RequestMapping(value = "/fetch/{roomId}/{providerId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemData> fetch(@PathVariable long roomId, @PathVariable long providerId, @PathVariable int sortOrder) {
		ResponseModel<ItemData> responseModel = new ResponseModel<ItemData>();
		try {
			if(roomId > 0) {
				ItemData itemData = itemService.getItemById(roomId);
				responseModel.addData(itemData);
			} else { 
				List<ItemData> itemList = null;
				if(providerId > 0) {
					itemList = itemService.getAllItemsByProviderId(providerId);
				} else {
					itemList = new ArrayList<>();
				}
				
				if (itemList != null && itemList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(itemList, new Comparator<ItemData>() {
							@Override
							public int compare(ItemData o1, ItemData o2) {
								return o1.getItemNo().compareTo(o2.getItemNo());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(itemList, new Comparator<ItemData>() {
							@Override
							public int compare(ItemData o1, ItemData o2) {
								return o2.getItemNo().compareTo(o1.getItemNo());
							}
						});
					}
				}
				
				responseModel.addDatas(itemList);
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
	public @ResponseBody ResponseModel<ItemData> create(@RequestBody ItemData itemData) {
		ResponseModel<ItemData> responseModel = new ResponseModel<ItemData>();
		try {
			long itemId = itemService.create(itemData, getLoginUserData().getId());
			if(itemId > 0) {
				ItemData data = itemService.getItemById(itemId);
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
	public @ResponseBody ResponseModel<ItemData> update(@RequestBody ItemData itemData) {
		ResponseModel<ItemData> responseModel = new ResponseModel<ItemData>();
		try {
			boolean status = itemService.update(itemData, getLoginUserData().getId());
			if(status) {
				ItemData data = itemService.getItemById(itemData.getId());
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
	
	@RequestMapping(value = "/delete/{roomId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemData> delete(@PathVariable long roomId) {
		ResponseModel<ItemData> responseModel = new ResponseModel<ItemData>();
		try {
			responseModel.setStatus(itemService.delete(roomId, getLoginUserData().getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@RequestMapping(value = "/isRoomNoAvailable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean isRoomNoAvailable(@RequestParam long roomId, @RequestParam String roomNo, @RequestParam long providerId) {
		boolean status = false;
		try {
			status = itemService.isAvailable(roomId, roomNo, providerId);
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

}
