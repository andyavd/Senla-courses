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
//	private static String dbDriver = "com.mysql.jdbc.Driver";
//	private static String dbUrl = "jdbc:mysql://localhost:3306/hotel? autoReconnect=true&useSSL=false";
//	private static String dbUsername = "root";
//	private static String dbPassword = "root";
	private Connection connection;
	private DataBaseAccess() {
		createConnection();
	}
	public static DataBaseAccess getInstance() {
		if (instance == null)
			instance = new DataBaseAccess();
		return instance;
	}
	private void createConnection() {
		try {
		Class.forName(dbDriver);
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		logger.info("Successfull connection!");
		} catch (ClassNotFoundException e) {
			logger.error("Failed to establish connection to the database.", e);
        } catch(SQLException e) {
        		logger.error("Failed to establish connection to the database.", e);
        }
	}
	public Connection getConnection() {
		if(connection == null) {
			createConnection();
		}
		return connection;
	}
	public void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				logger.info("Disconnected");
			}
		} catch (SQLException e) {
			logger.error("Failed to disconnect", e);
		}
	}
}
