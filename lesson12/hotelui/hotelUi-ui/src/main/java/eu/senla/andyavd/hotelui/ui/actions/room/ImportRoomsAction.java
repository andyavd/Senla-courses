package eu.senla.andyavd.hotelui.ui.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class ImportRoomsAction implements IAction {

	private final static Logger logger = Logger.getLogger(ImportRoomsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {
		try {

			Request request = new Request("importRoomsFromCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("Rooms were successfully imported!");
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
}