package eu.senla.andyavd.actions.sorters.room;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class EmptyByPrice implements IAction {

	final static Logger logger = Logger.getLogger(EmptyByPrice.class);
	
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
