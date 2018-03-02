package eu.senla.andyavd.hotelui.ui.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class ImportVisitorsAction implements IAction {

	private final static Logger logger = Logger.getLogger(ImportVisitorsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {
		try {

			Request request = new Request("importVisitorsFromCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("Visitors were successfully imported!");
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}