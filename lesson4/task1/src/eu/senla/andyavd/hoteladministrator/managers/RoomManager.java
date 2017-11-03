package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.api.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class RoomManager implements IRoomManager {

	public RoomsStorage rs = new RoomsStorage();
	ArrayWorker aw = new ArrayWorker();

	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	private Room[] castEntitiesArray(Entity[] entities) {
		Room[] roomArray = Arrays.copyOf(entities, entities.length, Room[].class);
		return roomArray;
	}

	@Override
	public void addRoom(Room room) {
		rs.addRoom(room);
	}

	@Override
	public Room[] getRooms() {
		return rs.getRooms();
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		rs.updateRoom(room, history);
	}

	@Override
	public Room[] showEmptyRooms() {
		return aw.getNotNullEmptyRooms(rs.getRooms());
	}

	@Override
	public Integer showEmptyRoomsNumber() {
		return aw.getNotNullEmptyRooms(rs.getRooms()).length;
	}

	@Override
	public Room showRoomDetails(Room room) {

		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] == room) {
				room = rs.getRooms()[i];
			}
		}
		return room;
	}

	/* ======================sorting============================ */

	@Override
	public void sortEmptyRooms(Comparator comparator) {
		Arrays.sort(aw.getNotNullEmptyRooms(rs.getRooms()), comparator);
	}

	@Override
	public void sortRooms(Comparator comparator) {
		Arrays.sort(rs.getRooms(), comparator);
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(rs.getRooms()),
				ArrayWorker.getArraySize(rs.getRooms()));
		FileWriter.writeToFile(stringArray, path);
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}
