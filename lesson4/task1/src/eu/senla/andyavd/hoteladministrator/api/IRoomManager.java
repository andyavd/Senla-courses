package eu.senla.andyavd.hoteladministrator.api;

import java.time.LocalDate;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public Room[] getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public Room[] showEmptyRooms();

	public Integer showEmptyRoomsNumber();

	public Room showRoomDetails(Room room);

	public void sortEmptyRooms(Comparator comparator);

	public void sortRooms(Comparator comparator);

}
