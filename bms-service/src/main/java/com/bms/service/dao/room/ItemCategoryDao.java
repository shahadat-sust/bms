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
import com.bms.service.data.room.ItemCategoryData;
import com.bms.service.data.room.RoomCategoryData;

@Repository("itemCategoryDao")
public class ItemCategoryDao extends BaseDao implements IItemCategoryDao {

	@Autowired
	@Qualifier("itemCategoryQuery")
	private Properties itemCategoryQuery;
	
	@Override
	public long create(ItemCategoryData itemCategoryData) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, itemCategoryData.getName());
					ps.setLong(2, itemCategoryData.getItemTypeId());
					ps.setDouble(3, itemCategoryData.getRent());
					ps.setInt(4, itemCategoryData.getCapacity());
					ps.setBoolean(5, itemCategoryData.isActive());
					ps.setInt(6, itemCategoryData.getStatus());
					ps.setLong(7, itemCategoryData.getCreatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(itemCategoryData.getCreatedOn().getTime()));
					ps.setLong(9, itemCategoryData.getUpdatedBy());
					ps.setTimestamp(10, new java.sql.Timestamp(itemCategoryData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long itemCategoryId = holder.getKey().longValue();
			
			String sql1 = itemCategoryQuery.getProperty("roomCategory.create");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
					ps.setLong(1, itemCategoryId);
					ps.setInt(2, itemCategoryData.getRoomCategoryData().getSize());
					ps.setLong(3, itemCategoryData.getRoomCategoryData().getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(itemCategoryData.getRoomCategoryData().getCreatedOn().getTime()));
					ps.setLong(5, itemCategoryData.getRoomCategoryData().getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(itemCategoryData.getRoomCategoryData().getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
			
			return itemCategoryId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ItemCategoryData itemCategoryData) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.update");
			boolean status = this.getTemplete().update(sql,
					itemCategoryData.getName(),
					itemCategoryData.getItemTypeId(),
					itemCategoryData.getRent(),
					itemCategoryData.getCapacity(),
					itemCategoryData.isActive() ? 1 : 0,
					itemCategoryData.getUpdatedBy(),
					new Timestamp(itemCategoryData.getUpdatedOn().getTime()),
					itemCategoryData.getId()) == 1;
			
			if (status) {
				sql = itemCategoryQuery.getProperty("roomCategory.update");
				status = this.getTemplete().update(sql,
						itemCategoryData.getRoomCategoryData().getSize(),
						itemCategoryData.getRoomCategoryData().getUpdatedBy(),
						new Timestamp(itemCategoryData.getRoomCategoryData().getUpdatedOn().getTime()),
						itemCategoryData.getId()) == 1;
			}
			
			return status;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(ItemCategoryData itemCategoryData) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.delete"); 
			return this.getTemplete().update(sql, 
					Constants.STATUS_NOT_EXIST,
					itemCategoryData.getUpdatedBy(),
					new Timestamp(itemCategoryData.getUpdatedOn().getTime()),
					itemCategoryData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ItemCategoryData getItemCategoryById(long itemCategoryId) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.getItemCategoryById");
			Object[] params = new Object[] {itemCategoryId};
			List<ItemCategoryData> itemCategoryDatas = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryData> () {
				@Override
				public ItemCategoryData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemCategoryData itemCategoryData = new ItemCategoryData();
					itemCategoryData.setId(rs.getLong(1));
					itemCategoryData.setName(rs.getString(2));
					itemCategoryData.setItemTypeId(rs.getLong(3));
					itemCategoryData.setItemTypeName(rs.getString(4));
					itemCategoryData.setRent(rs.getDouble(5));
					itemCategoryData.setCapacity(rs.getInt(6));
					itemCategoryData.setActive(rs.getBoolean(7));
					itemCategoryData.setStatus(rs.getInt(8));
					itemCategoryData.setRoomCategoryData(new RoomCategoryData());
					itemCategoryData.getRoomCategoryData().setId(rs.getLong(9));
					itemCategoryData.getRoomCategoryData().setSize(rs.getInt(10));
					return itemCategoryData;
				}	
			});
			
			if (itemCategoryDatas.isEmpty()) {
				return null;
			} else if (itemCategoryDatas.size() == 1) {
				return itemCategoryDatas.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ItemCategoryData> getAllItemCategoriesByItemTypeId(long itemTypeId) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.getAllItemCategoriesByItemTypeId");
			Object[] params = new Object[] {itemTypeId};
			List<ItemCategoryData> itemCategoryDatas = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryData> () {
				@Override
				public ItemCategoryData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemCategoryData itemCategoryData = new ItemCategoryData();
					itemCategoryData.setId(rs.getLong(1));
					itemCategoryData.setName(rs.getString(2));
					itemCategoryData.setItemTypeId(rs.getLong(3));
					itemCategoryData.setItemTypeName(rs.getString(4));
					itemCategoryData.setRent(rs.getDouble(5));
					itemCategoryData.setCapacity(rs.getInt(6));
					itemCategoryData.setActive(rs.getBoolean(7));
					itemCategoryData.setStatus(rs.getInt(8));
					itemCategoryData.setRoomCategoryData(new RoomCategoryData());
					itemCategoryData.getRoomCategoryData().setId(rs.getLong(9));
					itemCategoryData.getRoomCategoryData().setSize(rs.getInt(10));
					return itemCategoryData;
				}	
			});
			
			return itemCategoryDatas;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ItemCategoryData> getAllItemCategoriesByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.getAllItemCategoriesByProviderId");
			Object[] params = new Object[] {providerId};
			List<ItemCategoryData> itemCategoryDatas = this.getTemplete().query(sql, params, new RowMapper<ItemCategoryData> () {
				@Override
				public ItemCategoryData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemCategoryData itemCategoryData = new ItemCategoryData();
					itemCategoryData.setId(rs.getLong(1));
					itemCategoryData.setName(rs.getString(2));
					itemCategoryData.setItemTypeId(rs.getLong(3));
					itemCategoryData.setItemTypeName(rs.getString(4));
					itemCategoryData.setRent(rs.getDouble(5));
					itemCategoryData.setCapacity(rs.getInt(6));
					itemCategoryData.setActive(rs.getBoolean(7));
					itemCategoryData.setStatus(rs.getInt(8));
					itemCategoryData.setRoomCategoryData(new RoomCategoryData());
					itemCategoryData.getRoomCategoryData().setId(rs.getLong(9));
					itemCategoryData.getRoomCategoryData().setSize(rs.getInt(10));
					return itemCategoryData;
				}	
			});
			
			return itemCategoryDatas;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long itemCategoryId, String name, long itemTypeId) throws BmsSqlException {
		try {
			String sql = itemCategoryQuery.getProperty("itemCategory.isAvailable");
			Object[] params = new Object[] {itemCategoryId, itemCategoryId, name, itemCategoryId, name, itemTypeId};
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
