package eu.senla.andyavd.hotel.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import eu.senla.andyavd.hotel.api.dao.IServiceDao;
import eu.senla.andyavd.hotel.entity.beans.Service;

public class ServiceDao extends GenericDao<Service> implements IServiceDao {

	private final static Logger logger = Logger.getLogger(ServiceDao.class);

	public ServiceDao() {
		super(Service.class);
	}

	@Override
	public void changeServicePrice(Session session, Double dailyPrice, int id) throws Exception {
		try {
			Query query = session.createQuery("update Service set dailyPrice= :dailyPrice where id= :id");
			query.setParameter("id", id);
			query.setDouble("dailyPrice", dailyPrice);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Failed to Change room status!");
			throw new Exception();
		}
	}
}