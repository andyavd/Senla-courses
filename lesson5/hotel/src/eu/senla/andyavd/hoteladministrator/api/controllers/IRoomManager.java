package eu.senla.andyavd.hoteladministrator.api.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public List<Room> getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public void updateStorage(int id, Room room);
	
	public List<Room> getEmptyRooms(List<Room> entities);

	public Integer getEmptyRoomsNumber(List<Room> entities);

	public Room getRoomDetails(Room room);

	public List<Room> sortEmptyRooms(Comparator<Room> comparator);

	public List<Room> sortRooms(Comparator<Room> comparator);
	
	public Room getRoomById(Integer number);

	public void setRooms(List<Room> entities);
	
	public List<Room> getEmptyRoomsOnDate(LocalDate date);
	
	public void cloneRoom(Room room) throws IOException, ClassNotFoundException;
	
}
