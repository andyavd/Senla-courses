package eu.senla.andyavd.ui.actions.service;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers.ServiceToCSV;
import eu.senla.andyavd.ui.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		ServiceToCSV.writeServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}

}
