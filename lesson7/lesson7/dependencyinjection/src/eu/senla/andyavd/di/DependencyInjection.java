package lesson7.dependencyinjection.src.eu.senla.andyavd.di;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import lesson7.annotations.src.eu.senla.andyavd.annotations.Storage;
import lesson7.properties.src.eu.senla.andyavd.properties.Settings;

public class DependencyInjection {

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);
	private static final String INSTANCEPATH = Settings.getInstance().getProperty("instancepath");
//	private static final String INSTANCEPATH = "/Users/andreiaudzeichyk/Senla-courses-Ready/resources/data/instance.properties";
	private static DependencyInjection instance;
	
	private Map<String, String> dependencies = new HashMap<>();

	private DependencyInjection() {
        loadDependencies();
    }
	
	public static DependencyInjection getInstance() {
        if (instance == null) instance = new DependencyInjection();
        return instance;
    }
	
	@SuppressWarnings("unchecked")
	private void loadDependencies() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(INSTANCEPATH)) {
            properties.load(fileInputStream);
            dependencies = (Map<String, String>) properties.clone();
        } catch (IOException e) {
            logger.error("Properties were not loaded!", e);
        }
    }
	
	public Object getInstance(Class<?> type) {

        Class<?> cl = null;
        boolean isStorage = false;

        try {
            cl = Class.forName(dependencies.get(type.getName()));
            if (cl.isAnnotationPresent(Storage.class)) isStorage = true;
        } catch (ClassNotFoundException e) {
            logger.error("Class NotFound Exception!", e);
            return null;
        }

        if (isStorage) {
            try {
                return cl.getMethod("getInstance").invoke(null);
            } catch (IllegalAccessException e) {
            	logger.error("Illegal Access!", e);
                return null;
            } catch (InvocationTargetException e) {
            	logger.error("Invocation Target!", e);
                return null;
            } catch (NoSuchMethodException e) {
            	logger.error("No Such Method!", e);
                return null;
            }
            
        } else {
            try {
                return cl.newInstance();
            } catch (InstantiationException e) {
            	logger.error("Instantiation!", e);
                return null;
            } catch (IllegalAccessException e) {
            	logger.error("Illegal Access!", e);
                return null;
            }
        }
    }

	
}
