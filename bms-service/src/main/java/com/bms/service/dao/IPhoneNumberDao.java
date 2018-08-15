package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.PhoneNumberData;

public interface IPhoneNumberDao {
	
	long create(long userId, long providerId, PhoneNumberData phoneNumberData) throws BmsSqlException;
	
	boolean update(PhoneNumberData phoneNumberData) throws BmsSqlException;
	
	boolean delete(long phoneNumberId) throws BmsSqlException;
	
	PhoneNumberData getPhoneNumberById(long phoneNumberId) throws BmsSqlException;
	
	List<PhoneNumberData> getAllPhoneNumbersByUserId(long userId) throws BmsSqlException;
	
	List<PhoneNumberData> getAllPhoneNumbersByProviderId(long providerId) throws BmsSqlException;
	
	boolean isPhoneNumberAvailableForUser(long userId, String code, String number) throws BmsSqlException;
	
}
