package eu.senla.andyavd.ui.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.ui.api.IAction;
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