package eu.senla.andyavd.hotelui.ui.actions.room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class PrintEmptyOnDateAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintEmptyOnDateAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);
		try {
			LocalDate localDate = InputReader.getLocalDateInput(scanner, "Input the date like \"YYYY-MM-DD\"...");

			Request request = new Request("getEmptyRoomsOnDate", localDate);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);

			Printer.printList(rooms);
		} catch (Exception e) {
			logger.error("Failed to print empty Rooms! Input valid date!", e);
		}
	}
}
