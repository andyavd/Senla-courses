package eu.senla.andyavd.hotel.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.senla.andyavd.hotel.api.dao.IUserDao;
import eu.senla.andyavd.hotel.api.services.IUserService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public class UserService implements IUserService {

	private final static Logger logger = Logger.getLogger(UserService.class);
	private IUserDao userDao = (IUserDao) DependencyInjection.getInstance().getClassInstance(IUserDao.class);
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	public UserService() {
	}

	@Override
	public void registerUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			userDao.create(session, user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to create the User!");
			throw new Exception();
		}
	}

	@Override
	public List<User> getUsers(SortType type) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<User> users = userDao.getAll(session, SortType.id);
			transaction.commit();
			return users;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the Users!");
			throw new Exception();
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			userDao.update(session, user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to update the User!");
			throw new Exception();
		}
	}

	@Override
	public void deleteUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			userDao.delete(session, user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to delete the User!");
			throw new Exception();
		}
	}

	@Override
	public User getUserById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User user = userDao.getById(session, id);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the User!");
			throw new Exception();
		}
	}
	
	@Override
	public User getUserByUsername(String username) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User user = userDao.getUserByUsername(session, username);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Failed to get the User!");
			throw new Exception();
		}
	}
}
