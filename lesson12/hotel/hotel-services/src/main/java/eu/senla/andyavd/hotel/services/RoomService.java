package eu.senla.andyavd.hotel.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.dao.IRoomDao;
import eu.senla.andyavd.hotel.api.managers.IRoomManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class RoomService extends AService implements IRoomManager {

	private final static Logger logger = Logger.getLogger(RoomService.class);
	private IRoomDao roomDao = (IRoomDao) DependencyInjection.getInstance().getInstance(IRoomDao.class);

	public RoomService() {
	}

	@Override
	public void addRoom(Room room) throws Exception {
		try {
			session.beginTransaction();
			roomDao.create(session, room);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to create the Room!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getRooms() throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getAll(session, SortType.roomNumber);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Rooms!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public void updateRoom(Room room) throws Exception {
		try {
			session.beginTransaction();
			roomDao.update(session, room);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to update the Room!");
			throw new Exception();
		}
	}

	@Override
	public void deleteRoom(Room room) throws Exception {
		try {
			session.beginTransaction();
			roomDao.delete(session, room);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to delete the Room!");
			throw new Exception();
		}
	}

	@Override
	public Room getRoomById(int id) throws Exception {
		Room room = null;
		try {
			session.beginTransaction();
			roomDao.getById(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Room!");
			throw new Exception();
		}
		return room;
	}

	@Override
	public void changeRoomStatus(int id) throws Exception {
		try {
			session.beginTransaction();
			roomDao.changeRoomStatus(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to change the Room status!");
			throw new Exception();
		}
	}

	@Override
	public void changeRoomPrice(int id, Double dailyPrice) throws Exception {
		try {
			session.beginTransaction();
			roomDao.changeRoomPrice(session, id, dailyPrice);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to change the Room price!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getEmptyRooms() throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getEmpyRooms(session, SortType.roomNumber);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the empty Rooms!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public Integer getEmptyRoomsNumber() throws Exception {
		Integer number = null;
		try {
			session.beginTransaction();
			number = roomDao.getEmptyRoomsNumber(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the empty Rooms number!");
			throw new Exception();
		}
		return number;
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(Date date) throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getEmptyRoomsOnDate(session, DateFormatter.stringFromDate(date));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the empty Rooms on chosen date!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public List<Room> sortRooms(SortType type) throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getAll(session, type);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to sort the Rooms!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public List<Room> sortEmptyRooms(SortType type) throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getEmpyRooms(session, type);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to sort the empty Rooms!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public void cloneRoom(int id) throws Exception {
		Room clone = null;
		try {
			session.beginTransaction();
			clone = roomDao.getById(session, id);
			roomDao.create(session, clone);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to clone the Room!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getUsedRooms() throws Exception {
		List<Room> rooms = null;
		try {
			session.beginTransaction();
			rooms = roomDao.getUsedRooms(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get used Rooms!");
			throw new Exception();
		}
		return rooms;
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Room> importedRooms = (List<Room>) CsvReader.readFromFile(Room.class);
		try {
			session.beginTransaction();
			for (Room room : importedRooms) {
				session.saveOrUpdate(room);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to import the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			session.beginTransaction();
			CsvWriter.writeToFile(roomDao.getAll(session, SortType.id));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to export the Rooms!");
			throw new Exception();
		}
	}
}
