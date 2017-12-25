package lesson8.client.ui.src.eu.senla.andyavd.actions.service;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}
}