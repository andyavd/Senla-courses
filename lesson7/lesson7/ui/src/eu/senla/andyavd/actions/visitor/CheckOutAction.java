package lesson7.ui.src.eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson7.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson7.hotel.src.eu.senla.andyavd.utils.exceptions.EmptyRoomException;
import lesson7.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson7.ui.src.eu.senla.andyavd.api.IAction;
import lesson7.ui.src.eu.senla.andyavd.utils.InputReader;

public class CheckOutAction implements IAction {

	final static Logger logger = Logger.getLogger(CheckOutAction.class);
	
	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getVisitors());
		Printer.printList(HotelManager.getInstance().getRooms());

		Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id to check-out...");
		Integer roomId = InputReader.getIntegerInput(scanner, "Input the Room id...");
		
		try {
			
			HotelManager.getInstance().checkOutVisitor(HotelManager.getInstance().getVisitorById(visitorId),
					HotelManager.getInstance().getRoomById(roomId));
			
			StringBuilder s = new StringBuilder();
			s.append(HotelManager.getInstance().getVisitorById(visitorId).getLastName());
			s.append(" has checked-out from Room #");
			s.append(HotelManager.getInstance().getRoomById(roomId).getRoomNumber());

			Printer.print(s.toString());
		} catch (EmptyRoomException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
