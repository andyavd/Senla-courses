package eu.senla.andyavd.hoteladministrator.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.api.controllers.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriterSenla;

public class RoomManager implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomManager.class);
	
	FileReader fileReader = new FileReader();
	FileWriterSenla fileWriter = new FileWriterSenla();

	private static final String path = Path.ROOM_STORAGE_PATH.getPath();

	@Override
	public void addRoom(Room room) {
		RoomsStorage.getInstance().addRoom(room);
	}

	@Override
	public void cloneRoom(Room room) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(room);
		objectOutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Room roomClone = (Room) objectInputStream.readObject();
		roomClone.setRoomNumber(roomClone.getRoomNumber() + 100);

		this.addRoom(roomClone);
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
	public void updateStorage(int id, Room room) {
		getRooms().set(id - 1, room);
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
		List<Room> rooms = getRooms();

		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);

			if (room != null && room.getStatus() == RoomStatus.OCCUPIED) {
				List<RoomHistory> roomHistories = room.getHistories();

				for (int k = 0; k < roomHistories.size(); k++) {
					RoomHistory roomHistory = roomHistories.get(k);
					if (roomHistory != null && (date.isBefore(roomHistory.getCheckInDate())
							|| date.isAfter(roomHistory.getCheckOutDate()))) {
						emptyRoomsOnDate.add(room);
					}
				}
			} else if (room != null && room.getStatus() == RoomStatus.EMPTY) {
				emptyRoomsOnDate.add(room);
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

}
