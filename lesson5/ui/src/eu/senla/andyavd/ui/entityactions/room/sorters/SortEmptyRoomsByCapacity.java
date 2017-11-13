package eu.senla.andyavd.ui.entityactions.room.sorters;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class SortEmptyRoomsByCapacity implements IAction {

	@Override
	public void execute() {
		
		Printer.printList(HotelManager.getInstance().sortEmptyRoomsByCapacity());

	}
}