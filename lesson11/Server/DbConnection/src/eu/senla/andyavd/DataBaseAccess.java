package eu.senla.andyavd;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.apache.log4j.Logger;
import eu.senla.andyavd.Settings;

public class DataBaseAccess {
	private static DataBaseAccess instance;
	private static final Logger logger = Logger.getLogger(DataBaseAccess.class);
	private static String dbDriver = Settings.getCustomProperties().getDriver();
	private static String dbUrl = Settings.getCustomProperties().getUrl();
	private static String dbUsername = Settings.getCustomProperties().getUsername();
	private static String dbPassword = Settings.getCustomProperties().getPassword();
	private Connection connection;
	private DataBaseAccess() throws Exception {
		createConnection();
	}
	public static DataBaseAccess getInstance() throws Exception {
		if (instance == null)
			instance = new DataBaseAccess();
		return instance;
	}
	
	
	
	private void createConnection() throws Exception {
		try {
		Class.forName(dbDriver);
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		logger.info("Successfull connection!");
		} catch (ClassNotFoundException e) {
			logger.error("Failed to establish connection to the database.", e);
        } catch(SQLException e) {
        		logger.error("Failed to establish connection to the database.", e);
        		throw new Exception();
        }
	}
	public Connection getConnection() {
		if(connection == null) {
			try {
				createConnection();
			} catch (Exception e) {
				logger.error("Failed to create connection", e);
			}
		}
		return connection;
	}
	public void closeConnection() throws Exception {
		try {
			if(connection != null) {
				connection.close();
				connection = null;
				logger.info("Disconnected");
			}
		} catch (SQLException e) {
			logger.error("Failed to disconnect", e);
			throw new Exception();
		}
	}
}
