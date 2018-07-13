package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.ImageData;

public interface IImageDao {
	
	long create(long userId, long providerId, long itemCategoryId, ImageData imageData) throws BmsSqlException;
	
	boolean update(ImageData imageData) throws BmsSqlException;
	
	boolean delete(long imageId) throws BmsSqlException;
	
	ImageData getImageById(long imageId) throws BmsSqlException;
	
	List<ImageData> getAllImages() throws BmsSqlException;
	
}
