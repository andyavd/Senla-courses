package lesson6.ui.src.eu.senla.andyavd.actions.sorters.room;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;

public class ByStars implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().sortRoomsByStars());

	}
}
