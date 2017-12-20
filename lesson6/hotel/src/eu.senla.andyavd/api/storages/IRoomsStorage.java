package eu.senla.andyavd.hoteladministrator.api.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomsStorage {

	public void addRoom(Room room);

	public void setRooms(List<Room> rooms);

	public List<Room> getRooms();

	public void updateRoom(Room room, RoomHistory history);

	public Room getRoomById(Integer id);

	public void updateStorage(int id, Room room);

	public void deleteRoom(Room room);

}
