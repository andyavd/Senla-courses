package lesson6.ui.src.eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;

public class ImportRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportRoomsAction.class);
	
	@Override
	public void execute() {
		try {
			HotelManager.getInstance().importRoomsFromCSV();
			Printer.print("Rooms were successfully imported!");
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
}