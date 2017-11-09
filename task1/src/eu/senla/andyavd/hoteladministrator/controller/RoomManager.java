package eu.senla.andyavd.hoteladministrator.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.IRoomManager;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class RoomManager implements IRoomManager {

	public RoomsStorage rs = new RoomsStorage();

	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	@Override
	public void addRoom(Room room) {
		rs.addRoom(room);
	}

	@Override
	public List<Entity> getRooms() {
		return rs.getRooms();
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		rs.updateRoom(room, history);
	}

	@Override
	public List<Entity> showEmptyRooms() {
		return ArrayWorker.getNotNullEmptyRooms(rs.getRooms());
	}

	@Override
	public Integer showEmptyRoomsNumber() {
		return ArrayWorker.getNotNullEmptyRooms(rs.getRooms()).size();
	}

	@Override
	public Room getRoomDetails(Room room) {

		for (int i = 0; i < rs.getRooms().size(); i++) {
			if (rs.getRooms().get(i) == room) {
				room = (Room) rs.getRooms().get(i);
			}
		}
		return room;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sortEmptyRooms(Comparator comparator) {
		Collections.sort(ArrayWorker.getNotNullEmptyRooms(rs.getRooms()), comparator);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sortRooms(Comparator comparator) {
		Collections.sort(rs.getRooms(), comparator);
	}

	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(rs.getRooms()), path); 
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}