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
import com.bms.service.data.booking.BookingStatusData;
import com.bms.service.soa.booking.IBookingStatusService;

@Controller
@RequestMapping(value = "/bookingstatus")
@Scope("request")
public class BookingStatusController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private IBookingStatusService bookingStatusService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String bookingStatus(Model model) throws BmsSqlException, BmsException {
		List<BookingStatusData> bookingStatusList = bookingStatusService.getAllBookingStatuses();
		model.addAttribute("bookingStatusList", bookingStatusList);
		return "setup/bookingstatus";
	}
	
	@RequestMapping(value = "/fetch/{bookingStatusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingStatusData> getBookingStatusList(@PathVariable long bookingStatusId) {
		ResponseModel<BookingStatusData> responseModel = new ResponseModel<BookingStatusData>();
		try {
			if(bookingStatusId > 0) {
				BookingStatusData bookingStatusData = bookingStatusService.getBookingStatusById(bookingStatusId);
				responseModel.addData(bookingStatusData);
			} else {
				List<BookingStatusData> bookingStatusList = bookingStatusService.getAllBookingStatuses();
				responseModel.addDatas(bookingStatusList);
			}
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingStatusData> createBookingStatus(@RequestBody BookingStatusData bookingStatusData) {
		ResponseModel<BookingStatusData> responseModel = new ResponseModel<BookingStatusData>();
		try {
			if(bookingStatusService.getBookingStatusById(bookingStatusData.getId()) != null) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "id" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean isAvailable = bookingStatusService.isAvailable(bookingStatusData.getId(), bookingStatusData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = bookingStatusService.create(bookingStatusData, getLoginUserData().getId());
			if(status) {
				BookingStatusData data = bookingStatusService.getBookingStatusById(bookingStatusData.getId());
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
	public @ResponseBody ResponseModel<BookingStatusData> updateBookingStatus(@RequestBody BookingStatusData bookingStatusData) {
		ResponseModel<BookingStatusData> responseModel = new ResponseModel<BookingStatusData>();
		try {
			boolean isAvailable = bookingStatusService.isAvailable(bookingStatusData.getId(), bookingStatusData.getName());
			if(!isAvailable) {
				responseModel.setStatus(false);
				responseModel.addError(getMessageSource().getMessage("error.duplicate.entry", new Object[] { "name" }, Locale.getDefault()));
				return responseModel;
			}
			
			boolean status = bookingStatusService.update(bookingStatusData, getLoginUserData().getId());
			if(status) {
				BookingStatusData data = bookingStatusService.getBookingStatusById(bookingStatusData.getId());
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
	
	@RequestMapping(value = "/delete/{bookingStatusId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel<BookingStatusData> deleteBookingStatus(@PathVariable long bookingStatusId) {
		ResponseModel<BookingStatusData> responseModel = new ResponseModel<BookingStatusData>();
		try {
			responseModel.setStatus(bookingStatusService.delete(bookingStatusId));
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.addError(getMessageSource().getMessage("error.returned", new Object[] { e.getMessage() }, Locale.getDefault()));
		}
	    return responseModel;
	}

	@Autowired
	@Qualifier("bookingStatusService")
	public void setBookingStatusService(IBookingStatusService bookingStatusService) {
		this.bookingStatusService = bookingStatusService;
	}
	
}
