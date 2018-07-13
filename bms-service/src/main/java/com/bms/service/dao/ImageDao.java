package com.bms.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bms.service.BmsSqlException;
import com.bms.service.data.ImageData;

public class ImageDao extends BaseDao implements IImageDao {

	@Override
	public long create(long userId, long providerId, long itemCategoryId, ImageData imageData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO Image ")
		.append("( ")
			.append("Url, ")
			.append("Type, ")
			.append("IsDefault, ")
			.append("Status ")
		.append(") ")
		.append("VALUES ")
		.append("( ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("? ")
		.append(")");
		KeyHolder holder = new GeneratedKeyHolder();
		this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
				ps.setString(1, imageData.getUrl());
				ps.setInt(2, imageData.getType());
				ps.setBoolean(3, imageData.isDefault());
				ps.setInt(4, imageData.getStatus());
				return ps;
			}
		}, holder);
		long imageId = holder.getKey().longValue();
		
		if(userId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO UserImage ")
			.append("( ")
				.append("UserId, ")
				.append("ImageId, ")
				.append("CreatedBy, ")
				.append("CreatedOn, ")
				.append("UpdatedBy, ")
				.append("UpdatedOn ")
			.append(") ")
			.append("VALUES ")
			.append("( ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("? ")
			.append(")");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
					ps.setLong(1, userId);
					ps.setLong(2, imageId);
					ps.setLong(3, imageData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(imageData.getCreatedOn().getTime()));
					ps.setLong(5, imageData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(imageData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		} else if(providerId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO ProviderImage ")
			.append("( ")
				.append("ProviderId, ")
				.append("ImageId, ")
				.append("CreatedBy, ")
				.append("CreatedOn, ")
				.append("UpdatedBy, ")
				.append("UpdatedOn ")
			.append(") ")
			.append("VALUES ")
			.append("( ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("? ")
			.append(")");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
					ps.setLong(1, providerId);
					ps.setLong(2, imageId);
					ps.setLong(3, imageData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(imageData.getCreatedOn().getTime()));
					ps.setLong(5, imageData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(imageData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		} else if(itemCategoryId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO ItemCategoryImage ")
			.append("( ")
				.append("ProviderId, ")
				.append("ImageId, ")
				.append("CreatedBy, ")
				.append("CreatedOn, ")
				.append("UpdatedBy, ")
				.append("UpdatedOn ")
			.append(") ")
			.append("VALUES ")
			.append("( ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("?, ")
				.append("? ")
			.append(")");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
					ps.setLong(1, providerId);
					ps.setLong(2, imageId);
					ps.setLong(3, imageData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(imageData.getCreatedOn().getTime()));
					ps.setLong(5, imageData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(imageData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		}
		
		return imageId;
	}

	@Override
	public boolean update(ImageData imageData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE Image SET ")
			.append("Url = ?, ")
			.append("Type = ?, ")
			.append("IsDefault = ?, ")
			.append("Status = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				imageData.getUrl(),
				imageData.getType(),
				imageData.isDefault(),
				imageData.getStatus(), 
				imageData.getId()) == 1;
	}

	@Override
	public boolean delete(long imageId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM Image WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), imageId) == 1;
	}

	@Override
	public ImageData getImageById(long imageId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Url, ")
			.append("Type, ")
			.append("IsDefault, ")
			.append("Status ")
		.append("FROM Image ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {imageId};
		List<ImageData> imageList = this.getTemplete().query(sql.toString(), params, new RowMapper<ImageData>() {
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
	}

	@Override
	public List<ImageData> getAllImages() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Url, ")
			.append("Type, ")
			.append("IsDefault, ")
			.append("Status ")
		.append("FROM Image");
		
		List<ImageData> imageList = this.getTemplete().query(sql.toString(), new RowMapper<ImageData>() {
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
	}

}
