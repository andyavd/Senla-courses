package eu.senla.andyavd.hotel.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IGenericDao<T> {
	
	List<T> getAll(Session session, SortType type) throws SQLException, Exception;

	T getById(Session session, int id) throws SQLException, Exception;

	void create(Session session, T entity) throws SQLException, Exception;

	void update(Session session, T entity) throws SQLException, Exception;

	void delete(Session session, T entity) throws SQLException, Exception;
}