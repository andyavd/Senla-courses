package eu.senla.andyavd.dao;

import static eu.senla.andyavd.utils.ResultSetParser.parseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.api.dao.IServiceDao;

public class ServiceDao extends AEntityDao<Service> implements IServiceDao{

	final static Logger logger = Logger.getLogger(ServiceDao.class);
	private final String UPDATE_SERVICE = "UPDATE service SET service_name = ?, daily_price = ? WHERE id = ?";
    private final String CREATE_SERVICE = "INSERT INTO service (service_name, daily_price) VALUES (?,?) ";
    private final String GET_SERVICES = "SELECT * FROM service";
    private final String GET_SERVICE = "SELECT * FROM service WHERE id = ?";
    private final String DELETE_SERVICE = "DELETE * FROM service WHERE id = ?";
    private final String ORDER_BY = " ORDER BY";
    private final String NAME = " service_name";
	private final String PRICE = " service.daily_price";
	
	private static ServiceDao serviceDao;
	public static ServiceDao getInstance() {
		if (serviceDao == null) {
			serviceDao = new ServiceDao();
		}
		return serviceDao;
	}
	public ServiceDao() {
	}
	@Override
	protected Service parseResult(ResultSet resultSet) {
		return parseService(resultSet);
	}
	@Override
	public List<Service> sortServicesByName(Connection connection) {
		List<Service> list = new ArrayList<>();
		ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_SERVICES + ORDER_BY + NAME)) {
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseService(resultSet));
            }
        } catch (SQLException e) {
        	logger.error("Failed to sort Services by name!");
        }
		return list;
	}
	@Override
	public List<Service> sortServicesByPrice(Connection connection) {
		List<Service> list = new ArrayList<>();
		ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_SERVICES + ORDER_BY + PRICE)) {
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseService(resultSet));
            }
        } catch (SQLException e) {
        	logger.error("Failed to sort Services by price!");
        }
		return list;
	}
	@Override
	protected String getInsertQuery() {
		return CREATE_SERVICE;
	}
	@Override
	protected String getUpdateQuery() {
		return UPDATE_SERVICE;
	}
	@Override
	protected String getDeleteQuery() {
		return DELETE_SERVICE;
	}
	@Override
	protected String getByIdQuery() {
		return GET_SERVICE;
	}
	@Override
	protected String getGetAllQuery() {
		return GET_SERVICES;
	}
	@Override
	protected void setInsertPreparedStatement(PreparedStatement statement, Service entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setDouble(2, entity.getDailyPrice());	
	}
	@Override
	protected void setUpdatePreparedStatement(PreparedStatement statement, Service entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setDouble(2, entity.getDailyPrice());
		statement.setInt(3, entity.getId());
	}
}