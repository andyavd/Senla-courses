package eu.senla.andyavd.actions.visitor;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ExportVisitorsAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportVisitorsToCSV();
		Printer.print("All Visitors information was exported to visitors.csv file!");		
	}
}