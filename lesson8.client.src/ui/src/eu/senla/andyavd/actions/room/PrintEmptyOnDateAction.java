package ui.src.eu.senla.andyavd.actions.room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class PrintEmptyOnDateAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintEmptyOnDateAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {

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
