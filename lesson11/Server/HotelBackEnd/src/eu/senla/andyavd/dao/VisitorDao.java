package eu.senla.andyavd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.dao.IVisitorDao;
import eu.senla.andyavd.enums.SortType;

public class VisitorDao extends GenericDao<Visitor> implements IVisitorDao {

	private final static Logger logger = Logger.getLogger(VisitorDao.class);
	private final String UPDATE_VISITOR = "UPDATE visitor SET visitor_mane = ? WHERE id = ?";
    private final String CREATE_VISITOR = "INSERT INTO room (room_number, capacity, daily_price, stars) VALUES (?,?,?,?) ";
    private final String GET_VISITORS = "SELECT * FROM visitor";
    private final String GET_VISITOR = "SELECT * FROM visitor WHERE id = ?";
    private final String DELETE_VISITOR = "DELETE * FROM visitor WHERE id = ?";
    private final String GET_CHECKED_VISITORS = "SELECT * FROM visitor WHERE visitor.id IN (SELECT visitor_id FROM history WHERE history_status = 'CheckIn')";
    private final String GET_CHECKED_VISITORS_WITH_SERVICES = "SELECT * FROM visitor WHERE visitor.id IN (SELECT visitor_id FROM history WHERE history.id IN (SELECT DISTINCT history.id FROM history, visitor_service WHERE history.id = history_id AND history_status = 'CheckIn'))";
    private final String ORDER_BY = " ORDER BY";
		
	public VisitorDao() {
    }
	@Override
	protected Visitor parseResult(ResultSet resultSet) {
		return parseVisitor(resultSet);
	}
	@Override
	public List<Visitor> getCheckedVisitors(Connection connection, SortType type) throws Exception {
		List<Visitor> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CHECKED_VISITORS + ORDER_BY + type)) {
        	ResultSet resultSet = null;
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseVisitor(resultSet));
            }
        } catch (SQLException e) {
        		logger.error("Failed to get checked Visitors!");
        		throw new Exception();
        }
		return list;
	}
	@Override
	public List<Visitor> getCheckedVisitorsWithServices(Connection connection) throws Exception {
		List<Visitor> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CHECKED_VISITORS_WITH_SERVICES)) {
        	ResultSet resultSet = null;
        	resultSet = statement.executeQuery();
        while (resultSet.next()) {
                list.add(parseVisitor(resultSet));
            }
        } catch (SQLException e) {
        		logger.error("Failed to get checked Visitors!");
        		throw new Exception();
        }
        return list;
	}
	@Override
	protected String getInsertQuery() {
		return CREATE_VISITOR;
	}
	@Override
	protected String getAllQuery(SortType type) {
		return GET_VISITORS + ORDER_BY;
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
	protected void setInsertPreparedStatement(PreparedStatement statement, Visitor entity) throws SQLException {
		statement.setString(1, entity.getLastName());
	}
	@Override
	protected void setUpdatePreparedStatement(PreparedStatement statement, Visitor entity) throws SQLException {
		statement.setString(1, entity.getLastName());
		statement.setInt(2, entity.getId());
	}
	public static Visitor parseVisitor(ResultSet resultSet) {
		Visitor visitor = new Visitor();
		try {
			visitor.setId(resultSet.getInt("id"));
			visitor.setLastName(resultSet.getString("visitor_name"));
        } catch (SQLException e) {
            logger.error("Failed to parse visitor!");
        }
		return visitor;
	}
}
