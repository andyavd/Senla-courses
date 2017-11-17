package eu.senla.andyavd.hoteladministrator.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.controllers.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class RoomManager implements IRoomManager {
	
	FileReader fileReader = new FileReader();
	FileWriter fileWriter = new FileWriter();
	
	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	@Override
	public void addRoom(Room room) {
		RoomsStorage.getInstance().addRoom(room);
	}

	@Override
	public List<Room> getRooms() {
		return RoomsStorage.getInstance().getRooms();
	}

	@Override
	public void setRooms(List<Room> entities) {
		RoomsStorage.getInstance().setRooms(entities);
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		RoomsStorage.getInstance().updateRoom(room, history);
	}

	@Override
	public List<Room> getEmptyRooms(List<Room> entities) {

		List<Room> newEntity = new ArrayList<Room>();

		for (int i = 0; i < entities.size(); i++) {
			if (((Room) entities.get(i)).getStatus() == RoomStatus.EMPTY)
				newEntity.add(entities.get(i));
		}
		return newEntity;
	}

	@Override
	public Integer getEmptyRoomsNumber(List<Room> entities) {
		return entities.size();
	}

	@Override
	public Room getRoomDetails(Room room) {

		for (int i = 0; i < RoomsStorage.getInstance().getRooms().size(); i++) {
			if (RoomsStorage.getInstance().getRooms().get(i).equals(room)) {
				room = RoomsStorage.getInstance().getRooms().get(i);
			}
		}
		return room;
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) {

		List<Room> emptyRoomsOnDate = new ArrayList<Room>();

		for (int i = 0; i < getRooms().size(); i++) {
			if (getRooms().get(i) != null && ((Room) getRooms().get(i)).getStatus() == RoomStatus.OCCUPIED) {

				int r = 0;
				for (int k = 0; k < ((Room) getRooms().get(i)).getHistories().size(); k++) {

					if (((Room) getRooms().get(i)).getHistories().get(k) != null
							&& (date.isBefore(((Room) getRooms().get(i)).getHistories().get(k).getCheckInDate()) || date
									.isAfter(((Room) getRooms().get(i)).getHistories().get(k).getCheckOutDate()))) {
						r = 1;
						continue;
					}

					if (r == 1) {
						emptyRoomsOnDate.add(getRooms().get(i));
						break;
					}
				}
			}
		}

		for (int i = 0; i < getRooms().size(); i++) {
			if (getRooms().get(i) != null && ((Room) getRooms().get(i)).getStatus() == RoomStatus.EMPTY) {
				emptyRoomsOnDate.add((getRooms().get(i)));
			}
		}
		return emptyRoomsOnDate;
	}

	@Override
	public List<Room> sortEmptyRooms(Comparator<Room> comparator) {
		List<Room> sortedRooms = getEmptyRooms(RoomsStorage.getInstance().getRooms());
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public List<Room> sortRooms(Comparator<Room> comparator) {
		List<Room> sortedRooms = RoomsStorage.getInstance().getRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public Room getRoomById(Integer id) {
		return RoomsStorage.getInstance().getRoomById(id);
	}

	@Override
	public void saveToFile() {
		fileWriter.writeToFile(ArrayWorker.arrayToString(RoomsStorage.getInstance().getRooms()), path);
	}

	@Override
	public String[] loadFromFile() {
		return fileReader.readFromFile(path);
	}
}
