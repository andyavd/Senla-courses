package eu.senla.andyavd.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.dao.RoomHistoryDao;
import eu.senla.andyavd.enums.SortType;

public class RoomHistoryManager implements IRoomHistoryManager {
	
	private final static Logger logger = Logger.getLogger(RoomHistoryManager.class);
	public IRoomHistoryDao roomHistoryDao = new RoomHistoryDao();
	private DataBaseAccess dataBaseAccess;
	public RoomHistoryManager() {
	}
	
	@Override
	public void addHistory(RoomHistory history) {
		try {
			roomHistoryDao.create(dataBaseAccess.getConnection(), history);
		} catch (Exception e) {
			logger.error("Failed to check-in the Visitor!");
		}
	}
	@Override
	public void checkOutVisitor(int visitorId) {
		try {
		roomHistoryDao.checkOutVisitor(dataBaseAccess.getConnection(), visitorId);
		} catch (Exception e){
			logger.error("Failed to check-out the Visitor!");
		}
	}
	@Override
	public List<RoomHistory> getHistories() {
		try {
			return roomHistoryDao.getAll(dataBaseAccess.getConnection(), SortType.id);
		} catch (SQLException e) {
			logger.error("Failed to get histories!");
			return null;
		}
	}
	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId){
		return roomHistoryDao.getLastVisitorsOfRoom(dataBaseAccess.getConnection(), roomId);
	}
	@Override
	public void addServicesToVisitor(int visitorId, int serviceId) {
		roomHistoryDao.addServicesToVisitor(dataBaseAccess.getConnection(), visitorId, serviceId);
	}
	@Override
	public List<Service> getVisitorServices(int visitorId) {
		try {
			return roomHistoryDao.getVisitorServices(dataBaseAccess.getConnection(), visitorId);
		} catch (Exception e) {
			logger.error("Failed to get Services!");
			return null;
		}
	}
	@Override
	public Double billVisitor(int visitorId) {
		try {
			return roomHistoryDao.billVisitor(dataBaseAccess.getConnection(), visitorId);
		} catch (Exception e) {
			logger.error("Failed to bill Visitor!");
			return null;
		}
	}
	@Override
	public Integer getTotalVisitorsOnDate(String date) {
		return roomHistoryDao.getTotalVisitorsOnDate(dataBaseAccess.getConnection(), date);
	}
	@Override
	public void importFromCsv() {
//		historiesStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
//		historiesStorage.exportToCsv();
	}
}
