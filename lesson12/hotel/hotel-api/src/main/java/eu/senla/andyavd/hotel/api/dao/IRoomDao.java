package eu.senla.andyavd.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IRoomDao extends IGenericDao<Room> {
	
	List<Room> getEmpyRooms(Session session, SortType type) throws Exception;

	List<Room> getEmptyRoomsOnDate(Session session, String date) throws Exception;

	List<Room> getUsedRooms(Session session) throws Exception;

	Integer getEmptyRoomsNumber(Session session) throws Exception;

	void changeRoomStatus(Session session, int id) throws Exception;

	void changeRoomPrice(Session session, int id, Double dailyPrice) throws Exception;
}
