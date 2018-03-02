package eu.senla.andyavd.hotel.managers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.hotel.api.managers.IRoomHistoryManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class RoomHistoryManager extends AManager implements IRoomHistoryManager {

	private final static Logger logger = Logger.getLogger(RoomHistoryManager.class);
	private IRoomHistoryDao roomHistoryDao = (IRoomHistoryDao) DependencyInjection.getInstance()
			.getInstance(IRoomHistoryDao.class);

	public RoomHistoryManager() {
	}

	@Override
	public void addHistory(RoomHistory history) throws Exception {
		try {
			session.beginTransaction();
			roomHistoryDao.create(session, history);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to create the RoomHistory!");
			throw new Exception();
		}
	}

	@Override
	public void checkOutVisitor(int visitorId) throws Exception {
		try {
			session.beginTransaction();
			roomHistoryDao.checkOutVisitor(session, visitorId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to checkout the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<RoomHistory> getHistories() throws Exception {
		List<RoomHistory> histories = null;
		try {
			session.beginTransaction();
			histories = roomHistoryDao.getAll(session, SortType.id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Rooms!");
			throw new Exception();
		}
		return histories;
	}

	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) throws Exception {
		List<Visitor> visitors = null;
		try {
			session.beginTransaction();
			visitors = roomHistoryDao.getLastVisitorsOfRoom(session, roomId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the last Visitors of the Room!");
			throw new Exception();
		}
		return visitors;
	}

	@Override
	public void addServicesToVisitor(int visitorId, Service service) throws Exception {
		RoomHistory roomHistory = null;
		try {
			session.beginTransaction();
			roomHistory = roomHistoryDao.getVisitorsHistory(session, visitorId);
			List<Service> services = roomHistory.getServices();
			services.add(service);
			roomHistory.setServices(services);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to add Service to the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<Service> getVisitorServices(int visitorId) throws Exception {
		RoomHistory roomHistory = null;
		List<Service> services = null;
		try {
			session.beginTransaction();
			roomHistory = roomHistoryDao.getVisitorsHistory(session, visitorId);
			services = roomHistory.getServices();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Visitors Services!");
			throw new Exception();
		}
		return services;
	}

	@Override
	public Double billVisitor(int visitorId) throws Exception {
		Double bill = 0.0;
		try {
			session.beginTransaction();
			Long diff = roomHistoryDao.getVisitorOutDate(session, visitorId).getTime()
					- roomHistoryDao.getVisitorInDate(session, visitorId).getTime();
			Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			bill = days * roomHistoryDao.getVisitorRoomPrice(session, visitorId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to bill the Visitor!");
			throw new Exception();
		}
		return bill;
	}

	@Override
	public Integer getTotalVisitorsOnDate(Date date) throws Exception {
		Integer number = null;
		try {
			session.beginTransaction();
			number = roomHistoryDao.getTotalVisitorsOnDate(session, DateFormatter.stringFromDate(date));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Visitors on chosen date!");
			throw new Exception();
		}
		return number;
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<RoomHistory> importedRoomHistories = (List<RoomHistory>) CsvReader.readFromFile(RoomHistory.class);
		try {
			session.beginTransaction();
			for (RoomHistory roomHistory : importedRoomHistories) {
				session.saveOrUpdate(roomHistory);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to import the Room Histories!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			session.beginTransaction();
			CsvWriter.writeToFile(roomHistoryDao.getAll(session, SortType.id));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to export the Rooms!");
			throw new Exception();
		}
	}
}
