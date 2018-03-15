package eu.senla.andyavd.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Visitor;

public interface IRoomHistoryDao extends IGenericDao<RoomHistory> {

	Integer getTotalVisitorsOnDate(Session session, String date) throws Exception;

	List<Visitor> getLastVisitorsOfRoom(Session session, int id) throws Exception;

	RoomHistory getVisitorsHistory(Session session, int visitorId) throws Exception;

	void checkOutVisitor(Session session, int id) throws Exception;
}