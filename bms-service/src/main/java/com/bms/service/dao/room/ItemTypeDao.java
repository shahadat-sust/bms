package com.bms.service.dao.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.room.ItemTypeData;

@Repository("itemTypeDao")
public class ItemTypeDao extends BaseDao implements IItemTypeDao {

	@Autowired
	@Qualifier("itemTypeQuery")
	private Properties itemTypeQuery;
	
	@Override
	public long create(ItemTypeData itemTypeData) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, itemTypeData.getName());
					ps.setLong(2, itemTypeData.getProviderId());
					ps.setBoolean(3, itemTypeData.isActive());
					ps.setInt(4, itemTypeData.getStatus());
					ps.setLong(5, itemTypeData.getCreatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(itemTypeData.getCreatedOn().getTime()));
					ps.setLong(7, itemTypeData.getUpdatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(itemTypeData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ItemTypeData itemTypeData) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.update");
			return this.getTemplete().update(sql,
					itemTypeData.getName(),
					itemTypeData.getProviderId(),
					itemTypeData.isActive() ? 1 : 0,
					itemTypeData.getUpdatedBy(),
					new Timestamp(itemTypeData.getUpdatedOn().getTime()),
					itemTypeData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(ItemTypeData itemTypeData) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.delete"); 
			return this.getTemplete().update(sql, 
					Constants.STATUS_NOT_EXIST,
					itemTypeData.getUpdatedBy(),
					new Timestamp(itemTypeData.getUpdatedOn().getTime()),
					itemTypeData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ItemTypeData getItemTypeById(long itemTypeId) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.getItemTypeById");
			Object[] params = new Object[] {itemTypeId};
			List<ItemTypeData> itemTypes = this.getTemplete().query(sql, params, new RowMapper<ItemTypeData> () {
				@Override
				public ItemTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemTypeData itemTypeData = new ItemTypeData();
					itemTypeData.setId(rs.getLong(1));
					itemTypeData.setName(rs.getString(2));
					itemTypeData.setProviderId(rs.getLong(3));
					itemTypeData.setActive(rs.getBoolean(4));
					itemTypeData.setStatus(rs.getInt(5));
					return itemTypeData;
				}	
			});
			
			if (itemTypes.isEmpty()) {
				return null;
			} else if (itemTypes.size() == 1) {
				return itemTypes.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ItemTypeData> getAllItemTypesByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.getAllItemTypesByProviderId");
			Object[] params = new Object[] {providerId};
			List<ItemTypeData> itemTypes = this.getTemplete().query(sql, params, new RowMapper<ItemTypeData> () {
				@Override
				public ItemTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemTypeData itemTypeData = new ItemTypeData();
					itemTypeData.setId(rs.getLong(1));
					itemTypeData.setName(rs.getString(2));
					itemTypeData.setProviderId(rs.getLong(3));
					itemTypeData.setActive(rs.getBoolean(4));
					itemTypeData.setStatus(rs.getInt(5));
					return itemTypeData;
				}	
			});
			
			return itemTypes;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String name, long providerId) throws BmsSqlException {
		try {
			String sql = itemTypeQuery.getProperty("itemType.isAvailable");
			Object[] params = new Object[] {id, id, name, id, name, providerId};
			List<Long> ids = getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getLong(1);
				}
			});
			
			if (ids.isEmpty()) {
			  return true;
			} else if (ids.size() == 1) {
			  return false;
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
