package com.bms.admin.controller.setup;

import java.util.List;
import java.util.Locale;

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
import com.bms.service.data.permission.GroupData;
import com.bms.service.soa.permission.IGroupService;

@Controller
@RequestMapping(value = "/group")
@Scope("request")
public class GroupController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IGroupService groupService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String group(Model model) throws BmsSqlException, BmsException {
		List<GroupData> groupList = groupService.getAllGroups();
		model.addAttribute("groupList", groupList);
		return "setup/group";
	}
	
	@RequestMapping(value = "/fetch/{groupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> getGroupList(@PathVariable long groupId) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			if(groupId > 0) {
				GroupData groupData = groupService.getGroupById(groupId);
				responseModel.addData(groupData);
			} else {
				List<GroupData> groupList = groupService.getAllGroups();
				responseModel.addDatas(groupList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> createGroup(@RequestBody GroupData groupData) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			if(groupService.getGroupById(groupData.getId()) != null) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "id" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean isAvailable = groupService.isAvailable(groupData.getId(), groupData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = groupService.create(groupData, getLoginUserData().getId());
			if(status) {
				responseModel.setStatus(true);
				responseModel.addData(groupData);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> updateGroup(@RequestBody GroupData groupData) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			boolean isAvailable = groupService.isAvailable(groupData.getId(), groupData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = groupService.update(groupData, getLoginUserData().getId());
			if(status) {
				GroupData data = groupService.getGroupById(groupData.getId());
				responseModel.setStatus(true);
				responseModel.addData(data);
			} else {
				responseModel.setStatus(false);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{groupId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> deleteGroup(@PathVariable long groupId) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			responseModel.setStatus(groupService.delete(groupId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("groupService")
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

}
