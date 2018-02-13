package eu.senla.andyavd.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ImportServicesAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportServicesAction.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {		
		try {
			
			Request request = new Request("importServicesFromCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("Services were successfully imported!");
		} catch (Exception e) {
			logger.error("Services were not imported!", e);
		}
	}
}