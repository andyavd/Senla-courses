package eu.senla.andyavd.ui.entityactions.visitor;

import java.time.LocalDate;
import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class CheckInVisitor implements IAction {

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
			Printer.print(HotelManager.getInstance().checkInVisitor(HotelManager.getInstance().getVisitorById(visitorId),
					HotelManager.getInstance().getRoomById(roomId), inDate, outDate));
		} catch (NotEmptyRoomException e) {
			e.printStackTrace();
		}
	}
}