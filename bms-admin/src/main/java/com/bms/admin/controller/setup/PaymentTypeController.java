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
import com.bms.service.data.booking.PaymentMethodData;
import com.bms.service.data.booking.PaymentTypeData;
import com.bms.service.soa.booking.IPaymentTypeService;

@Controller
@RequestMapping(value = "/paymenttype")
@Scope("request")
public class PaymentTypeController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IPaymentTypeService paymentTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String paymentType(Model model) throws BmsSqlException, BmsException {
		List<PaymentTypeData> paymentTypeList = paymentTypeService.getAllPaymentTypes();
		model.addAttribute("paymentTypeList", paymentTypeList);
		return "setup/paymenttype";
	}
	
	@RequestMapping(value = "/fetch/{paymentTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentTypeData> getGroupList(@PathVariable long paymentTypeId) {
		ResponseModel<PaymentTypeData> responseModel = new ResponseModel<PaymentTypeData>();
		try {
			if(paymentTypeId > 0) {
				PaymentTypeData paymentTypeData = paymentTypeService.getPaymentTypeById(paymentTypeId);
				responseModel.addData(paymentTypeData);
			} else {
				List<PaymentTypeData> paymentTypeList = paymentTypeService.getAllPaymentTypes();
				responseModel.addDatas(paymentTypeList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentTypeData> createGroup(@RequestBody PaymentTypeData paymentTypeData) {
		ResponseModel<PaymentTypeData> responseModel = new ResponseModel<PaymentTypeData>();
		try {
			boolean status = paymentTypeService.create(paymentTypeData, getLoginUserData().getId());
			if(status) {
				PaymentTypeData data = paymentTypeService.getPaymentTypeById(paymentTypeData.getId());
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
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentTypeData> updateGroup(@RequestBody PaymentTypeData paymentTypeData) {
		ResponseModel<PaymentTypeData> responseModel = new ResponseModel<PaymentTypeData>();
		try {
			boolean status = paymentTypeService.update(paymentTypeData, getLoginUserData().getId());
			if(status) {
				PaymentTypeData data = paymentTypeService.getPaymentTypeById(paymentTypeData.getId());
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
	
	@RequestMapping(value = "/delete/{paymentTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentTypeData> deleteGroup(@PathVariable long paymentTypeId) {
		ResponseModel<PaymentTypeData> responseModel = new ResponseModel<PaymentTypeData>();
		try {
			responseModel.setStatus(paymentTypeService.delete(paymentTypeId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(e.getMessage());
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("paymentTypeService")
	public void setPaymentTypeService(IPaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}
	
}
