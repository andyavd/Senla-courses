package eu.senla.andyavd.hoteladministrator.utils;

import org.apache.log4j.Logger;

import eu.senla.andyavd.di.DependencyInjection;
import eu.senla.andyavd.hoteladministrator.controllers.RoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.controllers.RoomManager;
import eu.senla.andyavd.hoteladministrator.controllers.ServiceManager;
import eu.senla.andyavd.hoteladministrator.controllers.VisitorManager;
import eu.senla.andyavd.properties.Settings;

public class Initializer {

	private static final Logger logger = Logger.getLogger(Initializer.class);
	private static final String PATH = Settings.getInstance().getProperty("saveload");

	private RoomHistoryManager historyManager = (RoomHistoryManager) DependencyInjection.getInjection().getInstance(RoomHistoryManager.class);
	private RoomManager roomManager = (RoomManager) DependencyInjection.getInjection().getInstance(RoomManager.class);
	private ServiceManager serviceManager = (ServiceManager) DependencyInjection.getInjection().getInstance(ServiceManager.class);
	private VisitorManager visitorManager = (VisitorManager) DependencyInjection.getInjection().getInstance(VisitorManager.class);
	
	
	
}
