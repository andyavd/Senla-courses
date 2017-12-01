package eu.senla.andyavd.ui.actions.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.entities.Room;
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

			Room cloned = HotelManager.getInstance().cloneRoom(HotelManager.getInstance().getRoomById(id));
			Integer newNumber = InputReader.getIntegerInput(scanner, "Input the new room number for a cloned room...");

			List<Room> rooms = HotelManager.getInstance().getRooms();
			List<Integer> numbers = new ArrayList<Integer>();
			for (int i = 0; i < rooms.size(); i++) {
				Room room = rooms.get(i);
				numbers.add(room.getRoomNumber());
			}
			if (numbers.contains(newNumber)) {
				Printer.print("Such room number id already used! Cloning fails. Try again!");
//				HotelManager.getInstance().deleteRoom(HotelManager.getInstance().getRoomById(0));
				return;
			}
			// if (room.getRoomNumber() == newNumber) {
			// rooms.get(rooms.size() - 1).setRoomNumber(newNumber);
			//
			// }
			else {
				cloned.setRoomNumber(newNumber);
				HotelManager.getInstance().addRoom(cloned);
			}
		} catch (Exception e) {
			logger.error("No such room to clone!", e);
		}
	}

}
