package lesson7.ui.src.eu.senla.andyavd.actions.sorters.visitor;

import lesson7.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson7.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson7.ui.src.eu.senla.andyavd.api.IAction;

public class ByLastName implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortVisitorsByName());

	}
}
