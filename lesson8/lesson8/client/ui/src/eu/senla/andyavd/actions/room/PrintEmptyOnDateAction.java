package lesson8.client.ui.src.eu.senla.andyavd.actions.room;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

public class PrintEmptyOnDateAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintEmptyOnDateAction.class);
	
	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
try {
		LocalDate localDate = InputReader.getLocalDateInput(scanner, "Input the date like \"YYYY-MM-DD\"...");
		Printer.printList(HotelManager.getInstance().getEmptyRoomsOnDate(localDate));
} catch (Exception e) {
	logger.error("Failed to print empty Rooms! Input valid date!", e);
}
	}
}
