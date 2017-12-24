package lesson6.ui.src.eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class PrintLastVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintLastVisitorsAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getRooms());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
			Printer.printList(
					HotelManager.getInstance().getLastVisitorsOfRoom(HotelManager.getInstance().getRoomById(id)));
		} catch (Exception e) {
			logger.error("Failed to chek-in the visitor! Input existing Room!", e);
		}
	}

}
