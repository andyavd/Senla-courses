package eu.senla.andyavd.actions.visitor;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

public class CheckInAction implements IAction {

	private final static Logger logger = Logger.getLogger(CheckInAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		Printer.printList(HotelManager.getInstance().getRooms());

		Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id to check-in...");
		Integer roomId = InputReader.getIntegerInput(scanner, "Input the Room id...");
		LocalDate inDate = InputReader.getLocalDateInput(scanner, "Input the in-date like \"YYYY-MM-DD\"...");
		LocalDate outDate = InputReader.getLocalDateInput(scanner, "Input the out-date like \"YYYY-MM-DD\"...");

		try {

			HotelManager.getInstance().checkInVisitor(HotelManager.getInstance().getVisitorById(visitorId),
					HotelManager.getInstance().getRoomById(roomId), inDate, outDate);

			StringBuilder s = new StringBuilder();
			s.append(HotelManager.getInstance().getVisitorById(visitorId).getLastName());
			s.append(" was checked-in. Room #");
			s.append(HotelManager.getInstance().getRoomById(roomId).getRoomNumber());

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("Failed to chek-in the visitor! Input valid parameters!", e);
		}
	}
}
