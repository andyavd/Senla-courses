package eu.senla.andyavd.ui.actions.room;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.readers.CSVToRoom;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class ImportRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportRoomsAction.class);
	
	@Override
	public void execute() {
		try {
			HotelManager.getInstance().setRooms(FileParser.stringToRooms(CSVToRoom.readRooms()));
			Printer.print("Rooms were successfully imported!");
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
}