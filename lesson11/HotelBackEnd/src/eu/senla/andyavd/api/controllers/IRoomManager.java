package eu.senla.andyavd.api.controllers;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.Room;
import eu.senla.andyavd.enums.SortType;

public interface IRoomManager {

	void addRoom(Room room);
	List<Room> getRooms();
	void updateRoom(Room room);
	void deleteRoom(int id);
	Room getRoomById(int number);
	void changeRoomStatus(int id);
	void changeRoomPrice(int id, Double dailyPrice);
	List<Room> getEmptyRooms();
	List<Room> getUsedRooms();
	Integer getEmptyRoomsNumber(List<Room> entities);
	List<Room> getEmptyRoomsOnDate(LocalDate date);
	List<Room> sortEmptyRooms(SortType type);
	List<Room> sortRooms(SortType type);
	Room cloneRoom(int id);
	void importFromCsv();
	void exportToCsv();
}
