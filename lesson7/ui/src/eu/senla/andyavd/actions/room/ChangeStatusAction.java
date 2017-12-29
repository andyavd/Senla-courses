package eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

public class ChangeStatusAction implements IAction {

	final static Logger logger = Logger.getLogger(ChangeStatusAction.class);
	
	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getRooms());
		try {
		Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
		HotelManager.getInstance().changeRoomStatus(HotelManager.getInstance().getRoomById(id));
		} catch (Exception e) {
			logger.error("No such room to change the Status!", e);
		}
	}
}
