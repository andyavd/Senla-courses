package eu.senla.andyavd.di;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DependencyInjection {

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);
	private static Map<Class<?>, Object> instanceMap = new HashMap<>();
	private static InstanceProperties instanceProperties = new InstanceProperties();

	public DependencyInjection() {
	}

	private static DependencyInjection dependencyInjection;

	public static DependencyInjection getInjection() {
		if (dependencyInjection == null) {
			dependencyInjection = new DependencyInjection();
		}

		return dependencyInjection;
	}

	public Object getInstance(Class<?> className) {
		Object object = instanceMap.get(className);

		if (object == null) {
			String instanceName = instanceProperties.getRealization(className.getSimpleName());

			try {
				Class<?> instanceClass = Class.forName(instanceName);
				Object obj = instanceClass.newInstance();
				instanceMap.put(className, obj);
				return obj;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return object;
	}
}
