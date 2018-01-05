package ui.src.eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(ExportRoomsAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {
		
		try {

			Request request = new Request("exportRoomsToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Rooms information was exported to rooms.csv file!");

		} catch (Exception e) {
			logger.error("Failed to save Room data!", e);
		}
	}
}