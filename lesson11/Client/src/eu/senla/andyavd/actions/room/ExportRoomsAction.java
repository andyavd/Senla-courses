package eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ExportRoomsAction implements IAction {

	private final static Logger logger = Logger.getLogger(ExportRoomsAction.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		
		try {

			Request request = new Request("exportRoomsToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Rooms information was exported to rooms.csv file!");

		} catch (Exception e) {
			logger.error("Failed to save Room data!", e);
		}
	}
}