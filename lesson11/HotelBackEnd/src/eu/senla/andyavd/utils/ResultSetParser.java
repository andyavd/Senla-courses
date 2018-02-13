package eu.senla.andyavd.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Room;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public class ResultSetParser {
	
	private static final Logger logger = Logger.getLogger(ResultSetParser.class);
	public static Room parseRoom(ResultSet resultSet) {
		Room room = new Room();
		try {
            room.setId(resultSet.getInt("id"));
            room.setRoomNumber(resultSet.getInt("room_number"));
            room.setCapasity(resultSet.getInt("capacity"));
            room.setDailyPrice(resultSet.getDouble("daily_price"));
            room.setStars(resultSet.getString("stars"));
            room.setStatus(resultSet.getString("room_status"));
        } catch (SQLException e) {
            logger.error("Failed to parse room!");
        }
		return room;
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
//	public static RoomHistory parseHistory(ResultSet resultSet) {
//		RoomHistory history = new RoomHistory();
//		try {
//			history.setId(resultSet.getInt("id"));
//			history.setRoom();
//			
//        } catch (SQLException e) {
//            logger.error("Failed to parse room history!");
//        }
//		return history;
//	}
}
