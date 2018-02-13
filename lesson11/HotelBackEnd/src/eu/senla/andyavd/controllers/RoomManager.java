package eu.senla.andyavd.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.api.controllers.IRoomManager;
import eu.senla.andyavd.api.dao.IRoomDao;
import eu.senla.andyavd.api.storages.IRoomsStorage;

public class RoomManager implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomManager.class);
	private IRoomsStorage roomsStorage = (IRoomsStorage) DependencyInjection.getInstance().getInstance(IRoomsStorage.class);
	private IRoomDao roomDao = (IRoomDao) DependencyInjection.getInstance().getInstance(IRoomDao.class);
	private DataBaseAccess dataBaseAccess;
	public RoomManager() {
	}
	
	@Override
	public void addRoom(Room room) {
		try{
			roomDao.create(dataBaseAccess.getConnection(), room);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}

	@Override
	public void deleteRoom(Room room) {
		try{
			roomDao.delete(dataBaseAccess.getConnection(), room.getId());
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}
	//TODO
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
		try{
			return roomDao.getAll(dataBaseAccess.getConnection());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	//TODO - add history
	@Override
	public void updateRoom(Room room, RoomHistory history) {
		roomsStorage.updateRoom(room, history);
	}
	// What the...
	@Override
	public void updateStorage(int id, Room room) {
		getRooms().set(id - 1, room);
	}

	@Override
	public List<Room> getEmptyRooms() {
		try{
			return roomDao.getEmpyRooms(dataBaseAccess.getConnection());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}

	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) {
		try{
			return roomDao.getEmptyRoomsNumber(dataBaseAccess.getConnection());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	//Same as get by id
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
		try{
			return roomDao.getEmptyRoomsOnDate(dataBaseAccess.getConnection(), date.toString());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	//ReDo with the same logic as here
	@Override
	public List<Room> sortEmptyRooms(Comparator<Room> comparator) {
		List<Room> sortedRooms = getEmptyRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}
	//ReDo with the same logic as here
	@Override
	public List<Room> sortRooms(Comparator<Room> comparator) {
		List<Room> sortedRooms = roomsStorage.getRooms();
		Collections.sort(sortedRooms, comparator);
		return sortedRooms;
	}

	@Override
	public Room getRoomById(Integer id) {
		try{
			return roomDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}

	@Override
	public void importFromCsv() {
		roomsStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
		roomsStorage.exportToCsv();
	}
}
