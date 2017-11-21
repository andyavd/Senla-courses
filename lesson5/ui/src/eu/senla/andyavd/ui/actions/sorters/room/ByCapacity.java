package eu.senla.andyavd.ui.actions.sorters.room;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class SortRoomsByCapacity implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().sortRoomsByCapacity());

	}
}
