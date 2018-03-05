package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.dao.IVisitorDao;
import eu.senla.andyavd.hotel.api.managers.IVisitorManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class VisitorService extends AService implements IVisitorManager {

	private final static Logger logger = Logger.getLogger(VisitorService.class);
	private IVisitorDao visitorDao = (IVisitorDao) DependencyInjection.getInstance().getInstance(IVisitorDao.class);

	public VisitorService() {
	}

	@Override
	public void addVisitor(Visitor visitor) throws Exception {
		try {
			session.beginTransaction();
			visitorDao.create(session, visitor);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to create the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<Visitor> getVisitors() throws Exception {
		List<Visitor> visitors = null;
		try {
			session.beginTransaction();
			visitors = visitorDao.getAll(session, SortType.lastName);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Visitors!");
			throw new Exception();
		}
		return visitors;
	}

	@Override
	public void updateVisitor(Visitor visitor) throws Exception {
		try {
			session.beginTransaction();
			visitorDao.update(session, visitor);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to update the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public void deleteVisitor(Visitor visitor) throws Exception {
		try {
			session.beginTransaction();
			visitorDao.delete(session, visitor);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to delete the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public Visitor getVisitorById(int id) throws Exception {
		Visitor visitor = null;
		try {
			session.beginTransaction();
			visitorDao.getById(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get the Visitor!");
			throw new Exception();
		}
		return visitor;
	}

	@Override
	public List<Visitor> getCheckedVisitors() throws Exception {
		List<Visitor> visitors = null;
		try {
			session.beginTransaction();
			visitors = visitorDao.getCheckedVisitors(session, SortType.id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get checked Visitors!");
			throw new Exception();
		}
		return visitors;
	}

	@Override
	public List<Visitor> getCheckedVisitorsWithServices() throws Exception {
		List<Visitor> visitors = null;
		try {
			session.beginTransaction();
			visitors = visitorDao.getCheckedVisitorsWithServices(session, SortType.id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to get checked Visitors with services!");
			throw new Exception();
		}
		return visitors;
	}

	@Override
	public void importFromCsv() throws Exception {
		@SuppressWarnings("unchecked")
		List<Visitor> importedVisitors = (List<Visitor>) CsvReader.readFromFile(Visitor.class);
		try {
			session.beginTransaction();
			for (Visitor visitor : importedVisitors) {
				session.saveOrUpdate(visitor);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to import the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		try {
			session.beginTransaction();
			CsvWriter.writeToFile(visitorDao.getAll(session, SortType.id));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Failed to export the Rooms!");
			throw new Exception();
		}
	}
}
