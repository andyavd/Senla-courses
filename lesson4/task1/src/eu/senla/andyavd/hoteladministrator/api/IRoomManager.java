package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public Room[] getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public Entity[] getEmptyRooms();

	public Integer getEmptyRoomsNumber();

	public Room getRoomDetails(Room room);

	public Room[] sortEmptyRooms(Comparator<Room> comparator);

	public Room[] sortRooms(Comparator<Room>comparator);

}
