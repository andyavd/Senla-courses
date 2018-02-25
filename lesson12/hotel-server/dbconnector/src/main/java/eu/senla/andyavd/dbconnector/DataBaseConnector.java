package eu.senla.andyavd.dbconnector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DataBaseConnector {
	private static DataBaseConnector instance;
	private static final Logger logger = Logger.getLogger(DataBaseConnector.class);

	private static SessionFactory sessionFactory;

	private DataBaseConnector() {
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

	public static Session getSession() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			logger.error("Failed to open Session!" + e);
		}
		return session;
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
