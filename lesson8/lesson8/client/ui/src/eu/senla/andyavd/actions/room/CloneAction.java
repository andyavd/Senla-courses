package lesson8.client.ui.src.eu.senla.andyavd.actions.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.entities.Room;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

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
				return;
			}
			else {
				cloned.setRoomNumber(newNumber);
				HotelManager.getInstance().addRoom(cloned);
			}
		} catch (Exception e) {
			logger.error("No such room to clone!", e);
		}
	}

}
