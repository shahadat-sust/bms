package com.bms.service.soa.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IPaymentTypeDao;
import com.bms.service.data.booking.PaymentTypeData;
import com.bms.service.soa.BaseService;

@Service("paymentTypeService")
public class PaymentTypeService extends BaseService implements IPaymentTypeService {

	private IPaymentTypeDao paymentTypeDao;
	
	@Override
	public boolean create(PaymentTypeData paymentTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			paymentTypeData.setCreatedBy(loginUserId);
			paymentTypeData.setCreatedOn(currDate);
			paymentTypeData.setUpdatedBy(loginUserId);
			paymentTypeData.setUpdatedOn(currDate);
			return paymentTypeDao.create(paymentTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(PaymentTypeData paymentTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			paymentTypeData.setUpdatedBy(loginUserId);
			paymentTypeData.setUpdatedOn(currDate);
			return paymentTypeDao.update(paymentTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long paymentTypeId) throws BmsException, BmsSqlException {
		try {
			return paymentTypeDao.delete(paymentTypeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public PaymentTypeData getPaymentTypeById(long paymentTypeId) throws BmsException, BmsSqlException {
		try {
			return paymentTypeDao.getPaymentTypeById(paymentTypeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<PaymentTypeData> getAllPaymentTypes() throws BmsException, BmsSqlException {
		try {
			return paymentTypeDao.getAllPaymentTypes();
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String name) throws BmsException, BmsSqlException {
		try {
			return paymentTypeDao.isAvailable(id, name);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public IPaymentTypeDao getPaymentTypeDao() {
		return paymentTypeDao;
	}

	@Autowired
	@Qualifier("paymentTypeDao")
	@Override
	public void setPaymentTypeDao(IPaymentTypeDao paymentTypeDao) {
		this.paymentTypeDao = paymentTypeDao;
	}

}
