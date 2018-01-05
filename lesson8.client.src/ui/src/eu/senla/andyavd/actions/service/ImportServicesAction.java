package ui.src.eu.senla.andyavd.actions.service;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ImportServicesAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportServicesAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {		
		try {
			
			Request request = new Request("importServicesFromCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("Services were successfully imported!");
		} catch (Exception e) {
			logger.error("Services were not imported!", e);
		}
	}
}