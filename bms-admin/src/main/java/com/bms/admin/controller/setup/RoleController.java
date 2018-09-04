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
import com.bms.service.data.permission.RoleData;
import com.bms.service.soa.permission.IRoleService;

@Controller
@RequestMapping(value = "/role")
@Scope("request")
public class RoleController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IRoleService roleService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String role(Model model) throws BmsSqlException, BmsException {
		List<RoleData> roleList = roleService.getAllRoles();
		model.addAttribute("roleList", roleList);
		return "setup/role";
	}
	
	@RequestMapping(value = "/fetch/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<RoleData> getRoleList(@PathVariable long roleId) {
		ResponseModel<RoleData> responseModel = new ResponseModel<RoleData>();
		try {
			if(roleId > 0) {
				RoleData roleData = roleService.getRoleById(roleId);
				responseModel.addData(roleData);
			} else {
				List<RoleData> roleList = roleService.getAllRoles();
				responseModel.addDatas(roleList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<RoleData> createRole(@RequestBody RoleData roleData) {
		ResponseModel<RoleData> responseModel = new ResponseModel<RoleData>();
		try {
			boolean isAvailable = roleService.isAvailable(roleData.getId(), roleData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = roleService.create(roleData, getLoginUserData().getId());
			if(status) {
				responseModel.setStatus(true);
				responseModel.addData(roleData);
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
	public @ResponseBody ResponseModel<RoleData> updateRole(@RequestBody RoleData roleData) {
		ResponseModel<RoleData> responseModel = new ResponseModel<RoleData>();
		try {
			boolean isAvailable = roleService.isAvailable(roleData.getId(), roleData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = roleService.update(roleData, getLoginUserData().getId());
			if(status) {
				RoleData data = roleService.getRoleById(roleData.getId());
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
	
	@RequestMapping(value = "/delete/{roleId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<RoleData> deleteRole(@PathVariable long roleId) {
		ResponseModel<RoleData> responseModel = new ResponseModel<RoleData>();
		try {
			responseModel.setStatus(roleService.delete(roleId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("roleService")
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

}
