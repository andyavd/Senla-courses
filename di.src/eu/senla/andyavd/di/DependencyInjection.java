package eu.senla.andyavd.di;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DependencyInjection {

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);

	private static Map<String, Object> classMap = new HashMap<String, Object>();

	public static Object getClassInstance(Class<?> cl) {
		Object obj = null;
		if (classMap.containsKey(cl.getName())) {

			obj = classMap.get(cl.getName());
		} else {
			String implClassName = InstanceProperties.getClassName(cl.getName());
			try {
				Class<?> implClass = Class.forName(implClassName);
				obj = implClass.newInstance();
				classMap.put(cl.getName(), obj);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return obj;
	}
}
