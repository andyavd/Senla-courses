package eu.senla.andyavd.hotel.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IRoomDao;
import eu.senla.andyavd.hotel.api.services.IRoomService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class RoomService implements IRoomService {

	private final static Logger logger = Logger.getLogger(RoomService.class);
	private IRoomDao roomDao = (IRoomDao) DependencyInjection.getInstance().getClassInstance(IRoomDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	public RoomService() {
	}

	@Override
	public void addRoom(Room room) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomDao.create(session, room);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the Room!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getRooms(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Room> rooms = roomDao.getAll(session, null);
			transaction.commit();
			return rooms;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public void updateRoom(Room room) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomDao.update(session, room);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to update the Room!");
			throw new Exception();
		}
	}

	@Override
	public void deleteRoom(Room room) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomDao.delete(session, room);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to delete the Room!");
			throw new Exception();
		}
	}

	@Override
	public Room getRoomById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Room room = roomDao.getById(session, id);
			transaction.commit();
			return room;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Room!");
			throw new Exception();
		}
	}

	@Override
	public void changeRoomStatus(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomDao.changeRoomStatus(session, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to change the Room status!");
			throw new Exception();
		}
	}

	@Override
	public void changeRoomPrice(int id, Double dailyPrice) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomDao.changeRoomPrice(session, id, dailyPrice);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to change the Room price!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getEmptyRooms(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Room> rooms = roomDao.getEmpyRooms(session, SortType.roomNumber);
			transaction.commit();
			return rooms;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the empty Rooms!");
			throw new Exception();
		}
	}

	@Override
	public Integer getEmptyRoomsNumber() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Integer number = roomDao.getEmptyRoomsNumber(session);
			transaction.commit();
			return number;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the empty Rooms number!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(Date date) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Room> rooms = roomDao.getEmptyRoomsOnDate(session, DateFormatter.stringFromDate(date));
			transaction.commit();
			return rooms;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the empty Rooms on chosen date!");
			throw new Exception();
		}
	}

	@Override
	public void cloneRoom(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Room clone = null;
		try {
			transaction = session.beginTransaction();
			clone = roomDao.getById(session, id);
			roomDao.create(session, clone);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to clone the Room!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getUsedRooms() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Room> rooms = roomDao.getUsedRooms(session);
			transaction.commit();
			return rooms;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get used Rooms!");
			throw new Exception();
		}
	}
	
	@Override
	public Room getVisitorsRoom(int visitorId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Room room = roomDao.getVisitorsRoom(session, visitorId);
			transaction.commit();
			return room;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Visitors Room!");
			throw new Exception();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void importFromCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		List<Room> importedRooms = (List<Room>) CsvReader.readFromFile(Room.class);
		try {
			transaction = session.beginTransaction();
			for (Room room : importedRooms) {
				session.saveOrUpdate(room);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to import the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CsvWriter.writeToFile(roomDao.getAll(session, SortType.id));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to export the Rooms!");
			throw new Exception();
		}
	}
}
