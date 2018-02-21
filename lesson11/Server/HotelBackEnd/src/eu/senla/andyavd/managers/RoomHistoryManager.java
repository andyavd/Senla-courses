package eu.senla.andyavd.managers;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.api.managers.IRoomHistoryManager;
import eu.senla.andyavd.csvutils.CommonCSVReader;
import eu.senla.andyavd.csvutils.CommonCSVWriter;
import eu.senla.andyavd.enums.SortType;

public class RoomHistoryManager implements IRoomHistoryManager {
	
	private final static Logger logger = Logger.getLogger(RoomHistoryManager.class);
	private IRoomHistoryDao roomHistoryDao = (IRoomHistoryDao) DependencyInjection.getInstance().getInstance(IRoomHistoryDao.class);
	private DataBaseAccess dataBaseAccess;
	public RoomHistoryManager() {
	}
	
	@Override
	public void addHistory(RoomHistory history) throws Exception {
		try {
			roomHistoryDao.create(dataBaseAccess.getConnection(), history);
		} catch (Exception e) {
			logger.error("Failed to check-in the Visitor!");
			throw new Exception();
		}
	}
	@Override
	public void checkOutVisitor(int visitorId) throws Exception {
		try {
		roomHistoryDao.checkOutVisitor(dataBaseAccess.getConnection(), visitorId);
		} catch (Exception e){
			logger.error("Failed to check-out the Visitor!");
			throw new Exception();
		}
	}
	@Override
	public List<RoomHistory> getHistories() throws Exception {
		try {
			return roomHistoryDao.getAll(dataBaseAccess.getConnection(), SortType.id);
		} catch (Exception e) {
			logger.error("Failed to get histories!");
			throw new Exception();
		}
	}
	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) throws Exception{
		try {
			return roomHistoryDao.getLastVisitorsOfRoom(dataBaseAccess.getConnection(), roomId);
		} catch (Exception e) {
			logger.error("Failed to get last Visitors of the room!");
			throw new Exception();
		}
	}
	@Override
	public void addServicesToVisitor(int visitorId, int serviceId) throws Exception {
		try {
			roomHistoryDao.addServicesToVisitor(dataBaseAccess.getConnection(), visitorId, serviceId);
		} catch (Exception e) {
			logger.error("Failed to add Services to Visitor!");
			throw new Exception();
		}
	}
	@Override
	public List<Service> getVisitorServices(int visitorId) throws Exception {
		try {
			return roomHistoryDao.getVisitorServices(dataBaseAccess.getConnection(), visitorId);
		} catch (Exception e) {
			logger.error("Failed to get Services!");
			throw new Exception();
		}
	}
	@Override
	public Double billVisitor(int visitorId) throws Exception {
		Double bill = 0.0;
		Connection connetcion = dataBaseAccess.getConnection();
		try {
			connetcion.setAutoCommit(false);
			Double roomPrice = roomHistoryDao.getVisitorRoomPrice(dataBaseAccess.getConnection(), visitorId);
			Integer daysCount = roomHistoryDao.getVisitorsDays(dataBaseAccess.getConnection(), visitorId);
			connetcion.commit();
			connetcion.setAutoCommit(true);
			bill = roomPrice * daysCount;
		} catch (Exception e) {
			logger.error("Failed to bill Visitor!");
			throw new Exception();
		}
		return bill;
	}
	@Override
	public Integer getTotalVisitorsOnDate(String date) throws Exception {
		try {
			return roomHistoryDao.getTotalVisitorsOnDate(dataBaseAccess.getConnection(), date);
		} catch (Exception e) {
			logger.error("Failed to get total Visitors on date!");
			throw new Exception();
		}
	}
	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<RoomHistory> importedRoomHistories = (List<RoomHistory>) CommonCSVReader.readFromFile(RoomHistory.class);
		Connection connetcion = dataBaseAccess.getConnection();
		for (RoomHistory roomHistory : importedRoomHistories) {
			List<RoomHistory> existingRoomHistories;
			try {
				connetcion.setAutoCommit(false);
				existingRoomHistories = roomHistoryDao.getAll(dataBaseAccess.getConnection(), SortType.id);
				if (!existingRoomHistories.contains(roomHistory)) {
					roomHistoryDao.create(connetcion, roomHistory);;
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
			CommonCSVWriter.writeToFile(roomHistoryDao.getAll(dataBaseAccess.getConnection(), SortType.id));
		} catch (Exception e) {
			logger.error("Failed to get used Rooms!", e);
			throw new Exception();
		}
	}
}
