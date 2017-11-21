package eu.senla.andyavd.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Settings {
	private static final Logger logger = Logger.getLogger(Settings.class);
	private final String FILENAME = "config.properties";
	private static Properties properties = new Properties();
	private static InputStream input = null;

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
			input = Settings.class.getClassLoader().getResourceAsStream(FILENAME);
			properties.load(input);
		} catch (FileNotFoundException e) {
			logger.error("File not Found", e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
