package eu.senla.andyavd.actions.sorters.service;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ByName implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortServicesByName());

	}
}
