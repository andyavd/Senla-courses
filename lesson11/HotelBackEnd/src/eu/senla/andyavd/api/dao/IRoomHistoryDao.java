package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public interface IRoomHistoryDao extends IAEntityDao<RoomHistory> {
	List<Service> getVisitorServices(Connection connection, int id);
	Double billVisitor(Connection connection, int id);
	Integer getTotalVisitorsOnDate(Connection connection, String date);
	List<Visitor> getLastVisitorsOfRoom(Connection connection, int id);
	void addServicesToVisitor(Connection connection, int visitorId, int serviceId);
	void checkOutVisitor(Connection connection, int id);
}