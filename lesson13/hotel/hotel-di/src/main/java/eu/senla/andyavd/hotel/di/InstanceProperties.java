package eu.senla.andyavd.hotel.di;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class InstanceProperties {

	private static final Logger logger = Logger.getLogger(InstanceProperties.class);
	private static final String PATH = "/Users/andreiaudzeichyk/eclipse-workspace/hotel/hotel-di/src/main/resources/instance.properties";
	private static Map<Object, Object> propertiesMap;

	private static void init() {
		propertiesMap = new Properties();
		try (FileInputStream input = new FileInputStream(PATH)) {
			((Properties) propertiesMap).load(input);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static String getClassName(String key) {
		if (propertiesMap == null) {
			init();
		}
		return (String) propertiesMap.get(key);
	}
}