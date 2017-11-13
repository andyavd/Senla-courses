package eu.senla.andyavd.ui.entityactions.room;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class ChangeRoomStatus implements IAction {

	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getRooms());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
		
		Printer.print(HotelManager.getInstance().changeRoomStatus(HotelManager.getInstance().getRoomById(id)));

	}
}