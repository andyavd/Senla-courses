package ui.src.eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ImportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportVisitorsAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {
		try {
			
			Request request = new Request("importVisitorsFromCSV", null);
			serverWorker.sendRequest(request);
			
			Printer.print("Visitors were successfully imported!");
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}