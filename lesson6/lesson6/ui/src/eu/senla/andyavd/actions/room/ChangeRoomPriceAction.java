package lesson6.ui.src.eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class ChangeRoomPriceAction implements IAction {

	final static Logger logger = Logger.getLogger(ChangeRoomPriceAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		try {
			Printer.printList(HotelManager.getInstance().getRooms());

			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Room daily price...");

			HotelManager.getInstance().changePriceOnRoom(HotelManager.getInstance().getRoomById(id), dailyPrice);

		} catch (Exception e) {
			logger.error("No such room to change the Price!", e);
		}

	}
}