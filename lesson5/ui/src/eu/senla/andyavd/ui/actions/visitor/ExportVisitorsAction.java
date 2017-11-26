package eu.senla.andyavd.ui.actions.visitor;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers.VisitorToCSV;
import eu.senla.andyavd.ui.api.IAction;

public class ExportVisitorsAction implements IAction {

	@Override
	public void execute() {
		
		VisitorToCSV.writeVisitorsToCSV();
		Printer.print("All Visitors information was exported to visitors.csv file!");		
	}
}