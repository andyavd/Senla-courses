package eu.senla.andyavd.ui.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class PrintRoomDetailsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintRoomDetailsAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getRooms());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");

			StringBuilder s = new StringBuilder();
			s.append("Room details: ");
			s.append(HotelManager.getInstance().getRoomById(id).toString());

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("No such room to show the details!", e);
		}
	}
}
