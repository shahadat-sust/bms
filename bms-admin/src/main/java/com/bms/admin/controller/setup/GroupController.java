package com.bms.admin.controller.setup;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.admin.controller.BaseController;
import com.bms.admin.model.ResponseModel;
import com.bms.service.data.permission.GroupData;
import com.bms.service.soa.permission.IGroupService;

@Controller
@RequestMapping(value = "/group")
public class GroupController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IGroupService groupService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String group() {
		return "setup/group";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> getGroupList() {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			responseModel.addData(new GroupData());
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> addGroup(@RequestBody GroupData groupData) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> deleteGroup(@RequestBody GroupData groupData) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/delete/{groupId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<GroupData> deleteGroup(@PathVariable long groupId) {
		ResponseModel<GroupData> responseModel = new ResponseModel<GroupData>();
		try {
			
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}

	public IGroupService getGroupService() {
		return groupService;
	}

	@Autowired
	@Qualifier("groupService")
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

}
