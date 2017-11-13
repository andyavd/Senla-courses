package eu.senla.andyavd.ui.entityactions.visitor;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class PrintVisitors implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().getVisitors());
		
	}

}
