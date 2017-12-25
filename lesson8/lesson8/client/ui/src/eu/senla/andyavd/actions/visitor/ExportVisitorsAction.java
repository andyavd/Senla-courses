package lesson8.client.ui.src.eu.senla.andyavd.actions.visitor;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;

public class ExportVisitorsAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportVisitorsToCSV();
		Printer.print("All Visitors information was exported to visitors.csv file!");		
	}
}