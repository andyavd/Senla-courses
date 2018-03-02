package eu.senla.andyavd.hotelui.ui.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

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