package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.PostalAddressData;

public interface IPostalAddressDao {
	
	long create(long userId, long providerId, PostalAddressData postalAddressData) throws BmsSqlException;
	
	boolean update(PostalAddressData postalAddressData) throws BmsSqlException;
	
	boolean delete(long postalAddressId) throws BmsSqlException;
	
	PostalAddressData getPostalAddressById(long postalAddressId) throws BmsSqlException;
	
	List<PostalAddressData> getAllPostalAddressesByUserId(long userId) throws BmsSqlException;
	
	List<PostalAddressData> getAllPostalAddressesByProviderId(long providerId) throws BmsSqlException;
	
}
