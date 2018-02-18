package eu.senla.andyavd.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.api.controllers.IRoomManager;
import eu.senla.andyavd.api.dao.IRoomDao;
import eu.senla.andyavd.dao.RoomDao;
import eu.senla.andyavd.enums.SortType;

public class RoomManager implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomManager.class);
	private IRoomDao roomDao = new RoomDao();
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
	public List<Room> getRooms() {
		try{
			return roomDao.getAll(dataBaseAccess.getConnection(), SortType.room_number);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public void updateRoom(Room room) {
		try{
			roomDao.update(dataBaseAccess.getConnection(), room);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}
	@Override
	public void deleteRoom(int id) {
		try{
			roomDao.delete(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}
	@Override
	public Room getRoomById(int id) {
		try{
			return roomDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public void changeRoomStatus(int id) {
		Connection connetcion = dataBaseAccess.getConnection();
		Room room = null;
		try {
			room = roomDao.getById(connetcion, id);
			if(room.getStatus().equals("Empty")) {
				try {
					roomDao.changeRoomStatus(connetcion, room.getId());
				} catch (Exception e) {
					logger.error("Failed to change the Room status!", e);
		     	}
			} else {
				Printer.print("The Room can't be serviced now!");
			}
		} catch (SQLException e1) {
			logger.error("Failed to get the Room!", e1);
		}
	}
	@Override
	public void changeRoomPrice(int id, Double dailyPrice) {
		roomDao.changeRoomPrice(dataBaseAccess.getConnection(), id, dailyPrice);
	}
	@Override
	public List<Room> getEmptyRooms() {
		try{
			return roomDao.getEmpyRooms(dataBaseAccess.getConnection(), SortType.room_number);
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
	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) {
		try{
			return roomDao.getEmptyRoomsOnDate(dataBaseAccess.getConnection(), date.toString());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public List<Room> sortRooms(SortType type) {
		try{
			return roomDao.getAll(dataBaseAccess.getConnection(), type);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public List<Room> sortEmptyRooms(SortType type) {
		try{
			return roomDao.getEmpyRooms(dataBaseAccess.getConnection(), type);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public Room cloneRoom(int id) {
		Room clone = null;
		Connection connetcion = dataBaseAccess.getConnection();
		try {
			connetcion.setAutoCommit(false);
			clone = roomDao.getById(connetcion, id);
			roomDao.create(connetcion, clone);
			connetcion.commit();
			connetcion.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("Failed to clone the Room!", e);
    		}
		return clone;
	}
	@Override
	public List<Room> getUsedRooms() {
		return roomDao.getUsedRooms(dataBaseAccess.getConnection());
	}
	@Override
	public void importFromCsv() {
//		@SuppressWarnings("unchecked")
//		List<Room> importedRooms = (List<Room>) CommonCSVReader.readFromFile(Room.class);
//		for (Room room : importedRooms) {
//			if (!rooms.contains(room)) {
//				rooms.add(room);
//			}
//		}
	}
	@Override
	public void exportToCsv() {
//		CommonCSVWriter.writeToFile(rooms);
	}
}
