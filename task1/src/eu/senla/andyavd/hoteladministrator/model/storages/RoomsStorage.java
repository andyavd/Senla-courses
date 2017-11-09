package eu.senla.andyavd.hoteladministrator.model.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;

public class RoomsStorage {

	private List<Entity> rooms = new ArrayList<Entity>();
	private int counter = 0;
	
	public void addRoom(Room room) {
		rooms.add(room);
		rooms.get(counter).setId(counter + 1);
		counter++;
	}

	public void setRooms(List<Entity> rooms) {
		this.rooms = rooms;
	}

	public List<Entity> getRooms() {
		return rooms;
	}

	public void updateRoom(Room room, RoomHistory history) {
		room.getHistories().add(history);
		
	}
}