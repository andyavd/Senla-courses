package eu.senla.andyavd.hotel.api.services;

import java.util.Date;
import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IRoomHistoryService {
	
	public void addHistory(RoomHistory history) throws Exception;

	public List<RoomHistory> getHistories(SortType type) throws Exception;

	void checkOutVisitor(int visitorId) throws Exception;

	List<Visitor> getLastVisitorsOfRoom(int roomId) throws Exception;

	void addServicesToVisitor(int visitorId, Service service) throws Exception;

	List<Service> getVisitorServices(int visitorId) throws Exception;

	Integer getTotalVisitorsOnDate(Date date) throws Exception;

	Double billVisitor(int visitorId) throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}