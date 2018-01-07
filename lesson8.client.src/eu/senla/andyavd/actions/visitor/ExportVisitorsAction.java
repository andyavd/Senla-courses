package eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.TransactionWorker;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;

public class ExportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ExportVisitorsAction.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		
		try {

			Request request = new Request("exportVisitorsToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Visitors information was exported to visitors.csv file!");	
			
		} catch (Exception e) {
			logger.error("Failed to save Visitors data!", e);
		}	
	}
}