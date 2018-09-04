package com.bms.service.soa.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IBookingStatusDao;
import com.bms.service.data.booking.BookingStatusData;
import com.bms.service.soa.BaseService;

@Service("bookingStatusService")
public class BookingStatusService extends BaseService implements IBookingStatusService {

	private IBookingStatusDao bookingStatusDao;
	
	@Override
	public boolean create(BookingStatusData bookingStatusData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			bookingStatusData.setCreatedBy(loginUserId);
			bookingStatusData.setCreatedOn(currDate);
			bookingStatusData.setUpdatedBy(loginUserId);
			bookingStatusData.setUpdatedOn(currDate);
			return bookingStatusDao.create(bookingStatusData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(BookingStatusData bookingStatusData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			bookingStatusData.setUpdatedBy(loginUserId);
			bookingStatusData.setUpdatedOn(currDate);
			return bookingStatusDao.update(bookingStatusData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long bookingStatusId) throws BmsException, BmsSqlException {
		return bookingStatusDao.delete(bookingStatusId);
	}

	@Override
	public BookingStatusData getBookingStatusById(long bookingStatusId) throws BmsException, BmsSqlException {
		return bookingStatusDao.getBookingStatusById(bookingStatusId);
	}

	@Override
	public List<BookingStatusData> getAllBookingStatuses() throws BmsException, BmsSqlException {
		return bookingStatusDao.getAllBookingStatuses();
	}

	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		return bookingStatusDao.isAvailable(id, name);
	}
	
	@Override
	public IBookingStatusDao getBookingStatusDao() {
		return bookingStatusDao;
	}

	@Autowired
	@Qualifier("bookingStatusDao")
	@Override
	public void setBookingStatusDao(IBookingStatusDao bookingStatusDao) {
		this.bookingStatusDao = bookingStatusDao;
	}

}
