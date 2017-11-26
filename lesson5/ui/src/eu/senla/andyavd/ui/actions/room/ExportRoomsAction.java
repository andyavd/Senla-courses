package eu.senla.andyavd.ui.actions.room;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers.RoomHistoryToCSV;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers.RoomToCSV;
import eu.senla.andyavd.ui.api.IAction;

public class ExportRoomsAction implements IAction {

	@Override
	public void execute() {
		
		RoomToCSV.writeRoomsToCSV();
		RoomHistoryToCSV.writeHistoriesToCSV();
		Printer.print("All Rooms information was exported to rooms.csv file!");
		
	}
}