package eu.senla.andyavd.ui.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class ImportServicesAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportServicesAction.class);
	
	@Override
	public void execute() {		
		try {
			HotelManager.getInstance().importServicesFromCSV();
			Printer.print("Services were successfully imported!");
		} catch (Exception e) {
			logger.error("Services were not imported!", e);
		}
	}
}