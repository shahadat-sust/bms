package com.bms.service.soa.booking;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IBookingStatusDao;
import com.bms.service.data.booking.BookingStatusData;

public interface IBookingStatusService {
	
	boolean create(BookingStatusData bookingStatusData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(BookingStatusData bookingStatusData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long bookingStatusId) throws BmsException, BmsSqlException;
	
	BookingStatusData getBookingStatusById(long bookingStatusId) throws BmsException, BmsSqlException;
	
	List<BookingStatusData> getAllBookingStatuses() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsSqlException;
	
	IBookingStatusDao getBookingStatusDao();

	void setBookingStatusDao(IBookingStatusDao bookingStatusDao);
}
