package com.bms.admin.controller.setup;

import java.util.List;

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

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderTypeData;
import com.bms.service.soa.provider.IProviderTypeService;

@Controller
@RequestMapping(value = "/providertype")
@Scope("request")
public class ProviderTypeController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IProviderTypeService providerTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String providerType(Model model) throws BmsSqlException, BmsException {
		List<ProviderTypeData> providerTypeList = providerTypeService.getAllProviderTypes();
		model.addAttribute("providerTypeList", providerTypeList);
		return "setup/providertype";
	}
	
	@RequestMapping(value = "/fetch/{providerTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderTypeData> getGroupList(@PathVariable long providerTypeId) {
		ResponseModel<ProviderTypeData> responseModel = new ResponseModel<ProviderTypeData>();
		try {
			if(providerTypeId > 0) {
				ProviderTypeData providerTypeData = providerTypeService.getProviderTypeById(providerTypeId);
				responseModel.addData(providerTypeData);
			} else {
				List<ProviderTypeData> providerTypeList = providerTypeService.getAllProviderTypes();
				responseModel.addDatas(providerTypeList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderTypeData> createGroup(@RequestBody ProviderTypeData providerTypeData) {
		ResponseModel<ProviderTypeData> responseModel = new ResponseModel<ProviderTypeData>();
		try {
			long providerTypeId = providerTypeService.create(providerTypeData, getLoginUserData().getId());
			if(providerTypeId > 0) {
				providerTypeData.setId(providerTypeId);
				responseModel.setStatus(true);
				responseModel.addData(providerTypeData);
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
	public @ResponseBody ResponseModel<ProviderTypeData> updateGroup(@RequestBody ProviderTypeData providerTypeData) {
		ResponseModel<ProviderTypeData> responseModel = new ResponseModel<ProviderTypeData>();
		try {
			boolean status = providerTypeService.update(providerTypeData, getLoginUserData().getId());
			if(status) {
				ProviderTypeData data = providerTypeService.getProviderTypeById(providerTypeData.getId());
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
	
	@RequestMapping(value = "/delete/{providerTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<ProviderTypeData> deleteGroup(@PathVariable long providerTypeId) {
		ResponseModel<ProviderTypeData> responseModel = new ResponseModel<ProviderTypeData>();
		try {
			responseModel.setStatus(providerTypeService.delete(providerTypeId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("providerTypeService")
	public void setProviderTypeService(IProviderTypeService providerTypeService) {
		this.providerTypeService = providerTypeService;
	}
	
}
