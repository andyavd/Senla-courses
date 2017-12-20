package eu.senla.andyavd.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Settings {
	private static final Logger logger = Logger.getLogger(Settings.class);
	
	private static final String FILENAME = "/Users/andreiaudzeichyk/Senla-courses/lesson5/properties/resources/config.properties";
	
	private static Properties properties = new Properties();
	private static InputStream input;

	private static Settings setting;

	public static Settings getInstance() {
		if (setting == null) {
			setting = new Settings();
		}
		return setting;
	}

	public Settings() {
		this.initialize();
	}

	private void initialize() {

		try {
			input = new FileInputStream(FILENAME);
			properties.load(input);
		} catch (FileNotFoundException e) {
			logger.error("File not Found", e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
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
}
