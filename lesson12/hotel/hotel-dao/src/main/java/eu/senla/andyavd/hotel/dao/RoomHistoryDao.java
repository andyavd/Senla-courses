package eu.senla.andyavd.hotel.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import eu.senla.andyavd.hotel.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;

public class RoomHistoryDao extends GenericDao<RoomHistory> implements IRoomHistoryDao {
	public RoomHistoryDao() {
		super(RoomHistory.class);
	}

	private final static Logger logger = Logger.getLogger(RoomHistoryDao.class);

	@Override
	public Double getVisitorRoomPrice(Session session, int visitorId) throws Exception {
		Double price = 0.0;
		Criteria criteriaRoom = session.createCriteria(Room.class);
		Criteria criteriaRoomHistory = session.createCriteria(RoomHistory.class);
		try {
			Criterion visitorIdHistory = Restrictions.eq("visitor_id", visitorId);
			int roomId = ((Integer) criteriaRoomHistory.add(visitorIdHistory)
					.setProjection(Projections.property("room_id")).uniqueResult()).intValue();
			price = ((Double) criteriaRoom.add(Restrictions.eq("id", roomId))
					.setProjection(Projections.property("daily_price")).uniqueResult()).doubleValue();
		} catch (Exception e) {
			logger.error("Failed to get Visitors Room price!");
			throw new Exception();
		}
		return price;
	}

	@Override
	public Date getVisitorInDate(Session session, int visitorId) throws Exception {
		Criteria criteria = session.createCriteria(RoomHistory.class);
		Date inDate = null;
		try {
			inDate = DateFormatter.dateFromString(criteria.add(Restrictions.eq("visitor_id", visitorId))
					.setProjection(Projections.property("check_in")).uniqueResult().toString());
		} catch (Exception e) {
			logger.error("Failed to get Visitor check-in date!");
			throw new Exception();
		}
		return inDate;
	}

	@Override
	public Date getVisitorOutDate(Session session, int visitorId) throws Exception {
		Criteria criteria = session.createCriteria(RoomHistory.class);
		Date inDate = null;
		try {
			inDate = DateFormatter.dateFromString(criteria.add(Restrictions.eq("visitor_id", visitorId))
					.setProjection(Projections.property("check_out")).uniqueResult().toString());
		} catch (Exception e) {
			logger.error("Failed to get Visitor check-out date!");
			throw new Exception();
		}
		return inDate;
	}

	@Override
	public Integer getTotalVisitorsOnDate(Session session, String date) throws Exception {
		Integer number = 0;
		Criteria criteria = session.createCriteria(RoomHistory.class);
		try {
			Criterion dateBefore = Restrictions.lt("check_in", DateFormatter.dateFromString(date));
			Criterion dateAfter = Restrictions.gt("check_out", DateFormatter.dateFromString(date));
			LogicalExpression andExpression = Restrictions.and(dateBefore, dateAfter);
			number = (Integer) criteria.add(Restrictions.eq("history_status", "CheckIn")).add(andExpression)
					.setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception e) {
			logger.error("Failed to count Visitors!");
			throw new Exception();
		}
		return number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visitor> getLastVisitorsOfRoom(Session session, int roomId) throws Exception {
		List<Visitor> list = new ArrayList<>();
		Criteria criteriaVisitor = session.createCriteria(Visitor.class);
		try {
			DetachedCriteria criteriaRoomHistory = DetachedCriteria.forClass(RoomHistory.class);
			criteriaRoomHistory.add(Restrictions.eq("room_id", roomId)).setProjection(Projections.property("room_id"))
					.addOrder(Order.desc("check_out"));
			criteriaVisitor.add(Property.forName("id").in(criteriaRoomHistory)).setMaxResults(3);
			list = criteriaVisitor.list();
		} catch (Exception e) {
			logger.error("Failed to get the last Visitors of the Rooms!");
			throw new Exception();
		}
		return list;
	}

	@Override
	public RoomHistory getVisitorsHistory(Session session, int visitorId) throws Exception {
		RoomHistory roomHistory = null;
		Criteria criteria = session.createCriteria(RoomHistory.class);
		try {
			roomHistory = (RoomHistory) criteria.add(Restrictions.eq("history_status", "CheckIn"))
					.add(Restrictions.eq("visitor_id", visitorId)).uniqueResult();
		} catch (Exception e) {
			logger.error("Failed to get Visitors history id!");
			throw new Exception();
		}
		return roomHistory;
	}

	@Override
	public void checkOutVisitor(Session session, int visitorId) throws Exception {
		try {
			Query query = session.createQuery("update RoomHistory set status= \"CheckOut\" where id= :id");
			query.setParameter("visitorId", visitorId);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Failed to checkout the Visitor!");
			throw new Exception();
		}
	}

}