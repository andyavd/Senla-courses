package eu.senla.andyavd.controllers;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.controllers.IVisitorManager;
import eu.senla.andyavd.api.dao.IVisitorDao;
import eu.senla.andyavd.api.storages.IVisitorsStorage;

public class VisitorManager implements IVisitorManager {
	
	final static Logger logger = Logger.getLogger(VisitorManager.class);

	private IVisitorsStorage visitorsStorage = (IVisitorsStorage) DependencyInjection.getInstance().getInstance(IVisitorsStorage.class);
	private IVisitorDao visitorDao = (IVisitorDao) DependencyInjection.getInstance().getInstance(IVisitorDao.class);
	private DataBaseAccess dataBaseAccess;
	public VisitorManager() {
	}
	
	@Override
	public void addVisitor(Visitor visitor) {
		try{
			visitorDao.create(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		try{
			visitorDao.delete(dataBaseAccess.getConnection(), visitor.getId());
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}

	@Override
	public List<Visitor> getVisitors() {
		try{
			return visitorDao.getAll(dataBaseAccess.getConnection());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	//TODO
	@Override
	public Integer getTotalVisitorsOnDate(LocalDate date) {
		Integer count = 0;
		List<Visitor> visitors = getVisitors();
		for (int i = 0; i < visitors.size(); i++) {
			Visitor visitor = visitors.get(i);
			if (visitor != null && visitor.getHistory() != null) {
				if (date.isAfter(visitor.getHistory().getCheckInDate()) && date.isBefore(visitor.getHistory().getCheckOutDate())) {
					count++;
				}
			}
		}
		return count;
	}
	//TODO - add history
	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitorsStorage.updateVisitor(visitor, history);
	}
	//TODO
	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) {
		if (visitor.getHistory() != null) {

			visitor.getHistory().getService().add(service);

		} else {
			logger.error("No such visitor to bill!");
		}
	}

	@Override
	public List<Service> getVisitorServices(Visitor visitor) {
		try{
			return visitorDao.getVisitorServices(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}

	@Override
	public List<Visitor> sortVisitors() {
		try{
			return visitorDao.sortVisitorsByName(dataBaseAccess.getConnection());
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}

	@Override
	public List<Service> sortVisitorServicesByPrice(Visitor visitor) {
		try{
			return visitorDao.sortVisitorServicesByPrice(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	
	@Override
	public Visitor getVisitorById(Integer id) {
		try{
			return visitorDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	
	@Override
	public void importFromCsv() {
		visitorsStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
		visitorsStorage.exportToCsv();
	}
	public void setIVisitorDao(IVisitorDao visitorDao) {
        this.visitorDao = visitorDao;
    }
}
