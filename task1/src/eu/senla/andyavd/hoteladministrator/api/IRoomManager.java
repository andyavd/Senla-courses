package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public List<Entity> getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public List<Entity> showEmptyRooms();

	public Integer showEmptyRoomsNumber();

	public Room getRoomDetails(Room room);

	@SuppressWarnings("rawtypes")
	public void sortEmptyRooms(Comparator comparator);

	@SuppressWarnings("rawtypes")
	public void sortRooms(Comparator comparator);

}