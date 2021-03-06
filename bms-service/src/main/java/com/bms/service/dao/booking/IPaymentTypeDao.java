package com.bms.service.dao.booking;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.booking.PaymentTypeData;

public interface IPaymentTypeDao {
	
	boolean create(PaymentTypeData paymentTypeData) throws BmsSqlException;
	
	boolean update(PaymentTypeData paymentTypeData) throws BmsSqlException;
	
	boolean delete(long paymentTypeId) throws BmsSqlException;
	
	PaymentTypeData getPaymentTypeById(long paymentTypeId) throws BmsSqlException;
	
	List<PaymentTypeData> getAllPaymentTypes() throws BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsSqlException;
	
}
