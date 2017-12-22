package lesson6.ui.src.eu.senla.andyavd.actions.sorters.visitor;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;

public class ByLastName implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortVisitorsByName());

	}
}
