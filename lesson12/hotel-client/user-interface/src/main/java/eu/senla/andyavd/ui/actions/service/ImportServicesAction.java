package eu.senla.andyavd.ui.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ImportServicesAction implements IAction {

	private final static Logger logger = Logger.getLogger(ImportServicesAction.class);

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