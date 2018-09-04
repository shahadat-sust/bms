package com.bms.service.soa.booking;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IPaymentMethodDao;
import com.bms.service.data.booking.PaymentMethodData;

public interface IPaymentMethodService {
	
	boolean create(PaymentMethodData paymentMethodData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(PaymentMethodData paymentMethodData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long paymentMethodId) throws BmsException, BmsSqlException;
	
	PaymentMethodData getPaymentMethodById(long paymentMethodId) throws BmsException, BmsSqlException;
	
	List<PaymentMethodData> getAllPaymentMethods() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsSqlException;
	
	IPaymentMethodDao getPaymentMethodDao();

	void setPaymentMethodDao(IPaymentMethodDao paymentMethodDao);
}
