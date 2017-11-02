package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

import java.util.Arrays;

import com.danco.training.TextFileWorker;

import eu.senla.andyavd.hoteladministrator.entities.Room;

public class RoomsStorage {

	private Room[] rooms = new Room[10];
	private int counter = 0;
	private final TextFileWorker textFileWorker = new TextFileWorker(Path.ROOM_STORAGE_PATH.getPath());

	public void addRoom(Room room) {

		// if (rooms[rooms.length - 1] != null) {
		//
		// rooms = (Room[]) ArrayWorker.expandRoom(rooms);
		// }
		rooms[counter] = room;
		rooms[counter].setId(counter + 1);
		counter++;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void updateRoom(Room room, RoomHistory history) {
		for (int i = 0; i < room.getHistories().length; i++) {
			if (room.getHistories()[i] == null) {
				room.getHistories()[i] = history;
				break;
			}
		}
	}

	public void save() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(rooms), ArrayWorker.getArraySize(rooms));
		textFileWorker.writeToFile(stringArray);
	}

	public void load() {
		String[] stringArray = textFileWorker.readFromFile();

		for (int i = 0; i < stringArray.length; i++) {

			String[] line = stringArray[i].trim().split("--");
			String s = "";
			for (int j = 4; j < line.length; j++) {
	 			s += line[j] + "--";
			}
			rooms[i] = new Room(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2]), Double.parseDouble(line[3]));
			
            counter++;
			
		}	
	}
}
