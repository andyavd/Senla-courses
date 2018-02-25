package eu.senla.andyavd.managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.dbconnector.DataBaseConnector;
import eu.senla.andyavd.dependency.DependencyInjection;
import eu.senla.andyavd.api.dao.IServiceDao;
import eu.senla.andyavd.api.managers.IServiceManager;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.enums.SortType;
import eu.senla.andyavd.utils.csv.CommonCSVReader;
import eu.senla.andyavd.utils.csv.CommonCSVWriter;

public class ServiceManager implements IServiceManager {

	private final static Logger logger = Logger.getLogger(ServiceManager.class);
	private IServiceDao serviceDao = (IServiceDao) DependencyInjection.getInstance().getInstance(IServiceDao.class);
	private DataBaseConnector dataBaseAccess;

	public ServiceManager() {
	}

	@Override
	public void addService(Service service) throws Exception {
		try {
			serviceDao.create(dataBaseAccess.getConnection(), service);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
		;
	}

	@Override
	public List<Service> getServices() throws Exception {
		try {
			return serviceDao.getAll(dataBaseAccess.getConnection(), SortType.service_name);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void updateService(Service service) throws Exception {
		try {
			serviceDao.update(dataBaseAccess.getConnection(), service);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void deleteService(int id) throws Exception {
		try {
			serviceDao.delete(dataBaseAccess.getConnection(), id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public Service getServiceById(int id) throws Exception {
		try {
			return serviceDao.getById(dataBaseAccess.getConnection(), id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void changeServicePrice(int id, double dailyPrice) throws Exception {
		Connection connetcion = dataBaseAccess.getConnection();
		try {
			connetcion.setAutoCommit(false);
			serviceDao.getById(connetcion, id);
			serviceDao.changeServicePrice(connetcion, dailyPrice, id);
			;
			connetcion.commit();
			connetcion.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("Failed to change Service price!", e);
			throw new Exception();
		}
	}

	@Override
	public List<Service> sortServices(SortType type) throws Exception {
		try {
			return serviceDao.getAll(dataBaseAccess.getConnection(), type);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Service> importedServices = (List<Service>) CommonCSVReader.readFromFile(Service.class);
		Connection connetcion = dataBaseAccess.getConnection();
		for (Service service : importedServices) {
			List<Service> existingServices;
			try {
				connetcion.setAutoCommit(false);
				existingServices = serviceDao.getAll(dataBaseAccess.getConnection(), SortType.id);
				if (!existingServices.contains(service)) {
					serviceDao.create(connetcion, service);
					;
				}
				connetcion.commit();
				connetcion.setAutoCommit(true);
			} catch (Exception e) {
				logger.error("Failed to get used Rooms!", e);
				throw new Exception();
			}
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			CommonCSVWriter.writeToFile(serviceDao.getAll(dataBaseAccess.getConnection(), SortType.id));
		} catch (Exception e) {
			logger.error("Failed to get used Rooms!", e);
			throw new Exception();
		}
	}
}
