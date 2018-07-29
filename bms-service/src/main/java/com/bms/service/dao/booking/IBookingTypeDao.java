package com.bms.service.dao.booking;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.booking.BookingTypeData;

public interface IBookingTypeDao {
	
	boolean create(BookingTypeData bookingTypeData) throws BmsSqlException;
	
	boolean update(BookingTypeData bookingTypeData) throws BmsSqlException;
	
	boolean delete(long bookingTypeId) throws BmsSqlException;
	
	BookingTypeData getBookingTypeById(long bookingTypeId) throws BmsSqlException;
	
	List<BookingTypeData> getAllBookingTypes() throws BmsSqlException;
	
}
