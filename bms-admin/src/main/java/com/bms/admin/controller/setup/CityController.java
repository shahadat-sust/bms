package com.bms.admin.controller.setup;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.admin.controller.BaseController;

@Controller
@RequestMapping(value = "/city")
public class CityController extends BaseController{

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping( value = "", method = RequestMethod.GET)
	public String getGroupList() {
		return "setup/city";
	}
	
}
