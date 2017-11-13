package eu.senla.andyavd.ui.entityactions.room;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

import java.util.Scanner;

public class AddRoom implements IAction {
	
	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Integer roomNumber = InputReader.getIntegerInput(scanner, "Input the Room number...");
		Integer capacity = InputReader.getIntegerInput(scanner, "Input the Room capacity...");
		Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");
		RoomStars stars = InputReader.getStarsInput(scanner, "Input the Room rate...");
		RoomStatus status = InputReader.getStatusInput(scanner, "Input the Room status...");
		
		HotelManager.getInstance().addRoom(new Room(roomNumber, capacity, dailyPrice, stars, status));;
	}
}