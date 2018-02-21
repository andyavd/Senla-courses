package eu.senla.andyavd.dao;

import static eu.senla.andyavd.dao.VisitorDao.parseVisitor;
import static eu.senla.andyavd.dao.ServiceDao.parseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DateFormatter;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.dao.IRoomHistoryDao;
import eu.senla.andyavd.enums.SortType;

public class RoomHistoryDao extends GenericDao<RoomHistory> implements IRoomHistoryDao {
	public RoomHistoryDao() {
    }
	private final static Logger logger = Logger.getLogger(RoomHistoryDao.class);
	private final String CREATE_HISTORY = "INSERT INTO history (visitor_id, room_id, check_in, check_out, history_status) VALUES (?,?,?,?, 'CheckIn') ";
	private final String UPDATE_HISTORY = "UPDATE history SET visitor_id = ?, room_id = ?, check_in = ?, check_out = ?, history_status = ? WHERE id = ?";
	private final String CHECK_OUT = "UPDATE history SET history_status = 'CheckOut' WHERE visitor_id = ?";
	private final String VISITORS_ON_DATE = "SELECT count(*) FROM history WHERE ? BETWEEN check_in and check_out AND history_status = 'CheckIn'";
    private final String GET_HISTORIES = "SELECT * FROM history";
    private final String GET_HISTORY = "SELECT * FROM history WHERE id = ?";
    private final String DELETE_HISTORY = "DELETE * FROM history WHERE id = ?";
    private final String ADD_SERVICE = "INSERT INTO visitor_service (history_id, service_id) VALUES ((SELECT history.id FROM history WHERE history_status = 'CheckIn' AND visitor_id = ?), ?)";
    private final String GET_ROOM_PRICE = "SELECT room.daily_price FROM room WHERE room.id = (SELECT room_id FROM history WHERE visitor_id = ?);";
    private final String GET_DAYS = "SELECT DATEDIFF((SELECT check_out FROM history, visitor WHERE visitor.id = history.visitor_id AND visitor.id = ?), (SELECT check_in FROM history, visitor WHERE visitor.id = history.visitor_id AND visitor.id = ?))";
    private final String ORDER_BY = " ORDER BY";
	
	@Override
	protected RoomHistory parseResult(ResultSet resultSet) {
		return parseHistory(resultSet);
	}
	@Override
	protected String getInsertQuery() {
		return CREATE_HISTORY;
	}
	@Override
	protected String getUpdateQuery() {
		return UPDATE_HISTORY;
	}
	@Override
	protected String getDeleteQuery() {
		return DELETE_HISTORY;
	}
	@Override
	protected String getByIdQuery() {
		return GET_HISTORY;
	}
	@Override
	protected String getAllQuery(SortType type) {
		return GET_HISTORIES + ORDER_BY + type;
	}
	@Override
	protected void setInsertPreparedStatement(PreparedStatement statement, RoomHistory entity) throws SQLException {
		statement.setInt(1, entity.getVisitorId());
		statement.setInt(2, entity.getRoomId());
		statement.setString(3, DateFormatter.stringFromDate(entity.getCheckInDate()));
		statement.setString(4, DateFormatter.stringFromDate(entity.getCheckOutDate()));
	}
	@Override
	protected void setUpdatePreparedStatement(PreparedStatement statement, RoomHistory entity) throws SQLException {
		statement.setInt(1, entity.getVisitorId());
		statement.setInt(2, entity.getRoomId());
		statement.setString(3, DateFormatter.stringFromDate(entity.getCheckInDate()));
		statement.setString(4, DateFormatter.stringFromDate(entity.getCheckOutDate()));
		statement.setString(5, entity.getStatus());
		statement.setInt(6, entity.getId());
	}
	@Override
	public List<Service> getVisitorServices(Connection connection, int visitorId) throws Exception {
		List<Service> list = new ArrayList<>();
		
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE service.id IN (SELECT service_id FROM visitor_service WHERE history_id = (SELECT history.id FROM history WHERE visitor_id = " 
		+ visitorId + ")) ORDER BY daily_price;")) {
        ResultSet resultSet = null;
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseService(resultSet));
            }
        } catch (SQLException e) {
        	logger.error("Failed to get Visitors services!");
        	throw new Exception();
        }
		return list;
	}
	@Override
	public Double getVisitorRoomPrice(Connection connection, int visitorId) throws Exception {
		Double price = 0.0;
        try (PreparedStatement statement = connection.prepareStatement(GET_ROOM_PRICE)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, visitorId);
            while (resultSet.next()) {
            		price = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            logger.error("Failed to get empty Rooms number!");
            throw new Exception();
        }
		return price;
	}
	@Override
	public Integer getVisitorsDays(Connection connection, int visitorId) throws Exception {
        int days = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_DAYS)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, visitorId);
            statement.setInt(2, visitorId);
            while (resultSet.next()) {
            		days = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Failed to get empty Rooms number!");
            throw new Exception();
        }
        return days;
	}
	@Override
	public Integer getTotalVisitorsOnDate(Connection connection, String date) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(VISITORS_ON_DATE)) {
            statement.setString(1, date);
            return statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to count Visitors!");
        		throw new Exception();
        }
	}
	@Override
	public List<Visitor> getLastVisitorsOfRoom(Connection connection, int roomId) throws Exception {
		List<Visitor> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT visitor.id, visitor_name FROM visitor, history WHERE visitor.id = history.visitor_id AND room_id = " 
		+ roomId + " ORDER BY check_out DESC LIMIT 0, 3")) {
        	ResultSet resultSet = null;
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseVisitor(resultSet));
            }
        } catch (SQLException e) {
        	logger.error("Failed to get Visitors of the room!");
        	throw new Exception();
        }
		return list;
	}
	@Override
	public void addServicesToVisitor(Connection connection, int visitorId, int serviceId) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(ADD_SERVICE)) {
            statement.setInt(1, visitorId);
            statement.setInt(2, serviceId);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to check out the Visitor!");
        		throw new Exception();
        }
	}
	@Override
	public void checkOutVisitor(Connection connection, int visitorId) throws Exception {
		try (PreparedStatement statement = connection.prepareStatement(CHECK_OUT)) {
            statement.setInt(1, visitorId);
            statement.executeUpdate();
        } catch (SQLException e) {
        		logger.error("Failed to check out the Visitor!");
        		throw new Exception();
        }
	}
	public static RoomHistory parseHistory(ResultSet resultSet) {
		RoomHistory history = new RoomHistory();
		try {
			history.setId(resultSet.getInt("id"));
			history.setRoomId(resultSet.getInt("room_id"));
			history.setVisitorId(resultSet.getInt("visitor_id"));
			history.setCheckInDate(resultSet.getDate("check_in"));
			history.setCheckOutDate(resultSet.getDate("check_out"));
			history.setStatus(resultSet.getString("history_status"));
        } catch (SQLException e) {
            logger.error("Failed to parse room history!");
        }
		return history;
	}
}