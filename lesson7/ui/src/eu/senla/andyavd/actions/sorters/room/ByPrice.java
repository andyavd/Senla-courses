package eu.senla.andyavd.actions.sorters.room;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ByPrice implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().sortRoomsByPrice());

	}

}
