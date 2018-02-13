package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IAEntityDao<T>{
	List<T> getAll(Connection connection) throws SQLException;
	T getById(Connection connection, Integer id) throws SQLException;
	void create(Connection connection, T t) throws SQLException;
	void update(Connection connection, T object) throws SQLException;
	void delete(Connection connection, Integer id) throws SQLException;
}