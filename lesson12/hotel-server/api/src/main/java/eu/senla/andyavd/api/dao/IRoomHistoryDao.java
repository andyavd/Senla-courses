package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.beans.RoomHistory;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.beans.Visitor;

public interface IRoomHistoryDao extends IGenericDao<RoomHistory> {
	List<Service> getVisitorServices(Connection connection, int id) throws Exception;

	Double getVisitorRoomPrice(Connection connection, int id) throws Exception;

	Integer getVisitorsDays(Connection connection, int visitorId) throws Exception;

	Integer getTotalVisitorsOnDate(Connection connection, String date) throws Exception;

	List<Visitor> getLastVisitorsOfRoom(Connection connection, int id) throws Exception;

	void addServicesToVisitor(Connection connection, int visitorId, int serviceId) throws Exception;

	void checkOutVisitor(Connection connection, int id) throws Exception;
}