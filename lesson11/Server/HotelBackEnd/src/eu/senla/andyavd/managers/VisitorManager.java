package eu.senla.andyavd.managers;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DataBaseAccess;
import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.dao.IVisitorDao;
import eu.senla.andyavd.api.managers.IVisitorManager;
import eu.senla.andyavd.csvutils.CommonCSVReader;
import eu.senla.andyavd.csvutils.CommonCSVWriter;
import eu.senla.andyavd.enums.SortType;

public class VisitorManager implements IVisitorManager {
	
	private final static Logger logger = Logger.getLogger(VisitorManager.class);
	private IVisitorDao visitorDao = (IVisitorDao) DependencyInjection.getInstance().getInstance(IVisitorDao.class);
	private DataBaseAccess dataBaseAccess;
	public VisitorManager() {
	}
	@Override
	public void addVisitor(Visitor visitor) throws Exception {
		try{
			visitorDao.create(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
	}
	@Override
	public List<Visitor> getVisitors() throws Exception {
		try{
			return visitorDao.getAll(dataBaseAccess.getConnection(), SortType.visitor_name);
		} catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
	}
	@Override
	public void updateVisitor(Visitor visitor) throws Exception {
		try {
			visitorDao.update(dataBaseAccess.getConnection(), visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	@Override
	public void deleteVisitor(int id) throws Exception {
		try{
			visitorDao.delete(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
	}
	@Override
	public Visitor getVisitorById(int id) throws Exception {
		try{
			return visitorDao.getById(dataBaseAccess.getConnection(), id);
		} catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
	}
	@Override
	public List<Visitor> getCheckedVisitors() throws Exception {
		try {
			return visitorDao.getCheckedVisitors(dataBaseAccess.getConnection(), SortType.id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	@Override
	public List<Visitor> getCheckedVisitorsWithServices() throws Exception {
		try {
			return visitorDao.getCheckedVisitorsWithServices(dataBaseAccess.getConnection());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Visitor> importedVisitors = (List<Visitor>) CommonCSVReader.readFromFile(Visitor.class);
		Connection connetcion = dataBaseAccess.getConnection();
		for (Visitor visitor : importedVisitors) {
			List<Visitor> existingVisitors;
			try {
				connetcion.setAutoCommit(false);
				existingVisitors = visitorDao.getAll(dataBaseAccess.getConnection(), SortType.id);
				if (!existingVisitors.contains(visitor)) {
					visitorDao.create(connetcion, visitor);;
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
			CommonCSVWriter.writeToFile(visitorDao.getAll(dataBaseAccess.getConnection(), SortType.id));
		} catch (Exception e) {
			logger.error("Failed to get used Rooms!", e);
			throw new Exception();
		}
	}
}
