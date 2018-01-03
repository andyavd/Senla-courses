package ui.src.eu.senla.andyavd.actions.room;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.Response;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class PrintRoomsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintRoomsAction.class);
	
	@Override
	public void execute() {
		
		Request request = new Request("getRooms", null);
		Response response = ServerWorker.getInstance().sendMessage(request);

		if (response.isAccepted()) {
			Printer.print("The list of the Rooms is the following:");
		} else {
			Printer.print("Failed to print the Rooms!");
		}
	}
}