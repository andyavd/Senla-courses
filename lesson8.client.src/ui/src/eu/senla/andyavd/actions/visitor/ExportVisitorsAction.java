package ui.src.eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ExportVisitorsAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {
		
		try {

			Request request = new Request("exportVisitorsToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Visitors information was exported to visitors.csv file!");	
			
		} catch (Exception e) {
			logger.error("Failed to save Visitors data!", e);
		}	
	}
}