package com.bms.service.soa.booking;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.booking.IPaymentTypeDao;
import com.bms.service.data.booking.PaymentTypeData;

public interface IPaymentTypeService {
	
	long create(PaymentTypeData paymentTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(PaymentTypeData paymentTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long paymentTypeId) throws BmsException, BmsSqlException;
	
	PaymentTypeData getPaymentTypeById(long paymentTypeId) throws BmsException, BmsSqlException;
	
	List<PaymentTypeData> getAllPaymentTypes() throws BmsException, BmsSqlException;
	
	IPaymentTypeDao getPaymentTypeDao();

	void setPaymentTypeDao(IPaymentTypeDao paymentTypeDao);
}
