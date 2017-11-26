package eu.senla.andyavd.ui.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class CloneAction implements IAction {

	final static Logger logger = Logger.getLogger(CloneAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getRooms());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id you wnt to clone...");

			HotelManager.getInstance().cloneRoom(HotelManager.getInstance().getRoomById(id));
			Integer newNumber = InputReader.getIntegerInput(scanner, "Input the new room number for a cloned room...");
			for (int i = 0; i < HotelManager.getInstance().getRooms().size(); i++) {
				if (HotelManager.getInstance().getRooms().get(i).getRoomNumber() != newNumber) {
					HotelManager.getInstance().getRooms().get(HotelManager.getInstance().getRooms().size() - 1)
							.setRoomNumber(newNumber);
				}
				else {
					Printer.print("Such room number id already used! Try again!");
				}
			}

		} catch (Exception e) {
			logger.error("No such room to clone!", e);
		}
	}

}
