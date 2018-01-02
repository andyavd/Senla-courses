package eu.senla.andyavd.actions.service;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}
}