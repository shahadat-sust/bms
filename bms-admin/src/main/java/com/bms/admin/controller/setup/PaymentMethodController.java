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
import com.bms.service.data.booking.BookingTypeData;
import com.bms.service.data.booking.PaymentMethodData;
import com.bms.service.soa.booking.IPaymentMethodService;

@Controller
@RequestMapping(value = "/paymentmethod")
@Scope("request")
public class PaymentMethodController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IPaymentMethodService paymentMethodService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String paymentMethod(Model model) throws BmsSqlException, BmsException {
		List<PaymentMethodData> paymentMethodList = paymentMethodService.getAllPaymentMethods();
		model.addAttribute("paymentMethodList", paymentMethodList);
		return "setup/paymentmethod";
	}
	
	@RequestMapping(value = "/fetch/{paymentMethodId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentMethodData> getPaymentMethodList(@PathVariable long paymentMethodId) {
		ResponseModel<PaymentMethodData> responseModel = new ResponseModel<PaymentMethodData>();
		try {
			if(paymentMethodId > 0) {
				PaymentMethodData paymentMethodData = paymentMethodService.getPaymentMethodById(paymentMethodId);
				responseModel.addData(paymentMethodData);
			} else {
				List<PaymentMethodData> paymentMethodList = paymentMethodService.getAllPaymentMethods();
				responseModel.addDatas(paymentMethodList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentMethodData> createPaymentMethod(@RequestBody PaymentMethodData paymentMethodData) {
		ResponseModel<PaymentMethodData> responseModel = new ResponseModel<PaymentMethodData>();
		try {
			if(paymentMethodService.getPaymentMethodById(paymentMethodData.getId()) != null) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "id" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean isAvailable = paymentMethodService.isAvailable(paymentMethodData.getId(), paymentMethodData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = paymentMethodService.create(paymentMethodData, getLoginUserData().getId());
			if(status) {
				PaymentMethodData data = paymentMethodService.getPaymentMethodById(paymentMethodData.getId());
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
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentMethodData> updatePaymentMethod(@RequestBody PaymentMethodData paymentMethodData) {
		ResponseModel<PaymentMethodData> responseModel = new ResponseModel<PaymentMethodData>();
		try {
			boolean isAvailable = paymentMethodService.isAvailable(paymentMethodData.getId(), paymentMethodData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = paymentMethodService.update(paymentMethodData, getLoginUserData().getId());
			if(status) {
				PaymentMethodData data = paymentMethodService.getPaymentMethodById(paymentMethodData.getId());
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
	
	@RequestMapping(value = "/delete/{paymentMethodId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<PaymentMethodData> deletePaymentMethod(@PathVariable long paymentMethodId) {
		ResponseModel<PaymentMethodData> responseModel = new ResponseModel<PaymentMethodData>();
		try {
			responseModel.setStatus(paymentMethodService.delete(paymentMethodId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("paymentMethodService")
	public void setPaymentMethodService(IPaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}
	
}
