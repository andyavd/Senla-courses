package eu.senla.andyavd.hotel.dao.dbconnector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseAccess {

	private static final Logger logger = Logger.getLogger(DataBaseAccess.class);
	private static DataBaseAccess instance;
	private SessionFactory sessionFactory;

	private DataBaseAccess() {
		configure();
	};

	@SuppressWarnings("deprecation")
	private void configure() {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException e) {
			logger.info("Faild to configure the access!");
		}
	}

	public static DataBaseAccess getInstance() {
		if (instance == null) {
			instance = new DataBaseAccess();
		}
		return instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
