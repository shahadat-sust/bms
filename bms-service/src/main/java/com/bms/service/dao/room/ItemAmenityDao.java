package com.bms.service.dao.room;

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
import com.bms.service.dao.BaseDao;
import com.bms.service.data.room.ItemAmenityData;

@Repository("itemAmenityDao")
public class ItemAmenityDao extends BaseDao implements IItemAmenityDao {

	@Autowired
	@Qualifier("itemAmenityQuery")
	private Properties itemAmenityQuery;
	
	@Override
	public long create(ItemAmenityData itemAmenityData) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, itemAmenityData.getItemId());
					ps.setLong(2, itemAmenityData.getAmenityId());
					ps.setInt(3, itemAmenityData.getQuantity());
					ps.setLong(4, itemAmenityData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(itemAmenityData.getCreatedOn().getTime()));
					ps.setLong(6, itemAmenityData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(itemAmenityData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ItemAmenityData itemAmenityData) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.update");
			return this.getTemplete().update(sql, 
					itemAmenityData.getItemId(),
					itemAmenityData.getAmenityId(),
					itemAmenityData.getQuantity(),
					itemAmenityData.getUpdatedBy(),
					new java.sql.Timestamp(itemAmenityData.getUpdatedOn().getTime()),
					itemAmenityData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long itemAmenityId) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.delete");
			return this.getTemplete().update(sql, itemAmenityId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ItemAmenityData getItemAmenityById(long itemAmenityId) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.getItemAmenityById");
			Object[] params = new Object[] {itemAmenityId};
			List<ItemAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemAmenityData>() {
				@Override
				public ItemAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemAmenityData data = new ItemAmenityData();
					data.setId(rs.getLong(1));
					data.setItemId(rs.getLong(2));
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
	public ItemAmenityData getItemAmenityByItemIdAndAmenityId(long itemId, long amenityId) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.getItemAmenityByItemIdAndAmenityId");
			Object[] params = new Object[] {itemId, amenityId};
			List<ItemAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemAmenityData>() {
				@Override
				public ItemAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemAmenityData data = new ItemAmenityData();
					data.setId(rs.getLong(1));
					data.setItemId(rs.getLong(2));
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
	public List<ItemAmenityData> getAllItemAmenitisByItemId(long itemId) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.getAllItemAmenitisByItemId");
			Object[] params = new Object[] {itemId};
			List<ItemAmenityData> list = this.getTemplete().query(sql, params, new RowMapper<ItemAmenityData>() {
				@Override
				public ItemAmenityData mapRow(ResultSet rs, int index) throws SQLException {
					ItemAmenityData data = new ItemAmenityData();
					data.setId(rs.getLong(1));
					data.setItemId(rs.getLong(2));
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
	public boolean isAvailable(long id, long amenityId, long itemId) throws BmsSqlException {
		try {
			String sql = itemAmenityQuery.getProperty("itemAmenity.isAvailable");
			Object[] params = new Object[] {id, id, amenityId, itemId, id, amenityId, itemId};
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
