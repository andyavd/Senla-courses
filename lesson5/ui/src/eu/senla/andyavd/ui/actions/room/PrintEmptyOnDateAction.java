package eu.senla.andyavd.ui.actions.room;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class PrintEmptyRoomsOnDateAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintEmptyRoomsOnDateAction.class);
	
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
