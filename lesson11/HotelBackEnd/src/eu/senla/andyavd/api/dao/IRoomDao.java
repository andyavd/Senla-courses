package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.Room;

public interface IRoomDao extends IAEntityDao<Room>{
	List<Room> getEmpyRooms(Connection connection);
	List<Room> getEmptyRoomsOnDate(Connection connection, String date);
	Integer getEmptyRoomsNumber(Connection connection);
	List<Room> sortByCapacity(Connection connection);
	List<Room> sortByPrice(Connection connection);
	List<Room> sortByStars(Connection connection);
	List<Room> sortEmptyByCapacity(Connection connection);
	List<Room> sortEmptyByPrice(Connection connection);
	List<Room> sortEmptyByStars(Connection connection);
}
