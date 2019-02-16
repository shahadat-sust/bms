package com.bms.service.dao.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.SqlConstants;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.room.ItemCategoryAmenityData;

@Repository("itemCategoryAmenityDao")
public class ItemCategoryAmenityDao extends BaseDao implements IItemCategoryAmenityDao {

	@Autowired
	@Qualifier("itemCategoryAmenityQuery")
	private Properties itemCategoryAmenityQuery;
	
	@Override
	public long create(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, itemCategoryAmenityData.getItemCategoryId());
					ps.setLong(2, itemCategoryAmenityData.getAmenityId());
					ps.setInt(3, itemCategoryAmenityData.getQuantity());
					ps.setLong(4, itemCategoryAmenityData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(itemCategoryAmenityData.getCreatedOn().getTime()));
					ps.setLong(6, itemCategoryAmenityData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(itemCategoryAmenityData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.update");
			return this.getTemplete().update(sql, 
					itemCategoryAmenityData.getItemCategoryId(),
					itemCategoryAmenityData.getAmenityId(),
					itemCategoryAmenityData.getQuantity(),
					itemCategoryAmenityData.getUpdatedBy(),
					new java.sql.Timestamp(itemCategoryAmenityData.getUpdatedOn().getTime()),
					itemCategoryAmenityData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long itemCategoryAmenityId) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.delete");
			return this.getTemplete().update(sql, itemCategoryAmenityId) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ItemCategoryAmenityData getItemCategoryAmenityById(long itemCategoryAmenityId) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.getItemCategoryAmenityById");
			Object[] params = new Object[] {itemCategoryAmenityId};
			List<ItemCategoryAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryAmenityData>() {
				@Override
				public ItemCategoryAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemCategoryAmenityData data = new ItemCategoryAmenityData();
					data.setId(rs.getLong(1));
					data.setItemCategoryId(rs.getLong(2));
					data.setAmenityId(rs.getLong(3));
					data.setQuantity(rs.getInt(4));
					data.setAmenityName(rs.getString(5));
					return data;
				}
			});
			
			if (list.isEmpty()) {
			  return null;
			} else if (list.size() == 1) {
			  return list.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public ItemCategoryAmenityData getItemCategoryAmenityByItemCategoryIdAndAmenityId(long itemCategoryId, long amenityId) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.getItemCategoryAmenityByItemCategoryIdAndAmenityId");
			Object[] params = new Object[] {itemCategoryId, amenityId};
			List<ItemCategoryAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryAmenityData>() {
				@Override
				public ItemCategoryAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemCategoryAmenityData data = new ItemCategoryAmenityData();
					data.setId(rs.getLong(1));
					data.setItemCategoryId(rs.getLong(2));
					data.setAmenityId(rs.getLong(3));
					data.setQuantity(rs.getInt(4));
					data.setAmenityName(rs.getString(5));
					return data;
				}
			});
			
			if (list.isEmpty()) {
			  return null;
			} else if (list.size() == 1) {
			  return list.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ItemCategoryAmenityData> getAllItemCategoryAmenitiesByItemCategoryId(long itemCategoryId) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.getAllItemCategoryAmenitiesByItemCategoryId");
			Object[] params = new Object[] {itemCategoryId};
			List<ItemCategoryAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryAmenityData>() {
				@Override
				public ItemCategoryAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemCategoryAmenityData data = new ItemCategoryAmenityData();
					data.setId(rs.getLong(1));
					data.setItemCategoryId(rs.getLong(2));
					data.setAmenityId(rs.getLong(3));
					data.setQuantity(rs.getInt(4));
					data.setAmenityName(rs.getString(5));
					return data;
				}
			});
			
			return list;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, long amenityId, long itemCategoryId) throws BmsSqlException {
		try {
			String sql = itemCategoryAmenityQuery.getProperty("itemCategoryAmenity.isAvailable");
			Object[] params = new Object[] {id, id, amenityId, itemCategoryId, id, amenityId, itemCategoryId};
			List<Long> userIDs = getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getLong(1);
				}
			});
			
			if (userIDs.isEmpty()) {
			  return true;
			} else if (userIDs.size() == 1) {
			  return false;
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
