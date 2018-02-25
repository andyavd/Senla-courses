package eu.senla.andyavd.api.managers;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.enums.SortType;

public interface IRoomManager {

	void addRoom(Room room) throws Exception;

	List<Room> getRooms() throws Exception;

	void updateRoom(Room room) throws Exception;

	void deleteRoom(int id) throws Exception;

	Room getRoomById(int number) throws Exception;

	void changeRoomStatus(int id) throws Exception;

	void changeRoomPrice(int id, Double dailyPrice) throws Exception;

	List<Room> getEmptyRooms() throws Exception;

	List<Room> getUsedRooms() throws Exception;

	Integer getEmptyRoomsNumber(List<Room> entities) throws Exception;

	List<Room> getEmptyRoomsOnDate(LocalDate date) throws Exception;

	List<Room> sortEmptyRooms(SortType type) throws Exception;

	List<Room> sortRooms(SortType type) throws Exception;

	Room cloneRoom(int id) throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}
