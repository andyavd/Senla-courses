package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.Room;
import eu.senla.andyavd.enums.SortType;

public interface IRoomDao extends IAEntityDao<Room>{
	List<Room> getEmpyRooms(Connection connection, SortType type);
	List<Room> getEmptyRoomsOnDate(Connection connection, String date);
	List<Room> getUsedRooms(Connection connection);
	Integer getEmptyRoomsNumber(Connection connection);
	void changeRoomStatus(Connection connection, int id);
	void changeRoomPrice(Connection connection, int id, Double dailyPrice);
}
