package com.bms.service.soa.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IBookingTypeDao;
import com.bms.service.data.booking.BookingTypeData;
import com.bms.service.soa.BaseService;

@Service("bookingTypeService")
public class BookingTypeService extends BaseService implements IBookingTypeService {

	private IBookingTypeDao bookingTypeDao;
	
	@Override
	public boolean create(BookingTypeData bookingTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			bookingTypeData.setCreatedBy(loginUserId);
			bookingTypeData.setCreatedOn(currDate);
			bookingTypeData.setUpdatedBy(loginUserId);
			bookingTypeData.setUpdatedOn(currDate);
			return bookingTypeDao.create(bookingTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(BookingTypeData bookingTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			bookingTypeData.setUpdatedBy(loginUserId);
			bookingTypeData.setUpdatedOn(currDate);
			return bookingTypeDao.update(bookingTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long bookingTypeId) throws BmsException, BmsSqlException {
		return bookingTypeDao.delete(bookingTypeId);
	}

	@Override
	public BookingTypeData getBookingTypeById(long bookingTypeId) throws BmsException, BmsSqlException {
		return bookingTypeDao.getBookingTypeById(bookingTypeId);
	}

	@Override
	public List<BookingTypeData> getAllBookingTypes() throws BmsException, BmsSqlException {
		return bookingTypeDao.getAllBookingTypes();
	}

	@Override
	public IBookingTypeDao getBookingTypeDao() {
		return bookingTypeDao;
	}

	@Autowired
	@Qualifier("bookingTypeDao")
	@Override
	public void setBookingTypeDao(IBookingTypeDao bookingTypeDao) {
		this.bookingTypeDao = bookingTypeDao;
	}

}
