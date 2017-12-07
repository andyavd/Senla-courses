package eu.senla.andyavd.di;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import eu.senla.andyavd.properties.Settings;

public class InstanceProperties {
	private static InstanceProperties instanceProperties;

    private Properties properties = new Properties();

    public static InstanceProperties getInstance() {
        if (instanceProperties == null) {
        	instanceProperties = new InstanceProperties();
        }
        return instanceProperties;
    }

    public InstanceProperties() {
        try (FileInputStream stream = new FileInputStream(Settings.getProps().getPathToInstanceFile())) {
            properties.load(stream);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRealization(String className) {
        return properties.getProperty(className);
    }
}
