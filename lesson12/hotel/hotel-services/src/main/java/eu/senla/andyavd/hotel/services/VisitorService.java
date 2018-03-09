package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IVisitorDao;
import eu.senla.andyavd.hotel.api.services.IVisitorService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.utils.csv.CsvReader;
import eu.senla.andyavd.hotel.utils.csv.CsvWriter;

public class VisitorService implements IVisitorService {

	private final static Logger logger = Logger.getLogger(VisitorService.class);
	private IVisitorDao visitorDao = (IVisitorDao) DependencyInjection.getInstance().getInstance(IVisitorDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	public VisitorService() {
	}

	@Override
	public void addVisitor(Visitor visitor) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			visitorDao.create(session, visitor);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<Visitor> getVisitors(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Visitor> visitors = visitorDao.getAll(session, SortType.lastName);
			transaction.commit();
			return visitors;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Visitors!");
			throw new Exception();
		}
	}

	@Override
	public void updateVisitor(Visitor visitor) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			visitorDao.update(session, visitor);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to update the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public void deleteVisitor(Visitor visitor) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			visitorDao.delete(session, visitor);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to delete the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public Visitor getVisitorById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Visitor visitor = visitorDao.getById(session, id);
			transaction.commit();
			return visitor;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Visitor!");
			throw new Exception();
		}
	}

	@Override
	public List<Visitor> getCheckedVisitors() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Visitor> visitors = visitorDao.getCheckedVisitors(session, SortType.id);
			transaction.commit();
			return visitors;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get checked Visitors!");
			throw new Exception();
		}
	}

	@Override
	public List<Visitor> getCheckedVisitorsWithServices() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Visitor> visitors = visitorDao.getCheckedVisitorsWithServices(session, SortType.id);
			transaction.commit();
			return visitors;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get checked Visitors with services!");
			throw new Exception();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void importFromCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		List<Visitor> importedVisitors = (List<Visitor>) CsvReader.readFromFile(Visitor.class);
		try {
			transaction = session.beginTransaction();
			for (Visitor visitor : importedVisitors) {
				session.saveOrUpdate(visitor);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to import the Rooms!");
			throw new Exception();
		}
	}

	@Override
	public void exportToCsv() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CsvWriter.writeToFile(visitorDao.getAll(session, SortType.id));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to export the Rooms!");
			throw new Exception();
		}
	}
}
