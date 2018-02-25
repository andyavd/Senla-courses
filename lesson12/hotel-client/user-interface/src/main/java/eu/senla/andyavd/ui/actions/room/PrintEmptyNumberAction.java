package eu.senla.andyavd.ui.actions.room;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class PrintEmptyNumberAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintEmptyNumberAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {
		try {
			Request request = new Request("getEmptyRooms", null);

			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);

			request = new Request("getEmptyRoomsNumber", rooms);
			Integer number = (Integer) serverWorker.sendRequest(request);

			Printer.print(number.toString());
		} catch (Exception e) {
			logger.error("Failed to print the number of empty rooms!", e);
		}
	}
}
