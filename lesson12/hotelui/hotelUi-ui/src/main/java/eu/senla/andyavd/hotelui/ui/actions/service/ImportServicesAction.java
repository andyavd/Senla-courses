package eu.senla.andyavd.hotelui.ui.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

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