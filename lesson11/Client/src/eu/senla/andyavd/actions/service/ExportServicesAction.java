package eu.senla.andyavd.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ExportServicesAction implements IAction {

	private final static Logger logger = Logger.getLogger(ExportServicesAction.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		
		try {

			Request request = new Request("exportServicesToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Services information was exported to services.csv file!");

		} catch (Exception e) {
			logger.error("Failed to save Services data!", e);
		}
	}
}