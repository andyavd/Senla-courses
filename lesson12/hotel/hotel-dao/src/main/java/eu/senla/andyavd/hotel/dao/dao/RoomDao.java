package eu.senla.andyavd.hotel.dao.dao;

import java.util.ArrayList;
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

import eu.senla.andyavd.hotel.api.dao.IRoomDao;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.common.DateFormatter;

public class RoomDao extends GenericDao<Room> implements IRoomDao {

	private final static Logger logger = Logger.getLogger(RoomDao.class);

	public RoomDao() {
		super(Room.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getEmpyRooms(Session session, SortType type) throws Exception {
		List<Room> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Room.class);
		try {
			criteria.add(Restrictions.eq("room_status", "Empty")).addOrder(Order.asc(type.toString()));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Failed to get empty Rooms!");
			throw new Exception();
		}
		return list;
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(Session session, String date) throws Exception {
		List<Room> list = new ArrayList<>();
		Criteria criteriaRoom = session.createCriteria(Room.class);
		try {
			DetachedCriteria criteriaRoomHistory = DetachedCriteria.forClass(RoomHistory.class);
			Criterion dateBefore = Restrictions.lt("check_in", DateFormatter.dateFromString(date));
			Criterion dateAfter = Restrictions.gt("check_out", DateFormatter.dateFromString(date));
			Criterion historyStatus = Restrictions.eq("history_status", "CheckIn");
			LogicalExpression andExpression = Restrictions.and(dateBefore, dateAfter);
			criteriaRoomHistory.add(andExpression).add(historyStatus).setProjection(Projections.property("room_id"));
			Criterion status = Restrictions.eq("room_status", "Empty");
			criteriaRoom.add(status).add(Property.forName("id").in(criteriaRoomHistory)).list();
		} catch (Exception e) {
			logger.error("Failed to get empty Rooms on Date!");
			throw new Exception();
		}
		return list;
	}

	@Override
	public Integer getEmptyRoomsNumber(Session session) throws Exception {
		Integer number = 0;
		Criteria criteria = session.createCriteria(Room.class);
		try {
			number = (Integer) criteria.add(Restrictions.eq("room_status", "Empty"))
					.setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception e) {
			logger.error("Failed to get empty Rooms number!");
			throw new Exception();
		}
		return number;
	}

	@Override
	public void changeRoomStatus(Session session, int id) throws Exception {
		try {
			Query query = session.createQuery("update Room set status= \"Serviced\" where id= :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Failed to Change room status!");
			throw new Exception();
		}
	}

	@Override
	public void changeRoomPrice(Session session, int id, Double dailyPrice) throws Exception {
		try {
			Query query = session.createQuery("update Room set dailyPrice= :dailyPrice where id= :id");
			query.setParameter("id", id);
			query.setDouble("dailyPrice", dailyPrice);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Failed to Change room status!");
			throw new Exception();
		}
	}

	@Override
	public List<Room> getUsedRooms(Session session) throws Exception {
		List<Room> list = new ArrayList<>();
		Criteria criteriaRoom = session.createCriteria(Room.class);
		try {
			DetachedCriteria criteriaRoomHistory = DetachedCriteria.forClass(RoomHistory.class);
			criteriaRoomHistory.setProjection(Projections.property("room_id"))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteriaRoom.add(Property.forName("id").in(criteriaRoomHistory)).list();
		} catch (Exception e) {
			logger.error("Failed to get empty Rooms on Date!");
			throw new Exception();
		}
		return list;
	}

}