package lesson8.server.properties.src.eu.senla.andyavd.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Settings {
	private static final Logger logger = Logger.getLogger(Settings.class);
	
	private static final String FILENAME = "resources/config.properties";
	private static final String STATUS = "status";
	private static final String COUNT = "count";
	private static final String SAVE_LOAD = "saveload";
	private static final String INSTANCE_PATH = "instancepath";
	private static final String ROOM_HISTORIES = "roomhistories";
	private static final String ROOMS = "rooms";
	private static final String SERVICES = "services";
	private static final String VISITORS = "visitors";
	private static final String DATA_STORAGE = "datastorage";
	
	
	private static Properties properties = new Properties();
	private static CustomProperties customProperties = new CustomProperties();
	private static InputStream input;

	private static Settings setting;

	public static Settings getInstance() {
		if (setting == null) {
			setting = new Settings();
		}
		return setting;
	}

	public Settings() {
		initialize();
	}

	public static Properties initialize() {
		try (InputStream input = new FileInputStream(FILENAME)){

			properties.load(input);
			
			customProperties.setStatus(Boolean.parseBoolean(properties.getProperty(STATUS)));
			customProperties.setCount(Integer.parseInt(properties.getProperty(COUNT)));
			customProperties.setSaveLoadPath(properties.getProperty(SAVE_LOAD));
			customProperties.setEntityPath(properties.getProperty(INSTANCE_PATH));
			customProperties.setRoomHistoriesPath(properties.getProperty(ROOM_HISTORIES));
			customProperties.setRoomsPath(properties.getProperty(ROOMS));
			customProperties.setServicesPath(properties.getProperty(SERVICES));
			customProperties.setVisitorsPath(properties.getProperty(VISITORS));
			customProperties.setVisitorsPath(properties.getProperty(DATA_STORAGE));
			
			return properties;
		} catch (FileNotFoundException e) {
			logger.error("File not Found", e);
			return null;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	public static CustomProperties getCustomProperties() {
		return customProperties;	
	}
}
