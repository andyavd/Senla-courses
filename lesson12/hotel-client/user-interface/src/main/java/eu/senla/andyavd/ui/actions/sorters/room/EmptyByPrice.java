package eu.senla.andyavd.ui.actions.sorters.room;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class EmptyByPrice implements IAction {

	private final static Logger logger = Logger.getLogger(EmptyByPrice.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		try {

			Request request = new Request("sortEmptyRoomsByPrice", null);

			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);

			if (rooms == null || rooms.size() == 0) {
				Printer.print("There are no Rooms yet!");
			} else {
				Printer.printList(rooms);
			}

		} catch (Exception e) {
			logger.error("Failed print empty Rooms!", e);
		}
	}
}
