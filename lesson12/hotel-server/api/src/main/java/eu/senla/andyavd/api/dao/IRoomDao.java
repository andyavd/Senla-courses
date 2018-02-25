package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.enums.SortType;

public interface IRoomDao extends IGenericDao<Room> {
	List<Room> getEmpyRooms(Connection connection, SortType type) throws Exception;

	List<Room> getEmptyRoomsOnDate(Connection connection, String date) throws Exception;

	List<Room> getUsedRooms(Connection connection) throws Exception;

	Integer getEmptyRoomsNumber(Connection connection) throws Exception;

	void changeRoomStatus(Connection connection, int id) throws Exception;

	void changeRoomPrice(Connection connection, int id, Double dailyPrice) throws Exception;
}
