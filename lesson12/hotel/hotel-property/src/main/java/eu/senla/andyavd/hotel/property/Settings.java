package eu.senla.andyavd.hotel.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Settings {
	
	private static final Logger logger = Logger.getLogger(Settings.class);

	private static final String FILENAME = "/hotel-property/src/main/resources/config.properties";
	private static final String STATUS = "status";
	private static final String COUNT = "count";
	private static final String SAVE_LOAD = "saveload";
	private static final String INSTANCE_PATH = "instancepath";
	private static final String ROOM_HISTORIES = "roomhistories";
	private static final String ROOMS = "rooms";
	private static final String SERVICES = "services";
	private static final String VISITORS = "visitors";
	private static CustomProperties customProperties;

	public static CustomProperties getCustomProperties() {
		if (customProperties == null) {
			Properties properties = new Properties();
			try (FileInputStream input = new FileInputStream(FILENAME)) {
				customProperties = new CustomProperties();
				properties.load(input);
				customProperties.setStatus(Boolean.parseBoolean(properties.getProperty(STATUS)));
				customProperties.setCount(Integer.parseInt(properties.getProperty(COUNT)));
				customProperties.setSaveLoadPath(properties.getProperty(SAVE_LOAD));
				customProperties.setInstancePath(properties.getProperty(INSTANCE_PATH));
				customProperties.setRoomHistoriesPath(properties.getProperty(ROOM_HISTORIES));
				customProperties.setRoomsPath(properties.getProperty(ROOMS));
				customProperties.setServicesPath(properties.getProperty(SERVICES));
				customProperties.setVisitorsPath(properties.getProperty(VISITORS));
			} catch (FileNotFoundException e) {
				logger.error("File not Found", e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return customProperties;
	}
}
