package eu.senla.andyavd.hoteladministrator.api.managers;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomManager {

	public void addRoom(Room room);

	public List<AEntity> getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public List<AEntity> showEmptyRooms();

	public Integer showEmptyRoomsNumber();

	public Room getRoomDetails(Room room);

	public List<AEntity> sortEmptyRooms(Comparator<AEntity> comparator);

	public List<AEntity> sortRooms(Comparator<AEntity> comparator);
	
	public Room getRoomById(Integer number);

	public void saveToFile();

	public String[] loadFromFile();
}