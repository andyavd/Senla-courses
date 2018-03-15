package eu.senla.andyavd.hotel.api.services;

import java.util.Date;
import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IRoomService {

	void addRoom(Room room) throws Exception;

	List<Room> getRooms(SortType type) throws Exception;

	void updateRoom(Room room) throws Exception;

	void deleteRoom(Room room) throws Exception;

	Room getRoomById(int number) throws Exception;

	void changeRoomStatus(int id) throws Exception;

	void changeRoomPrice(int id, Double dailyPrice) throws Exception;

	List<Room> getEmptyRooms(SortType type) throws Exception;

	List<Room> getUsedRooms() throws Exception;

	Integer getEmptyRoomsNumber() throws Exception;

	List<Room> getEmptyRoomsOnDate(Date date) throws Exception;
	
	Room getVisitorsRoom(int visitorId) throws Exception;

	void cloneRoom(int id) throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}
