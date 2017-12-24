package lesson7.hotel.src.eu.senla.andyavd.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import lesson7.dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import lesson7.hotel.src.eu.senla.andyavd.api.controllers.IRoomManager;
import lesson7.hotel.src.eu.senla.andyavd.api.storages.IRoomsStorage;
import lesson7.hotel.src.eu.senla.andyavd.entities.Room;
import lesson7.hotel.src.eu.senla.andyavd.entities.RoomHistory;
import lesson7.hotel.src.eu.senla.andyavd.enums.RoomStatus;

public class RoomManager implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomManager.class);
	
	private IRoomsStorage roomsStorage = (IRoomsStorage) DependencyInjection.getInstance().getInstance(IRoomsStorage.class);
	
	public RoomManager() {
	}
	
	@Override
	public void addRoom(Room room) {
		roomsStorage.addRoom(room);
	}

	@Override
	public void deleteRoom(Room room) {
		roomsStorage.deleteRoom(room);
	}
	
	@Override
	public Room cloneRoom(Room room) {
		
		Room clone = null;
		try {
			clone = room.clone();
		} catch (CloneNotSupportedException e) {
			logger.error("Failed to clone the Room!", e);
     		}
		
		return clone;
	}
	
	@Override
	public List<Room> getRooms() {
		return roomsStorage.getRooms();
	}

	@Override
	public void setRooms(List<Room> rooms) {
		roomsStorage.setRooms(rooms);
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		roomsStorage.updateRoom(room, history);
	}
	
	@Override
	public void updateStorage(int id, Room room) {
		getRooms().set(id - 1, room);
	}

	@Override
	public List<Room> getEmptyRooms() {
		
		List<Room> rooms = roomsStorage.getRooms();
		
		List<Room> newRooms = new ArrayList<Room>();

		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getStatus() == RoomStatus.EMPTY)
				newRooms.add(rooms.get(i));
		}
		return newRooms;
	}

	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) {
		return rooms.size();
	}

	@Override
	public Room getRoomDetails(Room room) {

		for (int i = 0; i < roomsStorage.getRooms().size(); i++) {
			if (roomsStorage.getRooms().get(i).equals(room)) {
				room = roomsStorage.getRooms().get(i);
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
		List<Room> sortedRooms = getEmptyRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public List<Room> sortRooms(Comparator<Room> comparator) {
		List<Room> sortedRooms = roomsStorage.getRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public Room getRoomById(Integer id) {
		return roomsStorage.getRoomById(id);
	}

}
