package eu.senla.andyavd.hotel.di;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.annotations.annotations.Storage;

public class DependencyInjection {

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);
	private static DependencyInjection instance;

	private DependencyInjection() {
	}

	public static DependencyInjection getInstance() {
		if (instance == null)
			instance = new DependencyInjection();
		return instance;
	}

	public Object getClassInstance(Class<?> type) {
		Class<?> clazz = null;
		boolean isStorage = false;

		try {

			String implClassName = InstanceProperties.getClassName(type.getName());

			clazz = Class.forName(implClassName);
			if (clazz.isAnnotationPresent(Storage.class))
				isStorage = true;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException ", e);
			return null;
		}

		if (isStorage) {
			try {
				return clazz.getMethod("getInstance").invoke(null);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException ", e);
				return null;
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException ", e);
				return null;
			} catch (NoSuchMethodException e) {
				logger.error("NoSuchMethodException ", e);
				return null;
			}
		} else {
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				logger.error("InstantiationException ", e);
				return null;
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException ", e);
				return null;
			}
		}
	}
}