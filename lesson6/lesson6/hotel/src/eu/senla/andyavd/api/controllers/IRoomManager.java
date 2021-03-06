package lesson6.hotel.src.eu.senla.andyavd.api.controllers;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import lesson6.hotel.src.eu.senla.andyavd.entities.Room;
import lesson6.hotel.src.eu.senla.andyavd.entities.RoomHistory;

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

	public void setRooms(List<Room> entities);
	
	public List<Room> getEmptyRoomsOnDate(LocalDate date);

	public Room cloneRoom(Room room);

	public void deleteRoom(Room room);
	
}
