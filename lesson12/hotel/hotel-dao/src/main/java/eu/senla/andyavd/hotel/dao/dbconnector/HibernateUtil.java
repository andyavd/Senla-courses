package eu.senla.andyavd.hotel.dao.dbconnector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final Logger logger = Logger.getLogger(HibernateUtil.class);

	private static HibernateUtil instance = null;
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			logger.info("Hibernate configuration has been loaded!");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			logger.info("Hibernate ServiceRegistry has been created!");
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			logger.error("Failed to create SessionFactory!" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public SessionFactory getSessionFactory() {
		return buildSessionFactory();
	}

	public Session getCurrentSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			logger.error("Failed to get current Session!" + e);
			return null;
		}
	}

	public void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (HibernateException e) {
			logger.error("Failed to close Session" + e);
		}
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
