package hotel.src.eu.senla.andyavd.server;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import hotel.src.eu.senla.andyavd.view.HotelManager;

public class Invoker {

	private static final Logger logger = Logger.getLogger(Invoker.class);
	
	public static Object invokeHotelManager(String methodName, Object parameters) {
		
		HotelManager hotelManager = (HotelManager) DependencyInjection.getInstance().getInstance(HotelManager.class);
		Object response = null;
		
		try {
			if(parameters != null) {	
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