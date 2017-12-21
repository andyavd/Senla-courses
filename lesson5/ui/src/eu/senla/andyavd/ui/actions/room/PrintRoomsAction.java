package eu.senla.andyavd.ui.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class PrintRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintRoomsAction.class);
	
	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().getRooms());
	}
}