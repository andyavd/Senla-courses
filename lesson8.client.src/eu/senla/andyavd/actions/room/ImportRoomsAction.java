package eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.TransactionWorker;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;

public class ImportRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportRoomsAction.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		try {
			
			Request request = new Request("importRoomsFromCSV", null);
			serverWorker.sendRequest(request);
			
			Printer.print("Rooms were successfully imported!");
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
}