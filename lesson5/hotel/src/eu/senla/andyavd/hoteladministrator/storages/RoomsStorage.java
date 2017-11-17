package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IRoomsStorage;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public class RoomsStorage implements IRoomsStorage{

	private List<Room> rooms = new ArrayList<Room>();
	
	private static RoomsStorage roomsStorage;

	public static RoomsStorage getInstance() {
		if (roomsStorage == null) {
			roomsStorage = new RoomsStorage();
		}
		return roomsStorage;
	}
	
	@Override
	public void addRoom(Room room) {
		rooms.add(room);
		for (int i = 0; i < rooms.size(); i++) {
			rooms.get(rooms.size()-1).setId(rooms.size());
		}
	}

	@Override
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public List<Room> getRooms() {
		return rooms;
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		room.getHistories().add(history);
		
	}
	
	@Override
	public Room getRoomById(Integer id) {
		
		Room room = null;
		
		for(int i=0; i<rooms.size(); i++) {
			if( ((Room) rooms.get(i)).getId() == id ) {
				room = (Room) rooms.get(i);
			} 
		}
		return room;
	}
}
