package eu.senla.andyavd.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.controllers.IVisitorManager;
import eu.senla.andyavd.api.dao.IVisitorDao;
import eu.senla.andyavd.dao.VisitorDao;
import eu.senla.andyavd.enums.SortType;

public class VisitorManager implements IVisitorManager {
	
	final static Logger logger = Logger.getLogger(VisitorManager.class);

	private IVisitorDao visitorDao = new VisitorDao();
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
	public List<Visitor> getVisitors() {
		try{
			return visitorDao.getAll(dataBaseAccess.getConnection(), SortType.visitor_name);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public void updateVisitor(Visitor visitor) {
		try {
			visitorDao.update(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public void deleteVisitor(int id) {
		try{
			visitorDao.delete(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
        }
	}
	@Override
	public Visitor getVisitorById(int id) {
		try{
			return visitorDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@Override
	public List<Visitor> getCheckedVisitors() {
		try {
			return visitorDao.getCheckedVisitors(dataBaseAccess.getConnection(), SortType.id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Visitor> getCheckedVisitorsWithServices() {
		try {
			return visitorDao.getCheckedVisitorsWithServices(dataBaseAccess.getConnection());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void importFromCsv() {
//		visitorsStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
//		visitorsStorage.exportToCsv();
	}
}
