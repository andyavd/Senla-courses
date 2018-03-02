package eu.senla.andyavd.hotel.server;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;

public class Invoker {

	private static final Logger logger = Logger.getLogger(Invoker.class);

	public static Object invokeHotelManager(String methodName, Object parameters) {

		IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance().getInstance(IHotelManager.class);
		Object response = null;

		try {
			if (parameters != null) {
				response = hotelManager.getClass().getMethod(methodName, Object.class).invoke(hotelManager, parameters);
			} else {
				response = hotelManager.getClass().getMethod(methodName, new Class[] {}).invoke(hotelManager);
			}
		} catch (NoSuchMethodException e) {
			logger.error("No such Method!", e);
		} catch (IllegalAccessException e) {
			logger.error("Illegal Access!", e);
		} catch (InvocationTargetException e) {
			logger.error("Invocation Target!", e);
		}

		return response;
	}
}