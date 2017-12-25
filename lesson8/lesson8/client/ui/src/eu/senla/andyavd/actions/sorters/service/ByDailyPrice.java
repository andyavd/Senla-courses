package lesson8.client.ui.src.eu.senla.andyavd.actions.sorters.service;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;

public class ByDailyPrice implements IAction {

	@Override
	public void execute() {

		Printer.printList(HotelManager.getInstance().sortServicesByPrice());

	}

}
