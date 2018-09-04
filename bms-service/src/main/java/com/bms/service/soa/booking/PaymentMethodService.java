package com.bms.service.soa.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IPaymentMethodDao;
import com.bms.service.data.booking.PaymentMethodData;
import com.bms.service.soa.BaseService;

@Service("paymentMethodService")
public class PaymentMethodService extends BaseService implements IPaymentMethodService {

	private IPaymentMethodDao paymentMethodDao;
	
	@Override
	public boolean create(PaymentMethodData paymentMethodData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			paymentMethodData.setCreatedBy(loginUserId);
			paymentMethodData.setCreatedOn(currDate);
			paymentMethodData.setUpdatedBy(loginUserId);
			paymentMethodData.setUpdatedOn(currDate);
			return paymentMethodDao.create(paymentMethodData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(PaymentMethodData paymentMethodData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			paymentMethodData.setUpdatedBy(loginUserId);
			paymentMethodData.setUpdatedOn(currDate);
			return paymentMethodDao.update(paymentMethodData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long paymentMethodId) throws BmsException, BmsSqlException {
		return paymentMethodDao.delete(paymentMethodId);
	}

	@Override
	public PaymentMethodData getPaymentMethodById(long paymentMethodId) throws BmsException, BmsSqlException {
		return paymentMethodDao.getPaymentMethodById(paymentMethodId);
	}

	@Override
	public List<PaymentMethodData> getAllPaymentMethods() throws BmsException, BmsSqlException {
		return paymentMethodDao.getAllPaymentMethods();
	}

	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		return paymentMethodDao.isAvailable(id, name);
	}
	
	@Override
	public IPaymentMethodDao getPaymentMethodDao() {
		return paymentMethodDao;
	}

	@Autowired
	@Qualifier("paymentMethodDao")
	@Override
	public void setPaymentMethodDao(IPaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

}
