package com.bms.service.soa.booking;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IBookingTypeDao;
import com.bms.service.data.booking.BookingTypeData;

public interface IBookingTypeService {
	
	boolean create(BookingTypeData bookingTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(BookingTypeData bookingTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long bookingTypeId) throws BmsException, BmsSqlException;
	
	BookingTypeData getBookingTypeById(long bookingTypeId) throws BmsException, BmsSqlException;
	
	List<BookingTypeData> getAllBookingTypes() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsException, BmsSqlException;
	
	IBookingTypeDao getBookingTypeDao();

	void setBookingTypeDao(IBookingTypeDao bookingTypeDao);
}
