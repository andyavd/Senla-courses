package eu.senla.andyavd.api.controllers;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public List<Room> getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public void updateStorage(int id, Room room);
	
	public List<Room> getEmptyRooms();

	public Integer getEmptyRoomsNumber(List<Room> entities);

	public Room getRoomDetails(Room room);

	public List<Room> sortEmptyRooms(Comparator<Room> comparator);

	public List<Room> sortRooms(Comparator<Room> comparator);
	
	public Room getRoomById(Integer number);
	
	public List<Room> getEmptyRoomsOnDate(LocalDate date);

	public Room cloneRoom(Room room);

	public void deleteRoom(Room room);

	public void importFromCsv();

	public void exportToCsv();
	
}
