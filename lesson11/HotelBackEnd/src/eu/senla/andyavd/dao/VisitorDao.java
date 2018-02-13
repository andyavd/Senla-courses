package eu.senla.andyavd.dao;

import static eu.senla.andyavd.utils.ResultSetParser.parseService;
import static eu.senla.andyavd.utils.ResultSetParser.parseVisitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.time.temporal.ChronoUnit;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.dao.IVisitorDao;

public class VisitorDao extends AEntityDao<Visitor> implements IVisitorDao {

	final static Logger logger = Logger.getLogger(VisitorDao.class);
	private final String UPDATE_VISITOR = "UPDATE room SET daily_price = ?, capacity = ?, stars = ?, room_status = ? WHERE id = ?";
    private final String CREATE_VISITOR = "INSERT INTO room (room_number, capacity, daily_price, stars) VALUES (?,?,?,?) ";
    private final String GET_VISITORS = "SELECT * FROM visitor";
    private final String GET_VISITOR = "SELECT * FROM visitor WHERE id = ?";
    private final String DELETE_VISITOR = "DELETE * FROM visitor WHERE id = ?";
    private final String ORDER_BY = " ORDER BY";
	private final String NAME = " visitor_name";
	private final String PRICE = " service.daily_price";
	
	private static VisitorDao visitorDao;
	public static VisitorDao getInstance() {
		if (visitorDao == null) {
			visitorDao = new VisitorDao();
		}
		return visitorDao;
	}
	public VisitorDao() {
    }
	@Override
	protected Visitor parseResult(ResultSet resultSet) {
		return parseVisitor(resultSet);
	}
	@Override
	public List<Service> getVisitorServices(Connection connection, Visitor visitor) {
		List<Service> list = new ArrayList<>();
		if (visitor.getHistory() != null) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT visitor_name, service_name, service.daily_price FROM visitor, service, history, visitor_service WHERE visitor.id = history.visitor_id AND history.visitor_service_id = visitor_service.history_id AND visitor_service.service_id = service.id AND visitor.id = ?")) {
	        	statement.setInt(1, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	                list.add(parseService(resultSet));
	            }
	        } catch (SQLException e) {
	        	logger.error("Failed to get Visitors Services!");
	        }
        }
		return list;
	}
	@Override
	public Double billVisitor(Connection connection, Visitor visitor) {
		Double serviceSum = 0.0;
		int days = 0;
		Double roomPrice = 0.0;
		Double roomSum = 0.0;
		Double totalSum = 0.0;
		//count services sum
		if ((visitor.getHistory() != null)&&(visitor.getHistory().getService() != null)) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT SUM(service.daily_price) FROM visitor, service, history, visitor_service WHERE visitor.id = history.visitor_id AND history.visitor_service_id = visitor_service.history_id AND visitor_service.service_id = service.id AND visitor.id = ?")) {
	        	statement.setInt(1, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        		serviceSum = resultSet.getDouble(1);
	            }
	        } catch (SQLException e) {
	        	logger.error("Failed to get Visitors Services!");
	        }
		} else {
			serviceSum = 0.0;
		}
		//count days in a room
		if (visitor.getHistory() != null) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT DATEDIFF((SELECT check_out AS outdate FROM history, visitor WHERE visitor.id = history.visitor_id AND visitor.id = ?), (SELECT check_in AS indate FROM history, visitor WHERE visitor.id = history.visitor_id AND visitor.id = ?));")) {
	        	statement.setInt(1, visitor.getId());
	        	statement.setInt(2, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        		days = resultSet.getInt(1);
	        		}
	        } catch (SQLException e) {
	        	logger.error("Failed to get days!");
	        }
		} else {
			days = 0;
		}
		
		///////////////////////
		if (visitor.getHistory() != null) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT check_in, check_out FROM history, visitor WHERE visitor.id = history.visitor_id AND visitor.id = ?;")) {
	        	statement.setInt(1, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        		days = resultSet.getInt(1);
	        		}
	        } catch (SQLException e) {
	        	logger.error("Failed to get days!");
	        }
		} else {
			days = 0;
		}
		//long day = ChronoUnit.DAYS.between(visitor.getHistory().getCheckInDate(), visitor.getHistory().getCheckOutDate());
		///////////////////////
        //get room price
		if (visitor.getHistory() != null) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT room.daily_price FROM history, visitor, room  WHERE visitor.id = history.visitor_id AND room.id = history.room_id AND visitor.id = ?;")) {
	        	statement.setInt(1, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        	roomPrice = resultSet.getDouble(1);
	        		}
	        } catch (SQLException e) {
	        	logger.error("Failed to get Room price!");
	        }
		} else {
			roomPrice = 0.0;
		}
		roomSum = roomPrice * days;
		totalSum = serviceSum + roomSum;
		return totalSum;
	}
	@Override
	public List<Visitor> sortVisitorsByName(Connection connection) {
		List<Visitor> list = new ArrayList<>();
		ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_VISITORS + ORDER_BY + NAME)) {
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseVisitor(resultSet));
            }
        } catch (SQLException e) {
        	logger.error("Failed to sort Visitors by name!");
        }
		return list;
	}
	@Override
	public List<Service> sortVisitorServicesByPrice(Connection connection, Visitor visitor) {
		List<Service> list = new ArrayList<>();
		if (visitor.getHistory() != null) {
			ResultSet resultSet = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT visitor_name, service_name, service.daily_price FROM visitor, service, history, visitor_service WHERE visitor.id = history.visitor_id AND history.visitor_service_id = visitor_service.history_id AND visitor_service.service_id = service.id AND visitor.id = ?"
			+ ORDER_BY + PRICE)) {
	        	statement.setInt(1, visitor.getId());
	        	resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	                list.add(parseService(resultSet));
	            }
	        } catch (SQLException e) {
	        	logger.error("Failed to sort Visitors Services!");
	        }
        }
		return list;
	}
	@Override
	protected String getInsertQuery() {
		return CREATE_VISITOR;
	}
	@Override
	protected String getUpdateQuery() {
		return UPDATE_VISITOR;
	}
	@Override
	protected String getDeleteQuery() {
		return DELETE_VISITOR;
	}
	@Override
	protected String getByIdQuery() {
		return GET_VISITOR;
	}
	@Override
	protected String getGetAllQuery() {
		return GET_VISITORS;
	}
	@Override
	protected void setInsertPreparedStatement(PreparedStatement statement, Visitor entity) throws SQLException {
		statement.setString(1, entity.getLastName());
	}
	@Override
	protected void setUpdatePreparedStatement(PreparedStatement statement, Visitor entity) throws SQLException {
		statement.setInt(1, entity.getId());
		statement.setString(2, entity.getLastName());
	}
}
