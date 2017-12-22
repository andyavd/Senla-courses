package lesson7.ui.src.eu.senla.andyavd.actions.service;

import lesson7.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson7.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson7.ui.src.eu.senla.andyavd.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}
}