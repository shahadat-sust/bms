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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.SqlConstants;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.room.ItemData;
import com.bms.service.data.room.RoomData;

@Repository("itemDao")
public class ItemDao extends BaseDao implements IItemDao {

	@Autowired
	@Qualifier("itemQuery")
	private Properties itemQuery;
	
	@Override
	public long create(ItemData itemData) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setLong(1, itemData.getItemCategoryId());
					ps.setString(2, itemData.getItemNo());
					ps.setDouble(3, itemData.getRent());
					ps.setInt(4, itemData.getAdultCapacity());
					ps.setInt(5, itemData.getChildCapacity());
					ps.setBoolean(6, itemData.isActive());
					ps.setInt(7, itemData.getStatus());
					ps.setLong(8, itemData.getCreatedBy());
					ps.setTimestamp(9, new java.sql.Timestamp(itemData.getCreatedOn().getTime()));
					ps.setLong(10, itemData.getUpdatedBy());
					ps.setTimestamp(11, new java.sql.Timestamp(itemData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long itemId = holder.getKey().longValue();
			
			String sql1 = itemQuery.getProperty("room.create");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
					ps.setLong(1, itemId);
					ps.setInt(2, itemData.getRoomData().getFloorNo());
					ps.setInt(3, itemData.getRoomData().getSize());
					ps.setInt(4, itemData.getRoomData().getNumberOfBed());
					ps.setString(5, itemData.getRoomData().getLandLine());
					ps.setLong(6, itemData.getRoomData().getCreatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(itemData.getRoomData().getCreatedOn().getTime()));
					ps.setLong(8, itemData.getRoomData().getUpdatedBy());
					ps.setTimestamp(9, new java.sql.Timestamp(itemData.getRoomData().getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
			
			return itemId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ItemData itemData) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.update");
			boolean status = this.getTemplete().update(sql,
					itemData.getItemCategoryId(),
					itemData.getItemNo(),
					itemData.getRent(),
					itemData.getAdultCapacity(),
					itemData.getChildCapacity(),
					itemData.isActive() ? 1 : 0,
					itemData.getUpdatedBy(),
					new Timestamp(itemData.getUpdatedOn().getTime()),
					itemData.getId()) == 1;
			
			if (status) {
				sql = itemQuery.getProperty("room.update");
				status = this.getTemplete().update(sql,
						itemData.getRoomData().getFloorNo(),
						itemData.getRoomData().getSize(),
						itemData.getRoomData().getNumberOfBed(),
						itemData.getRoomData().getLandLine(),
						itemData.getRoomData().getUpdatedBy(),
						new Timestamp(itemData.getRoomData().getUpdatedOn().getTime()),
						itemData.getId()) == 1;
			}
			
			return status;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(ItemData itemData) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.delete"); 
			return this.getTemplete().update(sql, 
					Constants.STATUS_NOT_EXIST,
					itemData.getUpdatedBy(),
					new Timestamp(itemData.getUpdatedOn().getTime()),
					itemData.getId()) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		}  catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ItemData getItemById(long itemId) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.getItemById");
			Object[] params = new Object[] {itemId};
			List<ItemData> itemDatas = this.getTemplete().query(sql, params, new RowMapper<ItemData> () {
				@Override
				public ItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemData itemData = new ItemData();
					itemData.setId(rs.getLong(1));
					itemData.setItemCategoryId(rs.getLong(2));
					itemData.setItemCategoryName(rs.getString(3));
					itemData.setItemNo(rs.getString(4));
					itemData.setRent(rs.getDouble(5));
					itemData.setAdultCapacity(rs.getInt(6));
					itemData.setChildCapacity(rs.getInt(7));
					itemData.setActive(rs.getBoolean(8));
					itemData.setStatus(rs.getInt(9));
					itemData.setItemTypeId(rs.getLong(10));
					itemData.setItemTypeName(rs.getString(11));
					itemData.setRoomData(new RoomData());
					itemData.getRoomData().setId(rs.getLong(12));
					itemData.getRoomData().setFloorNo(rs.getInt(13));
					itemData.getRoomData().setSize(rs.getInt(14));
					itemData.getRoomData().setNumberOfBed(rs.getInt(15));
					itemData.getRoomData().setLandLine(rs.getString(16));
					return itemData;
				}	
			});
			
			if (itemDatas.isEmpty()) {
				return null;
			} else if (itemDatas.size() == 1) {
				return itemDatas.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<ItemData> getAllItemsByItemCategoryId(long itemCategoryId) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.getAllItemsByItemCategoryId");
			Object[] params = new Object[] {itemCategoryId};
			List<ItemData> itemDatas = this.getTemplete().query(sql, params, new RowMapper<ItemData> () {
				@Override
				public ItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemData itemData = new ItemData();
					itemData.setId(rs.getLong(1));
					itemData.setItemCategoryId(rs.getLong(2));
					itemData.setItemCategoryName(rs.getString(3));
					itemData.setItemNo(rs.getString(4));
					itemData.setRent(rs.getDouble(5));
					itemData.setAdultCapacity(rs.getInt(6));
					itemData.setChildCapacity(rs.getInt(7));
					itemData.setActive(rs.getBoolean(8));
					itemData.setStatus(rs.getInt(9));
					itemData.setItemTypeId(rs.getLong(10));
					itemData.setItemTypeName(rs.getString(11));
					itemData.setRoomData(new RoomData());
					itemData.getRoomData().setId(rs.getLong(12));
					itemData.getRoomData().setFloorNo(rs.getInt(13));
					itemData.getRoomData().setSize(rs.getInt(14));
					itemData.getRoomData().setNumberOfBed(rs.getInt(15));
					itemData.getRoomData().setLandLine(rs.getString(16));
					return itemData;
				}	
			});
			
			return itemDatas;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ItemData> getAllItemsByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.getAllItemsByProviderId");
			Object[] params = new Object[] {providerId};
			List<ItemData> itemDatas = this.getTemplete().query(sql, params, new RowMapper<ItemData> () {
				@Override
				public ItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ItemData itemData = new ItemData();
					itemData.setId(rs.getLong(1));
					itemData.setItemCategoryId(rs.getLong(2));
					itemData.setItemCategoryName(rs.getString(3));
					itemData.setItemNo(rs.getString(4));
					itemData.setRent(rs.getDouble(5));
					itemData.setAdultCapacity(rs.getInt(6));
					itemData.setChildCapacity(rs.getInt(7));
					itemData.setActive(rs.getBoolean(8));
					itemData.setStatus(rs.getInt(9));
					itemData.setItemTypeId(rs.getLong(10));
					itemData.setItemTypeName(rs.getString(11));
					itemData.setRoomData(new RoomData());
					itemData.getRoomData().setId(rs.getLong(12));
					itemData.getRoomData().setFloorNo(rs.getInt(13));
					itemData.getRoomData().setSize(rs.getInt(14));
					itemData.getRoomData().setNumberOfBed(rs.getInt(15));
					itemData.getRoomData().setLandLine(rs.getString(16));
					return itemData;
				}	
			});
			
			return itemDatas;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long itemId, String name, long providerId) throws BmsSqlException {
		try {
			String sql = itemQuery.getProperty("item.isAvailable");
			Object[] params = new Object[] {itemId, itemId, name, itemId, name, providerId};
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
