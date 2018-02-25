package eu.senla.andyavd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.api.dao.IRoomDao;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.enums.SortType;

public class RoomDao extends GenericDao<Room> implements IRoomDao {

	private final static Logger logger = Logger.getLogger(RoomDao.class);
	private final String CREATE_ROOM = "INSERT INTO room (room_number, capacity, daily_price, stars) VALUES (?,?,?,?) ";
	private final String UPDATE_ROOM = "UPDATE room SET capacity = ?, daily_price = ?, stars = ?, room_status = ? WHERE id = ?";
	private final String CHANGE_STATUS = "UPDATE room SET room_status = 'Serviced' WHERE id = ?";
	private final String CHANGE_PRICE = "UPDATE room SET daily_price = ? WHERE id = ?";
	private final String GET_ROOMS = "SELECT * FROM room";
	private final String GET_ROOM = "SELECT * FROM room WHERE id = ?";
	private final String DELETE_ROOM = "DELETE * FROM room WHERE id = ?";
	private final String ROOM_COUNT = "SELECT count(*) FROM room";
	private final String EMPTY_CASE = " WHERE room_status = 'Empty'";
	private final String USED_ROOMS = "SELECT * FROM room WHERE room.id IN (SELECT distinct room_id FROM history); ";
	private final String ORDER_BY = " ORDER BY";

	public RoomDao() {
	}

	@Override
	protected Room parseResult(ResultSet resultSet) {
		return parseRoom(resultSet);
	}

	@Override
	protected String getInsertQuery() {
		return CREATE_ROOM;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_ROOM;
	}

	@Override
	protected String getDeleteQuery() {
		return DELETE_ROOM;
	}

	@Override
	protected String getByIdQuery() {
		return GET_ROOM;
	}

	@Override
	protected String getAllQuery(SortType type) {
		return GET_ROOMS + ORDER_BY + type;
	}

	@Override
	protected void setInsertPreparedStatement(PreparedStatement statement, Room entity) throws SQLException {
		statement.setInt(1, entity.getRoomNumber());
		statement.setInt(2, entity.getCapasity());
		statement.setDouble(3, entity.getDailyPrice());
		statement.setString(4, entity.getStars());
	}

	@Override
	protected void setUpdatePreparedStatement(PreparedStatement statement, Room entity) throws SQLException {
		statement.setInt(1, entity.getCapasity());
		statement.setDouble(2, entity.getDailyPrice());
		statement.setString(3, entity.getStars());
		statement.setString(4, entity.getStatus());
		statement.setInt(5, entity.getId());
	}

	@Override
	public List<Room> getEmpyRooms(Connection connection, SortType type) throws Exception {
		List<Room> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(GET_ROOMS + EMPTY_CASE + ORDER_BY + type)) {
			ResultSet resultSet = null;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(parseRoom(resultSet));
			}
		} catch (SQLException e) {
			logger.error("Failed to get empty Rooms!");
			throw new Exception();
		}
		return list;
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(Connection connection, String date) throws Exception {
		List<Room> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT room.id, room_number, capacity, daily_price, stars FROM room, history WHERE room.id = history.room_id  AND room_status = 'Occupied'  AND "
						+ date
						+ " NOT BETWEEN check_in and check_out UNION SELECT room.id, room_number, capacity, daily_price, stars FROM room WHERE room_status = 'Empty';")) {
			ResultSet resultSet = null;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(parseRoom(resultSet));
			}
		} catch (SQLException e) {
			logger.error("Failed to get empty Rooms on Date!");
			throw new Exception();
		}
		return list;
	}

	@Override
	public Integer getEmptyRoomsNumber(Connection connection) throws Exception {
		int number = 0;
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(ROOM_COUNT + EMPTY_CASE);
			while (resultSet.next()) {
				number = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("Failed to get empty Rooms number!");
			throw new Exception();
		}
		return number;
	}

	@Override
	public void changeRoomStatus(Connection connection, int id) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Failed to Change room status!");
			throw new Exception();
		}
	}

	@Override
	public void changeRoomPrice(Connection connection, int id, Double dailyPrice) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE)) {
			statement.setDouble(1, dailyPrice);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Failed to Change room price!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getUsedRooms(Connection connection) throws Exception {
		List<Room> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(USED_ROOMS)) {
			ResultSet resultSet = null;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(parseResult(resultSet));
			}
		} catch (SQLException e) {
			logger.error("Failed to get used rooms!");
			throw new Exception();
		}
		return list;
	}

	public static Room parseRoom(ResultSet resultSet) {
		Room room = new Room();
		try {
			room.setId(resultSet.getInt("id"));
			room.setRoomNumber(resultSet.getInt("room_number"));
			room.setCapasity(resultSet.getInt("capacity"));
			room.setDailyPrice(resultSet.getDouble("daily_price"));
			room.setStars(resultSet.getString("stars"));
			room.setStatus(resultSet.getString("room_status"));
		} catch (SQLException e) {
			logger.error("Failed to parse room!");
		}
		return room;
	}
}