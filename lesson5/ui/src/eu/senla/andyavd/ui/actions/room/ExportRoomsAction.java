package eu.senla.andyavd.ui.actions.room;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class ExportRoomsAction implements IAction {

	@Override
	public void execute() {
		HotelManager.getInstance().exportRoomsToCSV();
		Printer.print("All Rooms information was exported to rooms.csv file!");

	}
}