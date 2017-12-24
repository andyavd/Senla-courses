package lesson7.ui.src.eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import lesson7.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson7.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson7.ui.src.eu.senla.andyavd.api.IAction;

public class PrintVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintVisitorsAction.class);
	
	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().getVisitors());
		
	}

}