package eu.senla.andyavd.hotel.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.hotel.api.services.IRoomHistoryService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class RoomHistoryService implements IRoomHistoryService {

	private final static Logger logger = Logger.getLogger(RoomHistoryService.class);
	private IRoomHistoryDao roomHistoryDao = (IRoomHistoryDao) DependencyInjection.getInstance()
			.getClassInstance(IRoomHistoryDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	
	public RoomHistoryService() {
	}

	@Override
	public void addHistory(RoomHistory history) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomHistoryDao.create(session, history);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the RoomHistory!");
			throw new Exception();
		}
	}

	@Override
	public void checkOutVisitor(int visitorId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roomHistoryDao.checkOutVisitor(session, visitorId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to checkout the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<RoomHistory> getHistories(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<RoomHistory> histories = roomHistoryDao.getAll(session, null);
			transaction.commit();
			return histories;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Visitor> visitors = roomHistoryDao.getLastVisitorsOfRoom(session, roomId);
			transaction.commit();
			return visitors;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the last Visitors of the Room!");
			throw new Exception();
		}
	}

	@Override
	public void addServicesToVisitor(int visitorId, Service service) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		RoomHistory roomHistory = null;
		try {
			transaction = session.beginTransaction();
			roomHistory = roomHistoryDao.getVisitorsHistory(session, visitorId);
			List<Service> services = roomHistory.getServices();
			services.add(service);
			roomHistory.setServices(services);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to add Service to the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<Service> getVisitorServices(int visitorId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			RoomHistory roomHistory = roomHistoryDao.getVisitorsHistory(session, visitorId);
			List<Service> services = roomHistory.getServices();
			transaction.commit();
			return services;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Visitors Services!");
			throw new Exception();
		}
	}

	@Override
	public Long billVisitor(int visitorId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			RoomHistory history = roomHistoryDao.getVisitorsHistory(session, visitorId);
			Long diff = history.getCheckOutDate().getTime() - history.getCheckInDate().getTime();
			Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			transaction.commit();
			return days;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to bill the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public Integer getTotalVisitorsOnDate(Date date) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Integer number = roomHistoryDao.getTotalVisitorsOnDate(session, DateFormatter.stringFromDate(date));
			transaction.commit();
			return number;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Visitors on chosen date!");
			throw new Exception();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void importFromCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		List<RoomHistory> importedRoomHistories = (List<RoomHistory>) CsvReader.readFromFile(RoomHistory.class);
		try {
			transaction = session.beginTransaction();
			for (RoomHistory roomHistory : importedRoomHistories) {
				session.saveOrUpdate(roomHistory);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to import the Room Histories!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CsvWriter.writeToFile(roomHistoryDao.getAll(session, SortType.id));
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
