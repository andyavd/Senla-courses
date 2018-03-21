package eu.senla.andyavd.hotel.dao.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import eu.senla.andyavd.hotel.api.dao.IGenericDao;
import eu.senla.andyavd.hotel.entity.beans.AEntity;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public abstract class GenericDao<T extends AEntity> implements IGenericDao<T> {

	private final static Logger logger = Logger.getLogger(GenericDao.class);

	private Class<T> classs;

	protected GenericDao(Class<T> classs) {
		this.classs = classs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Session session, SortType type) throws Exception {
		if(type == null) {
            type = SortType.id;
		}
		Criteria criteria = session.createCriteria(classs).addOrder(Order.asc(type.toString()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Session session, int id) throws Exception {
		T entity = null;
		try {
			entity = (T) session.get(classs, id);
		} catch (Exception e) {
			logger.error("Dao error! Failed to execute getById method!");
		}
		return entity;
	}

	@Override
	public void create(Session session, T entity) throws Exception {
		try {
			session.save(entity);
		} catch (Exception e) {
			logger.error("Dao error! Failed to execute create method!");
			throw new Exception();
		}
	}

	@Override
	public void update(Session session, T entity) throws Exception {
		try {
			session.update(entity);
		} catch (Exception e) {
			logger.error("Dao error! Failed to execute update method!");
			throw new Exception();
		}
	}

	@Override
	public void delete(Session session, T entity) throws Exception {
		try {
			session.delete(entity);
		} catch (Exception e) {
			logger.error("Dao error! Failed to execute delete method!");
			throw new Exception();
		}
	}
}
