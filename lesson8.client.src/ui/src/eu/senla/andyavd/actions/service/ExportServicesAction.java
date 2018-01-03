package ui.src.eu.senla.andyavd.actions.service;

import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}
}