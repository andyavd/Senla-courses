package eu.senla.andyavd.hotelui.ui.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class ExportVisitorsAction implements IAction {

	private final static Logger logger = Logger.getLogger(ExportVisitorsAction.class);

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