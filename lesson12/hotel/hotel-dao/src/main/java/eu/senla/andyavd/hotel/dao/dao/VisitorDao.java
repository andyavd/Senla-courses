package eu.senla.andyavd.hotel.dao.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import eu.senla.andyavd.hotel.api.dao.IVisitorDao;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public class VisitorDao extends GenericDao<Visitor> implements IVisitorDao {

	private final static Logger logger = Logger.getLogger(VisitorDao.class);

	public VisitorDao() {
		super(Visitor.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visitor> getCheckedVisitors(Session session, SortType type) throws Exception {
		List<Visitor> list = new ArrayList<Visitor>();
		Criteria criteriaVisitor = session.createCriteria(Visitor.class);
		try {
			DetachedCriteria criteriaRoomHistory = DetachedCriteria.forClass(RoomHistory.class);
			Criterion historyStatus = Restrictions.eq("history_status", "CheckIn");
			criteriaRoomHistory.add(historyStatus).setProjection(Projections.property("visitor_id"));
			criteriaVisitor.add(Property.forName("id").in(criteriaRoomHistory)).addOrder(Order.asc(type.toString()));
			list = criteriaVisitor.list();
		} catch (Exception e) {
			logger.error("Failed to get checked Visitors!");
			throw new Exception();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visitor> getCheckedVisitorsWithServices(Session session, SortType type) throws Exception {
		List<Visitor> visitorList = new ArrayList<Visitor>();
		List<RoomHistory> historyList = new ArrayList<RoomHistory>();
		List<Integer> historyWithServices = new ArrayList<Integer>();
		Criteria criteriaVisitor = session.createCriteria(Visitor.class);
		Criteria criteriaRoomHistory = session.createCriteria(RoomHistory.class);
		try {
			historyList = criteriaRoomHistory.add(Restrictions.eq("history_status", "CheckIn")).list();
			for (int i = 0; i < historyList.size(); i++) {
				RoomHistory roomHistory = historyList.get(i);
				if (roomHistory.getServices() != null) {
					historyWithServices.add(roomHistory.getId());
				}
			}
			visitorList = criteriaVisitor.add(Property.forName("id").in(historyWithServices)).list();
		} catch (Exception e) {
			logger.error("Failed to get checked Visitors with services!");
			throw new Exception();
		}
		return visitorList;
	}
}
