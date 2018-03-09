package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IServiceDao;
import eu.senla.andyavd.hotel.api.services.IServiceService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class ServiceService implements IServiceService {

	private final static Logger logger = Logger.getLogger(ServiceService.class);
	private IServiceDao serviceDao = (IServiceDao) DependencyInjection.getInstance().getInstance(IServiceDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	public ServiceService() {
	}

	@Override
	public void addService(Service service) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			serviceDao.create(session, service);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the Service!");
			throw new Exception();
		}
	}

	@Override
	public List<Service> getServices(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Service> services = serviceDao.getAll(session, null);
			transaction.commit();
			return services;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Services!");
			throw new Exception();
		}
	}

	@Override
	public void updateService(Service service) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			serviceDao.update(session, service);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to update the Service!");
			throw new Exception();
		}
	}

	@Override
	public void deleteService(Service service) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			serviceDao.delete(session, service);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to delete the Service!");
			throw new Exception();
		}
	}

	@Override
	public Service getServiceById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Service service = serviceDao.getById(session, id);
			transaction.commit();
			return service;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Service!");
			throw new Exception();
		}
	}

	@Override
	public void changeServicePrice(int id, double dailyPrice) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			serviceDao.changeServicePrice(session, dailyPrice, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to change the Service price!");
			throw new Exception();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void importFromCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		List<Service> importedServices = (List<Service>) CsvReader.readFromFile(Service.class);
		try {
			transaction = session.beginTransaction();
			for (Service service : importedServices) {
				session.saveOrUpdate(service);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to import the Services!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CsvWriter.writeToFile(serviceDao.getAll(session, SortType.id));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to export the Services!");
			throw new Exception();
		}
	}
}
