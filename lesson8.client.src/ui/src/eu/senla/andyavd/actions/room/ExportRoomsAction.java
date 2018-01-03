package ui.src.eu.senla.andyavd.actions.room;

import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportRoomsAction implements IAction {

	@Override
	public void execute() {
		HotelManager.getInstance().exportRoomsToCSV();
		Printer.print("All Rooms information was exported to rooms.csv file!");

	}
}