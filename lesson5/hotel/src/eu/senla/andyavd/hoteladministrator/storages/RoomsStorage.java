package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IRoomsStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public class RoomsStorage implements IRoomsStorage{

	private List<AEntity> rooms = new ArrayList<AEntity>();
	private int counter = 0;
	
	@Override
	public void addRoom(Room room) {
		rooms.add(room);
		rooms.get(counter).setId(counter + 1);
		counter++;
	}

	@Override
	public void setRooms(List<AEntity> rooms) {
		this.rooms = rooms;
	}

	@Override
	public List<AEntity> getRooms() {
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