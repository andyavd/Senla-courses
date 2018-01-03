package ui.src.eu.senla.andyavd.actions.sorters.room;

import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.api.IAction;

public class EmptyByCapacity implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().sortEmptyRoomsByCapacity());

	}
}
