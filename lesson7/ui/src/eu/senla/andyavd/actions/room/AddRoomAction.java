package eu.senla.andyavd.actions.room;

import eu.senla.andyavd.entities.Room;
import eu.senla.andyavd.enums.RoomStars;
import eu.senla.andyavd.enums.RoomStatus;
import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

import java.util.Scanner;

import org.apache.log4j.Logger;

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
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		try {
			Integer roomNumber = InputReader.getIntegerInput(scanner, "Input the Room number...");
			Integer capacity = InputReader.getIntegerInput(scanner, "Input the Room capacity...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");
			RoomStars stars = integerToStar(InputReader.getIntegerInput(scanner, "Input the Room rate from 1 to 4..."));
			RoomStatus status = integerToStatus(InputReader.getIntegerInput(scanner,
					"Input the Room status, where 1 = EMPTY, 2 = OCCUPIED, 3 = SERVICED..."));
			HotelManager.getInstance().addRoom(new Room(roomNumber, capacity, dailyPrice, stars, status));

		} catch (Exception e) {
			logger.error("Failed to add a Room! Input valid parameters!", e);
		}
	}
}