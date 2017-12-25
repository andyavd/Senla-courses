package lesson8.client.ui.src.eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;

public class PrintRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintRoomsAction.class);
	
	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().getRooms());
	}
}