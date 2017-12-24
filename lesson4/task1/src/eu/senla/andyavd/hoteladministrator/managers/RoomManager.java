package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.api.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class RoomManager implements IRoomManager {

	public RoomsStorage roomsStorage = new RoomsStorage();
	ArrayWorker arrayWorker = new ArrayWorker();

	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	private Room[] castEntitiesArray(Entity[] entities) {
		Room[] roomArray = Arrays.copyOf(entities, entities.length, Room[].class);
		return roomArray;
	}

	@Override
	public void addRoom(Room room) {
		roomsStorage.addRoom(room);
	}

	@Override
	public Room[] getRooms() {
		return roomsStorage.getRooms();
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		roomsStorage.updateRoom(room, history);
	}

	@Override
	public Entity[] getEmptyRooms() {
		Entity[] emptyRooms = new Entity[roomsStorage.getRooms().length];
		for(int i=0; i<roomsStorage.getRooms().length; i++) {
			if (roomsStorage.getRooms()[i] != null && roomsStorage.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				emptyRooms[i] = roomsStorage.getRooms()[i];
			}
		}
		return  ArrayWorker.getNotNullArray(emptyRooms);
	}

	@Override
	public Integer getEmptyRoomsNumber() {
		return this.getEmptyRooms().length;
	}

	@Override
	public Room getRoomDetails(Room room) {

		for (int i = 0; i < roomsStorage.getRooms().length; i++) {
			if (roomsStorage.getRooms()[i] == room) {
				room = roomsStorage.getRooms()[i];
			}
		}
		return room;
	}

	@Override
	public Room[] sortEmptyRooms(Comparator<Room> comparator) {
		Room[] sortedRooms = castEntitiesArray(this.getEmptyRooms());
		Arrays.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public Room[] sortRooms(Comparator<Room> comparator) {
		Room[] sortedRooms = roomsStorage.getRooms();
		Arrays.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(roomsStorage.getRooms()),
				ArrayWorker.getArraySize(roomsStorage.getRooms()));
		FileWriter.writeToFile(stringArray, path);
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}
