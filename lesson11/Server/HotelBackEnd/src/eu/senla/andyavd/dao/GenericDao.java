package eu.senla.andyavd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.AEntity;
import eu.senla.andyavd.api.dao.IGenericDao;
import eu.senla.andyavd.enums.SortType;

public abstract class GenericDao<T extends AEntity> implements IGenericDao<T> {
	
	private final static Logger logger = Logger.getLogger(GenericDao.class);
	protected static final String ORDER_BY = " order by ";
	
	protected abstract T parseResult(ResultSet resultSet);
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getByIdQuery();
    protected abstract String getAllQuery(SortType type);
    protected abstract void setInsertPreparedStatement(PreparedStatement statement, T entity) throws SQLException;
    protected abstract void setUpdatePreparedStatement(PreparedStatement statement, T entity) throws SQLException;
    
    @Override
    public List<T> getAll(Connection connection, SortType type) throws Exception {
    		List<T> list = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(getAllQuery(type))) {
            	ResultSet resultSet = null;
            	resultSet = statement.executeQuery();
            while (resultSet.next()) {
                    list.add(parseResult(resultSet));
                }
            } catch (SQLException e) {
            	logger.error("Failed to get!");
            	throw new Exception();
            }
    		return list;
    }
    @Override
	public T getById(Connection connection, int id) throws Exception {
		T entity = null;
        try (PreparedStatement statement = connection.prepareStatement(getByIdQuery())) {
            statement.setInt(1, id);
            entity = parseResult(statement.executeQuery());
        } catch (SQLException e) {
        		logger.error("Failed to get by id!");
        		throw new Exception();
        }
        return entity;
	}
    @Override
	public void create(Connection connection, T entity) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(getInsertQuery())) {
			setInsertPreparedStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to create entity!");
        		throw new Exception();
        }
	}
    @Override
	public void update(Connection connection, T entity) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
			setUpdatePreparedStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to update entity!");
        		throw new Exception();
        }
	}
    @Override
	public void delete(Connection connection, int id) throws Exception{
		try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	logger.error("Failed to delete entity!");
        	throw new Exception();
        }
	}
}
