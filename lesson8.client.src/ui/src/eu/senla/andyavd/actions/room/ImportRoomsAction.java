package ui.src.eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ImportRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportRoomsAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {
		try {
			
			Request request = new Request("importRoomsFromCSV", null);
			serverWorker.sendRequest(request);
			
			Printer.print("Rooms were successfully imported!");
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
}