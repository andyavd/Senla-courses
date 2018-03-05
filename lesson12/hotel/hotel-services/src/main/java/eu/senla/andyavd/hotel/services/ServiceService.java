package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.dao.IServiceDao;
import eu.senla.andyavd.hotel.api.managers.IServiceManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class ServiceService extends AService implements IServiceManager {

	private final static Logger logger = Logger.getLogger(ServiceService.class);
	private IServiceDao serviceDao = (IServiceDao) DependencyInjection.getInstance().getInstance(IServiceDao.class);

	public ServiceService() {
	}

	@Override
	public void addService(Service service) throws Exception {
		try {
			session.beginTransaction();
			serviceDao.create(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to create the Service!");
			throw new Exception();
		}
	}

	@Override
	public List<Service> getServices() throws Exception {
		List<Service> services = null;
		try {
			session.beginTransaction();
			services = serviceDao.getAll(session, SortType.name);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Services!");
			throw new Exception();
		}
		return services;
	}

	@Override
	public void updateService(Service service) throws Exception {
		try {
			session.beginTransaction();
			serviceDao.update(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to update the Service!");
			throw new Exception();
		}
	}

	@Override
	public void deleteService(Service service) throws Exception {
		try {
			session.beginTransaction();
			serviceDao.delete(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to delete the Service!");
			throw new Exception();
		}
	}

	@Override
	public Service getServiceById(int id) throws Exception {
		Service service = null;
		try {
			session.beginTransaction();
			serviceDao.getById(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Service!");
			throw new Exception();
		}
		return service;
	}

	@Override
	public void changeServicePrice(int id, double dailyPrice) throws Exception {
		try {
			session.beginTransaction();
			serviceDao.changeServicePrice(session, dailyPrice, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to change the Service price!");
			throw new Exception();
		}
	}

	@Override
	public List<Service> sortServices(SortType type) throws Exception {
		List<Service> services = null;
		try {
			session.beginTransaction();
			services = serviceDao.getAll(session, type);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to sort the Services!");
			throw new Exception();
		}
		return services;
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Service> importedServices = (List<Service>) CsvReader.readFromFile(Service.class);
		try {
			session.beginTransaction();
			for (Service service : importedServices) {
				session.saveOrUpdate(service);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to import the Services!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			session.beginTransaction();
			CsvWriter.writeToFile(serviceDao.getAll(session, SortType.id));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to export the Services!");
			throw new Exception();
		}
	}
}
