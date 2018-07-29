package com.bms.service.dao.booking;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.booking.BookingStatusData;

public interface IBookingStatusDao {
	
	boolean create(BookingStatusData bookingStatusData) throws BmsSqlException;
	
	boolean update(BookingStatusData bookingStatusData) throws BmsSqlException;
	
	boolean delete(long bookingStatusId) throws BmsSqlException;
	
	BookingStatusData getBookingStatusById(long bookingStatusId) throws BmsSqlException;
	
	List<BookingStatusData> getAllBookingStatuses() throws BmsSqlException;
	
}
