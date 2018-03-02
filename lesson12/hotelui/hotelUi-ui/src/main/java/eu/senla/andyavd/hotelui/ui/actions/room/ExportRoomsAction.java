package eu.senla.andyavd.hotelui.ui.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class ExportRoomsAction implements IAction {

	private final static Logger logger = Logger.getLogger(ExportRoomsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		try {

			Request request = new Request("exportRoomsToCSV", null);
			serverWorker.sendRequest(request);

			Printer.print("All Rooms information was exported to rooms.csv file!");

		} catch (Exception e) {
			logger.error("Failed to save Room data!", e);
		}
	}
}