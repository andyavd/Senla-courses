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

public class PrintDetailsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintDetailsAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			
			Request request = new Request("getRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);
			
			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
			
			request = new Request("getRoomById", id);
			Room room = (Room) serverWorker.sendRequest(request);
			
			StringBuilder s = new StringBuilder();
			s.append("Room details: ");
			s.append(room.toString());

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("No such room to show the details!", e);
		}
	}
}
