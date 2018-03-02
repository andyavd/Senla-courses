package eu.senla.andyavd.hotelui.ui.actions.room;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class PrintRoomsAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintRoomsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		try {

			Request request = new Request("getRooms", null);

			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);

			if (rooms == null || rooms.size() == 0) {
				Printer.print("There are no Rooms yet!");
			} else {
				Printer.printList(rooms);
			}

		} catch (Exception e) {
			logger.error("Failed print Rooms!", e);
		}
	}
}