package lesson6.ui.src.eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class PrintDetailsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintDetailsAction.class);

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
