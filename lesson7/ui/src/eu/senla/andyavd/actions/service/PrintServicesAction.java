package eu.senla.andyavd.actions.service;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class PrintServicesAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintServicesAction.class);
	
	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().getServices());

	}
}