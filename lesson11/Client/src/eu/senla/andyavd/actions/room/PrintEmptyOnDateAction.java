package eu.senla.andyavd.actions.room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.InputReader;

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
