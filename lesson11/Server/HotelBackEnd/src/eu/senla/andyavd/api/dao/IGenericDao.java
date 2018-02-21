package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import eu.senla.andyavd.enums.SortType;

public interface IGenericDao<T>{
	List<T> getAll(Connection connection, SortType type) throws SQLException, Exception;
	T getById(Connection connection, int id) throws SQLException, Exception;
	void create(Connection connection, T t) throws SQLException, Exception;
	void update(Connection connection, T object) throws SQLException, Exception;
	void delete(Connection connection, int id) throws SQLException, Exception;
}