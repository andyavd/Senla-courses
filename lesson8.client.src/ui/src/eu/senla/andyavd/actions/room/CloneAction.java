package ui.src.eu.senla.andyavd.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class CloneAction implements IAction {

	final static Logger logger = Logger.getLogger(CloneAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			
			Request request = new Request("getRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);

			Integer id = InputReader.getIntegerInput(scanner, "Input Room id...");
			request = new Request("getRoomById", id);
			Room room = (Room) serverWorker.sendRequest(request);

			request = new Request("cloneRoom", room);
			serverWorker.sendRequest(request);
			
			Printer.print("The Room was successfully cloned!");
			
		} catch (Exception e) {
			logger.error("No such room to clone!", e);
		}
	}

}
