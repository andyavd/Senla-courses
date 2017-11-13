package eu.senla.andyavd.ui.entityactions.service;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class PrintServices implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().getServices());

	}
}