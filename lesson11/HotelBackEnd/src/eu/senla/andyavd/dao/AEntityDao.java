package eu.senla.andyavd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.AEntity;
import eu.senla.andyavd.api.dao.IAEntityDao;

public abstract class AEntityDao<T extends AEntity> implements IAEntityDao<T> {
	
	final static Logger logger = Logger.getLogger(AEntityDao.class);
	protected static final String SQL_ERROR = "SQL Error!";
	
	protected abstract T parseResult(ResultSet resultSet);
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getByIdQuery();
    protected abstract String getGetAllQuery();
    protected abstract void setInsertPreparedStatement(PreparedStatement statement, T entity) throws SQLException;
    protected abstract void setUpdatePreparedStatement(PreparedStatement statement, T entity) throws SQLException;
    
    @Override
    public List<T> getAll(Connection connection) throws SQLException {
    		List<T> list = new ArrayList<>();
    		ResultSet resultSet = null;
            try (PreparedStatement statement = connection.prepareStatement(getGetAllQuery())) {
            	resultSet = statement.executeQuery();
            while (resultSet.next()) {
                    list.add(parseResult(resultSet));
                }
            } catch (SQLException e) {
            	logger.error("Failed to get!");
            }
    		return list;
    }
    @Override
	public T getById(Connection connection, Integer id) throws SQLException {
		T entity = null;
        try (PreparedStatement statement = connection.prepareStatement(getByIdQuery())) {
            statement.setInt(1, id);
            entity = parseResult(statement.executeQuery());
        } catch (SQLException e) {
        		logger.error("Failed to get by id!");
        }
        return entity;
	}
    @Override
	public void create(Connection connection, T entity) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(getInsertQuery())) {
			setInsertPreparedStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to get by create entity!");
        }
	}
    @Override
	public void update(Connection connection, T entity) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
			setUpdatePreparedStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to update entity!");
        }
	}
    @Override
	public void delete(Connection connection, Integer id) throws SQLException{
		try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	logger.error("Failed to delete entity!");
        }
	}
}
