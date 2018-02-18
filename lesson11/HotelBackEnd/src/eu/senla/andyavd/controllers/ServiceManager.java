package eu.senla.andyavd.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.api.controllers.IServiceManager;
import eu.senla.andyavd.api.dao.IServiceDao;
import eu.senla.andyavd.dao.ServiceDao;
import eu.senla.andyavd.enums.SortType;

public class ServiceManager implements IServiceManager {

	final static Logger logger = Logger.getLogger(ServiceManager.class);
	private IServiceDao serviceDao = new ServiceDao();
	private DataBaseAccess dataBaseAccess;
	public ServiceManager() {
	}
	@Override
	public void addService(Service service) {
		try {
			serviceDao.create(dataBaseAccess.getConnection(), service);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		};
	}
	@Override
	public List<Service> getServices() {
		try {
			return serviceDao.getAll(dataBaseAccess.getConnection(), SortType.service_name);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void updateService(Service service) {
		try{
			serviceDao.update(dataBaseAccess.getConnection(), service);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}
	@Override
	public void deleteService(int id) {
		try {
			serviceDao.delete(dataBaseAccess.getConnection(), id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public Service getServiceById(int id) {
		try {
			return serviceDao.getById(dataBaseAccess.getConnection(), id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void changeServicePrice(int id, double dailyPrice) {
		Connection connetcion = dataBaseAccess.getConnection();
		try {
			connetcion.setAutoCommit(false);
			serviceDao.getById(connetcion, id);
			serviceDao.changeServicePrice(connetcion, dailyPrice, id);;
			connetcion.commit();
			connetcion.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("Failed to change Service price!", e);
     	}
	}
	@Override
	public List<Service> sortServices(SortType type) {
		try {
			return serviceDao.getAll(dataBaseAccess.getConnection(), type);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void importFromCsv() {
//		serviceDao.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
//		serviceDao.exportToCsv();
	}
}
