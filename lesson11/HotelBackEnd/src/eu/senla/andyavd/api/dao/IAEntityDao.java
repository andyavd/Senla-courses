package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import eu.senla.andyavd.enums.SortType;

public interface IAEntityDao<T>{
	List<T> getAll(Connection connection, SortType type) throws SQLException;
	T getById(Connection connection, int id) throws SQLException;
	void create(Connection connection, T t) throws SQLException;
	void update(Connection connection, T object) throws SQLException;
	void delete(Connection connection, int id) throws SQLException;
}