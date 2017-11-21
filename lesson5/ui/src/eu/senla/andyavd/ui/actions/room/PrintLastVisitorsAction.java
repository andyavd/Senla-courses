package eu.senla.andyavd.ui.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class PrintLastVisitorsOfRoomAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintLastVisitorsOfRoomAction.class);

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
