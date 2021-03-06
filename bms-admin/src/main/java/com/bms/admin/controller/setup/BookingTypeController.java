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
import com.bms.service.soa.booking.IBookingTypeService;

@Controller
@RequestMapping(value = "/bookingtype")
@Scope("request")
public class BookingTypeController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IBookingTypeService bookingTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String bookingType(Model model) throws BmsSqlException, BmsException {
		List<BookingTypeData> bookingTypeList = bookingTypeService.getAllBookingTypes();
		model.addAttribute("bookingTypeList", bookingTypeList);
		return "setup/bookingtype";
	}
	
	@RequestMapping(value = "/fetch/{bookingTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingTypeData> getBookingTypeList(@PathVariable long bookingTypeId) {
		ResponseModel<BookingTypeData> responseModel = new ResponseModel<BookingTypeData>();
		try {
			if(bookingTypeId > 0) {
				BookingTypeData bookingTypeData = bookingTypeService.getBookingTypeById(bookingTypeId);
				responseModel.addData(bookingTypeData);
			} else {
				List<BookingTypeData> bookingTypeList = bookingTypeService.getAllBookingTypes();
				responseModel.addDatas(bookingTypeList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingTypeData> createBookingType(@RequestBody BookingTypeData bookingTypeData) {
		ResponseModel<BookingTypeData> responseModel = new ResponseModel<BookingTypeData>();
		try {
			if(bookingTypeService.getBookingTypeById(bookingTypeData.getId()) != null) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "id" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean isAvailable = bookingTypeService.isAvailable(bookingTypeData.getId(), bookingTypeData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = bookingTypeService.create(bookingTypeData, getLoginUserData().getId());
			if(status) {
				BookingTypeData data = bookingTypeService.getBookingTypeById(bookingTypeData.getId());
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
	public @ResponseBody ResponseModel<BookingTypeData> updateBookingType(@RequestBody BookingTypeData bookingTypeData) {
		ResponseModel<BookingTypeData> responseModel = new ResponseModel<BookingTypeData>();
		try {
			boolean isAvailable = bookingTypeService.isAvailable(bookingTypeData.getId(), bookingTypeData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = bookingTypeService.update(bookingTypeData, getLoginUserData().getId());
			if(status) {
				BookingTypeData data = bookingTypeService.getBookingTypeById(bookingTypeData.getId());
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
	
	@RequestMapping(value = "/delete/{bookingTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingTypeData> deleteBookingType(@PathVariable long bookingTypeId) {
		ResponseModel<BookingTypeData> responseModel = new ResponseModel<BookingTypeData>();
		try {
			responseModel.setStatus(bookingTypeService.delete(bookingTypeId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("bookingTypeService")
	public void setBookingTypeService(IBookingTypeService bookingTypeService) {
		this.bookingTypeService = bookingTypeService;
	}
	
}
