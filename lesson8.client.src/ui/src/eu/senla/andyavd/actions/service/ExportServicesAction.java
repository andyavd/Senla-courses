package ui.src.eu.senla.andyavd.actions.service;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportServicesAction implements IAction {

	final static Logger logger = Logger.getLogger(ExportServicesAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {
		
		try {

			Request request = new Request("exportServicesToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Services information was exported to services.csv file!");

		} catch (Exception e) {
			logger.error("Failed to save Services data!", e);
		}
	}
}