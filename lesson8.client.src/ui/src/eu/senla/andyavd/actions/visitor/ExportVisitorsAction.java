package ui.src.eu.senla.andyavd.actions.visitor;

import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportVisitorsAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportVisitorsToCSV();
		Printer.print("All Visitors information was exported to visitors.csv file!");		
	}
}