package eu.senla.andyavd.ui.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;

public class AddRoomAction implements IAction {

	private final static Logger logger = Logger.getLogger(AddRoomAction.class);

	String integerToStar(Integer input) {

		String roomStar = null;
		switch (input) {
		case 1:
			roomStar = "Standard";
			break;
		case 2:
			roomStar = "Juniour_Suite";
			break;
		case 3:
			roomStar = "Lux";
			break;
		case 4:
			roomStar = "President_Lux";
			break;
		default:
			Printer.print("Wrong input. Please, input a number from 1 to 4.");
		}
		return roomStar;
	}

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			int roomNumber = InputReader.getIntegerInput(scanner, "Input the Room number...");
			int capacity = InputReader.getIntegerInput(scanner, "Input the Room capacity...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");
			String stars = integerToStar(InputReader.getIntegerInput(scanner, "Input the Room rate from 1 to 4..."));

			Room room = new Room(roomNumber, capacity, dailyPrice, stars);

			Request request = new Request("addRoom", room);
			serverWorker.sendRequest(request);

			Printer.print("Room was successfully added!");

		} catch (Exception e) {
			logger.error("Failed to add a Room!", e);
		}
	}
}