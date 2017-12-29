package eu.senla.andyavd.actions.room;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ExportRoomsAction implements IAction {

	@Override
	public void execute() {
		HotelManager.getInstance().exportRoomsToCSV();
		Printer.print("All Rooms information was exported to rooms.csv file!");

	}
}