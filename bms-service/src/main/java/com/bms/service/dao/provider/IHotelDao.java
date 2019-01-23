package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.HotelData;
import com.bms.service.data.provider.ProviderData;

public interface IHotelDao {

	long create(HotelData hotelData) throws BmsSqlException;
	
	boolean update(HotelData hotelData) throws BmsSqlException;
	
	HotelData getHotelDataByProviderId(long hotelId) throws BmsSqlException;
	
	List<HotelData> getAllHotelDatas() throws BmsSqlException;

}
