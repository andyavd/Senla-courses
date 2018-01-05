package ui.src.eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.enums.RoomStars;
import hotel.src.eu.senla.andyavd.enums.RoomStatus;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class AddRoomAction implements IAction {

	final static Logger logger = Logger.getLogger(AddRoomAction.class);

	RoomStars integerToStar(Integer input) {

		RoomStars roomStar = null;
		switch (input) {
		case 1:
			roomStar = RoomStars.STANDARD;
			break;
		case 2:
			roomStar = RoomStars.JUNIOR_SUITE;
			break;
		case 3:
			roomStar = RoomStars.LUX;
			break;
		case 4:
			roomStar = RoomStars.PRESIDENT_LUX;
			break;
		default:
			Printer.print("Wrong input. Please, input a number from 1 to 4.");
		}
		return roomStar;
	}

	RoomStatus integerToStatus(Integer input) {
		RoomStatus roomStatus = null;

		switch (input) {
		case 1:
			roomStatus = RoomStatus.EMPTY;
			break;
		case 2:
			roomStatus = RoomStatus.OCCUPIED;
			break;
		case 3:
			roomStatus = RoomStatus.SERVICED;
			break;
		default:
			Printer.print("Wrong input. Please, input a number from 1 to 3.");
		}
		return roomStatus;
	}

	@Override
	public void execute(ServerWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);
		
		try {
			Integer roomNumber = InputReader.getIntegerInput(scanner, "Input the Room number...");
			Integer capacity = InputReader.getIntegerInput(scanner, "Input the Room capacity...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");
			RoomStars stars = integerToStar(InputReader.getIntegerInput(scanner, "Input the Room rate from 1 to 4..."));
			RoomStatus status = integerToStatus(InputReader.getIntegerInput(scanner,
					"Input the Room status, where 1 = EMPTY, 2 = OCCUPIED, 3 = SERVICED..."));

			Room room = new Room(roomNumber, capacity, dailyPrice, stars, status);

			Request request = new Request("addRoom", room);
			serverWorker.sendRequest(request);

			Printer.print("Room was successfully added!");

		} catch (Exception e) {
			logger.error("Failed to add a Room!", e);
		}
	}
}