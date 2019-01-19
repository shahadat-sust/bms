package com.bms.admin.controller.provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.admin.controller.BaseController;
import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
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
	
	
	
}
