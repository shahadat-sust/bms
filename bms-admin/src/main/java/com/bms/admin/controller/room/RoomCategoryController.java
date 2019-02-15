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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.AppConstants;
import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.room.ItemCategoryData;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.room.IItemCategoryService;
import com.bms.service.soa.room.IItemTypeService;

@Controller
@RequestMapping(value = "/roomcategory")
@Scope("request")
public class RoomCategoryController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IItemTypeService itemTypeService;
	private IItemCategoryService itemCategoryService;
	
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
		return "room/roomcategory";
	}
	
	@RequestMapping(value = "/fetch/{roomCategoryId}/{roomTypeId}/{providerId}/{sortOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemCategoryData> fetch(@PathVariable long roomCategoryId, @PathVariable long roomTypeId, @PathVariable long providerId, @PathVariable int sortOrder) {
		ResponseModel<ItemCategoryData> responseModel = new ResponseModel<ItemCategoryData>();
		try {
			if(roomCategoryId > 0) {
				ItemCategoryData itemCategoryData = itemCategoryService.getItemCategoryById(roomCategoryId);
				responseModel.addData(itemCategoryData);
			} else { 
				List<ItemCategoryData> itemCategoryList = null;
				if(roomTypeId > 0) {
					itemCategoryList = itemCategoryService.getAllItemCategoriesByItemTypeId(roomTypeId);
				} else if(providerId > 0) {
					itemCategoryList = itemCategoryService.getAllItemCategoriesByProviderId(providerId);
				} else {
					itemCategoryList = new ArrayList<>();
				}
				
				if (itemCategoryList != null && itemCategoryList.size() > 0) {
					if (sortOrder == Constants.SORT_ORDER_ASC) {
						Collections.sort(itemCategoryList, new Comparator<ItemCategoryData>() {
							@Override
							public int compare(ItemCategoryData o1, ItemCategoryData o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
					} else if (sortOrder == Constants.SORT_ORDER_DESC) {
						Collections.sort(itemCategoryList, new Comparator<ItemCategoryData>() {
							@Override
							public int compare(ItemCategoryData o1, ItemCategoryData o2) {
								return o2.getName().compareTo(o1.getName());
							}
						});
					}
				}
				
				responseModel.addDatas(itemCategoryList);
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
	public @ResponseBody ResponseModel<ItemCategoryData> create(@RequestBody ItemCategoryData itemCategoryData) {
		ResponseModel<ItemCategoryData> responseModel = new ResponseModel<ItemCategoryData>();
		try {
			long itemCategoryId = itemCategoryService.create(itemCategoryData, getLoginUserData().getId());
			if(itemCategoryId > 0) {
				ItemCategoryData data = itemCategoryService.getItemCategoryById(itemCategoryId);
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
	public @ResponseBody ResponseModel<ItemCategoryData> update(@RequestBody ItemCategoryData itemCategoryData) {
		ResponseModel<ItemCategoryData> responseModel = new ResponseModel<ItemCategoryData>();
		try {
			boolean status = itemCategoryService.update(itemCategoryData, getLoginUserData().getId());
			if(status) {
				ItemCategoryData data = itemCategoryService.getItemCategoryById(itemCategoryData.getId());
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
	
	@RequestMapping(value = "/delete/{roomCategoryId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ItemCategoryData> delete(@PathVariable long roomCategoryId) {
		ResponseModel<ItemCategoryData> responseModel = new ResponseModel<ItemCategoryData>();
		try {
			responseModel.setStatus(itemCategoryService.delete(roomCategoryId, getLoginUserData().getId()));
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

	public IItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	@Autowired
	@Qualifier("itemCategoryService")
	public void setItemCategoryService(IItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	
}
