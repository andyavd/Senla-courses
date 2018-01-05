package ui.src.eu.senla.andyavd.actions.room;

import java.util.List;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class PrintEmptyNumberAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintEmptyNumberAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {
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
