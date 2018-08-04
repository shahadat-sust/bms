package com.bms.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.data.ImageData;

@Repository("imageDao")
public class ImageDao extends BaseDao implements IImageDao {

	@Autowired
	@Qualifier("imageQuery")
	private Properties imageQuery;
	
	@Override
	public long create(long userId, long providerId, long itemCategoryId, ImageData imageData) throws BmsSqlException {
		try {
			String sql = imageQuery.getProperty("image.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, imageData.getUrl());
					ps.setInt(2, imageData.getType());
					ps.setBoolean(3, imageData.isDefault());
					ps.setInt(4, imageData.getStatus());
					ps.setLong(5, imageData.getCreatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(imageData.getCreatedOn().getTime()));
					ps.setLong(7, imageData.getUpdatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(imageData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long imageId = holder.getKey().longValue();
			
			if(userId > 0) {
				String sql1 = imageQuery.getProperty("userImage.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, userId);
						ps.setLong(2, imageId);
						ps.setLong(3, imageData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(imageData.getCreatedOn().getTime()));
						ps.setLong(5, imageData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(imageData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(providerId > 0) {
				String sql1 = imageQuery.getProperty("providerImage.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, providerId);
						ps.setLong(2, imageId);
						ps.setLong(3, imageData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(imageData.getCreatedOn().getTime()));
						ps.setLong(5, imageData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(imageData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(itemCategoryId > 0) {
				String sql1 = imageQuery.getProperty("itemCategoryImage.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, providerId);
						ps.setLong(2, imageId);
						ps.setLong(3, imageData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(imageData.getCreatedOn().getTime()));
						ps.setLong(5, imageData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(imageData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			}
			
			return imageId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ImageData imageData) throws BmsSqlException {
		try {
			String sql = imageQuery.getProperty("image.update");
			return this.getTemplete().update(sql, 
					imageData.getUrl(),
					imageData.getType(),
					imageData.isDefault(),
					imageData.getStatus(), 
					imageData.getUpdatedBy(),
					new java.sql.Timestamp(imageData.getUpdatedOn().getTime()),
					imageData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long imageId) throws BmsSqlException {
		try {
			String sql = imageQuery.getProperty("image.delete");
			return this.getTemplete().update(sql, imageId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ImageData getImageById(long imageId) throws BmsSqlException {
		try {
			String sql = imageQuery.getProperty("image.getImageById");
			Object[] params = new Object[] {imageId};
			List<ImageData> imageList = this.getTemplete().query(sql, params, new RowMapper<ImageData>() {
				@Override
				public ImageData mapRow(ResultSet rs, int index) throws SQLException {
					ImageData imageData = new ImageData();
					imageData.setId(rs.getLong(1));
					imageData.setUrl(rs.getString(2));
					imageData.setType(rs.getInt(3));
					imageData.setDefault(rs.getBoolean(4));
					imageData.setStatus(rs.getInt(5));
					return imageData;
				}
			});
			
			if (imageList.isEmpty()) {
			  return null;
			} else if (imageList.size() == 1) {
			  return imageList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ImageData> getAllImages() throws BmsSqlException {
		try {
			String sql = imageQuery.getProperty("image.getAllImages");
			List<ImageData> imageList = this.getTemplete().query(sql, new RowMapper<ImageData>() {
				@Override
				public ImageData mapRow(ResultSet rs, int index) throws SQLException {
					ImageData imageData = new ImageData();
					imageData.setId(rs.getLong(1));
					imageData.setUrl(rs.getString(2));
					imageData.setType(rs.getInt(3));
					imageData.setDefault(rs.getBoolean(4));
					imageData.setStatus(rs.getInt(5));
					return imageData;
				}
			});
			return imageList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
