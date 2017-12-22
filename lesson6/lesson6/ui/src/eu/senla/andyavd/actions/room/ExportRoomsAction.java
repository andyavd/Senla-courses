package lesson6.ui.src.eu.senla.andyavd.actions.room;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;

public class ExportRoomsAction implements IAction {

	@Override
	public void execute() {
		HotelManager.getInstance().exportRoomsToCSV();
		Printer.print("All Rooms information was exported to rooms.csv file!");

	}
}