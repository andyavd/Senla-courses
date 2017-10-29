package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Room;

public class RoomsStorage {

	private Room[] rooms = new Room[10];

	public void addRoom(Room room) {
		for (int i = 0; i < rooms.length; i++) {
//			 if(i == rooms.length - 1) {
//			 Room[] stretchedArray = new Room[rooms.length * 2];
//			 System.arraycopy(rooms, 0, stretchedArray, 0, rooms.length - 1);
//			 rooms = stretchedArray;
//			 }
			if (rooms[i] == null) {
				rooms[i] = room;
				rooms[i].setId(i + 1);
				break;
			}
		}
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