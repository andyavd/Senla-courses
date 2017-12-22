package lesson6.ui.src.eu.senla.andyavd.actions.room;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

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
