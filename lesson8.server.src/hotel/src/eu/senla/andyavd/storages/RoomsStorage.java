package hotel.src.eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import annotations.src.eu.senla.andyavd.annotations.Storage;
import hotel.src.eu.senla.andyavd.api.storages.IRoomsStorage;
import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.entities.RoomHistory;
import hotel.src.eu.senla.andyavd.utils.csvutils.CommonCSVReader;
import hotel.src.eu.senla.andyavd.utils.csvutils.CommonCSVWriter;

@Storage
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
	public synchronized void addRoom(Room room) {
		rooms.add(room);
		rooms.get(rooms.size()-1).setId(rooms.size());
	}

	@Override
	public synchronized void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public List<Room> getRooms() {
		return rooms;
	}

	@Override
	public synchronized void updateRoom(Room room, RoomHistory history) {
		room.getHistories().add(history);
		
	}
	
	@Override
	public synchronized void updateStorage(int id, Room room) {
		rooms.set(id - 1, room);
	}
	
	@Override
	public synchronized void deleteRoom(Room room) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i) == room) {
				rooms.remove(i);
			}
		}
	}

	@Override
	public Room getRoomById(Integer id) {
		
		Room room = null;
		
		for(int i=0; i<rooms.size(); i++) {
			if( (rooms.get(i)).getId() == id ) {
				room = rooms.get(i);
			} 
		}
		return room;
	}
	
	@Override
	public void importFromCsv() {
		@SuppressWarnings("unchecked")
		List<Room> importedRooms = (List<Room>) CommonCSVReader.readFromFile(Room.class);
		for (Room room : importedRooms) {
			if (!rooms.contains(room)) {
				rooms.add(room);
			}
		}
	}

	@Override
	public void exportToCsv() {
		CommonCSVWriter.writeToFile(rooms);
	}
}
