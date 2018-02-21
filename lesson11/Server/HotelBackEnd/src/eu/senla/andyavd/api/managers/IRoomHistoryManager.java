package eu.senla.andyavd.api.managers;

import java.util.List;

import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public interface IRoomHistoryManager {
	public void addHistory(RoomHistory history) throws Exception;
	public List<RoomHistory> getHistories() throws Exception;
	void checkOutVisitor(int visitorId) throws Exception;
	List<Visitor> getLastVisitorsOfRoom(int roomId) throws Exception;
	void addServicesToVisitor(int visitorId, int serviceId) throws Exception;
	List<Service> getVisitorServices(int visitorId) throws Exception;
	Integer getTotalVisitorsOnDate(String date) throws Exception;
	Double billVisitor(int visitorId) throws Exception;
	void importFromCsv() throws Exception;
	void exportToCsv() throws Exception;
}