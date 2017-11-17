package eu.senla.andyavd.ui.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class PrintEmptyRoomsNumberAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintEmptyRoomsNumberAction.class);
	
	@Override
	public void execute() {
		
		Printer.print(HotelManager.getInstance().getEmptyRoomsNumber(HotelManager.getInstance().getEmptyRooms(HotelManager.getInstance().getRooms())).toString());

	}
}