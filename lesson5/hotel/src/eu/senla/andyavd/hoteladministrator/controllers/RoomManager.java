package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.managers.IRoomManager;
import eu.senla.andyavd.hoteladministrator.api.storages.IRoomsStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class RoomManager implements IRoomManager {

	private IRoomsStorage roomStorage = new RoomsStorage();

	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	@Override
	public void addRoom(Room room) {
		roomStorage.addRoom(room);
	}

	@Override
	public List<AEntity> getRooms() {
		return roomStorage.getRooms();
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		roomStorage.updateRoom(room, history);
	}

	@Override
	public List<AEntity> showEmptyRooms() {
		return ArrayWorker.getNotNullEmptyRooms(roomStorage.getRooms());
	}

	@Override
	public Integer showEmptyRoomsNumber() {
		return ArrayWorker.getNotNullEmptyRooms(roomStorage.getRooms()).size();
	}

	@Override
	public Room getRoomDetails(Room room) {

		for (int i = 0; i < roomStorage.getRooms().size(); i++) {
			if (roomStorage.getRooms().get(i) == room) {
				room = (Room) roomStorage.getRooms().get(i);
			}
		}
		return room;
	}

	@Override
	public List<AEntity> sortEmptyRooms(Comparator<AEntity> comparator) {
		List<AEntity> sortedRooms = ArrayWorker.getNotNullEmptyRooms(roomStorage.getRooms());
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortRooms(Comparator<AEntity> comparator) {
		List<AEntity> sortedRooms = roomStorage.getRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}
	
	@Override
	public Room getRoomById(Integer id) {
		return roomStorage.getRoomById(id);
	}

	@Override
	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(roomStorage.getRooms()), path); 
	}

	@Override
	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}