package eu.senla.andyavd.ui.actions.service;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class ExportServicesAction implements IAction {

	@Override
	public void execute() {
		
		HotelManager.getInstance().exportServicesToCSV();
		Printer.print("All Services information was exported to services.csv file!");
		
	}
}