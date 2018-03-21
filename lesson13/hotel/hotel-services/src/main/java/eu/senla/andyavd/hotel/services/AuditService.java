package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IAuditDao;
import eu.senla.andyavd.hotel.api.services.IAuditService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Audit;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public class AuditService implements IAuditService {

	private final static Logger logger = Logger.getLogger(AuditService.class);
	private IAuditDao auditDao = (IAuditDao) DependencyInjection.getInstance().getClassInstance(IAuditDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	public AuditService() {
	}

	@Override
	public void addAudit(Audit audit) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			auditDao.create(session, audit);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the Audit line!");
			throw new Exception();
		}
	}

	@Override
	public List<Audit> getAudits(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Audit> audits = auditDao.getAll(session, SortType.id);
			transaction.commit();
			return audits;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Audit lines!");
			throw new Exception();
		}
	}
}
