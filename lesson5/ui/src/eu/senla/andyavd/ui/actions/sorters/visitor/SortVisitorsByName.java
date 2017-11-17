package eu.senla.andyavd.ui.actions.sorters.visitor;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class SortVisitorsByName implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortVisitorsByName());

	}
}