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
		configureHibernate();
	}

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	private void configureHibernate() {
		try {

			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			logger.info("Hibernate configuration has been loaded!");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			configuration.buildSessionFactory(serviceRegistry);
			logger.info("Hibernate ServiceRegistry has been created!");
		} catch (Exception e) {
			logger.error("Failed to create SessionFactory!" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		try {
			return sessionFactory.openSession();
		} catch (HibernateException e) {
			logger.error("Failed to open Session!" + e);
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
