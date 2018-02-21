package eu.senla.andyavd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.api.dao.IServiceDao;
import eu.senla.andyavd.enums.SortType;

public class ServiceDao extends GenericDao<Service> implements IServiceDao{

	private final static Logger logger = Logger.getLogger(ServiceDao.class);
    private final String CREATE_SERVICE = "INSERT INTO service (service_name, daily_price) VALUES (?,?) ";
	private final String UPDATE_SERVICE = "UPDATE service SET service_name = ?, daily_price = ? WHERE id = ?";
	private final String CHANGE_PRICE = "UPDATE service SET daily_price = ? WHERE id = ?";
    private final String GET_SERVICES = "SELECT * FROM service ORDER BY ";
    private final String GET_SERVICE = "SELECT * FROM service WHERE id = ?";
    private final String DELETE_SERVICE = "DELETE * FROM service WHERE id = ?";
	
	public ServiceDao() {
	}
	@Override
	protected Service parseResult(ResultSet resultSet) {
		return parseService(resultSet);
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
	protected String getAllQuery(SortType type) {
		return GET_SERVICES + type;
	}
	@Override
	public void changeServicePrice(Connection connection, Double daily_price,  int id) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(CHANGE_PRICE)) {
            statement.setDouble(1, daily_price);
			statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	logger.error("Failed to change Service Price!", e);
        	throw new Exception();
        }
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
	public static Service parseService(ResultSet resultSet) {
		Service service = new Service();
		try {
			service.setId(resultSet.getInt("id"));
			service.setName(resultSet.getString("service_name"));
			service.setDailyPrice(resultSet.getDouble("daily_price"));
        } catch (SQLException e) {
            logger.error("Failed to parse service!");
        }
		return service;
	}
}