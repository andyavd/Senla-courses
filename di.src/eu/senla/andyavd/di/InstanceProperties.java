package eu.senla.andyavd.di;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import eu.senla.andyavd.properties.Settings;

public class InstanceProperties {

	private static final Logger logger = Logger.getLogger(InstanceProperties.class);
	private static InstanceProperties instanceProperties;
	private Properties properties = new Properties();

	public static InstanceProperties getInstance() {
		if (instanceProperties == null) {
			instanceProperties = new InstanceProperties();
		}
		return instanceProperties;
	}

	public InstanceProperties() {
		try (FileInputStream input = new FileInputStream(Settings.getCustomProperties().getEntityPath())) {
			properties.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getRealization(String className) {
		return properties.getProperty(className);
	}
}
