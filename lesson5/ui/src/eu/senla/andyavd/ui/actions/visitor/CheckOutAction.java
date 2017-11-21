package eu.senla.andyavd.ui.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class CheckOutVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(CheckOutVisitorAction.class);
	
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
