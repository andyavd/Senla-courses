package eu.senla.andyavd.api.controllers;

import java.util.List;

import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public interface IRoomHistoryManager {
	public void addHistory(RoomHistory history);
	public List<RoomHistory> getHistories();
	void checkOutVisitor(int visitorId);
	List<Visitor> getLastVisitorsOfRoom(int roomId);
	void addServicesToVisitor(int visitorId, int serviceId);
	List<Service> getVisitorServices(int visitorId);
	Integer getTotalVisitorsOnDate(String date);
	Double billVisitor(int visitorId);
	void importFromCsv();
	void exportToCsv();
}