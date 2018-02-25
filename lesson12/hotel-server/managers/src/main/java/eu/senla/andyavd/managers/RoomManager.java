package eu.senla.andyavd.managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.dbconnector.DataBaseConnector;
import eu.senla.andyavd.dependency.DependencyInjection;
import eu.senla.andyavd.api.dao.IRoomDao;
import eu.senla.andyavd.api.managers.IRoomManager;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.enums.SortType;
import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.utils.csv.CommonCSVReader;
import eu.senla.andyavd.utils.csv.CommonCSVWriter;

public class RoomManager implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomManager.class);
	private IRoomDao roomDao = (IRoomDao) DependencyInjection.getInstance().getInstance(IRoomDao.class);
	private DataBaseConnector dataBaseAccess;

	public RoomManager() {
	}

	@Override
	public void addRoom(Room room) throws Exception {
		try {
			roomDao.create(dataBaseAccess.getConnection(), room);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<Room> getRooms() throws Exception {
		try {
			return roomDao.getAll(dataBaseAccess.getConnection(), SortType.room_number);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void updateRoom(Room room) throws Exception {
		try {
			roomDao.update(dataBaseAccess.getConnection(), room);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void deleteRoom(int id) throws Exception {
		try {
			roomDao.delete(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public Room getRoomById(int id) throws Exception {
		try {
			return roomDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void changeRoomStatus(int id) throws Exception {
		Connection connetcion = dataBaseAccess.getConnection();
		Room room = null;
		try {
			room = roomDao.getById(connetcion, id);
			if (room.getStatus().equals("Empty")) {
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
			throw new Exception();
		}
	}

	@Override
	public void changeRoomPrice(int id, Double dailyPrice) throws Exception {
		roomDao.changeRoomPrice(dataBaseAccess.getConnection(), id, dailyPrice);
	}

	@Override
	public List<Room> getEmptyRooms() throws Exception {
		try {
			return roomDao.getEmpyRooms(dataBaseAccess.getConnection(), SortType.room_number);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) throws Exception {
		try {
			return roomDao.getEmptyRoomsNumber(dataBaseAccess.getConnection());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) throws Exception {
		try {
			return roomDao.getEmptyRoomsOnDate(dataBaseAccess.getConnection(), date.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<Room> sortRooms(SortType type) throws Exception {
		try {
			return roomDao.getAll(dataBaseAccess.getConnection(), type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<Room> sortEmptyRooms(SortType type) throws Exception {
		try {
			return roomDao.getEmpyRooms(dataBaseAccess.getConnection(), type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public Room cloneRoom(int id) throws Exception {
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
			throw new Exception();
		}
		return clone;
	}

	@Override
	public List<Room> getUsedRooms() throws Exception {
		try {
			return roomDao.getUsedRooms(dataBaseAccess.getConnection());
		} catch (Exception e) {
			logger.error("Failed to get used Rooms!", e);
			throw new Exception();
		}
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Room> importedRooms = (List<Room>) CommonCSVReader.readFromFile(Room.class);
		Connection connetcion = dataBaseAccess.getConnection();
		for (Room room : importedRooms) {
			List<Room> existingRooms;
			try {
				connetcion.setAutoCommit(false);
				existingRooms = roomDao.getAll(dataBaseAccess.getConnection(), SortType.id);
				if (!existingRooms.contains(room)) {
					roomDao.create(connetcion, room);
					;
				}
				connetcion.commit();
				connetcion.setAutoCommit(true);
			} catch (Exception e) {
				logger.error("Failed to get used Rooms!", e);
				throw new Exception();
			}
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			CommonCSVWriter.writeToFile(roomDao.getAll(dataBaseAccess.getConnection(), SortType.id));
		} catch (Exception e) {
			logger.error("Failed to get used Rooms!", e);
			throw new Exception();
		}
	}
}
