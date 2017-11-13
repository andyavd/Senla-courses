package eu.senla.andyavd.ui.entityactions.room;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class ChangeRoomPrice implements IAction {

	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getRooms());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
		Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Room daily price...");
		
		HotelManager.getInstance().changePriceOnRoom(HotelManager.getInstance().getRoomById(id), dailyPrice);

	}

}
