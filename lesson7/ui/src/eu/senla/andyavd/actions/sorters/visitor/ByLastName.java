package eu.senla.andyavd.actions.sorters.visitor;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ByLastName implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortVisitorsByName());

	}
}
