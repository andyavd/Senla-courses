package eu.senla.andyavd.hoteladministrator.actions;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;


public interface IRoomManager {

	public void addRoom(Room room);
	public void showRooms();
	public void updateRoom(Room room, RoomHistory history);
	public void showEmptyRooms();
	public Integer showEmptyRoomsNumber();
	public Room showRoomDetails(int roomNumber);
	public void showRoomsEmptyOnAParticularDate(String date);
	
	public void sortEmptyRoomsByPrice();
	public void sortEmptyRoomsByCapacity();
	public void sortEmptyRoomsByStars();
	public void sortRoomsByPrice();
	public void sortRoomsByCapacity();
	public void sortRoomsByStars();

}