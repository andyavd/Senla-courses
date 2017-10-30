package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.entities.Room;

public class RoomsStorage {

	private Room[] rooms = new Room[10];
	private int counter = 0;
	
	public void addRoom(Room room) {
		
//		if (rooms[rooms.length-1] != null) {
//            rooms = (Room[]) ArrayWorker.expand(rooms);
//        }
        rooms[counter] = room;
        rooms[counter].setId(counter + 1);
        counter++;
	}

	public Room[] getRooms() {
		return rooms;
	}
	
	public void updateRoom(Room room, RoomHistory history) {
		for(int i=0; i<room.getHistories().length; i++) {
			if(room.getHistories()[i] == null) {
				room.getHistories()[i] = history;
				break;
			}
		}
	}
}
